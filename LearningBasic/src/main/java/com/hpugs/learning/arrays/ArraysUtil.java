package com.hpugs.learning.arrays;

import java.util.Arrays;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/17 下午11:38
 */
public class ArraysUtil {

    public static void main(String[] args) {
//        setAll();

//        fill();

//        copyOf();

//        deepEquals();

//        sort();

//        binarySearch();

        parallelPrefix();
    }

    private static void setAll() {
        String[] strArr = new String[5];

        // 串行初始化数组
        Arrays.setAll(strArr, n -> n + "");
        outArr(strArr);

        // 并发初始化数组
        Arrays.parallelSetAll(strArr, n -> {
            System.out.println(n);
            return n + "" + n;
        });
        outArr(strArr);
    }

    private static void fill() {
        String[] strArr = new String[5];
        Arrays.fill(strArr, "fill");
        outArr(strArr);

        String[][] arr = new String[2][5];
        for (String[] str : arr){
            Arrays.fill(str, "fill");
        }
        outArr(arr);
    }

    private static void copyOf() {
        String[] strArr1 = new String[5];

        Arrays.setAll(strArr1, n -> n + "");
        outArr(strArr1);

        String[] strArr2 = Arrays.copyOf(strArr1, 10);
        outArr(strArr2);
    }

    private static void deepEquals() {
        String[] strArr1 = new String[5];

        Arrays.setAll(strArr1, n -> n + "");
        outArr(strArr1);

        String[] strArr2 = Arrays.copyOf(strArr1, strArr1.length);
        outArr(strArr2);

        boolean flag = Arrays.deepEquals(strArr1, strArr2);
        System.out.println("strArr1 == strArr2 ? " + flag);
    }

    private static void sort() {
        Integer[] intArr1 = {3, 1, 5, 2, 8, 9};
        outArr(intArr1);
        Arrays.sort(intArr1);
        outArr(intArr1);

        Integer[] intArr2 = {3, 1, 5, 2, 8, 9};
        outArr(intArr2);
        Arrays.parallelSort(intArr2);
        outArr(intArr2);
    }

    private static void binarySearch() {
        Integer[] intArr1 = {3, 1, 5, 2, 8, 9};
        // 2分查找
        int index = Arrays.binarySearch(intArr1, 5);
        System.out.printf("key=%d index=%d", 5, index);
    }

    private static void parallelPrefix() {
        Integer[] intArr1 = {3, 1, 5, 2, 8, 9};
        Arrays.parallelPrefix(intArr1, (first, second) -> first * second);
        outArr(intArr1);
    }

    private static <T> void outArr(T[] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

    private static <T> void outArr(T[][] arr) {
        System.out.println(Arrays.deepToString(arr));
    }

}
