package com.NurseUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.NurseUser.CommonPages.GoClinical_NurseUser_Menu;
import com.NurseUser.PagesData.NurseUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import jxl.read.biff.BiffException;

public class NurseUser_MyWorkOrders extends BasePages {
	
	
	AdminUser_LoginPageData Admindata = new AdminUser_LoginPageData();
	NurseUser_LoginPageData data = new NurseUser_LoginPageData();
	GoClinical_NurseUser_Menu MenuPage = new GoClinical_NurseUser_Menu();
	
	String WorkOrder;
	
	
	
	
	public void NurseUser_Home(int row) {	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

// Objects from Application
// My Work Orders
	/*public static WebElement readonly_MyWorkOrdersHeader() {
		return driver.findElement(By.xpath("//div[@class='work_orders']//h2[text()='My Work Orders']"));

	}*/
	
	public WebElement lnk_MyWrkOrdrsLtstRcrd(int row) {
     
		WorkOrder = data.Getdata("Work Order Number", row);		
		String WorkOrderNumber = WorkOrder.substring(11);
		
		
		return driver.findElement(By.xpath("//a[@data-work-order-id='"+WorkOrderNumber+"' and @class='list-group-item work-order-without-record']"));

	}
	
	public static WebElement readonly_VitalSignsHeader() {
		return driver.findElement(By.xpath("//div[@class='col-xs-12']//h1"));

	}
	
	public static WebElement readonly_PatientID() {
		return driver.findElement(By.xpath("//form[@id='1']//p[contains(text(),'Patient ID: ')]//strong"));

	}

	public static WebElement txt_BloodPressure_Systolic_mm_Hg() {
		return driver.findElement(By.xpath("//input[@id='4' and @class='form-control has-feedback']"));

	}

	public static WebElement txt_BloodPressure_Diastolic_mm_Hg() {
		return driver.findElement(By.xpath("//input[@id='2' and @class='form-control has-feedback']"));

	}

	public static WebElement txt_HeartRate() {
		return driver.findElement(By.xpath("//input[@id='3' and @class='form-control has-feedback']"));

	}

	public static WebElement btn_Continue() {
		return driver.findElement(By.xpath("//button[text()='Continue' and @type='submit']"));

	}

	public WebElement btn_Back() {
		return driver.findElement(By.xpath("//button[text()='Back' and @type='button']"));

	}
	
	public static WebElement txt_ReEnterPassword() {
		return driver.findElement(By.xpath("//input[@id='password' and @type='password']"));

	}
	
	public static WebElement btn_SendtoHomeOffice() {
		return driver.findElement(By.xpath("//button[@class='btn btn-primary pull-right' and text()='Send to Home Office']"));

	}
	
	public static WebElement btn_SubmitforReview() {
		return driver.findElement(By.xpath("//button[@class='btn btn-primary pull-right' and text()='Submit for Review']"));

	}
	
	public static WebElement btn_Edit() {
		return driver.findElement(By.xpath("//button[@class='btn btn-primary' and text()='Edit']"));

	}

	public static WebElement readonly_RecordSubmitted() {
		return driver.findElement(By.xpath("//div[@class='col-xs-12']//h1"));

	}
	
	public static WebElement readonly_RecordSubmittedMsg() {
		return driver.findElement(By.xpath("//div[@class='col-xs-12']//p"));

	}

	

	// Each Object Performance Method
	// My Work Orders Info
	
	public void ClickMyWrkOrdrLtstRcrd(int row) throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickMainMenu(lnk_MyWrkOrdrsLtstRcrd(row));
	}
	
	public void EnterBP_Systolic_mm_Hg(int row) throws IOException {
		String BloodPressureSystolic_mm_Hg = data.Getdata("Blood Pressure Systolic mm Hg", row).trim();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		enterText(txt_BloodPressure_Systolic_mm_Hg(), "BLOOD PRESSURE (SYSTOLIC MM HG)", BloodPressureSystolic_mm_Hg);
	}	

	public void EnterBP_Diastolic_mm_Hg(int row) throws IOException {
		String BloodPressureDiastolic_mm_Hg = data.Getdata("Blood Pressure Diastolic mm Hg", row).trim();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		enterText(txt_BloodPressure_Diastolic_mm_Hg(), "BLOOD PRESSURE (DIASTOLIC MM HG)", BloodPressureDiastolic_mm_Hg);
	}	

	public void EnterHeartRate(int row) throws IOException {
		String HeartRate = data.Getdata("Heart Rate", row).trim();

		enterText(txt_HeartRate(), "HEART RATE", HeartRate);
	}

	public void ClickContinue() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnButton(btn_Continue(), "Continue");
	}	
	
	public void EnterPassword() throws IOException {

		enterText(txt_ReEnterPassword(), "Re-Enter Password", LaunchBrowser.TestSettingsObjects.getProperty("NurseUserPassword"));
	}

	public void ClickSendtoHomeOffice() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnButton(btn_SendtoHomeOffice(), "Send to Home Office");
	}
	
	public void ClickSubmitforReview() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnButton(btn_SubmitforReview(), "Submit for Review");
	}
		
	// Each Object Performance Method
		// Verification Section

	/*public void ValidateMyWorkOrdersHeader(int row) throws IOException {
		String ExpectedMyWrkOrdrsHeader = data.Getdata("Expected My Work Orders Header", row).trim();
		verifyTextEqual(readonly_MyWorkOrdersHeader(), ExpectedMyWrkOrdrsHeader,
				"Validate My Work Orders Header");

	}*/
	
	public void ValidateVitalSignsHeader(int row) throws IOException {
		String ExpectedVitalSignsHeader = data.Getdata("Expected Vital Signs Header", row).trim();
		verifyTextEqual(readonly_VitalSignsHeader(), ExpectedVitalSignsHeader,
				"Validate Vital Signs Header");

	}
	
	public void ValidatePatientID(int row) throws IOException {
		String ExpectedPatientID = data.Getdata("Patient ID", row).trim();
		verifyTextEqual(readonly_PatientID(), ExpectedPatientID,
				"Validate Patient ID");

	}
	
	public void ValidateRecordSubmitted(int row) throws IOException {
		String ExpectedRecordSubmitted = data.Getdata("Expected Submit Record", row).trim();
		verifyTextEqual(readonly_RecordSubmitted(), ExpectedRecordSubmitted,
				"Validate Record Submitted Message");

	}
	
	public void GetRecordSubmittedTxt(int row) throws IOException, BiffException {
		String RecordSubmittedTxt = readonly_RecordSubmittedMsg().getText();
		data.setData("Record Submitted Text", row, RecordSubmittedTxt);

	}
	
	
	// Actual Functional Method 

	public void MyWorkOrders_VitalSigns(int row) throws IOException, InterruptedException, BiffException {
		
		NurseUser_Home(row);
		/*ValidateMyWorkOrdersHeader(row);*/
		BasePages.scrollElementIntoView(lnk_MyWrkOrdrsLtstRcrd(row));
		ClickMyWrkOrdrLtstRcrd(row);		
		ValidateVitalSignsHeader(row);
		ValidatePatientID(row);
		EnterBP_Systolic_mm_Hg(row);				
		EnterBP_Diastolic_mm_Hg(row);				
		EnterHeartRate(row);
		BasePages.scrollElementIntoView(btn_Continue());
		ClickContinue();				
		EnterPassword();
		BasePages.scrollElementIntoView(btn_SubmitforReview());
		ClickSubmitforReview();
		Thread.sleep(4000);
		ValidateRecordSubmitted(row);
		GetRecordSubmittedTxt(row);
				
	}

}