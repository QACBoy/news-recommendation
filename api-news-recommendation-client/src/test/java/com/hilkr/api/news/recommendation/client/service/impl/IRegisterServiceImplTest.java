package com.hilkr.api.news.recommendation.client.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hilkr.api.news.recommendation.client.Utils.JsonUtil;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@Slf4j
public class IRegisterServiceImplTest {

    @Autowired
    IRegisterService iRegisterService;

    @Test
    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("hilkr");
        loginRequest.setKey("0f640018b41d241c12c175f2c3c76726");
        LoginResponse loginResponse = iRegisterService.login(loginRequest);
        try {
            log.info("返回的实体为：{} ",JsonUtil.obj2Json(loginResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signUp() {
    }
}