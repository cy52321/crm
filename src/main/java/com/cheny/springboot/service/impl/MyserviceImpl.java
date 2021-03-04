package com.cheny.springboot.service.impl;

import com.cheny.springboot.dao.UserMapper;
import com.cheny.springboot.domain.User;
import com.cheny.springboot.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyserviceImpl implements Myservice {
    @Autowired
    private UserMapper mapper;

    @Override
    @Transactional
    public Integer insert(User user) {
        int a=mapper.insert(user);
        return a;
    }
}
