package com.hpugs.learning.pattern.structure.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 *
 * @author gaoshang
 * date: 2020/11/30 下午3:05
 */
public class CompoundGraphic implements Graphic {

    public List<Graphic> graphicList = new ArrayList<>();

    public void add(Graphic graphic){
        graphicList.add(graphic);
    }

    public void remove(Graphic graphic){
        graphicList.remove(graphic);
    }

    @Override
    public void move(int x, int y) {
        graphicList.forEach(graphic -> graphic.move(x, y));
    }

    @Override
    public void draw() {
        graphicList.forEach(graphic -> graphic.draw());
    }
}
