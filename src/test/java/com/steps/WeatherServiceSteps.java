package com.steps;

import java.util.List;



import com.sapient.impl.WeatherServiceImpl;
import com.sapient.utils.Reporter;
import com.sapient.utils.XpathValue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.*;

public class WeatherServiceSteps {

	private static String weatherResult;

	@Given("^weather service is available$")
	public void validateWeatherServiceAvailable() throws Throwable {
		/*WeatherServiceImpl impl = new WeatherServiceImpl();
		impl.validateAvailable();*/
		
	
	}

	@When("^system checks weather for city \"([^\"]*)\" with country code \"([^\"]*)\"$")
	public void getWeather(String city, String countryCode) throws Throwable {
		/*WeatherServiceImpl impl = new WeatherServiceImpl();
		weatherResult = impl.getWeather(city, countryCode);*/
	}

	@Then("^user validates weather service sent the following JSON response:$")
	public void getWeather(List<XpathValue> xpathList) throws Throwable {
		/*WeatherServiceImpl impl = new WeatherServiceImpl();
		impl.validateWeatherResult(weatherResult, xpathList);*/
	}

}