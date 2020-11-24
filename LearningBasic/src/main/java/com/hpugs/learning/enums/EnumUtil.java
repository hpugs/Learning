package com.hpugs.learning.enums;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/18 上午10:06
 */
public class EnumUtil {

    public static void main(String[] args) {
//        traverseEnum();

//        System.out.println("--------------");

//        enumSet();

//        System.out.println("--------------");

//        enumMap();

        System.out.println("--------------");

        simpleTest();

    }

    private static void traverseEnum() {
        for (EnumSimple simple : EnumSimple.values()) {
            System.out.println("--------------");
            System.out.println(simple + " simple.ordinal=" + simple.ordinal());
            System.out.println("getName=" + simple.getName());
            System.out.println("EnumSimple.SECOND.compareTo?" + simple.compareTo(EnumSimple.SECOND));
            System.out.println("EnumSimple.SECOND.equals?" + simple.equals(EnumSimple.SECOND));
            System.out.println("EnumSimple.SECOND==?" + (simple == EnumSimple.SECOND));
            System.out.println("class=" + simple.getDeclaringClass());
            System.out.println("name=" + simple.name());
            System.out.println("name()=" + simple.toString());
        }

        System.out.println("--------------");

        for (String str : "FIRST,SECOND,THIRDLY".split(",")) {
            EnumSimple simple = Enum.valueOf(EnumSimple.class, str);
            System.out.println(simple);
        }
    }

    private static void enumSet() {
        // 当枚举数量小于64时，将枚举存储在Long的64位上，效率相对集合高了不知道多少倍
        // 当枚举数量大于64时，将枚举存储在Long的64位上，以64进行分组，存储在分组数量的Long集合中
        EnumSet<EnumBig> enumBigs = EnumSet.allOf(EnumBig.class);
        System.out.println(enumBigs.toString());

        EnumSet<EnumBig> points = EnumSet.noneOf(EnumBig.class);
        System.out.println(points.toString());
        points.add(EnumBig.A1);
        System.out.println(points.toString());
        points.addAll(EnumSet.of(EnumBig.A2, EnumBig.A3));
        System.out.println(points.toString());

        points.remove(EnumBig.A2);
        System.out.println(points);
        points.removeAll(EnumSet.of(EnumBig.A1, EnumBig.A3));
        System.out.println(points.toString());

        points = EnumSet.range(EnumBig.A4, EnumBig.A8);
        System.out.println(points.toString());
        points = EnumSet.complementOf(points);
        System.out.println(points.toString());
    }

    private static void enumMap() {
        // 采用数组存储，数组长度为枚举类型的数量
        EnumMap<EnumBig, String> bigMap = new EnumMap<>(EnumBig.class);
        bigMap.put(EnumBig.A1, EnumBig.A1.name().toLowerCase());
        bigMap.put(EnumBig.A2, EnumBig.A2.name().toLowerCase());
        bigMap.put(EnumBig.A3, EnumBig.A3.name().toLowerCase());
        bigMap.put(EnumBig.A4, EnumBig.A4.name().toLowerCase());
        System.out.println(bigMap.toString());
    }

    private static void simpleTest() {

        EnumSet.allOf(EnumSimple.class).stream().forEach(EnumSimple::pintName);

    }

}
