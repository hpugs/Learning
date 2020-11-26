package com.hpugs.learning.pattern.builder;

/**
 * 生成器模式
 *
 * 1、定义组件库标准（Builder）
 * 2、根据组件库标准生成组件（CarBuilder）
 * 3、定义产品对象（Car）
 * 4、创建装配车间，定义产品装配流程（Director）
 *
 * @author gaoshang
 * date: 2020/11/26 下午5:36
 */
public class CreateCarTest {

    public static void main(String[] args) {
        // 初始化汽车组件库
        CarBuilder carBuilder = new CarBuilder();

        // 初始化装配车间
        Director director = new Director();

        // 将组件库传入生产轿车装配车间
        director.constructSportsCar(carBuilder);
        Car product = carBuilder.getProduct();
        product.print();

        // 将组件库传入生产SUV装配车间
        director.constructSportsSUV(carBuilder);
        product = carBuilder.getProduct();
        product.print();
    }

}
