package com.hilkr.api.news.recommendation.client.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hilkr.api.news.recommendation.client.Utils.*;
import com.hilkr.api.news.recommendation.client.dal.dao.UserMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import com.hilkr.api.news.recommendation.client.enums.UserServiceEnum;
import com.hilkr.api.news.recommendation.client.enums.ResponseEnum;
import com.hilkr.api.news.recommendation.client.request.AuthorizationRequest;
import com.hilkr.api.news.recommendation.client.request.CheckUserNameRequest;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.AuthorizationResponse;
import com.hilkr.api.news.recommendation.client.response.CheckUserNameResponse;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.response.SignUpResponse;
import com.hilkr.api.news.recommendation.client.service.IRegisterService;
import com.hilkr.api.news.recommendation.client.vo.AuthorizationVO;
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
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        /*
         * 1. 判断用户名是否存在
         * 2. 通过用户名从数据库获取用户信息，生成 key
         * 3. 通过前端传过来的 uesrname 和 key ( 前后端保持一致的生成的规则，两者进行比对 ) 进行验证账户的准确性
         */
        try {
            log.info(" ###### [ RegisterServiceImpl-login:LoginResponse ] 接收到的参数为：{} ", JsonUtil.obj2Json(loginRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String username = loginRequest.getUsername();
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            log.info(" ###### [ RegisterServiceImpl-login:LoginResponse ] 用户: {} 不存在！", username);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setCode(UserServiceEnum.USER_NOT_EXIST.getCode());
            loginResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            loginResponse.setMsg(UserServiceEnum.USER_NOT_EXIST.getMsg());
            return loginResponse;
        } else {
            String password = user.getPassword();
            String time = loginRequest.getTime();
            String key = loginRequest.getKey();
            LoginResponse loginResponse = new LoginResponse();
            LoginVO loginVO = new LoginVO();
            if (LoginKeyUtil.checkLoginKey(username, password, time, key)) {
                log.info(" ###### [ RegisterServiceImpl-login:LoginResponse ] 用户：{} 验证通过！", username);
                loginResponse.setCode(ResponseEnum.SUCCESS.getCode());
                loginResponse.setIsSuccess(ResponseEnum.STATE_SUCCESS.getMsg());
                loginResponse.setMsg(ResponseEnum.SUCCESS.getMsg());
                loginVO.setUsername(user.getUsername());
                loginVO.setToken(TokenUtil.generateToken(key, username));
                loginResponse.setData(loginVO);
            } else {
                log.info(" ###### [ RegisterServiceImpl-login:LoginResponse ] 用户：{} 验证失败！", username);
                loginResponse.setCode(UserServiceEnum.PASSWORD_ERROR.getCode());
                loginResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
                loginResponse.setMsg(UserServiceEnum.PASSWORD_ERROR.getMsg());
            }
            loginResponse.setData(loginVO);
            return loginResponse;
        }
    }

    @Override
    public AuthorizationResponse authorization(AuthorizationRequest authorizationRequest) {
        /*
         * 1. 校验 token 中的用户信息
         * 2. 校验 token 是否失效
         * 3. 在验证为有效 token 时，更新 token 的有效时间，重新回传
         */
        try {
            log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ] 接收到的参数为：{} ", JsonUtil.obj2Json(authorizationRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        AuthorizationResponse authorizationResponse = new AuthorizationResponse();
        String username = authorizationRequest.getUsername();
        String token = authorizationRequest.getToken();
        Token tokenObj = null;
        try {
            log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ] tokenStr2Obj ！");
            tokenObj = TokenUtil.tokenStr2Obj(token);
        } catch (Exception e) {
            log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ] token 转换失败 ！");
            authorizationResponse.setCode(UserServiceEnum.TOKEN_ERROR.getCode());
            authorizationResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            authorizationResponse.setMsg(UserServiceEnum.TOKEN_ERROR.getMsg());
            return authorizationResponse;
        }
        if (!TokenUtil.checkTokenOwnerIsRigth(tokenObj, username)) {
            log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ] token 拥有者信息不相符 ！");
            authorizationResponse.setCode(UserServiceEnum.TOKEN_ILLEGAL.getCode());
            authorizationResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            authorizationResponse.setMsg(UserServiceEnum.TOKEN_ILLEGAL.getMsg());
            return authorizationResponse;
        }
        if (TokenUtil.checkTokenIsTimeOut(tokenObj)) {
            log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ]  token 失效 ！");
            authorizationResponse.setCode(UserServiceEnum.TOKEN_TIMEOUT.getCode());
            authorizationResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            authorizationResponse.setMsg(UserServiceEnum.TOKEN_TIMEOUT.getMsg());
            return authorizationResponse;
        }
        authorizationResponse.setCode(UserServiceEnum.TOKEN_LEGAL.getCode());
        authorizationResponse.setIsSuccess(ResponseEnum.STATE_SUCCESS.getMsg());
        authorizationResponse.setMsg(UserServiceEnum.TOKEN_LEGAL.getMsg());
        AuthorizationVO authorizationVO = new AuthorizationVO();
        String key = tokenObj.getKey();
        String newToken = TokenUtil.generateToken(key,username);
        log.info(" ###### [ RegisterServiceImpl-authorization:AuthorizationResponse ] token 验证通过,并且更新成功，token {{}} ！", newToken);
        authorizationVO.setToken(newToken);
        authorizationResponse.setData(authorizationVO);
        return authorizationResponse;

    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        /*
         * 1. 先判断一下用户名是否为空, 防止极端情况下的注册冲突
         * 2. 通过前端检验完成的数据进行数据库的数据插入
         */
        String username =signUpRequest.getUsername();
        SignUpResponse signUpResponse = new SignUpResponse();
        SignUpVO signUpVO = new SignUpVO();
        User userCheck = userMapper.selectByUserName(username);
        if (userCheck == null) {
            User user = new User();
            BeanUtils.copyProperties(signUpRequest, user);
            user.setNickname(username);
            try {
                userMapper.insert(user);
                signUpResponse.setCode(UserServiceEnum.SIGN_UP_SUCCESS.getCode());
                signUpResponse.setIsSuccess(ResponseEnum.STATE_SUCCESS.getMsg());
                signUpResponse.setMsg(UserServiceEnum.SIGN_UP_SUCCESS.getMsg());
                signUpVO.setUsername(username);
            } catch (Exception e) {
                signUpResponse.setCode(ResponseEnum.FAILED.getCode());
                signUpResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
                signUpResponse.setMsg(ResponseEnum.FAILED.getCode());
            }
        } else {
            signUpResponse.setCode(UserServiceEnum.USERNAME_ALREADY_EXISTS.getCode());
            signUpResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            signUpResponse.setMsg(UserServiceEnum.USERNAME_ALREADY_EXISTS.getMsg());
        }
        signUpResponse.setData(signUpVO);
        return signUpResponse;
    }

    @Override
    public CheckUserNameResponse checkUserName(CheckUserNameRequest checkUserNameRequest) {
        /*
         * 1. 根据用户名查询，进行反馈
         */
        String username =checkUserNameRequest.getUsername();
        User user = userMapper.selectByUserName(username);
        CheckUserNameResponse checkUserNameResponse = new CheckUserNameResponse();
        CheckUserNameVO checkUserNameVO = new CheckUserNameVO();
        if (user == null) {
            checkUserNameResponse.setCode(UserServiceEnum.USERNAME_ENABLE.getCode());
            checkUserNameResponse.setIsSuccess(ResponseEnum.STATE_SUCCESS.getMsg());
            checkUserNameResponse.setMsg(UserServiceEnum.USERNAME_ENABLE.getMsg());
            checkUserNameVO.setUsername(username);
        } else {
            checkUserNameResponse.setCode(UserServiceEnum.USERNAME_ALREADY_EXISTS.getCode());
            checkUserNameResponse.setIsSuccess(ResponseEnum.STATE_FAILED.getMsg());
            checkUserNameResponse.setMsg(UserServiceEnum.USERNAME_ALREADY_EXISTS.getMsg());
        }
        checkUserNameResponse.setData(checkUserNameVO);
        return checkUserNameResponse;
    }
}
