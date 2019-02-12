package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.RealEstateBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		/*List<RealEstateBean> list=new ELearningDAO().getLogins();
		
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
		*/	
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:\\Users\\PrachiJahagirdar\\Documents\\screenshots\\Selenium_DataSheet.xlsx"; 
		String sheetName="Login";
		return new ApachePOIExcelRead().getExcelContent(fileName, sheetName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:\\Users\\PrachiJahagirdar\\Documents\\screenshots\\Selenium_DataSheet.xlsx", "Sheet1"); 
	}
}
