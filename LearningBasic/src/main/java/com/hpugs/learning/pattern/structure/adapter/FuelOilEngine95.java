package com.hpugs.learning.pattern.structure.adapter;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午5:01
 */
public class FuelOilEngine95 implements FuelOilEngine {

    @Override
    public EnumFuelTypes getFuelTypes() {
        return EnumFuelTypes.T95;
    }

    @Override
    public Long getCap() {
        return 200L;
    }

    @Override
    public EnumUnit getUnit() {
        return EnumUnit.LITRE;
    }
}
