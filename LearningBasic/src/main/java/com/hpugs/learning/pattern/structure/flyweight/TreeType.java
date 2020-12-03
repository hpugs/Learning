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
public class TreeType {

    private String name;

    private Color color;

    private String otherTreeData;

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }

}
