package com.training.dataproviders;

import org.testng.annotations.DataProvider;

import com.training.readexcel.ApachePOIExcelRead;

public class RealEstateDataProvider {
	
	@DataProvider(name = "TC87_Data")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\PrachiJahagirdar\\Documents\\screenshots\\Selenium_DataSheet.xlsx"; 
		String sheetName="Feature";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName); 
	}
	
	@DataProvider(name = "TC89_Data")
	public Object[][] getExcelData1(){
		String fileName ="C:\\Users\\PrachiJahagirdar\\Documents\\screenshots\\Selenium_DataSheet.xlsx"; 
		String sheetName="Region";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName); 
	}
	
	@DataProvider(name = "TC90_Data")
	public Object[][] getExcelData2(){
		String fileName ="C:\\Users\\PrachiJahagirdar\\Documents\\screenshots\\Selenium_DataSheet.xlsx"; 
		String sheetName="Property";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName); 
	}

}
