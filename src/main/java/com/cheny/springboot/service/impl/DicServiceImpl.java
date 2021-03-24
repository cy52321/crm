package com.cheny.springboot.service.impl;


import com.cheny.springboot.dao.DicTypeMapper;
import com.cheny.springboot.dao.DicValueMapper;
import com.cheny.springboot.domain.DicType;
import com.cheny.springboot.domain.DicValue;
import com.cheny.springboot.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicServiceImpl implements DicService {

    @Autowired
    private DicValueMapper dicValueMapper;
    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<DicType> getTypeList() {
        List<DicType> list=dicTypeMapper.getTypeList();
        return list;
    }

    @Override
    public List<DicValue> getListByCode(String code) {
        List<DicValue> list=dicValueMapper.getListByCode(code);
        return list;
    }
}
