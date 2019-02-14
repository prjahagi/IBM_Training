package com.training.functional.tests;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.dataproviders.RealEstateDataProvider;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.FeaturesPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_087
 * Test Case Description:   To verify whether application allows admin to create multiple property details based on the Feature created   
 */
public class TC87_CreatePropertyWithNewFeature_DataProvider {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private PropertiesPOM propertiesPOM;
	private FeaturesPOM featuresPOM;
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
		featuresPOM =new FeaturesPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		screenShot.captureScreenShot("TC87_01_URL Opening");
	}
	//To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC87_02_Login Successful_Dashboard Opened");
	}
	//Add multiple features with data provider
	@Test(priority=2,dataProvider = "TC87_Data", dataProviderClass = RealEstateDataProvider.class)
	public void addNewFeature(String featureName, String featureSlug, String featureDescription, String propertyName, String propertyText) {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickFeaturesLink();
		screenShot.captureScreenShot("TC87_03_Feature Screen Opened");
	    featuresPOM.enterDataInFeatureNameTextbox(featureName);
		featuresPOM.enterDataInFeatureSlugbox(featureSlug);
		featuresPOM.enterDetailsInFeatureDescription(featureDescription);
		screenShot.captureScreenShot("TC87_04_Feature Details entered");
		featuresPOM.clickAddNewFeatureButton();
		featuresPOM.scrollScreenToSearchBox();
		featuresPOM.searchFeature(featureName);
		String actualResult=featuresPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(featureName));
		screenShot.captureScreenShot("TC87_05_Feature Added");
	}
	//Create multiple properties with newly added features with Data provider
	@Test(priority=3,dataProvider = "TC87_Data", dataProviderClass = RealEstateDataProvider.class)
	public void CreateNewProperty(String featureName, String featureSlug, String featureDescription, String propertyName, String propertyText) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		propertiesPOM.mouseOverPropertiesIcon();
		propertiesPOM.clickOnAddNewPropertyLink();
		propertiesPOM.enterTitleToProperty(propertyName);
		propertiesPOM.enterTextToProperty(propertyText);
		propertiesPOM.clickNewFeatureCheckbox(featureName);
		screenShot.captureScreenShot("TC87_06_property details entered and selected feature");
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		propertiesPOM.clickOnPublishButton();
		Thread.sleep(3000);
		screenShot.captureScreenShot("TC87_07_property Added");
		assertTrue(propertiesPOM.checkPropertyPublishedMessageDisplayed());
	}
}
