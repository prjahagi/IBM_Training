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
	String regionName;
	
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
		regionName="regiontest123";
		regionsPOM.enterDataInRegionNameTextbox(regionName);
		regionsPOM.enterDataInRegionSlugbox("bookingtest");
		regionsPOM.selectNoneInParentRegion();
		regionsPOM.enterDetailsInRegionDescription("test tc");
		screenShot.captureScreenShot("TC86_4_Region Details entered");
		regionsPOM.clickAddNewRegionButton();
		regionsPOM.scrollScreenToSearchBox();
		regionsPOM.searchRegion(regionName);
		String actualResult=regionsPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(regionName));
		screenShot.captureScreenShot("TC86_5_Region Added");
	}
	
	@Test(priority=3,dataProvider = "db-inputs", dataProviderClass = LoginDataProviders.class)
	public void checkRegionInDB(String regionNameDB, String regionSlugDB,String parentRegionDB, String regionDescriptionDB) {
		assertEquals(regionNameDB, regionName);
	}
}
