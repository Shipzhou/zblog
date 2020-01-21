package com.shipzhou.zblog.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.shipzhou.zblog.constant.ResponseEnum;
import com.shipzhou.zblog.entity.vo.common.ResponseVO;
import com.shipzhou.zblog.exception.checked.UnAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(TokenExpiredException.class)
    public ResponseVO TokenExpiredException(HttpServletRequest request, TokenExpiredException e) throws Exception {
        return ResponseVO.genFailResponse(ResponseEnum.LOGINEXPIRE);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JWTVerificationException.class)
    public ResponseVO JWTVerificationException(HttpServletRequest request, JWTVerificationException e) throws Exception {
        return ResponseVO.genFailResponse(ResponseEnum.ILLEGALLOGIN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnAuthException.class)
    public ResponseVO UnAuthException(HttpServletRequest request, UnAuthException e) throws Exception {
        return ResponseVO.genFailResponse(ResponseEnum.UNAUTHORIZED);
    }

}
