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
import com.clic.model.Item;
import com.clic.model.ItemsListResponse;
import com.clic.model.SubCategory;
import com.clic.model.SubCategoryListResponse;
import com.clic.model.Brand;
import com.clic.model.Category;
import com.clic.model.CategoryListResponse;
import com.clic.model.Product;
import com.clic.model.SuccessBean;



/**
 * UserDao.java
 * Purpose: ProductsDao class is responsible for 
 * @author CLIC
 * @version 1.0
 */

public class ProductsDao {
	
    String errorcode = "";
                  
    /**
     * Method to validate user mobile number 
     */
    public Object brandslist() {
    	try
        {
        	return getbrandslist();
        } catch (Exception e) {
            throw new RuntimeException(e);
		}
    } 
    /**
	 * Method to check user existed or not
	 */
    private Object getbrandslist() {		
		Connection c = null;		
		PreparedStatement ps = null;
		
		ResultSet rs=null;
		String getqry="select * from brands_m";
		ArrayList<Brand> brandslist = new ArrayList<Brand>();
		try {
			c = ConnectionHelper.getConnection();
			ps = c.prepareStatement(getqry);
	      	rs=ps.executeQuery();
	      	while(rs.next())
	      	{
	      		Brand brands = new Brand();
	      		brands.setBrandID(rs.getString("brand_id"));
	      		brands.setBrandName(rs.getString("brand_name"));
	      		brands.setLogoURL(rs.getString("brand_logo"));
	      		brands.setDescription(rs.getString("brand_description"));
	      		brandslist.add(brands);
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
		return brandslist;
	}
    
    
/**
* Method to validate user mobile number 
*/
public Object productslist(String brandid) {
try
{
	return getproductslist(brandid);
} catch (Exception e) {
 throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getproductslist(String brandid) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
ArrayList<Product> productslist = new ArrayList<Product>();
String getqry="select * from products_m where brand_id=?";
try {
	c = ConnectionHelper.getConnection();
	ps = c.prepareStatement(getqry);
	ps.setString(1, brandid);
	rs=ps.executeQuery();
	while(rs.next())
	{

		Product products = new Product();
		products.setBrandID(rs.getString("brand_id"));
		products.setProductName(rs.getString("product_name"));
		products.setProductID(rs.getString("product_id"));
		products.setDescription(rs.getString("description"));
		productslist.add(products);
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
return productslist;
}

/**
* Method to validate user mobile number 
*/
public Object itemsexist(String customerid) {
try
{
	return checkitemexist(customerid);
} catch (Exception e) {
 throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object checkitemexist(String customerid) {		
Connection c = null;		
PreparedStatement ps = null;
Product products = new Product();
ResultSet rs=null;
String getqry="select * from customer_item_data_t where customer_id=?";
SuccessBean bean= new SuccessBean();

try {
	c = ConnectionHelper.getConnection();
	ps = c.prepareStatement(getqry);
	ps.setString(1, customerid);
	rs=ps.executeQuery();
	bean.setResponseCode("200");
	bean.setServiceType("Method for check items existed or not for logged in user");
	bean.setCustomerID(customerid);
	if(rs.next())
	{
		bean.setStatus("yes");
	}
	else
	{
		bean.setStatus("no");
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
return bean;
}



/**
* Method to validate user mobile number 
*/
public Object categoryslist(Category categorys) {
try
{
return getcategoryslist(categorys);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getcategoryslist(Category categorys) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
CategoryListResponse categorysList = new CategoryListResponse();
ArrayList<Category> categoryslist = new ArrayList<Category>();
String getqry="select * from category_m where brand_id=? and product_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, categorys.getBrandID());
ps.setString(2, categorys.getProductID());
rs=ps.executeQuery();

while(rs.next())
{
	Category category = new Category();
	category.setBrandID(rs.getString("brand_id"));
	category.setCategoryName(rs.getString("category_name"));
	category.setProductID(rs.getString("product_id"));
	category.setDescription(rs.getString("description"));
	category.setCategoryID(rs.getString("category_id"));
	categoryslist.add(category);
	System.out.println("in c");
}
categorysList.setResponseCode("200");

categorysList.setCategorylist(categoryslist);
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
return categorysList;
}

/**
* Method to validate user mobile number 
*/
public Object subcategoryslist(SubCategory subcategorys) {
try
{
return getsubcategoryslist(subcategorys);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getsubcategoryslist(SubCategory subcategorys) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
SubCategoryListResponse subcategorysList = new SubCategoryListResponse();
ArrayList<SubCategory> subcategoryslist = new ArrayList<SubCategory>();
String getqry="select * from subcategory_m where brand_id=? and product_id = ? and category_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, subcategorys.getBrandID());
ps.setString(2, subcategorys.getProductID());
ps.setString(3, subcategorys.getCategoryID());
rs=ps.executeQuery();

while(rs.next())
{
	SubCategory subcategory = new SubCategory();
	subcategory.setBrandID(rs.getString("brand_id"));
	subcategory.setSubCategoryName(rs.getString("name"));
	subcategory.setProductID(rs.getString("product_id"));
	subcategory.setDescription(rs.getString("description"));
	subcategory.setCategoryID(rs.getString("category_id"));
	subcategory.setSubcategoryID(rs.getString("subcategory_id"));
	subcategoryslist.add(subcategory);
}
subcategorysList.setResponseCode("200");
subcategorysList.setSubcategorylist(subcategoryslist);
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
return subcategorysList;
}


/**
* Method to validate user mobile number 
*/
public Object itemslist(Item items) {
try
{
return getitemslist(items);
} catch (Exception e) {
throw new RuntimeException(e);
}
} 
/**
* Method to check user existed or not
*/
private Object getitemslist(Item items) {		
Connection c = null;		
PreparedStatement ps = null;
ResultSet rs=null;
ItemsListResponse itemsList = new ItemsListResponse();
ArrayList<Item> itemslist = new ArrayList<Item>();
String getqry="select * from master_item_m where brand_id=? and product_id = ? and category_id = ? and subcategory_id = ?";
try {
c = ConnectionHelper.getConnection();
ps = c.prepareStatement(getqry);
ps.setString(1, items.getBrandID());
ps.setString(2, items.getProductID());
ps.setString(3, items.getCategoryID());
ps.setString(4, items.getSubcategoryID());
rs=ps.executeQuery();

while(rs.next())
{
	Item obj = new Item();
	obj.setBrandID(rs.getString("brand_id"));
	obj.setModelNumber(rs.getString("Model_Number"));
	obj.setProductID(rs.getString("product_id"));
	obj.setDescription(rs.getString("description"));
	obj.setCategoryID(rs.getString("category_id"));
	obj.setSubcategoryID(rs.getString("subcategory_id"));
	obj.setSpecification(rs.getString("Specification"));
	obj.setItemID(rs.getString("item_id"));
	itemslist.add(obj);
}
itemsList.setResponseCode("200");
itemsList.setItemsList(itemslist);

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
return itemsList;
}

	}