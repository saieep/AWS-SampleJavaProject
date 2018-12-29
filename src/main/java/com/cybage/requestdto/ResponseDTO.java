package com.cybage.requestdto;

import java.io.Serializable;


public class ResponseDTO implements  Serializable {

	private int scenarioid;
	
	private int groupid;
	
	private String response;

	private int score;

	public int getScenarioid() {
		return scenarioid;
	}

	public void setScenarioid(int scenarioid) {
		this.scenarioid = scenarioid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
