package com.shipzhou.zblog.service;

import com.shipzhou.zblog.entity.dto.article.CommentDTO;
import com.shipzhou.zblog.entity.pojo.account.Account;
import com.shipzhou.zblog.exception.checked.ObjectNullException;

/**
 * @author Shipzhou
 * @date: 2020/1/22
 */
public interface CommentService {

    /**
     * 添加一条评论
     * @param account
     * @param commentDTO
     * @return
     */
    Long createComment(Account account, CommentDTO commentDTO) throws ObjectNullException;
}
