package com.training.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPOM {
	
	private WebDriver driver; 
	

	public DashboardPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Properties")
	private WebElement propertiesLink;	
	
	@FindBy(xpath="//a[@class='wp-has-submenu wp-not-current-submenu menu-top menu-icon-post open-if-no-js menu-top-first']//div[@class='wp-menu-image dashicons-before dashicons-admin-post']")
	private WebElement postsIcon;
	
	@FindBy(linkText="Add New")
	private WebElement postsAddNewLink;
	
	@FindBy(xpath="(//ul[1]/li[1]/a[1])[10]")
	private WebElement newlyaddedPostLinkInActivity;
	
	@FindBy(xpath="//div[contains(@class,'wp-menu-image dashicons-before dashicons-admin-comments')]")
	private WebElement commentsLink;	
	
	@FindBy(xpath="//a[contains(text(),'Howdy,')]")
	private WebElement userLoggedInLink;
	
	@FindBy(xpath="//a[@class='ab-item'][contains(text(),'Log Out')]")
	private WebElement logoutLink;
	
	@FindBy(xpath="//a[contains(text(),'log out')]")
	private WebElement logoutConfirmationLink;
	
	public void clickPropertiesLink() {
		this.propertiesLink.click();
	}	
	
	public void mouseOverPostsLink() {
		Actions action=new Actions(this.driver);
		action.moveToElement(this.postsIcon).build().perform();
	}
	
	public void clickOnAddNewPostLink() {
		this.postsAddNewLink.click();
	}
	
	public void clickNewlyaddedPostLinkInActivity() {
		this.newlyaddedPostLinkInActivity.click();
	}
	public void scrollDown() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}
	public void scrollUp() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}
	public void acceptAlert() {
		Alert alert=this.driver.switchTo().alert();
		alert.accept();
	}
	public void clickCommentsLink() {
		this.commentsLink.click();
	}
	public void mouseOverUserLoggedInLink() {
		Actions action=new Actions(this.driver);
		action.moveToElement(this.userLoggedInLink).build().perform();
	}
	public void clickLogoutLink() {
		this.logoutLink.click();
	}
	public void clickLogoutConfirmationLink() {
		this.logoutConfirmationLink.click();
	}
	
}
