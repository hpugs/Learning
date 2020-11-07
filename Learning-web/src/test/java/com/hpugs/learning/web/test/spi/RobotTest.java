package com.hpugs.learning.web.test.spi;

import com.hpugs.learning.web.spi.dubbo.DubboRobot;
import com.hpugs.learning.web.spi.java.JavaRobot;
import com.hpugs.learning.web.test.BaseApplicationTests;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/7 下午5:56
 */
public class RobotTest extends BaseApplicationTests {

    @Test
    public void spiTest() {
        ServiceLoader<JavaRobot> serviceLoader = ServiceLoader.load(JavaRobot.class);
        System.out.println("java spi");
        serviceLoader.forEach(JavaRobot::sayHello);
    }

    @Test
    public void dubboSpiTest() {
        ExtensionLoader<DubboRobot> robotExtensionLoader = ExtensionLoader.getExtensionLoader(DubboRobot.class);
        System.out.println("dubbo spi");
        DubboRobot optimusPrime = robotExtensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        DubboRobot bumblebee = robotExtensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }


}
