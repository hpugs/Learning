package com.hpugs.learning.pattern.factory.abstractmethod;

/**
 * 抽象工厂模式
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:08
 */
public class Application {

    public ButtonFactory getButtonFactory(String type){
        switch (type){
            case "mac":
                return new MacFactory();
            case "win":
                return new WinFactory();
            case "linux":
                return new LinuxFactory();
            default:
                return null;
        }
    }

    public ConsoleFactory getConsoleFactory(String type){
        switch (type){
            case "mac":
                return new MacFactory();
            case "win":
                return new WinFactory();
            default:
                return null;
        }
    }

}
