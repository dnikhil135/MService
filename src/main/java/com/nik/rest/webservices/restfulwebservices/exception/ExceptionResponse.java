package com.nik.rest.webservices.restfulwebservices.exception;

import java.util.Date;

public class ExceptionResponse {

	private Date timestap;
	private String message;
	private String detail;
	
	public ExceptionResponse(Date timestap, String message, String detail) {
		super();
		this.timestap = timestap;
		this.message = message;
		this.detail = detail;
	}
	public Date getTimestap() {
		return timestap;
	}
	public String getMessage() {
		return message;
	}
	public String getDetail() {
		return detail;
	}
	
	
}
