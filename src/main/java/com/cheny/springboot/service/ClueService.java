package com.cheny.springboot.service;

import com.cheny.springboot.domain.Activity;
import com.cheny.springboot.domain.Clue;
import com.cheny.springboot.domain.Tran;

import java.util.List;

public interface ClueService {
    boolean save(Clue clue);

    List<Clue> query();

    Clue detail(String id);

    List<Activity> getActivityListByClueId(String id);

    boolean delete(String id);

    List<Activity> getActivityListByNameAndNotByClueId(String aname, String clueId);

    boolean bund(String cid, String[] aids);

    List<Activity> getActivityListByName(String aname);

    boolean convert(String clueId, Tran t, String createBy);
}
