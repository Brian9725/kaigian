package pers.kaigian.learning.jvm;

import sun.misc.Launcher;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.URL;

/**
 * @author brian
 * @date 2021-03-22 13:37
 **/
public class ClassLoaderTest {
	class Apple {
		int weight;
	}

	public static void main(String[] args) {
		System.out.println("String类的类加载器：" + String.class.getClassLoader());
		System.out.println("DNSNameService类的类加载器：" + DNSNameService.class.getClassLoader());
		System.out.println("Apple类的类加载器：" + Apple.class.getClassLoader());
		System.out.println("----------------------分割线----------------------");
		URL[] urls = Launcher.getBootstrapClassPath().getURLs();
		System.out.println("bootstrapLoader加载路径如下：");
		for (URL url : urls) {
			System.out.println(url);
		}
		System.out.println("----------------------分割线----------------------");
		System.out.println("extClassLoader加载路径如下：" + System.getProperty("java.ext.dirs"));
		// appClassLoader加载路径较长，建议复制到txt文本中查看
		System.out.println("appClassLoader加载路径如下：" + System.getProperty("java.class.path"));
		System.out.println("系统默认的类加载器：" + ClassLoader.getSystemClassLoader());
	}
}
