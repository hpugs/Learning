package com.hpugs.learning.basic.stream;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/20 下午4:56
 */
public class ParallelStreamUtil {

    private static final int COUNT = 100_100;

    public static boolean isPrime(Long n) {
        if (n > 2 && (n & 1) == 0) {
            return false;
        }

        /* 运用试除法:
         * 1.只有奇数需要被测试
         * 2.测试范围从2与根号{n},反之亦然 */
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static final Long SZ = 100_000_000L;

    private static Long defaultValue = SZ * (SZ + 1) / 2;

    public static void check(String node, Long checkValue, LongSupplier operation) {
        System.out.print(node + ":");

        Long start = System.currentTimeMillis();
        long value = operation.getAsLong();
        Long time = System.currentTimeMillis() - start;
        if (checkValue.equals(value)) {
            System.out.printf("executeTime=%d", time);
        } else {
            System.out.printf("executeTime=%d value=%d checkValue=%d",
                    time, value, checkValue);
        }
    }

    public static void main(String[] args) {
//        calculatePrime();
//
//        System.out.println("------------------------");
//
//        calculateParallelPrime();
//
//        System.out.println("------------------------");
//
//        carlFriedrichGauss();
//
//        System.out.println("------------------------");
//
//        generateArray();
//
//        System.out.println("------------------------");

        peek();
    }

    private static void calculatePrime() {
        Long start = System.currentTimeMillis();

        List<String> primes = LongStream.iterate(2, i -> i + 1)
                .filter(n -> ParallelStreamUtil.isPrime(n))
                .limit(COUNT)
                .mapToObj(Long::toString)
                .collect(Collectors.toList());

        Long time = System.currentTimeMillis() - start;
        System.out.printf("stream串行计算 time=%ds size=%d\n", time, primes.size());
    }

    private static void calculateParallelPrime() {
        Long start = System.currentTimeMillis();

        List<String> primes = LongStream.iterate(2, i -> i + 1)
                .parallel()
                .filter(n -> ParallelStreamUtil.isPrime(n))
                .limit(COUNT)
                .mapToObj(Long::toString)
                .collect(Collectors.toList());

        Long time = System.currentTimeMillis() - start;
        System.out.printf("stream并行计算 time=%ds size=%d\n", time, primes.size());
    }

    private static void carlFriedrichGauss() {
        check("Sum Stream", defaultValue, () -> LongStream.rangeClosed(0, SZ).sum());

        System.out.println();

        check("Sum Stream Parallel", defaultValue, () -> LongStream.rangeClosed(0, SZ).parallel().sum());

        System.out.println();

        check("Sum iterate", defaultValue, () -> LongStream.iterate(0, i -> i + 1).limit(SZ + 1).sum());

        System.out.println();
    }

    private static void generateArray(){
        // 正常数据输出
        List<Integer> intArr = Stream.generate(new IntGenerator()).limit(10).collect(Collectors.toList());
        System.out.println("IntGenerator intArr=" + JSON.toJSONString(intArr));

        // 出现数据跳跃、丢失
        intArr = Stream.generate(new IntGenerator()).limit(10).parallel().collect(Collectors.toList());
        System.out.println("IntGenerator parallel intArr=" + JSON.toJSONString(intArr));

        // 原子操作，避免跳跃
        intArr = Stream.generate(new IntGeneratorAtomic()).limit(10).parallel().collect(Collectors.toList());
        System.out.println("IntGenerator parallel intArr=" + JSON.toJSONString(intArr));

    }

    static class IntGeneratorAtomic implements Supplier<Integer>{

        public static Integer n = 0;

        @Override
        public Integer get() {
            return n++;
        }
    }

    static class IntGenerator implements Supplier<Integer>{

        public static AtomicInteger count = new AtomicInteger();

        @Override
        public Integer get() {
            return count.getAndIncrement();
        }
    }

    private static void peek(){
        List<Integer> arr = IntStream.range(0, 30)
                .peek(value -> System.out.println("value=" + value + "  thread:" + Thread.currentThread().getName()))
                .limit(10).parallel().boxed().collect(Collectors.toList());
        System.out.println(arr);
    }

}
