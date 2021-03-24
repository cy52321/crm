package com.cheny.springboot.controller;


import com.cheny.springboot.domain.Customer;
import com.cheny.springboot.domain.Tran;
import com.cheny.springboot.domain.TranHistory;
import com.cheny.springboot.domain.User;
import com.cheny.springboot.service.Myservice;
import com.cheny.springboot.service.TranService;
import com.cheny.springboot.utils.DateTimeUtil;
import com.cheny.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "workbench/transaction")
public class WorkBenchTransaction {
    @Autowired
    private Myservice myservice;
    @Autowired
    private TranService tranService;

    @RequestMapping(value = "/add")
    public ModelAndView add(){
        ModelAndView modelAndView=new ModelAndView();
        List<User> uList=myservice.select();
        modelAndView.addObject("uList",uList);
        modelAndView.setViewName("forward:/workbench/transaction/save.jsp");
        return modelAndView;
    }
    @RequestMapping(value = "/getCustomerName")
    @ResponseBody
    public List<String> getCustomerName(String name){
        List<String> list=tranService.getCustomerName(name);
        return list;
    }

    @RequestMapping(value = "/save")
    public ModelAndView save(Tran tran, String customername, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        tran.setId(UUIDUtil.getUUID());
        tran.setCreatetime(DateTimeUtil.getSysTime());
        tran.setCreateby(((User)request.getSession().getAttribute("user")).getName());
        boolean res=tranService.save(tran,customername);
        if(res){
            modelAndView.setViewName("redirect:/workbench/transaction/index.jsp");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getTranList")
    @ResponseBody
    public List<Tran> getTranList(){
        List<Tran> list=tranService.selectById();
        return list;
    }
    @RequestMapping(value = "/detail")
    public ModelAndView detail(String id){
        ModelAndView modelAndView=new ModelAndView();
        Tran tran=tranService.selectId(id);
        modelAndView.addObject("t",tran);
        modelAndView.setViewName("forward:/workbench/transaction/detail.jsp");
        return modelAndView;
    }
    @RequestMapping(value = "/getTranHistoryByTranId")
    @ResponseBody
    public List<TranHistory> getTranHistoryByTranId(String tranId){
        List<TranHistory> list=tranService.selectHistory(tranId);
        return list;
    }
    @RequestMapping(value = "/getCharts")
    @ResponseBody
    public Map<String,Object> getCharts(){
        Map<String,Object> map=tranService.getCharts();
        return map;
    }
}
