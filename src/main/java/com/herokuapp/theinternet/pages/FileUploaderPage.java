package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploaderPage extends BasePageObject {

	private String pageUrl = "http://the-internet.herokuapp.com/upload";

	private By choseFileFieldLocator = By.id("file-upload");
	private By uploadButtonLocator = By.id("file-submit");
	private By uploadedFileLocator = By.id("uploaded-files");

	public FileUploaderPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/** Open FileUploaderPage with it's url */
	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

	/** Push Upload button */
	public void pushUploadButton() {
		log.info("Clicking on upload button");
		click(uploadButtonLocator);
	}

	/** Select a File */
	public void selectFile(String fileName) {
		log.info("Selecting '" + fileName + "' file from Files folder");
		// Selecting file
		String filePath = System.getProperty("user.dir") + "//src//main//resources//files//" + fileName;
		//file upload is done by using .sendKeys() method
		typeInto(filePath, choseFileFieldLocator);
		log.info("File selected");
	}

	/** Get the name of uploaded file */
	public String getUploadedFileName() {
		String name = find(uploadedFileLocator).getText();
		log.info("Uploaded file: " + name);
		return name;
	}

}
