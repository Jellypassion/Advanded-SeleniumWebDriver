package com.herokuapp.theinternet.checkboxespagetest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class CheckboxesTest extends TestUtilities {
	
	@Test
	public void selectTwoCheckboxesTest() {
		
		WelcomePage welcomePage = new WelcomePage(driver, log);
		//Open mainPage
		welcomePage.openPage();
		//Click on Checkboxes link
		CheckboxesPage checkboxesPage = welcomePage.openCheckboxesPage();
		//Select all checkboxes
		checkboxesPage.selectAllCheckboxes();
		sleep(2000);
		//Verify all checkboxes are selected
		Assert.assertEquals(checkboxesPage.areAllCheckboxesSelected(), true);
		
	}
	
	
}
