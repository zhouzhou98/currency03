package com.example.currency03;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 5、运用Thread、AtomicLong、线程isAlive等实现
 */
public class Counter5 {
    public static AtomicLong inc = new AtomicLong();
    public void increase() {
        inc.getAndIncrement();
    }
    public static void count() throws InterruptedException {
        final Counter5 c5 = new Counter5();
        Thread  th = null;
        long t1 = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            th = new Thread(() -> {
                for(int j = 0; j < 100000; j++) {
                    c5.increase();
                }
            });
            th.start();
            while(th.isAlive()){
                Thread.sleep(1);
            }

        }
        long t2 = System.currentTimeMillis();
        System.out.println("Counter5, "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.set(0);
            count();
        }

    }
}
