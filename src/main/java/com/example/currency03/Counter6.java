package com.example.currency03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * 6、运用Thread、synchronized、线程CountDownLatch等实现
 */
public class Counter6 {
    public static int inc = 0;
    public synchronized static void increase() {
        inc++;
    }
    public static void count() throws InterruptedException {
        int threadCount = 1000;
        long t1 = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 100000; j++) {
                        increase();
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }).start();


        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        System.out.println("Counter6, "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));

    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc = 0;
            count();
        }
    }

}
