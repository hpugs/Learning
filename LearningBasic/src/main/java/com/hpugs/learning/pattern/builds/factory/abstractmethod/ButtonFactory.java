package com.hpugs.learning.pattern.builds.factory.abstractmethod;

/**
 * 抽象按钮工厂
 *
 * @author gaoshang
 * date: 2020/11/26 下午4:04
 */
public interface ButtonFactory {

    Button createButton(String name);

}
