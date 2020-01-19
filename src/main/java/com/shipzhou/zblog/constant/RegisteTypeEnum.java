package com.shipzhou.zblog.constant;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
public enum RegisteTypeEnum {
    ACCOUNT("账号",1),
    EMAIL("邮箱",2),
    PHONE("手机",3);

    private String typeName;
    private Integer type;

    RegisteTypeEnum(String typeName, Integer type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getType() {
        return type;
    }
}
