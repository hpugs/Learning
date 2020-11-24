package com.hpugs.learning.generics;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午3:49
 */
public class GenericsTest {

    public static void main(String[] args) {
        createRobot();

        Return<String, Integer> aReturn = createReturn();
        System.out.println(aReturn);

        SubReturn<Boolean, String, Integer> aSubReturn = createSubReturn();
        System.out.println(aSubReturn);

        randomTest();

        Stream.generate(new Fibonacci()).limit(18).map(n -> n + " ").forEach(System.out::print);
        System.out.println();

        GenericsTest genericsTest = new GenericsTest();
        genericsTest.out('a');
        genericsTest.out("abc");
        genericsTest.out(1);
        genericsTest.out(2);
        genericsTest.out(3L);
        genericsTest.out(Boolean.TRUE);

        System.out.println("----- makeList --------");
        List<String> strings = makeList("ABCDEFGH".split(""));
        System.out.println(strings);
        strings.add("O");
        System.out.println(strings);
        strings.addAll(makeList("PQRSTUVWXYZ".split("")));
        System.out.println(strings);

        System.out.println("----- Arrays.asList --------");
//        strings = Arrays.asList("ABCDEFGH".split(""));
//        System.out.println(strings);
//        strings.add("O");
//        System.out.println(strings);
//        strings.addAll(makeList("PQRSTUVWXYZ".split("")));
//        System.out.println(strings);

        Set<EnumWatercolors> set1 = EnumSet.range(EnumWatercolors.ZINC, EnumWatercolors.ORANGE);
        System.out.println(set1);

        Set<EnumWatercolors> set2 = EnumSet.range(EnumWatercolors.BRILLIANT_RED, EnumWatercolors.COBALT_BLUE_HUE);
        System.out.println(set2);


        // 泛型擦除
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2);
    }

    /**
     * 泛型应用
     */
    private static void createRobot() {
        GenericsRobot1 genericsRobot1 = new GenericsRobot1(new Robot("机器人"));
        System.out.println(genericsRobot1.getRobot().toString());

        GenericsRobot2 genericsRobot2 = new GenericsRobot2(new Robot("Object机器人"));
        System.out.println(genericsRobot2.getRobot().toString());

        GenericsRobot3<Robot> genericsRobot3 = new GenericsRobot3(new Robot("泛型机器人"));
        System.out.println(genericsRobot3.getRobot().toString());
    }

    /**
     * 泛型元组测试
     *
     * @return 元组
     */
    private static Return<String, Integer> createReturn() {
        return new Return<>("第一个返回参数", 200);
    }

    /**
     * 泛型元组测试
     *
     * @return 元组
     */
    private static SubReturn<Boolean, String, Integer> createSubReturn() {
        return new SubReturn<>(Boolean.TRUE, "第二个返回参数", 200);
    }

    /**
     * 集合泛型
     */
    private static void randomTest() {

        RandomList<String> randomList = new RandomList();

        String str = "JL HYK SB JAVA IOS Android hpugs";
        Arrays.asList(str.split(" ")).forEach(randomList::add);

        IntStream.range(0, 10).forEach(i -> System.out.print(randomList.select() + " "));
    }

    /**
     * 方法泛型
     *
     * @param obj
     * @param <T>
     */
    private <T> void out(T obj) {
        System.out.println("className：" + obj.getClass().getName() + "  value：" + obj.toString());
    }

    /**
     * 转集合
     *
     * @param arrs
     * @param <T>
     * @return
     */
    private static <T> List<T> makeList(T... arrs) {
        ArrayList<T> arrayList = new ArrayList<>();
        Arrays.asList(arrs).forEach(arrayList::add);
        return arrayList;
    }

}
