package com.shipzhou.zblog.entity.pojo.article;

import com.shipzhou.zblog.annotation.AutoKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Shipzhou
 * @date: 2020/1/22
 * 评论表
 */
@Data
@Document(collection = "comment")
public class Comment {
    @AutoKey
    @Id
    private Long id;

    // 评论者的账号id
    @Field
    private Long accountId;

    // 评论文章的id
    @Field
    private Long articleId;

    // 回复某条消息的id 若是直接评论非回复消息 该值为0
    @Field
    private Long replyId;

    // 评论的内容
    @Field
    private String content;

    // 评论时间
    @Field
    private Long commentTime;

    // 赞同数
    @Field
    private Integer agreeNum;

    // 反对数
    @Field
    private Integer oppositionNum;

}
