package com.sapient.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sapient.framework.BasePage;



public class GoogleSearchPage extends BasePage {
 
	private WebDriver driver;

	@CacheLookup
	@FindBy(name="q")
	private WebElement searchInputBox ;


	@FindBy(name="btnG")
	private WebElement searchButton;


	public GoogleSearchPage(WebDriver driver) {
		// TODO Auto-generated constructor stub		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public void searchFor(String searchCriteria) {
		enterSearchCriteria(searchCriteria);
		search();
	}

	private void enterSearchCriteria(String searchCriteria) {
	
		searchInputBox.clear();
		searchInputBox.sendKeys(searchCriteria);
	}

	private void search() {
		searchButton.click();
	}

	public boolean getSearchResults(String linkText) {
		try{
			driver.findElement(By.partialLinkText(linkText));
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}
}
