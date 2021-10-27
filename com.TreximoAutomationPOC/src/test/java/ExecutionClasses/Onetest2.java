package ExecutionClasses;

import java.io.IOException;
import java.text.ParseException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;



public class Onetest2 {
	
	Object methodname;
	Steps.GoClinical_Nurse_MyWorkOrdersAndRecords_PageSteps GoClinicalNursePageSteps;
	static com.Utils.BasePages ReportingPages;
	public static String TestMethodName;
	
	/*static String HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\GoClinical_NurseUser_MyWorkOrders_AutomationTesting.html";
	static String CurrentPageTestCaseName = "GoClinical Nurse User My Work Orders Automation Testing";
	static com.Utils.BasePages ReportingPages1 = new BasePages(HTMLReportPath, CurrentPageTestCaseName);
	public static String excelSheetPath = System.getProperty("user.dir")
			+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
	public static String Sheetname = "My Work Orders";
	public static String WritePath = System.getProperty("user.dir")
			+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";*/

	@BeforeTest
	public void launchBrowser() throws IOException {		
		GoClinicTest GoClinicTest = new GoClinicTest("NurseMyWorkOrders");
		
		LaunchBrowser.LaunchBrowserapp();
		
		
		GoClinicalNursePageSteps = new Steps.GoClinical_Nurse_MyWorkOrdersAndRecords_PageSteps();
		GoClinicalNursePageSteps.createpageconnections(GoClinicTest.excelSheetPath, GoClinicTest.Sheetname, GoClinicTest.WritePath);
		ReportingPages = new BasePages(GoClinicTest.HTMLReportPath, GoClinicTest.CurrentPageTestCaseName);
	}

	@Test
	public void NurseUserMYWorkOrdersAndRecords() throws BiffException, IOException, InterruptedException, ParseException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <= GoClinicalNursePageSteps.ExcelCount()-1; row++) {
			GoClinicalNursePageSteps.LaunchUrl(row);
			GoClinicalNursePageSteps.Login(row);
			GoClinicalNursePageSteps.CreateWorkOrderAutomationTestingRecord(row);
			GoClinicalNursePageSteps.ReviewRecords(row);
			GoClinicalNursePageSteps.Logout(row);
		}
	}
	
	@AfterTest
	public static void CloseBroswer() throws IOException {
		ReportingPages.CloseBrowser();

	}
}
