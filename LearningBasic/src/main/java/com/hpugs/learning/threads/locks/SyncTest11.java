package com.hpugs.learning.threads.locks;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * synchronized 通过代码块对类对象的原子属性加锁，锁定类对象
 *
 * @author gaoshang
 * date: 2020/11/1 下午5:51
 */
public class SyncTest11 implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    @Override
    public void run() {
        for (int n = 0; n < 10000; n++) {
            add();
        }
    }

    private void add() {
        synchronized (i) {
            i.addAndGet(1);
        }

    }

    public static void main(String[] args) throws Exception {
        // --------  单个实例并发验证  ----------
        System.out.println("--------  单个实例并发验证  ----------");
        SyncTest11 syncTest = new SyncTest11();
        Thread thread1 = new Thread(syncTest);
        Thread thread2 = new Thread(syncTest);

        thread1.start();
        thread2.start();

        // 主线程等待200毫秒，避免子线程未全部执行
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i=" + syncTest.i);
    }
}
