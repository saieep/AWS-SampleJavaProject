package com.cybage.model;

public class Error {
	private int statusValue;
	private String message;

	public int getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(int statusValue) {
		this.statusValue = statusValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Error(int statusValue, String message) {
		super();
		this.statusValue = statusValue;
		this.message = message;
	}
}
