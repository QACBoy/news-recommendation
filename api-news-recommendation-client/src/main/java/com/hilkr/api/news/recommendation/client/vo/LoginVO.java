package com.hilkr.api.news.recommendation.client.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class LoginVO {
    private String username;
    private String nickname;
    private String token;
}
