package com.clic.dao;

import java.awt.image.BufferedImage;
import java.io.File;

/****************************************************************************
 *   Copyright (c)2016 CLIC. All rights reserved.
 *
 *   THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF CLIC.
 *
 *   The copyright notice above does not evidence any actual or intended
 *   publication of such source code.
 *****************************************************************************/


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.codehaus.jackson.io.SegmentedStringWriter;

import com.clic.Dbconnection.ConnectionHelper;
import com.clic.Utility.ErrorMessage;
import com.clic.Utility.ImageUtility;
import com.clic.Utility.Utility;
import com.clic.model.Content;
import com.clic.model.ContentRequest;
import com.clic.model.ContentResponse;
import com.clic.model.Customer;
import com.clic.model.CustomerItemsRespose;
import com.clic.model.DocTypeResponse;
import com.clic.model.DocsType;
import com.clic.model.ItemDocs;
import com.clic.model.OTP;
import com.clic.model.OtpValidation;
import com.clic.model.RequestType;
import com.clic.model.RequestTypeResponse;
import com.clic.model.ServiceRequest;
import com.clic.model.ServiceRequestAsRespose;
import com.clic.model.ServiceType;
import com.clic.model.ServiceTypeResponse;
import com.clic.model.SuccessBean;
import com.clic.model.Ticket;
import com.clic.model.UserItem;
import com.clic.model.UserItemsResponse;
import com.clic.model.shareProfile;
import com.clic.model.sharedProfile;
import com.clic.model.sharedProfilesList;



/**
 * UserDao.java
 * Purpose: Userdao class is responsible for user creation , login , otp generation and check user duplication.
 * @author CLIC
 * @version 1.0
 */

public class UserDao {
	
    String errorcode = "";
                  
    /**
     * Method to validate user mobile number 
     */
    public Object validatenumber(String number) {
    	try
        {
        	boolean checkduplicate = false;
        	checkduplicate = validatemobilenumber(number);
        	if(checkduplicate == false) 
            {
        		return "{\"responsecode\":\"200\",\"servicetype\":\"Check Duplicates\",\"status\":\"no\"}";           	
            }else
            {
            	return new ErrorMessage().getErrorResponse("102");  
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private boolean validatemobilenumber(String number) {		
		Connection c = null;		
		PreparedStatement ps = null;
		boolean questioninsertion=false;
		ResultSet rs=null;
		String getqry="select customer_mob from customer_t where customer_mob = ?";
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, number);
	      	rs=ps.executeQuery();
	      	if(rs.next())
	      	{
	      		questioninsertion = true;
	      	}
		
		} catch (Exception e) {
			
		}
		finally
		{
 			ConnectionHelper.close(c);	
 			try {
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
			}
		}
		return questioninsertion;
	}
    
    // New user signup
    
    public Object createuser(Customer userbean)
    {
    	Object obj=insertuser(userbean);
    	
    	return obj;
    }
    //Create user method
    public Object insertuser(Customer userbean)
    {
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2= null;
		OTP bean=new  OTP();
		try {
				c = ConnectionHelper.getConnection();
				ps = c.prepareStatement("insert into customer_t(customer_name,customer_email,customer_mob,customer_password) values (?,?,?,?)");
				ps.setString(1, userbean.getCustomerName());
				ps.setString(2, userbean.getEmailID());
				ps.setString(3, userbean.getPhoneNumber());
				ps.setString(4, userbean.getPassword());
				int i =ps.executeUpdate();
				int otp=0;
				if(i>0)
				{
					otp=Utility.generatePIN();
					ps2=c.prepareStatement("select customer_id from customer_t where customer_mob = ?");
					ps2.setString(1, userbean.getPhoneNumber());
					rs2 = ps2.executeQuery();
					if(rs2.next())
					{
						ps1 = c.prepareStatement("insert into otp_t(customer_id,otp) values (?,?)");
						ps1.setString(1,  rs2.getString(1));
						ps1.setString(2, otp+"");
						int k=ps1.executeUpdate();
						if(k>0)
						{
							bean.setCustomerID(rs2.getString(1));
							bean.setOtpNum(otp+"");
							bean.setResponseCode(200);
							bean.setStatus("User created successfully , Login with this otp");
						}
						else
						{
							return new ErrorMessage().getErrorResponse("100");
						}
					}
				}
				else
				{
					return new ErrorMessage().getErrorResponse("101");
				}
				
					
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			finally
			{
					ConnectionHelper.close(c);	
					try {
					if(ps!=null)ps.close();
					if(ps1!=null)ps1.close();
					if(rs2!=null)rs2.close();
					if(ps2!=null)ps2.close();
				} catch (SQLException e) {
				}
			}
    	return bean;
    }
	// code for validate otp
	public Object validateOtp(OtpValidation otpValidation)
	{
		Object obj= validateotp(otpValidation);
		return obj;
	}
	public Object validateotp(OtpValidation otpValidation)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		OTP otpbean=new  OTP();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select *  from otp_t where customer_id = ? and otp=?");
		ps.setString(1, otpValidation.getCustomerID());
		ps.setString(2, otpValidation.getCustomerOTP());
		ResultSet rs =ps.executeQuery();
		if(rs.next())
		{
			ps1=c.prepareStatement("delete from otp_t where otp= ? and customer_id = ? ");
			ps1.setString(2, otpValidation.getCustomerID());
			ps1.setString(1, otpValidation.getCustomerOTP());
			ps1.executeUpdate();
			otpbean.setResponseCode(200);
			otpbean.setStatus("User validated successfully");
			otpbean.setCustomerID(otpValidation.getCustomerID());
		}else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return new ErrorMessage().getErrorResponse("100");
	}
		return otpbean;
		
	}
	public Object login(Customer userbean) {
		// TODO Auto-generated method stub
		
		Object obj= authenticateUser(userbean);
		return obj;
	}
	private Object authenticateUser(Customer userbean) {
		// TODO Auto-generated method stub
		Connection c = null;		
		PreparedStatement ps = null;
		SuccessBean bean= new SuccessBean();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement("select customer_id  from customer_t where customer_mob = ? and customer_password=?");
			ps.setString(1, userbean.getPhoneNumber());
			ps.setString(2, userbean.getPassword());
			ResultSet rs =ps.executeQuery();
			if(rs.next())
			{
				bean.setResponseCode("200");
				bean.setServiceType("User Authentication");
				bean.setStatus("User authenticated successfully");
				bean.setCustomerID(rs.getString(1));
				System.out.println("bean : "+bean.getCustomerID());
				return bean;
			}
			else
			{
				return new ErrorMessage().getErrorResponse("105");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return bean;
	}
	
	public Object addUserItemData(UserItem useritem)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		SuccessBean successbean=new  SuccessBean();
	try {
		String addressId="CA"; //Customer Address
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("INSERT INTO customer_item_data_t"
								+ "(brand_id,product_id,category_id,subcategory_id,year_of_purchase,"
								+ "Model_Number,customer_id,warrenty,master_item_id) VALUES"
								+ " (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, useritem.getBrandID());
		ps.setString(2, useritem.getProductID());
		ps.setString(3, useritem.getCategoryID());
		ps.setString(4, useritem.getSubcategoryID());
		ps.setString(5, useritem.getYearop());
		ps.setString(6, useritem.getModelNumber());
		ps.setString(7, useritem.getCustomerID());
		ps.setString(8, useritem.getWarrentyMonths());
		ps.setString(9, useritem.getItemID());
		int i=ps.executeUpdate();
	    if(i>0)
	    {
	    	ps1= c.prepareStatement("select item_id from customer_item_data_t where customer_id = ? order by item_id desc limit 1");
		    ps1.setString(1, useritem.getCustomerID());
		    ResultSet rs =ps1.executeQuery();
		    String item_id = "";
		    System.out.println(useritem.getCustomerID());
		    if(rs.next())
		    {
		    	item_id = rs.getString(1);
		    }
		    System.out.println(item_id);
		    System.out.println(useritem.getSameAddress());
		    if(useritem.getSameAddress().equalsIgnoreCase("no"))
			{
						ps2=c.prepareStatement("INSERT INTO Customer_address_t(line1,line2,address_city,address_district,address_state,address_country,address_pincode,lat,lang) "
												+ "Values(?,?,?,?,?,?,?,?,?)");
						ps2.setString(1, useritem.getAddress().getLine1());
						ps2.setString(2, useritem.getAddress().getLine2());
						ps2.setString(3, useritem.getAddress().getCity());
						ps2.setString(4, useritem.getAddress().getDistrict());
						ps2.setString(5, useritem.getAddress().getState());
						ps2.setString(6, useritem.getAddress().getCountry());
						ps2.setString(7, useritem.getAddress().getPinCode());
						ps2.setString(8, useritem.getAddress().getLan());
						ps2.setString(9, useritem.getAddress().getLang());
						ps2.executeUpdate();
						ps3=c.prepareStatement("SELECT customer_address_id from customer_address_t order by customer_address_id desc limit 1");
						ResultSet rs2= ps3.executeQuery();
						if(rs2.next())
							addressId=rs2.getString(1);
						ps4=c.prepareStatement("INSERT INTO customer_address_map_t(customer_id,address_id,"
								+ "address_type_id,customer_item_id) values (?,?,?,?)");
						ps4.setString(1, useritem.getCustomerID());
						ps4.setString(2, addressId);
						ps4.setString(3, useritem.getAddress().getAddressType());
						ps4.setString(4, item_id);
						ps4.executeUpdate();
			}
		    ArrayList<ItemDocs> listofdocs = useritem.getItemDocs();
		    if(listofdocs!=null)
		    {
			    for(int l=0;l<listofdocs.size();l++)
			    {
					ps = c.prepareStatement("INSERT INTO customer_item_documents_t"
											+ "(item_id,doc_type_id,file_path) VALUES (?,?,?)");
					ps.setString(1, item_id);
					ps.setString(2, listofdocs.get(l).getDocType());
					String filepath=Utility.getDocsPath("customerdocuments");
					long millisStart = Calendar.getInstance().getTimeInMillis();
					String fullfile = filepath + useritem.getCustomerID()+item_id+listofdocs.get(l).getDocType()+l+millisStart;
					BufferedImage bufImg = ImageUtility.decodeToImage(listofdocs.get(l).getImageData().toString());
					ps.setString(3, useritem.getCustomerID()+item_id+listofdocs.get(l).getDocType()+l+millisStart+".png");
					ImageUtility.writeImageRepository(bufImg, new File(fullfile+".png"), "png");
					ps.executeUpdate();
			    }
				if(i>0)
				{
					successbean.setResponseCode("200");
					successbean.setStatus("Added products successfully");
					successbean.setServiceType("Adding products");
					successbean.setCustomerID(useritem.getCustomerID());
				}
				else 
				{
						return new ErrorMessage().getErrorResponse("100");
				}
		    }
	    }
	    else 
		{
				return new ErrorMessage().getErrorResponse("100");
		}
	} catch (Exception e) {
		// TODO: handle exception
		System.out.print(e.getMessage());
		return new ErrorMessage().getErrorResponse("100");
	}
		return successbean;
		
	}

	public Object itemslist(String customerId) {
		try
		{
		return getitemslist(customerId);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	} 
	
	private Object getitemslist(String customerId)
	{		
		Connection c = null;		
		PreparedStatement ps = null;
		ResultSet rs=null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		ResultSet rs1=null;
		
		CustomerItemsRespose responsedata = new CustomerItemsRespose();
		ArrayList<UserItemsResponse> itemslist = new ArrayList<UserItemsResponse>();
		ArrayList<ServiceRequestAsRespose> servicerquests = new ArrayList<ServiceRequestAsRespose>();
		try 
		{
			c = ConnectionHelper.getConnection();
				ps2=c.prepareStatement("SELECT sr.customer_item_id,cr.model_number,sr.request_id FROM service_request_t "
										+ " as sr join customer_item_data_t as cr On cr.item_id = sr.customer_item_id  "
										+ " where sr.status = ? and sr.ticket_status =? and sr.customer_id=?");
				ps2.setString(1, "1");
				ps2.setString(2, "pending");
				ps2.setString(3, customerId);
				rs2=ps2.executeQuery();
				while(rs2.next())
				{
					System.out.println("in rs 2");
					ServiceRequestAsRespose requests = new ServiceRequestAsRespose();
					requests.setCustomerItemID(rs2.getString(1));
					requests.setCustomerItemName(rs2.getString(2));
					requests.setServiceRequestId(rs2.getString(3));
					requests.setStatus("Pending");
					servicerquests.add(requests);
				}
				String getqry="select cudata.category_id,cudata.subcategory_id,cudata.product_id,cudata.brand_id"
						+ ",cudata.model_number,cudata.item_id,cudata.warrenty,br.brand_name,cm.category_name"
						+ ",sm.name,pm.product_name,im.image_url,cudata.year_of_purchase from customer_item_data_t as cudata "
						+ "left join brands_m as br "
						+ "ON cudata.brand_id = br.brand_id "
						+ "left join category_m as cm "
						+ "ON cudata.category_id = cm.category_id "
						+ "left join subcategory_m as sm "
						+ "ON cudata.subcategory_id = sm.subcategory_id "
						+ "left join products_m as pm "
						+ "ON cudata.product_id = pm.product_id "
						+ "left join master_item_m im"
						+ " ON cudata.master_item_id = im.item_id "
						+ " where cudata.customer_id=?";
				System.out.println("here in customer items list : "+getqry);
					ps = c.prepareStatement(getqry);
					ps.setString(1, customerId);
					rs=ps.executeQuery();
				while(rs.next())
				{
					UserItemsResponse items = new UserItemsResponse();
					items.setBrandID(rs.getString(4));
					items.setCategoryID(rs.getString(1));
					items.setSubcategoryID(rs.getString(2));
					items.setProductID(rs.getString(3));
					items.setItemID(rs.getString(6));
					items.setModelNumber(rs.getString(5));
					items.setCustomerID(customerId);
					items.setWarrentyMonths(rs.getString(7));
					items.setBrandName(rs.getString(8));
					items.setProductName(rs.getString(11));
					items.setCategoryName(rs.getString(9));
					items.setSubcategoryName(rs.getString(10));
					items.setItemImageUrl(rs.getString(12));
					items.setYearop(rs.getString(13));
					ArrayList<ItemDocs> idocs= new ArrayList<ItemDocs>();			
					ps1 = c.prepareStatement("select * from customer_item_documents_t where item_id = ?");
					ps1.setString(1, rs.getString("item_id"));
					rs1=ps1.executeQuery();
					while(rs1.next())
					{
						ItemDocs docs= new ItemDocs();
						docs.setDocType(rs1.getString("doc_type_id"));
						String filepath=Utility.getDocsPath("customerdocumentsurl");
						docs.setFilePath(filepath+rs1.getString("file_path"));
						idocs.add(docs);
					}
					items.setItemDocs(idocs);
					itemslist.add(items);
				}
				responsedata.setServicerequestslist(servicerquests);
				responsedata.setItemslist(itemslist);
		} 
		catch (Exception e)
		{
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
		}
		finally
		{
			ConnectionHelper.close(c);	
			try 
			{
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} 
			catch (SQLException e) {
			}
		}
		return responsedata;
	}
	
	public Object itemsdoclist(String customerId) {
		try
		{
		return getitemsdoclist(customerId);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	} 
	
	private Object getitemsdoclist(String customerId)
	{		
		UserItem items = new UserItem();
		Connection c = null;		
		PreparedStatement ps = null;
		ResultSet rs=null;
		PreparedStatement ps1 = null;
		ResultSet rs1=null;
		ArrayList<UserItem> itemslist = new ArrayList<UserItem>();
	
		String getqry="select * from customer_item_data_t where customer_id=?";
		try 
		{
				c = ConnectionHelper.getConnection();
				ps = c.prepareStatement(getqry);
				ps.setString(1, items.getBrandID());
				rs=ps.executeQuery();
				while(rs.next())
				{
					items.setBrandID(rs.getString("brand_id"));
					items.setModelNumber(rs.getString("Model_Number"));
					items.setProductID(rs.getString("product_id"));
					items.setDescription(rs.getString("description"));
					items.setCategoryID(rs.getString("category_id"));
					items.setSubcategoryID(rs.getString("subcategory_id"));
					items.setSpecification(rs.getString("Specification"));
					items.setItemID(rs.getString("item_id"));
					items.setCustomerID(customerId);
					items.setWarrentyMonths(rs.getString("warrenty"));
					ArrayList<ItemDocs> idocs= new ArrayList<ItemDocs>();			
					// Need to get list of docs
					ps1 = c.prepareStatement("select * from customer_item_documents_t where item_id = ?");
					ps1.setString(1, rs.getString("item_id"));
					rs1=ps1.executeQuery();
					while(rs1.next())
					{
						ItemDocs docs= new ItemDocs();
						docs.setDocType("Invoice");
						docs.setFilePath("local");
						idocs.add(docs);
					}
					items.setItemDocs(idocs);
					itemslist.add(items);
				}
		} 
		catch (Exception e)
		{
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
		}
		finally
		{
			ConnectionHelper.close(c);	
			try 
			{
				if(ps!=null)ps.close();
				if(rs!=null)rs.close();
			} 
			catch (SQLException e) {
			}
		}
		return itemslist;
	}
	
	
	
	public Object addUserTicket(Ticket ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		SuccessBean successbean=new  SuccessBean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO ticket"
								+ "(customer_id,customer_item_id,raisedby,query,status"
								+ ") VALUES (?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerID());
		ps.setString(2, ticketBean.getCustomerItemID());
		ps.setString(3, "self");
		ps.setString(4, ticketBean.getQuery());
		ps.setString(5, "new");
	    int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
	}
		return successbean;
		
	}
	
	public Object userticketsList(String customerID)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		ArrayList<Ticket>  ticketsList =  new ArrayList<Ticket>();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select *  from ticket where customer_id = ? ");
		ps.setString(1, customerID);
		
		ResultSet rs =ps.executeQuery();
		
		while(rs.next())
		{
			Ticket ticket=new Ticket();
			ticket.setCustomerID(rs.getString("customer_id"));
			ticket.setCustomerItemID(rs.getString("customer_item_id"));
			ticket.setQuery(rs.getString("query"));
			ticket.setTicketID(rs.getString("ticket_id"));
			ticket.setStatus(rs.getString("status"));
			ticketsList.add(ticket);
			
		}
			
	} catch (Exception e) {
		// TODO: handle exception
	}
		return ticketsList;
		
	}
	
	
	public Object updateTicket(Ticket ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		SuccessBean successbean=new  SuccessBean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO ticket_status"
								+ "(customer_id,customer_item_id,raisedby,query,status"
								+ ") VALUES (?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerID());
		ps.setString(2, ticketBean.getCustomerItemID());
		ps.setString(3, "self");
		ps.setString(4, ticketBean.getQuery());
		ps.setString(5, "new");
	    int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("104");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
	}
		return successbean;
		
	}
	public Object addserviceRequest(ServiceRequest ticketBean)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		SuccessBean successbean=new  SuccessBean();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("INSERT INTO service_request_t"
								+ "(customer_id,type_of_request,description,scheduled_time,lat,lang,image_path,address"
								+ ",token_number,repair_id,customer_item_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, ticketBean.getCustomerID());
		ps.setString(2, ticketBean.getTypeOfRequest());
		ps.setString(3, ticketBean.getDescription());
		ps.setString(4, ticketBean.getScheduledDate());
		ps.setString(5, ticketBean.getLat());
		ps.setString(6, ticketBean.getLang());
		long millisStart = Calendar.getInstance().getTimeInMillis();
		String fullfile = "no file";
		if(ticketBean.getImageString()!=null){
		String filepath=Utility.getDocsPath("customerservicedocuments");
		fullfile = filepath + ticketBean.getCustomerID()+millisStart;
		BufferedImage bufImg = ImageUtility.decodeToImage(ticketBean.getImageString());
		ImageUtility.writeImageRepository(bufImg, new File(fullfile+".png"), "png");
		}
		
		ps.setString(7, fullfile);
		ps.setString(8,"address"); // need to update address
		ps.setString(9, ticketBean.getCustomerID()+millisStart);
		ps.setString(10, ticketBean.getRepaiTypeId());
		ps.setString(11, ticketBean.getCustomerItemID());
		int i=ps.executeUpdate();
		
	    //images need to create in server
	    
		if(i>0)
		{
			successbean.setResponseCode("200");
			successbean.setStatus("Request raised successfully");
			successbean.setCustomerID(ticketBean.getCustomerID());
				//success code
		}
		else 
		{
				return new ErrorMessage().getErrorResponse("100");
		}
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
		return new ErrorMessage().getErrorResponse("100");
	}
		return successbean;
		
	}
	

	public Object typeofservices()
	{
		Connection c = null;		
		PreparedStatement ps = null;
		ServiceTypeResponse response = new ServiceTypeResponse();
	try {
		c = ConnectionHelper.getConnection();
		
		
		
		
		ps = c.prepareStatement("select *  from request_types_m where status = ? ");
		ps.setBoolean(1, true);
		ResultSet rs =ps.executeQuery();
		ArrayList<ServiceType> servicetypes = new ArrayList<ServiceType>();
		while(rs.next())
		{
			ServiceType obj= new ServiceType();
			obj.setServiceType(rs.getString("request_type"));
			obj.setServiceTypeID(rs.getString("typeid"));
			servicetypes.add(obj);
		}

		response.setCustomerID("");
		response.setResponseCode("200");
		response.setStatus("Service types List");
		response.setServiceType("Type of services");
		response.setServiceRequest(servicetypes);
		
	} catch (Exception e) {
		// TODO: handle exception
	}
		return response;
		
	}
	
	public Object typeofdocs()
	{
		Connection c = null;		
		PreparedStatement ps = null;
		DocTypeResponse response = new DocTypeResponse();
	try {
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("select *  from docs_type_m ");
//		ps.setBoolean(1, true);
		ResultSet rs =ps.executeQuery();
		ArrayList<DocsType> doctypes = new ArrayList<DocsType>();
		while(rs.next())
		{
			DocsType obj= new DocsType();
			obj.setDocType(rs.getString("docttype"));
			obj.setDocTypeId(rs.getString("type_id"));
			doctypes.add(obj);
		}

		response.setCustomerID("");
		response.setResponseCode("200");
		response.setStatus("Doc types List");
		response.setServiceType("Type of services");
		response.setDocstype(doctypes);
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
	}
		return response;
		
	}
	
	
	public Object typeofrepairs(String customerItemId)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		RequestTypeResponse response = new RequestTypeResponse();
	try {
		c = ConnectionHelper.getConnection();
		//Need to add where condition for item or brand data
		ps = c.prepareStatement("select *  from repair_types_m where status = ? and  item_id = (select master_item_id from customer_item_data_t where item_id = '"+customerItemId+"' )");
		ps.setBoolean(1, true);
//		ps.setString(2, customerItemId);
		ResultSet rs =ps.executeQuery();
		ArrayList<RequestType> requesttypes = new ArrayList<RequestType>();
		while(rs.next())
		{
			RequestType obj= new RequestType();
			obj.setRequestTypeID(rs.getString("type_id"));
			obj.setRequestType(rs.getString("repair_code"));
			obj.setDescription(rs.getString("description"));
			requesttypes.add(obj);
		}

		response.setCustomerID("");
		response.setResponseCode("200");
		response.setStatus("Repair types List");
		response.setServiceType("Type of Repairs");
		response.setRequestType(requesttypes);
		
	} catch (Exception e) {
		// TODO: handle exception
		return new ErrorMessage().getErrorResponse("100");
	}
		return response;
		
	}

	
	
	public Object getContentData(ContentRequest contentrequest)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		ContentResponse response = new ContentResponse();
	try {
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("select * from content_data_m where content_type_id =? and meta_key_words like '%' || ? || '%'");
	    ps.setString(1, contentrequest.getContenttype());
	    ps.setString(2, contentrequest.getMetadata());
		ResultSet rs=ps.executeQuery();
		ArrayList<Content> contentlist = new ArrayList<Content>();
	    while(rs.next())
	    {
	    	Content content = new Content();
	    	content.setContentId(rs.getString("content_dataid"));
	    	content.setPath(rs.getString("path"));
	    	content.setText(rs.getString("text"));
	    	contentlist.add(content);
	    }
		response.setResponseCode("200");
		response.setCustomerID(contentrequest.getCustomerID());
		response.setContentlist(contentlist);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ex :"+e);
		return new ErrorMessage().getErrorResponse("100");
	}
		return response;
		
	}

	public Object insertcallback(String customerID)
	{
		SuccessBean bean = new SuccessBean();
		Connection c = null;		
		PreparedStatement ps = null;
	try {
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("insert into callback(customer_id) values(?)");
		ps.setString(1, customerID);
		int i =ps.executeUpdate();
		if(i>0)
		{
			bean.setCustomerID(customerID);
			bean.setResponseCode("200");
			bean.setStatus("We are received your request , We will call you in short time");
		}
		else
			return new ErrorMessage().getErrorResponse("100");
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return new ErrorMessage().getErrorResponse("100");
		
	}
		return bean;
		
	}
	
	public Object shareprofile(shareProfile shareProfile)
	{
		SuccessBean bean = new SuccessBean();
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;

		ResultSet rs= null;

		ResultSet rs2= null;
	try {
		c = ConnectionHelper.getConnection();
		ps = c.prepareStatement("select customer_id from customer_t where customer_mob = ?");
		ps.setString(1, shareProfile.getPhonenumber());
		rs=ps.executeQuery();
		if(rs.next())
		{
			ps1=c.prepareStatement("insert into customer_shared_profiles_t"
					+ "(customer_id,sub_customer_id) values (?,?)");
			ps1.setString(1, shareProfile.getCustomerId());
			ps1.setString(2, rs.getString(1));
			ps1.executeUpdate();
		}
		else
		{
			ps1=c.prepareStatement("insert into customer_t"
					+ "(customer_name,customer_mob,customer_password,customer_type)"
					+ " values(?,?,?,?)");

			ps1.setString(1, shareProfile.getName());
			ps1.setString(2, shareProfile.getPhonenumber());
			ps1.setString(3, shareProfile.getPhonenumber()); //Password need to update
			ps1.setString(4, "sharedProfile");
			int i = ps1.executeUpdate();
			if(i>0)
			{
				ps2 = c.prepareStatement("select customer_id from customer_t where customer_mob = ?");
				ps2.setString(1, shareProfile.getPhonenumber());
				rs2=ps2.executeQuery();
				if(rs2.next())
				{
					ps3=c.prepareStatement("insert into customer_shared_profiles_t"
							+ "(customer_id,sub_customer_id) values (?,?)");
					ps3.setString(1, shareProfile.getCustomerId());
					ps3.setString(2, rs2.getString(1));
					ps3.executeUpdate();
				}
			}

			else
				return new ErrorMessage().getErrorResponse("100");
			
			
					
		}
		bean.setCustomerID(shareProfile.getCustomerId());
		bean.setResponseCode("200");
		bean.setStatus("Shared your profile");
//		return bean;
			
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return new ErrorMessage().getErrorResponse("100");
		
	}
		return bean;
		
	}
	public Object sharedprofiles(String customerID)
	{
		Connection c = null;		
		PreparedStatement ps = null;
		sharedProfile profiles = new sharedProfile();
		ArrayList<sharedProfilesList> sahredProfilesList = new ArrayList<sharedProfilesList>();
	try {
		c = ConnectionHelper.getConnection();
		
		ps = c.prepareStatement("select csp.customer_id,cus.customer_name  from customer_shared_profiles_t csp join"
				+ " customer_t cus on cus.customer_id = csp.customer_id where csp.sub_customer_id = ? ");
		ps.setString(1, customerID);
		
		ResultSet rs =ps.executeQuery();
		
		while(rs.next())
		{
			sharedProfilesList profileslist = new sharedProfilesList();
			profileslist.setSubcustomerId(rs.getString(1));
			profileslist.setName(rs.getString(2));
			sahredProfilesList.add(profileslist);		
		}
		profiles.setCustomerId(customerID);
		profiles.setSahredProfilesList(sahredProfilesList);
		profiles.setResponsecode("200");
			
	} catch (Exception e) {
		System.out.println("ex :"+e);
		// TODO: handle exception

		return new ErrorMessage().getErrorResponse("100");
	}
		return profiles;
		
	}
	

}