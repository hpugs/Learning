package com.hpugs.learning.threads.keyword;

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

}
