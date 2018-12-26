package com.hilkr.api.news.recommendation.client.service.impl;

import com.hilkr.api.news.recommendation.client.dal.dao.UsersMapper;
import com.hilkr.api.news.recommendation.client.dal.model.Users;
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
    UsersMapper usersMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Users users = usersMapper.selectByPrimaryKey(loginRequest.getUsername());
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
        Users users = new Users();
        BeanUtils.copyProperties(signUpRequest,users);
        usersMapper.insert(users);
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpVO signUpVO = new SignUpVO();
        signUpResponse.setCode("200");
        signUpResponse.setIsSuccess("true");
        signUpResponse.setMsg("用户注册成功！");
        signUpVO.setUsername(users.getUsername());
        signUpResponse.setData(signUpVO);
        return signUpResponse;
    }
}
