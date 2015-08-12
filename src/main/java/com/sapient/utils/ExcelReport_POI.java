package com.sapient.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReport_POI {

	private  Workbook wb;
	private  Sheet sh;
	private FileOutputStream fileOut;
	private File file;
	private InputStream fileIn;
	private String strPathOfExcel, strSheetName;

	private final String[] strLabels = { "FeatureName", "ScenarioName",
			"StepName", "Status", "Duration (milli seconds)" };

	// Constructor with two arguments
	public ExcelReport_POI(String strPathOfExcel, String strSheetName) {
		this.strPathOfExcel = strPathOfExcel;
		this.strSheetName = strSheetName;
	}

	// The main logic of this class is in this method
	public void createExcelFile(List<Features> features) throws EncryptedDocumentException, InvalidFormatException {
		String strSafeName;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMDDYY_hhmmss_a");
		String formattedDate = sdf.format(date);

		try {
			file = new File(strPathOfExcel);

			// if excel file is absent at the specified path, create it
			if (!file.exists()) {
				File parentDirectory = file.getParentFile();
				if (parentDirectory != null) {
					parentDirectory.mkdirs();
				}
				file.createNewFile();
				wb = new HSSFWorkbook();
			} else {
				fileIn = new FileInputStream(file);
				try {
					wb=WorkbookFactory.create(file); 
				    
				  } finally {
					//  fileIn.close();
				  }
			}


			
			
			// Excel Sheet name should be <31 characters
			int sheetCount = wb.getNumberOfSheets();
			System.out.println(sheetCount);
			/*if(sheetCount > 0){
				strSheetName += formattedDate;
				System.out.println(strSheetName);
			} */
			//strSafeName = WorkbookUtil.createSafeSheetName(strSheetName);
			
			
			sh = wb.createSheet(strSheetName);
			

			// Create Report Headers
			createReportHeaders();

			// Populate Report body
			createReportBody(features);

			// Flush & close Output Stream
			writeOutputToFileAndClose();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createReportHeaders() {

		CellStyle headerStyle = getHeaderStyle();
		Row headerRow = sh.createRow(0);

		for (int i = 0; i < strLabels.length; i++) {
			Cell col = headerRow.createCell(i);
			col.setCellStyle(headerStyle);
			col.setCellValue(strLabels[i]);
			sh.autoSizeColumn(i);
		}

	}

	private void createReportBody(List<Features> features) {
		String strCurrFeature, strCurrScenario, strOverAllStatus;
		int rowCount = 1;
		Row row;
		Cell cell;
		String featureStatus = "passed";
		long featureExecTime = 0;
		String scenarioStatus = "passed";
		long scenarioExecTime = 0;

		CellStyle featureStyle = getFeatureRowStyle();
		CellStyle scenarioStyle = getScenarioRowStyle();
		CellStyle passStyle = getPassStyle();
		CellStyle failStyle = getFailStyle();

		for (Features feature : features) {
			featureStatus = "passed";
			featureExecTime = 0;
			strCurrFeature = feature.getFeatureName();
			row = sh.createRow(rowCount++);
			Row fRow = row;
			cell = row.createCell(0);
			cell.setCellValue(strCurrFeature);
			cell.setCellStyle(featureStyle);
			sh.autoSizeColumn(0);
			for (int i = 1; i < strLabels.length; i++) { // This loop is just for formatting
				cell = row.createCell(i);
				cell.setCellStyle(featureStyle);
			}

			for (Scenario scenario : feature.getScenario()) {
				scenarioStatus = "passed";
				scenarioExecTime = 0;
				strCurrScenario = scenario.getScenarioName();
				row = sh.createRow(rowCount++);
				Row sRow = row;
				cell = row.createCell(1);
				cell.setCellValue(strCurrScenario);
				cell.setCellStyle(scenarioStyle);
				sh.autoSizeColumn(1);
				for (int i = 2; i < strLabels.length; i++) { // This loop is just for formatting
					cell = row.createCell(i);
					cell.setCellStyle(scenarioStyle);
				}

				int size = scenario.getDetails().size();

				for (int x = 0; x < size; x++) {
					row = sh.createRow(rowCount++);

					cell = row.createCell(2);
					cell.setCellValue(scenario.getDetails().get(x).get(0).toString());
					sh.autoSizeColumn(2);

					cell = row.createCell(3);
					String execStatus = scenario.getDetails().get(x).get(1).toString();
					cell.setCellValue(execStatus);
					if (execStatus.equalsIgnoreCase("PASSED"))
						cell.setCellStyle(passStyle);
					else if (execStatus.equalsIgnoreCase("FAILED")) {
						cell.setCellStyle(failStyle);
						scenarioStatus = "failed";
					} else
						System.out.println("Execution Status must be either 'passed' or 'failed'.");

					cell = row.createCell(4);
					long execTime = (long) (scenario.getDetails().get(x).get(2)) / 1000;
					cell.setCellValue(execTime); // return time in milliseconds
					scenarioExecTime += execTime;
				} // end of details loop

				if (!featureStatus.equalsIgnoreCase("failed")
						&& scenarioStatus.equalsIgnoreCase("failed"))
					featureStatus = "failed";
				featureExecTime += scenarioExecTime;
				cell = sRow.createCell(3);
				cell.setCellValue(scenarioStatus);
				cell = sRow.createCell(4);
				cell.setCellValue(scenarioExecTime);

			} // end of scenario loop

			// featureStatus = scenarioStatus;
			cell = fRow.createCell(3);
			cell.setCellValue(featureStatus);
			cell = fRow.createCell(4);
			cell.setCellValue(featureExecTime);

		} // end of feature loop

		// End of execution summary
		row = sh.createRow(++rowCount);
		cell = row.createCell(0);
		cell.setCellValue("( ----- END OF REPORT ----- )");
	}

	private  CellStyle getHeaderStyle() {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setAlignment(CellStyle.ALIGN_CENTER);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);

		return style;
	}

	private  CellStyle getFeatureRowStyle() {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		return style;
	}

	private  CellStyle getScenarioRowStyle() {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		return style;
	}

	private  CellStyle getPassStyle() {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		return style;
	}

	private  CellStyle getFailStyle() {
		CellStyle style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);

		return style;
	}

	private void writeOutputToFileAndClose() {
		try {
			wb.write(fileOut);
			fileOut.close();
			//fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
