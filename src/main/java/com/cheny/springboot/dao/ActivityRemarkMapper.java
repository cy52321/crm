package com.cheny.springboot.dao;

import com.cheny.springboot.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    int getCountByIds(String[] ids);

    int deleteByIds(String[] ids);

    List<ActivityRemark> getRemarkListByAid(String aid);

    int deleteRemark(String id);

    int updateRemark(ActivityRemark activityRemark);

    int saveRemark(ActivityRemark activityRemark);
}