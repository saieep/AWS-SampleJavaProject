package com.cybage.exception;

public class SessionIdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3596881276029938318L;

	public   SessionIdNotFoundException(String message){
		System.out.println("Session id not found");
	}
}
