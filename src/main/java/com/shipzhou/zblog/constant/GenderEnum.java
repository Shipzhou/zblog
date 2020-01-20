package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
public enum GenderEnum {
    UNKONW("未知",0),
    Male("男",1),
    Female("女",2);

    GenderEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
