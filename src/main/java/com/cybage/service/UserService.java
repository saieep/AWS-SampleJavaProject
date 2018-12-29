package com.cybage.service;

import java.util.List;

import com.cybage.command.User;
import com.cybage.requestdto.UserDTO;


public interface UserService {
	public List<User> showUser();
	public void insertUser(UserDTO userdto);
	public boolean isUserExist(UserDTO userDto);
	public void updateRoleAndStatus(User user);
	public User findUserById(int id);
	public List<User> showUserByid(int id);
}
