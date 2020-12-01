package com.hpugs.learning.pattern.structure.combination;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/30 下午3:00
 */
public class Circle extends Dot {

    public int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println(String.format("圆x=%d y=%d radius=%d", x, y, radius));
    }

}
