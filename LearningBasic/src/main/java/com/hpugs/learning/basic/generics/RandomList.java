package com.hpugs.learning.basic.generics;

import java.util.ArrayList;
import java.util.Random;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/16 下午4:17
 */
public class RandomList<T> extends ArrayList<T> {

    private Random random = new Random(100);

    public T select(){
        return get(random.nextInt(size()));
    }

}
