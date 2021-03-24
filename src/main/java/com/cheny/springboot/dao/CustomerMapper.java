package com.cheny.springboot.dao;

import com.cheny.springboot.domain.Customer;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    List<String> getCustomerName(String name);

    Customer getCustomerByName(String customername);
}