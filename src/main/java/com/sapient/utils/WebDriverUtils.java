package com.sapient.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.apache.commons.lang.StringUtils;

public class WebDriverUtils {
	private static WebDriver driver = null;
	private static final String configFile = "Config.properties";
	private static PropertiesFileUtils prop;
	private static String browser;

	public static WebDriver getNewDriver() throws Exception {
		WebDriver driver = null;

		try {
			prop = new PropertiesFileUtils("src/main/resources/" + configFile);
			browser = prop.propertiesFile.getProperty("browser");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Properties File Not found - Please check the path");
		}

		Collection<String> browserName = ScenarioUtils.getscenario()
				.getSourceTagNames();
		Iterator<String> browserList = browserName.iterator();
		boolean flag=false;
		while (browserList.hasNext()) {
			if(flag==true){
				break;
			}
			switch (browserList.next().replace("@", "")) {
			case "firefox":
				browser = "firefox";
				flag=true;
				break;
			case "IE":
				browser = "IE";
				flag=true;
				break;
			case "chrome":
				browser = "chrome";
				flag=true;

			}
		}

		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver",
					"src/main/resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:

			System.exit(0);
			break;
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	/**
	 * 
	 * @param driver
	 */
	public static void closeWebDriver() {
		if (driver != null) {
			driver.quit();
		}
		driver = null;
	}

	public static WebDriver getDriver() throws Exception {
		if (driver == null) {
			driver = getNewDriver();
		}
		return driver;
	}

	public static boolean isTakeScreenShot() {
		String flag = prop.propertiesFile.getProperty("takescreenshot");
		if (StringUtils.equals(flag, "true")) {
			return true;
		} else {
			return false;
		}
	}

	public static byte[] takeScreenShot() {

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
