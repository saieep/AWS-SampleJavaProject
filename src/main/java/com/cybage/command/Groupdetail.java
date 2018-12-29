package com.cybage.command;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.stereotype.Component;

/**
 * The persistent class for the groupdetails database table.
 * 
 */
//TODO Remove

@Entity
@Table(name = "groupdetails")
@NamedQuery(name = "Groupdetail.findAll", query = "SELECT g FROM Groupdetail g")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Groupdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String groupname;

	private byte isactive;

	@Column(columnDefinition = "BLOB", name = "password")
	@ColumnTransformer(read = "DES_DECRYPT(password, 'password')", write = "DES_ENCRYPT(?, 'password')")
	private String password;

	// bi-directional many-to-one association to Audit
	@OneToMany(mappedBy = "groupdetail")
	@JsonBackReference
	private List<Audit> audits;

	// bi-directional many-to-one association to Session
	@ManyToOne
	@JoinColumn(name = "sessionid")
	@JsonManagedReference
	private Session session;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "groupmember", joinColumns = { @JoinColumn(name = "groupid") }, inverseJoinColumns = { @JoinColumn(name = "userid") })
	private List<User> users;

	// bi-directional many-to-one association to Response
	public Groupdetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Audit> getAudits() {
		return this.audits;
	}

	public void setAudits(List<Audit> audits) {
		this.audits = audits;
	}

	public Audit addAudit(Audit audit) {
		getAudits().add(audit);
		audit.setGroupdetail(this);

		return audit;
	}

	public Audit removeAudit(Audit audit) {
		getAudits().remove(audit);
		audit.setGroupdetail(null);

		return audit;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}