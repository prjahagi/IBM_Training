package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.LoginBean;
import com.training.bean.RealEstateBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class RealEstateDAO {
	
	Properties properties; 
	
	public RealEstateDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new RealEstateDAO().getRegions().forEach(System.out :: println);
	}

	public List<RealEstateBean> getRegions() {
		String sql = properties.getProperty("get.regions"); 
		
		GetConnection gc  = new GetConnection(); 
		List<RealEstateBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<RealEstateBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				RealEstateBean temp= new RealEstateBean();
				temp.setRegionName(gc.rs1.getString(1));
				temp.setRegionSlug(gc.rs1.getString(2));
				temp.setParentRegion(gc.rs1.getString(3));
				temp.setRegionDescription(gc.rs1.getString(4));
				list.add(temp);	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return list;
	}
	
	
}
