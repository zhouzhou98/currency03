package com.example.currency03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class Counter12 {
    // 请求总数
    public static int clientTotal = 100000000;
    public static AtomicLong count = new AtomicLong(0);

    public static void add() {
        count.getAndIncrement();
    }

    public static void count() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 获取可用的线程数
        final int nThreads = Runtime.getRuntime().availableProcessors();
        final Semaphore semaphore = new Semaphore(nThreads << 1);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

        executorService.shutdown();
        long t2 = System.currentTimeMillis();
        System.out.println("Counter12 , "+String.format("结果：%s,耗时(ms)：%s", count, (t2 - t1)));
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            count.set(0);
            count();
        }
    }

}
