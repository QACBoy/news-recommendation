package com.hilkr.api.news.recommendation.client.dal.dao;

import com.hilkr.api.news.recommendation.client.dal.model.Users;
import org.apache.ibatis.annotations.Select;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    @Select("select * from users where username = #{username}")
    Users selectByPrimaryKey(String username);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}