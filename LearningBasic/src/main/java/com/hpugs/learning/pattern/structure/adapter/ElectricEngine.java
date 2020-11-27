package com.hpugs.learning.pattern.structure.adapter;

/**
 * 电动发动机
 *
 * @author gaoshang
 * date: 2020/11/27 下午4:35
 */
public interface ElectricEngine {

    /**
     * 电压
     */
    String getVoltage();

    /**
     * 容量
     *
     * @return
     */
    Long getCap();

    /**
     * 动力单位
     *
     * @return
     */
    EnumUnit getUnit();

    default void print(){
        System.out.println("电动发动机 电压等级：" + getVoltage() + " 容量：" + getCap() + getUnit().getSymbol());
    }

}
