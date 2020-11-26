package com.hpugs.learning.pattern.factory.method;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午3:22
 */
public class Cat extends Animal implements AnimalFactory {

    public Cat() {
    }

    public Cat(String name) {
        this.setName(name);
    }

    @Override
    public void eat() {
        System.out.println("爱吃鱼");
    }

    @Override
    public int getLeg() {
        return 4;
    }

    @Override
    public Animal create(String name) {
        return new Cat(name);
    }
}
