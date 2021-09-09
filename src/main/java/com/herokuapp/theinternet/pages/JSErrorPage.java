package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class JSErrorPage extends BasePageObject {

	public JSErrorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	private String pageUrl = "https://the-internet.herokuapp.com/javascript_error";
	
	public void openPage() {
		log.info("Opening page " + pageUrl);
		openUrl(pageUrl);
	}

}
