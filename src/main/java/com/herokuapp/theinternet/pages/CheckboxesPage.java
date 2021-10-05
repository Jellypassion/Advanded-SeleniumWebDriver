package com.herokuapp.theinternet.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage extends BasePageObject {

	private By checkboxLocator = By.xpath("//input[@type='checkbox']");
	private By footerLocator = By.cssSelector("#page-footer");
	private WebElement footer = driver.findElement(By.xpath("//div[@id='page-footer']/div/div"));

	public CheckboxesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// get the list of all checkboxes and check them if they are unchecked
	public void selectAllCheckboxes() {
		log.info("Selecting all checkboxes on the page...");
		List<WebElement> checkboxes = findAll(checkboxLocator);
		for (var checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}

	}

	// Check if all checkboxes are selected
	public boolean areAllCheckboxesSelected() {
		List<WebElement> checkboxes = findAll(checkboxLocator);
		for (var checkbox : checkboxes) {
			if (!checkbox.isSelected()) {
				log.info("At least one checkbox is not selected");
				return false;
			}
		}
		log.info("All checkboxes are selected");
		return true;
	}

	public boolean isFooterDisplayed() {
		boolean is = isElementDisplayed(footerLocator);
		log.info("Footer visibility: " + is);
		if (is) {
			String style = footer.getAttribute("style");
			String cleanStyle = style.replaceAll("\\s+", "");
			if (cleanStyle.equals("text-align:center;"))
				log.info("Footer position is correct, " + cleanStyle.substring(11, cleanStyle.length() - 1));
			else
				log.info("Footer position may be incorrect " + cleanStyle.substring(11, cleanStyle.length() - 1));
		}
		return is;
	}

}
