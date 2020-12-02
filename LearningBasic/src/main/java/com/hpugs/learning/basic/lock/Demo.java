package com.hpugs.learning.basic.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/2 上午10:19
 */
public class Demo {

    /**
     * wait/Notify、park/UnPark、park/interrupt实现线程同步
     * <p>
     * 先调用unpark，再调用park时，仍能够正确实现同步，不会造成由wait/notify调用顺序不当所引起的阻塞。
     * 因此park/unpark相比wait/notify更加的灵活
     * <p>
     * 在主线程调用park阻塞后，在myThread线程中发出了中断信号，此时主线程会继续运行，
     * 也就是说明此时interrupt起到的作用与unpark一样
     */
    public static void main(String[] args) {
        waitAndNotify();
//        notifyAndWait();

//        parkAndUnPark();
//        unParkAndPark();

//        interrupt();

//        lockPark();
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("before notify");

                // 唤醒wait阻塞线程
                notify();

                System.out.println("after notify");
            }
        }

    }

    /**
     * wait/Notify实现线程同步，必须先调用wait，再调用Notify，否则wait将不被唤醒
     */
    private static void waitAndNotify() {
        MyRunnable myRunnable = new MyRunnable();
        synchronized (myRunnable) {
            new Thread(myRunnable).start();

            try {
                // 主线程休眠
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("before wait");

            try {
                // 阻塞主线程，释放锁
                myRunnable.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("after wait");
        }

    }

    private static void notifyAndWait() {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();

        synchronized (myRunnable) {
            try {
                // 主线程休眠
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("before wait");

            try {
                // 阻塞主线程，释放锁
                myRunnable.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("after wait");
        }

    }

    static class MyThread extends Thread {

        private Thread t;

        public MyThread(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            try {
                // 线程休眠
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("before unPark");
            LockSupport.unpark(t);
            System.out.println("after unPark");
        }
    }

    public static void parkAndUnPark() {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();

        System.out.println("before park");

        LockSupport.park();

        System.out.println("after park");
    }

    public static void unParkAndPark() {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();

        try {
            // 主线程休眠
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("before park");

        LockSupport.park();

        System.out.println("after park");
    }

    static class MyInterruptThread extends Thread {

        private Thread t;

        public MyInterruptThread(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            System.out.println("before interrupt");

            try {
                // 线程休眠
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 中断线程
            t.interrupt();
            System.out.println("after interrupt");
        }
    }

    /**
     * 在主线程调用park阻塞后，在myThread线程中发出了中断信号，此时主线程会继续运行，
     * 也就是说明此时interrupt起到的作用与unpark一样
     */
    public static void interrupt() {
        MyInterruptThread myThread = new MyInterruptThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park();
        System.out.println("after park");
    }

    static class MyLockThread extends Thread {

        private Thread t;

        public MyLockThread(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            synchronized (t) {
                System.out.println("before unPark");

                LockSupport.unpark(t);

                System.out.println("after unPark");
            }
        }
    }

    /**
     * park锁测试
     */
    public static void lockPark() {
        Thread mainThread = Thread.currentThread();
        MyLockThread myLockThread = new MyLockThread(mainThread);

        synchronized (mainThread) {
            myLockThread.start();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("before park");

            // park并不释放锁资源
            LockSupport.park();

            System.out.println("after park");
        }
    }

}
