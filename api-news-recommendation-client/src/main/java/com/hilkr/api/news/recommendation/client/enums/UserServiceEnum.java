package com.hilkr.api.news.recommendation.client.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/27
 *
 */
@Getter
@AllArgsConstructor
public enum UserServiceEnum {

    LOGIN_SUCCESS("100","用户验证通过，跳转中..."),
    USER_NOT_EXIST("101","用户不存在"),
    PASSWORD_ERROR("102","密码错误"),
    TOKEN_ERROR("103","token 解析错误"),
    TOKEN_TIMEOUT("104","token 已失效"),
    TOKEN_ILLEGAL("105","非法的 token"),
    TOKEN_LEGAL("106","合法的 token"),
    USERNAME_ALREADY_EXISTS("107","该用户名已经存在"),
    USERNAME_ENABLE("108","该用户名可以使用"),
    SIGN_UP_SUCCESS("109","注册成功");

    String code;
    String msg;
}
