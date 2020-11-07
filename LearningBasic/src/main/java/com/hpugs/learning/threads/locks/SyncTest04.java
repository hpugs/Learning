package com.hpugs.learning.threads.locks;

/**
 * 多线程同步操作，数据是否共享？否
 * volatile 实现线程间数据共享
 * 异步操作输出线程名称进行验证
 *
 * @author gaoshang
 * date: 2020/11/1 下午5:51
 */
public class SyncTest04 implements Runnable {

    private static volatile Integer i = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        add();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        System.out.println(Thread.currentThread().getName() + "  i=" + i++);
    }

    public static void main(String[] args) throws Exception {
        // --------  单个实例并发验证  ----------
        System.out.println("--------  单个实例并发验证  ----------");
        SyncTest04 syncTest = new SyncTest04();
        Thread thread1 = new Thread(syncTest);
        Thread thread2 = new Thread(syncTest);

        thread1.start();
        thread2.start();

        // --------  多个实例并发验证  ----------
//        System.out.println("--------  多个实例并发验证  ----------");
//        Thread thread1 = new Thread(new SyncTest04());
//        Thread thread2 = new Thread(new SyncTest04());
//
//        thread1.start();
//        thread2.start();
    }
}
