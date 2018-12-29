package com.cybage.dao;

import java.util.List;

import com.cybage.command.User;


public interface UserDAO {
  public List<User> showUser();
  public void insertUser(User user);
  public boolean isUserExist(User user);
  public User findUserById(int id);
  public void updateRoleAndStatus(User user);
  public List<User> showUserByid(int id);
}
