package com.hpugs.learning.web.spi.dubbo;

import org.apache.dubbo.common.extension.SPI;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/10/28 下午9:45
 */
@SPI
public interface DubboRobot {

    void sayHello();

}
