package com.cheny.springboot.dao;

import com.cheny.springboot.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> selectAll();
    int save(Activity activity);

    List<Activity> selectbycondition(Map<String,Object> map);
    int selectcountbycondition(Map<String,Object> map);

    int deleteByIds(String[] ids);
    Activity getListActivity(String id);

    int updateone(Activity activity);

    Activity detail(String id);
}