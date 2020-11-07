package com.hpugs.learning.threads.locks;

/**
 * synchronized 通过类静态方法加锁，锁定类
 *
 * @author gaoshang
 * date: 2020/11/1 下午5:51
 */
public class SyncTest06 implements Runnable {

    private static Integer i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            add();
        }
    }

    private static synchronized void add(){
        i++;
    }

    public static void main(String[] args) throws Exception {
        // --------  单个实例并发验证  ----------
        System.out.println("--------  单个实例并发验证  ----------");
        SyncTest06 syncTest = new SyncTest06();
        Thread thread1 = new Thread(syncTest);
        Thread thread2 = new Thread(syncTest);

        thread1.start();
        thread2.start();

        // --------  多个实例并发验证  ----------
//        System.out.println("--------  多个实例并发验证  ----------");
//        Thread thread1 = new Thread(new SyncTest06());
//        Thread thread2 = new Thread(new SyncTest06());
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
