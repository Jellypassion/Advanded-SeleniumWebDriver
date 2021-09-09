package com.herokuapp.theinternet.jsalertstests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.JSAlertsPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class JSAlertsTest extends TestUtilities{
	
	@Test
	public void jsAcceptTest() {
		var expectedAlertText = "I am a JS Alert";
		var expectedResultText = "You successfully clicked an alert";
		//create new softAssert instance for soft assertions
		SoftAssert softAssert = new SoftAssert();
		
		WelcomePage welcomePage = new WelcomePage (driver, log);
		welcomePage.openPage();
		
		//Open alerts page and Click JS Alerts button
		JSAlertsPage alertsPage = welcomePage.openJSAlertsPage();
		alertsPage.openJSAlert();
		//Get Alert text
		var alertText = alertsPage.getAlertText();
		//Click ok button
		alertsPage.acceptAlert();
		//Get result text
		var resultText = alertsPage.getResultText();
		//Verifications
		//1 - Alert text is expected
		softAssert.assertTrue(alertText.equals(expectedAlertText), "Alert text is incorrect!");
		//2 - result text is expected
		softAssert.assertTrue(resultText.equals (expectedResultText), "Result text is incorrect!");
		//should be added at the end of test, otherwise the test will not fail even if there are test failures 
		softAssert.assertAll();
	}
	
	@Test
	public void jsDismissTest() {
		var expectedAlertText = "I am a JS Confirm";
		var expectedResultText = "You clicked: Cancel";
		
		WelcomePage welcomePage = new WelcomePage (driver, log);
		welcomePage.openPage();
		
		//Open alerts page and Click JS Alerts button
		JSAlertsPage alertsPage = welcomePage.openJSAlertsPage();
		alertsPage.openJSConfirm();
		//Get Alert text
		var alertText = alertsPage.getAlertText();
		//Click cancel button
		alertsPage.dismissAlert();
		//Get result text
		var resultText = alertsPage.getResultText();
		//Verifications
		//1 - Alert text is expected
		Assert.assertTrue(alertText.equals(expectedAlertText), "Alert text is incorrect!");
		//2 - result text is expected
		Assert.assertTrue(resultText.equals (expectedResultText), "Result text is incorrect!");
		
	}
	
	@Test
	public void jsPromptTest() {
		var textToEnter = "I am TEXT";
		var expectedResultText = "You entered: " + textToEnter;
		var expectedAlertText = "I am a JS prompt";
		
		WelcomePage welcomePage = new WelcomePage (driver, log);
		welcomePage.openPage();
		
		//Open alerts page and Click JS Alerts button
		JSAlertsPage alertsPage = welcomePage.openJSAlertsPage();
		alertsPage.openJSPrompt();
		//Get Alert text
		var alertText = alertsPage.getAlertText();
		//Enter the text
		alertsPage.typeIntoAlert(textToEnter);
		
		//Get result text
		var resultText = alertsPage.getResultText();
		//Verifications
		//1 - Alert text is expected
		Assert.assertTrue(alertText.equals(expectedAlertText), "Alert text is incorrect! \nFound: " + alertText + "\nBut should be: " + expectedAlertText);
		//2 - result text is expected
		Assert.assertTrue(resultText.equals (expectedResultText), "Result text is incorrect! \nFound: " + expectedResultText + "\nBut should be: " + expectedResultText);
		
	}
}
