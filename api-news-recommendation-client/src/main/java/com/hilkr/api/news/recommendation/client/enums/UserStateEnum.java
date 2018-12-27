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
public enum UserStateEnum {

    USER_NOT_EXIST("100","用户不存在"),
    USERNAME_ALREADY_EXISTS("101","用户名已经存在"),
    PASSWORD_ERROR("102","密码错误");

    String code;
    String msg;
}
