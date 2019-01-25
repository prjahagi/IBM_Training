package com.training.functional.tests;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.pom.DashboardPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;


public class MovePropertiesToTrash {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private PropertiesPOM propertiesPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test(priority=0)
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		dashboardPOM = new DashboardPOM(driver);
		propertiesPOM= new PropertiesPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		screenShot.captureScreenShot("TC26_1_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC26_2_Login Successful");
	}
	
	@Test(priority=2)
	public void movePropertiesToTrash() throws InterruptedException {
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickAllPropertiesLink();
		screenShot.captureScreenShot("TC26_3_All properties Screen Opened");
		propertiesPOM.searchProperty("New Launch");
		propertiesPOM.clickPropertyCheckbox();
		propertiesPOM.clickOnBulkActionList();
		propertiesPOM.selectMoveToTrash();	
		screenShot.captureScreenShot("TC26_4_Property Selected to Delete");
		propertiesPOM.clickApplyButton();
		Thread.sleep(2000);
		assertTrue(propertiesPOM.checkMovedToTrashMessageDisplayed());
		screenShot.captureScreenShot("TC26_5_Property Deleted");	
	}
 
}
