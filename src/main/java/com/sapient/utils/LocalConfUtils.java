package com.sapient.utils;

import java.io.File;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class LocalConfUtils {
	private static Properties localConf=null;
	private static String RESOURCE_PATH = "src" + File.separator + "main" 
			+ File.separator + "resources";
	
	public static Properties loadLocalConf(){
		/**
		 * Check command line
		 */
		String localPath = System.getProperty("configurationFile");
		if(StringUtils.isBlank(localPath)){
			localPath = "Conf.properties";
		}
		
		localConf = new Properties();
		try{
			PropertiesFileUtils.loadPropsFromResource(localConf,localPath);
			localConf = loadSystemProperties(localConf);
			
		}catch(Exception e){
			//CucumberLogUtils.logError("Failed to load properties from path:" + localPath);
		}
		return localConf;
	}
	
	private static Properties loadSystemProperties(Properties localProps){
		Properties systemProperties = System.getProperties();
		Enumeration<?> e = systemProperties.propertyNames();
		String key;
		String value;
	    while(e.hasMoreElements()){
	    	key = (String) e.nextElement();
	    	value = systemProperties.getProperty(key);
	    	localProps.setProperty(key, value);
	    }	    
	    return localProps;		
	}
	
	public static Properties getProperties(){
		if(localConf == null){
			loadLocalConf();
		}
		return localConf;
	}
	
	public static String getProperty(String key){
		if(localConf == null){
			loadLocalConf();
		}
		return localConf.getProperty(key);		
	}

	public static String getRootDir(){
		return System.getProperty("user.dir");		
	}

	public static String getResourceDir(){
		return getResourceDir() + File.separator + RESOURCE_PATH;		
	}
	
}
