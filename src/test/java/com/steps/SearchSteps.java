package com.steps;

import com.sapient.impl.SearchImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchSteps {
	
	 @Given("^user is on google search page$")
	    public void I_am_on_the_google_search_page() throws Throwable {
	        SearchImpl search = new SearchImpl();
	        search.navigateToSearchPage();
	    }
	
	  @When("^user search for \"([^\"]*)\"$")
	     public void user_search_for(String searchText) throws Throwable {
	        
	       /*  SearchImpl gp=new SearchImpl();
	         gp.searchFor(searchText);*/
	        
	     }

	   @Then("^result should display the link \"([^\"]*)\"$")
	     public void result_should_display_the_link(String arg1) throws Throwable {
	      /* SearchImpl gp=new SearchImpl();
	       gp.assertSearchResultsFound(arg1);*/
	     }
	
}
