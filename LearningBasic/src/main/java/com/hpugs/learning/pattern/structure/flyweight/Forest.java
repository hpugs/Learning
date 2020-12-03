package com.hpugs.learning.pattern.structure.flyweight;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 外观模式
 *
 * @author gaoshang
 * date: 2020/12/3 下午5:57
 */
public class Forest extends JFrame {

    private java.util.List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeTypeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }

}
