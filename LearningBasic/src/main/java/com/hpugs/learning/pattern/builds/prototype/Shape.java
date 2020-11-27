package com.hpugs.learning.pattern.builds.prototype;

/**
 * 定义一个原型对象
 *
 * @author gaoshang
 * date: 2020/11/27 上午11:44
 */
public abstract class Shape {

    public Integer x;

    public Integer y;

    public String color;

    public Shape() {
    }

    public Shape(Shape shape) {
        this.x = shape.x;
        this.y = shape.y;
        this.color = shape.color;
    }

    /**
     * 设置clone对象的抽象方法
     *
     * @return
     */
    abstract Shape cloneObj();
}
