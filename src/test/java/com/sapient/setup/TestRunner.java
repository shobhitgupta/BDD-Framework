package com.sapient.setup;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	    plugin     =   {"html:target/cucumber" , "json:target/cucumber.json" },
		features   =   {"src/test/resources/features"},
		glue       =   {"com.steps","com.sapient.setup"},
		//tags       = {"@service"},
		dryRun     =   true,
		strict     =   false,
		monochrome =   false
		)
public class TestRunner {
	
}