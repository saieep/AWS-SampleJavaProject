package com.cybage.command;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the audit database table.
 * 
 */

@Entity
@NamedQuery(name="Audit.findAll", query="SELECT a FROM Audit a")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int auditid;

	//bi-directional many-to-one association to Groupdetail
	@ManyToOne
	@JoinColumn(name="groupid")
	@JsonManagedReference
	private Groupdetail groupdetail;

	//bi-directional many-to-one association to Scenario
	@ManyToOne
	@JoinColumn(name="scenarioid")
	@JsonManagedReference
	private Scenario scenario;

	//bi-directional many-to-one association to Action
	@ManyToOne
	@JoinColumn(name="actionid")
	
	private Action action;

	public Audit() {
	}

	public int getAuditid() {
		return this.auditid;
	}

	public void setAuditid(int auditid) {
		this.auditid = auditid;
	}

	public Groupdetail getGroupdetail() {
		return this.groupdetail;
	}

	public void setGroupdetail(Groupdetail groupdetail) {
		this.groupdetail = groupdetail;
	}

	public Scenario getScenario() {
		return this.scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}