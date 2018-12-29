package com.cybage.service;

import java.util.List;

import com.cybage.command.Session;
import com.cybage.requestdto.SessionDTO;

public interface SessionService {
	public List<Session> showSession();

	public void insertSession(SessionDTO sessionDto);

	public Session findsessionById(int id);
	
	public void updateStatus(Session session); 
	
	public List<Session> findSessionbyuserid(int id);
}
