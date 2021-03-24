package com.cheny.springboot.service;

import com.cheny.springboot.domain.Tran;
import com.cheny.springboot.domain.TranHistory;

import java.util.List;
import java.util.Map;

public interface TranService {
    List<String> getCustomerName(String name);

    boolean save(Tran tran, String customername);

    List<Tran> selectById();

    Tran selectId(String id);

    List<TranHistory> selectHistory(String tranId);

    Map<String, Object> getCharts();
}
