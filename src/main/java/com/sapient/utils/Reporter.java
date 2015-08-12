package com.sapient.utils;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * @author sgu109
 *
 */
/**
 * @author sgu109
 * 
 */
public class Reporter {
	private static SoftAssertions verification = null;

	/**
	 * Compares two strings and fails the test if they are not equal
	 * 
	 * @param actual
	 * @param expected
	 * @param message
	 * @throws Exception
	 */
	public static void assertValue(String actual, String expected,
			String message) throws Exception {
		assertThat(actual).as(message).isEqualTo(expected);
	}

	/**
	 * Check whether a string contains the expected characters
	 * 
	 * @param actual
	 * @param expected
	 * @param message
	 * @throws Exception
	 */
	public static void assertContains(String actual, String expected,
			String message) throws Exception {
		assertThat(actual).as(message).contains(expected);
	}

	/**
	 * Check whether the boolean condition is true or not
	 * 
	 * @param message
	 * @param condition
	 * @throws Exception
	 */
	public static void assertCondition(String message, boolean condition)
			throws Exception {
		assertThat(condition).as("message");
	}

	/**
	 * Checks whether an item is present in the list or not
	 * 
	 * @param list
	 * @param item
	 * @throws Exception
	 */
	public static void assertListcontains(List<Object> list, Object item)
			throws Exception {
		assertThat(list).contains(item);
	}

	/**
	 * Reset the soft assertions of AssertJ .
	 */
	public static void resetVerification() {
		verification = new SoftAssertions();

	}

	// Soft Assertion of checking the two strings
	public static void verifyValue(String actual, String expected,
			String message) throws Exception {
		verification.assertThat(actual).as(message).isEqualTo(expected);
	}

	// Soft Assertion to check whether String contains the expected characters
	public static void verifyContains(String actual, String expected,
			String message) throws Exception {
		verification.assertThat(actual).as(message).contains(expected);
	}

	/**
	 * Soft Assertion to check the boolean condition
	 * 
	 * @param message
	 * @param condition
	 * @throws Exception
	 */
	public static void verifyCondition(String message, boolean condition)
			throws Exception {
		verification.assertThat(condition).as("message");
	}

	/**
	 * Check for all the soft assertions and if there is any will mark the
	 * scenario as failed
	 */
	public static void verifyAll() {
		verification.assertAll();
	}

	/**
	 * In a JSON response it check for the value at a particular node
	 * 
	 * @param res
	 * @param node
	 * @param value
	 */
	public static void assertResponse(Response res, String node, Object value) {
		res.then().body(node, equalTo(value));
	}

	/**
	 * Allows user to write some text in the scenario which will be visible in
	 * report
	 * 
	 * @param message
	 * @throws Exception
	 */
	public static void write(String message) throws Exception {
		ScenarioUtils.getscenario().write(message);
	}

	/**
	 * Captures the screenshot and add it to the reports
	 * 
	 * @throws Exception
	 */
	public static void addScreenshot() throws Exception {
		ScenarioUtils.getscenario().embed(WebDriverUtils.takeScreenShot(),
				"image/png");
	}

	public static String saveResponseAsFile(Response res, String fileName)
			throws IOException {
		String responseType = res.getContentType();
		String fileType = null;
		if (responseType.contains("json")) {
			fileType = ".json";
		} else if (responseType.contains("xml")) {
			fileType = ".xml";

		}
		String jsonString = res.asString();
		String fileLoc = AppConfUtils.getResultsDataDir() + File.separator
				+ fileName + "_" + System.currentTimeMillis() + fileType;
		File resultsFile = new File(fileLoc);
		FileUtils.writeStringToFile(resultsFile, jsonString);
		return fileLoc;
	}
	
	public static String saveResponseAsText(Response res, String fileName)
			throws IOException {
		
	
		String jsonString = res.asString();
		String fileLoc = AppConfUtils.getResultsDataDir() + File.separator
				+ fileName + "_" + System.currentTimeMillis() + ".txt";
		File resultsFile = new File(fileLoc);
		FileUtils.writeStringToFile(resultsFile, jsonString);
		return fileLoc;
	}

	public static void embedResponse(Response res, String responseName)
			throws IOException {
		String fileName = saveResponseAsText(res, responseName);
		ScenarioUtils.getscenario().write(
				"<a href=" + fileName + " target=_blank>" + responseName
						+ "</a>");
	}

}
