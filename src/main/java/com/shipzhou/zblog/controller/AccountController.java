package com.shipzhou.zblog.controller;

import com.shipzhou.zblog.annotation.Current;
import com.shipzhou.zblog.entity.pojo.account.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shipzhou
 * @date: 2020/1/21
 */
@RestController
public class AccountController {

    @GetMapping("/test")
    public void test(@Current Account account) {

    }
}
