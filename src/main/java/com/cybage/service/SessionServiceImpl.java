package com.cybage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybage.command.Session;
import com.cybage.command.User;
import com.cybage.dao.SessionDAO;
import com.cybage.requestdto.SessionDTO;

@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	SessionDAO sessionDao;
	
	
	
	public List<Session> showSession() {
		List<Session> list=sessionDao.showSession();
		return list;
	}
	public void insertSession(SessionDTO sessionDto){
		Session session = new Session();
		User user = new User();
		
		user.setId(sessionDto.getUserid());
		
		session.setUser(user);
		session.setStarttime(sessionDto.getStarttime());
		session.setEndtime(sessionDto.getEndtime());
		session.setSessiondate(sessionDto.getSessiondate());
		session.setIsactive(sessionDto.getIsactive());
		
		 sessionDao.insertSession(session);
	}
	
	
	public Session findsessionById(int id) {
		return sessionDao.findSessionbyId(id);
	}
	
	
	public void updateStatus(Session session) {
		sessionDao.updateStatus(session);
	}
	
	public List<Session> findSessionbyuserid(int id) {
		return sessionDao.findSessionbyuserid(id);
	}

}
