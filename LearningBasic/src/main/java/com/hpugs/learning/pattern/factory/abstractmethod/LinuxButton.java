package com.hpugs.learning.pattern.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:22
 */
public class LinuxButton implements Button {

    private String name;

    public LinuxButton(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Linux系统按钮：" + name + "键");
    }
}
