package com.hpugs.learning.pattern.factory.method;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午3:20
 */
public class Dog extends Animal implements AnimalFactory {

    public Dog() {
    }

    public Dog(String name) {
        this.setName(name);
    }

    @Override
    public void eat() {
        System.out.println("爱吃骨头");
    }

    @Override
    public int getLeg() {
        return 4;
    }

    @Override
    public Animal create(String name) {
        return new Dog(name);
    }
}
