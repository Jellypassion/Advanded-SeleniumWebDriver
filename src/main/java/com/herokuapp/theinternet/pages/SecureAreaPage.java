package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject{
	
	private String expectedUrl = "https://the-internet.herokuapp.com/secure";
	//private String expectedSuccessMessage = "You logged into a secure area!";
	private By logOutButtonLocator = By.xpath("//a[@class='button secondary radius']");
	private By successMessageLocator = By.id("flash");
	
	public SecureAreaPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	
	//return url variable from PageObject
	public String getExpectedUrl() {
		return expectedUrl;
	}
	
	//Check that logout button is visible
	public boolean logOutButtonVisibility() {
		//log.info("Looking for logOut button...");
		 //isVisible(logOutButtonLocator);
		return find(logOutButtonLocator).isDisplayed();	
	}
	
	//Check the Success message
	public String getSuccessMessageText() {
		//getTextFrom(successMessageLocator)
		return find(successMessageLocator).getText();
	}

}
