package com.hpugs.learning.web.spi.dubbo;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/10/28 下午9:47
 */
public class Bumblebee implements DubboRobot {

    @Override
    public void sayHello() {
        System.out.println("dubbo spi Bumblebee hello");
    }

}
