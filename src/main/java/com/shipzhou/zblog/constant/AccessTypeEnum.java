package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 * 权限级别 依次提升
 */
public enum AccessTypeEnum {
    ALL("游客", 1),
    LOGIN("用户", 2),
    FANS("粉丝", 3),
    SELF("自己", 4),
    NOBODY("拒绝", 5);

    private String name;
    private Integer value;

    AccessTypeEnum(String name, Integer value) {
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
