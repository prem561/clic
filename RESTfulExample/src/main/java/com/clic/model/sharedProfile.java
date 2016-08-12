package com.clic.model;

import java.util.ArrayList;

public class sharedProfile {

	private String customerId;
	private String responsecode;
	
	private ArrayList<sharedProfilesList> sahredProfilesList = new ArrayList<sharedProfilesList>();

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public ArrayList<sharedProfilesList> getSahredProfilesList() {
		return sahredProfilesList;
	}

	public void setSahredProfilesList(ArrayList<sharedProfilesList> sahredProfilesList) {
		this.sahredProfilesList = sahredProfilesList;
	}
	
	
	

}
