package com.sapient.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtil {
	private static Map<Object, Object> storeValue = new HashMap<Object, Object>();
	private static Logger logger = (Logger) SetupUtils.getState("logger");

	private static WebDriver driver() throws Exception {
		return WebDriverUtils.getDriver();
	}

	public static void navigateTo(String url) throws Exception {
		logger.info("navigating to " + url);
		driver().get(url);

	}

	public static WebElement getObject(By by) throws Exception {

		try {

			WebElement Element = (new WebDriverWait(driver(), 10))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
			logger.info("Found Element with description " + by.toString());
			return Element;
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			logger.info("Element with description not found " + by.toString());
			throw new Exception("Element is not found. Please check - "
					+ e.getMessage());
		}

	}

	public static void click(By by) throws Exception {
		getObject(by).click();

	}

	public static void sendkeys(By by, String value) throws Exception {
		getObject(by).clear();
		getObject(by).sendKeys(value);
	}

	public static void doubleClick(By by) throws Exception {
		Actions action = new Actions(driver());
		action.doubleClick(getObject(by)).build().perform();
	}

	public static void rightClick(By by) throws Exception {
		Actions action = new Actions(driver());
		action.contextClick(getObject(by)).build().perform();
	}

	public static boolean validateValue(By by, String attribute, String expected)
			throws Exception {

		if (getObject(by).getAttribute(attribute).equals(expected)) {
			return true;
		}
		return false;
	}

	public static void refresh() throws Exception {
		logger.info("testing click");
		driver().navigate().refresh();
	}

	public static void back() throws Exception {
		driver().navigate().back();
	}

	public static void forward() throws Exception {
		driver().navigate().forward();
	}

	public static void saveValue(String key, String value) throws Exception {
		storeValue.put(key, value);
	}

	public static String getValue(String key) throws Exception {
		return (String) storeValue.get(key);
	}

	public static void selectbyText(By by, String text) throws Exception {
		new Select(getObject(by)).selectByVisibleText(text);
	}

	public static void hover(By by) throws Exception {

		new Actions(driver()).moveToElement(getObject(by)).build().perform();

	}

	public static void switchFrame(String frameNumber) throws Exception {

		driver().switchTo().frame(Integer.parseInt(frameNumber));

	}

	public static void switchDefault() throws Exception {
		driver().switchTo().defaultContent();
	}

	public static boolean verifyTitle(String value) throws Exception {
		String title = driver().getTitle();
		if (title.contains(value)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean verifyElementPresent(By by) throws Exception {
		try {
			driver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}

	}

	public static boolean validateEnabled(By by) throws Exception {

		if (driver().findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public static void accetpAlert() throws Exception {
		driver().switchTo().alert().accept();

	}

	public static void dismissAlert() throws Exception {

		driver().switchTo().alert().dismiss();

	}
}
