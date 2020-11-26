package com.hpugs.learning.basic.threads.locks;

/**
 * 多线程同步操作，数据是否共享？否
 * volatile 实现线程间数据共享
 *
 * @author gaoshang
 * date: 2020/11/1 下午5:51
 */
public class SyncTest02 implements Runnable {

    private static volatile Integer i = 0;

    @Override
    public void run() {
        for (int n = 0; n < 10000; n++) {
            add();
        }
    }

    private void add() {
        i++;
    }

    public static void main(String[] args) throws Exception {
        // --------  单个实例并发验证  ----------
        System.out.println("--------  单个实例并发验证  ----------");
        SyncTest02 syncTest = new SyncTest02();
        Thread thread1 = new Thread(syncTest);
        Thread thread2 = new Thread(syncTest);

        thread1.start();
        thread2.start();

        // --------  多个实例并发验证  ----------
//        System.out.println("--------  多个实例并发验证  ----------");
//        Thread thread1 = new Thread(new SyncTest02());
//        Thread thread2 = new Thread(new SyncTest02());
//
//        thread1.start();
//        thread2.start();

        // 主线程等待200毫秒，避免子线程未全部执行
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i=" + i);
    }
}