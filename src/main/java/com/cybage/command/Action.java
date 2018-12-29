package com.cybage.command;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the actions database table.
 * 
 */

@Entity
@Table(name="actions")
@NamedQuery(name="Action.findAll", query="SELECT a FROM Action a")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int actionid;

	private String description;

	//bi-directional many-to-one association to Audit
	@OneToMany(mappedBy="action")
	@JsonBackReference
	private List<Audit> audits;

	public Action() {
	}

	public int getActionid() {
		return this.actionid;
	}

	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Audit> getAudits() {
		return this.audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}

	public Audit addAudit(Audit audit) {
		getAudits().add(audit);
		audit.setAction(this);

		return audit;
	}

	public Audit removeAudit(Audit audit) {
		getAudits().remove(audit);
		audit.setAction(null);

		return audit;
	}

}