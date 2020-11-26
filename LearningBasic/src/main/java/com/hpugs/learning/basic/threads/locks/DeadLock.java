package com.hpugs.learning.basic.threads.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 死锁
 *
 * @author gaoshang
 * date: 2020/11/24 下午5:18
 */
public class DeadLock {

    private Integer id;

    public DeadLock(Integer id) {
        this.id = id;
    }

    public static AtomicBoolean atomicBoolean1 = new AtomicBoolean();

    public static AtomicBoolean atomicBoolean2 = new AtomicBoolean();

    public void method1() {
        System.out.println("id=" + this.id + " 准备执行method1");

        synchronized (atomicBoolean1) {
            atomicBoolean1.set(false);

            System.out.println("id=" + this.id + " 获的atomicBoolean1锁");

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (atomicBoolean2) {
                atomicBoolean2.set(false);

                System.out.println("id=" + this.id + " 获的atomicBoolean2锁");

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        System.out.println("id=" + this.id + " 执行method1完成");
    }

    public void method2() {
        System.out.println("id=" + this.id + " 准备执行method2");

        synchronized (atomicBoolean2) {
            atomicBoolean2.set(false);

            System.out.println("id=" + this.id + " 获的atomicBoolean2锁");

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (atomicBoolean1) {
                atomicBoolean1.set(false);

                System.out.println("id=" + this.id + " 获的atomicBoolean1锁");

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("id=" + this.id + " 执行method1完成");
    }

    /**
     * 死锁出现的原因：多核并行处理，同时获取相对锁定资源出现死锁
     */
    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock(1);
        DeadLock deadLock2 = new DeadLock(2);

        // 串行执行未出现死锁
//        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 并行执行出现死锁
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> deadLock1.method1());
        executorService.submit(() -> deadLock2.method2());

        executorService.shutdown();
    }

}
