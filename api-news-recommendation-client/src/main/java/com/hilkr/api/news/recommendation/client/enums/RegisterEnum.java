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
public enum RegisterEnum {

    LOGIN_SUCCESS("100","用户验证通过，跳转中..."),
    USER_NOT_EXIST("101","用户不存在"),
    PASSWORD_ERROR("102","密码错误"),
    USERNAME_ALREADY_EXISTS("103","用户名已经存在");

    String code;
    String msg;
}
