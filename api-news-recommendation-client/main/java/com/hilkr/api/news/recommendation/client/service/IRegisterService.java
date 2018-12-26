package com.hilkr.api.news.recommendation.client.service;

import com.hilkr.api.news.recommendation.client.dal.model.Users;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.LoginResponse;
import com.hilkr.api.news.recommendation.client.response.SignUpResponse;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/25
 *
 */
public interface IRegisterService {

    /**
     *
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     *
     * @param signUpRequest
     * @return
     */
    SignUpResponse signUp(SignUpRequest signUpRequest);

}
