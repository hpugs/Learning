package com.hpugs.learning.web.test.factory;

import com.hpugs.learning.core.handle.Car;
import com.hpugs.learning.core.handle.CarFactory;
import com.hpugs.learning.web.test.BaseApplicationTests;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午3:08
 */
public class CarFactoryTest extends BaseApplicationTests {

    @Resource
    private CarFactory carFactory;
    
    @Test
    public void buildCar(){
        Car suv = carFactory.getCar("SUV");
        suv.setName("SUV");
        suv.build();
    }

}
