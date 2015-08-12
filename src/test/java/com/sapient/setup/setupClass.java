package com.sapient.setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sapient.utils.Reporter;
import com.sapient.utils.ScenarioUtils;
import com.sapient.utils.SetupUtils;
import com.sapient.utils.WebDriverUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * @author sgu109
 *
 */
public class setupClass {

	Logger logger = null;
	
	
	@Before()
	public void initialSetupNoTag(Scenario scenario) {
		if (logger == null) {
			logger = setupLogger();
		}
		Reporter.resetVerification();
		ScenarioUtils.setScenario(scenario);
		logger = (Logger) SetupUtils.getState("logger");
		logger.info("starting testing scenario "
				+ ScenarioUtils.getScenarioName());

	}

	/**
	 * Initial Setup like logger , reset soft assertions etc for web service related tests
	 * @param scenario
	 */
	@Before("@service")
	public void initialSetup(Scenario scenario) {
		if (logger == null) {
			logger = setupLogger();
		}
		Reporter.resetVerification();
		ScenarioUtils.setScenario(scenario);
		logger = (Logger) SetupUtils.getState("logger");
		logger.info("starting service testing scenario "
				+ ScenarioUtils.getScenarioName());

	}

	/**
	 * Initialize the browser, logger and do the initial settings for browser based automation tests
	 * @param scenario
	 * @throws Exception
	 */
	@Before("@browser")
	public void setUp(Scenario scenario) throws Exception {
		if (logger == null) {
			logger = setupLogger();
		}
		Reporter.resetVerification();
		ScenarioUtils.setScenario(scenario);
		logger = (Logger) SetupUtils.getState("logger");
		logger.info("starting browser testing scenario "
				+ ScenarioUtils.getScenarioName());
		//WebDriverUtils.getDriver();

	}

	
	/**
	 * Executed after the scenario has been completed for the browser based automation
	 * @param scenario
	 * @throws Exception
	 */
	@After("@browser")
	public void afterSetup(Scenario scenario) throws Exception {
		logger = (Logger) SetupUtils.getState("logger");
		if (scenario.isFailed()) {
			if (WebDriverUtils.isTakeScreenShot()) {
				logger.info("Scenario: " + ScenarioUtils.getScenarioName()
						+ " Failed. Taking screenshot");
				scenario.embed(WebDriverUtils.takeScreenShot(), "image/png");

			}
		}

		//WebDriverUtils.closeWebDriver();

	}

	
	
	/**
	 * Initialize the logger instance as per the log4j configuration properties file
	 * @return
	 */
	private Logger setupLogger() {

		logger = Logger.getLogger("testExecutionLogger");
		String log4JPropertyFile = "src/main/resources/log4j.properties";
		Properties prop = new Properties();
		String folderName = new SimpleDateFormat("yyMMdd").format(new Date());
		String fileName = new SimpleDateFormat("HH.mm.ss").format(new Date());
		fileName.replace(".", "\\");

		try {
			prop.load(new FileInputStream(log4JPropertyFile));
			prop.setProperty("log4j.appender.LogToFile.File", "build//logs//"
					+ folderName + "//log_" + fileName + ".log");
			PropertyConfigurator.configure(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SetupUtils.saveState("logger", logger);
		return logger;

	}

}
