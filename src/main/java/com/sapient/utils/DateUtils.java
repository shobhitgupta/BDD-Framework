package com.sapient.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	private static final String LOG_DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String[] SUPPORTED_DATE_FORMATS = {"MM/dd/yyyy","MM-dd-yyyy hh:mm:ss a",
															"MM-dd-yyyy hh:mm:ss", "yyyy-MM-dd"};

	public static String getLogTime(){
		return getTimeStampByFormat(LOG_DATEFORMAT);
	}
	
	public static String getTimeStampByFormat(String format){
		DateFormat dateFormat;
		Calendar cal;
		
		dateFormat = new SimpleDateFormat(format);
		cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());		
	}
	
	public static String formatDateTime(String dateTime, String format) throws Exception {
		DateFormat dateFormat;
		Calendar cal = Calendar.getInstance();
		Date date=null;
		
		for(String formatStr : SUPPORTED_DATE_FORMATS){
			try{
				date = new SimpleDateFormat(formatStr).parse(dateTime);
				break;
			}catch(ParseException e){
				// try next format
			}
		}
		
		cal.setTime(date);
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(cal.getTime());
		
	}
	
	public static long dateDiff(String dateOne, String dateTwo) throws Exception, ParseException {
		SimpleDateFormat format = new SimpleDateFormat(LOG_DATEFORMAT);
		dateOne = formatDateTime(dateOne,LOG_DATEFORMAT);
		dateTwo = formatDateTime(dateTwo,LOG_DATEFORMAT);
		
		Date date1 = format.parse(dateOne);
		Date date2 = format.parse(dateTwo);
		
		long returnValue = (date2.getTime() - date1.getTime())/1000;
		return returnValue;		
	}
		
}
