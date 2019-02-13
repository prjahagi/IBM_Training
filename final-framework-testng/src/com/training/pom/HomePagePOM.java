package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePOM {
	
	private WebDriver driver; 	

	public HomePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@title='Real Estate']")
	private WebElement titleLink;
	
	@FindBy(xpath="//input[@placeholder='Search here for Properties..']")
	private WebElement searchBox;
	
	@FindBy(xpath="//span[@class='overlap']")
	private WebElement searchResultDropdown;
	
	public void clickOnTitleLink() {
		Actions action=new Actions(this.driver);
		action.moveToElement(this.titleLink).build().perform();
		this.titleLink.click();
	}	
	public void searchPropertyOnHomePage(String propertyName) {
		this.searchBox.click();
		this.searchBox.sendKeys(propertyName);
		this.searchResultDropdown.click();
	}
	public void switchToNewWindow() {
		for(String winHandle : this.driver.getWindowHandles()){
		    this.driver.switchTo().window(winHandle);
		}		
	}
	public boolean checkSearchedProperyNameOnNewTab(String propertyName) {
		String start="//h2[contains(text(),'";
		String end="')]";
		String CompleteXpath=start+propertyName+end;
		return this.driver.findElement(By.xpath(CompleteXpath)).isDisplayed();	
		
	}
	
}
