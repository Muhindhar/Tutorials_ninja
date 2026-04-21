package com.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class ExcelData {
	@DataProvider(name="validData",parallel = true)
	public Object[][] excelDataProvider() throws IOException{
		Object[][] arrObj =getExcelData("C:\\Users\\Muhindhar S V\\eclipse-workspace\\Website\\src\\test\\resources\\Qafox.xlsx","Sheet1");
		return arrObj;
	}
	@DataProvider(name="InvalidData",parallel = true)
	public Object[][] excelDataProvider1() throws IOException{
		Object[][] arrObj =getExcelData("C:\\Users\\Muhindhar S V\\eclipse-workspace\\Website\\src\\test\\resources\\Qafox.xlsx","Sheet2");
		return arrObj;
	}
	public String[][] getExcelData(String filename,String sheetName)throws IOException{
		 String[][] data=null;
		 try {
			 FileInputStream fs=new FileInputStream(filename);
			 XSSFWorkbook workbook=new XSSFWorkbook(fs);
			 XSSFSheet sheet=workbook.getSheet(sheetName);
			 XSSFRow row=sheet.getRow(0);
			 int noOfRows=sheet.getPhysicalNumberOfRows();
			 int noOfCols=row.getLastCellNum();
			 Cell cell;
			 data=new String[noOfRows-1][noOfCols];
			 for(int i=1;i<noOfRows;i++) {
				 for( int j=0;j<noOfCols;j++) {
					 row=sheet.getRow(i);
					 cell=row.getCell(j);
					 data[i-1][j]=cell.getStringCellValue();
				 }
			 }
		 }catch (Exception e) {
			 System.out.println(e.getMessage());
		 }
		 return data;
	 }

}