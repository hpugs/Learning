package com.hpugs.learning.pattern.structure.adapter;

/**
 * 电动发动机适配器
 *
 * @author gaoshang
 * date: 2020/11/27 下午4:37
 */
public class ElectricEngineAdapter implements ElectricEngine {

    private FuelOilEngine fuelOilEngine;

    public ElectricEngineAdapter(FuelOilEngine fuelOilEngine) {
        this.fuelOilEngine = fuelOilEngine;
    }

    @Override
    public String getVoltage() {
        return fuelOilEngine.getFuelTypes().getVoltage();
    }

    @Override
    public Long getCap() {
        return fuelOilEngine.getCap() / 2;
    }

    @Override
    public EnumUnit getUnit() {
        return EnumUnit.DEGREE;
    }

    public static void main(String[] args) {
        // 定义燃油发动机
        FuelOilEngine92 fuelOilEngine92 = new FuelOilEngine92();
        fuelOilEngine92.print();
        FuelOilEngine95 fuelOilEngine95 = new FuelOilEngine95();
        fuelOilEngine95.print();

        // 通过电动机适配器，将燃油发动机改造为电动机
        ElectricEngine ElectricEngine92 = new ElectricEngineAdapter(fuelOilEngine92);
        ElectricEngine92.print();
        ElectricEngine ElectricEngine95 = new ElectricEngineAdapter(fuelOilEngine95);
        ElectricEngine95.print();
    }
}
