package com.sapient.impl;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.sapient.utils.AppConfUtils;
import com.sapient.utils.Reporter;
import com.sapient.utils.XpathValue;


public class WeatherServiceImpl {

	/**
	 * Validate that the weather restful service is available. If
	 * not then an exception occurs.
	 */
	
	private Response res;
	public void validateAvailable() throws Throwable {		
		
		
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";
		 res = given().
					   expect().
					   statusCode(200).
					   when().
					   then().contentType(ContentType.JSON)
					   .get();
		 
		
		Reporter.embedResponse(res, "WeatherService");
	
		
	}

	/**
	 * Call the restful web service to get a JSON string with weather
	 * results for a city.
	 * 
	 * @param city
	 * @param countryCode
	 * @return 
	 * @throws Throwable
	 */
	public String getWeather(String city, String countryCode) throws Throwable {
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/weather";
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("q", city + "," + countryCode);
		
		 res = given().
					   queryParams(params).
					   expect().
					   statusCode(200).
					   when().
					   then().contentType(ContentType.JSON)
					   .get();
		
	
		
		
		return res.asString();		
	}

	/**
	 * Validate xpath values of the resulting weather query
	 * @param weatherResult
	 * @param xpathList
	 * @throws Throwable
	 */
	public void validateWeatherResult(String weatherResult, List<XpathValue> xpathList)
			throws Throwable {
			
			for(@SuppressWarnings("unused") XpathValue xpathValue : xpathList){
				
			}	
		}
}
