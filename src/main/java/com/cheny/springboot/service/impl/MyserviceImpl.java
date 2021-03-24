package com.cheny.springboot.service.impl;

import com.cheny.springboot.dao.UserMapper;
import com.cheny.springboot.domain.User;
import com.cheny.springboot.exception.LoginException;
import com.cheny.springboot.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MyserviceImpl implements Myservice {
    @Autowired
    private UserMapper mapper;

    @Override
    @Transactional
    public User login(String loginAct, String loginPwd) throws LoginException {
        User user = mapper.selectByActPwd(loginAct,loginPwd);
        if (user==null){
            throw new LoginException("账号密码错误");
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> select() {
        List<User> list=mapper.selectAll();
        return list;
    }

    @Override
    public boolean updatePwd(String pwd, String id) {
        boolean res=false;
        int a=mapper.updatePwd(pwd,id);
        if(a==1){
            res=true;
        }
        return res;
    }


}
