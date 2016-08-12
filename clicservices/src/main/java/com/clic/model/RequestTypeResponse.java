package com.clic.model;

import java.util.ArrayList;

public class RequestTypeResponse {

	private ArrayList<RequestType> requestType;
	private String serviceType;
	private String status;
	private String responseCode;
	private String customerID;

	

	public ArrayList<RequestType> getRequestType() {
		return requestType;
	}

	public void setRequestType(ArrayList<RequestType> requestType) {
		this.requestType = requestType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	

}
