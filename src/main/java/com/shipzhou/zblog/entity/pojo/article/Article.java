package com.shipzhou.zblog.entity.pojo.article;

import com.shipzhou.zblog.annotation.AutoKey;
import com.shipzhou.zblog.constant.ArticleStatusEnum;
import com.shipzhou.zblog.constant.ExistEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 * 文章表
 */
@Data
@Document
public class Article {

    @AutoKey
    @Id
    private Long id;

    // 作者id
    @Field
    private Long accountId;

    // 文章标题
    @Field
    private String title;

    // 文章内容
    @Field
    private String content;

    // 文章来源类别 1 原创 2 转载 3 翻译
    @Field
    private String sourceType;

    // 文章内容分类 勾选创建的分类
    @Field
    private Long contentType;

    // 文章标签
    @Field
    private List<String> tags;

    // 文章可见权限 1 公开（任何人可见） 2 登录可见  3 粉丝可见  4 仅自己可见
    @Field
    private Integer accessType;

    // 文章评论权限 1 所有人可以评论（包括游客）  2 登录后才能评论 3 粉丝才能评论 4 仅自己评论
    @Field
    private Integer commentType;

    // 发布时间
    @Field
    private Long createTime;

    // 最后编辑时间
    @Field
    private Long lastEditTime;

    // 阅读数
    @Field
    private Integer readNum;

    // 评论数
    @Field
    private Integer commentNum;

    // 文章状态 1 已发布 2 草稿
    @Field
    private Integer status;

    // 删除标识 1 存在 0已删除
    @Field
    private Integer exist;

    public static Article genDefaultDraftArticle() {
        Article article = genDefaultArticle();
        article.setStatus(ArticleStatusEnum.DRAFT.getValue());
        return article;
    }

    public static Article genDefaultPublishArticle() {
        Article article = genDefaultArticle();
        article.setStatus(ArticleStatusEnum.PUBLISHED.getValue());
        article.setCreateTime(System.currentTimeMillis());
        return article;
    }

    private static Article genDefaultArticle() {
        return new Article(System.currentTimeMillis(), ExistEnum.TRUE.getValue(),0);
    }

    private Article(Long lastEditTime, Integer exist,Integer readNum) {
        this.lastEditTime = lastEditTime;
        this.exist = exist;
        this.readNum = readNum;
    }

    public Article() {
    }
}
