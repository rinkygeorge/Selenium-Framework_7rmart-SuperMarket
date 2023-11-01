package com.obsqura.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.obsqura.utilities.PageUtility;

public class CreateDeleteEditExpense extends PageUtility {

	WebDriver driver;

	public CreateDeleteEditExpense(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li[class='nav-item has-treeview menu-open'] ul:nth-child(3)")
	WebElement ManageExpense;

	@FindBy(css = "div[class='col-sm-12'] a[class='btn btn-rounded btn-danger']")
	WebElement New;

	@FindBy(css = "div[class='col-sm-12'] a[class='btn btn-rounded btn-primary']")
	WebElement MenuSearch;	

	@FindBy(css = "select[id='user_id']") 
	WebElement User;

	@FindBy(css = "input[class='form-control date']")
	WebElement Date;

	@FindBy(xpath = "(//th[@class='datepicker-switch']) [1]")
	WebElement Selectmonth;

	@FindBy(xpath = "(//th[@class='next']) [1]")
	WebElement Nexticon;

	@FindBy(css = "td[class='day']")
	List<WebElement> Days;

	@FindBy(xpath = "(//select[@class='form-control selectpicker'])[2]")
	WebElement Category;

	@FindBy(id = "ex_type")
	WebElement Type;

	@FindBy(css = "input[id='amount']")
	WebElement Amount;

	@FindBy(id = "content")
	WebElement Remarks;

	@FindBy(xpath = "//button[@name='create']")
	WebElement save;

	@FindBy(xpath = "//tbody/tr/td[9]/a[1]")
	WebElement Edit;

	@FindBy(css = "button[name='update']")
	WebElement Update;

	@FindBy(xpath = "//tbody/tr/td[9]/a[2]")
	WebElement Delete;

	@FindBy(xpath = "(//input[@placeholder='Enter the Date'])[1]")
	WebElement DateFrom;

	@FindBy(xpath = "(//input[@placeholder='Enter the Date'])[2]")
	WebElement DateTo;

	@FindBy(css = "button[name='Search']")
	WebElement FormSearch;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement AlertText ;

	@FindBy(css = "select[class='form-control selectpicker']")
	WebElement UserinSearch;

	@FindBy(id = "ty")
	WebElement TypeinSearch;

	@FindBy(css = "span[id='res']")
	WebElement ResultNotFound;
	
	@FindBy(css = "input[name='userfile']")
	WebElement UploadFile;

	

	public String addExpense( int randomNumber) throws InterruptedException, AWTException {

		elementClick(New); 
		String user = "Elsa(Admin)";
		staticDropDown(User, user);
		String month = "December";
		String dateValue = "5";
		getCalendarValue(Date, Selectmonth, month, Nexticon, Days, dateValue);
		String category = "FruitNew";
		staticDropDown(Category, category);
		String type = "Debit Bank";
		staticDropDown(Type, type); 
		setTextBox(Amount, Integer.toString(randomNumber));
		Thread.sleep(1000);
		pageScroll(save);
		Thread.sleep(2000);		
		elementClick(save);
		String alertText = getElementText(AlertText);
		System.out.println(alertText.split("Alert!")[1].trim());
		return alertText.split("Alert!")[1].trim();

	}


	public String searchInvalidData() throws InterruptedException {

		elementClick(MenuSearch); 
		String user = "Elsa(Admin)";
		staticDropDown(UserinSearch, user);
		String month = "October";
		String dateValue = "1";
		getCalendarValue(DateFrom, Selectmonth, month, Nexticon, Days, dateValue);	
		String nextmonth = "December";
		String nextdateValue =  "30";
		getCalendarValue(DateTo, Selectmonth, nextmonth, Nexticon, Days, nextdateValue);
		String category = "newitem";
		staticDropDown(Category, category);
		String type = "Credit Bank";
		staticDropDown(TypeinSearch, type); 
		pageScrollFixed(FormSearch);	 
		Thread.sleep(3000);
		elementClick(FormSearch);
		pageScrollFixed(FormSearch);		
		String message = getElementText(ResultNotFound);
		System.out.println(message);
		return message;
	}


	public String editExpense(int randomNumber) throws InterruptedException {


		elementClick(MenuSearch);
		staticDropDown(UserinSearch, "Elsa(Admin)");
		String month = "October";
		String dateValue = "1";
		getCalendarValue(DateFrom, Selectmonth, month, Nexticon, Days, dateValue);	
		String nextmonth = "December";
		String nextdateValue =  "30";
		getCalendarValue(DateTo, Selectmonth, nextmonth, Nexticon, Days, nextdateValue);
		pageScrollFixed(FormSearch);	 
		Thread.sleep(3000);
		elementClick(FormSearch); 
		pageScrollFixed(Edit);
		Thread.sleep(2000);

		elementClick(Edit);
		setTextBox(Amount, Integer.toString(randomNumber));
		pageScroll(Update);	  
		Thread.sleep(1000);
		elementClick(Update);
		String alertText = getElementText(AlertText);
		System.out.println(alertText.split("Alert!")[1].trim());
		return alertText.split("Alert!")[1].trim();


	}

	public String deleteExpense() throws InterruptedException {


		elementClick(MenuSearch);
		staticDropDown(UserinSearch, "Elsa(Admin)");
		String month = "October";
		String dateValue = "1";
		getCalendarValue(DateFrom, Selectmonth, month, Nexticon, Days, dateValue);	
		String nextmonth = "December";
		String nextdateValue =  "30";
		getCalendarValue(DateTo, Selectmonth, nextmonth, Nexticon, Days, nextdateValue);
		pageScrollFixed(FormSearch);	 
		Thread.sleep(3000);
		elementClick(FormSearch); 
		pageScrollFixed(Delete);
		Thread.sleep(2000);
		elementClick(Delete);
		AcceptAlert();
		String alertText = getElementText(AlertText);
		System.out.println(alertText.split("Alert!")[1].trim());
		return alertText.split("Alert!")[1].trim();

	}

	/*
	 * public void ValidateFileUpload() throws AWTException { uploadFile(); }
	 */







}
