package com.sapient.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
	
	
	static List<Features> feature=null;
	public static void main(String[] args) throws Exception {
		JSONParser parser=new JSONParser();
		try {
			
			Object obj=parser.parse(new FileReader("data\\cucumber.json"));
			JSONArray jsonObject=(JSONArray) obj;
			jsonObject.size();
			int i=0;
			feature=new ArrayList<Features>();
			while(i<jsonObject.size()){
				
				
				JSONObject obj1=(JSONObject) jsonObject.get(i);
				obj1.get("elements");
				//System.out.println(obj1.get("id").toString());
				feature.add(new Features(obj1.get("name").toString()));
				
				
				
				JSONArray scenarioElements=(JSONArray) obj1.get("elements");
				feature.get(i).setScenario(new ArrayList<Scenario>());
				for(int j=0;j<scenarioElements.size();j++){
				  JSONObject scenarioObject=(JSONObject)scenarioElements.get(j);
				  String scenarioName=scenarioObject.get("name").toString();
				  feature.get(i).getScenario().add(new Scenario(scenarioName));
				  feature.get(i).getScenario().get(j).setDetails(new ArrayList<ArrayList<Object>>());
				  JSONArray steps=(JSONArray) scenarioObject.get("steps");
				  feature.get(i).getScenario().get(j).getDetails().addAll(scenarioDetails(steps));
				  
				}
				
				i++;
				
			}
	
		//printData(feature);
		printExcelReport(feature);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printData(List<Features> features){
		String featureStatus="passed";
		long featureDuration = 0;
		long scenarioTime = 0;
		String scenarioStatus = null;
		
		for(Features feature : features){
			featureDuration=0;
			featureStatus="passed";
			System.out.println("Feature Name: " + feature.getFeatureName());
			
			for(Scenario scenario:feature.getScenario()){
				int i=scenario.getDetails().size();
				scenarioTime=0;
				scenarioStatus="passed";
				System.out.println("Scenario Name: " + scenario.getScenarioName());
				
				for(int x=0;x<i;x++){
					System.out.println("Step Name : " + scenario.getDetails().get(x).get(0).toString());
					System.out.println("Step Result: "+scenario.getDetails().get(x).get(1).toString());
					System.out.println("Step Duration: "+scenario.getDetails().get(x).get(2).toString());
					scenarioTime=scenarioTime + (long)scenario.getDetails().get(x).get(2);
					if(scenarioStatus!="failed" && scenario.getDetails().get(x).get(1).equals("failed")){
						scenarioStatus="failed";
					}
				}
				System.out.println("scenario duration :" + scenarioTime);
				System.out.println("scenarioStatus :" + scenarioStatus);
				featureDuration=featureDuration+ scenarioTime;
				if(featureStatus!="failed" && scenarioStatus.equals("failed")){
					featureStatus="failed";
				}
				
			}
			System.out.println("Feature Status: " + featureStatus);
			System.out.println("Feature Duration: " + featureDuration);
		}
	}
	
	public static void printExcelReport(List<Features> feature) throws EncryptedDocumentException, InvalidFormatException{
		//ExcelReport_POI xls = new ExcelReport_POI("c:/DeleteThis/2/Example2.xls", "Result");
		ExcelReport_POI xls = new ExcelReport_POI("src/Reports/Example2.xls", "Hello");
		xls.createExcelFile(feature);
	}
	
	
	private static List<ArrayList<Object>> scenarioDetails(JSONArray jsonArray) throws Exception{
		
		List<ArrayList<Object>> data=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> stepData=null;
		for(int i=0;i<jsonArray.size();i++){
			stepData=new ArrayList<Object>();
			JSONObject resultData=(JSONObject) jsonArray.get(i);
			JSONObject result=(JSONObject) resultData.get("result");
			Long duration=Long.parseLong(result.get("duration").toString());
			String status=result.get("status").toString();
			String stepName=resultData.get("name").toString();
			stepData.add(0, stepName );
			stepData.add(1, status);
			stepData.add(2, duration);
			data.add(stepData);
		  	
		}
		return data;
		
	}

}
