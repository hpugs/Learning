package com.hpugs.learning.basic.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午8:26
 */
public class ConcurrentHashMapTest {

    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) {

        /**
         * JDK-1.7 ConcurrentHashMap构造方法
         * ConcurrentHashMap 初始容量：16  负载因子：0.75f
         */
        initMap(16, 0.75f, 16);

        /**
         * 线程级更新变量
         */
        putOrderedObject();

        /**
         * cas测试
         */
        boolean flag = m.compareAndSet(0, 1);
        System.out.println("cas测试 flag=" + flag + "  " + "m.value=" + m.get());
        flag = m.compareAndSet(0, 2);
        System.out.println("cas测试 flag=" + flag + "  " + "m.value=" + m.get());

        /**
         * JDK-1.8 ConcurrentHashMap构造方法
         */
        initConcurrentHashMap(4);

        // 关键字段解析
        final int RESIZE_STAMP_BITS = 16;
        final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
        int rs = resizeStamp(16);
        System.out.println("rs = " + rs);
        int result = (rs << RESIZE_STAMP_SHIFT) + 2;
        System.out.println("result=" + result);

        // 获取数字大小
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.size();
    }

    static final int resizeStamp(int n) {
        final int RESIZE_STAMP_BITS = 16;
        return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
    }

    /**
     * JDK-1.7 ConcurrentHashMap构造方法
     *
     * @param initialCapacity
     * @param loadFactor
     * @param concurrencyLevel
     */
    private static void initMap(int initialCapacity,
                                float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();
        if (concurrencyLevel > 16)
            concurrencyLevel = 16;
        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        // 计算并行级别 ssize，因为要保持并行级别是 2 的 n 次方
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        // 我们这里先不要那么烧脑，用默认值，concurrencyLevel 为 16，sshift 为 4
        // 那么计算出 segmentShift 为 28，segmentMask 为 15，后面会用到这两个值
        int segmentShift = 32 - sshift;
        int segmentMask = ssize - 1;

        int MAXIMUM_CAPACITY = 1 << 30;
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;

        // initialCapacity 是设置整个 map 初始的大小，
        // 这里根据 initialCapacity 计算 Segment 数组中每个位置可以分到的大小
        // 如 initialCapacity 为 64，那么每个 Segment 或称之为"槽"可以分到 4 个
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;
        // 默认 MIN_SEGMENT_TABLE_CAPACITY 是 2，这个值也是有讲究的，因为这样的话，对于具体的槽上，
        // 插入一个元素不至于扩容，插入第二个的时候才会扩容
        int cap = 2;
        while (cap < c)
            cap <<= 1;

        System.out.println("initialCapacity=" + initialCapacity + "  loadFactor=" + loadFactor
                + "  concurrencyLevel=" + concurrencyLevel + "  sshift=" + sshift
                + "  ssize=" + ssize + "  c=" + c + "  cap=" + cap);
    }

    public static void putOrderedObject() {
        AtomicReference atomicReference = new AtomicReference();

        atomicReference.set(1);
        System.out.println("set=" + atomicReference.get());

        atomicReference.lazySet(2);
        System.out.println("lazySet=" + atomicReference.get());
    }

    public static void initConcurrentHashMap(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException();
        int MAXIMUM_CAPACITY = 1 << 30;
        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
                MAXIMUM_CAPACITY :
                tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
        System.out.println("sizeCtl=" + cap);
    }

    private static final int tableSizeFor(int c) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
