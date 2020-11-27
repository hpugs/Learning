package com.hpugs.learning.core.handle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午2:49
 */
@Component
public class CarFactory implements ApplicationContextAware, InitializingBean {

    private ApplicationContext context;

    private ConcurrentHashMap<String, Car> carMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, Car> beans = context.getBeansOfType(Car.class);
        for (Car car : beans.values()) {
            carMap.put(car.getType(), car);
        }
    }

    public Car getCar(String type) {
        return carMap.get(type);
    }
}
