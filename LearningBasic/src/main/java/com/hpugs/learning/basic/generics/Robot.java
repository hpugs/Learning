package com.hpugs.learning.basic.generics;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午3:49
 */
public class Robot {

    private String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "name='" + name + '\'' +
                '}';
    }
}
