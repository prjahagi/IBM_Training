package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	private WebElement propertyMessage;
	
	@FindBy(xpath="//a[contains(text(),'Undo')]")
	private WebElement undolink;
	
	@FindBy(xpath="(//a[@class='row-title'])[1]")
	private WebElement titleOfFirstProperty;
	
	@FindBy(xpath="//div[@class='wp-menu-image dashicons-before dashicons-admin-multisite']")
	private WebElement propertiesIconsLink;
	
	@FindBy(linkText="Add New")
	private WebElement AddNewLink;
	
	@FindBy(xpath="//a[@class='page-title-action']")
	private WebElement AddNewButton;
	
	@FindBy(id="title-prompt-text")
	private WebElement propertiesTitle;
	
	@FindBy(id="content")
	private WebElement propertyText;
	
	@FindBy(xpath="(//input[@type='checkbox' and @name='tax_input[property_feature][]'])[1]")
	private WebElement firstFeatureCheckbox;
	
	@FindBy(xpath="(//input[@type='checkbox' and @name='tax_input[region][]'])[1]")
	private WebElement firstRegionCheckbox;
	
	@FindBy(id="publish")
	private WebElement publishButton;
	
	@FindBy(xpath="//a[contains(text(),'View post')]")
	private WebElement additionMessageViewPostLink;
	
	@FindBy(xpath="//a[@class='submitdelete deletion']")
	private WebElement MoveToTrashLink;
	
	@FindBy(xpath="//a[@href='edit.php?post_status=trash&post_type=property']")
	private WebElement menuBarTrashIcon;
	
	@FindBy(xpath="(//tr[1]//strong[1])[1]")
	private WebElement nameOfFirstPropertyInTrash;
	
	@FindBy(id="_price")
	private WebElement propertyPrice;
	
	@FindBy(id="_price_per")
	private WebElement propertyPricePerSqft;
	
	@FindBy(xpath="//a[@id='ui-id-2']")
	private WebElement MainDetailsTabProperty;
	
	@FindBy(id="_status")
	private WebElement MainDetailsTabStatus;
	
	@FindBy(id="_location")
	private WebElement MainDetailsTabLocation;
	
	@FindBy(id="_possession")
	private WebElement MainDetailsTabPossession;
	
	@FindBy(xpath="//a[@id='ui-id-3']")
	private WebElement LocationTabProperty;
	
	@FindBy(id="_friendly_address")
	private WebElement LocationTabAddress;
	
	@FindBy(id="_address")
	private WebElement LocationTabGoogleMapsAddress;
	
	@FindBy(id="_geolocation_lat")
	private WebElement LocationTabLatitude;
	
	@FindBy(id="_geolocation_long")
	private WebElement LocationTabLongitude;
	
	@FindBy(xpath="//a[@id='ui-id-4']")
	private WebElement DetailsTabProperty;
	
	@FindBy(id="_storage_room")
	private WebElement DetailsTabStoraegeRoom;
	
	@FindBy(xpath="//ul[@class='children acf-bl']//input[@value='24']")
	private WebElement KeywordTagsCheckbox;

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
		if(this.propertyMessage.isDisplayed()&&this.undolink.isDisplayed())			
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
	public void mouseOverPropertiesIcon() {
		Actions action=new Actions(this.driver);
		action.moveToElement(this.propertiesIconsLink).build().perform();
	}	
	public void clickOnAddNewPropertyLink() {
		this.AddNewLink.click();
	}
	public void clickOnAddNewPropertyButton() {
		this.AddNewButton.click();
	}
	public void enterTitleToProperty(String propertyName) {
		Actions actions = new Actions(this.driver);
		actions.moveToElement(this.propertiesTitle);
		actions.click();
		actions.sendKeys(propertyName);
		actions.build().perform();
	}	
	public void enterTextToProperty(String propertyText) {
		this.propertyText.click();
		this.propertyText.sendKeys(propertyText);
	}
	public void clickNewFeatureCheckbox() {
		this.firstFeatureCheckbox.click();
	}
	public void clickOnPublishButton() {
		//Actions actions = new Actions(this.driver);
		//actions.moveToElement(this.publishButton).click().build().perform();;
		this.publishButton.click();
	}
	public boolean checkPropertyPublishedMessageDisplayed() {
		if(this.propertyMessage.isDisplayed()&&this.additionMessageViewPostLink.isDisplayed())			
			return true;
		else
			return false;
	}
	public void clickNewRegionCheckbox() {
		this.firstRegionCheckbox.click();
	}
	public void clickMoveToTrashLink() {
		this.MoveToTrashLink.click();
	}
	public void clickmenuBarTrashIcon() {
		this.menuBarTrashIcon.click();
	}
	public String returnNameOfFirstPropertyInTrash() {
		 return (this.nameOfFirstPropertyInTrash.getText());
	}
	public void enterPriceToProperty(String propertyPrice) {
		this.propertyPrice.click();
		this.propertyPrice.sendKeys(propertyPrice);
	}
	public void enterPricePerSqFtToProperty(String propertyPricePerSqft) {
		this.propertyPricePerSqft.click();
		this.propertyPricePerSqft.sendKeys(propertyPricePerSqft);
	}
	public void clickOnMainDetailsTab() {
		this.MainDetailsTabProperty.click();
	}
	public void enterStatusToProperty(String propertyStatus) {
		this.MainDetailsTabStatus.click();
		this.MainDetailsTabStatus.sendKeys(propertyStatus);
	}
	public void enterLocationToProperty(String propertyLocation) {
		this.MainDetailsTabLocation.click();
		this.MainDetailsTabLocation.sendKeys(propertyLocation);
	}
	public void enterPossessionToProperty(String propertyPossession) {
		this.MainDetailsTabPossession.click();
		this.MainDetailsTabPossession.sendKeys(propertyPossession);
	}
	public void clickOnLocationTab() {
		this.LocationTabProperty.click();
	}
	public void enterAddressToProperty(String propertyAddress) {
		this.LocationTabAddress.click();
		this.LocationTabAddress.sendKeys(propertyAddress);
	}
	public void enterGoogleMapsAddressToProperty(String propertyAddress) {
		this.LocationTabGoogleMapsAddress.click();
		this.LocationTabGoogleMapsAddress.sendKeys(propertyAddress);
	}
	public void enterLatitudeToProperty(String propertyLatitude) {
		this.LocationTabLatitude.click();
		this.LocationTabLatitude.sendKeys(propertyLatitude);
	}
	public void enterLongitudeToProperty(String propertyLongitude) {
		this.LocationTabLongitude.click();
		this.LocationTabLongitude.sendKeys(propertyLongitude);
	}
	public void clickOnDetailsTab() {
		this.DetailsTabProperty.click();
	}
	public void enterStorageRoomToProperty(String StorageRoom) {
		this.DetailsTabStoraegeRoom.click();
		this.DetailsTabStoraegeRoom.sendKeys(StorageRoom);
	}
	public void clickOnKeywordTagsCheckbox() {
		this.KeywordTagsCheckbox.click();
	}
}
