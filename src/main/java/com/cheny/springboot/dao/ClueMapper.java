package com.cheny.springboot.dao;

import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.Clue;
import com.cheny.springboot.domain.Customer;

import java.util.List;

public interface ClueMapper {
    int deleteByPrimaryKey(String id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    List<Clue> query();

    Clue detail(String id);

    List<Activity> getActivityListByClueId(String id);

    List<Activity> getActivityListByNameAndNotByClueId(String aname, String clueId);

    List<Activity> getActivityListByName(String aname);

    Customer selectCustomerByName(String company);
}