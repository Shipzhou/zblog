package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
public enum  ExistEnum {
    TRUE("存在",1),
    FALSE("删除",0);

    private String name;
    private Integer value;

    ExistEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
