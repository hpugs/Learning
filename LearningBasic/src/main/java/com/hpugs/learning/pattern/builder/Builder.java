package com.hpugs.learning.pattern.builder;

/**
 * 组件库标准
 *
 * @author gaoshang
 * date: 2020/11/26 下午5:15
 */
public interface Builder {

    /**
     * 重置安装
     */
    void reset(String carName);

    /**
     * 安装座椅
     */
    void setSeats(String name);

    /**
     * 安装引擎
     */
    void setEngine(String name);

    /**
     * 安装行程计算机
     */
    void setTripComputer(String name);

    /**
     * 安装GPS
     */
    void setGPS(String name);

}
