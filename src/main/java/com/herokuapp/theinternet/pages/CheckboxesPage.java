package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage extends BasePageObject{

	private By checkbox = By.xpath("//input[@type='checkbox']");
	
	public CheckboxesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	//get the list of all checkboxes and check them if they are unchecked
	public void selectAllCheckboxes() {
		log.info("Selecting all checkboxes on the page...");
		List<WebElement> checkboxes = findAll(checkbox);
		for (var checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
	
	}
	
	//Check if all checkboxes are selected
	public boolean areAllCheckboxesSelected() {
		List<WebElement> checkboxes = findAll(checkbox);
		for (var checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				log.info("At least one checkbox is not selected");
				return false;
			}
		}
		log.info("All checkboxes are selected");
		return true;
	}

}
