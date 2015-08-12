package com.sapient.utils;

import java.util.List;

import gherkin.formatter.Formatter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

public class CustomReporter implements Formatter {

	@Override
	public void background(Background arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endOfScenarioLifeCycle(Scenario arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eof() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void examples(Examples arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void feature(Feature arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scenario(Scenario arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scenarioOutline(ScenarioOutline arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startOfScenarioLifeCycle(Scenario arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(Step arg0) {
		// TODO Auto-generated method stub
		System.out.println("beforeStep");
	}

	@Override
	public void syntaxError(String arg0, String arg1, List<String> arg2,
			String arg3, Integer arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uri(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
