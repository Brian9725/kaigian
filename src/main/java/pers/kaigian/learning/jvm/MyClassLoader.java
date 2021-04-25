package pers.kaigian.learning.jvm;

/**
 * @author hukaiyang
 * @date 2021-03-29 18:21
 **/
public class MyClassLoader extends ClassLoader {

	static int a = 7;

	static {
		a = 8;
	}

	public MyClassLoader() {
		System.out.println(a);
	}

	public static void main(String[] args) {
		System.out.println(a);
	}
}
