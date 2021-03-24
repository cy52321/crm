package com.cheny.springboot.dao;

import com.cheny.springboot.domain.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByActPwd(String loginAct,String loginPwd);
    List<User> selectAll();

    int updatePwd(String pwd, String id);
}