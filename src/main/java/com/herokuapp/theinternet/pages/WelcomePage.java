package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject{
	
	
	private String pageUrl = "https://the-internet.herokuapp.com/";
	
	private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
	private By checkboxesLink = By.linkText("Checkboxes");
	private By dropdownLink = By.linkText("Dropdown");
	private By jsAlertsLink = By.linkText("JavaScript Alerts");
	private By windowsLink = By.linkText("Multiple Windows");
	
	public WelcomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Open Welcome Page with its url
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl); 
		log.info("Page Opened! ");
	}
	
	//Open Login page by clicking on Form Authentication link
	public LoginPage clickFormAuthenticationLink() {
		log.info("Clicking on Form Authentication link on Welcome page");
		click(formAuthenticationLinkLocator);
		return new LoginPage(driver, log);
	}
	
	//Open checkboxes page
	public CheckboxesPage openCheckboxesPage() {
		log.info("Clicking on Checkboxes link on Welcome page");
		click(checkboxesLink);
		return new CheckboxesPage(driver, log);
	}
	
	//Open dropdown page
	public DropdownPage openDropdownPage() {
		log.info("Opening Dropdown page");
		click(dropdownLink);
		return new DropdownPage(driver, log);
	}
	
	//open JS Alerts page
	public JSAlertsPage openJSAlertsPage() {
		log.info("Opening JS Alerts page");
		click(jsAlertsLink);
		return new JSAlertsPage(driver, log);
	}
	
	//open Windows page
	public WindowsPage openWidowsPage() {
		log.info("Opening Multiple Windows page");
		click(windowsLink);
		return new WindowsPage(driver, log);
	}
		
		
		
		
		
		
		
		
		
		
		
		
	
}
