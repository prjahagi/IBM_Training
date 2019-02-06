package com.training.functional.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC59_AddPropertyDetailsToTrash {
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
		screenShot.captureScreenShot("TC59_1_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC59_2_Login Successful_Dashboard Opened");
	}
	
	@Test(priority=2)
	public void AddPropertyToTrash() throws AWTException {
		String propertyName="New property-launch12";
		dashboardPOM.clickPropertiesLink();
		screenShot.captureScreenShot("TC59_3_All Property details displayed");
		propertiesPOM.clickOnAddNewPropertyButton();
		propertiesPOM.enterTitleToProperty(propertyName);
		propertiesPOM.enterTextToProperty("Gateway");
		screenShot.captureScreenShot("TC59_4_title and text entered");
		propertiesPOM.clickNewFeatureCheckbox();
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();
		propertiesPOM.clickNewRegionCheckbox();
		screenShot.captureScreenShot("TC59_5_Feature and Region selected");
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		propertiesPOM.clickMoveToTrashLink();
		dashboardPOM.acceptAlert();
		assertTrue(propertiesPOM.checkMovedToTrashMessageDisplayed());
		screenShot.captureScreenShot("TC59_6_Property Moved To Trash");
		propertiesPOM.clickmenuBarTrashIcon();
		String actual=propertiesPOM.returnNameOfFirstPropertyInTrash();
		assertEquals(actual, propertyName);
		screenShot.captureScreenShot("TC59_7_Display property in Trash");		
	}
}
