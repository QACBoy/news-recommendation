package com.hilkr.api.news.recommendation.client.dal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News extends BaseModel{

    private String uuid;

    private String title;

    private String content;

    private String url;

    private Date releaseTime;

    @Override
    protected Serializable pkVal() {
        return getId();
    }
}