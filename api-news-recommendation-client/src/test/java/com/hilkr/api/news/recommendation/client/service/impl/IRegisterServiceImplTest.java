package com.hilkr.api.news.recommendation.client.service.impl;

import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/26
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IRegisterServiceImplTest {

    @Autowired
    IRegisterService iRegisterService;

    @Test
    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hilkr3");
        loginRequest.setPassword("hilkr");
        iRegisterService.login(loginRequest);
    }

    @Test
    public void signUp() {
    }
}