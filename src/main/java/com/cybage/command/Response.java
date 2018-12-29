package com.cybage.command;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The persistent class for the response database table.
 * 
 */

@Entity
@NamedQuery(name="Response.findAll", query="SELECT r FROM Response r")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResponsePK id;

	private String response;

	private int score;

	//bi-directional many-to-one association to Scenario
	

	public Response() {
	}

	public ResponsePK getId() {
		return this.id;
	}

	public void setId(ResponsePK id) {
		this.id = id;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}


}