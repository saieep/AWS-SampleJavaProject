package com.cybage.responsedto;

import java.io.Serializable;

public class AuditResponseDTO implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 5323942946096197247L;

	private String auditid;
  
  private String groupid;
  
  private String scenarioid;
  
  private String description;
  
  

public String getAuditid() {
	return auditid;
}

public void setAuditid(String auditid) {
	this.auditid = auditid;
}

public String getGroupid() {
	return groupid;
}

public void setGroupid(String groupid) {
	this.groupid = groupid;
}

public String getScenarioid() {
	return scenarioid;
}

public void setScenarioid(String scenarioid) {
	this.scenarioid = scenarioid;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}



  
  
  
}
