package com.shipzhou.zblog.entity.pojo.account;

import com.shipzhou.zblog.annotation.AutoKey;
import com.shipzhou.zblog.constant.AccountStatusEnum;
import com.shipzhou.zblog.constant.ExistEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
@Data
@Document(collection = "account")
public class Account {
    @AutoKey
    @Id
    private Long id;

    // 注册账号
    @Field
    private String account;

    // 注册密码 md5算法后
    @Field
    private String password;

    // 手机号
    @Field
    private String phone;

    // 邮箱
    @Field
    private String email;

    // 账号状态 1正常 2冻结
    @Field
    private Integer status;

    // 删除标识 1 存在 2已删除
    @Field
    private Integer exist;

    public static Account genNewNormalExistAccount() {
        return new Account(AccountStatusEnum.NORMAL.getStatus(), ExistEnum.TRUE.getValue());
    }

    public Account() {

    }

    private Account(Integer status, Integer exist) {
        this.status = status;
        this.exist = exist;
    }
}

