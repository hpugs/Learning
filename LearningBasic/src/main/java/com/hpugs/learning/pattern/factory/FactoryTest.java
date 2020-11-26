package com.hpugs.learning.pattern.factory;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午3:27
 */
public class FactoryTest {

    public static void main(String[] args) {
        factory1("dog", "哈士奇");
        factory1("cat", "tom");
    }

    private static void factory1(String type, String name) {
        // 根据类型获取工厂
        AnimalFactory animalFactory;
        switch (type){
            case "dog":
                animalFactory = new Dog();
                break;
            case "cat":
                animalFactory = new Cat();
                break;
            default:
                System.out.println("类型不存在");
                return;
        }

        // 通过工厂生成对象
        Animal animal = animalFactory.create(name);
        System.out.println(animal.getName());
        animal.leg();
        animal.eat();
    }

}
