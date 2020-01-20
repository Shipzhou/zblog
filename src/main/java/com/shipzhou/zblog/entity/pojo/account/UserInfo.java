package com.shipzhou.zblog.entity.pojo.account;

import com.shipzhou.zblog.annotation.AutoKey;
import com.shipzhou.zblog.constant.GenderEnum;
import com.shipzhou.zblog.entity.pojo.common.Area;
import com.shipzhou.zblog.exception.runtime.RequireParamNullException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 * 用户信息表
 */
@Data
@Document(collection = "user_info")
public class UserInfo {

    @AutoKey
    @Id
    private Long id;

    // 关联的账号id
    @Field
    private Long accountId;

    // 昵称
    @Field
    private String nick;

    // 等级
    @Field
    private Integer level;

    // 性别 0 未知 1 男  2 女
    @Field
    private Integer gender;

    // 姓名
    @Field
    private String name;

    // 生日
    @Field
    private Long birthDay;

    // 地区
    @Field
    private Area area;

    // 职业
    @Field
    private String job;

    // 简介
    @Field
    private String profile;

    // 关注量
    @Field
    private Long followNum;

    // 粉丝量
    @Field
    private Long fansNum;

    // 经验值/积分
    @Field
    private Long exp;

    public static UserInfo getDefaultUserInfo(Account account) {
        if (account.getId() == null)
            throw new RequireParamNullException();
        // 用户等级从1级开始 默认昵称为用户+id的形式
        String defaultNick = "用户" + account.getId();
        return new UserInfo(account.getId(),defaultNick,1, GenderEnum.UNKONW.getValue(),0L,0L,0L);
    }

    public UserInfo() {

    }

    private UserInfo(Long accountId,String nick, Integer level, Integer gender, Long followNum, Long fansNum, Long exp) {
        this.accountId = accountId;
        this.nick = nick;
        this.level = level;
        this.gender = gender;
        this.followNum = followNum;
        this.fansNum = fansNum;
        this.exp = exp;
    }
}
