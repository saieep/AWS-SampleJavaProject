package com.cybage.requestdto;

import java.util.List;

public class GroupDetailDTO {
	private int id;

	private String groupname;

	private byte isactive;

	private String password;
	
	private int sessionid;
	
	private List<Integer> userid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public byte getIsactive() {
		return isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSessionid() {
		return sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

	public List<Integer> getUserid() {
		return userid;
	}

	public void setUserid(List<Integer> userid) {
		this.userid = userid;
	}

	
	
}
