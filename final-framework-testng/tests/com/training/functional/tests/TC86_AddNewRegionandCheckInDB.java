package com.training.functional.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import com.training.pom.RegionsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_086
 * Test Case Description:   To Verify whether application allows admin to Add New Region in the application & added details should get displayed in database  
 */
public class TC86_AddNewRegionandCheckInDB {
	
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private PropertiesPOM propertiesPOM;
	private RegionsPOM regionsPOM;
	private static Properties properties;
	private ScreenShot screenShot;	
	private String regionName;
	private String regionSlug;
	private String parentRegion;
	private String regionDescription;
	
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
		screenShot.captureScreenShot("TC86_1_URL Opening");
	}
	//To Login on Real Estate Web site
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC86_2_Login Successful");
	}
	// Add new region with all details
	@Test(priority=2)
	public void addNewRegion()  {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickRegionsLink();
		screenShot.captureScreenShot("TC86_3_Region Screen Opened");
		regionsPOM.enterDataInRegionNameTextbox("regiontest123");
		regionName=regionsPOM.getRegionNameTextBox();
		regionsPOM.enterDataInRegionSlugbox("bookingtest");
		regionSlug=regionsPOM.getRegionSlugTextBox();
		regionsPOM.selectNoneInParentRegion();
		parentRegion=regionsPOM.getParentRegionDropdown();
		regionsPOM.enterDetailsInRegionDescription("test tc");
		regionDescription=regionsPOM.getRegionDescription();
		screenShot.captureScreenShot("TC86_4_Region Details entered");
		regionsPOM.clickAddNewRegionButton();
		regionsPOM.scrollScreenToSearchBox();
		regionsPOM.searchRegion(regionName);
		String actualResult=regionsPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(regionName));
		screenShot.captureScreenShot("TC86_5_Region Added");
	}
	
	//To compare created region details from real estate website and region table from db.
	@Test(priority=3,dataProvider = "db-inputs", dataProviderClass = RealEstateDataProvider.class)
	public void checkRegionInDB(String regionNameDB, String regionSlugDB,String parentRegionDB, String regionDescriptionDB) {
		assertEquals(regionNameDB, regionName);
		assertEquals(regionSlugDB, regionSlug);
		assertEquals(parentRegionDB, parentRegion);
		assertEquals(regionDescriptionDB, regionDescription);
	}
}
