package com.nik.rest.webservices.restfulwebservices.model;

public class DisplayFirstNLastName {

	private String fName;
	private String lName;
	
	public DisplayFirstNLastName() {
		super();
	}
	
	public DisplayFirstNLastName(String fName, String lName) {
		super();
		this.fName = fName;
		this.lName = lName;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	
	
}
