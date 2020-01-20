package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
public enum  ArticleStatusEnum {
    PUBLISHED("已发布",1),
    DRAFT("草稿",2);

    private String name;
    private Integer value;

    ArticleStatusEnum(String name, Integer value) {
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
