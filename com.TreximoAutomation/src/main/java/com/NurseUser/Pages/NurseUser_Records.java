package com.NurseUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.NurseUser.CommonPages.GoClinical_NurseUser_Menu;
import com.NurseUser.PagesData.NurseUser_LoginPageData;
import com.Utils.BasePages;

import jxl.read.biff.BiffException;

public class NurseUser_Records  extends BasePages {
	
	NurseUser_LoginPageData data = new NurseUser_LoginPageData();
	GoClinical_NurseUser_Menu MenuPage = new GoClinical_NurseUser_Menu();
	String Protocol;
	String PatientId;
	String Visit;
	String WorkOrder;
	String WorkOrderNumber;
	
	public void NurseUser_RecordsPage(int row) {

		/*String RecordId = data.Getdata("Expected My Work Orders Header", row).trim();*/
		
		WorkOrder = data.Getdata("Work Order Number", row);		
		WorkOrderNumber = WorkOrder.substring(11);
		Protocol = data.Getdata("Protocol", row).trim();
		PatientId = data.Getdata("Patient ID", row).trim();
		Visit = data.Getdata("Visit", row).trim();
		
	}
	
	// Objects from Application
	// Records
	
		public static WebElement lnk_Records() {
			return driver.findElement(By.xpath("//li//a[@href='/records' and text()='Records']"));

		}
		
		public static WebElement readonly_RecordsHeader() {
			return driver.findElement(By.xpath("//div[@id='records_index']//h1[contains(text(),'Records')]"));

		}
		
		public WebElement readonly_GetLatestRecord_CreatedDate() {
			return driver.findElement(By.xpath("//table[@class='table table-condensed table-hover']//td[text()='"+WorkOrderNumber+"']//..//td[text()='"+Protocol+"']//..//td[text()='"+PatientId+"']//..//td[text()='"+Visit+"']//..//td[text()='REVIEW']//..//td[6]"));

		}
		
		

		

		// Each Object Performance Method
		// Records
		
		public void ClickRecordsHeader() throws IOException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			clickOnLink(lnk_Records(), "Records ");
		}
		
		
		
			
		// Each Object Performance Method
			// Verification Section

		public void ValidateRecordsHeader(int row) throws IOException {
			String ExpectedRecordsHeader = data.Getdata("Expected Nurse Records Header", row).trim();
			verifyTextEqual(readonly_RecordsHeader(), ExpectedRecordsHeader,
					"Validate Records Header");

		}
		
		public void ValidateLatestRecord(int row) throws IOException {
			String ExpectedLatestRecord = data.Getdata("Expected Latest Record", row).trim();
			verifyTextEqual(readonly_GetLatestRecord_CreatedDate(), ExpectedLatestRecord,
					"Validate latest Record");

		}
		
		public void GetLtstRcrdCrtdDate(int row) throws IOException, BiffException {
			String LatestRecordCreateDate = readonly_GetLatestRecord_CreatedDate().getText();
			data.setData("Latest Record Created Date", row, LatestRecordCreateDate);

		}
		
		
		
		
		// Actual Functional Method 

		public void Records(int row) throws IOException, InterruptedException, BiffException {
			NurseUser_RecordsPage(row);
			ClickRecordsHeader();
			ValidateRecordsHeader(row);
			GetLtstRcrdCrtdDate(row);
			//ValidateLatestRecord(row);			
		}

}