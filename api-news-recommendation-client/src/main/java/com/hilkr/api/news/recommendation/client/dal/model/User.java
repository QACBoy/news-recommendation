package com.hilkr.api.news.recommendation.client.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel{

    private String username;

    private String password;

    private String tel;

    private String email;

    private String nickname;

    @Override
    protected Serializable pkVal() {
        return getId();
    }
}