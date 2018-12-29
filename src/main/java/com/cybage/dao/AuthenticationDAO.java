package com.cybage.dao;

import java.util.List;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;

public interface AuthenticationDAO {
public List checkGroupid(Groupdetail groupdetail);
public List checkUserId(User user);
}
