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

import com.training.dataproviders.LoginDataProviders;
import com.training.dataproviders.RealEstateDataProvider;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_090
 * Test Case Description:   To verify whether application allows admin to add multiple property with all details   
 */

public class TC90_AddNewPropertyWithAllDetails_DataProvider {
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
		screenShot.captureScreenShot("TC90_1_URL Opening");
	}
	
  	//To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC90_2_Login Successful_Dashboard Opened");
	}
	// To add multiple properties with all details with Data provider
	@Test(priority=2,dataProvider = "TC90_Data", dataProviderClass = RealEstateDataProvider.class)
	public void AddNewPropertyWithAllDetails (String [] propertyDetails) throws AWTException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		propertiesPOM.mouseOverPropertiesIcon();
		propertiesPOM.clickOnAddNewPropertyLink();
		propertiesPOM.enterTitleToProperty(propertyDetails[0]);
		propertiesPOM.enterTextToProperty(propertyDetails[1]);
		propertiesPOM.clickNewFeatureCheckbox();
		screenShot.captureScreenShot("TC90_3_Property details name and text entered");
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();
		propertiesPOM.clickNewRegionCheckbox();
		propertiesPOM.enterPriceToProperty(propertyDetails[2]);
		propertiesPOM.enterPricePerSqFtToProperty(propertyDetails[3]);
		screenShot.captureScreenShot("TC90_4_Property Price details entered, feature and region checkbox selected");
		propertiesPOM.clickOnMainDetailsTab();
		propertiesPOM.enterStatusToProperty(propertyDetails[4]);
		propertiesPOM.enterLocationToProperty(propertyDetails[5]);
		propertiesPOM.enterPossessionToProperty(propertyDetails[6]);
		screenShot.captureScreenShot("TC90_5_Property Main Details entered");
		propertiesPOM.clickOnLocationTab();
		propertiesPOM.enterAddressToProperty(propertyDetails[7]);
		propertiesPOM.enterGoogleMapsAddressToProperty(propertyDetails[8]);
		propertiesPOM.enterLatitudeToProperty(propertyDetails[9]);
		propertiesPOM.enterLongitudeToProperty(propertyDetails[10]);
		screenShot.captureScreenShot("TC90_6_Property Location entered");
		propertiesPOM.clickOnDetailsTab();
		propertiesPOM.enterStorageRoomToProperty(propertyDetails[11]);
		screenShot.captureScreenShot("TC90_7_Property details entered");	
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();
		propertiesPOM.clickOnKeywordTagsCheckbox();
		screenShot.captureScreenShot("TC90_8_Property Keyword Tag selected");
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
		screenShot.captureScreenShot("TC90_9_Property Published");
	}
}
