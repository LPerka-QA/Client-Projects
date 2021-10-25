package com.Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
public class TestListener implements ITestListener  {
	public void onTestStart(ITestResult result) {
	
		System.out.println("Started Test: " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		 System.out.println("Finished Test: " + result.getName() + " :PASSED");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Finished Test: " + result.getName() + " :FAILED");
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Finished Test: " + result.getName() + " :SKIPPED");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		 System.out.println("Finished Test: " + result.getName()
         + " :FAILED BUT WITHIN SUCCESS PERCENTAGE");
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}
