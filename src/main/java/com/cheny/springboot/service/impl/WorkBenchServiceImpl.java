package com.cheny.springboot.service.impl;

import com.cheny.springboot.Vo.PageVo;
import com.cheny.springboot.dao.ActivityMapper;
import com.cheny.springboot.dao.ActivityRemarkMapper;
import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.ActivityRemark;
import com.cheny.springboot.service.WorkBenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class WorkBenchServiceImpl implements WorkBenchService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<Activity> selectAll() {
        List<Activity> list=activityMapper.selectAll();
        return list;
    }

    @Override
    public int insert(Activity activity) {
        int a=activityMapper.save(activity);
        return a;
    }

    @Override
    public PageVo<Activity> selectcondition(Map<String,Object> map) {
        int count=activityMapper.selectcountbycondition(map);
        List<Activity> list=activityMapper.selectbycondition(map);
        PageVo<Activity> pageVo=new PageVo<>();
        pageVo.setTotal(count);
        pageVo.setDataList(list);
        return pageVo;
    }

    public Boolean delete(String[] ids) {
        Boolean flag=false;
        int count1=activityRemarkMapper.getCountByIds(ids);
        int count2=activityRemarkMapper.deleteByIds(ids);
        if(count1==count2){
            flag=true;
        }
        int count3=activityMapper.deleteByIds(ids);
        return flag;
    }

    @Override
    public Activity getListActivity(String id) {
        Activity activity=activityMapper.getListActivity(id);
        return activity;
    }

    @Override
    public String updateone(Activity activity) {
        String res="false";
        int a=activityMapper.updateone(activity);
        if(a==1){
            res="true";
        }
        return res;
    }

    @Override
    public Activity detail(String id) {
        Activity activity=activityMapper.detail(id);
        return activity;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String aid) {
        List<ActivityRemark> list=activityRemarkMapper.getRemarkListByAid(aid);
        return list;
    }

    @Override
    public boolean deleteRemark(String id) {
        boolean res=false;
        int a=activityRemarkMapper.deleteRemark(id);
        if(a==1){
            res=true;
        }
        return res;
    }

    @Override
    public boolean updateRemark(ActivityRemark activityRemark) {
        boolean res=false;
        int a=activityRemarkMapper.updateRemark(activityRemark);
        if(a==1){
            res=true;
        }
        return res;
    }

    @Override
    public boolean saveRemark(ActivityRemark activityRemark) {
        boolean res=false;
        int a=activityRemarkMapper.saveRemark(activityRemark);
        if(a==1){
            res=true;
        }
        return res;
    }
}
