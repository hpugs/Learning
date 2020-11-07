package com.hpugs.learning.web.spi.dubbo;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/10/28 下午9:45
 */
public class OptimusPrime implements DubboRobot {

    @Override
    public void sayHello() {
        System.out.println("dubbo spi OptimusPrime hello");
    }
}
