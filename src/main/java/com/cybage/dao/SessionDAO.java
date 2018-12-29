package com.cybage.dao;

import java.util.List;

import com.cybage.command.Session;

public interface SessionDAO {
	public List<Session> showSession();

	public void insertSession(Session session);

	public Session findSessionbyId(int id);
	
	public void updateStatus(Session session);
	
	public List<Session> findSessionbyuserid(int id);
}
