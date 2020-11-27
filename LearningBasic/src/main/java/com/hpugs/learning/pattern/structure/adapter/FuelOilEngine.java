package com.hpugs.learning.pattern.structure.adapter;

/**
 * 燃油发动机
 *
 * @author gaoshang
 * date: 2020/11/27 下午4:34
 */
public interface FuelOilEngine {

    /**
     * 汽油型号
     */
    EnumFuelTypes getFuelTypes();

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
        System.out.println("燃油发动机 汽油型号：" + getFuelTypes().getType() + " 容量：" + getCap() + getUnit().getSymbol());
    }

}
