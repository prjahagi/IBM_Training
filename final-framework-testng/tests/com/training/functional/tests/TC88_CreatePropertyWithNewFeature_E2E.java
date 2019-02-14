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
import com.training.pom.FeaturesPOM;
import com.training.pom.HomePagePOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_088
 * Test Case Description:   To verify whether application allows admin to create property details based on the Feature created & added property get displayed on home screen for user  
 */
public class TC88_CreatePropertyWithNewFeature_E2E {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private HomePagePOM homepagePOM;
	private PropertiesPOM propertiesPOM;
	private FeaturesPOM featuresPOM;
	private static Properties properties;
	private ScreenShot screenShot;	
	private String propertyName;
	private String featureName;
	
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
		homepagePOM= new HomePagePOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		screenShot.captureScreenShot("TC88_01_URL Opening");
	}
	//To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC88_02_Login Successful_Dashboard Opened");
	}
	// To add new feature with all details
	@Test(priority=2)
	public void addNewFeature() {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickFeaturesLink();
		screenShot.captureScreenShot("TC88_03_Feature Screen Opened");
		featureName="featuretest123";
		featuresPOM.enterDataInFeatureNameTextbox(featureName);
		featuresPOM.enterDataInFeatureSlugbox("bookingtest");
		featuresPOM.enterDetailsInFeatureDescription("test tc");
		screenShot.captureScreenShot("TC88_04_Feature Details entered");
		featuresPOM.clickAddNewFeatureButton();
		featuresPOM.scrollScreenToSearchBox();
		featuresPOM.searchFeature(featureName);
		String actualResult=featuresPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(featureName));
		screenShot.captureScreenShot("TC88_05_Feature Added");
	}
	//Create New property with newly added feature
	@Test(priority=3)
	public void CreateNewProperty() throws InterruptedException {
		propertyName="Test Property2";
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		propertiesPOM.mouseOverPropertiesIcon();
		propertiesPOM.clickOnAddNewPropertyLink();
		propertiesPOM.enterTitleToProperty(propertyName);
		propertiesPOM.enterTextToProperty("test123");
		propertiesPOM.clickNewFeatureCheckbox(featureName);
		screenShot.captureScreenShot("TC88_06_property details entered and selected feature");
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		propertiesPOM.clickOnPublishButton();
		Thread.sleep(3000);
		screenShot.captureScreenShot("TC88_07_property Added");
		assertTrue(propertiesPOM.checkPropertyPublishedMessageDisplayed());
	}
	// Search property on home page and open property page.
	@Test(priority=4)
	public void SearchPropertyOnHomePage() throws AWTException {
		dashboardPOM.mouseOverUserLoggedInLink();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.clickLogoutLink();
		screenShot.captureScreenShot("TC88_08_User Logged out");
		//dashboardPOM.clickLogoutConfirmationLink();
		homepagePOM.clickOnTitleLink();
		dashboardPOM.scrollDown();
		homepagePOM.searchPropertyOnHomePage(propertyName);
		homepagePOM.switchToNewWindow();
		screenShot.captureScreenShot("TC88_09_New property details displayed");
		assertTrue(homepagePOM.checkSearchedProperyNameOnNewTab(propertyName));
	}
}
