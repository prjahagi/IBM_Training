package com.training.functional.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.BlogTabPOM;
import com.training.pom.CommentsPOM;
import com.training.pom.DashboardPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC58_AdminReplyToCommentAddedByUser {
	private WebDriver driver;
	private String baseUrl;
	private String baseUrl1;
	private LoginPOM loginPOM;
	private DashboardPOM dashboardPOM;
	private CommentsPOM commentsPOM;
	private BlogTabPOM blogtabPOM;
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
		commentsPOM= new CommentsPOM(driver);
		blogtabPOM =new BlogTabPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		baseUrl1 = properties.getProperty("baseURL1");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl1);
		screenShot.captureScreenShot("TC58_01_URL Opening");
	}
	
	@Test(priority=1)
	public void validLoginTest() {
		loginPOM.sendUserName("PrachiTest");
		loginPOM.sendPassword("Jan20189");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC58_02_URL Login Successful");
	}
	
	@Test(priority=2)
	public void AddCommentOnPost() throws AWTException, InterruptedException {
		blogtabPOM.clickOnBlogTabLink();
		dashboardPOM.scrollDown();
		screenShot.captureScreenShot("TC58_03_Blog Page displayed");
		blogtabPOM.clickOnReadMoreLink();
		screenShot.captureScreenShot("TC58_04_Selected blog details displayed");
		dashboardPOM.scrollDown();
		dashboardPOM.scrollDown();		
		blogtabPOM.enterDataInCommentBox("Tested");
		screenShot.captureScreenShot("TC58_05_Entered details in Comment textbox displayed");
		blogtabPOM.clickOnPostCommentButton();
		screenShot.captureScreenShot("TC58_06_Comment should get added and displayed on the screen");
		assertTrue(blogtabPOM.checkCommentAdded());	
		driver.get(baseUrl1);;
		dashboardPOM.scrollDown();
		blogtabPOM.clickOnLogoutLink();		
		screenShot.captureScreenShot("TC58_07_Logged out successfully");
	}
	
	@Test(priority=3)
	public void OpenNewWindowAndLogin() {
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs= new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(baseUrl1);	
		screenShot.captureScreenShot("TC58_08_New Window Opened");
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		screenShot.captureScreenShot("TC58_09_Logged in as Admin successfully");
	}
	
	@Test(priority=4)
	public void ReplyToComment() throws InterruptedException {
		dashboardPOM.clickCommentsLink();
		int expected=2;
		screenShot.captureScreenShot("TC58_10_comments added for the post displayed");
		commentsPOM.mouseOverFirstComment();
		Thread.sleep(1000);
		commentsPOM.clickOnCommentReplyLink();
		commentsPOM.enterReplyInTextBox("TESTEDREPLY");
		screenShot.captureScreenShot("TC58_11_Reply comment added in reply text box");
		commentsPOM.clickReplyButton();
		Thread.sleep(3000);
		driver.navigate().refresh();
		int actual=commentsPOM.getCountInCommentResponseIcon();
		assertEquals(actual, expected);
		screenShot.captureScreenShot("TC58_12_Number incremented in In Response icon of comment");
	}
	
	
}
