package com.hpugs.learning.threads.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式
 *
 * 1、通过Thread创建线程
 * 2、通过Runnable创建线程
 * 3、通过Callable创建回调线程
 * 4、通过Executors创建线程池创建线程
 *
 * @author gaoshang
 * date: 2020/11/7 下午3:55
 */
public class CreateThreadTest {

    public static void main(String[] args) throws Throwable {
        Thread.currentThread().setName("主线程");
        System.out.println(Thread.currentThread().getName() + ":" + "输出的结果");

        passThreadCreate();
        passRunnableCreate();
        passCallableCreate();
        passThreadPoolCreate();

        System.out.println(Thread.currentThread().getName() + "执行完成");
    }

    /**
     * 通过Thread创建线程
     */
    private static void passThreadCreate() {
        Thread thread = new T("通过Thread创建的子线程");
        thread.start();
    }

    /**
     * 通过Runnable创建线程
     */
    private static void passRunnableCreate() {
        Thread thread = new Thread(new R());
        thread.setName("通过Runnable创建的子线程");
        thread.start();
    }

    /**
     * 通过Callable创建回调线程
     */
    private static void passCallableCreate() {
        Thread thread = new Thread(new FutureTask<>(new C()));
        thread.setName("通过Callable创建的子线程");
        thread.start();
    }

    /**
     * 通过Executors创建线程池创建线程
     */
    private static void passThreadPoolCreate() throws Throwable {
        // 创建2个线程大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ER("线程池创建的线程1"));
        executorService.execute(new ER("线程池创建的线程2"));
        executorService.execute(new ER("线程池创建的线程3"));

        // 会导致主线程等待
//        //submit()适用于实现Callable接口创建的线程
//        Future<Boolean> task = executorService.submit(new C());
//        //获取call()方法的返回值
//        Boolean result = task.get();
//        System.out.println("Callable创建的子线程返回结果：" + result);
//
//        //submit()适用于实现Callable接口创建的线程
//        FutureTask futureTask = new FutureTask(new C());
//        task = (Future<Boolean>) executorService.submit(futureTask);
//        //获取call()方法的返回值
//        result = task.get();
//        System.out.println("Callable创建的子线程返回结果：" + result);

        //关闭线程池
        executorService.shutdown();
    }

    static class T extends Thread {

        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + "输出的结果");
        }
    }

    static class R implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + "输出的结果");
        }
    }

    static class C implements Callable {
        @Override
        public Boolean call() throws Exception {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + "输出的结果");
            return Boolean.TRUE;
        }
    }

    static class ER implements Runnable {

        private String name;

        public ER(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ":" + Thread.currentThread().getName());
        }
    }

}
