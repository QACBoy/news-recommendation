package com.hilkr.api.news.recommendation.client.Utils;

import lombok.*;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token {
    private String time;
    private String username;
    private String key;
}
