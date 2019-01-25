package com.training.functional.tests;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.pom.RegionsPOM;
import com.training.pom.DashboardPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AddNewRegion {
  
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
		screenShot.captureScreenShot("TC29_1_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC29_2_Login Successful");
	}
	
	@Test(priority=2)
	public void addNewRegion() throws InterruptedException {
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickRegionsLink();
		screenShot.captureScreenShot("TC29_3_Region Screen Opened");
		String regionName="regiontest123";
		regionsPOM.enterDataInRegionNameTextbox(regionName);
		regionsPOM.enterDataInRegionSlugbox("bookingtest");
		regionsPOM.selectNoneInParentRegion();
		regionsPOM.enterDetailsInRegionDescription("test tc");
		screenShot.captureScreenShot("TC29_4_Region Details entered");
		regionsPOM.clickAddNewRegionButton();
		regionsPOM.scrollScreenToSearchBox();
		Thread.sleep(3000);
		screenShot.captureScreenShot("TC29_5_Scroll up");
		assertTrue(regionsPOM.checkRegionAddedMessageDisplayed());
		regionsPOM.searchRegion(regionName);
		String actualResult=regionsPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(regionName));
		screenShot.captureScreenShot("TC29_6_Region Added");
	}
	
}
