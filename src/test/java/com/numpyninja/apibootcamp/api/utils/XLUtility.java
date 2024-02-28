package com.numpyninja.apibootcamp.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static FileInputStream fi;
	public FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;   
	public File jsonFile;


	//data driven through feature file
	/*private static int getDataRow(String dataKey, int dataColumn) {
		int rowCount = sheet.getLastRowNum();
		for(int row=0; row<= rowCount; row++){
			if(XLUtility.getCellData(row, dataColumn).equalsIgnoreCase(dataKey)){
				return row;
			}
		}
		return 0;		
	}

	private static String getCellData(int rowNumb, int colNumb) {
		cell = sheet.getRow(rowNumb).getCell(colNumb);

		if(cell.getCellType() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING);
		}
		String cellData = cell.getStringCellValue();
		return cellData;
	}*/

	public static Map<String, String> getData(String sheetName, String fileName, int rowNumber) throws Exception {

		Map<String, String> dataMap = new HashMap<String, String>();
		fi=new FileInputStream(fileName);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
	
		int rowCount = sheet.getLastRowNum();
		if (rowCount == 0) {
			throw new Exception("NO DATA FOUND for dataKey: "+sheet);
		}
		int columnCnt = sheet.getRow(0).getLastCellNum();
     
		for(int i=0;i<columnCnt;i++) {
			cell = sheet.getRow(rowNumber).getCell(i);
			String cellData = null; 
			if (cell != null) {
				if(cell.getCellType() == CellType.NUMERIC) {
					cell.setCellType(CellType.STRING);
				}
				cellData = cell.getStringCellValue();
			}
			dataMap.put(sheet.getRow(0).getCell(i).getStringCellValue(), cellData);
		}
		return dataMap;
	}


	public static int getPositiveScenarioCount(String sheetName, String file) throws IOException {
		fi=new FileInputStream(file);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	       
		
	}


}