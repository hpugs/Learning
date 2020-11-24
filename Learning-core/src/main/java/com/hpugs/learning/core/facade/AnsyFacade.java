package com.hpugs.learning.core.facade;

import com.hpugs.learning.common.constant.Constants;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/24 下午6:36
 */
@Component
public class AnsyFacade {

    @Async(value = Constants.THREAD_POOL_NAME)
    public Future<Boolean> method1(CountDownLatch countDownLatch, long threadSleepTime) {
        try {
            System.out.println("method1 异步执行开始");

            sleep(threadSleepTime);

            System.out.println("method2 异步执行结束");
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }

        return new AsyncResult(true);
    }

    @Async(value = Constants.THREAD_POOL_NAME)
    public Future<Boolean> method2(CountDownLatch countDownLatch, long threadSleepTime) {
        try {
            System.out.println("method2 异步执行开始");

            sleep(threadSleepTime);

            System.out.println("method2 异步执行结束");
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }

        return new AsyncResult(true);
    }

    public void method3(long threadSleepTime) {
        System.out.println("method3 异步执行开始");

        sleep(threadSleepTime);

        System.out.println("method3 异步执行结束");
    }

    public void method4(long threadSleepTime) {
        System.out.println("method4 异步执行开始");

        sleep(threadSleepTime);

        System.out.println("method4 异步执行结束");
    }

    private void sleep(long threadSleepTime){
        try {
            Thread.sleep(threadSleepTime);
        } catch (InterruptedException e) {
            System.out.println("thread=" + Thread.currentThread().getName() + " 执行中断");
        }
    }

}
