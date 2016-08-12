package com.clic.Utility;

import java.io.*;
import java.util.Properties;

import com.clic.config.ProjectConfig;

public class Utility {
	
		public static int generatePIN() 
	   {
	        //generate a 4 digit integer 1000 <10000
	        int randomPIN = (int)(Math.random()*9000)+1000;
	        return randomPIN;
	   }
	   public static String getDocsPath(String doctype)
	   {
			ProjectConfig prop=new ProjectConfig("app.properties");
	        Properties properties = prop.getProjectProperties();
	        String filepath=properties.getProperty(doctype);
		   return filepath;
	   }
		public static void writeFile(String filepath)
		{
			File f=new File("D:\\1234.pdf");
		    OutputStream oos;
			try {
					oos = new FileOutputStream("D:\\test.pdf");
		            byte[] buf = new byte[8192];
			        InputStream is = new FileInputStream(f);
				    int c = 0;
				        while ((c = is.read(buf, 0, buf.length)) > 0) {
				            oos.write(buf, 0, c);
				            oos.flush();
				        }
				        oos.close();
				        System.out.println("stop");
				        is.close();
				}		
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

       
}
