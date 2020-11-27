package com.hpugs.learning.core.handle;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/27 下午2:43
 */
public abstract class Car {

    /**
     * 汽车名称
     */
    private String name;

    public abstract String getType();

    public final void build(){
        String config = getCarConfig();
        System.out.println(config);
    }

    public abstract String getCarConfig();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
