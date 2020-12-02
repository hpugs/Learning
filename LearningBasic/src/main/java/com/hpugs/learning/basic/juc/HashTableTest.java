package com.hpugs.learning.basic.juc;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.stream.IntStream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午8:27
 */
public class HashTableTest {

    public Hashtable<String, Integer> hashtable = new Hashtable<>();

    public HashMap<String, Integer> hashMap = new HashMap<>();

    private static final String key = "test";

    public static void main(String[] args) {
        exe1();

        System.out.println("------------------");

        exe2();
    }

    private static void exe1() {
        HashTableTest hashTableTest = new HashTableTest();

        Long start = System.currentTimeMillis();

        /**
         *  初始容量：16，负载因子：0.75，初始阀值：12，链表转红黑树最小链表长度：8，最小树形结构数组容量：64
         *  数据+单向链表(红黑树)，链表采用尾插法
         *  扩容逻辑：数组长度左移1位，链表中元素key的hashCode与原数组长度进行与操作，不等于0时将元素存放到当前数据组下标+原数组长度位
         */
        IntStream.range(0,10).parallel().peek(n ->hashTableTest.hashMap.put(key, n))
                .forEach(i -> System.out.println(hashTableTest.hashMap.get(key)));

        System.out.println("hashMap执行时间：" + (System.currentTimeMillis() - start));

    }

    private static void exe2() {
        HashTableTest hashTableTest = new HashTableTest();

        Long start = System.currentTimeMillis();

        /**
         *  初始容量：11，负载因子：0.75，初始阀值：8
         *  数据+单向链表，链表采用头插法
         *  扩容逻辑：遍历全部元素，与数据组长度取余
         */
        IntStream.range(0,10).parallel().peek(n ->hashTableTest.hashtable.put(key, n))
                .forEach(i -> System.out.println(hashTableTest.hashtable.get(key)));


        System.out.println("hashTable执行时间：" + (System.currentTimeMillis() - start));
    }

}
