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
public enum  ResponseEnum {

    SUCCESS("000","请求处理成功"),
    FAILED("001","请求处理失败，请重试！"),
    ILLEGAL_REQUEST("002","非法请求！"),
    STATE_SUCCESS("003","true"),
    STATE_FAILED("004","false");

    String code;
    String msg;
}
