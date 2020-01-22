package com.shipzhou.zblog.entity.dto.article;

import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/22
 */
@Data
public class CommentDTO {

    private Long id;

    // 评论文章的id
    private Long articleId;

    // 回复某条消息的id 若是直接评论非回复消息 该值为0
    private Long replyId;

    // 评论的内容
    private String content;

}
