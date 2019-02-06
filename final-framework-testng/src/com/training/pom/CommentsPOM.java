package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommentsPOM {
	private WebDriver driver; 

	public CommentsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(xpath="//td[contains(@class,'author column-author')]//strong[contains(text(),'PrachiTest')]")
	private WebElement firstComment;
	
	@FindBy(linkText="Reply")
	private WebElement commentReplyLink;
	
	@FindBy(id="replycontent")
	private WebElement replyTextBox;
	
	@FindBy(id="replybtn")
	private WebElement replyButton;
	
	@FindBy(xpath="(//span[@class='comment-count-approved'])[1]")
	private WebElement commentResponseIcon;
	
	public void mouseOverFirstComment() {
		Actions action=new Actions(this.driver);
		action.moveToElement(this.firstComment).build().perform();
	}
	public void clickOnCommentReplyLink() {
		this.commentReplyLink.click();
	}
	public void enterReplyInTextBox(String reply) {
		this.replyTextBox.click();
		this.replyTextBox.sendKeys(reply);
	}
	public void clickReplyButton() {
		this.replyButton.click();
	}	
	public int getCountInCommentResponseIcon() {
		int i=Integer.parseInt(this.commentResponseIcon.getText());
		return i;
	}
}
