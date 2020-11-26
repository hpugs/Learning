package com.hpugs.learning.basic.annotation;

import java.lang.annotation.Repeatable;

/**
 * 可重入自定义注解
 *
 * @author gaoshang
 * date: 2020/11/19 下午3:20
 */
@Repeatable(ArrayAnnotations.class)
public @interface ArrayAnnotation {

    String name();

    int sort() default 0;

}
