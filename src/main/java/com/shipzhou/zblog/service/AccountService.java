package com.shipzhou.zblog.service;

import com.shipzhou.zblog.entity.dto.account.AccountDTO;
import com.shipzhou.zblog.entity.dto.account.UserInfoDTO;
import com.shipzhou.zblog.exception.checked.*;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
public interface AccountService {
    /**
     * 账号注册
     * @param accountDTO
     * @return
     */
    Long registeAccount(AccountDTO accountDTO) throws AccountExistException, PhoneExistException, EmailExistException;

    /**
     * 设置用户信息
     * @param userInfoDTO
     */
    void setUserInfo(UserInfoDTO userInfoDTO);

    /**
     * 统一密码登录接口
     * @param accountDTO
     * @return
     */
    String login(AccountDTO accountDTO) throws AccountNotExistException, AccountFrozenException, PasswordNotMatchException;
}
