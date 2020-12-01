package com.hpugs.learning.pattern.structure.decorator;

/**
 * 抽象基础装饰
 *
 * @author gaoshang
 * date: 2020/12/1 下午3:37
 */
public class DataSourceDecorator implements DataSource {

    private DataSource dataSource;

    public DataSourceDecorator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void write(String txt) {
        dataSource.write(txt);
    }

    @Override
    public String read() {
        return dataSource.read();
    }
}
