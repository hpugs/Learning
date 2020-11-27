package com.hpugs.learning.pattern.builds.prototype;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 上午11:48
 */
public class Rectangle extends Shape {

    public Integer width;

    public Integer height;

    public Rectangle() {
    }

    public Rectangle(Rectangle source) {
        super(source);
        this.width = source.width;
        this.height = source.height;
    }

    @Override
    Rectangle cloneObj() {
        return new Rectangle(this);
    }
}
