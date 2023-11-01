package com.obsqura.Listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.obsqura.test.BaseTest;
import com.obsqura.utilities.ExtentReporterNG;
import com.obsqura.utilities.PageUtility;


public class ReportListeners extends BaseTest implements ITestListener {


	ExtentReports extent = ExtentReporterNG.getReport();
	ExtentTest test;
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {	
		
		test = extent.createTest(result.getMethod().getMethodName());
		//setting object test into threadLocal object
		extentTest.set(test);
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passes");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) { 
		//object result holds all the information about the failed test case inside this method
		//similarly for each test case a driver will be assigned. That driver will also be stored in this 
		//result variable 
		
		//this will print the error message in the report
		extentTest.get().fail(result.getThrowable());
		//to get screenshot		
		
		try {
			//this is to get the driver from failed test case from the result object coz there is no driver in this class
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
	    String filepath = null;
		try {
			//this is get screenshot from the method written in base test
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // to add screenshot to your report
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		ITestListener.super.onFinish(context);
	}

	

}
