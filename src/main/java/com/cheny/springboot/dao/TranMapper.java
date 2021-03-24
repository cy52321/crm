package com.cheny.springboot.dao;

import com.cheny.springboot.domain.Tran;

import java.util.List;
import java.util.Map;

public interface TranMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);

    List<Tran> selectById();

    Tran selectId(String id);

    List<Map<String, Object>> getCharts();

    int getTotal();
}