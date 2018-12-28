package com.hilkr.api.news.recommendation.client.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hilkr.api.news.recommendation.client.Utils.JsonUtil;
import com.hilkr.api.news.recommendation.client.Utils.LoginKeyUtil;
import com.hilkr.api.news.recommendation.client.Utils.TokenUtil;
import com.hilkr.api.news.recommendation.client.dal.dao.UserMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import com.hilkr.api.news.recommendation.client.enums.RegisterEnum;
import com.hilkr.api.news.recommendation.client.enums.ResponseEnum;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class IRegisterServiceImpl implements IRegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            log.info(" ****** 接收到的参数为：{} ", JsonUtil.obj2Json(loginRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String username = loginRequest.getUsername();
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            log.info(" ****** 用户: {} 不存在！", username);
            LoginResponse loginResponse = new LoginResponse();
            // LoginVO loginVO = new LoginVO();
            loginResponse.setCode(RegisterEnum.USER_NOT_EXIST.getCode());
            loginResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            loginResponse.setMsg(RegisterEnum.USER_NOT_EXIST.getMsg());
            // loginResponse.setData(loginVO);
            return loginResponse;
        } else {
            String password = user.getPassword();
            String time = loginRequest.getTime();
            String key = loginRequest.getKey();
            LoginResponse loginResponse = new LoginResponse();
            LoginVO loginVO = new LoginVO();
            if (LoginKeyUtil.checkLoginKey(username, password, time, key)) {
                log.info(" ****** 用户：{} 验证通过！", username);
                loginResponse.setCode(ResponseEnum.SUCCESS.getCode());
                loginResponse.setIsSuccess(ResponseEnum.STATE_SUCCESS.getMsg());
                loginResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
                loginVO.setUsername(user.getUsername());
                loginVO.setToken(TokenUtil.generateToken(key));
                loginResponse.setData(loginVO);
            } else {
                log.info(" ****** 用户：{} 验证失败！", username);
                loginResponse.setCode(RegisterEnum.PASSWORD_ERROR.getCode());
                loginResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
                loginResponse.setMsg(RegisterEnum.PASSWORD_ERROR.getMsg());
            }
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
        } catch (Exception e) {
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
        } else {
            checkUserNameResponse.setCode("401");
            checkUserNameResponse.setIsSuccess("false");
            checkUserNameResponse.setMsg("用户名重复了！");
            checkUserNameVO.setIsExist(1);
        }
        checkUserNameResponse.setData(checkUserNameVO);
        return checkUserNameResponse;
    }
}
