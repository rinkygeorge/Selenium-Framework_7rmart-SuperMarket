package com.obsqura.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageUtility {

	WebDriver driver;
	int index;

	public PageUtility(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setTextBox(WebElement element, String value ) {
		element.clear();
		element.sendKeys(value);
	}

	public void elementClick(WebElement element) {
		element.click();
	}

	public String getElementText(WebElement element) {
		return element.getText();
	}

	public void AcceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}


	public void staticDropDown(WebElement dropdown, String value) {

		Select staticDropdown = new Select(dropdown);
		staticDropdown.selectByVisibleText(value);
		

	}

	public void getCalendarValue(WebElement Date, WebElement month, String value, WebElement next, List<WebElement> days, String datevalue) 

	{

		elementClick(Date);
		while(!month.getText().contains(value)) {
			elementClick(next);

		}


		for(WebElement day:days) {
			
			explicitWait(day);
			if(day.getText().equals(datevalue)) {
				elementClick(day);
				break;
			}
		}


	}
	
	
	public void explicitWait(WebElement save) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
		
		wait.until(ExpectedConditions.elementToBeClickable(save));

	}
	

	public void pageScroll(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		//js.executeScript("window.scrollBy(0,10000);");
	}

	public void pageScrollFixed(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("window.scrollBy(0,300);");
	}
	
	public void uploadFile(WebElement element) throws AWTException {
		
			
		// creating object of Robot class
	    Robot rb = new Robot();
	    
	 // copying File path to Clipboard
	    StringSelection str = new StringSelection("C:\\Users\\Administrator\\Downloads\\TestCases.xlsx");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

	    rb.delay(250);
	     rb.keyPress(KeyEvent.VK_ENTER);
	     rb.keyRelease(KeyEvent.VK_ENTER);
	 // press Contol+V for pasting
	     rb.keyPress(KeyEvent.VK_CONTROL);
	     rb.keyPress(KeyEvent.VK_V);
	 
	    // release Contol+V for pasting
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	    rb.keyRelease(KeyEvent.VK_V);
	 
	    // for pressing and releasing Enter
	    rb.keyPress(KeyEvent.VK_ENTER);
	    rb.delay(90);
	    rb.keyRelease(KeyEvent.VK_ENTER);
	   }


}	

