package com.cybage.responsedto;

import java.io.Serializable;

public class ScenarioResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101481968637122939L;

	private String scenarioid;

	private String content;

	private String duration;

	private String isactive;

	
	
	private String sessionid;

	public String getScenarioid() {
		return scenarioid;
	}

	public void setScenarioid(String scenarioid) {
		this.scenarioid = scenarioid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	
	
	
}
