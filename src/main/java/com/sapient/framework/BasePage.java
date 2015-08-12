package com.sapient.framework;

import org.apache.log4j.Logger;

import com.sapient.utils.SetupUtils;

/**
 * All the page classes should extend the base page to get the logger instance
 * @author sgu109
 *
 */
public abstract class BasePage {
	
	// Provides the logger instance to all page classes
	protected static Logger logger=(Logger) SetupUtils.getState("logger");
    
	

	 
}
