package com.hpugs.learning.pattern.factory.method;

/**
 * 定义一个模板类
 *
 * @author gaoshang
 * date: 2020/11/26 上午11:08
 */
public abstract class Animal {

    /**
     * 模板属性
     */
    private String name;

    /**
     * 模板方法
     */
    public void leg(){
        int count = getLeg();
        System.out.println(count + "腿");
    }

    /**
     * 自定义方法
     */
    public abstract void eat();

    public abstract int getLeg();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
