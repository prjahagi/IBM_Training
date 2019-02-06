package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BlogTabPOM {
	private WebDriver driver; 
	
	public BlogTabPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath="//ul[@class='menu']//li[@class='menu-item menu-item-type-post_type menu-item-object-page menu-item-617']//a[contains(text(),'Blog')]")
	private WebElement blogTabLink;
	
	@FindBy(xpath="(//a[@class='read-more'][contains(text(),'Read More')])[2]")
	private WebElement readMoreLink;
	
	@FindBy(id="comment")
	private WebElement commentTextBox;
	
	@FindBy(id="submit")
	private WebElement postCommentButton;
	
	@FindBy(xpath="//h5[contains(text(),'PrachiTest')]")
	private WebElement addedCommentUserName;
	
	@FindBy(xpath="//ul[@class='my-account-nav']//a[contains(text(),'Log Out')]")
	private WebElement logoutLink;
	
	public void clickOnBlogTabLink() {
		this.blogTabLink.click();
	}
	public void clickOnReadMoreLink(){
		this.readMoreLink.click();
	}
	public void enterDataInCommentBox(String comment) {
		this.commentTextBox.click();
		this.commentTextBox.sendKeys(comment);
	}
	public void clickOnPostCommentButton() {
		this.postCommentButton.click();
	}
	public boolean checkCommentAdded() {
		if(this.addedCommentUserName.isDisplayed())
			return true;
		else
			return false;
	}
	public void clickOnLogoutLink() {
		this.logoutLink.click();
	}
	
	
	
	
	
}
