package com.AdminUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.AdminUser.CommonPages.GoClinical_AdminUser_Menu;
import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import jxl.read.biff.BiffException;

public class AdminUser_Records extends BasePages {
	

	AdminUser_LoginPageData data = new AdminUser_LoginPageData();
	GoClinical_AdminUser_Menu MenuPage = new GoClinical_AdminUser_Menu();
	String WorkOrder;
	String WorkOrderNumber;
	String Protocol;
	String PatientID;
	String Visit;
	String RecordCreatedDate;
	String TextField1;
	String NumberField1;
	String Radio1;
	
	
	
	
	
	public void AdminUser_Home(int row) {
		
		WorkOrder = data.Getdata("Work Order Number", row);		
		WorkOrderNumber = WorkOrder.substring(11);
		Protocol = data.Getdata("Protocol", row).trim();
		PatientID = data.Getdata("Patient ID", row).trim();
		Visit = data.Getdata("Visit", row).trim();		
		RecordCreatedDate = data.Getdata("Latest Record Created Date", row);
		
		/*TextField1 = data.Getdata("TEXT FIELD 1", row).trim();
		NumberField1 = data.Getdata("NUMBER FIELD 1", row).trim();	
		Radio1 = data.Getdata("Radio 1", row);*/

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static WebElement lnk_Records() {
		return driver.findElement(By.xpath("//li//a[@href='/records' and text()='Records']"));

	}
	
	public static WebElement readonly_RecordsHeader() {
		return driver.findElement(By.xpath("//div[@id='records_index']//h1"));

	}
	
	public WebElement readonly_LatestRecord() {
		
		return driver.findElement(By.xpath("//td[text()='"+WorkOrderNumber+"']//..//td[text()='"+Protocol+"']//..//td[text()='"+PatientID+"']//..//td[text()='"+Visit+"']//..//td[text()='"+RecordCreatedDate+"']//..//td[text()='REVIEW']//..//td//a[contains(@href,'/records/')]"));

	}	
	
	public WebElement Lnk_ViewLatestRecord() {
		return driver.findElement(By.xpath("//td[text()='"+WorkOrderNumber+"']//..//td[text()='"+Protocol+"']//..//td[text()='"+PatientID+"']//..//td[text()='"+Visit+"']//..//td[text()='"+RecordCreatedDate+"']//..//td[text()='REVIEW']//..//td//a[contains(@href,'/records/') and text()='View']"));
											
	}
	
	public static WebElement Lnk_Next() {
		return driver.findElement(By.xpath("//a[@href='/records?page=2' and text()='Next ›']"));

	}
	
	public  static WebElement readonly_WorkOrdersID() {
		return driver.findElement(By.xpath("//table[@class='table table-condensed']//tbody//tr[3]//th[text()='Work Order ID:']//..//td"));

	}
	
	public  static WebElement readonly_PatientID() {
		return driver.findElement(By.xpath("//table[@class='table table-condensed']//tbody//tr[4]//th[text()='Patient ID:']//..//td"));

	}
	
	public  static WebElement readonly_StudyVisit() {
		return driver.findElement(By.xpath("//table[@class='table table-condensed']//tbody//tr[5]//th[text()='Study Visit:']//..//td"));

	}
	
	public static WebElement txt_Password() {
		return driver.findElement(By.xpath("//input[@id='password' and @type='password']"));

	}
	
	public WebElement btn_SendToStudySite() {
		return driver.findElement(By.xpath("//input[@value='Send to Study Site' and @type='submit']"));

	}
	
	public static WebElement readonly_BloodPressure_Systolic_mm_Hg() {
		return driver.findElement(By.xpath("//h2[text()='Vital Signs']//..//td[contains(text(),'Blood Pressure (Systolic mm Hg)')]//..//td[2][contains(text(),'')]"));

	}
	
	public static WebElement readonly_BloodPressure_Diastolic_mm_Hg() {
		return driver.findElement(By.xpath("//h2[text()='Vital Signs']//..//td[contains(text(),'Blood Pressure (Diastolic mm Hg)')]//..//td[2][contains(text(),'')]"));

	}
	
	public static WebElement readonly_HeartRate() {
		return driver.findElement(By.xpath("//h2[text()='Vital Signs']//..//td[contains(text(),'Heart Rate')]//..//td[2][contains(text(),'')]"));

	}
	
	public WebElement readonly_TextField1() {
		return driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//tr//td[contains(text(),'Text Field 1')]//..//td[2][contains(text(),'')]"));

	}
	
	public WebElement readonly_NumberField1() {
		return driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//tr//td[contains(text(),'Number Field 1')]//..//td[2][contains(text(),'')]"));

	}
	
	public WebElement readonly_Radio1() {
		return driver.findElement(By.xpath("//table[@class='table table-bordered']//tbody//tr//td[contains(text(),'Radio 1')]//..//td[2][contains(text(),'')]"));

	}
	
	
	public WebElement readonly_RecordSubmittedMsg() {
		return driver.findElement(By.xpath("//div[@class='notice']//p"));

	}
	
	public WebElement readonly_AuditTrailStatus() {
		return driver.findElement(By.xpath("//section[@class='version-history']//td[text()='Status']//..//td[text()='REVIEW']//..//td[4]"));

	}	
	
	

	

	// Each Object Performance Method
	// Records
	
	public void ClickRecordsHeader() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnLink(lnk_Records(), "Records ");
	}
	
	public void ClickViewLatestRecord() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnLink(Lnk_ViewLatestRecord(), "View");
	}
	
	public void EnterPassword(int row) throws IOException {
		enterText(txt_Password(), "Password", LaunchBrowser.TestSettingsObjects.getProperty("AdminUserPassword"));
	}
	
	public void ClickSendToStudySite() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnButton(btn_SendToStudySite(), "Send to Study Site");
	}
	
		
	// Each Object Performance Method
		// Verification Section

	public void ValidateRecordsHeader(int row) throws IOException {
		String ExpectedRecordsHeader = data.Getdata("Expected Admin User Records Header", row).trim();
		verifyCorrectPageHeading(readonly_RecordsHeader(), ExpectedRecordsHeader);

	}
	
	public void ValidateLatestRecord(int row) throws IOException {
		String ExpectedLatestRecord = data.Getdata("Expected Admin User Latest Record", row).trim();
		verifyTextEqual(readonly_LatestRecord(), ExpectedLatestRecord,
					"Validate latest Record");

	}
	
	public void ValidateWorkOrdersID(int row) throws IOException {
		String ExpectedWorkOrdersID = WorkOrderNumber;
		verifyTextEqual(readonly_WorkOrdersID(), ExpectedWorkOrdersID,
				"Work Order Number is ");

	}
	
	public void ValidatePatientID(int row) throws IOException {
		String ExpectedPatientID = data.Getdata("Patient ID", row).trim();
		verifyTextEqual(readonly_PatientID(), ExpectedPatientID,
				"Patient ID is ");

	}
	
	public void ValidateStudyVisit(int row) throws IOException {
		String ExpectedStudyVisit = data.Getdata("Visit", row).trim();
		verifyTextEqual(readonly_StudyVisit(), ExpectedStudyVisit,
				"Visit Value is ");

	}
	
	public void ValidateBloodPressureSystolic(int row) throws IOException {
		String ExpectedBPSystolic = data.Getdata("Blood Pressure Systolic mm Hg", row).trim();
		verifyTextEqual(readonly_BloodPressure_Systolic_mm_Hg(), ExpectedBPSystolic,
				"Blood Pressure Systolic Value is ");

	}
	
	public void ValidateBloodPressureDiastolic(int row) throws IOException {
		String ExpectedBPDiastolic = data.Getdata("Blood Pressure Diastolic mm Hg", row).trim();
		verifyTextEqual(readonly_BloodPressure_Diastolic_mm_Hg(), ExpectedBPDiastolic,
				"Blood Pressure Diastolic Value is");

	}
	
	public void ValidateHeartRate(int row) throws IOException {
		String ExpectedHeartRate = data.Getdata("Heart Rate", row).trim();
		verifyTextEqual(readonly_HeartRate(), ExpectedHeartRate,
				"Heart Rate Value is");

	}
	
	
	public void ValidateTextField1(int row) throws IOException {
		String ExpectedTextField1 = data.Getdata("TEXT FIELD 1", row).trim();;
		verifyTextEqual(readonly_TextField1(), ExpectedTextField1,
				"Text Field 1 Value is ");

	}
	
	public void ValidateNumberField1(int row) throws IOException {
		String ExpectedNumberField1 = data.Getdata("NUMBER FIELD 1", row).trim();
		verifyTextEqual(readonly_NumberField1(), ExpectedNumberField1,
				"Number Field 1 Value is ");

	}
	
	public void ValidateRadio1(int row) throws IOException {
		String ExpectedRadio1 = data.Getdata("RADIO 1", row).trim();
		verifyTextEqual(readonly_Radio1(), ExpectedRadio1,
				"Radio 1 Value is ");

	}
	
	public void ValidateRecordSubmittedMsg(int row) throws IOException {
		String ExpectedWorkOrdersID = data.Getdata("Expected Admin Record Submitted", row).trim();;
		verifyTextEqual(readonly_RecordSubmittedMsg(), ExpectedWorkOrdersID,
				"Admin Record Submit");

	}
	
	public void ValidateAuditTrailStatus(int row) throws IOException {
		String ExpectedAuditTrailStatus = data.Getdata("Expected Audit Trail Status", row).trim();;
		verifyTextEqual(readonly_AuditTrailStatus(), ExpectedAuditTrailStatus,
				"Record Audit Trail Status");

	}
	
	
	
	// Actual Functional Method 

	public void Records(int row) throws IOException, InterruptedException, BiffException {
		
		
		ClickRecordsHeader();
		ValidateRecordsHeader(row);
		AdminUser_Home(row);
		if(driver.findElements(By.xpath("//td[text()='"+WorkOrderNumber+"']//..//td[text()='"+Protocol+"']//..//td[text()='"+PatientID+"']//..//td[text()='"+Visit+"']//..//td[text()='REVIEW']//..//td[text()='"+RecordCreatedDate+"']//..//td//a[contains(@href,'/records/')]") ).size() != 0)
		{
			BasePages.scrollElementIntoView(Lnk_ViewLatestRecord());
			ValidateLatestRecord(row);
		}
		else
		{
			BasePages.scrollElementIntoView(Lnk_Next());
			clickOnLink(Lnk_Next(), "Next");
			BasePages.scrollElementIntoView(Lnk_ViewLatestRecord());
			ValidateLatestRecord(row);
		}		
		ClickViewLatestRecord();
		ValidateBloodPressureSystolic(row);
		ValidateBloodPressureDiastolic(row);
		ValidateHeartRate(row);
		EnterPassword(row);
		ClickSendToStudySite();
		ValidateRecordSubmittedMsg(row);
		ValidateAuditTrailStatus(row);
	}
	
public void AutomationTestingRecords(int row) throws IOException, InterruptedException, BiffException {
		
		
		ClickRecordsHeader();
		ValidateRecordsHeader(row);
		AdminUser_Home(row);
		if(driver.findElements(By.xpath("//td[text()='"+WorkOrderNumber+"']//..//td[text()='"+Protocol+"']//..//td[text()='"+PatientID+"']//..//td[text()='"+Visit+"']//..//td[text()='REVIEW']//..//td[text()='"+RecordCreatedDate+"']//..//td//a[contains(@href,'/records/')]") ).size() != 0)
		{
			BasePages.scrollElementIntoView(Lnk_ViewLatestRecord());
			ValidateLatestRecord(row);
		}
		else
		{
			BasePages.scrollElementIntoView(Lnk_Next());
			clickOnLink(Lnk_Next(), "Next");
			ValidateLatestRecord(row);
		}	
		ClickViewLatestRecord();
		ValidateWorkOrdersID(row);
		ValidatePatientID(row);
		ValidateStudyVisit(row);
		ValidateTextField1(row);
		ValidateNumberField1(row);
		ValidateRadio1(row);
		EnterPassword(row);
		ClickSendToStudySite();
		ValidateRecordSubmittedMsg(row);
		ValidateAuditTrailStatus(row);
	}

}