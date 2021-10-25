package Steps;

import java.io.IOException;
import com.Utils.ReadWriteDatatoExcel;
import PageObjects.GoClinical_AdminUser_Menu;
import PageObjects.NurseUser_Login;
import PageObjects.NurseUser_MyWorkOrders_AutomationTesting;
import jxl.read.biff.BiffException;

public class GoClinicalNewWorkOrdersTestSuiteSteps {
	GoClinical_AdminUser_Menu goClinical_AdminUser_Menu;
	NurseUser_Login nurseUser_Login;
	ReadWriteDatatoExcel readwritetoExcel ;
	ReadWriteDatatoExcel writetestreport;
	NurseUser_MyWorkOrders_AutomationTesting nurseUser_MyWorkOrders_AutomationTesting;
	

	public void Login(int row) throws BiffException, IOException {
		nurseUser_Login.LaunchURL(row);
		nurseUser_Login.EnterUserEmail(row);
		nurseUser_Login.EnterUserPassword(row);
		nurseUser_Login.ClickLogInButton(row);
		nurseUser_Login.ValidateSignInMsg(row, readwritetoExcel.Getdata("Expected Signed In Message", row).trim());
		nurseUser_Login.ValidateMyWorkOrdersPageMsg(row, readwritetoExcel.Getdata("Expected Nurse Home Page", row).trim());
	}
	
	public void CreateWorkOrderAutomationTestingRecord(int row) throws IOException, InterruptedException, BiffException {
		nurseUser_MyWorkOrders_AutomationTesting.ClickMyWrkOrdrLtstRcrd(row,readwritetoExcel.Getdata("Work Order Number", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ValidateAutoTestingForm1Header(row, readwritetoExcel.Getdata("Expected Auto Testing Form1 Header", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ValidatePatientID(row, readwritetoExcel.Getdata("Patient ID", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.Enter_TextField1(row, readwritetoExcel.Getdata("TEXT FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.Enter_NumberField1(row,readwritetoExcel.Getdata("NUMBER FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.UploadFile(row);
		nurseUser_MyWorkOrders_AutomationTesting.Enter_DateField1(row, readwritetoExcel.Getdata("DATE FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.Enter_TimeField1(row, readwritetoExcel.Getdata("TIME FIELD 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.SelectDropdown1(row,readwritetoExcel.Getdata("DROP DOWN 1", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ClickRadio1(row, readwritetoExcel.Getdata("Radio1 Yes", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.ClickContinue(row);
		nurseUser_MyWorkOrders_AutomationTesting.EnterPassword(row);
		nurseUser_MyWorkOrders_AutomationTesting.ClickSubmitforReview(row);
		nurseUser_MyWorkOrders_AutomationTesting.ValidateRecordSubmitted(row,readwritetoExcel.Getdata("Expected Submit Record", row).trim());
		nurseUser_MyWorkOrders_AutomationTesting.GetRecordSubmittedTxt(row);
		
	}
	
	
	public void createpageconnections(String excelSheetPath, String Sheetname, String WritePath) {
		
		readwritetoExcel=new ReadWriteDatatoExcel( excelSheetPath,  Sheetname,  WritePath);
		writetestreport = new ReadWriteDatatoExcel(System.getProperty("user.dir")
				+ "\\Resources\\TestData\\TestResult.xls", "Test Result");
		goClinical_AdminUser_Menu = new GoClinical_AdminUser_Menu();
		nurseUser_Login =new NurseUser_Login();
		nurseUser_MyWorkOrders_AutomationTesting = new NurseUser_MyWorkOrders_AutomationTesting();
		
	}
	
	public void WriteTestReportinExcel( String Action, String Expectedresults,String ActualResults, int row) throws BiffException, IOException {
		
	//	writetestreport.setReportData("StepNo", row, Stepno);
		writetestreport.setReportData("Action", row, Action);
		writetestreport.setReportData("ExpectedResult", row, Expectedresults);
		writetestreport.setReportData("ActualResult", row, ActualResults);
		writetestreport.setReportData("ScreenshotPath", row, System.getProperty("user.dir") 
			+ "\\Resources\\Screenshots");
	}
	

}
