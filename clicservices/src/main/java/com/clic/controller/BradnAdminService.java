package com.clic.controller;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.clic.dao.BrandAdminDao;
import com.clic.dao.UserDao;
import com.clic.model.BrandAdminLogin;
import com.clic.model.BrandUser;
import com.clic.model.ServiceRequestUpdate;
 
@Path("/brandadmin")
public class BradnAdminService {
 
	/*
	 * Method for validate user
	 */
	
	@POST
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public Object ValidateUser(BrandAdminLogin brandadminbean)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.login(brandadminbean);
	}
	@GET
	@Path("servicerequestlist/{param}")
	public Object serviceslist(@PathParam("param") String brandId)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.serviceslist(brandId,"s");
	}
	@GET
	@Path("demorequestlist/{param}")
	public Object demoserviceslist(@PathParam("param") String brandId)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.serviceslist(brandId,"d");
	}
	@GET
	@Path("usertypeslist/{param}")
	public Object usertypeslist(@PathParam("param") String brandId)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.usertypeslist(brandId);	
	}
	@GET
	@Path("retailerstoreslist/{param}")
	public Object retailerstoreslist(@PathParam("param") String brandId)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.retailerstoreslist(brandId);	
	}

	@POST
	@Path("addBrandUser")
	@Consumes("application/json")
	@Produces("application/json")
	public Object addBrandUser(BrandUser branduser)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.addBrandUser(branduser);
	}
	
	@POST
	@Path("updateRequest")
	@Consumes("application/json")
	@Produces("application/json")
	public Object updateRequest(ServiceRequestUpdate requestUpdate)
	{
		BrandAdminDao obj = new BrandAdminDao();
		return obj.updateRequest(requestUpdate);
	}
	
	
}