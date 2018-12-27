package com.hilkr.api.news.recommendation.client.service.impl;

import com.hilkr.api.news.recommendation.client.Utils.TokenUtils;
import com.hilkr.api.news.recommendation.client.dal.dao.UserMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import com.hilkr.api.news.recommendation.client.request.CheckUserNameRequest;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.CheckUserNameResponse;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.response.SignUpResponse;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import com.hilkr.api.news.recommendation.client.vo.CheckUserNameVO;
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
        String username = loginRequest.getUsername();
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            System.out.println("user 不存在");
            LoginResponse loginResponse = new LoginResponse();
            LoginVO loginVO = new LoginVO();
            loginResponse.setCode("404");
            loginResponse.setIsSuccess("false");
            loginResponse.setMsg("用户不存在");
            loginResponse.setData(loginVO);
            return loginResponse;
        } else {
            String password = user.getPassword();
            String token = loginRequest.getPassword();
            LoginResponse loginResponse = new LoginResponse();
            LoginVO loginVO = new LoginVO();
            if (TokenUtils.checkToken(username, password, token)) {
                loginResponse.setCode("200");
                loginResponse.setIsSuccess("true");
                loginResponse.setMsg("验证成功 ！");
                loginVO.setUsername(user.getUsername());
                loginResponse.setData(loginVO);
            }
            loginResponse.setCode("403");
            loginResponse.setIsSuccess("false");
            loginResponse.setMsg("密码错误 ！");
            loginResponse.setData(loginVO);
            return loginResponse;
        }
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        User user = new User();
        BeanUtils.copyProperties(signUpRequest, user);
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpVO signUpVO = new SignUpVO();
        try {
            userMapper.insert(user);
            signUpResponse.setCode("200");
            signUpResponse.setIsSuccess("true");
            signUpResponse.setMsg("用户注册成功！");
            signUpVO.setUsername(user.getUsername());
        }catch (Exception e){
            signUpResponse.setCode("402");
            signUpResponse.setIsSuccess("false");
            signUpResponse.setMsg("注册失败！");
            signUpVO.setUsername(user.getUsername());
        }
        signUpResponse.setData(signUpVO);
        return signUpResponse;
    }

    @Override
    public CheckUserNameResponse checkUserName(CheckUserNameRequest checkUserNameRequest) {
        User user = userMapper.selectByUserName(checkUserNameRequest.getUsername());
        CheckUserNameResponse checkUserNameResponse = new CheckUserNameResponse();
        CheckUserNameVO checkUserNameVO = new CheckUserNameVO();
        if (user == null) {
            checkUserNameResponse.setCode("200");
            checkUserNameResponse.setIsSuccess("true");
            checkUserNameResponse.setMsg("用户名验证通过！");
            checkUserNameVO.setIsExist(0);
        }else{
            checkUserNameResponse.setCode("401");
            checkUserNameResponse.setIsSuccess("false");
            checkUserNameResponse.setMsg("用户名重复了！");
            checkUserNameVO.setIsExist(1);
        }
        checkUserNameResponse.setData(checkUserNameVO);
        return checkUserNameResponse;
    }
}
