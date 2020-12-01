package com.hpugs.learning.pattern.structure.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 *
 * @author gaoshang
 * date: 2020/11/30 下午3:11
 */
public class ImageEditor {

    public static void main(String[] args) {

        List<Graphic> graphicList = new ArrayList<>();

        graphicList.add(new Dot(1, 2));
        graphicList.add(new Circle(3, 4, 5));

        CompoundGraphic compoundGraphic = new CompoundGraphic();
        compoundGraphic.add(new Dot(6, 7));
        compoundGraphic.add(new Circle(8, 9, 0));
        graphicList.add(compoundGraphic);

        graphicList.forEach(graphic -> graphic.draw());
    }

}
