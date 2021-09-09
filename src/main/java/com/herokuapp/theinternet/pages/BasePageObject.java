package com.herokuapp.theinternet.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class describes methods that can be the same as for child pageObject classes
public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// open page by its url
	protected void openUrl(String url) {
		driver.get(url);
	}
	
	//get page URL
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/** Get title of current page */
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}
	
	/** Get source of current page */
	public String getCurrentPageSource() {
		return driver.getPageSource();
	}


	// Find element by locator
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}
	
	//Find all elements by locator
	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}
	
	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).click();
	}

	/** Type given text into element with given locator */
	protected void typeInto(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}
	
	//Switch to JavaScript alert
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
//Check if the element is visible or not
	protected boolean isVisible(By locator) {
		waitForVisibilityOf(locator, 5);
		WebElement element = find(locator);
		if (element.isDisplayed() == false) {
			log.info("The element" + element + "is not visible");
			return false;
		}
		return true;
	}
	
//Get text from the element
	protected String getTextFrom(By locator) {
		waitForVisibilityOf(locator, 5);
		WebElement element = find(locator);
		String text = element.getText();
		if (text==null||text.isEmpty()) {
			log.info("The element " + element + "does not contain text or text is invisible");
		}
		return text;
	}
	
	//Switch to the window with provided title
	protected void switchToWindowWithTitle(String title) {
		String firstWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		log.info("Number of windows: " + allWindows.size());
		Iterator<String> it = allWindows.iterator();
		while (it.hasNext()) {
			String windowHandle = it.next().toString();
			if (!windowHandle.equals(firstWindow)) {
				driver.switchTo().window(windowHandle);
				if (getCurrentPageTitle().equals(title)) {
					break;
				}
				
			}
			
		}
	}
	
	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}
	
	//A method to perform scroll to the bottom of the page using JS Executor
	protected void scrollToBottom() {
		log.info("Scrolling to the bottom of the page");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollTo (0, document.body.scrollHeight)");
	}
	
	//hover over some element
	protected void hoverOver(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).build().perform();
	}
	
	//Add cookie
	public void setCookie(Cookie ck) {
		log.info("Adding cookie " + ck.getName());
		driver.manage().addCookie(ck);
		log.info("Cookie added");
	}
	
	//Get Cookie
	public String getCookie(String name) {
		log.info("Getting value of cookie " + name);
		return driver.manage().getCookieNamed(name).getValue();
	}
	
	
	
	
}
