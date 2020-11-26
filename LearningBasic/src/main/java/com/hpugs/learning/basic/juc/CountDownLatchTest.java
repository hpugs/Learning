package com.hpugs.learning.basic.juc;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/13 上午12:08
 */
public class CountDownLatchTest implements Runnable {

    public CountDownLatch countDown;

    public CountDownLatchTest() {

    }

    public CountDownLatchTest(CountDownLatch countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        syncRun(countDown);
    }

    public static void main(String[] args) {
        System.out.println("主线程执行开始");

//        execute1();

//        execute2();

        execute3();

        System.out.println("主线程执行完成");
    }

    private static void execute1() {

        // 默认开启10个线程
        int size = 10;

        CountDownLatch countDown = new CountDownLatch(size);
        List<Future> futureList = new ArrayList<>();
        try {
            for (int i = 0; i < size; i++) {
                futureList.add(asynExecute(countDown, i));
            }

            boolean flag = countDown.await(3, TimeUnit.SECONDS);
            if (!flag) {
                throw new RuntimeException();
            }

            int count = 0;
            for (Future future : futureList) {
                if (future != null) {
                    count = count + (Integer) future.get();
                }
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            for (Future future : futureList) {
                if (future != null) {
                    future.cancel(true);
                }
            }
        }
    }

    private static Future asynExecute(CountDownLatch countDown, Integer i) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest(countDown);
        FutureTask futureTask = new FutureTask(countDownLatchTest, i);
        new Thread(futureTask).start();
        return futureTask;
    }

    private static void execute2() {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                completableFutureList.add(CompletableFuture.runAsync(new CountDownLatchTest()));
            }

            CompletableFuture[] completableFutures = completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]);
            CompletableFuture.allOf(completableFutures).get(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("执行被打断");
        } catch (ExecutionException e) {
            System.out.println("执行异常");
        } catch (TimeoutException e) {
            System.out.println("执行超时");
        } finally {
            for (CompletableFuture completableFuture : completableFutureList) {
                if (completableFuture != null) {
                    completableFuture.cancel(true);
                }
            }
        }

    }

    private static void execute3() {
        List<CompletableFuture<String>> completableFutureList = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                completableFutureList.add(CompletableFuture.supplyAsync(()->syncRun(null)));
            }
            CompletableFuture[] completableFutures = completableFutureList.toArray(new CompletableFuture[completableFutureList.size()]);
            CompletableFuture.allOf(completableFutures).get(5, TimeUnit.SECONDS);

            for (CompletableFuture<String> completableFuture : completableFutureList) {
                System.out.println(completableFuture.get());
            }
        } catch (InterruptedException e) {
            System.out.println("执行被打断");
        } catch (ExecutionException e) {
            System.out.println("执行异常");
        } catch (TimeoutException e) {
            System.out.println("执行超时");
        } finally {
            for (CompletableFuture completableFuture : completableFutureList) {
                if (completableFuture != null) {
                    completableFuture.cancel(true);
                }
            }
        }
    }

    private static String syncRun(CountDownLatch countDown){
        try {
            Thread.sleep(RandomUtils.nextInt(0, 3) * 1000);

            System.out.println(String.format("线程%s执行完成", Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            System.out.println(String.format("线程%s被打断", Thread.currentThread().getName()));
        } finally {
            if (countDown != null) {
                countDown.countDown();
            }
        }
        return String.format("线程%s执行时间%d", Thread.currentThread().getName(), System.currentTimeMillis());
    }

}
