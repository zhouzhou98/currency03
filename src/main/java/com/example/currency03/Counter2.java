package com.example.currency03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2、运用thread、reentrantlock、线程join
 */
public class Counter2 {
    public static Integer inc = 0;
    Lock lock = new ReentrantLock();
    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }
    public static void count() throws InterruptedException {
        final Counter2 c2 = new Counter2();
        Thread th;
        Long t1 = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            th = new Thread(() -> {
                for(int j = 0; j < 100000; j++) {
                    c2.increase();
                }
            });
            th.start();
            th.join();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Counter2 , "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc = 0;
            count();
        }
    }
}
