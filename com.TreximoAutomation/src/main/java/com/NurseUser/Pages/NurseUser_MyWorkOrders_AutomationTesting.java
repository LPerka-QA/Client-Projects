package com.NurseUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.NurseUser.CommonPages.GoClinical_NurseUser_Menu;
import com.NurseUser.PagesData.NurseUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import jxl.read.biff.BiffException;

public class NurseUser_MyWorkOrders_AutomationTesting extends BasePages {
	
	
	
	NurseUser_LoginPageData data = new NurseUser_LoginPageData();
	GoClinical_NurseUser_Menu MenuPage = new GoClinical_NurseUser_Menu();
		
	String WorkOrder;
	String Radio1;
	
	
	public void NurseUser_Home() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

// Objects from Application
// My Work Orders
	/*public static WebElement readonly_MyWorkOrdersHeader() {
		return driver.findElement(By.xpath("//div[@class='work_orders']//h2"));

	}*/
	
	public WebElement lnk_MyWrkOrdrsLtstRcrd(int row) {
		WorkOrder = data.Getdata("Work Order Number", row);
		
		System.out.println(WorkOrder);
		String WorkOrderNumber = WorkOrder.substring(11);
		
		System.out.println(WorkOrderNumber);
		
		return driver.findElement(By.xpath("//a[@data-work-order-id='"+WorkOrderNumber+"' and @class='list-group-item work-order-without-record']"));

	}

	public static WebElement readonly_AutoTestingForm1Header() {
		return driver.findElement(By.xpath("//div[@class='col-xs-12']//h1"));

	}
	
	public static WebElement readonly_PatientID() {
		return driver.findElement(By.xpath("//form[@id='15']//p[contains(text(),'Patient ID: ')]//strong"));

	}
	
	public static WebElement readonly_WorkOrderID() {
		return driver.findElement(By.xpath("//form[@id='15']//..//strong[3]"));

	}

	public static WebElement txt_TextField1() {
		return driver.findElement(By.xpath("//input[@class='form-control has-feedback' and @type='text']"));

	}

	public static WebElement txt_NumberField1() {
		return driver.findElement(By.xpath("//input[@class='form-control has-feedback' and @type='number']"));

	}

	public static WebElement txt_FileField1() {	
		  
		   return driver.findElement(By.xpath("//input[@class='form-control has-feedback' and @type='file']"));		

	}
	
	public static WebElement txt_DateField1() {	
		  
		   return driver.findElement(By.xpath("//input[@class='form-control' and @type='date']"));		

	}
	
	public static WebElement txt_TimeField1() {	
		  
		   return driver.findElement(By.xpath("//input[@class='form-control has-feedback' and @type='time']"));		

	}
	
	public static WebElement dpd_Dropdown1() {	
		  
		   return driver.findElement(By.xpath("//select[@class='form-control has-feedback']"));		

	}
	
	public WebElement rdbtn_Radio1(int row) {
		
		Radio1 = data.Getdata("RADIO 1", row);
		  
		   return driver.findElement(By.xpath("//input[@value='"+Radio1+"' and @type='radio']"));		

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
	
	public void Enter_TextField1(int row) throws IOException {
		String TextField1 = data.Getdata("TEXT FIELD 1", row).trim();		
		enterText(txt_TextField1(), "TEXT FIELD 1", TextField1);
	}
	
	public void Enter_NumberField1(int row) throws IOException {
		String NumberField1 = data.Getdata("NUMBER FIELD 1", row).trim();		
		enterText(txt_NumberField1(), "NUMBER FIELD 1", NumberField1);
	}
	
	public void UploadFile() throws IOException {
		
		 //click on ‘Choose file’ to upload the desired file
		enterText(txt_FileField1(), "File Field 1", "C:\\Users\\lperka\\Downloads\\Treximo\\Treximo\\File Upload Testing 01.png");
	}
	
	public void Enter_DateField1(int row) throws IOException, InterruptedException {
		String DateField1 = data.Getdata("DATE FIELD 1", row).trim();		
		enterText(txt_DateField1(), "DATE FIELD 1", DateField1);
	}
	
	public void Enter_TimeField1(int row) throws IOException {
		String TimeField1 = data.Getdata("TIME FIELD 1", row).trim();		
		enterText(txt_TimeField1(), "TIME FIELD 1", TimeField1);
	}

	public void SelectDropdown1(int row) throws IOException, InterruptedException {		
		String Dropdown1Value = data.Getdata("DROP DOWN 1", row);		
		selectByText(dpd_Dropdown1(), "DROP DOWN 1" , Dropdown1Value);		
	}
	
	public void ClickRadio1(int row) throws IOException {
		
		clickOnButton(rdbtn_Radio1(row), "Radio1 Yes");
	}


	public void ClickContinue() throws IOException {
		
		clickOnButton(btn_Continue(), "Continue");
	}	
	
	public void EnterPassword() throws IOException {

		enterText(txt_ReEnterPassword(), "Re Enter Password", LaunchBrowser.TestSettingsObjects.getProperty("NurseUserPassword"));
	}

	public void ClickSendtoHomeOffice() throws IOException {
		
		clickOnButton(btn_SendtoHomeOffice(), "Send to Home Office");
	}
	
	public void ClickSubmitforReview() throws IOException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		clickOnButton(btn_SubmitforReview(), "Submit for Review");
	}
	
		
	// Each Object Performance Method
		// Verification Section

	/*public void ValidateMyWorkOrdersHeader(int row) throws IOException {
		String ExpectedMyWrkOrdrsHeader = data.Getdata("Expected Nurse Home Page", row).trim();
		verifyCorrectPageHeading(readonly_MyWorkOrdersHeader(), ExpectedMyWrkOrdrsHeader);

	}*/
	
	public void ValidateAutoTestingForm1Header(int row) throws IOException {
		String ExpectedAutoTestingForm1Header = data.Getdata("Expected Auto Testing Form1 Header", row).trim();
		verifyCorrectPageHeading(readonly_AutoTestingForm1Header(), ExpectedAutoTestingForm1Header);

	}
	
	public void ValidatePatientID(int row) throws IOException {
		String ExpectedPatientID = data.Getdata("Patient ID", row).trim();
		verifyTextEqual(readonly_PatientID(), ExpectedPatientID,
				"Validate Patient ID");

	}
	
	public void ValidateRecordSubmitted(int row) throws IOException {
		String ExpectedRecordSubmitted = data.Getdata("Expected Submit Record", row).trim();
		verifyTextEqual(readonly_RecordSubmitted(), ExpectedRecordSubmitted,
				"Record Submitted Message");

	}
	
	public void GetRecordSubmittedTxt(int row) throws IOException, BiffException {
		String RecordSubmittedTxt = readonly_RecordSubmittedMsg().getText();
		data.setData("Expected Record Submitted Text", row, RecordSubmittedTxt);

	}
	
	
	// Actual Functional Method 

	public void MyWorkOrders_AutomationTesting(int row) throws IOException, InterruptedException, BiffException {
		
		/*ValidateMyWorkOrdersHeader(row);*/
		Thread.sleep(5000);
		BasePages.scrollElementIntoView(lnk_MyWrkOrdrsLtstRcrd(row));
		Thread.sleep(2000);
		ClickMyWrkOrdrLtstRcrd(row);
		ValidateAutoTestingForm1Header(row);
		ValidatePatientID(row);
		Enter_TextField1(row);
		Enter_NumberField1(row);
		UploadFile();
		Thread.sleep(5000);
		Enter_DateField1(row);
		BasePages.scrollElementIntoView(btn_Continue());
		Enter_TimeField1(row);
		SelectDropdown1(row);
		ClickRadio1(row);
		ClickContinue();				
		EnterPassword();
		BasePages.scrollElementIntoView(btn_SubmitforReview());
		ClickSubmitforReview();
		Thread.sleep(5000);
		ValidateRecordSubmitted(row);
		GetRecordSubmittedTxt(row);
				
	}

}