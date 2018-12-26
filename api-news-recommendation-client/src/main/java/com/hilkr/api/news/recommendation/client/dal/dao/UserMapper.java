package com.hilkr.api.news.recommendation.client.dal.dao;

import com.hilkr.api.news.recommendation.client.dal.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    @Select("select * from user where username = #{username}")
    User selectByUserName(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}