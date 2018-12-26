package com.hilkr.api.news.recommendation.client.dal.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilkr.api.news.recommendation.client.dal.model.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<News> {

}