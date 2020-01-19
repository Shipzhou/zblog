package com.shipzhou.zblog.entity.dto.account;

import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
@Data
public class AccountDTO {
    // 注册账号
    private String account;
    // 注册密码 md5算法后
    private String password;
    // 手机号
    private String phone;
    // 邮箱
    private String email;
    // 注册方式 1账号密码（设置安全问题找回） 2手机号（验证码找回） 3 邮箱（邮件验证找回）
    private Integer registeType;
}
