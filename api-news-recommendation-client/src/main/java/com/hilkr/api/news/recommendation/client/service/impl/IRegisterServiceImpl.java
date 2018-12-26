package com.hilkr.api.news.recommendation.client.service.impl;

import com.hilkr.api.news.recommendation.client.dal.dao.UserMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.response.SignUpResponse;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import com.hilkr.api.news.recommendation.client.vo.LoginVO;
import com.hilkr.api.news.recommendation.client.vo.SignUpVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/25
 *
 */
@Service
public class IRegisterServiceImpl implements IRegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User users = userMapper.selectByUserName(loginRequest.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        LoginVO loginVO = new LoginVO();
        loginResponse.setCode("200");
        loginResponse.setIsSuccess("true");
        loginResponse.setMsg("验证成功 ！");
        loginVO.setUsername(loginRequest.getUsername());
        loginResponse.setData(loginVO);
        return loginResponse;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        BeanUtils.copyProperties(signUpRequest, user);
        userMapper.insert(user);
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpVO signUpVO = new SignUpVO();
        signUpResponse.setCode("200");
        signUpResponse.setIsSuccess("true");
        signUpResponse.setMsg("用户注册成功！");
        signUpVO.setUsername(user.getUsername());
        signUpResponse.setData(signUpVO);
        return signUpResponse;
    }
}
