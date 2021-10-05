package com.herokuapp.theinternet.slidertests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.HorizontalSliderPage;

public class HorizontalSliderTests extends TestUtilities {

	
	@Test
	public void moveHorizontalSlider() {
		
		//open slider page
		HorizontalSliderPage sliderPage = new HorizontalSliderPage(driver, log);
		sliderPage.openPage();
		//move slider to value 3
		sliderPage.moveSliderTo("1.5");
		sleep(2000);
		//get the range value and verify it's correct
		var range = sliderPage.getOffsetValue();
		Assert.assertEquals(range, 3);
	}
	
	
}
