package com.hpugs.learning.rtti;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/14 下午9:18
 */
public class InvokeTest implements Interface {

    private Interface anInterface;

    public InvokeTest(Interface anInterface) {
        this.anInterface = anInterface;
    }

    public static void main(String[] args) {
//        outInvokeInfo();

//        invokeTest();

        simpleProxy();
    }

    private static void outInvokeInfo(){
        Class obj = InvokeTest.class;

        Method[] methods = obj.getMethods();
        for (Method method : methods){
            System.out.println("methodName:" + method.toString());
        }

        Constructor[] constructors = obj.getConstructors();
        for (Constructor constructor : constructors){
            System.out.println("constructorName:" + constructor.toString());
        }
    }

    private static void invokeTest() {
        InvokeTest invokeTest = new InvokeTest(new RttiTest());
        invokeTest.doSomething();
        invokeTest.somethingElse("from InvokeTest");
    }

    @Override
    public void doSomething() {
        anInterface.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        anInterface.somethingElse(arg);
    }

    private static void simpleProxy() {
        RttiTest rttiTest = new RttiTest();
        outInterface(rttiTest);

        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[]{Interface.class}, new DynamicProxyHandler(rttiTest));
        outInterface(proxy);
    }

    private static void outInterface(Interface anInterface){
        anInterface.doSomething();
        anInterface.somethingElse("from simpleProxy");
    }
}
