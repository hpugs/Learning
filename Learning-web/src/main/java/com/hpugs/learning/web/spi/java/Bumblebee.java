package com.hpugs.learning.web.spi.java;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/10/28 下午9:47
 */
public class Bumblebee implements JavaRobot {

    @Override
    public void sayHello() {
        System.out.println("java spi Bumblebee hello");
    }

}
