package com.hilkr.api.news.recommendation.client.dal.dao;

import com.hilkr.api.news.recommendation.client.BaseMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    int insert(User record);

    @Select("select * from user where username = #{username}")
    User selectByUserName(String name);
}