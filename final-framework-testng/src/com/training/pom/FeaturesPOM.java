package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FeaturesPOM {
	private WebDriver driver; 

	public FeaturesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(id="tag-name")
	private WebElement featuresName;
	
	@FindBy(id="tag-slug")
	private WebElement featuresSlug;
	
	@FindBy(id="tag-description")
	private WebElement featuresDescription;
	
	@FindBy(id="submit")
	private WebElement addNewFeatureButton;
	
	@FindBy(id="bulk-action-selector-top")
	private WebElement bulkActionList;
	
	@FindBy(id="doaction")
	private WebElement applyButton;
	
	@FindBy(id="tag-search-input")
	private WebElement featureSearchBox;
	
	@FindBy(id="search-submit")
	private WebElement featureSearchButton;
	
	@FindBy(xpath="(//input[@type='checkbox' and @name='delete_tags[]'])[1]")
	private WebElement firstFeatureCheckbox;
	
	@FindBy(xpath="//p[contains(text(),'Items deleted.')]")
	private WebElement featureDeletionMessage;
	
	@FindBy(xpath="(//a[@class='row-title'])[1]")
	private WebElement titleOfFirstFeature;
	
	@FindBy(xpath="//p[contains(text(),'Item added.')]")
	private WebElement featureAdditionMessage;
	
	@FindBy(xpath="//td[contains(text(),'No categories found.')]")
	private WebElement noCategoriesFoundMessage;
	
	public void enterDataInFeatureNameTextbox(String featureName) {
		this.featuresName.clear();
		this.featuresName.sendKeys(featureName);
	}
	public void enterDataInFeatureSlugbox(String featureslug) {
		this.featuresSlug.clear();
		this.featuresSlug.sendKeys(featureslug);
	}
	public void enterDetailsInFeatureDescription(String featureDescription) {
		this.featuresDescription.clear();
		this.featuresDescription.sendKeys(featureDescription);
	}
	public void clickAddNewFeatureButton() {
		this.addNewFeatureButton.click();
	}
		
	public void clickFeatureCheckbox() {
		this.firstFeatureCheckbox.click();
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
	public boolean checkFeatureDeletedMessageDisplayed() {
		try{
			if(this.featureDeletionMessage.isDisplayed())			
			return true;
		else
			return false;
		}
		catch(NoSuchElementException n){ 
			return false;
		}
	}
	public String returnlabelforFirstRowFromList() {
		 return (this.titleOfFirstFeature.getAttribute("aria-label"));
	}
	public void searchFeature(String featureName) {
		this.featureSearchBox.clear();
		this.featureSearchBox.sendKeys(featureName);
		this.featureSearchButton.click();		
	}
	public void scrollScreenToSearchBox() {
		((JavascriptExecutor)this.driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		
	}
	public boolean checkFeatureAddedMessageDisplayed() {
		try {
		if(this.featureAdditionMessage.isDisplayed())			
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
}
