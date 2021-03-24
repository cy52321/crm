package com.cheny.springboot.service.impl;

import com.cheny.springboot.dao.*;
import com.cheny.springboot.domain.*;
import com.cheny.springboot.service.ClueService;
import com.cheny.springboot.utils.DateTimeUtil;
import com.cheny.springboot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Resource
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Resource
    private ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private TranMapper tranMapper;
    @Autowired
    private TranHistoryMapper tranHistoryMapper;


    @Override
    public boolean save(Clue clue) {
        boolean re=false;
        int a=clueMapper.insert(clue);
        if(a==1){
            re=true;
        }
        return re;
    }

    @Override
    public List<Clue> query() {
        List<Clue> list=clueMapper.query();
        return list;
    }

    @Override
    public Clue detail(String id) {
        Clue clue=clueMapper.detail(id);
        return clue;
    }

    @Override
    public List<Activity> getActivityListByClueId(String id) {
        List<Activity> list=clueMapper.getActivityListByClueId(id);
        return list;
    }

    @Override
    public boolean delete(String id) {
        boolean res=false;
        int a=clueActivityRelationMapper.deleteByPrimaryKey(id);
        if(a==1){
            res=true;
        }
        return res;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(String aname, String clueId) {
        List<Activity> list=clueMapper.getActivityListByNameAndNotByClueId(aname,clueId);
        return list;
    }

    @Override
    public boolean bund(String cid, String[] aids) {
        boolean res=true;
        for(int i=0;i<aids.length;i++){
            ClueActivityRelation clueActivityRelation=new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtil.getUUID());
            clueActivityRelation.setClueid(cid);
            clueActivityRelation.setActivityid(aids[i]);
            int b=clueActivityRelationMapper.insert(clueActivityRelation);
            if(b!=1){
                res=false;
            }
        }
        return res;
    }

    @Override
    public List<Activity> getActivityListByName(String aname) {
        List<Activity> list=clueMapper.getActivityListByName(aname);
        return list;
    }

    @Override
    public boolean convert(String clueId, Tran t, String createBy) {
        boolean res=true;
        String createTime=DateTimeUtil.getSysTime();
        Clue clue=clueMapper.selectByPrimaryKey(clueId);
        Customer customer=clueMapper.selectCustomerByName(clue.getCompany());
        if(customer==null){
            customer=new Customer();
            customer.setWebsite(clue.getWebsite());
            customer.setPhone(clue.getPhone());
            customer.setOwner(clue.getOwner());
            customer.setNextcontacttime(clue.getNextcontacttime());
            customer.setName(clue.getCompany());
            customer.setId(UUIDUtil.getUUID());
            customer.setDescription(clue.getDescription());
            customer.setCreatetime(createTime);
            customer.setCreateby(createBy);
            customer.setContactsummary(clue.getContactsummary());
            customer.setAddress(clue.getAddress());
            int count1=customerMapper.insert(customer);
            if(count1!=1){
                res=false;
            }
        }
        Contacts contacts=new Contacts();
        contacts.setId(UUIDUtil.getUUID());
        contacts.setSource(clue.getSource());
        contacts.setOwner(clue.getOwner());
        contacts.setNextcontacttime(clue.getNextcontacttime());
        contacts.setMyphone(clue.getMyphone());
        contacts.setJob(clue.getJob());
        contacts.setFullname(clue.getFullname());
        contacts.setEmail(clue.getEmail());
        contacts.setDescription(clue.getDescription());
        contacts.setCustomerid(customer.getId());
        contacts.setCreatetime(createTime);
        contacts.setCreateby(createBy);
        contacts.setContactsummary(clue.getContactsummary());
        contacts.setAppellation(clue.getAppellation());
        contacts.setAddress(clue.getAddress());
        int count2=contactsMapper.insert(contacts);
        if(count2!=1){
            res=false;
        }
        List<ClueRemark> clueRemarks=clueRemarkMapper.selectByClueId(clueId);
        for(ClueRemark clueRemark:clueRemarks){
            String s=clueRemark.getNotecontent();
            CustomerRemark customerRemark=new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateby(createBy);
            customerRemark.setCreatetime(createTime);
            customerRemark.setEditflag("0");
            customerRemark.setNotecontent(s);
            customerRemark.setCustomerid(customer.getId());
            int count3=customerRemarkMapper.insert(customerRemark);
            if(count3!=1){
                res=false;
            }

            ContactsRemark contactsRemark=new ContactsRemark();
            contactsRemark.setNotecontent(s);
            contactsRemark.setEditflag("0");
            contactsRemark.setCreatetime(createTime);
            contactsRemark.setCreateby(createBy);
            contactsRemark.setContactsid(contacts.getId());
            contactsRemark.setId(UUIDUtil.getUUID());
            int count4=contactsRemarkMapper.insert(contactsRemark);
            if(count4!=1){
                res=false;
            }
        }
        List<ClueActivityRelation> clueActivityRelations=clueActivityRelationMapper.getListByClueId(clueId);
        for(ClueActivityRelation clueActivityRelation:clueActivityRelations){
            String activityId=clueActivityRelation.getActivityid();
            ContactsActivityRelation contactsActivityRelation=new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityid(activityId);
            contactsActivityRelation.setContactsid(contacts.getId());
            int count5=contactsActivityRelationMapper.insert(contactsActivityRelation);
            if(count5!=1){
                res=false;
            }
        }
        if(t!=null){
            t.setSource(clue.getSource());
            t.setOwner(clue.getOwner());
            t.setNextcontacttime(clue.getNextcontacttime());
            t.setDescription(clue.getDescription());
            t.setContactsid(contacts.getId());
            t.setCustomerid(customer.getId());
            t.setContactsummary(clue.getContactsummary());
            int count6=tranMapper.insert(t);
            if(count6!=1){
                res=false;
            }
        }

        TranHistory th=new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setCreateby(createBy);
        th.setCreatetime(createTime);
        th.setExpecteddate(t.getExpecteddate());
        th.setMoney(t.getMoney());
        th.setStage(t.getStage());
        th.setTranid(t.getId());
        int count7=tranHistoryMapper.insert(th);
        if(count7!=1){
            res=false;
        }
        for(ClueRemark clueRemark:clueRemarks){
            int count8=clueRemarkMapper.deleteByPrimaryKey(clueRemark.getId());
            if(count8!=1){
                res=false;
            }
        }
        for(ClueActivityRelation clueActivityRelation:clueActivityRelations){
            int count9=clueActivityRelationMapper.deleteByPrimaryKey(clueActivityRelation.getId());
            if(count9!=1){
                res=false;
            }
        }
        int count10=clueMapper.deleteByPrimaryKey(clueId);
        if(count10!=1){
            res=false;
        }

        return res;
    }
}
