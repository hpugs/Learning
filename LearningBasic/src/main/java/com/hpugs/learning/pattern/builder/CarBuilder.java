package com.hpugs.learning.pattern.builder;

/**
 * 汽车组件库
 *
 * @author gaoshang
 * date: 2020/11/26 下午5:18
 */
public class CarBuilder implements Builder {

    private Car car;

    @Override
    public void reset(String carName) {
        this.car = new Car();
        this.car.carConfig.append("name=").append(carName).append(";");
    }

    @Override
    public void setSeats(String name) {
        this.car.carConfig.append("Seats=").append(name).append(";");
    }

    @Override
    public void setEngine(String name) {
        this.car.carConfig.append("Engine=").append(name).append(";");
    }

    @Override
    public void setTripComputer(String name) {
        this.car.carConfig.append("TripComputer=").append(name).append(";");
    }

    @Override
    public void setGPS(String name) {
        this.car.carConfig.append("GPS=").append(name).append(";");
    }

    // 获取产品
    public Car getProduct() {
        Car product = car;
        return product;
    }
}
