package com.cybage.service;

import java.util.List;

import com.cybage.command.Groupdetail;
import com.cybage.requestdto.GroupDetailDTO;

public interface GroupDetailService {
public List<Groupdetail> showGroupDetail();
public void insertGroupDetail(GroupDetailDTO groupDetailDto);
public Groupdetail findGroupById(int id);
public List<Groupdetail> showGroupById(int id);
}
