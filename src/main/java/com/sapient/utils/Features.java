package com.sapient.utils;

import java.util.ArrayList;
import java.util.List;

public class Features {

	private String featureName;
	private List<Scenario> scenario;

	public Features(String featureName) {
		this.setFeatureName(featureName);
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public List<Scenario> getScenario() {
		return scenario;
	}

	public void setScenario(List<Scenario> scenario) {
		this.scenario = scenario;
	}

}

class Scenario {

	private String scenarioName;
	private ArrayList<ArrayList<Object>> details;

	public Scenario(String scenarioName) {
		this.setScenarioName(scenarioName);
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public ArrayList<ArrayList<Object>> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<ArrayList<Object>> details) {
		this.details = details;
	}

}
