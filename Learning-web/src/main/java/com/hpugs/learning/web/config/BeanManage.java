package com.hpugs.learning.web.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/3 下午2:32
 */
@Component
public class BeanManage implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        /**
         * @PostConstruct注解好多人以为是Spring提供的。其实是Java自己的注解。

         * Java中该注解的说明：@PostConstruct该注解被用来修饰一个非静态的void（）方法。
         * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
         * PostConstruct在构造函数之后执行，init（）方法之前执行。

         * 通常我们会是在Spring框架中使用到@PostConstruct注解 该注解的方法在整个Bean初始化中的执行顺序：

         * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
         */
        System.out.println("-------------BeanManage已被初始化---------------");
    }

    @Override
    public void afterPropertiesSet() {
        BeanManage bean = applicationContext.getBean(BeanManage.class);
        if (bean == null) {
            System.out.println("-------------未获取到BeanManage---------------");
        }
        System.out.println("-------------获取到BeanManage---------------");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("-------------BeanManage正在将被销毁---------------");
    }

}
