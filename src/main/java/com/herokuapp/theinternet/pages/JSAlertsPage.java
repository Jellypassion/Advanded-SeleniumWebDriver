package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JSAlertsPage extends BasePageObject {

	public JSAlertsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	private By jsAlertButtonLocator = By.xpath("//button[text()='Click for JS Alert']");
	private By jsConfirmButtonLocator = By.xpath("//button[text()='Click for JS Confirm']");
	private By jsPromptButtonLocator = By.xpath("//button[text()='Click for JS Prompt']");
	private By resultTextLocator = By.id("result");

	// Click on 'Click for JS Alert' button
	public void openJSAlert() {
		log.info("Clicking on 'Click for JS Alert' button to open alert");
		click(jsAlertButtonLocator);
	}

	// Click on 'Click for JS Confirm' button
	public void openJSConfirm() {
		log.info("Clicking on 'Click for JS Confirm' button to open alert");
		click(jsConfirmButtonLocator);
	}

	// Click on 'Click for JS Prompt' button
	public void openJSPrompt() {
		log.info("Clicking on 'Click for JS Prompt' button to open alert");
		click(jsPromptButtonLocator);
	}

	// Get text from alert
	public String getAlertText() {
		Alert alert = switchToAlert();
		var alertText = alert.getText();
		log.info("Alert's text is: " + alertText);
		return alertText;
	}

	// Switch to alert and press OK
	public void acceptAlert() {
		Alert alert = switchToAlert();
		alert.accept();
		log.info("Alert Accepted.");
	}

	// Switch to alert and press Cancel
	public void dismissAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		log.info("Alert Dismissed.");
	}

	// Type text inside alert
	public void typeIntoAlert(String text) {
		log.info("Typing text into alert");
		Alert alert = switchToAlert();
		alert.sendKeys(text);
		alert.accept();
	}

	// Get text from result-text element
	public String getResultText() {
		String result = find(resultTextLocator).getText();
		log.info("Result text is: " + result);
		return result;
	}

}
