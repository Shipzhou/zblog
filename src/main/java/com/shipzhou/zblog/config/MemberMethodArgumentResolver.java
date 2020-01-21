package com.shipzhou.zblog.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.shipzhou.zblog.annotation.Current;
import com.shipzhou.zblog.entity.pojo.account.Account;
import com.shipzhou.zblog.exception.checked.UnAuthException;
import com.shipzhou.zblog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class MemberMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // 带有@Current注解的参数 进行解析
        if (parameter.getParameterType() == Account.class && parameter.hasParameterAnnotation(Current.class))
            return true;

        return false;
    }

    @Override
    public Account resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws UnAuthException {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token))
            throw new UnAuthException();

        Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
        String accountJson = claimMap.get("accountJson").asString();
        Account account = JSONObject.parseObject(accountJson,Account.class);
        return account;
    }

}
