package com.hpugs.learning.pattern.structure.adapter;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午5:02
 */
public class FuelOilEngine92 implements FuelOilEngine {

    @Override
    public EnumFuelTypes getFuelTypes() {
        return EnumFuelTypes.T92;
    }

    @Override
    public Long getCap() {
        return 180L;
    }

    @Override
    public EnumUnit getUnit() {
        return EnumUnit.LITRE;
    }
}
