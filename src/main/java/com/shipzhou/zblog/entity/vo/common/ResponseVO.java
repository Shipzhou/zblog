package com.shipzhou.zblog.entity.vo.common;

import com.shipzhou.zblog.constant.ResponseEnum;
import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/21
 * 统一返回实体
 */
@Data
public class ResponseVO {
    private Integer code;  // 状态码 判断请求是否成功
    private String mesaage; // 请求失败时的原因   如（登录密码错误、没有权限等 前端可直接展示该值）
    private Object data; // 返回请求的数据
    private String error; // 请求服务器报错时的错误信息 如 （请求xxApi异常 为了用户体验 前端最好自定义提示)

    public static ResponseVO genFailResponse(ResponseEnum responseEnum) {
        return new ResponseVO(responseEnum.getCode(),responseEnum.getDescribe());
    }

    public static ResponseVO genSuccessResponse(Object data) {
        return new ResponseVO(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getDescribe(),data);
    }

    public ResponseVO() {
    }

    private ResponseVO(Integer code, String mesaage) {
        this.code = code;
        this.mesaage = mesaage;
    }

    private ResponseVO(Integer code,String message, Object data) {
        this.code = code;
        this.data = data;
        this.mesaage = message;
    }
}
