package com.shipzhou.zblog.entity.dto.account;

import com.shipzhou.zblog.annotation.AutoKey;
import com.shipzhou.zblog.entity.pojo.common.Area;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
@Data
public class UserInfoDTO {

    // 数据id
    private Long id;
    
    // 昵称
    private String nick;

    // 性别 0 未知 1 男  2 女
    private Integer gender;

    // 姓名
    private String name;

    // 生日
    private Long birthDay;

    // 地区
    private Area area;

    // 职业
    private String job;

    // 简介
    private String profile;
}
