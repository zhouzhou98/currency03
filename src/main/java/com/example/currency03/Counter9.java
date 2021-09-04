package com.example.currency03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * 9、运用Thread、LongAccumulator、线程CountDownLatch等实现
 */
public class Counter9 {
    private static LongAccumulator inc = new LongAccumulator((x, y) -> x + y, 0L);

    public static void incr() {
        inc.accumulate(1);
    }

    public static void count() throws InterruptedException {
        int threadCount = 1000;
        long t1 = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for(int j = 0; j < 100000; j++) {
                        incr();
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }).start();
        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        System.out.println("Counter9, "+String.format("结果：%s,耗时(ms)：%s", inc.longValue(), (t2 - t1)));

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.reset();
            count();
        }
    }

}
