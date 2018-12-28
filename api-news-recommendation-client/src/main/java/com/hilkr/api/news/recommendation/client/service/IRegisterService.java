package com.hilkr.api.news.recommendation.client.service;

import com.hilkr.api.news.recommendation.client.request.AuthorizationRequest;
import com.hilkr.api.news.recommendation.client.request.CheckUserNameRequest;
import com.hilkr.api.news.recommendation.client.request.LoginRequest;
import com.hilkr.api.news.recommendation.client.request.SignUpRequest;
import com.hilkr.api.news.recommendation.client.response.AuthorizationResponse;
import com.hilkr.api.news.recommendation.client.response.CheckUserNameResponse;
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
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);

    /**
     *
     * @param authorizationRequest
     * @return
     */
    AuthorizationResponse authorization(AuthorizationRequest authorizationRequest);

    /**
     * @param signUpRequest
     * @return
     */
    SignUpResponse signUp(SignUpRequest signUpRequest);

    /**
     *
     * @param checkUserNameRequest
     * @return
     */
    CheckUserNameResponse checkUserName(CheckUserNameRequest checkUserNameRequest);
}
