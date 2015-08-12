package com.sapient.application;

public class WeatherApplication {
	
	private String city;
	private String country;
	private double temperature;
	private double latitude;
	private double longitude;
	
	public void setCity(String city){
		this.city=city;	
	}
	
	public void setCountry(String country){
		this.country=country;
	}
	
	public double gettemperature() throws Exception{
		if(city=="London" && country=="UK"){
		temperature=11.0;
		}else{
			throw new Exception("City and Country not available in database");
		}
		return temperature;
		
	}
	
	public double getLatitude() throws Exception{
		if(city=="London" && country=="UK"){
			temperature=51.51;
			}else{
				throw new Exception("City and Country not available in database");
			}
			return temperature;
	}
	

}
