package pers.kaigian.learning.jvm;

/**
 * @author BrianHu
 * @create 2021-04-01 16:29
 **/
public class VolatileTest {
    public static volatile int cnt = 0;

    public static void main(String[] args) {
        cnt++;
        Thread thread = new Thread(() -> {
            System.out.println("hh");
        }, "a");
        thread.start();
    }
}
