package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowsPage extends BasePageObject {
	
	private By clickHereLinkLocator = By.xpath("//a[text()='Click Here']");
	
	public WindowsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//Click on 'Click Here' link to open a new window
	public void openNewWindow() {
		log.info("Clicking on 'Click Here' link");
		click(clickHereLinkLocator);
	}
	
	//Find page with title "New Window" and switch to it
	public NewWindowPage switchToNewWindowPage() {
		log.info("Switching to the new window");
		switchToWindowWithTitle("New Window");
		return new NewWindowPage(driver, log);
	}

}
