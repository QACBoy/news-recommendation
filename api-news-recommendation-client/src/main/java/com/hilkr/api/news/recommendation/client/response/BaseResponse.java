package com.hilkr.api.news.recommendation.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/25
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private String code;
    private String msg;
    private String isSuccess;
    private T data;
}
