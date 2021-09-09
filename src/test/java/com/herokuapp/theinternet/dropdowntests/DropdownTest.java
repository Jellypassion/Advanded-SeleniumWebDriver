package com.herokuapp.theinternet.dropdowntests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.DropdownPage;
import com.herokuapp.theinternet.pages.WelcomePage;

public class DropdownTest extends TestUtilities {

	@Test
	public void selectOptionFromDropdown() {
		String expectedOption = "Option 2";
		WelcomePage welcomepage = new WelcomePage (driver, log);
		welcomepage.openPage();
		//open dropdown page
		DropdownPage dropdownPage = welcomepage.openDropdownPage();
		//select option 2
		int option = 2;
		dropdownPage.selectOption(option);
		sleep(2000);
		//verify that option 2 is selected
		String optionText = dropdownPage.getSelectedOption();
		log.info("Option [" + optionText + "] is selected");
		Assert.assertEquals(optionText, expectedOption);
	}

}
