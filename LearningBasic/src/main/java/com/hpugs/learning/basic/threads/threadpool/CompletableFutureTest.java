package com.hpugs.learning.basic.threads.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/23 下午6:19
 */
public class CompletableFutureTest {

    static class MyRun implements Runnable {

        private Integer id;

        public MyRun(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            new Nmp(100L);
            System.out.printf("id=%d thread=%s\n", id, Thread.currentThread().getName());
        }
    }

    static class MyCallable implements Callable<Integer> {

        private Integer id;

        public MyCallable(Integer id) {
            this.id = id;
        }

        @Override
        public Integer call() {
            int val = 0;
            for (int i = 0; i < 100; i++) {
                val++;
            }
            System.out.printf("id=%d thread=%s val=%d\n", id, Thread.currentThread().getName(), val);
            return val;
        }
    }

    static class Nmp {

        private Long timeOut;

        public Nmp(Long timeOut) {
            this.timeOut = timeOut;
            sleep();
        }

        private void sleep() {
            if (timeOut == null) {
                return;
            }

            try {
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                System.out.println("线程支持中断");
            }
        }
    }

    static class Sum implements Runnable {

        public static Integer val = 0;

        private Integer id;

        public Sum(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                val++;
            }

            System.out.printf("id=%d thread=%s val=%d\n", id, Thread.currentThread().getName(), val);
        }
    }

    static class NoRunable {
        public static void execute() {
            System.out.println("NoRunable");
        }
    }

    static class NoCallable {
        public static Integer get() {
            System.out.println("NoCallable");
            return 100;
        }
    }

    static class Machina {

        enum Status {

            START, INPUT, CHECK, EXECUTE, OUTPUT, END;

            public Status stop() {
                if (equals(END)) {
                    return END;
                }
                return values()[ordinal() + 1];
            }
        }

        private Integer id;

        public Machina(Integer id) {
            this.id = id;
        }

        private Status status = Status.START;

        public static Machina work(Machina m) {
            if (!m.status.equals(Status.END)) {
                new Nmp(100L);
                m.status = m.status.stop();
            }
            System.out.println(m);
            return m;
        }

        @Override
        public String toString() {
            return "Machina" + id + ": " + (status.equals(Status.END) ? "complete" : status);
        }

    }

    public static void main(String[] args) {
//        exe1();
//
//        System.out.println("----------------------------");
//
//        exe2();
//
//        System.out.println("----------------------------");
//
//        exe3();
//
//        System.out.println("----------------------------");
//
//        exe4();
//
//        System.out.println("----------------------------");
//
//        exe5();
//
//        System.out.println("----------------------------");
//
//        exe6();
//
//        System.out.println("----------------------------");
//
//        exe7();
//
//        System.out.println("----------------------------");
//
//        exe8();
//
//        System.out.println("----------------------------");
//
//        exe9();

        System.out.println("----------------------------");

        exe10();

        System.out.println("----------------------------");

        exe11();

    }

    private static void exe1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10).mapToObj(MyRun::new).forEach(executorService::execute);

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " awaiting termination");
            new Nmp(100L);
        }
        System.out.println("线程池关闭");
    }

    private static void exe2() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10).mapToObj(MyRun::new).forEach(executorService::execute);

        executorService.shutdownNow();
        while (!executorService.isTerminated()) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " awaiting termination");
            new Nmp(100L);
        }
        System.out.println("线程池关闭");
    }

    private static void exe3() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 10).mapToObj(MyRun::new).forEach(executorService::execute);

        executorService.shutdown();
    }

    private static void exe4() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        IntStream.range(0, 10).mapToObj(Sum::new).forEach(executorService::execute);

        executorService.shutdown();
    }

    private static void exe5() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(0, 10).mapToObj(Sum::new).forEach(executorService::execute);

        executorService.shutdown();
    }

    private static void exe6() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<MyCallable> myCallableList = IntStream.range(0, 10).mapToObj(MyCallable::new).collect(Collectors.toList());

        try {
            List<Future<Integer>> futures = executorService.invokeAll(myCallableList);
            Integer count = futures.stream().map(CompletableFutureTest::extractResult).reduce(0, Integer::sum);
            System.out.println("sum=" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    public static Integer extractResult(Future<Integer> f) {
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void exe7() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futureList.add(executorService.submit(new MyCallable(i)));
        }

        try {
            int sum = futureList.stream().mapToInt(future -> {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return 0;
            }).sum();
            System.out.println("sum=" + sum);
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            executorService.shutdown();
        }
    }

    private static void exe8() {
        System.out.println(IntStream.range(0, 10).parallel().mapToObj(n -> new MyCallable(n))
                .map(ct -> ct.call()).reduce(0, Integer::sum));
    }

    public static void exe9() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            System.out.println("Lambda1");
        });

        executorService.submit(NoRunable::execute);

        Future<Integer> submit = executorService.submit(NoCallable::get);
        try {
            System.out.println("NoCallable result=" + submit.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Future<Integer> lambda4 = executorService.submit(() -> {
            System.out.println("Lambda4");
            return 1;
        });
        try {
            System.out.println("Lambda4 result=" + lambda4.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * 回调 thenApply() 一旦开始一个操作，在完成所有任务之前，不会完成 CompletableFuture 的构建。
     * 虽然这有时很有用，但是开始所有任务通常更有价值，这样就可以运行继续前进并执行其他操作
     */
    private static void exe10() {
        long start = System.currentTimeMillis();
        CompletableFuture<Machina> cf = CompletableFuture.completedFuture(new Machina(0))
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work)
                .thenApply(Machina::work);
        try {
            System.out.println("status=" + cf.get().status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 同步调用 (我们通常使用的那种) 意味着：“当你完成工作时，才返回”，而异步调用以意味着： “立刻返回并继续后续工作”。
     * 正如你所看到的，cf 的创建现在发生的更快。每次调用 thenApplyAsync() 都会立刻返回，因此可以进行下一次调用，
     * 整个调用链路完成速度比以前快得多。事实上，如果没有回调 cf.join() 方法，程序会在完成其工作之前退出。
     * 而 cf.join() 直到 cf 操作完成之前，阻止 main() 进程结束。我们还可以看出本示例大部分时间消耗在 cf.join()
     */
    private static void exe11() {
        long start = System.currentTimeMillis();
        CompletableFuture<Machina> cf = CompletableFuture.completedFuture(new Machina(0))
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work);
        try {
            System.out.println(cf.join());
            System.out.println("status=" + cf.get().status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);
    }


}
