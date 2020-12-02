package com.hpugs.learning.basic.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午8:26
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        exe1();
    }

    private static void exe1() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        String key = "key";

        IntStream.range(0,10).parallel().peek(n ->concurrentHashMap.put(key, n))
                .forEach(i -> System.out.println(concurrentHashMap.get(key)));
    }

}
