package com.hpugs.learning.pattern.builds.builder;

/**
 * 汽车模型
 *
 * @author gaoshang
 * date: 2020/11/26 下午5:15
 */
public class Car {

    public StringBuffer carConfig = new StringBuffer();

    public void print(){
        System.out.println(carConfig.toString());
    }

}
