package com.shipzhou.zblog.constant;

import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/21
 */
public enum  ResponseEnum {
    SUCCESS(200,"成功"),
    UNAUTHORIZED(4011,"未登录"),
    LOGINEXPIRE(4012,"登陆过期"),
    ILLEGALLOGIN(4013,"异常登录");
    private Integer code;

    private String describe;

    ResponseEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
