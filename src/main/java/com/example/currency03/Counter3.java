package com.example.currency03;
/**
 * 3、运用thread、synchronized、线程join
 */
public class Counter3 {
    public static Integer inc = 0;
    public synchronized void increase() {
        inc++;
    }
    public static void count() throws InterruptedException {
        final Counter3 c3 = new Counter3();
        Thread th;
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            th = new Thread(() -> {
                for(int j = 0; j < 100000; j++) {
                    c3.increase();
                }
            });
            th.start();
            th.join();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Counter3, "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc = 0;
            count();
        }

    }
}
