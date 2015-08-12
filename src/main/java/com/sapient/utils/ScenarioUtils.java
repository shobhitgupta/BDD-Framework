package com.sapient.utils;

import java.lang.reflect.Field;

import cucumber.api.Scenario;
import cucumber.runtime.junit.ExecutionUnitRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.CucumberBackground;
import cucumber.runtime.model.CucumberFeature;
import cucumber.runtime.model.CucumberScenario;

public class ScenarioUtils {
	
		private static Scenario scenario = null;

		public static String getScenarioName(){
			String returnValue = null;
			try{
				returnValue = extractScenarioName();
			}
			catch(Throwable ex){
				
			}
			return returnValue;
		}

		public static String getFeatureName(Scenario scenario){
			String returnValue = null;
			try{
				returnValue = extractFeatureName();
			}
			catch(Throwable ex){
				
			}
			return returnValue;
		}
		
		private static String extractScenarioName() throws Throwable {
			String returnValue = null;
			Field f = scenario.getClass().getDeclaredField("reporter");
            f.setAccessible(true);
            JUnitReporter reporter = (JUnitReporter) f.get(scenario);
 
            Field executionRunnerField = reporter.getClass().getDeclaredField("executionUnitRunner");
            executionRunnerField.setAccessible(true);
            ExecutionUnitRunner executionUnitRunner = (ExecutionUnitRunner) executionRunnerField.get(reporter);
 
            Field cucumberScenarioField = executionUnitRunner.getClass().getDeclaredField("cucumberScenario");
            cucumberScenarioField.setAccessible(true);
            CucumberScenario cucumberScenario = (CucumberScenario) cucumberScenarioField.get(executionUnitRunner);
            returnValue = cucumberScenario.getVisualName(); 
            return returnValue.replace("Scenario:", "").trim();
 		}

		private static String extractFeatureName() throws Throwable {
			Field f = scenario.getClass().getDeclaredField("reporter");
            f.setAccessible(true);
            JUnitReporter reporter = (JUnitReporter) f.get(scenario);
 
            Field executionRunnerField = reporter.getClass().getDeclaredField("executionUnitRunner");
            executionRunnerField.setAccessible(true);
            ExecutionUnitRunner executionUnitRunner = (ExecutionUnitRunner) executionRunnerField.get(reporter);
 
            Field cucumberScenarioField = executionUnitRunner.getClass().getDeclaredField("cucumberScenario");
            cucumberScenarioField.setAccessible(true);
            CucumberScenario cucumberScenario = (CucumberScenario) cucumberScenarioField.get(executionUnitRunner);
 
            Field cucumberBackgroundField = cucumberScenario.getClass().getDeclaredField("cucumberBackground");
            cucumberBackgroundField.setAccessible(true);
            CucumberBackground cucumberBackground = (CucumberBackground) cucumberBackgroundField.get(cucumberScenario);
 
            Field cucumberFeatureField = cucumberBackground.getClass().getSuperclass().getDeclaredField("cucumberFeature");
            cucumberFeatureField.setAccessible(true);
            CucumberFeature cucumberFeature = (CucumberFeature) cucumberFeatureField.get(cucumberBackground);	
            return cucumberFeature.getGherkinFeature().getName().trim();            
		}
		
		
		public static void setScenario(Scenario scenario){
			ScenarioUtils.scenario=scenario;
		}
		
		
		public static Scenario getscenario(){
			return ScenarioUtils.scenario;
		}
}
