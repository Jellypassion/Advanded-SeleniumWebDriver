package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject{

	private By usernameLocator = By.id("username");
	private By passwordLocator = By.name("password");
	private By logInButtonLocator = By.className("radius");
	private By errorMessageLocator = By.id("flash");
	
	public LoginPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	// Execute log in
	public SecureAreaPage logIn(String username, String password) {
		log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
		typeInto(username, usernameLocator);
		typeInto(password, passwordLocator);
		click(logInButtonLocator); 
		return new SecureAreaPage(driver, log);		
	}
	
	// Method for negative log in
	public void negativeLogin(String username, String password) {
		log.info("Executing LogIn with username [" + username + "] and password [" + password + "]");
		typeInto(username, usernameLocator);
		typeInto(password, passwordLocator);
		click(logInButtonLocator); 
	}
	
	//wait for visibility of error message
	public void waitForErrorMessage() {
		waitForVisibilityOf(errorMessageLocator, 5);
	}
	
	//get error message text
	public String getErrorMessage() {
		return find(errorMessageLocator).getText();
	}

	
	
}
