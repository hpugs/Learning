package com.hpugs.learning.pattern.structure.bridging;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午5:51
 */
public class Radio implements Device {

    private boolean on = false;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        this.on = true;
    }

    @Override
    public void disable() {
        this.on = false;
    }

    @Override
    public void printStatus() {
        System.out.println("Radio isEnabled=" + on);
    }
}
