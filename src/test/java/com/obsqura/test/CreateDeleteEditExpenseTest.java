package com.obsqura.test;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.obsqura.utilities.DateUtility;
import com.obsqura.utilities.GenerateRandomNumber;

public class CreateDeleteEditExpenseTest extends BaseTest {

	@Test (priority = 0)
	public void createExpense() throws InterruptedException, AWTException  {
		lp.loginApplication();
		hp.navigateToExpenseCategory();

		String currentDate = DateUtility.getCurrentDate();
		int randomNumber = GenerateRandomNumber.getRandomNumber();
		String alertTextCreation = cdp.addExpense(randomNumber); 
		Assert.assertEquals(alertTextCreation, "Expense Record Created Successfully", "Record Creation Validation Failed");
		
	}
	
	
	@Test (priority = 1)
	public void editExpense() throws InterruptedException  {
		hp.navigateToExpenseCategory();
		int andomNumberEdit = GenerateRandomNumber.getRandomNumber();
		String alertText = cdp.editExpense(andomNumberEdit);
		Assert.assertEquals(alertText, "Expense Record Updated Successfully", "Edit Validation Failed");
		
	}
	
	@Test (priority = 2)
	public void deleteExpense() throws InterruptedException  {
		hp.navigateToExpenseCategory();
		String alertTextDeletion = cdp.deleteExpense();
		Assert.assertEquals(alertTextDeletion, "Expense Record Deleted Successfully", "Record Deletion Validation Failed");
		 
	}
	
	
	@Test(priority = 3)
	public void invalidInputTest() throws InterruptedException {
		
		
		hp.navigateToExpenseCategory();
		String message = cdp.searchInvalidData();
		Assert.assertEquals(message,".........RESULT NOT FOUND.......", "Result Not Found Validation Failed");
		
	}

}
