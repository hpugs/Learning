package com.hpugs.learning.pattern.structure.bridging;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午6:00
 */
public class BaseRemote implements Remote {

    protected Device device;

    public BaseRemote(Device device) {
        this.device = device;
    }

    @Override
    public void open() {
        System.out.println("打开设备");
        device.enable();
    }

    @Override
    public void close() {
        System.out.println("关闭设备");
        device.disable();
    }
}
