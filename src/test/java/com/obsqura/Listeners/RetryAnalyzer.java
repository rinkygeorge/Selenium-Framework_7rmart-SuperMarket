package com.obsqura.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count =0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxTry) {
			return true;
		}
		return false;
	}

}
