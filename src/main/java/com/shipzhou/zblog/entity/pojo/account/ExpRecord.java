package com.shipzhou.zblog.entity.pojo.account;

import com.shipzhou.zblog.annotation.AutoKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 * 用户经验增长记录表
 */

@Data
@Document(collection = "exp_record")
public class ExpRecord {
    @AutoKey
    @Id
    private Long id;

    // 对应的用户信息id
    @Field
    private Long userInfoId;

    // 增长类型  1 签到  2 发文  3 评论  4 活动
    @Field
    private Integer type;

    // 此次增长的经验值
    @Field
    private Integer exp;

    // 获得经验的时间
    @Field
    private Long createTime;

    // 获得经验前的经验值
    @Field
    private Long oldExp;

    // 获得经验后的经验值
    @Field
    private Long newExp;

    // 获得经验后的总经验值
    @Field
    private Long totalExp;

    public ExpRecord(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public ExpRecord() {
    }
}
