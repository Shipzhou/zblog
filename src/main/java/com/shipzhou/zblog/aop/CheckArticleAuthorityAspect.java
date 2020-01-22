package com.shipzhou.zblog.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Shipzhou
 * @date: 2020/1/22
 */
@Aspect
@Component
@Slf4j
public class CheckArticleAuthorityAspect {
    //execution表达式自行搜索引擎
    @Pointcut("execution(* com.shipzhou.zblog.controller.ArticleController.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void printParam(JoinPoint joinPoint){
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info(method + ":" + params);
    }
}
