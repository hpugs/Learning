package com.hpugs.learning.basic.generics;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午4:08
 */
public class Return<A, B> {

    public final A a;

    public final B b;

    public Return(A a, B b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Return{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
