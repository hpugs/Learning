package com.hpugs.learning.pattern.structure.combination;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/30 下午2:58
 */
public class Dot implements Graphic {

    public int x;

    public int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void draw() {
        System.out.println(String.format("点x=%d y=%d", x, y));
    }

}
