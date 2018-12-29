package com.cybage.dao;

import java.util.List;

import com.cybage.command.Groupdetail;

public interface GroupDetailDAO {
public List<Groupdetail> showGroupDetail();
public void insertGroupDetail(Groupdetail groupdetail);
public Groupdetail findGroupById(int id);
public List<Groupdetail> showGroupById(int id);
}
