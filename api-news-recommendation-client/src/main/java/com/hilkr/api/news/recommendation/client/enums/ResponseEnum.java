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
    FAILED("001","请求处理失败"),
    STATE_SUCCESS("002","true"),
    STATE_FAILED("003","false");

    String code;
    String msg;
}
