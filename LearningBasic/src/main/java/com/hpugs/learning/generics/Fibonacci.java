package com.hpugs.learning.generics;

import java.util.function.Supplier;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午4:28
 */
public class Fibonacci implements Supplier<Integer> {

    private int count = 0;

    @Override
    public Integer get() {
        return fib(count++);
    }

    private int fib(int n){
        if(n < 2) return 1;
        return fib(n-2) + fib(n-1);
    }
}
