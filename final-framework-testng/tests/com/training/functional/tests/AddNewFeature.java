package com.training.functional.tests;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.PropertiesPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.FeaturesPOM;
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

public class AddNewFeature {
  
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
		screenShot.captureScreenShot("TC27_1_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC27_2_Login Successful_Dashboard Opened");
	}
	
	@Test(priority=2)
	public void addNewFeature() throws InterruptedException {
		dashboardPOM.clickPropertiesLink();
		propertiesPOM.clickFeaturesLink();
		screenShot.captureScreenShot("TC27_3_Feature Screen Opened");
		String featureName="featuretest123";
		featuresPOM.enterDataInFeatureNameTextbox(featureName);
		featuresPOM.enterDataInFeatureSlugbox("bookingtest");
		featuresPOM.enterDetailsInFeatureDescription("test tc");
		screenShot.captureScreenShot("TC27_4_Feature Details entered");
		featuresPOM.clickAddNewFeatureButton();
		featuresPOM.scrollScreenToSearchBox();
		Thread.sleep(3000);
		screenShot.captureScreenShot("TC27_5_Scroll up");
		assertTrue(featuresPOM.checkFeatureAddedMessageDisplayed());
		featuresPOM.searchFeature(featureName);
		String actualResult=featuresPOM.returnlabelforFirstRowFromList();		
		assertTrue(actualResult.contains(featureName));
		screenShot.captureScreenShot("TC27_6_Feature Added");
	}
}
