package com.obsqura.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
	
	@Test(dataProvider = "getData")
	public void incorrectLoginTest(String username, String password) {
		String alert = lp.incorrectLoginCheck(username, password);
		Assert.assertEquals(alert, "Invalid Username/Password", "Invalid User Validation Failed");
	}
	
	@Test()
	public void loginTest() {
		lp.loginApplication();
		
		
	}
	

	@DataProvider
	public Object[][] getData() {
		
		Object[][] data = new Object[2][2];
		
		data[0][0]="admin";
		data[0][1]="123";
		data[1][0]="yui";
		data[1][1]="admin";
		
		return data;
		
		}
	
	
	

}
