package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.RealEstateBean;
import com.training.dao.RealEstateDAO;
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
	
	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {
		List<RealEstateBean> list=new RealEstateDAO().getRegions();		
		Object[][] result=new Object[list.size()][];
		int count=0;
		for(RealEstateBean temp:list){
			Object[] obj=new Object[4];
			obj[0]=temp.getRegionName();
			obj[1]=temp.getRegionSlug();
			obj[2]=temp.getParentRegion();
			obj[3]=temp.getRegionDescription();			
			result[count ++] = obj; 
		}		
		return result;
	}
	

}
