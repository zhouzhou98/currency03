package com.example.currency03;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1· 运用Thread、AtomicLong、线程join实现
 */
public class Counter1 {
    public static AtomicLong inc = new AtomicLong();
    public void increase() {
        // 数据加一
        inc.getAndIncrement();
    }

    public static void count() throws InterruptedException {
        final Counter1 c1 = new Counter1();
        Thread  th;
        long t1 = System.currentTimeMillis();

        for(int i = 0; i < 1000; i++) {
            th = new Thread(() -> {
                for(int j = 0; j < 100000; j++) {
                    c1.increase();
                }
            });
            th.start();
            th.join();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Counter1 , "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.set(0);
            count();
        }
    }
}
