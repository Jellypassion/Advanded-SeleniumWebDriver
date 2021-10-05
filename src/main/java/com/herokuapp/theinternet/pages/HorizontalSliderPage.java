package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HorizontalSliderPage extends BasePageObject {
	private String pageUrl = "https://the-internet.herokuapp.com/horizontal_slider";
	private By sliderLocator = By.xpath("//input[@type='range']");
	private By rangeLocator = By.xpath("//span[@id='range']");
	
	public HorizontalSliderPage(WebDriver driver, Logger log) {
		super(driver, log);
		// TODO Auto-generated constructor stub
	}
	//open slider page
	public void openPage() {
		log.info("Opening page " + pageUrl);
		openUrl(pageUrl);
	}
	//move slider to a specific value
	//move slider: normal method
	//find xOffset using given value
	//max value 5.0, step - 0.5 (10 steps total)
	//since drag-n-drop is performed from the center of locator, the central point of
	//movement is 2.5. If range<2.5 the slider moves to the left, if range>2.5 - to the right
	public void moveSliderTo(String value) {
		log.info("Moving slider to " + value);
		int width = find(sliderLocator).getSize().getWidth();
		double doubleWidth = Double.valueOf(width);
		double doubleValue = Double.parseDouble(value);
		//int xOffset = (int) (width * percent);
		double step = width / 10;
		Actions a = new Actions(driver);
		//double range = 0.0;
		var range = (doubleValue - 2.5) / 0.5 * step;
		a.dragAndDropBy(find(sliderLocator), (int) range, 0).build().perform();
	
	}
		
	
	
	//get slider value
	public double getOffsetValue() {
		log.info("Getting range value");
		String stringValue = find(rangeLocator).getText();
		return Double.parseDouble(stringValue);
	}
	
	
	
}
