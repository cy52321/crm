package com.cheny.springboot.controller;


import com.cheny.springboot.domain.User;
import com.cheny.springboot.exception.LoginException;
import com.cheny.springboot.service.impl.MyserviceImpl;
import com.cheny.springboot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/workbench")
public class WorkBenchController {
    @Autowired
    private MyserviceImpl myservice;
    @RequestMapping(value = "/activity/getUserList")
    @ResponseBody
    public List<User> selectAll(){
        List<User> list=myservice.select();
        return list;
    }


}
