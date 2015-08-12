package com.sapient.utils;

import org.openqa.selenium.By;

public class ORConfiguration {
	
	
	/**
	 * Return a link element based on link text
	 * @param arg1
	 * @return
	 */
	public static By FindLinkByText(String arg1) {
		return By.xpath("//a[contains(text(), '" + arg1 + "')]");
	}

	public static By FindByText(String arg1) {
		return By.xpath("//*[contains(., '" + arg1 + "')]");
	}

	public static By FindButtonByText(String arg1) {
		return By.xpath("//button[@type='submit' and contains(., '" + arg1 + "')]");
	}

	public static By FindTableDataWithText(String arg1) {
		return By.xpath("//tr/td[text()='" + arg1 + "']");
	}
	
	public static By FindSpanByText(String arg1) {
		return By.xpath("//span[contains(text(), '" + arg1 + "')]");
	}
	
	public static By FindSpanById(String arg1) {
		return By.xpath("//span[contains(@id, '" + arg1 + "')]");
	}
	
	public static By FindInputByName(String arg1){
		return By.xpath("//input[contains(@name, '" + arg1 + "')]");
	}
	
	public static By FindInputById(String arg1){
		return By.xpath("//input[contains(@id, '" + arg1 + "')]");
	}
	

}
