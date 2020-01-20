package com.shipzhou.zblog.entity.dto.article;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
@Data
public class ArticleDTO {

    private Long id;

    // 文章标题
    private String title;

    // 文章内容
    private String content;

    // 文章来源类别 1 原创 2 转载 3 翻译
    private Integer sourceType;

    // 文章内容分类 勾选创建的分类
    private Long contentType;

    // 文章标签
    private List<String> tags;

    // 文章可见权限 1 公开（任何人可见） 2 登录可见  3 粉丝可见  4 仅自己可见
    private Integer accessType;

    // 文章评论权限 1 所有人可以评论（包括游客）  2 登录后才能评论 3 粉丝才能评论 4 仅自己评论
    private Integer commentType;
}
