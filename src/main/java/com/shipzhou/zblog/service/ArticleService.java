package com.shipzhou.zblog.service;

import com.shipzhou.zblog.constant.AttrConstant;
import com.shipzhou.zblog.entity.dto.article.ArticleDTO;
import com.shipzhou.zblog.entity.pojo.article.Article;
import com.shipzhou.zblog.exception.checked.ArticleContentAndTitleNullException;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
public interface ArticleService  {

    /**
     * 创建草稿 在写博客时系统每分钟默认保存一次草稿 或用户主动点击存为草稿
     * @param articleDTO
     * @return
     */
    Long createDraftArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException;

    /**
     * 更新草稿
     * @param articleDTO
     * @throws ArticleContentAndTitleNullException
     */
    void updateDraftArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException;


    /**
     * 发布文章
     * @param articleDTO
     * @return
     */
    Long publishArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException;
}
