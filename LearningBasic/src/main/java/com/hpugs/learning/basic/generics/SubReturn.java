package com.hpugs.learning.basic.generics;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午4:13
 */
public class SubReturn<A, B, C> extends Return<A, B> {

    public final C c;

    public SubReturn(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    @Override
    public String toString() {
        return "SubReturn{" +
                "c=" + c +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
