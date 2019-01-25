package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PropertiesPOM {
	private WebDriver driver; 

	public PropertiesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText="All Properties")
	private WebElement allPropertiesLink;
	
	@FindBy(linkText="Features")
	private WebElement featuresLink;
	
	@FindBy(linkText="Regions")
	private WebElement regionsLink;	
	
	@FindBy(id="bulk-action-selector-top")
	private WebElement bulkActionList;
	
	@FindBy(id="doaction")
	private WebElement applyButton;
	
	@FindBy(id="post-search-input")
	private WebElement propertySearchBox;
	
	@FindBy(id="search-submit")
	private WebElement propertySearchButton;
	
	@FindBy(xpath="(//input[@type='checkbox' and @name='post[]'])[1]")
	private WebElement firstPropertyCheckbox;
	
	@FindBy(id="message")
	private WebElement propertyDeletionMessage;
	
	@FindBy(xpath="//a[contains(text(),'Undo')]")
	private WebElement undolink;
	
	@FindBy(xpath="(//a[@class='row-title'])[1]")
	private WebElement titleOfFirstProperty;
	
	public void clickAllPropertiesLink() {
		this.allPropertiesLink.click();
	}	
	public void clickPropertyCheckbox() {
		this.firstPropertyCheckbox.click();
	}	
	public void clickOnBulkActionList() {
		this.bulkActionList.click();
	}
	public void selectMoveToTrash() {
		Select list= new Select(this.bulkActionList);
		list.selectByVisibleText("Move to Trash");
	}	
	public void clickApplyButton() {
		this.applyButton.click();
	}	
	public boolean checkMovedToTrashMessageDisplayed() {
		if(this.propertyDeletionMessage.isDisplayed()&&this.undolink.isDisplayed())			
			return true;
		else
			return false;
	}
	public void clickFeaturesLink() {
		this.featuresLink.click();
	}	
	public void clickRegionsLink() {
		this.regionsLink.click();
	}
	public String returnlabelforFirstRowFromList() {
		 return (this.titleOfFirstProperty.getAttribute("aria-label"));
	}
	public void searchProperty(String propertyName) {
		this.propertySearchBox.clear();
		this.propertySearchBox.sendKeys(propertyName);
		this.propertySearchButton.click();	
		
	}
	
}
