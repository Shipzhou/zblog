package com.shipzhou.zblog.service.impl;

import com.shipzhou.zblog.constant.RegisteTypeEnum;
import com.shipzhou.zblog.entity.dto.account.AccountDTO;
import com.shipzhou.zblog.entity.pojo.account.Account;
import com.shipzhou.zblog.exception.checked.AccountExistException;
import com.shipzhou.zblog.exception.checked.EmailExistException;
import com.shipzhou.zblog.exception.checked.PhoneExistException;
import com.shipzhou.zblog.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Long registeAccount(AccountDTO accountDTO) throws AccountExistException, PhoneExistException, EmailExistException {
        // 判断账号、邮箱、手机号是否已经被使用
        Integer type = accountDTO.getRegisteType();

        if (checkAccountExistance(accountDTO)) {
            if (type == RegisteTypeEnum.ACCOUNT.getType())
                throw new AccountExistException();
            if (type == RegisteTypeEnum.EMAIL.getType())
                throw new EmailExistException();
            if (type == RegisteTypeEnum.PHONE.getType())
                throw new PhoneExistException();
        }

        Account account = Account.genNewNormalExistAccount();
        BeanUtils.copyProperties(accountDTO,account);
        // 处理创建账号的业务
        doRegisteAccount(account);
        return account.getId();
    }

    private void doRegisteAccount(Account account) {
        // 存到数据库
        mongoTemplate.insert(account);
        // todo others
    }

    private void createDefaulUserInfo (Account account) {

    }


    /**
     * 检查登录的三项是否已经存在
     * @param accountDTO
     * @return
     */
    private boolean checkAccountExistance(AccountDTO accountDTO) {
        Criteria existCri = new Criteria();
        if (!StringUtils.isEmpty(accountDTO.getAccount()))
            existCri.orOperator(Criteria.where("account").is(accountDTO.getAccount()));
        if (!StringUtils.isEmpty(accountDTO.getEmail()))
            existCri.orOperator(Criteria.where("email").is(accountDTO.getEmail()));
        if (!StringUtils.isEmpty(accountDTO.getPhone()))
            existCri.orOperator(Criteria.where("phone").is(accountDTO.getPhone()));
        Query query = new Query(existCri);
        Account account = mongoTemplate.findOne(query,Account.class);
        if (account != null)
            return true;
        return false;
    }
}
