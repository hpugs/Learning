package com.hpugs.learning.pattern.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:04
 */
public class WinButton implements Button {

    private String name;

    public WinButton(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Win系统按钮：" + name + "键");
    }

}
