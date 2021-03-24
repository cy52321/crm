package com.cheny.springboot.service;

import com.cheny.springboot.domain.DicType;
import com.cheny.springboot.domain.DicValue;

import java.util.List;

public interface DicService {
    List<DicType> getTypeList();
    List<DicValue> getListByCode(String code);
}
