package com.cybage.command;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the session database table.
 * 
 */

@Entity
@NamedQuery(name="Session.findAll", query="SELECT s FROM Session s")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sessionid;

	private Time endtime;

	private byte isactive;

	@Temporal(TemporalType.DATE)
	private Date sessiondate;

	private Time starttime;

	//bi-directional many-to-one association to Groupdetail
	@OneToMany(mappedBy="session")
	@JsonBackReference
	private List<Groupdetail> groupdetails;

	//bi-directional many-to-one association to Scenario
	@OneToMany(mappedBy="session")
	@JsonBackReference
	private List<Scenario> scenarios;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonManagedReference
	private User user;

	public Session() {
	}

	public int getSessionid() {
		return this.sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

	public Time getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Time endtime) {
		this.endtime = endtime;
	}

	public byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public Date getSessiondate() {
		return this.sessiondate;
	}

	public void setSessiondate(Date sessiondate) {
		this.sessiondate = sessiondate;
	}

	public Time getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}

	public List<Groupdetail> getGroupdetails() {
		return this.groupdetails;
	}

	public void setGroupdetails(List<Groupdetail> groupdetails) {
		this.groupdetails = groupdetails;
	}

	public Groupdetail addGroupdetail(Groupdetail groupdetail) {
		getGroupdetails().add(groupdetail);
		groupdetail.setSession(this);

		return groupdetail;
	}

	public Groupdetail removeGroupdetail(Groupdetail groupdetail) {
		getGroupdetails().remove(groupdetail);
		groupdetail.setSession(null);

		return groupdetail;
	}

	public List<Scenario> getScenarios() {
		return this.scenarios;
	}

	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	public Scenario addScenario(Scenario scenario) {
		getScenarios().add(scenario);
		scenario.setSession(this);

		return scenario;
	}

	public Scenario removeScenario(Scenario scenario) {
		getScenarios().remove(scenario);
		scenario.setSession(null);

		return scenario;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}