package pers.kaigian.learning.jvm;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hukaiyang
 * @date 2021-05-18 13:59
 **/
public class HashMapTest {

	public static void main(String[] args) {
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}


class MyThread extends Thread {

	private static Map map = new ConcurrentHashMap();

	private static AtomicInteger ai = new AtomicInteger(1);

	@Override
	public void run() {
		while (ai.get() < 10000) {
			synchronized (map) {
				map.put(ai.get(), ai.get());
				ai.getAndIncrement();
			}
			System.out.println("ai is:" + ai.get());
		}
		map.forEach((k, v) -> System.out.println("key is:" + k));
		System.out.println("map size is:" + map.size());
	}
}
