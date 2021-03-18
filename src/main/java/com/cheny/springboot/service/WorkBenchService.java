package com.cheny.springboot.service;

import com.cheny.springboot.Vo.PageVo;
import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface WorkBenchService {
    List<Activity> selectAll();

    int insert(Activity activity);
    PageVo<Activity> selectcondition(Map<String,Object> map);
    Boolean delete(String[] ids);

    Activity getListActivity(String id);

    String updateone(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String aid);

    boolean deleteRemark(String id);

    boolean updateRemark(ActivityRemark activityRemark);

    boolean saveRemark(ActivityRemark activityRemark);
}
