package com.hpugs.learning.pattern.factory.abstractmethod;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:34
 */
public abstract class Console {

    public final void print(){
        System.out.println("命令行启动命令：" + getCommand());
    }

    abstract String getCommand();
}
