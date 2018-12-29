package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.User;
import com.cybage.dao.UserDAO;
import com.cybage.requestdto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;

	public List<User> showUser() {
		List<User> list = userDao.showUser();
		return list;
	}

	public void insertUser(UserDTO userdto) {
		User user = new User();

		user.setDateofcreation(userdto.getDateofcreation());
		user.setIsactive(userdto.getIsactive());
		user.setRole(userdto.getRole());
		user.setUsername(userdto.getUsername());
		user.setPassword(userdto.getPassword());
		userDao.insertUser(user);
	}

	public boolean isUserExist(UserDTO userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		if (userDao.isUserExist(user))
			return true;
		return false;
	}

	public void updateRoleAndStatus(User user) {
		userDao.updateRoleAndStatus(user);
	}

	public User findUserById(int id) {
		return userDao.findUserById(id);
	}
	public List<User> showUserByid(int id){
		return userDao.showUserByid(id);
	}
}
