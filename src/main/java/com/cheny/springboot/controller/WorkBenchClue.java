package com.cheny.springboot.controller;



import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.Clue;
import com.cheny.springboot.domain.Tran;
import com.cheny.springboot.domain.User;
import com.cheny.springboot.service.impl.ClueServiceImpl;
import com.cheny.springboot.service.impl.MyserviceImpl;
import com.cheny.springboot.utils.DateTimeUtil;
import com.cheny.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/workbench/clue")
public class WorkBenchClue {

    @Autowired
    private ClueServiceImpl clueService;

    @Autowired
    private MyserviceImpl myservice;

    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public List<User> getUserList(){
        List<User> list=myservice.select();
        return list;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public boolean save(HttpServletRequest request,Clue clue){
        clue.setId(UUIDUtil.getUUID());
        clue.setCreatetime(DateTimeUtil.getSysTime());
        clue.setCreateby(((User)request.getSession().getAttribute("user")).getName());
        boolean res=clueService.save(clue);
        return res;
    }
    @RequestMapping(value = "/query")
    @ResponseBody
    public List<Clue> query(){
        List<Clue> list=clueService.query();
        return list;
    }

    @RequestMapping(value ="/detail" )
    public ModelAndView detail(String id){
        ModelAndView modelAndView=new ModelAndView();
        Clue clue=clueService.detail(id);
        modelAndView.addObject("c",clue);
        modelAndView.setViewName("forward:/workbench/clue/detail.jsp");
        return modelAndView;
    }

    @RequestMapping(value = "/getActivityListByClueId")
    @ResponseBody
    public List<Activity> getActivityListByClueId(String clueId){
        List<Activity> list=clueService.getActivityListByClueId(clueId);
        return list;
    }
    @RequestMapping(value = "/unbund")
    @ResponseBody
    public boolean unbund(String id){
        boolean res=clueService.delete(id);
        return res;
    }
    @RequestMapping(value = "/getActivityListByNameAndNotByClueId")
    @ResponseBody
    public List<Activity> getActivityListByNameAndNotByClueId(String aname,String clueId){
        List<Activity> list=clueService.getActivityListByNameAndNotByClueId(aname,clueId);
        return list;
    }
    @RequestMapping(value = "/bund")
    @ResponseBody
    public boolean bund(HttpServletRequest request){
        String cid=request.getParameter("cid");
        String[] aids=request.getParameterValues("aid");
        boolean res=clueService.bund(cid,aids);
        return res;
    }
    @RequestMapping(value = "/getActivityListByName")
    @ResponseBody
    public List<Activity> getActivityListByName(String aname){
        List<Activity> list=clueService.getActivityListByName(aname);
        return list;
    }
    @RequestMapping(value = "/convert")
    public String convert(HttpServletRequest request){
        String s="";
        String clueId=request.getParameter("clueId");
        String flag=request.getParameter("flag");
        String createBy=((User)request.getSession().getAttribute("user")).getName();
        Tran t=null;
        if("a".equals(flag)){
            t=new Tran();
            t.setId(UUIDUtil.getUUID());
            t.setCreatetime(DateTimeUtil.getSysTime());
            t.setCreateby(((User)request.getSession().getAttribute("user")).getName());
            t.setMoney(request.getParameter("money"));
            t.setName(request.getParameter("name"));
            t.setExpecteddate(request.getParameter("expectedDate"));
            t.setStage(request.getParameter("stage"));
            t.setActivityid(request.getParameter("activityId"));
        }
        boolean flag1=clueService.convert(clueId,t,createBy);
        if(flag1){
            s="redirect:"+request.getContextPath()+"/workbench/clue/index.jsp";
        }
        return s;
    }
}
