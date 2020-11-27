package com.hpugs.learning.pattern.builds.prototype;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式
 *
 * @author gaoshang
 * date: 2020/11/27 上午11:54
 */
public class Application {

    private List<Shape> shapes = new ArrayList<>();

    public Application() {
        Circle circle = new Circle();
        circle.x = 1;
        circle.y = 2;
        circle.color = "red";
        shapes.add(circle);

        Circle circle1 = circle.cloneObj();
        shapes.add(circle1);

        Rectangle rectangle = new Rectangle();
        rectangle.width = 3;
        rectangle.height = 4;
        shapes.add(rectangle);
    }

    public void businessLogic(){
        List<Shape> copyShapes = new ArrayList<>();
        for (Shape shape : shapes){
            copyShapes.add(shape.cloneObj());
        }

        System.out.println(JSONObject.toJSONString(shapes));
        System.out.println("----------------");
        System.out.println(JSONObject.toJSONString(copyShapes));
    }

    public static void main(String[] args) {
        new Application().businessLogic();
    }
}
