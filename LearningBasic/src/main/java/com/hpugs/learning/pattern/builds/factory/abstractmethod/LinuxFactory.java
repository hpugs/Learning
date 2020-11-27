package com.hpugs.learning.pattern.builds.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:23
 */
public class LinuxFactory implements ButtonFactory{

    @Override
    public Button createButton(String name) {
        return new LinuxButton(name);
    }

}
