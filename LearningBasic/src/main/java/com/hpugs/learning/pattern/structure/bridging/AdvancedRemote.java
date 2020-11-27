package com.hpugs.learning.pattern.structure.bridging;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午6:01
 */
public class AdvancedRemote extends BaseRemote {

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void timingClose(Long timing) {
        try {
            System.out.println("定时关闭，定时：" + timing + "ms");
            Thread.sleep(timing);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        device.disable();
    }
}
