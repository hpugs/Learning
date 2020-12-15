package com.hpugs.learning.basic.threads.keyword;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/24 下午2:51
 */
public class KeywordUtil {

    private static final int COUNT = 100;

    static class MyRunable implements Runnable {

        private int id;

        public MyRunable(int id) {
            this.id = id;
        }

        private AtomicBoolean running = new AtomicBoolean();

        public void quit() {
            if (running.get() == true) {
                return;
            }
            running.getAndSet(false);
        }

        @Override
        public void run() {
            do {
                npm(100L);
            } while (running.get());
            System.out.printf("threadId=%d ", id);
        }
    }

    public static void npm(long timeout) {
        if (timeout > 0) {
            return;
        }
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exe1();

        System.out.println("-------------------");

        exe2();

        System.out.println("-------------------");

        /**
         * Thread.yield() 方法，使当前线程由执行状态，变成为就绪状态，让出cpu时间，
         * 在下一个线程执行时候，此线程有可能被执行，也有可能没有被执行。
         */
        yieldTest();
    }

    private static void exe1() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<MyRunable> myRunableList = IntStream.range(0, COUNT).mapToObj(id -> new MyRunable(id))
                .peek(run -> executorService.execute(run))
                .collect(Collectors.toList());

        myRunableList.forEach(MyRunable::quit);

        executorService.shutdown();
    }

    private static void exe2() {
        List<MyRunable> myRunableList = IntStream.range(0, COUNT).mapToObj(id -> new MyRunable(id))
                .collect(Collectors.toList());
        List<CompletableFuture<Void>> completableFutureList = myRunableList.stream().map(CompletableFuture::runAsync)
                .collect(Collectors.toList());

        npm(100L);
        myRunableList.forEach(MyRunable::quit);
        completableFutureList.forEach(CompletableFuture::join);
    }

    public static void yieldTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyRun("Thread" + i));
        }
        executorService.shutdown();
    }

    static class MyRun implements Runnable {

        private String name;

        public MyRun(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("" + name + "-----" + i);
                // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
                if (i % 5 == 0) {
                    System.out.println("线程：" + name + "进入就绪状态");
                    Thread.yield();
                }
            }
        }
    }

}
