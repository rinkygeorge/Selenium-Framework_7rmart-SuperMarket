package com.obsqura.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.obsqura.Listeners.RetryAnalyzer;

public class AddAdminUserTest extends BaseTest {
	
	@Test
	public void addAdminUser() throws InterruptedException
	{	
		lp.loginApplication();
		hp.navigateToAddAdminUsers();
		String alert = aau.addUser();
		Assert.assertEquals(alert, "User Created Successfully", "User Creation Validation Failed");
		
	}
	
	@Test
	public void addExistingUser() throws InterruptedException
	{	
		
		hp.navigateToAddAdminUsers();
		String alertText = aau.existingUserCheck();
		Assert.assertEquals(alertText, "Username already exists.", "Existing User Validation Failed");
	}
	
	@Test
	public void searchUser() throws InterruptedException {
		
		hp.navigateToAddAdminUsers();
		aau.search();
	}
}
