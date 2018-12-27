package com.hilkr.api.news.recommendation.client.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/27
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserNameRequest {

    private String username;
}
