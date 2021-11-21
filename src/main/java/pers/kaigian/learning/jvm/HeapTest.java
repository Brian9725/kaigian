package pers.kaigian.learning.jvm;

import java.util.ArrayList;

/**
 * @Author BrianHu
 * @Create 2021-07-15 15:45
 **/
public class HeapTest {
	byte[] a = new byte[1024 * 100];

	public static void main(String[] args) throws InterruptedException {
		ArrayList<HeapTest> heapTests = new ArrayList<>();
		while (true) {
			heapTests.add(new HeapTest());
			Thread.sleep(10);
		}
	}
}
