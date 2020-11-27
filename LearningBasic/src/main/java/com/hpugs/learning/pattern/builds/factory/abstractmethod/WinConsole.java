package com.hpugs.learning.pattern.builds.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:40
 */
public class WinConsole extends Console {

    private String command;

    public WinConsole(String command) {
        this.command = command;
    }

    @Override
    String getCommand() {
        return command;
    }
}
