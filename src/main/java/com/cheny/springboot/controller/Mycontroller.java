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
import java.util.Map;

@Controller
public class Mycontroller {

    @Autowired
    private MyserviceImpl myservice;
    @RequestMapping(value = "/userlogin")
    @ResponseBody
    public Map<String, String> userlogin(HttpServletRequest request, String loginAct, String loginPwd){
        Map<String,String> map=new HashMap<>();
        try {
            String loginPwd2= MD5Util.getMD5(loginPwd);
            User user=myservice.login(loginAct,loginPwd2);
            if (user!=null){
                request.getSession().setAttribute("user",user);
                map.put("success","true");
            }
        } catch (LoginException e) {
            e.printStackTrace();
            map.put("success","false");
            map.put("msg",e.getMessage());
        }finally {
            return map;
        }
    }
    @RequestMapping(value = "/changepwd")
    @ResponseBody
    public Map<String,String> changepwd(HttpServletRequest request,String oldpwd,String newpwd,String confirmpwd){
        String res="";
        Map<String ,String> map=new HashMap<>();
        User user=(User)request.getSession().getAttribute("user");
        if(!MD5Util.getMD5(oldpwd).equals(user.getLoginPwd())){
            res="false1";
        }else {
            if(!newpwd.equals(confirmpwd)){
                res="false2";
            }else{
                String pwd=MD5Util.getMD5(confirmpwd);
                boolean r=myservice.updatePwd(pwd,user.getId());
                if(r){
                    res="true";
                }
            }
        }
        map.put("result",res);
        return map;
    }
}

