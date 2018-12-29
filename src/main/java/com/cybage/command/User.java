package com.cybage.command;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.stereotype.Component;

/**
 * The persistent class for the user database table.
 * 
 */

@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date dateofcreation;

	private byte isactive;

	private String role;

	private String username;
	
	@Column(columnDefinition= "BLOB", name="password") 
	@ColumnTransformer(
	  read="DES_DECRYPT(password, 'password')", 
	  write="DES_ENCRYPT(?, 'password')")
	private String password;

	//bi-directional many-to-many association to Groupdetail
	@ManyToMany(mappedBy="users")
	@JsonBackReference
	private List<Groupdetail> groupdetails;

	//bi-directional many-to-one association to Session
	@OneToMany(mappedBy="user")
	@JsonBackReference
	private List<Session> sessions;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateofcreation() {
		return this.dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public byte getIsactive() {
		return this.isactive;
	}

	public void setIsactive(byte isactive) {
		this.isactive = isactive;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Groupdetail> getGroupdetails() {
		return this.groupdetails;
	}

	public void setGroupdetails(List<Groupdetail> groupdetails) {
		this.groupdetails = groupdetails;
	}

	public List<Session> getSessions() {
		return this.sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Session addSession(Session session) {
		getSessions().add(session);
		session.setUser(this);

		return session;
	}

	public Session removeSession(Session session) {
		getSessions().remove(session);
		session.setUser(null);

		return session;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", dateofcreation=" + dateofcreation
				+ ", isactive=" + isactive + ", role=" + role + ", username="
				+ username + ", password=" + password + "]";
	}

}