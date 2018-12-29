package com.cybage.responsedto;

public class UserResponseDTO {
	private int id;

	private String dateofcreation;

	private String isactive;

	private String role;

	private String username;

	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(String dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
