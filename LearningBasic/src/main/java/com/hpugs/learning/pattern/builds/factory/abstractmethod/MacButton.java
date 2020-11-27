package com.hpugs.learning.pattern.builds.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:03
 */
public class MacButton implements Button {

    private String name;

    public MacButton(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Mac系统按钮：" + name + "键");
    }

}
