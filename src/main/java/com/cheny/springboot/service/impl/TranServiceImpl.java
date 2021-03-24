package com.cheny.springboot.service.impl;

import com.cheny.springboot.dao.CustomerMapper;
import com.cheny.springboot.dao.TranHistoryMapper;
import com.cheny.springboot.dao.TranMapper;
import com.cheny.springboot.domain.Customer;
import com.cheny.springboot.domain.Tran;
import com.cheny.springboot.domain.TranHistory;
import com.cheny.springboot.service.TranService;
import com.cheny.springboot.utils.DateTimeUtil;
import com.cheny.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TranServiceImpl implements TranService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public List<String> getCustomerName(String name) {
        List<String> list=customerMapper.getCustomerName(name);
        return list;
    }

    @Override
    public boolean save(Tran tran, String customername) {
        boolean res=true;
        Customer customer=customerMapper.getCustomerByName(customername);
        if(customer==null){
            customer=new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(customername);
            customer.setCreatetime(DateTimeUtil.getSysTime());
            customer.setCreateby(tran.getCreateby());
            customer.setContactsummary(tran.getContactsummary());
            customer.setNextcontacttime(tran.getNextcontacttime());
            customer.setOwner(tran.getOwner());
            int count1=customerMapper.insert(customer);
            if(count1!=1){
                res=false;
            }
        }
        tran.setCustomerid(customer.getId());
        int count2=tranMapper.insert(tran);
        if(count2!=1){
            res=false;
        }
        TranHistory th=new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setTranid(tran.getId());
        th.setStage(tran.getStage());
        th.setMoney(tran.getMoney());
        th.setExpecteddate(tran.getExpecteddate());
        th.setCreatetime(DateTimeUtil.getSysTime());
        th.setCreateby(tran.getCreateby());
        int count3=tranHistoryMapper.insert(th);
        if(count3!=1){
            res=false;
        }

        return res;
    }

    @Override
    public List<Tran> selectById() {
        List<Tran> list=tranMapper.selectById();
        return list;
    }

    @Override
    public Tran selectId(String id) {
        Tran tran=tranMapper.selectId(id);
        return tran;
    }

    @Override
    public List<TranHistory> selectHistory(String tranId) {
        List<TranHistory> list=tranHistoryMapper.selectByTranId(tranId);
        return list;
    }

    @Override
    public Map<String, Object> getCharts() {
        List<Map<String,Object>> list=tranMapper.getCharts();
        int total=tranMapper.getTotal();
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("dataList",list);
        return map;
    }
}
