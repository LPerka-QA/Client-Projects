package com.GoClinical_WorkOrder_ExecutionSuiteClasses;

import java.io.IOException;

import org.testng.annotations.Test;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class TestClass extends GoClinical_WorkOrder_AutomationTesting_ExecutionSuite{
	
	
	@Test
	public void abcd() throws BiffException, WriteException, IOException, InterruptedException {
		
		AdminUserNewWorkOrder();
		NurseUserMyWorkOrders();
		AdminUserReviewRecords();
		
	}

}

