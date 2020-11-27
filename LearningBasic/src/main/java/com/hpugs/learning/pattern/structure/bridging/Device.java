package com.hpugs.learning.pattern.structure.bridging;

/**
 * 设备
 *
 * @author gaoshang
 * date: 2020/11/27 下午5:47
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    void printStatus();

}
