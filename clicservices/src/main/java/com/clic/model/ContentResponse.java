package com.clic.model;

import java.util.ArrayList;

public class ContentResponse {

	private String customerID;
	private String responseCode;
	private ArrayList<Content> contentlist ;
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public ArrayList<Content> getContentlist() {
		return contentlist;
	}
	public void setContentlist(ArrayList<Content> contentlist) {
		this.contentlist = contentlist;
	}
	
	
	
	

}
