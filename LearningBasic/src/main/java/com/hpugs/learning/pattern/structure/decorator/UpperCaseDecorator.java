package com.hpugs.learning.pattern.structure.decorator;

/**
 * 转大写字母装饰
 *
 * @author gaoshang
 * date: 2020/12/1 下午3:39
 */
public class UpperCaseDecorator extends DataSourceDecorator {

    public UpperCaseDecorator(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void write(String txt) {
        super.write(txt.toUpperCase());
    }

    @Override
    public String read() {
        return super.read();
    }
}
