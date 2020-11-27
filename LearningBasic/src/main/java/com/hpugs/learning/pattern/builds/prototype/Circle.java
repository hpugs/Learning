package com.hpugs.learning.pattern.builds.prototype;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 上午11:51
 */
public class Circle extends Shape {

    public Double radius;

    public Circle() {
    }

    public Circle(Circle circle) {
        super(circle);
        this.radius = circle.radius;
    }

    @Override
    Circle cloneObj() {
        return new Circle(this);
    }
}
