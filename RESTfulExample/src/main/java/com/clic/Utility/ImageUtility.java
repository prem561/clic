package com.clic.Utility;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;


public class ImageUtility {


	
	public static BufferedImage decodeToImage(String imageBase64String) {
	    
		BufferedImage image=null;
	    byte[] imageByte;
	    
	    try {
	    
	    	imageByte = Base64.decodeBase64(removeBase64Prefix(imageBase64String));
	  
	    	ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	        
	    	image = ImageIO.read(bis);
	        	        
	    	bis.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    return image;
	 
	}
	
	/*
	 * Removes prefix that HTML tends to add for Base 64. 
	 * This removal is necessary for Base64 decoding in java
	 */
	public static String removeBase64Prefix(String imageBase64String) {
		
		String metaString = "data:image/jpeg;base64,";
		String metaStringPng = "data:image/png;base64,";
		
		if (imageBase64String.startsWith(metaString)) {
			
			int metaIndex = imageBase64String.indexOf(metaString) + metaString.length();

			imageBase64String = imageBase64String.substring(metaIndex);			
		}else if (imageBase64String.startsWith(metaStringPng)) {
			
			int metaIndex = imageBase64String.indexOf(metaStringPng) + metaStringPng.length();

			imageBase64String = imageBase64String.substring(metaIndex);			
		}
		
		return imageBase64String;
	}
	
	
	public static void  writeImageRepository(BufferedImage bufImg, File destFile, String imageFormat)
	{
    	try {
			ImageIO.write(bufImg, imageFormat, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*public static String encodeToString(BufferedImage image, String type) {
	    String imageString = null;
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();

	    try {
	        ImageIO.write(image, type, bos);
	        byte[] imageBytes = bos.toByteArray();

	        BASE64Encoder encoder = new BASE64Encoder();
	        imageString = encoder.encode(imageBytes);

	        bos.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return imageString;
	}*/
	
	/*public static void uploadImage(String base64Image)
	{
		System.out.println("base64Image : "+base64Image);
		logger.info("base64Image : "+base64Image);
		try {
			byte[] decodedBytes = Base64.decodeBase64(base64Image);
			String decodedBytes2 = Base64.encodeBase64String(decodedBytes);
			System.out.println("Post decoding: \n"+decodedBytes2);
			logger.info("Post decoding: \n"+decodedBytes2);
			FileOutputStream osf = new FileOutputStream(new File("C:\\Users\\Asim\\Documents\\Projects\\Cards\\temp\\yourImage.jpg"));
			osf.write(decodedBytes); 
			osf.flush();
			osf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

}
