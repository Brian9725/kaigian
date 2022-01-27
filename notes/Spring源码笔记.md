# Spring源码阅读笔记

spring-version：5.3.10

## Spring启动流程

### bean扫描：

ClassPathBeanDefinitionScanner.scan()

通过findCandidateComponents()找到所有的beanDefinition

    1.resource可读（class文件）
    2.判断注解
        1）不能有excludeFilters中的注解
        2）必须有includeFilters中的注解（默认有Component）
        3）匹配条件注解（@Condition）
    3.只有顶级类（非内部类）、非抽象类和含有@Lookup注解方法的抽象类才能成为一个bean

处理找到的beanDefinition

    1.设置scope（singleton，prototype，request，session）
    2.生成beanName（默认情况下用Introspector.decapitalize(shortClassName)生成：只要不是开头连续两个大写字母，首字母小写）
    3.给其他注解赋初值（@Lazy，@Primary等）
    4.解析@Lazy、@Primary、@DependsOn、@Role、@Description
    5.检查是否已经有这个beanDefinition（如果resource相同则认为是同一个beanDefinition）
    6.将beanDefinition放入beanDefinitionMap缓存起来

### bean生成：

AbstractApplicationContext.refresh()
    ->finishBeanFactoryInitialization(beanFactory)
    ->beanFactory.preInstantiateSingletons()

AnnotationApplicationContext使用的是DefaultListableBeanFactory

beanFactory.preInstantiateSingletons()

    1.合并beanDefinition（可能有一些beanDefinition有父beanDefinition，将其合并为RootBeanDefinition）
    2.只有非抽象非懒加载的单例bean才会在spring容器启动时创建
    3.专门处理FactoryBean类型的bean
        1）如何判断是否是FactoryBean：
            （1）如果该bean已经被创建，直接instance of
            （2）如果当前容器没有这个bean则查看父容器是否知晓
            （3）beanDefinition中有isFactoryBean字段缓存
            （4）获取该bean的Class来判断，如果该类被加载直接getBeanClass，如果没有则ASM
        2）FactoryBean需要先创建本身的bean对象，如果实现了SmartFactoryBean接口并且isEagerInit返回true，立刻调用getObject方法创建bean
    4.调用getBean方法
    5.所有的非懒加载单例Bean都创建完了后，如果有bean实现了SmartInitializingSingleton接口，则调用SmartInitializingSingleton.afterSingletonsInstantiated()方法

getBean方法
    
    1.先从单例池拿，如果拿到：
        1）如果beanName以&开头，说明想拿FactoryBean类型的bean，判断单例池拿到的bean是否符合，符合则返回，不符合抛异常
        2）如果拿到的bean不是FactoryBean类型的，可以直接返回
        3）此时，单例池拿到的bean是FactoryBean，并且beanName没有以&开头，说明想调getObject方法
    ***todo***
    2.如果当前容器没有这个bean的beanDefinition，则让父容器创建
    3.获取RootBeanDefinition并检查beanDefinition是否是抽象的
    4.处理@DependsOn注解（如果有循环依赖则抛异常，这种循环依赖是没法解决的）
    5.分情况处理singleton，prototype，和其他类型的bean，下面以singleton为例：
    6.确保beanClass被加载
    ***todo***使用的类加载器？？？
    7.实例化前操作
        1）挨个调用InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()方法
        2）如果返回了一个非空的bean直接走“初始化后”逻辑并返回这个bean
    8.实例化这个bean
    ***todo***推断构造方法？？？
    9.后置处理合并后的BeanDefinition：
        1）调用MergedBeanDefinitionPostProcessor.postProcessMergedBeanDefinition()方法
        2）这里可以对BeanDefinition进行操作，比如设置属性值，后面就会用设置的属性值填充属性
    10.属性填充
        1）实例化后操作：调用InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation()方法
        2）处理AutowireMode：AUTOWIRE_BY_NAME，AUTOWIRE_BY_TYPE；默认是AUTOWIRE_NO，这种方法基本不用了（这种是将属性放到pvs集合中，并不是立刻设置属性）
        3）调用InstantiationAwareBeanPostProcessor.postProcessProperties()方法
            （1）@Autowired，@Value，@Inject由AutowiredAnnotationBeanPostProcessor负责处理（pvs中有值了则不会处理该属性）
            （2）@Resource由CommonAnnotationBeanPostProcessor负责处理
        4）将pvs集合中的属性设置进去
    11.初始化
        1）初始化前：调用BeanPostProcessor.postProcessBeforeInitialization()方法
        2）初始化：
            （1）如果实现了InitializingBean接口则调用afterPropertiesSet方法
            （2）如果设置了initMethod则调用initMethod
        3）初始化后：调用BeanPostProcessor.postProcessAfterInitialization()方法
        ***todo***AOP实现？？？
    12.bean销毁相关：registerDisposableBeanIfNecessary(beanName, bean, mbd)
    ***todo***
    13.如果有requiredType则检查通过name所获得到的beanInstance的类型是否是requiredType


@Autowired自动注入

    1.是通过AutowiredAnnotationBeanPostProcessor完成的
        1）该类实现了MergedBeanDefinitionPostProcessor接口，重写了postProcessMergedBeanDefinition()方法
        2）该类实现了SmartInstantiationAwareBeanPostProcessor接口，是InstantiationAwareBeanPostProcessor的子接口，重写了postProcessProperties()方法
    2.postProcessMergedBeanDefinition()方法
        1）目的是为了找出bean当中的所有注入点，也就是被@Autowired注解的字段和方法
        2）不仅仅会找出当前类被注释的字段和方法，也会通过循环找出父类中的
        3）static字段和static方法不会被标记为注入点
        4）即使方法没有入参，只要被注释也是一个注入点，依赖注入时会调用
    3.postProcessProperties()方法
        1）遍历postProcessMergedBeanDefinition()方法找到的注入点
        2）找到对应的bean
        3）利用反射机制设置属性
