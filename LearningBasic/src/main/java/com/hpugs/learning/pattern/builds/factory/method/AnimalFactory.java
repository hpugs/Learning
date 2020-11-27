package com.hpugs.learning.pattern.builds.factory.method;

/**
 * 工厂方法模式
 *
 * @author gaoshang
 * date: 2020/11/26 下午3:23
 */
public interface AnimalFactory {

    /**
     * 创建对象
     *
     * @return 模板对象
     */
    Animal create(String name);

}
