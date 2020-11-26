package com.hpugs.learning.pattern.builder;

/**
 * 装配车间
 *
 * @author gaoshang
 * date: 2020/11/26 下午5:27
 */
public class Director {

    /**
     * 定义产品装配流程
     *
     * @param builder
     */
    public void constructSportsCar(Builder builder){
        builder.reset("Car");
        builder.setEngine("T3引擎");
        builder.setSeats("织物座椅");
        builder.setGPS("GPS");
        builder.setTripComputer("10英寸行车电脑");
    }

    public void constructSportsSUV(Builder builder){
        builder.reset("SUV");
        builder.setEngine("T4引擎");
        builder.setSeats("真皮座椅");
        builder.setGPS("GPS+北斗导航");
        builder.setTripComputer("10英寸行车电脑");
    }
}
