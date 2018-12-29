package com.cybage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Groupdetail;
import com.cybage.command.User;
import com.cybage.dao.GroupDetailDAO;
import com.cybage.dao.SessionDAO;
import com.cybage.dao.UserDAO;
import com.cybage.requestdto.GroupDetailDTO;

//To automatically create bean of service IMPL at the time of component base-scan

@Service

public class GroupDetailServiceImpl implements GroupDetailService {

	@Autowired
	GroupDetailDAO groupDetailDao;

	@Autowired
	UserDAO userDAO;

	@Autowired
	SessionDAO sessionDAO;



	public List<Groupdetail> showGroupDetail() {        //for fetching values from groupdetail table
		List<Groupdetail> list = groupDetailDao.showGroupDetail();   //calling showGroupDetail() method of groupdetaildao
		return list;
	}

	public void insertGroupDetail(GroupDetailDTO groupDto) {    //for inserting data into groupdetail
		Groupdetail groupdetail = new Groupdetail();
		groupdetail.setGroupname(groupDto.getGroupname());  //setting groupname by taking values from groupDto
		groupdetail.setPassword(groupDto.getPassword());    //setting password by taking values from groupDto
		groupdetail.setIsactive(groupDto.getIsactive());   //setting isactive by taking values from groupDto
		List<User> groupMembers = new ArrayList<User>();         
		for (int id : groupDto.getUserid()) {                //to inseert data into groupmember table
			groupMembers.add(userDAO.findUserById(id));
		}
		groupdetail.setUsers(groupMembers);                       
		groupdetail.setSession(sessionDAO.findSessionbyId(groupDto             
				.getSessionid()));

		groupDetailDao.insertGroupDetail(groupdetail);   //calling insertGroupDetail of groupDetailDao

	}

	public Groupdetail findGroupById(int id) {
		return groupDetailDao.findGroupById(id);
	}

	public List<Groupdetail> showGroupById(int id) {
		return groupDetailDao.showGroupById(id);
	}
}
