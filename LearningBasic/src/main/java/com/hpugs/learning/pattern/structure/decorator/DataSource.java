package com.hpugs.learning.pattern.structure.decorator;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午3:23
 */
public interface DataSource {

    void write(String txt);

    String read();

}
