package com.hpugs.learning.annotation;

import java.lang.reflect.Method;

/**
 * 自定义注解测试
 *
 * @author gaoshang
 * date: 2020/11/19 下午2:49
 */
public class AnnotationTest {

    public static void main(String[] args) {
        outAnnotation(AnnotationTest.class);
    }

    @SimpleAnnotation(name = "method1", sort = 1)
    private void method1() {
        System.out.println("method1");
    }

    @SimpleAnnotation(sort = 2)
    private void method2() {
        System.out.println("method2");
    }

    private void method3() {
        System.out.println("method3");
    }

    @ArrayAnnotations({
            @ArrayAnnotation(name = "method4", sort = 4),
            @ArrayAnnotation(name = "method4")
    })
    private void method4() {
        System.out.println("method4");
    }

    private static void outAnnotation(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            SimpleAnnotation annotation = method.getAnnotation(SimpleAnnotation.class);
            ArrayAnnotations arrayAnnotations = method.getAnnotation(ArrayAnnotations.class);
            if (annotation == null && arrayAnnotations == null) {
                System.out.println("方法：" + method.getName() + "未添加注解");
                continue;
            }

            if(annotation != null){
                System.out.println("方法：" + method.getName() + "；单注解name=" + annotation.name() + "；sort=" + annotation.sort());
            }

            if(arrayAnnotations != null){
                for (ArrayAnnotation arrayAnnotation : arrayAnnotations.value()){
                    System.out.println("方法：" + method.getName() + "；复注解name=" + arrayAnnotation.name() + "；sort=" + arrayAnnotation.sort());
                }
            }
        }
    }

}
