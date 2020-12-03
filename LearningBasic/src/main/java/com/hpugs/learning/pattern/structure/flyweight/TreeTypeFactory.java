package com.hpugs.learning.pattern.structure.flyweight;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 工厂方法+享元模式
 * 工厂方法负责控制对象的创建
 * 享元模式保证对象仅创建一次
 *
 * @author gaoshang
 * date: 2020/12/3 下午5:52
 */
public class TreeTypeFactory {

    private static Map<String, TreeType> treeTypeMap = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType treeType = treeTypeMap.get(name);
        if (treeType == null) {
            treeType = new TreeType(name, color, otherTreeData);
            treeTypeMap.put(name, treeType);
        }
        return treeType;
    }

}
