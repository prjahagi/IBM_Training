package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostsPOM {

	private WebDriver driver; 	

	public PostsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="title-prompt-text")
	private WebElement postsTitle;
	
	@FindBy(id="content")
	private WebElement postsBody;
	
	@FindBy(id="in-category-1")
	private WebElement postCategoryCheckbox;
	
	@FindBy(id="publish")
	private WebElement publishButton;
	
	@FindBy(xpath="//div[@id='publishing-action']")
	private WebElement publishingactionframe;
	
	@FindBy(xpath="//div[@class='wp-menu-image dashicons-before dashicons-dashboard']")
	private WebElement dashboardIconLink;
	
	@FindBy(xpath="//h1[@class='wp-heading-inline']")
	private WebElement editPostTitle;
	
	@FindBy(id="title")
	private WebElement editPostName;
	
	@FindBy(id="message")
	private WebElement postAdditionMessage;
	
	@FindBy(xpath="//a[contains(text(),'View post')]")
	private WebElement additionMessageViewPostLink;
	
	public void enterTitleToPost(String postName) {
		Actions actions = new Actions(this.driver);
		actions.moveToElement(this.postsTitle);
		actions.click();
		actions.sendKeys(postName);
		actions.build().perform();
	}
	
	public void enterBodyToPost(String postBody) {
		this.postsBody.click();
		this.postsBody.sendKeys(postBody);
	}
	
	public void selectCategoryCheckbox() {
		this.postCategoryCheckbox.click();
	}
	
	public void clickOnPublishButton() throws InterruptedException {
		//Actions actions = new Actions(this.driver);
		//actions.moveToElement(this.publishButton).click().build().perform();
		this.publishButton.click();
	}	
	public void clickOnDashboardIcon() {
		this.dashboardIconLink.click();
	}
	
	public boolean checkEditPostScreenDisplayed() {
		return this.editPostTitle.isDisplayed();
	}
	
	public String returnPostName() {
		 return (this.editPostName.getAttribute("value"));
	}
	
	public boolean checkPostPublishedMessageDisplayed() {
		if(this.postAdditionMessage.isDisplayed()&&this.additionMessageViewPostLink.isDisplayed())			
			return true;
		else
			return false;
	}
}
