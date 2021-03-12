package com.cheny.springboot.service;


import com.cheny.springboot.domain.User;
import com.cheny.springboot.exception.LoginException;

import java.util.List;

public interface Myservice {
    User login(String loginAct,String loginPwd) throws LoginException;
    List<User> select();
}
