package com.hpugs.learning.basic.threadlocal;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/30 下午5:23
 */
public class ThreadLocalTest {

    public static Object obj1;

    public Object obj2;

    public static final AtomicInteger n = new AtomicInteger(0);

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return n.addAndGet(1);
        }
    };

    public static Object getObject1() {
        if (obj1 == null) {
            System.out.println("获取数据库连接线程：" + Thread.currentThread().getName());
            obj1 = new Object();
        }
        return obj1;
    }

    public Object getObject2() {
        if (obj2 == null) {
            System.out.println("获取数据库连接线程：" + Thread.currentThread().getName());
            obj2 = new Object();
        }
        return obj2;
    }

    public static Integer getObject3() {
        System.out.println("获取数据库连接线程：" + Thread.currentThread().getName());
        return threadLocal.get();
    }

    public static void main(String[] args) {
        exe1();

        System.out.println("-----------exe2()-------------");

        exe2();

        System.out.println("-----------exe3()-------------");

        exe3();

        System.out.println("-----------exe4()-------------");

        exe4();

        System.out.println("-----------exe5()-------------");

        exe5();
    }

    /**
     * 通过单例获取静态变量，多线程场景会出现实例创建冲突
     */
    private static void exe1() {
        Set<Object> connectionSet = IntStream.range(1, 10).parallel().mapToObj(i -> ThreadLocalTest.getObject1()).collect(Collectors.toSet());

        System.out.println("connectionSet.size=" + connectionSet.size());
    }

    /**
     * 通过单例获取成员变量，多线程场景下，因对象不同，不会出现实例创建冲突
     */
    private static void exe2() {
        Set<Object> connectionSet = IntStream.range(1, 10).parallel().mapToObj(i -> new ThreadLocalTest().getObject2()).collect(Collectors.toSet());

        System.out.println("connectionSet.size=" + connectionSet.size());
    }

    /**
     * 通过ThreadLocal获取对象
     */
    private static void exe3() {
        ThreadLocalTest.n.set(0);

        Set<Integer> connectionSet = IntStream.range(1, 10).parallel().mapToObj(i -> ThreadLocalTest.getObject3()).collect(Collectors.toSet());

        System.out.println(connectionSet);
    }

    /**
     * 先清除线程中对象，在进行对象获取操作，避免线程缓存导致数据异常
     */
    private static void exe4() {
        ThreadLocalTest.n.set(0);

        Set<Integer> connectionSet = IntStream.range(1, 10).parallel().peek(i -> ThreadLocalTest.threadLocal.remove())
                .mapToObj(i -> ThreadLocalTest.getObject3()).collect(Collectors.toSet());

        System.out.println(connectionSet);
    }

    /**
     * ThreadLocal导致内存溢出
     * <p>
     * 如果用线程池来操作ThreadLocal 对象确实会造成内存泄露, 因为对于线程池里面不会销毁的线程,
     * 里面总会存在着<ThreadLocal, LocalVariable>的强引用, 因为final static 修饰的 ThreadLocal 并不会释放,
     * 而ThreadLocalMap 对于 Key 虽然是弱引用, 但是强引用不会释放, 弱引用当然也会一直有值, 同时创建的LocalVariable对象也不会释放,
     * 就造成了内存泄露; 如果LocalVariable对象不是一个大对象的话, 其实泄露的并不严重,
     * 泄露的内存 = 核心线程数 * LocalVariable对象的大小;
     */
    // 1、定义一个类对象
    static class LocalVariable {
        private Long[] longs = new Long[1024 * 1024];
    }

    // 2、定义ThreadLocal
    public static final ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    // 测试方法
    private static void exe5() {

        // 4、创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>());

        // 3、循环调用ThradLocal
        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> {
                localVariable.set(new LocalVariable());

                System.out.println("use local varaible" + localVariable.get());

                /**
                 * 所以, 为了避免出现内存泄露的情况, ThreadLocal提供了一个清除线程中对象的方法,
                 * 即 remove, 其实内部实现就是调用 ThreadLocalMap 的remove方法:
                 */
                localVariable.remove();
            });
        }

        System.out.println("pool execute over");

        executorService.shutdown();
    }

}


