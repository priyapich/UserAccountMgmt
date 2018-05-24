package com.boeing.access.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author in841e
 *
 */
public class UserAccessUtil {

	public UserAccessUtil() {
		// TODO Auto-generated constructor stub
	}
	 private static Properties prop;
     
	    static{
	        InputStream is = null;
	        try {
	            prop = new Properties();
	            is = ClassLoader.class.getResourceAsStream("/UserAccessDetails.properties");
	            prop.load(is);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	     
	    public static String getPropertyValue(String key){
	        return prop.getProperty(key);
	    }
	
}
