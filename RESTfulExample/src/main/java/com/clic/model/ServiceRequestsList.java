package com.clic.model;

public class ServiceRequestsList {

	private String typeOfRequest;
	private String description;
	private String createdDate;
	private String customerID;
	private String itemName;
	private String lastComment;
	private String customerItemID;
	private String repaiTypeId;
	private String status;
	private String serviceRequestId;

	private String editRequest;
	
	
	
	
	public String getEditRequest() {
		return editRequest;
	}

	public void setEditRequest(String editRequest) {
		this.editRequest = editRequest;
	}

	public String getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(String serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLastComment() {
		return lastComment;
	}

	public void setLastComment(String lastComment) {
		this.lastComment = lastComment;
	}

	public String getCustomerItemID() {
		return customerItemID;
	}

	public void setCustomerItemID(String customerItemID) {
		this.customerItemID = customerItemID;
	}

	public String getRepaiTypeId() {
		return repaiTypeId;
	}

	public void setRepaiTypeId(String repaiTypeId) {
		this.repaiTypeId = repaiTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getTypeOfRequest() {
		return typeOfRequest;
	}

	public void setTypeOfRequest(String typeOfRequest) {
		this.typeOfRequest = typeOfRequest;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
