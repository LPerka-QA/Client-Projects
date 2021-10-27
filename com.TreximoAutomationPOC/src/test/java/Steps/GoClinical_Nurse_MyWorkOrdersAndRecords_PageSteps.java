package Steps;

import java.io.IOException;
import java.text.ParseException;

import com.Utils.BasePages;
import com.Utils.ReadWriteDatatoExcel;
//import PageObjects.GoClinical_AdminUser_Menu;
import PageObjects.GoClinical_NurseUser_Menu;
import PageObjects.NurseUser_Login;
import PageObjects.NurseUser_MyWorkOrders_AutomationTesting;
import PageObjects.NurseUser_Records;
import jxl.read.biff.BiffException;

public class GoClinical_Nurse_MyWorkOrdersAndRecords_PageSteps {
	GoClinical_NurseUser_Menu goClinical_NurseUser_Menu;
	NurseUser_Login nurseUser_Login;
	ReadWriteDatatoExcel readwritetoExcel ;
	NurseUser_MyWorkOrders_AutomationTesting nurseUser_MyWorkOrders_AutomationTesting;
	NurseUser_Records nurseUser_Records;
	
	public void LaunchUrl(int row) throws BiffException, IOException {
		nurseUser_Login.LaunchURL(row);
	}

	public void Login(int row) throws BiffException, IOException {
		nurseUser_Login.EnterUserEmail(row);
		nurseUser_Login.EnterUserPassword(row);
		nurseUser_Login.ClickLogInButton(row);
		nurseUser_Login.ValidateSignInMsg(row, readwritetoExcel.Getdata("Expected Signed In Message", row).trim());
		nurseUser_Login.ValidateMyWorkOrdersPageMsg(row, readwritetoExcel.Getdata("Expected Nurse Home Page", row).trim());
	}
	
	public void CreateWorkOrderAutomationTestingRecord(int row) throws IOException, InterruptedException, BiffException, ParseException {
		nurseUser_MyWorkOrders_AutomationTesting.ClickMyWrkOrdrLtstRcrd(row,readwritetoExcel.Getdata("Work Order ID", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ValidateAutoTestingForm1Header(row, readwritetoExcel.Getdata("Expected Auto Testing Form1 Header", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ValidatePatientID(row, readwritetoExcel.Getdata("Patient ID", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.Enter_TextField1(row, readwritetoExcel.Getdata("TEXT FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.Enter_NumberField1(row,readwritetoExcel.Getdata("NUMBER FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.UploadFile(row);
		nurseUser_MyWorkOrders_AutomationTesting.Enter_DateField1(row, readwritetoExcel.Getdata("DATE FIELD 1", row).trim());
		readwritetoExcel.setData("Expected Date Field 1", row, BasePages.TreximoDateField1(readwritetoExcel.Getdata("DATE FIELD 1", row).trim()));
		readwritetoExcel.setData("Expected Time Field 1", row, BasePages.TreximoTimeField1(readwritetoExcel.Getdata("TIME FIELD 1", row).trim()));
		nurseUser_MyWorkOrders_AutomationTesting.Enter_TimeField1(row, readwritetoExcel.Getdata("TIME FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.SelectDropdown1(row,readwritetoExcel.Getdata("DROP DOWN 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ClickRadio1(row, readwritetoExcel.Getdata("RADIO 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ClickContinue(row);
		nurseUser_MyWorkOrders_AutomationTesting.EnterPassword(row);
		nurseUser_MyWorkOrders_AutomationTesting.ClickSubmitforReview(row);
		Thread.sleep(3000);
		nurseUser_MyWorkOrders_AutomationTesting.ValidateRecordSubmitted(row,readwritetoExcel.Getdata("Expected Submit Record", row).trim());
		readwritetoExcel.setData("Record Submitted Text", row, nurseUser_MyWorkOrders_AutomationTesting.GetRecordSubmittedTxt(row));
		
	}
	
	public void ReviewRecords(int row) throws IOException, InterruptedException, BiffException {
		
		nurseUser_Records.ClickRecords(row);
		nurseUser_Records.ValidateRecordsHeader(row, readwritetoExcel.Getdata("Expected Nurse Records Header", row).trim());
		readwritetoExcel.setData("Record ID", row, nurseUser_Records.GetLtstRcrdID(row, readwritetoExcel.Getdata("Work Order ID", row).trim(), 
				readwritetoExcel.Getdata("Protocol", row).trim(),readwritetoExcel.Getdata("Patient ID", row).trim(), 
				readwritetoExcel.Getdata("Visit", row).trim()));
		readwritetoExcel.setData("Latest Record Created Date", row, nurseUser_Records.GetLtstRcrdCrtdDate(row, readwritetoExcel.Getdata("Work Order ID", row).trim(), 
				readwritetoExcel.Getdata("Protocol", row).trim(),readwritetoExcel.Getdata("Patient ID", row).trim(), 
				readwritetoExcel.Getdata("Visit", row).trim()));
		nurseUser_Records.ValidateLatestRecord(row, readwritetoExcel.Getdata("Expected Latest Record", row).trim(), 
				readwritetoExcel.Getdata("Work Order ID", row).trim(), readwritetoExcel.Getdata("Protocol", row).trim(), 
				readwritetoExcel.Getdata("Patient ID", row).trim(), readwritetoExcel.Getdata("Visit", row).trim());
	}
	
	public void Logout(int row) throws BiffException, IOException {
		goClinical_NurseUser_Menu.ClickWelcomUser(row);
		goClinical_NurseUser_Menu.ClickLogout(row);
	}
	
	
	public void createpageconnections(String excelSheetPath, String Sheetname, String WritePath) {
		
		readwritetoExcel=new ReadWriteDatatoExcel( excelSheetPath,  Sheetname,  WritePath);
		goClinical_NurseUser_Menu = new GoClinical_NurseUser_Menu();
		nurseUser_Login =new NurseUser_Login();
		nurseUser_MyWorkOrders_AutomationTesting = new NurseUser_MyWorkOrders_AutomationTesting();
		nurseUser_Records = new NurseUser_Records();
		
	}
	
	public int ExcelCount() {
		return readwritetoExcel.GetRows();
	}
	

}
