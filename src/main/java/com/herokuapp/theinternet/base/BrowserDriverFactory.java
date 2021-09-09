package com.herokuapp.theinternet.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserDriverFactory {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	private Logger log;
	
	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}
	
	public WebDriver createDriver() {
		// Create driver
		log.info("Create driver: " + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
			driver.set(new ChromeDriver());
			break;
			
		case "safari":
			System.setProperty("webdriver.safari.driver", "src/main/resources/safaridriver");
			driver.set(new SafariDriver());
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
			driver.set(new FirefoxDriver());
			break;

		default:
			System.out.println("Do not know how to start: " + browser + ", starting chrome instead.");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
			driver.set(new ChromeDriver());
			break;
		}
		return driver.get();
	}
	
	public WebDriver createChromeWithProfile(String profile) {
		log.info("Starting Chrome with profile: " + profile);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("user-data-dir=src/main/resources" + profile);
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		driver.set(new ChromeDriver(chromeOptions));
		return driver.get();
	}
	
	public WebDriver createChromeWIthMobileEmulation(String deviceName) {
		log.info("Stating driver with " + deviceName + "Simulation");
		Map<String, String> mobileEmulation = new HashMap<>();
		mobileEmulation.put("deviceName", deviceName);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		driver.set(new ChromeDriver(chromeOptions));
		return driver.get();
	}
	
	
	
	

}
