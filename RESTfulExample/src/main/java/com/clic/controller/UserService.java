package com.clic.controller;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.clic.dao.ProductsDao;
import com.clic.dao.UserDao;
import com.clic.model.Category;
import com.clic.model.ContentRequest;
import com.clic.model.Customer;
import com.clic.model.Item;
import com.clic.model.OtpValidation;
import com.clic.model.ServiceRequest;
import com.clic.model.SubCategory;
import com.clic.model.Ticket;
import com.clic.model.UserItem;
import com.clic.model.shareProfile;
 
@Path("/customer")
public class UserService {
 
	/*
	 * Method for user mobile number duplication
	 */
	
	@GET
	@Path("checkuserduplicate/{param}")
	public Object ValidateUser(@PathParam("param") String mobilenumber)
	{
		UserDao obj = new UserDao();
		return obj.validatenumber(mobilenumber);
	}

	@POST
	@Path("createcustomer")
	@Consumes("application/json")
	@Produces("application/json")
	public Object createuser(Customer user)
	{
		UserDao obj=new UserDao();
		
		return obj.createuser(user);
	}
	
	
	@POST
	@Path("otpvalidation")
	@Consumes("application/json")
	@Produces("application/json")
	public Object ValidateOtp(OtpValidation otpValidation)
	{
		UserDao obj=new UserDao();
		
		return obj.validateOtp(otpValidation);
	}

	@POST
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public Object login(Customer userbean)
	{
		UserDao obj=new UserDao();
		return obj.login(userbean);
	}

	/*
	 * Method for get brands list
	 */
	
	@GET
	@Path("brandlist")
	public Object brandslist()
	{
		ProductsDao obj = new ProductsDao();
		return obj.brandslist();
	}
	

	@GET
	@Path("itmesexist/{param}")
	public Object itmesexist(@PathParam("param") String customerID)
	{
		ProductsDao obj = new ProductsDao();
		return obj.itemsexist(customerID);
	}
	
	
	@GET
	@Path("productslist/{param}")
	public Object productslist(@PathParam("param") String brandid)
	{
		ProductsDao obj = new ProductsDao();
		return obj.productslist(brandid);
	}
	
	@POST
	@Consumes("application/json")
	@Path("categoryslist")
	public Object categoryslist(Category categorys)
	{
		ProductsDao obj = new ProductsDao();
		return obj.categoryslist(categorys);
	}
	
	@POST
	@Consumes("application/json")
	@Path("subcategoryslist")
	public Object subcategoryslist(SubCategory subcategorys)
	{
		ProductsDao obj = new ProductsDao();
		return obj.subcategoryslist(subcategorys);
	}
	@POST
	@Consumes("application/json")
	@Path("itemslist")
	public Object itemslist(Item items)
	{
		ProductsDao obj = new ProductsDao();
		return obj.itemslist(items);
	}
	
	
	@POST
	@Consumes("application/json")
	@Path("addcustomeritem")
	public Object adduseritem(UserItem items)
	{
		UserDao obj = new UserDao();
		return obj.addUserItemData(items);
	}
	
	@GET
	@Path("customeritemslist/{param}")
	public Object useritemslist(@PathParam("param") String customerId)
	{
		UserDao obj = new UserDao();
		return obj.itemslist(customerId);
	}
	
	

	@GET
	@Path("customeritemsdoclist/{param}")
	public Object useritemsdoclist(@PathParam("param") String consumerId)
	{
		UserDao obj = new UserDao();
		return obj.itemsdoclist(consumerId);
	}
	
	
	
	@POST
	@Consumes("application/json")
	@Path("raiseaticket")
	public Object useriteminfo(Ticket ticketBean)
	{
		UserDao obj = new UserDao();
		return obj.addUserTicket(ticketBean);
	}
	
	@GET
	@Path("customertickets/{param}")
	public Object usertickets(@PathParam("param") String customerID)
	{
		UserDao obj = new UserDao();
		return obj.userticketsList(customerID);
	}
	@POST
	@Consumes("application/json")
	@Path("updateticket")
	public Object updateTicket(Ticket ticketBean)
	{
		UserDao obj = new UserDao();
		return obj.updateTicket(ticketBean);
	}
	
	@GET
	@Path("typeoservices/")
	public Object typeoservices()
	{
		UserDao obj = new UserDao();
		return obj.typeofservices();
	}
	@GET
	@Path("typeodocs/")
	public Object typeofdocs()
	{
		UserDao obj = new UserDao();
		return obj.typeofdocs();
	}
	
	
	@GET
	@Path("typeofrepairs/{param}")
	public Object typeofrepairs(@PathParam("param") String customerItemId)
	{
		UserDao obj = new UserDao();
		return obj.typeofrepairs(customerItemId);
	}
	
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("servicerequest")
	public Object servicerequest(ServiceRequest servrequst)
	{
		UserDao obj = new UserDao();
//		System.out.println("hee");
		return obj.addserviceRequest(servrequst);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("content")
	public Object contentdata(ContentRequest contentrequest)
	{
		UserDao obj = new UserDao();
		return obj.getContentData(contentrequest);
	}
	@GET
	@Path("customerservicelist/{param}")
	public Object customerservicelist(@PathParam("param") String customerID)
	{
		UserDao obj = new UserDao();
		return obj.userticketsList(customerID);
	}
	@GET
	@Path("callback/{param}")
	public Object callback(@PathParam("param") String customerID)
	{
		UserDao obj = new UserDao();
		return obj.insertcallback(customerID);
	}
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("shareprofile")
	public Object shareprofile(shareProfile shareProfile)
	{
		UserDao obj = new UserDao();
		return obj.shareprofile(shareProfile);
	}
	@GET
	@Path("sharedprofiles/{param}")
	public Object sharedprofiles(@PathParam("param") String customerID)
	{
		UserDao obj = new UserDao();
		return obj.sharedprofiles(customerID);
	}
}