package com.herokuapp.theinternet.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

//@Listeners({ com.herokuapp.theinternet.base.TestListener.class })
public class BaseTest {

	protected WebDriver driver;
	protected Logger log;
	protected String testSuiteName;
	protected String testName;
	protected String testMethodName;

	@Parameters({ "browser", "chromeProfile", "diviceName" })
	@BeforeMethod(alwaysRun = true)
	// If NO parameter named "browser" is found in testng.xml file,
	// your test method will receive the default value specified inside the
	// @Optional annotation: "chrome".
	public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
			@Optional String deviceName, ITestContext ctx) {
		// Реализация позволяет добавить логирование чечрез log4j
		String testName = ctx.getCurrentXmlTest().getName();// gets the test name
		log = LogManager.getLogger(testName);
		// Создаем соответствующий драйвер согласно указанного в параметрах браузера
		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);

		if (profile != null) {
			driver = factory.createChromeWithProfile(profile);
		} else if (deviceName != null) {
			driver = factory.createChromeWIthMobileEmulation(deviceName);
		} else {
			driver = factory.createDriver();
		}

		driver.manage().window().maximize();

		this.testSuiteName = ctx.getSuite().getName();
		this.testName = testName;
		this.testMethodName = method.getName();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");
		// Close browser
		driver.quit();
	}

}
