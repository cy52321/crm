package com.cheny.springboot.controller;


import com.cheny.springboot.Vo.PageVo;
import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.ActivityRemark;
import com.cheny.springboot.domain.User;
import com.cheny.springboot.service.impl.MyserviceImpl;
import com.cheny.springboot.service.impl.WorkBenchServiceImpl;
import com.cheny.springboot.utils.DateTimeUtil;
import com.cheny.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping(value = "/workbench")
public class WorkBenchActivity {
    @Autowired
    private MyserviceImpl myservice;
    @Autowired
    private WorkBenchServiceImpl workBenchService;

    @RequestMapping(value = "/activity/getUserList")
    @ResponseBody
    public List<User> selectAll(){
        List<User> list=myservice.select();
        return list;
    }

    @RequestMapping(value = "/activity/save")
    @ResponseBody
    public Map save(HttpServletRequest request,Activity activity){

        Map<String,String> map=new HashMap<>();
        /*Activity activity=new Activity();*/
        activity.setId(UUIDUtil.getUUID());
        /*activity.setOwner(request.getParameter("owner"));
        activity.setName(request.getParameter("name"));
        activity.setStartdate(request.getParameter("startDate"));
        activity.setEnddate(request.getParameter("endDate"));
        activity.setCost(request.getParameter("cost"));
        activity.setDescription(request.getParameter("description"));*/
        activity.setCreatetime(DateTimeUtil.getSysTime());
        activity.setCreateby(((User)request.getSession().getAttribute("user")).getName());
        int a=workBenchService.insert(activity);
        if(a==1){
            map.put("data","success");
        }else {
            map.put("data","fail");
        }
        return map;
    }

    @RequestMapping(value = "/activity/pageList")
    @ResponseBody
    public PageVo<Activity> pageList(HttpServletRequest request){
        int pageNo=Integer.valueOf(request.getParameter("pageNo"));
        int pageSize=Integer.valueOf(request.getParameter("pageSize"));
        int skipCount=(pageNo-1)*pageSize;
        Map<String,Object> map=new HashMap<>();
        map.put("name",request.getParameter("name"));
        map.put("owner",request.getParameter("owner"));
        map.put("startdate",request.getParameter("startdate"));
        map.put("enddate",request.getParameter("enddate"));
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize);
        PageVo<Activity> vo=new PageVo<>();
        vo=workBenchService.selectcondition(map);
        return vo;
    }
    @RequestMapping(value = "/activity/delete")
    @ResponseBody
    public Map<String,String> delete(HttpServletRequest request){
        Boolean success=false;
        String[] ids=request.getParameterValues("id");
        success=workBenchService.delete(ids);
        Map<String,String> map=new HashMap<>();
        map.put("data",String.valueOf(success));
        return map;
    }

    @RequestMapping(value = "/activity/getUserListAndActivity")
    @ResponseBody
    public Map<String,Object> getListActivity(String id){
        Activity activity=workBenchService.getListActivity(id);
        List<User> list=myservice.select();
        Map<String,Object> map=new HashMap<>();
        map.put("data",activity);
        map.put("list",list);
        return map;
    }

    @RequestMapping(value = "/activity/update")
    @ResponseBody
    public Map<String,Object> updateone(HttpServletRequest request,Activity activity){
        activity.setEdittime(DateTimeUtil.getSysTime());
        activity.setEditby(((User)request.getSession().getAttribute("user")).getName());
        String res=workBenchService.updateone(activity);
        Map<String,Object> map=new HashMap<>();
        map.put("success",res);
        return map;
    }
    @RequestMapping(value ="/activity/detail" )
    public ModelAndView detail(String id){
        ModelAndView modelAndView=new ModelAndView();
        Activity activity=workBenchService.detail(id);
        modelAndView.addObject("a",activity);
        modelAndView.setViewName("/workbench/activity/detail.jsp");
        return modelAndView;
    }

    @RequestMapping(value ="/activity/getRemarkListByAid" )
    @ResponseBody
    public List<ActivityRemark> getRemarkListByAid(String activityId){
        List<ActivityRemark> list=workBenchService.getRemarkListByAid(activityId);
        return list;
    }

    @RequestMapping(value ="/activity/deleteRemark" )
    @ResponseBody
    public boolean deleteRemark(String id){
        boolean success=workBenchService.deleteRemark(id);
        return success;
    }
    @RequestMapping(value ="/activity/updateRemark" )
    @ResponseBody
    public Map<String,Object> updateRemark(HttpServletRequest request,String id,String noteContent) throws InterruptedException {
        ActivityRemark activityRemark=new ActivityRemark();
        activityRemark.setId(id);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditFlag("1");
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        activityRemark.setEditBy(((User)request.getSession().getAttribute("user")).getName());
        Map<String,Object> map=new HashMap<>();
        /*ExecutorService threadpool=new ThreadPoolExecutor(2,5,1L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i=0;i<2;i++){
            threadpool.execute(()->{
            });
        }*/
        /*AtomicBoolean success= new AtomicBoolean(false);
        AtomicReference<ActivityRemark> activityRemark1= new AtomicReference<>(new ActivityRemark());
        Thread thread1=new Thread(()->{
            success=workBenchService.updateRemark(activityRemark);
        });
        thread1.start();
        thread1.join();
        new Thread(()->{
            activityRemark1=workBenchService.getRemarkById(id);
        }).start();
        map.put("success",success);
        map.put("ar",activityRemark1);*/
        boolean success=workBenchService.updateRemark(activityRemark);
        map.put("success",success);
        map.put("ar",activityRemark);

        return map;
    }

    @RequestMapping(value ="/activity/saveRemark")
    @ResponseBody
    public Map<String,Object> saveRemark(HttpServletRequest request,String noteContent,String activityId){
        ActivityRemark activityRemark=new ActivityRemark();
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setActivityId(activityId);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditFlag("0");
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setCreateBy(((User)request.getSession().getAttribute("user")).getName());
        boolean success=workBenchService.saveRemark(activityRemark);
        Map<String,Object> map=new HashMap<>();
        map.put("success",success);
        map.put("ar",activityRemark);
        return map;
    }


}
