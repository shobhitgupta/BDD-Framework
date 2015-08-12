package com.sapient.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesFileUtils {
	public Properties propertiesFile;
	
	/**
	 * 
	 * @param filePath
	 * @throws IOException 
	 */
	public PropertiesFileUtils(String filePath) throws IOException{
		propertiesFile = new Properties();
		File inputFile = new File(filePath);
		
		
			FileInputStream fis = new FileInputStream(inputFile);
			propertiesFile.load(fis);
			fis.close();
		
	}
	
	public static void loadPropsFromResource(Properties properties,
			String configPath) throws IOException {
		
		if(StringUtils.isNotBlank(configPath)){
			InputStream inputStream = PropertiesFileUtils.class.getResourceAsStream(configPath);
			properties.load(inputStream);
		}
		else{
			throw new NullPointerException("properties configuration path cannot be null");
		}
	}

}
