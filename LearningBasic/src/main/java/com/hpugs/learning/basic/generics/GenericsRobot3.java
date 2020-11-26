package com.hpugs.learning.basic.generics;

import org.apache.poi.ss.formula.functions.T;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午4:01
 */
public class GenericsRobot3<T> {

    private T robot;

    public GenericsRobot3(T robot) {
        this.robot = robot;
    }

    public T getRobot() {
        return robot;
    }
}
