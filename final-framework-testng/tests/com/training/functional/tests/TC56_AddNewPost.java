package com.training.functional.tests;

import org.testng.annotations.Test;
import com.training.generics.ScreenShot;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.pom.PostsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

/* Author               :   Prachi Jahagirdar
 * Test Case ID         :   RETC_056
 * Test Case Description:   To verify whether application displays details of post added in Activity section of Dashboard. *   
 */

public class TC56_AddNewPost {
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private PostsPOM postsPOM;
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
		postsPOM = new PostsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		screenShot.captureScreenShot("TC56_1_URL Opening");
	}
  
  	//To Login on Real Estate Web site	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC56_2_Login Successful_Dashboard Opened");
	}
	
	//Add new Post and validate on Dashboard if its published.
	@Test(priority=2)
	public void addNewPost() throws InterruptedException { 
		String postName="New launch-post13";
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		dashboardPOM.mouseOverPostsLink();
		dashboardPOM.clickOnAddNewPostLink();
		screenShot.captureScreenShot("TC56_3_Add New Post screen opened");
		postsPOM.enterTitleToPost(postName);
		postsPOM.enterBodyToPost("New post details 1234");
		postsPOM.selectCategoryCheckbox();
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		postsPOM.clickOnPublishButton();
		Thread.sleep(2000);
		assertTrue(postsPOM.checkPostPublishedMessageDisplayed());
		screenShot.captureScreenShot("TC56_4_Post published");
		postsPOM.clickOnDashboardIcon();
		screenShot.captureScreenShot("TC56_5_Newly added post displayed on dashboard");
		dashboardPOM.clickNewlyaddedPostLinkInActivity();
		assertTrue(postsPOM.checkEditPostScreenDisplayed());
		String actual=postsPOM.returnPostName();
		assertEquals(actual, postName);	
		screenShot.captureScreenShot("TC56_6_Edit Post containing details displayed");
	}

}
