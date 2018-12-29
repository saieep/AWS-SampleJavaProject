package com.cybage.requestdto;

import java.io.Serializable;


public class ActionDTO implements  Serializable{
  
	private int actionid;
	
	private String description;
	
	
	public int getActionid() {
		return actionid;
	}
	
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
