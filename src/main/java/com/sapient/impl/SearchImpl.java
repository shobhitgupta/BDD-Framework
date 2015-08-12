package com.sapient.impl;

import org.junit.Assert;

import com.sapient.pages.GoogleSearchPage;
import com.sapient.utils.Reporter;
import com.sapient.utils.WebDriverUtils;

public class SearchImpl {


	public void navigateToSearchPage() throws Exception {
		
		WebDriverUtils.getDriver().get("http://www.google.com");

	}

	public void searchFor(String searchCriteria) throws Exception {
		GoogleSearchPage GoogleSearchPage = new GoogleSearchPage(WebDriverUtils.getDriver());
		GoogleSearchPage.searchFor(searchCriteria);
	}

	public void assertSearchResultsFound(String linkText) throws Exception {
		GoogleSearchPage GoogleSearchPage = new GoogleSearchPage(WebDriverUtils.getDriver());
		boolean searchResults = GoogleSearchPage.getSearchResults(linkText);
		Assert.assertTrue("Checking that link is present", searchResults);
		//Reporter.assertCondition("Checking that link is present", SearchResults);
		

	}

}
