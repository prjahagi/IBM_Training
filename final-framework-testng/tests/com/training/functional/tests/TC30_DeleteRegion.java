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

public class TC30_DeleteRegion {
  
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
		screenShot.captureScreenShot("TC30_1_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC30_2_Login Successful");
	}
	
	@Test(priority=2)
	public void deleteRegion() throws InterruptedException {
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickRegionsLink();
		screenShot.captureScreenShot("TC30_3_Region Screen Opened");
		regionsPOM.searchRegion("regiontest123");
		regionsPOM.clickRegionCheckbox();
		regionsPOM.clickOnBulkActionList();
		regionsPOM.selectMoveToDelete();
		screenShot.captureScreenShot("TC30_4_Region Selected to Delete");
		regionsPOM.clickApplyButton();
		Thread.sleep(2000);
		assertTrue(regionsPOM.checkRegionDeletedMessage());
		assertTrue(regionsPOM.checkNoCategoriesFoundMessageDisplayed());
		screenShot.captureScreenShot("TC30_5_Region Deleted");		
	}
}
