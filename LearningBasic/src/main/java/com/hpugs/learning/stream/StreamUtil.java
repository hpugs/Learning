package com.hpugs.learning.stream;

import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/17 下午11:51
 */
public class StreamUtil {

    public static void main(String[] args) {
        range();

        System.out.println("---------------------------");

        streamGenerate();
    }

    private static void range() {
        IntStream.range(0, 10)
                .skip(5)
                .limit(10)
                .peek(n -> System.out.print(n))
                .forEach(System.out::println);
    }

    private static void streamGenerate() {
        Stream.generate(() -> new MySupplier())
                .skip(10)
                .limit(5)
                .map(Supplier::get)
                .forEach(System.out::println);
    }

    static class MySupplier implements Supplier<String> {

        private static int count;

        public MySupplier() {
            count++;
        }

        @Override
        public String get() {
            return count + "" + count;
        }

    }

}
