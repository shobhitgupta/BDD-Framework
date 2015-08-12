package com.sapient.utils;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

import java.util.List;

public class MyReporter implements gherkin.formatter.Formatter,
gherkin.formatter.Reporter {

	@Override
	public void after(Match arg0, Result arg1) {
		// TODO Auto-generated method stub
		
		System.out.println("hjhg");
	}

	@Override
	public void before(Match arg0, Result arg1) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void embedding(String arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		System.out.println("embed");
	}

	@Override
	public void match(Match arg0) {
		// TODO Auto-generated method stub
		System.out.println("match");
	}

	@Override
	public void result(Result arg0) {
		// TODO Auto-generated method stub
		System.out.println("result");
	}

	@Override
	public void write(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("write");
		
	}

	@Override
	public void background(Background arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("");
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		System.out.println("done");
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
	public void step(Step arg0) {
		// TODO Auto-generated method stub
		
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
