package com.hilkr.api.news.recommendation.client.dal.dao;

import com.hilkr.api.news.recommendation.client.dal.model.News;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}