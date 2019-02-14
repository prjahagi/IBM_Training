package com.training.functional.tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_060
 * Test Case Description:   To verify whether application allows admin to add new property with all details 
 */

public class TC60_AddNewPropertyWithAllDetails {
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
  
	//To perform the initial browser setup
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
		screenShot.captureScreenShot("TC60_1_URL Opening");
	}
	
    //To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC60_2_Login Successful_Dashboard Opened");
	}
	
	//Add New property on Real estate with all the details of property
	@Test(priority=2)
	public void AddNewPropertyWithAllDetails () throws AWTException, InterruptedException {
		String propertyName="New property detailed";
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		propertiesPOM.mouseOverPropertiesIcon();
		propertiesPOM.clickOnAddNewPropertyLink();
		propertiesPOM.enterTitleToProperty(propertyName);
		propertiesPOM.enterTextToProperty("new launch");
		propertiesPOM.clickNewFeatureCheckbox();
		screenShot.captureScreenShot("TC60_3_Property details name and text entered");
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();
		propertiesPOM.clickNewRegionCheckbox();
		propertiesPOM.enterPriceToProperty("50000.00");
		propertiesPOM.enterPricePerSqFtToProperty("200.00");
		screenShot.captureScreenShot("TC60_4_Property Price details entered, feature and region checkbox selected");
		propertiesPOM.clickOnMainDetailsTab();
		propertiesPOM.enterStatusToProperty("New");
		propertiesPOM.enterLocationToProperty("Electronic city");
		propertiesPOM.enterPossessionToProperty("immediate");
		screenShot.captureScreenShot("TC60_5_Property Main Details entered");
		propertiesPOM.clickOnLocationTab();
		propertiesPOM.enterAddressToProperty("yeshwanthapur");
		propertiesPOM.enterGoogleMapsAddressToProperty("yeshwanthapur");
		propertiesPOM.enterLatitudeToProperty("120");
		propertiesPOM.enterLongitudeToProperty("56");
		screenShot.captureScreenShot("TC60_6_Property Location entered");
		propertiesPOM.clickOnDetailsTab();
		propertiesPOM.enterStorageRoomToProperty("2");
		screenShot.captureScreenShot("TC60_7_Property details entered");	
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();
		propertiesPOM.clickOnKeywordTagsCheckbox();
		screenShot.captureScreenShot("TC60_8_Property Keyword Tag selected");
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		dashboardPOM.scrollUp();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		propertiesPOM.clickOnPublishButton();
		Thread.sleep(3000);
		assertTrue(propertiesPOM.checkPropertyPublishedMessageDisplayed());
		screenShot.captureScreenShot("TC60_9_Property Published");
	}
}
