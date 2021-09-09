package com.herokuapp.theinternet.loginpagetests;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class PositiveLoginTests extends TestUtilities {

	@Test
	public void logInTest() {
		log.info("Starting logIn test");

		WelcomePage welcomePage = new WelcomePage(driver, log);
		// open main page
		welcomePage.openPage();
		takeScreenshot("Welcomepage opened");

		// Click on Form Authentication link
		LoginPage loginPage = welcomePage.clickFormAuthenticationLink();
		takeScreenshot("LogInPage opened");

		// Adding cookie
		// create a cookie that we want to add
		Cookie ck = new Cookie("username", "tomsmith", "the-internet.herokuapp.com", "/", null);
		loginPage.setCookie(ck); 

		// execute login
		SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");
		takeScreenshot("SecureAreaPage opened");
		
		//get cookies
		String username = secureAreaPage.getCookie("username");
		log.info("Username cookies is " + username);
		String sesssionCookie = secureAreaPage.getCookie("rack.session");
		log.info("Session cookies is " + sesssionCookie);

		// verifications
		// new url
		Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getExpectedUrl());

		// log out button is visible
		Assert.assertTrue(secureAreaPage.logOutButtonVisibility(), "logOutButton is not visible.");

		// Successful log in message
		String expectedSuccessMessage = "You logged into a secure area!";
		Assert.assertTrue(secureAreaPage.getSuccessMessageText().contains(expectedSuccessMessage),
				"actualSuccessMessage does not contain expectedSuccessMessage\nexpectedSuccessMessage: "
						+ expectedSuccessMessage + "\nactualSuccessMessage: " + secureAreaPage.getSuccessMessageText());

	}
}
