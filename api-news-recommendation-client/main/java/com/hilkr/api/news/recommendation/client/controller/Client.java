package com.hilkr.api.news.recommendation.client.controller;

import com.hilkr.api.news.recommendation.client.dal.test.TestDao;
import com.hilkr.api.news.recommendation.client.dal.test.TestInfo;
import com.hilkr.api.news.recommendation.client.dal.test.TestMsg;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.response.SignUpResponse;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/14
 *
 */
@RestController
public class Client {

    @Autowired
    private IRegisterService iRegisterService;

    public String userName = "hilkr";
    public String nickName = "hilkr";

    @GetMapping(value = "/")
    public TestDao testGet() {
        TestDao testDao = new TestDao();
        TestInfo testInfo = new TestInfo();
        testInfo.setUserName(this.userName);
        testInfo.setNickName(this.nickName);
        testDao.setCode("200");
        testDao.setInfo(testInfo);
        System.out.println("用户信息请求成功");
        return testDao;
    }

    @PostMapping(value = "/update-nickname")
    public TestDao testPost(@RequestBody TestInfo testInfo) {
        TestMsg testMsg = new TestMsg();
        System.out.println("接收到信息：nickName: " + testInfo.nickName + "  userName: " + testInfo.userName);
        testMsg.setMsg(testInfo.nickName);
        TestDao testDao = new TestDao();
        testDao.setCode("200");
        testDao.setInfo(testMsg);
        System.out.println("nickName:" + testInfo.nickName);
        return testDao;
    }

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return iRegisterService.login(loginRequest);
    }

    @PostMapping(value = "/sign-up")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return iRegisterService.signUp(signUpRequest);
    }
}
