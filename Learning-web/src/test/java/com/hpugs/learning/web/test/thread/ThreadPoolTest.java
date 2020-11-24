package com.hpugs.learning.web.test.thread;

import com.hpugs.learning.common.constant.Constants;
import com.hpugs.learning.core.facade.AnsyFacade;
import com.hpugs.learning.web.test.BaseApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/24 下午6:34
 */
public class ThreadPoolTest extends BaseApplicationTests {

    @Resource(name = Constants.THREAD_POOL_NAME)
    private Executor threadPool;

    @Resource
    private AnsyFacade ansyFacade;

    @Test
    public void countDownLatchTest() {
        final int threadSize = 2;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        List<Future<Boolean>> ansylist = new ArrayList<>();

        ansylist.add(ansyFacade.method1(countDownLatch, 200L));
        ansylist.add(ansyFacade.method2(countDownLatch, 100L));

        try {
            countDownLatch.await(150, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("线程执行超时");
        } finally {
            for (Future<Boolean> future : ansylist) {
                if (future != null) {
                    future.cancel(true);
                }
            }
        }
    }

    @Test
    public void completableFutureTest() {
        CompletableFuture voidCompletableFuture1 = CompletableFuture.runAsync(() -> ansyFacade.method3(200L), threadPool);
        CompletableFuture voidCompletableFuture2 = CompletableFuture.runAsync(() -> ansyFacade.method4(100L), threadPool);

        try {
            CompletableFuture.allOf(voidCompletableFuture1, voidCompletableFuture2).get(150, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("线程执行超时");
        }
    }

}
