package com.hpugs.learning.rtti;

import org.apache.commons.lang3.RandomUtils;

import java.util.Optional;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/13 下午11:33
 */
public class RttiTest extends Parent implements Interface {

    @Override
    public void doSomething() {
        System.out.println("do RttiTest");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("do RttiTest arg=" + arg);
    }

    public static void main(String[] args) throws Throwable {

//        classLoad();

        genericity();

        optionalTest();
    }

    private static void classLoad() throws Throwable {
        Class rttiTest = Class.forName("com.hpugs.learning.rtti.RttiTest");
        System.out.println("class.getName()：" + rttiTest.getName());
        System.out.println("class.isInterface()：" + rttiTest.isInterface());
        System.out.println("class.getSimpleName()：" + rttiTest.getSimpleName());
        System.out.println("class.getCanonicalName()：" + rttiTest.getCanonicalName());

        Class<Initable1> initable1 = Initable1.class;
        System.out.println("after execute Initable1.class");
        System.out.println(Initable1.STATIC_FINAL1);
        System.out.println(Initable1.STATIC_FINAL2);

        new Initable2();
        System.out.println("after execute new Initable2()");
        System.out.println(Initable2.STATIC_NO_FIAN);

        Class<?> initable3 = Class.forName("com.hpugs.learning.rtti.RttiTest$Initable3");
        System.out.println("after execute Class.forName(\"Initable3\")");
        System.out.println(Initable3.STATIC_NO_FIAN);
    }

    private static void genericity() throws Throwable {
        Class intClass1 = int.class;
        intClass1 = Integer.class;
        intClass1 = Long.class;


        Class<Integer> intClass2 = int.class;
        intClass2 = Integer.class;
        // 泛型指定class类型后无法转换
        // intClass2 = Long.class;

        Class<?> intClass3 = int.class;
        intClass3 = Integer.class;
        intClass3 = Long.class;

        Class<? extends Number> intClass4 = int.class;
        intClass4 = Integer.class;
        intClass4 = Long.class;

        Class<? super RttiTest> objClass = RttiTest.class.getSuperclass();
        Object object = objClass.newInstance();
        System.out.println("object instanceof Object: " + (object instanceof Object));

        Parent parent = new RttiTest();
        Class<RttiTest> rttiTestClass = RttiTest.class;
        RttiTest rttiTest = rttiTestClass.cast(parent);

    }

    public static class Initable1 {

        public static final int STATIC_FINAL1 = 1;

        public static final int STATIC_FINAL2 = RandomUtils.nextInt();

        static {
            System.out.println("init Initable1");
        }

    }

    public static class Initable2 {
        public static int STATIC_NO_FIAN = 2;

        static {
            System.out.println("init Initable2");
        }
    }

    public static class Initable3 {
        public static int STATIC_NO_FIAN = 3;

        static {
            System.out.println("init Initable3");
        }
    }

    private static void optionalTest() {

        // Optional.of(null) 存在空指针问题
//        Optional optional = Optional.of(null);
//        System.out.println(optional.orElse("is null"));

        Optional optional = Optional.ofNullable(null);
        System.out.println("------------ifPresent:boolean-------------");
        System.out.println(optional.isPresent() ? "not null" : "is null");
        System.out.println("------------ifPresent:Consumer.accept-------------");
        optional.ifPresent((value) -> System.out.println("value=" + value));
        System.out.println("------------orElse-------------");
        System.out.println(optional.orElse("is null"));
        System.out.println("------------orElseGett-------------");
        optional.orElseGet(()-> "is null");
        System.out.println("------------orElseThrow-------------");
        try {
            optional.orElseThrow(() -> new NullPointerException("is null"));
        } catch (Throwable throwable) {
            System.out.println(throwable.toString());
        }

    }

}
