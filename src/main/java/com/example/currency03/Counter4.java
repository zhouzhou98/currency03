package com.example.currency03;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 4、运用thread、AtomicLong、线程状态TERMINATED
 */
public class Counter4 {
    public static AtomicLong inc = new AtomicLong();
    public void crease() {
        inc.getAndIncrement();
    }
    public static void count() throws InterruptedException {
        final Counter4 c4 = new Counter4();
        long t1 = System.currentTimeMillis();
        Thread  th = null;
        for(int i = 0; i < 1000; i++) {
            th = new Thread(() -> {
                for(int j = 0; j < 100000; j++) {
                    c4.crease();
                }
            });
            th.start();
            while(th.getState()!=Thread.State.TERMINATED){
                Thread.sleep(1);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Counter4, "+String.format("结果：%s,耗时(ms)：%s", inc, (t2 - t1)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            inc.set(0);
            count();
        }
    }

}
