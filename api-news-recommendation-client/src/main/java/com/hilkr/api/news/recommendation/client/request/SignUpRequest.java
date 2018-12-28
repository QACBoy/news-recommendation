package com.hilkr.api.news.recommendation.client.request;

import lombok.*;
import org.apache.ibatis.annotations.Select;

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
public class SignUpRequest {

    private String username;

    private String password;

    private String tel;

    private String email;

    // private String token;
}
