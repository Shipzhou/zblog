package com.shipzhou.zblog.service.impl;

import com.shipzhou.zblog.annotation.AutoKey;
import com.shipzhou.zblog.constant.AccessTypeEnum;
import com.shipzhou.zblog.entity.dto.article.CommentDTO;
import com.shipzhou.zblog.entity.pojo.account.Account;
import com.shipzhou.zblog.entity.pojo.article.Article;
import com.shipzhou.zblog.exception.checked.ObjectNullException;
import com.shipzhou.zblog.exception.runtime.RequireParamNullException;
import com.shipzhou.zblog.service.BaseService;
import com.shipzhou.zblog.service.CommentService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author Shipzhou
 * @date: 2020/1/22
 */
@Service
public class CommentServiceImpl extends BaseService implements CommentService  {

    @AutoKey
    private MongoTemplate mongoTemplate;

   public Long createComment(Account account, CommentDTO commentDTO) throws ObjectNullException {
       // 校验有没有评论权限
       checkCommentPermission(account,commentDTO);
       return  0L;
   }

    private boolean checkCommentPermission(Account account, CommentDTO commentDTO) throws ObjectNullException {
       Long accountId = account.getId();
       Long articleId = commentDTO.getArticleId();
       String content = commentDTO.getContent();
       if (articleId == null || StringUtils.isEmpty(content))
           throw new RequireParamNullException();
        Query query = new Query(getCriteriaById(articleId));
        Article article = mongoTemplate.findOne(query,Article.class);
        if (article == null)
            throw new ObjectNullException("该文章已被删除");

        Integer commentType = article.getCommentType();
        // 暂时不开放游客评论 所以登录用户是评论的最宽权限
        if (commentType == AccessTypeEnum.LOGIN.getValue())
            return true;
        else if (commentType == AccessTypeEnum.FANS.getValue()) {
            checkIsAuthorFans(account,article);
        }
    }

    private void checkIsAuthorFans(Account account, Article article) {
    }
}
