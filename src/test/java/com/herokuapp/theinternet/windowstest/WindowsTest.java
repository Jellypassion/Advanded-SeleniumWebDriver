package com.herokuapp.theinternet.windowstest;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.NewWindowPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WindowsPage;

public class WindowsTest extends TestUtilities{
	
	@Test
	public void findTextInNewWindow() {
		String neededText = "New Window";
		WelcomePage welcomePage = new WelcomePage(driver, log);
		welcomePage.openPage();
		//Navigate to Windows page
		WindowsPage windowsPage = welcomePage.openWidowsPage();
		//Click on the link which opens new window
		windowsPage.openNewWindow();
		sleep(2000);
		//switch to new window
		NewWindowPage newWindow = windowsPage.switchToNewWindowPage();
		//Check if the page contains needed text
		Assert.assertTrue(newWindow.doesPageContainText(neededText), "There is no such text on the page");
		
	
	}

}
