package com.shipzhou.zblog.entity.vo.article;

import com.shipzhou.zblog.entity.pojo.article.Article;
import lombok.Data;

/**
 * @author Shipzhou
 * @date: 2020/1/21
 */
@Data
public class ListArticleVO extends Article {

    // 阅读数
    private Integer readNum;

    // 评论数
    private Integer commentNum;
}
