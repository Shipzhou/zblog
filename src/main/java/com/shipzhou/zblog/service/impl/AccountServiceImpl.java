package com.shipzhou.zblog.service.impl;

import com.shipzhou.zblog.annotation.Current;
import com.shipzhou.zblog.constant.AccountStatusEnum;
import com.shipzhou.zblog.constant.RegisteTypeEnum;
import com.shipzhou.zblog.entity.dto.account.AccountDTO;
import com.shipzhou.zblog.entity.dto.account.UserInfoDTO;
import com.shipzhou.zblog.entity.pojo.account.Account;
import com.shipzhou.zblog.entity.pojo.account.ExpRecord;
import com.shipzhou.zblog.entity.pojo.account.UserInfo;
import com.shipzhou.zblog.exception.checked.*;
import com.shipzhou.zblog.exception.runtime.RequireParamNullException;
import com.shipzhou.zblog.service.AccountService;
import com.shipzhou.zblog.service.BaseService;
import com.shipzhou.zblog.util.JwtUtil;
import com.shipzhou.zblog.util.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Shipzhou
 * @date: 2020/1/19
 */
@Service
public class AccountServiceImpl extends BaseService implements AccountService {

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
        doRegisteAccount(account);;
        return account.getId();
    }

    @Override
    public void setUserInfo(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getId() == null)
            throw  new RequireParamNullException();
        Query query = new Query(getCriteriaById(userInfoDTO.getId()));
        Update update = new Update();
        update.set("nick",userInfoDTO.getNick())
        .set("gender",userInfoDTO.getGender())
        .set("name",userInfoDTO.getName())
        .set("birthDay",userInfoDTO.getBirthDay())
        .set("area",userInfoDTO.getArea())
        .set("job",userInfoDTO.getJob())
        .set("profile",userInfoDTO.getProfile());
        mongoTemplate.updateFirst(query,update,UserInfo.class);
    }

    @Override
    public String login(AccountDTO accountDTO) throws AccountNotExistException, AccountFrozenException, PasswordNotMatchException {
        // 检验登录参数
        checkLoginParam(accountDTO);

        // 查找账号
        Account account = getLoginAccount(accountDTO);

        // 判断账号状态
        if (account.getStatus() == AccountStatusEnum.FROZEN.getStatus())
            throw new AccountFrozenException();

        // 计算加盐后的密码是否和用户输入密码相匹配
        if (!MD5Utils.getSaltverifyMD5(accountDTO.getPassword(),account.getPassword()))
            throw new PasswordNotMatchException();

        return JwtUtil.genToken(account);
    }

    @Override
    public void test(Account account) {
        System.out.println(account.toString());
    }


    /**
     * 从数据库根据登录数据查找账号
     * @param accountDTO
     * @return
     * @throws AccountNotExistException
     */
    private Account getLoginAccount(AccountDTO accountDTO) throws AccountNotExistException {
        String account  =accountDTO.getAccount();
        String email = accountDTO.getEmail();
        String phone = accountDTO.getPhone();
        String password = accountDTO.getPassword();
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("account").is(account),Criteria.where("email").is(email),Criteria.where("phone").is(phone));
        Query query = new Query(criteria);

        Account findAccount = mongoTemplate.findOne(query,Account.class);
        if (findAccount == null)
            throw new AccountNotExistException();

        return findAccount;
    }

    private void checkLoginParam(AccountDTO accountDTO) {
        String account  =accountDTO.getAccount();
        String email = accountDTO.getEmail();
        String phone = accountDTO.getPhone();
        String password = accountDTO.getPassword();

        if ((StringUtils.isEmpty(account) && StringUtils.isEmpty(email) && StringUtils.isEmpty(phone)) || StringUtils.isEmpty(password))
            throw  new  RequireParamNullException();

    }

    private void doRegisteAccount(Account account) {
        // 创建账号
        doCreateAccount(account);
        // 初始化用户信息 这里的用户信息包括 关注、粉丝、积分、经验等
        initDefaulUserInfos(account);
    }

    private void initDefaulUserInfos (Account account) {
        // 创建默认的初始化用户信息
        UserInfo defaultUserInfo = UserInfo.getDefaultUserInfo(account);

        // 真正在数据库中创建一系列用户信息
        doCreateUserInfos(defaultUserInfo);
    }

    private void doCreateUserInfos(UserInfo defaultUserInfo) {
        // 数据库中创建用户信息数据 并返回id
        mongoTemplate.insert(defaultUserInfo);

    }

    private void doCreateAccount(Account account) {
        // 数据库中创建账号数据
        mongoTemplate.insert(account);
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
