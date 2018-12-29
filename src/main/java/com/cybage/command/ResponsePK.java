package com.cybage.command;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the response database table.
 * 
 */

@Embeddable
public class ResponsePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int groupid;

	@Column(insertable=false, updatable=false)
	private int scenarioid;

	public ResponsePK() {
	}
	public int getGroupid() {
		return this.groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getSenarioid() {
		return this.scenarioid;
	}
	public void setSenarioid(int senarioid) {
		this.scenarioid = senarioid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ResponsePK)) {
			return false;
		}
		ResponsePK castOther = (ResponsePK)other;
		return 
			(this.groupid == castOther.groupid)
			&& (this.scenarioid == castOther.scenarioid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.groupid;
		hash = hash * prime + this.scenarioid;
		
		return hash;
	}
}