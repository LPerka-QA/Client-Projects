package com.AdminUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.Utils.BasePages;
import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.AdminUser.CommonPages.GoClinical_AdminUser_Menu;
import jxl.read.biff.BiffException;

public class AdminUser_NewWorkOrder extends BasePages {
	

	AdminUser_LoginPageData data = new AdminUser_LoginPageData();
	GoClinical_AdminUser_Menu MenuPage = new GoClinical_AdminUser_Menu();
	String PatientIDValue;
	String WorkOrderRecordNumber;
	
	
	
	public void AdminUser_Home() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

// Objects from Application
// New Work Order
	
	public static WebElement dpd_Sponsor() {
		return driver.findElement(By.id("sponsor_id"));

	}
	

	public static WebElement dpd_Protocol() {
		return driver.findElement(By.xpath("//select[@id='work_order_protocol_id']"));

	}

	public static WebElement txt_PatientID() {
		return driver.findElement(By.xpath("//input[@id='work_order_patient_study_id' and @type='text']"));

	}

	public static WebElement dpd_Visit() {
		return driver.findElement(By.xpath("//select[@id='work_order_visit_id']"));

	}

	public static WebElement txt_SrvcePrvdrEmail_RVSP() {
		return driver.findElement(By.xpath("//input[@id='work_order[rvsp_email]' and @type='text']"));

	}

	public static WebElement dpd_SndRcrdTo_SiteCntct() {
		return driver.findElement(By.xpath("//select[@id='work_order_site_user_id']"));

	}

	public WebElement btn_CreateWorkOrder() {
		return driver.findElement(By.xpath("//div//input[@value='Create Work order' and @type='submit']"));

	}

	public static WebElement btn_Back() {
		return driver.findElement(By.xpath("//a[@href='/work_orders' and text()='Back']"));

	}

	public static WebElement readonly_WrkOrdrCrteSuccessMsg() {
		return driver.findElement(By.xpath("//div[@class='notice']//p"));
		
	}
	
	public static WebElement readonly_WorkOrderNumber() {
		return driver.findElement(By.xpath("//div[@class='col-xs-12' and @id='work_order_show']//h1"));
		
	}
	
	public static WebElement readonly_ActiveStatus() {
		return driver.findElement(By.xpath("//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Active Status']//..//dd"));
		
	}
	
	public static WebElement readonly_Status() {
		return driver.findElement(By.xpath("//dt//..//span[@class='label label-primary']"));
		
	}
	
	public static WebElement readonly_PatientID() {
		return driver.findElement(By.xpath("//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Patient']//..//dd[3]"));
		
	}
	
	public static WebElement readonly_Protocol() {
		return driver.findElement(By.xpath("//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Protocol']//..//dd[4]"));
		
	}
	
	public static WebElement readonly_Visit() {
		return driver.findElement(By.xpath("//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Visit']//..//dd[5]"));
		
	}
	
	public static WebElement readonly_ServiceProvider() {
		return driver.findElement(By.xpath("//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Service Provider']//..//em"));
		
	}
	
	public static WebElement PaperOriginalLnk() {
		return driver.findElement(By.xpath("//a[contains(@href,'/records/new') and text()='(Paper original?)']"));
		
	}
	
	public static WebElement SendWorktoUserBtn() {
		return driver.findElement(By.xpath("//input[@value='Send Work Order to Lokendernath-rvsp Perka' and @type='submit']"));
		
	}
	
	public static WebElement EditLnk() {
		return driver.findElement(By.xpath("//a[contains(@href,'/work_orders/') and text()='Edit']"));
		
	}
	
	public static WebElement BacktoWorkOrdersLnk() {
		return driver.findElement(By.xpath("//a[@href='/work_orders' and text()='Back to Work Orders']"));
		
	}
	
	public static WebElement readonly_NotifyMsg() {
		return driver.findElement(By.xpath("//div[@id='notify']//p[@class='bg-success']"));
		
	}
	
	
	// Each Object Performance Method
	// New Work Order Info
	
	
	public void SelectSponsor(int row) throws IOException, InterruptedException {		
		String SponsorValue = data.Getdata("Sponsor", row);		
		selectByText(dpd_Sponsor(), "Sponsor" , SponsorValue);		
	}	

	public void SelectProtocol(int row) throws IOException, InterruptedException  {
		String ProtocolValue = data.Getdata("Protocol", row).trim();
		selectByText(dpd_Protocol(), "Protocol", ProtocolValue);
	}	

	public void EnterPatientID(int row) throws IOException {
		PatientIDValue = data.Getdata("Patient ID", row).trim();
		enterText(txt_PatientID(), "Patient ID", PatientIDValue);
	}

	public void SelectVisit(int row) throws IOException {
		String VisitValue = data.Getdata("Visit", row).trim();
		selectByText(dpd_Visit(), "Visit", VisitValue);
	}	
	
	public void EnterSrvcePrvdrEmail_RVSP(int row) throws IOException {
		String ServiceProviderEmail_RVSPValue = data.Getdata("Service Provider Email RVSP", row).trim();
		enterText(txt_SrvcePrvdrEmail_RVSP(), "Service Provider Email (RVSP)", ServiceProviderEmail_RVSPValue);
	}

	public void SelectSndRcrdTo_SiteCntct(int row) throws IOException {
		String SendRecordtoSiteContactValue = data.Getdata("Send Record to Site Contact", row).trim();
		selectByText(dpd_SndRcrdTo_SiteCntct(), "Send Record To (Site Contact)", SendRecordtoSiteContactValue);
	}
	
	// Each Object Performance Method
	// Submit Section
		
	public void ClickCreateWorkOrder() throws IOException {		
		clickOnButton(btn_CreateWorkOrder(), "Create Work order");
		
	}
	
	public void ClickSendWorkOrdertoUser() throws IOException {		
		clickOnButton(SendWorktoUserBtn(), "Send Work Order to User");
		
	}
	
	// Each Object Performance Method
		// Verification Section

	public void ValidateCrtWrkOrdrSuccessMsg(int row) throws IOException {
		String ExpectedCrtWrkOrdrSuccessMsg = data.Getdata("Expected Work Order Created Message", row).trim();
		verifyTextEqual(readonly_WrkOrdrCrteSuccessMsg(), ExpectedCrtWrkOrdrSuccessMsg,
				"New Work Order with PatientID " + PatientIDValue);
	}
	
	public void GetWrkOrdrNmbr(int row) throws IOException, BiffException {
		
		WorkOrderRecordNumber = readonly_WorkOrderNumber().getText();		
		data.setData("Work Order Number", row, WorkOrderRecordNumber);
		
	}
	
	public void GetActiveStatus(int row) throws IOException, BiffException {
		
		String ActiveStatus = readonly_ActiveStatus().getText();		
		data.setData("Active Status", row, ActiveStatus);
		
	}
	
	public void GetStatus(int row) throws IOException, BiffException {
		
		String Status = readonly_Status().getText();		
		data.setData("Status", row, Status);
		
	}
	
	public void ValidatePatientID(int row) throws IOException {
		String PatientId = data.Getdata("Patient ID", row).trim();
		verifyTextEqual(readonly_PatientID(), PatientId,
				"PatientID");
	}
	
	public void ValidateProtocol(int row) throws IOException {
		String ExpectedProtocol = data.Getdata("Protocol", row).trim();
		verifyTextEqual(readonly_Protocol(), ExpectedProtocol,
				"Protocol");
	}
	
	public void ValidateVisit(int row) throws IOException {
		String ExpectedVisit = data.Getdata("Visit", row).trim();
		verifyTextEqual(readonly_Visit(), ExpectedVisit,
				"Visit");
	}
	
	public void ValidateSrvcePrvdr(int row) throws IOException {
		String ExpectedSrvcePrvdrEmail = data.Getdata("Service Provider Email RVSP", row).trim();
		verifyTextEqual(readonly_ServiceProvider(), ExpectedSrvcePrvdrEmail,
				"Service Provider Email RVSP");
	}
	
	public void ValidateNotifyMsg(int row) throws IOException, BiffException {
		
		if(readonly_NotifyMsg().getText().contains(data.Getdata("Service Provider Email RVSP", row))== true)
		{
		String NotifyMsg = readonly_NotifyMsg().getText();		
		data.setData("Notification Message", row, NotifyMsg);
		}
		
	}
	
	
	// Actual Functional Method
	public void WorkOrders_New(int row) throws Exception {
		
		MenuPage.ClickWorkOrders();
		
		MenuPage.ClickWorkOrdersNew();
		
				SelectSponsor(row);
				
				SelectProtocol(row);
				
				EnterPatientID(row);
				
				SelectVisit(row); 
				
				EnterSrvcePrvdrEmail_RVSP(row);
				
				SelectSndRcrdTo_SiteCntct(row);
				
				BasePages.scrollElementIntoView(btn_CreateWorkOrder());
				
				ClickCreateWorkOrder();
				
				ValidateCrtWrkOrdrSuccessMsg(row);
				
				GetWrkOrdrNmbr(row);
				
				GetActiveStatus(row);
				
				GetStatus(row);
				
				ValidatePatientID(row);
				
				ValidateProtocol(row);
				
				ValidateVisit(row);
				
				ValidateSrvcePrvdr(row);
				
				ClickSendWorkOrdertoUser();
				
				Thread.sleep(3000);
				
				ValidateNotifyMsg(row);
				
	}

	

}