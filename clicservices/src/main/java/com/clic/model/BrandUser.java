package com.clic.model;

public class BrandUser {

	private String username;
	private String password;
	private String usertype;
	private String retailerstore;
	private String brandId;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRetailerstore() {
		return retailerstore;
	}

	public void setRetailerstore(String retailerstore) {
		this.retailerstore = retailerstore;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
