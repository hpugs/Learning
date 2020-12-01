package com.hpugs.learning.common.constant;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午4:58
 */
public enum ErrorEnum {

    /**
     * 常见错误
     */
    DEFAULT_ERROR(100, "默认错误"),
    PARAMS_ERROR(101, "参数错误"),
    HAS_NO_POWER(102, "没有权限操作"),
    DB_ERROR(103, "数据库操作错误"),
    DATA_NOT_EXIST(104, "数据不存在"),
    DEGRADE_EXIT(105,"现在人数较多，请稍后再试"),
    USER_LOGIN_INFO_ERROR(113, "用户登录信息异常"),

    USER_NOT_LOGIN(10212, "用户未登录"),


    ;

    /**
     * 值
     */
    private int value;

    /**
     * 名称
     */
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ErrorEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

}
