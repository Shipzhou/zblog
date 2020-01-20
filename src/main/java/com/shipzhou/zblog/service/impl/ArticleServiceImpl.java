package com.shipzhou.zblog.service.impl;

import com.shipzhou.zblog.constant.ArticleConstant;
import com.shipzhou.zblog.entity.dto.article.ArticleDTO;
import com.shipzhou.zblog.entity.pojo.account.UserInfo;
import com.shipzhou.zblog.entity.pojo.article.Article;
import com.shipzhou.zblog.exception.checked.ArticleContentAndTitleNullException;
import com.shipzhou.zblog.exception.runtime.RequireParamNullException;
import com.shipzhou.zblog.service.ArticleService;
import com.shipzhou.zblog.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Shipzhou
 * @date: 2020/1/20
 */
@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Long createDraftArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException {
        // 检验必要参数是否缺失
        checkArticleRequireParam(articleDTO, false);
        Article draftArticle = Article.genDefaultDraftArticle();
        BeanUtils.copyProperties(articleDTO, draftArticle);
        mongoTemplate.insert(draftArticle);
        return draftArticle.getId();
    }

    @Override
    public void updateDraftArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException {
        // 检验必要参数是否缺失
        checkArticleRequireParam(articleDTO, true);

        // 更新数据
        doUpdateDraftArticle(articleDTO);
    }

    @Override
    public Long publishArticle(ArticleDTO articleDTO) throws ArticleContentAndTitleNullException {

        // 检验必要参数是否缺失
        checkArticleRequireParam(articleDTO, false);

        Article article = Article.genDefaultPublishArticle();
        BeanUtils.copyProperties(articleDTO, article);

        // 发布操作
        doPublishArticle(article);

        return article.getId();
    }

    private void doPublishArticle(Article article) {

        if (article.getId() == null)
            // 没有草稿的文章 直接创建
            mongoTemplate.insert(article);
        else
            // 有草稿的文章 更新
            doUpdatePublishArticle(article);
    }

    private void doUpdateDraftArticle(ArticleDTO articleDTO) {
        Query query = new Query(getCriteriaById(articleDTO.getId()));
        Update update = new Update();
        update.set("title", articleDTO.getTitle())
                .set("content", articleDTO.getContent())
                .set("sourceType", articleDTO.getSourceType())
                .set("contentType", articleDTO.getContentType())
                .set("tags", articleDTO.getTags())
                .set("accessType", articleDTO.getAccessType())
                .set("commentType", articleDTO.getCommentType())
                .set("lastEditTime", System.currentTimeMillis());
        mongoTemplate.updateFirst(query, update, Article.class);
    }

    private void doUpdatePublishArticle(Article article) {
        Query query = new Query(getCriteriaById(article.getId()));
        Update update = new Update();
        update.set("title", article.getTitle())
                .set("content", article.getContent())
                .set("sourceType", article.getSourceType())
                .set("contentType", article.getContentType())
                .set("tags", article.getTags())
                .set("accessType", article.getAccessType())
                .set("commentType", article.getCommentType())
                .set("createTime", article.getCreateTime())
                .set("lastEditTime", System.currentTimeMillis())
                .set("status", article.getStatus())
                .set("exist", article.getExist());
        mongoTemplate.updateFirst(query, update, Article.class);
    }

    private void checkArticleRequireParam(ArticleDTO articleDTO, Boolean checkId) throws ArticleContentAndTitleNullException {
        String title = articleDTO.getTitle();
        String content = articleDTO.getContent();
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content))
            throw new ArticleContentAndTitleNullException(); // 标题和内容都为空 抛出异常给控制层

        Integer sourceType = articleDTO.getSourceType();
        Integer accessType = articleDTO.getAccessType();
        Integer commentType = articleDTO.getCommentType();
        Long contentType = articleDTO.getContentType();

        // 以下参数不能缺少
        if (sourceType == null || accessType == null || commentType == null || contentType == null)
            throw new RequireParamNullException();

        if (checkId) {
            if (articleDTO.getId() == null)
                throw new RequireParamNullException();
        }
    }

}
