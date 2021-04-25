package pers.kaigian.learning.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author hukaiyang
 * @date 2021-03-31 15:16
 **/
public class ClassHeaderTest {
	public static void main(String[] args) {
		ClassLayout classLayout = ClassLayout.parseInstance(new Person());
		System.out.println(classLayout.toPrintable());

	}
}

class Person{
	int age;
	boolean sex;
	String name;
	byte aByte;
	byte bByte;
	Boolean isEnd;
}
