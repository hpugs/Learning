package com.hpugs.learning.basic.annotation;

import java.lang.annotation.*;

/**
 * 简单的自定义注解
 *
 * @author gaoshang
 * date: 2020/11/19 下午2:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAnnotation {

    String name() default "DEFAULT";

    int sort();

}
