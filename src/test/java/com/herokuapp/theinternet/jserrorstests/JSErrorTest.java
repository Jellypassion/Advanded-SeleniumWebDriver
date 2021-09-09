package com.herokuapp.theinternet.jserrorstests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.logging.LogEntry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.JSErrorPage;

public class JSErrorTest extends TestUtilities {
	
	@Test
	public void jsErrorTest() {
		log.info("Starting jsErrorTest");
		SoftAssert softAssert = new SoftAssert();
		JSErrorPage jsErrorPage = new JSErrorPage(driver, log);
		jsErrorPage.openPage();
		
		//get logs
		List<LogEntry> logs = getBrowserLogs();
		
		//get only severe logs from all log entries
		for (LogEntry logEntry : logs) {
		if (logEntry.getLevel().toString().equals("SEVERE")) {
				softAssert.fail("Severe Error: " + logEntry.getMessage());
			}
		}
		softAssert.assertAll();	
			
	}
	
	
}
