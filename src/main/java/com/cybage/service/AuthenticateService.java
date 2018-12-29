package com.cybage.service;

import java.util.List;

import com.cybage.requestdto.GroupDetailDTO;

public interface AuthenticateService {
public List checkGroupId(GroupDetailDTO groupDetailDTO);
public List checkUserId(GroupDetailDTO groupDetailDTO);
}
