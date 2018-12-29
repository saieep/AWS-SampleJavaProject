package com.cybage.requestdto;

import java.io.Serializable;

public class ResponsePKDTO implements Serializable {

	private int groupid;
	
	private int scenarioid;

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getScenarioid() {
		return scenarioid;
	}

	public void setScenarioid(int scenarioid) {
		this.scenarioid = scenarioid;
	}
	
	
}
