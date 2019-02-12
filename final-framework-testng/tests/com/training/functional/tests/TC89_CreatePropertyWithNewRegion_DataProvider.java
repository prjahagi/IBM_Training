package com.training.functional.tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.dataproviders.RealEstateDataProvider;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.pom.RegionsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_089
 * Test Case Description:   To verify whether application allows admin to create multiple  property details based on the Region created   
 */
public class TC89_CreatePropertyWithNewRegion_DataProvider {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private PropertiesPOM propertiesPOM;
	private RegionsPOM regionsPOM;
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
		regionsPOM =new RegionsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		screenShot.captureScreenShot("TC89_1_URL Opening");
	}
	
	//To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC89_2_Login Successful_Dashboard Opened");
	}
	
	// To Add multiple regions with Data provider
	@Test(priority=2,dataProvider = "TC89_Data", dataProviderClass = RealEstateDataProvider.class)
	public void addNewRegion(String regionName, String regionSlug,String parentRegion, String regionDescription, String propertyName, String propertyText) {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickRegionsLink();
		screenShot.captureScreenShot("TC89_3_Region Screen Opened");
		regionsPOM.enterDataInRegionNameTextbox(regionName);
		regionsPOM.enterDataInRegionSlugbox(regionSlug);
		regionsPOM.selectParentRegion(parentRegion);
		regionsPOM.enterDetailsInRegionDescription(regionDescription);
		screenShot.captureScreenShot("TC89_4_Region Details entered");
		regionsPOM.clickAddNewRegionButton();
		regionsPOM.scrollScreenToSearchBox();
		regionsPOM.searchRegion(regionName);
		String actualResult=regionsPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(regionName));
		screenShot.captureScreenShot("TC89_5_Region Added");
	}
	
	//Create New multiple properties with newly added regions with Data provider
	@Test(priority=3,dataProvider = "TC89_Data", dataProviderClass = RealEstateDataProvider.class)
	public void CreateNewProperty(String regionName, String regionSlug,String parentRegion, String regionDescription, String propertyName, String propertyText) throws AWTException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);		
		propertiesPOM.mouseOverPropertiesIcon();
		propertiesPOM.clickOnAddNewPropertyLink();
		propertiesPOM.enterTitleToProperty(propertyName);
		propertiesPOM.enterTextToProperty(propertyText);
		dashboardPOM.scrollDown();
		propertiesPOM.clickNewRegionCheckbox(regionName);
		screenShot.captureScreenShot("TC89_6_property details entered and selected region");
		dashboardPOM.scrollUp();
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		propertiesPOM.clickOnPublishButton();
		screenShot.captureScreenShot("TC89_7_property Added");
		assertTrue(propertiesPOM.checkPropertyPublishedMessageDisplayed());
	}
}
