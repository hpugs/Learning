package com.hpugs.learning.basic.threads.threadpool;

import com.alibaba.acm.shaded.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/13 下午4:07
 */
public class ThreadPoolTest {

    public static ExecutorService executorServiceOne;

    public static ExecutorService executorServiceTwo;

    /**
     * 核心线程数量
     */
    public static final int corePoolSize = 1;

    /**
     * 最大线程数量
     */
    public static final int maximumPoolSize = 5;

    /**
     * 线程等待最大时间：3s
     */
    public static final long keepAliveTime = 3;

    /**
     * 线程等待时间单位
     */
    public static final TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 设置线程队列
     */
    public static final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);

    static {
        executorServiceOne = Executors.newSingleThreadExecutor();

        // 规范创建线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-thread-%d").build();
        executorServiceTwo = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public void executeOne(Runnable runnable){
        executorServiceOne.execute(runnable);
    }

    public Future submitOne(Runnable runnable){
        return executorServiceOne.submit(runnable);
    }

    public Future submitOne(Callable callable){
        return executorServiceOne.submit(callable);
    }



    public void executeTwo(Runnable runnable){
        executorServiceOne.execute(runnable);
    }

    public Future submitTwo(Runnable runnable){
        return executorServiceOne.submit(runnable);
    }

    public Future submitTwo(Callable callable){
        return executorServiceOne.submit(callable);
    }

    public static void main(String[] args) {
//        executorServiceOne.execute(() -> System.out.println("123"));

//        executorServiceOneTest();


        executorServiceTwoTest();
    }

    private static void executorServiceOneTest(){
        for (int i = 0; i < 10; i++) {
            executorServiceOne.execute(new Runnable(){
                @Override
                public void run() {
                    ThreadPoolTest.run();
                }
            });
        }
    }

    private static void executorServiceTwoTest(){
        for (int i = 0; i < 20; i++) {
            try {
                executorServiceTwo.execute(new Runnable() {
                    @Override
                    public void run() {
                        ThreadPoolTest.run();
                    }
                });
            }catch (RejectedExecutionException e){
                System.out.println("线程池已满");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private static void run(){
        System.out.println(Thread.currentThread().getName() + "开始执行");
        try {
            Thread.sleep(RandomUtils.nextInt(1, 5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "开始结束");
    }

}
