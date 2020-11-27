package com.hpugs.learning.pattern.builds.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:06
 */
public class WinFactory implements ButtonFactory,ConsoleFactory {

    @Override
    public Button createButton(String name) {
        return new WinButton(name);
    }

    @Override
    public Console createConsole(String command) {
        return new WinConsole(command);
    }
}
