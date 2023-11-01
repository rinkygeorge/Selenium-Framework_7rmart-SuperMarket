package com.obsqura.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.obsqura.utilities.PageUtility;

public class HomePage extends PageUtility {
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "ul[role ='menu'] li:nth-child(4)")
	WebElement ManageExp;	

	@FindBy(css = "li[class='nav-item has-treeview menu-open'] ul:nth-child(3)")
	WebElement ManageExpense;
	
	@FindBy(xpath = "(//li[@class='nav-item'])[21]")
	WebElement AdminUsers;
	
	
	public void navigateToExpenseCategory() {
		elementClick(ManageExp);
		elementClick(ManageExpense);
	}
	
	public void navigateToAddAdminUsers() throws InterruptedException {
		
		pageScroll(AdminUsers);	 
		Thread.sleep(2000);
		elementClick(AdminUsers);
		
	}


}
