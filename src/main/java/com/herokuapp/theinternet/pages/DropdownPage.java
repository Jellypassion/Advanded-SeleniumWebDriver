package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePageObject{

	public DropdownPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	private By dropdownSelector = By.id("dropdown");
	
	//select an option from the dropdown
	public void selectOption(int i) {
		log.info("Selecting Option " + i + " from the dropdown");
		WebElement dropdown = find(dropdownSelector);
		Select s = new Select(dropdown);
		//there are 3 options to select from dropdown
		//#1 - by Index
		s.selectByIndex(i);
		
		//#2 - by value (String)
		//s.selectByValue("" + i);
		
		//#3 - By text
		//s.selectByVisibleText("Option " + i);		
	}
	
	//This method returns the selected option in dropdown as a String
	public String getSelectedOption() {
		WebElement dropdown = find(dropdownSelector);
		Select s = new Select(dropdown);
		String selectedOption = s.getFirstSelectedOption().getText();
		return selectedOption;
	}

}
