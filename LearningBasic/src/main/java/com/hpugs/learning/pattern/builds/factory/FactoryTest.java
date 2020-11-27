package com.hpugs.learning.pattern.builds.factory;

import com.hpugs.learning.pattern.builds.factory.abstractmethod.*;
import com.hpugs.learning.pattern.builds.factory.method.Animal;
import com.hpugs.learning.pattern.builds.factory.method.AnimalFactory;
import com.hpugs.learning.pattern.builds.factory.method.Cat;
import com.hpugs.learning.pattern.builds.factory.method.Dog;

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

        System.out.println("-----------------------");

        factory2("mac", "ctl");
        factory2("win", "ctl");
        factory2("linux", "ctl");

        System.out.println("-----------------------");

        factory3("mac", "终端");
        factory3("win", "cmd");
    }

    /**
     * 工厂方法模式
     *
     * @param type
     * @param name
     */
    private static void factory1(String type, String name) {
        // 根据类型获取工厂
        AnimalFactory animalFactory;
        switch (type) {
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

    /**
     * 抽象工厂方法模式
     *
     * @param type
     * @param name
     */
    private static void factory2(String type, String name) {
        Application application = new Application();
        ButtonFactory buttonFactory = application.getButtonFactory(type);
        Button button = buttonFactory.createButton(name);
        button.print();
    }

    /**
     * 抽象工厂方法模式
     *
     * @param type
     * @param command
     */
    private static void factory3(String type, String command) {
        Application application = new Application();
        ConsoleFactory consoleFactory = application.getConsoleFactory(type);
        Console console = consoleFactory.createConsole(command);
        console.print();
    }

}
