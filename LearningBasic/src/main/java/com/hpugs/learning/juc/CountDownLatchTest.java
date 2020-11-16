package com.hpugs.learning.juc;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/13 上午12:08
 */
public class CountDownLatchTest implements Runnable {

    public CountDownLatch countDown;

    public CountDownLatchTest(CountDownLatch countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(RandomUtils.nextInt(0, 10) * 1000);

            System.out.println(String.format("线程%s执行完成", Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            System.out.println(String.format("线程%s被打断", Thread.currentThread().getName()));
        } finally {
            countDown.countDown();
        }
    }

    public static void main(String[] args) {
        // 默认开启10个线程
        int size = 10;

        System.out.println("主线程执行开始");

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
        System.out.println("主线程执行完成");
    }

    private static Future asynExecute(CountDownLatch countDown, Integer i) {
        CountDownLatchTest countDownLatchTest = new CountDownLatchTest(countDown);
        FutureTask futureTask = new FutureTask(countDownLatchTest, i);
        new Thread(futureTask).start();
        return futureTask;
    }

}
