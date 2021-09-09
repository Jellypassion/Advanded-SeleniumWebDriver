package com.herokuapp.theinternet.loginpagetests;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.CsvDataProviders;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class NegativeLoginTests extends TestUtilities {
	
	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void negativeLogInTest(Map<String, String> testData) {
		/*
		 * if (username.equalsIgnoreCase("incorrectUsername"))
		 * log.info("Starting negativeUsernameTest"); if
		 * (password.equalsIgnoreCase("incorrectPassword"))
		 * log.info("Starting negativePasswordTest");
		 */
		//Data
		String no = testData.get("no");
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedErrorMessage = testData.get("expectedMessage");
		String description = testData.get("description");
		
		log.info("Starting negativeLoginTest # " + no + " for " + description);
		
		WelcomePage welcomePage = new WelcomePage(driver, log);
		// open main page
		welcomePage.openPage();

		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();

		// execute login with provided (negative) credentials
		loginPage.negativeLogin(username, password);

		// Verification
		//Wait and then check that actual error message is the same as expected one
		loginPage.waitForErrorMessage();
		String actualErrorMessage = loginPage.getErrorMessage();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"actualErrorMessage does not contain expectedErrorMessage\nexpectedErrorMessage: "
						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
	}


	
}
