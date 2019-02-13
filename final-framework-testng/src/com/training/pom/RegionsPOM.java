package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegionsPOM {
	private WebDriver driver; 

	public RegionsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="tag-name")
	private WebElement regionsName;
	
	@FindBy(id="tag-slug")
	private WebElement regionsSlug;
	
	@FindBy(id="parent")
	private WebElement parentRegionBox;
	
	@FindBy(id="tag-description")
	private WebElement regionsDescription;
	
	@FindBy(id="submit")
	private WebElement addNewRegionButton;
	
	@FindBy(id="bulk-action-selector-top")
	private WebElement bulkActionList;
	
	@FindBy(id="doaction")
	private WebElement applyButton;
	
	@FindBy(id="tag-search-input")
	private WebElement regionSearchBox;
	
	@FindBy(id="search-submit")
	private WebElement regionSearchButton;
	
	@FindBy(xpath="(//input[@type='checkbox' and @name='delete_tags[]'])[1]")
	private WebElement firstRegionCheckbox;
	
	@FindBy(xpath="//p[contains(text(),'Items deleted.')]")
	private WebElement regionDeletionMessage;
	
	@FindBy(xpath="(//a[@class='row-title'])[1]")
	private WebElement titleOfFirstRegion;
	
	@FindBy(xpath="//p[contains(text(),'Item added.')]")
	private WebElement regionAdditionMessage;
	
	@FindBy(xpath="//td[contains(text(),'No categories found.')]")
	private WebElement noCategoriesFoundMessage;
	
	public void enterDataInRegionNameTextbox(String regionName) {
		this.regionsName.clear();
		this.regionsName.sendKeys(regionName);
	}
	public String getRegionNameTextBox() {
		return this.regionsName.getAttribute("value");		
	}
	public void enterDataInRegionSlugbox(String regionslug) {
		this.regionsSlug.clear();
		this.regionsSlug.sendKeys(regionslug);
	}
	public String getRegionSlugTextBox() {
		return this.regionsSlug.getAttribute("value");
	}
	public void clickOnParentRegionList() {
		this.parentRegionBox.click();
	}
	public void selectNoneInParentRegion() {
		Select list= new Select(this.parentRegionBox);
		list.selectByVisibleText("None");	
	}
	public String getParentRegionDropdown() {
		Select list= new Select(this.parentRegionBox);
		return list.getFirstSelectedOption().getText();
	}
	public void enterDetailsInRegionDescription(String regionDescription) {
		this.regionsDescription.clear();
		this.regionsDescription.sendKeys(regionDescription);
	}
	public String getRegionDescription() {
		return this.regionsDescription.getAttribute("value");
	}
	public void clickAddNewRegionButton() {
		this.addNewRegionButton.click();
	}

	public void clickRegionCheckbox() {
		this.firstRegionCheckbox.click();
	}
	public void clickOnBulkActionList() {
		this.bulkActionList.click();
	}
	public void selectMoveToDelete() {
		Select list= new Select(this.bulkActionList);
		list.selectByVisibleText("Delete");	
	}
	public void clickApplyButton() {
		this.applyButton.click();
	}	
	public boolean checkRegionDeletedMessage() {
		try {
		if(this.regionDeletionMessage.isDisplayed())			
			return true;
		else
			return false;
		}
		catch(NoSuchElementException n){ 
			return false;
		}
	}
	public String returnlabelforFirstRowFromList() {
		 return (this.titleOfFirstRegion.getAttribute("aria-label"));
	}
	public void searchRegion(String featureName) {
		this.regionSearchBox.clear();
		this.regionSearchBox.sendKeys(featureName);
		this.regionSearchButton.click();		
	}
	public void scrollScreenToSearchBox() {
		((JavascriptExecutor)this.driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		
	}
	public boolean checkRegionAddedMessageDisplayed() {
		try {
		if(this.regionAdditionMessage.isDisplayed())			
			return true;
		else
			return false;
		}
		catch(NoSuchElementException n){ 
			return false;
		}
	}
	public boolean checkNoCategoriesFoundMessageDisplayed() {
		try{
			if(this.noCategoriesFoundMessage.isDisplayed())			
			return true;
		else
			return false;
		}
		catch(NoSuchElementException n){ 
			return false;
		}
	}
	public void selectParentRegion(String parentRegion) {
		Select list= new Select(this.parentRegionBox);
		list.selectByVisibleText(parentRegion);		
	}
	
}
