package com.cybage.command;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the scenario database table.
 * 
 */

@Entity
@NamedQuery(name="Scenario.findAll", query="SELECT s FROM Scenario s")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Scenario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int scenarioid;

	private String content;

	private byte isactive;
	
	
	private int duration;

	//bi-directional many-to-one association to Audit
	@OneToMany(mappedBy="scenario")
	@JsonBackReference
	private List<Audit> audits;

	//bi-directional many-to-one association to Response
	
	//bi-directional many-to-one association to Session
	@ManyToOne
	@JoinColumn(name="sessionid")
	@JsonManagedReference
	private Session session;

	public Scenario() {
	}

	public int getScenarioid() {
		return this.scenarioid;
	}

	public void setScenarioid(int scenarioid) {
		this.scenarioid = scenarioid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public byte getIsactive() {
		return this.isactive;
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

	public List<Audit> getAudits() {
		return this.audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}

	public Audit addAudit(Audit audit) {
		getAudits().add(audit);
		audit.setScenario(this);

		return audit;
	}

	public Audit removeAudit(Audit audit) {
		getAudits().remove(audit);
		audit.setScenario(null);

		return audit;
	}
	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "Scenario [scenarioid=" + scenarioid + ", content=" + content
				+ ", isactive=" + isactive + ", duration=" + duration
				+ ", audits=" + audits + ", session=" + session + "]";
	}

}