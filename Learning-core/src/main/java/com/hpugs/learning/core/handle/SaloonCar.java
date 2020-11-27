package com.hpugs.learning.core.handle;

import org.springframework.stereotype.Component;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午2:46
 */
@Component
public class SaloonCar extends Car {

    @Override
    public String getType() {
        return "SaloonCar";
    }

    @Override
    public String getCarConfig() {
        return getName();
    }

}
