package com.shipzhou.zblog.service;

import com.shipzhou.zblog.entity.dto.account.AccountDTO;
import com.shipzhou.zblog.exception.checked.AccountExistException;
import com.shipzhou.zblog.exception.checked.EmailExistException;
import com.shipzhou.zblog.exception.checked.PhoneExistException;

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
}
