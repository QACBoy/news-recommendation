package com.hilkr.api.news.recommendation.client.dal.dao;

import com.hilkr.api.news.recommendation.client.BaseMapper;
import com.hilkr.api.news.recommendation.client.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);
}