package com.hpugs.learning.pattern.structure.bridging;

/**
 * 桥接模式
 *
 * @author gaoshang
 * date: 2020/11/27 下午6:04
 */
public class RemoteTest {

    public static void main(String[] args) {
        TV tv = new TV();
        useRemote(tv);

        System.out.println("-------------------");

        Radio radio = new Radio();
        useRemote(radio);
    }

    private static void useRemote(Device device){
        BaseRemote baseRemote = new BaseRemote(device);
        baseRemote.open();
        device.printStatus();
        baseRemote.close();
        device.printStatus();

        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.open();
        device.printStatus();
        advancedRemote.timingClose(500L);
        device.printStatus();
    }

}
