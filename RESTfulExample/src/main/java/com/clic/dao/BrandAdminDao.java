package com.clic.dao;

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

import com.clic.Dbconnection.ConnectionHelper;
import com.clic.Utility.ErrorMessage;
import com.clic.Utility.Utility;
import com.clic.model.Item;
import com.clic.model.ItemDocs;
import com.clic.model.ItemsListResponse;
import com.clic.model.SubCategory;
import com.clic.model.SubCategoryListResponse;
import com.clic.model.Brand;
import com.clic.model.BrandAdminLogin;
import com.clic.model.BrandAdminSuccessBean;
import com.clic.model.BrandUser;
import com.clic.model.Category;
import com.clic.model.CategoryListResponse;
import com.clic.model.CustomerItemsRespose;
import com.clic.model.Product;
import com.clic.model.ServiceRequestAsRespose;
import com.clic.model.ServiceRequestUpdate;
import com.clic.model.ServiceRequestsList;
import com.clic.model.SuccessBean;
import com.clic.model.UserItemsResponse;
import com.clic.model.UserTypes;
import com.clic.model.retailerstores;



/**
 * UserDao.java
 * Purpose: ProductsDao class is responsible for 
 * @author CLIC
 * @version 1.0
 */

public class BrandAdminDao {
	
    String errorcode = "";
                  
    /**
     * Method to validate user mobile number 
     */
    public Object login(BrandAdminLogin bean) {
    	try
        {
        	return authentication(bean);
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to validate user 
	 */
    private Object authentication(BrandAdminLogin bean) {		
		Connection c = null;		
		PreparedStatement ps = null;
		BrandAdminSuccessBean response = new BrandAdminSuccessBean();
		ResultSet rs=null;
		String getqry="select * from brand_admin where name = ? and password = ?";
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getPassword());
			System.out.println(": "+bean.getUsername() + bean.getPassword());
			rs=ps.executeQuery();
	      	if(rs.next())
	      	{
	      		response.setAdminId(rs.getString("id"));
	      		response.setResponseCode("200");
	      		response.setServiceType("Authentication");
	      		response.setStatus("Authentication successfull");
	      		
	      	}
	      	else
	      		return new ErrorMessage().getErrorResponse("300");

		
		} catch (Exception e) {
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
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
		return response;
	}
    public Object serviceslist(String brandId,String requesttype) {
		try
		{
		return getservicerequestslist(brandId,requesttype);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	} 
    private Object getservicerequestslist(String brandId,String requesttype)
	{		
		Connection c = null;		
		PreparedStatement ps = null;
		ResultSet rs=null;
		PreparedStatement ps1 = null;
		ResultSet rs1=null;

		ArrayList<ServiceRequestsList> serlist = new ArrayList<ServiceRequestsList>();
		String qry ="";
		String statusqry="";
		try 
		{
			c = ConnectionHelper.getConnection();
			qry="SELECT sr.customer_item_id,cr.model_number,sr.request_id,sr.ticket_status"
					+ " ,sr.description,sr.received_time FROM service_request_t "
					+ " as sr join customer_item_data_t as cr On cr.item_id = sr.customer_item_id  "
					+ " where sr.status = ? and sr.ticket_status !=? and cr.brand_id=?";
			if(requesttype.equals("s"))
				qry = qry + "AND sr.type_of_request !=?"; // All request's Except Demo
			else
				qry = qry + "AND sr.type_of_request =?";
			
				
			ps=c.prepareStatement(qry);
			ps.setString(1, "1");
			ps.setString(2, "NA");
			ps.setString(3, brandId);
			ps.setString(4, "1");
			rs=ps.executeQuery();
			while(rs.next())
			{
				statusqry = "select comment from service_request_update_t  "
						+ " where service_request_id = ?"	
						+ " order by id desc limit 1"
							;
				ps1 = c.prepareStatement(statusqry);
				ps1.setString(1,rs.getString(3) );
				rs1=ps1.executeQuery();
				ServiceRequestsList slist = new ServiceRequestsList();
				
				if(rs1.next())
					slist.setLastComment(rs1.getString(1));
				else
					slist.setLastComment("N/A");
				slist.setItemName(rs.getString(2));
				slist.setStatus(rs.getString(4));
				slist.setServiceRequestId(rs.getString(3));
				slist.setDescription(rs.getString(5));
				slist.setCreatedDate(rs.getString(6));
				slist.setEditRequest("<a onclick=editrequest("+rs.getString(3)+")>Edit</a>");
				serlist.add(slist);
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
		return serlist;
	}
	
	public Object usertypeslist(String brandId) {
    	try
        {
        	return getusertpeslist(brandId);
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private Object getusertpeslist(String brandId) {		
		Connection c = null;		
		PreparedStatement ps = null;
		
		ResultSet rs=null;
		String getqry="select * from user_type_m where status = ?";
		ArrayList<UserTypes> usertypes = new ArrayList<UserTypes>();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, "1");
	      	rs=ps.executeQuery();
	      	while(rs.next())
	      	{
	      		UserTypes usertype = new UserTypes();
	      		usertype.setTyprId(rs.getString(1));
	      		usertype.setUserType(rs.getString(2));
	      		usertypes.add(usertype);
	      	}
		
		} catch (Exception e) {
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
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
		return usertypes;
	}
    public Object retailerstoreslist(String brandId) {
    	try
        {
        	return getretailerstoreslist(brandId);
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private Object getretailerstoreslist(String brandId) {		
		Connection c = null;		
		PreparedStatement ps = null;
		
		ResultSet rs=null;
		String getqry="select * from retailer_m where status = ?";
		ArrayList<retailerstores> retailerstores = new ArrayList<retailerstores>();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, "1");
	      	rs=ps.executeQuery();
	      	while(rs.next())
	      	{
	      		retailerstores retailerstore = new retailerstores();
	      		retailerstore.setId(rs.getString(1));
	      		retailerstore.setStoreName(rs.getString(2));
	      		retailerstores.add(retailerstore);
	      	}
		
		} catch (Exception e) {
			return new ErrorMessage().getErrorResponse("100");
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
		return retailerstores;
	}
    /**
     * Method to validate user mobile number 
     */
    public Object addBrandUser(BrandUser branduser) {
    	try
        {
        	return insertuser(branduser);
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to validate user 
	 */
    private Object insertuser(BrandUser branduser) {		
		Connection c = null;		
		PreparedStatement ps = null;
		BrandAdminSuccessBean response = new BrandAdminSuccessBean();
		ResultSet rs=null;
		String getqry="INSERT INTO brand_users_t(usertype,name,password,retail_id,brand_id) VALUES (?,?,?,?,?)";
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps.setString(1, branduser.getUsertype());
			ps.setString(2, branduser.getUsername());
			ps.setString(3, branduser.getPassword());
			ps.setString(4, branduser.getRetailerstore());
			ps.setString(5, branduser.getBrandId());
			int i=ps.executeUpdate();
	      	if(i>0)
	      	{
//	      		response.setAdminId(rs.getString("id"));
	      		System.out.println(" Here : Data Response ");
	      		response.setResponseCode("200");
	      		response.setServiceType("User Creation");
	      		response.setStatus("User Created successfully");
	      		return response;
	      	}
	      	else
	      		return new ErrorMessage().getErrorResponse("300");

		
		} catch (Exception e) {
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
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
//		return response;
	}
    /**
     * Method to validate user mobile number 
     */
    public Object updateRequest(ServiceRequestUpdate requestUpdate) {
    	try
        {
        	return insertrequesttpdate(requestUpdate);
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to validate user 
	 */
    private Object insertrequesttpdate(ServiceRequestUpdate requestUpdate) {		
		Connection c = null;		
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		BrandAdminSuccessBean response = new BrandAdminSuccessBean();
		ResultSet rs=null;
		String getqry="INSERT INTO service_request_update_t(comment,updated_by,service_request_id) VALUES (?,?,?)";
		String updateqry = "Update service_request_t set ticket_status = ? where request_id = ?";
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
			ps1 = c.prepareStatement(updateqry);
			ps1.setString(1, requestUpdate.getStatus());
			ps1.setString(2, requestUpdate.getServicerequestId());
			ps1.executeUpdate();
			ps.setString(1, requestUpdate.getComment());
			ps.setString(2, "admin");
			ps.setString(3, requestUpdate.getServicerequestId());
			int i=ps.executeUpdate();
	      	if(i>0)
	      	{
//	      		response.setAdminId(rs.getString("id"));
	      		System.out.println(" Here : Data Response ");
	      		response.setResponseCode("200");
	      		response.setServiceType("Updated Comment");
	      		response.setStatus("Updated Comment successfully");
	      		return response;
	      	}
	      	else
	      		return new ErrorMessage().getErrorResponse("300");

		
		} catch (Exception e) {
			System.out.println(e);
			return new ErrorMessage().getErrorResponse("100");
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
//		return response;
	}

    
}