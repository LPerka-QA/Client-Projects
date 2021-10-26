package Steps;

import java.io.IOException;
import com.Utils.ReadWriteDatatoExcel;

import PageObjects.AdminUser_Login;
import PageObjects.AdminUser_Records;
import PageObjects.GoClinical_AdminUser_Menu;
import jxl.read.biff.BiffException;

public class GoClinical_Admin_Records_PageSteps {
	GoClinical_AdminUser_Menu goClinical_AdminUser_Menu;
	AdminUser_Login adminUser_Login;
	ReadWriteDatatoExcel readwritetoExcel ;
	AdminUser_Records adminUser_Records;
	
	public void LaunchUrl(int row) throws BiffException, IOException {
		adminUser_Login.LaunchURL(row);
	}
	
	public void Login(int row) throws BiffException, IOException {
		adminUser_Login.EnterUserEmail(row);
		adminUser_Login.EnterUserPassword(row);
		adminUser_Login.ClickLogInButton(row);
		adminUser_Login.ValidateSignInMsg(row, readwritetoExcel.Getdata("Expected Signed In Message", row).trim());
		adminUser_Login.ValidateAdminHomePageMsg(row, readwritetoExcel.Getdata("Expected Admin Home Page", row).trim());
	}
	
	public void ReviewAndSubmitRecords(int row) throws IOException, InterruptedException, BiffException {
		adminUser_Records.ClickRecords(row);
		adminUser_Records.ValidateRecordsHeader(row, readwritetoExcel.Getdata("Expected Admin User Records Header", row).trim());
		Thread.sleep(3000);
		adminUser_Records.ValidateLatestRecord(row, readwritetoExcel.Getdata("Work Order ID", row).trim(), 
				readwritetoExcel.Getdata("Protocol", row).trim(), readwritetoExcel.Getdata("Patient ID", row).trim(), 
				readwritetoExcel.Getdata("Visit", row).trim(), readwritetoExcel.Getdata("Latest Record Created Date", row).trim(),
				readwritetoExcel.Getdata("Expected Admin User Latest Record", row).trim());
		Thread.sleep(3000);
		adminUser_Records.ClickViewLatestRecord(row, readwritetoExcel.Getdata("Work Order ID", row).trim(), 
				readwritetoExcel.Getdata("Protocol", row).trim(), readwritetoExcel.Getdata("Patient ID", row).trim(), 
				readwritetoExcel.Getdata("Visit", row).trim(), readwritetoExcel.Getdata("Latest Record Created Date", row).trim());
		Thread.sleep(3000);
		adminUser_Records.ValidateRecordID(row, readwritetoExcel.Getdata("Record ID", row).trim());
		adminUser_Records.ValidateWorkOrdersID(row, readwritetoExcel.Getdata("Work Order ID", row).trim());
		adminUser_Records.ValidatePatientID(row, readwritetoExcel.Getdata("Patient ID", row).trim());
		adminUser_Records.ValidateStudyVisit(row, readwritetoExcel.Getdata("Visit", row).trim());
		adminUser_Records.ValidateTextField1(row, readwritetoExcel.Getdata("TEXT FIELD 1", row).trim());
		adminUser_Records.ValidateNumberField1(row, readwritetoExcel.Getdata("NUMBER FIELD 1", row).trim());
		adminUser_Records.ValidateRadio1(row, readwritetoExcel.Getdata("RADIO 1", row).trim());
		adminUser_Records.EnterPassword(row);
		adminUser_Records.ClickSendToStudySite(row);
		Thread.sleep(3000);
		adminUser_Records.ValidateRecordSubmittedMsg(row, readwritetoExcel.Getdata("Expected Admin Record Submitted", row).trim());
		adminUser_Records.ValidateAuditTrailStatus(row, readwritetoExcel.Getdata("Expected Audit Trail Status", row).trim());	
	}
	
	public void Logout(int row) throws BiffException, IOException {
		goClinical_AdminUser_Menu.ClickWelcomUser(row);
		goClinical_AdminUser_Menu.ClickLogout(row);
	}
	
	
	public void createpageconnections(String excelSheetPath, String Sheetname, String WritePath) {
		
		readwritetoExcel=new ReadWriteDatatoExcel( excelSheetPath,  Sheetname,  WritePath);
		goClinical_AdminUser_Menu = new GoClinical_AdminUser_Menu();
		adminUser_Login =new AdminUser_Login();
		adminUser_Records = new AdminUser_Records();
		
	}
	
	public int ExcelCount() {
		return readwritetoExcel.GetRows();
	}

}
