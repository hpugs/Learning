package com.hpugs.learning.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可重入自定义注解
 *
 * @author gaoshang
 * date: 2020/11/19 下午3:13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayAnnotations {

    ArrayAnnotation[] value();

}
