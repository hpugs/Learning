package com.hpugs.learning.pattern.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:06
 */
public class MacFactory implements ButtonFactory,ConsoleFactory {

    @Override
    public Button createButton(String name) {
        return new MacButton(name);
    }

    @Override
    public Console createConsole(String command) {
        return new MacConsole(command);
    }
}
