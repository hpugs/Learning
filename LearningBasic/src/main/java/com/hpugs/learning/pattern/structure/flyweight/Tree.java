package com.hpugs.learning.pattern.structure.flyweight;

import lombok.AllArgsConstructor;

import java.awt.*;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/3 下午5:47
 */
@AllArgsConstructor
public class Tree {

    private int x;

    private int y;

    public TreeType treeType;

    public void draw(Graphics graphics) {
        treeType.draw(graphics, x, y);
    }

}
