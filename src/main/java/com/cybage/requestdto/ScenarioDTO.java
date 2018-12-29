package com.cybage.requestdto;

import java.sql.Date;




public class ScenarioDTO {

	private int scenarioid;

	private String content;

	private int duration;

	private byte isactive;



	private int sessionid;

	public int getScenarioid() {
		return scenarioid;
	}

	public void setScenarioid(int scenarioid) {
		this.scenarioid = scenarioid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public byte getIsactive() {
		return isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSessionid() {
		return sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}
	
}
