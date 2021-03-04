package com.cheny.springboot.controller;

import com.cheny.springboot.domain.User;
import com.cheny.springboot.service.impl.MyserviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mycontroller {
    @Autowired
    private MyserviceImpl myservice;
    @RequestMapping(value = "/useradd")
    public String useradd(User user){
        Integer integer=myservice.insert(user);
        String res="";
        if (integer==1){
            res="user/addsuccess";
        }else {
            res="user/adderror";
        }
        return res;
    }
}
