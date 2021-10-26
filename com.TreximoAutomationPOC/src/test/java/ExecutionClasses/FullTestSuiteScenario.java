package ExecutionClasses;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;
import com.aventstack.extentreports.Status;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;



public class FullTestSuiteScenario {
	
	Object methodname;
	Steps.GoClinical_Admin_NewWorkOrder_PageSteps GoClinicalAdminPageSteps;
	Steps.GoClinical_Nurse_MyWorkOrdersAndRecords_PageSteps GoClinicalNursePageSteps;
	Steps.GoClinical_Admin_Records_PageSteps GoClinicalAdminRecordsPageSteps;
	static com.Utils.BasePages ReportingPages;
	public static String TestMethodName;

	@BeforeTest
	public void launchBrowser() throws IOException {
		GoClinicTest GoClinicTest = new GoClinicTest("GoClinicalWorkOrder");
		LaunchBrowser.LaunchBrowserapp();
		GoClinicalAdminPageSteps = new Steps.GoClinical_Admin_NewWorkOrder_PageSteps();
		GoClinicalNursePageSteps = new Steps.GoClinical_Nurse_MyWorkOrdersAndRecords_PageSteps();
		GoClinicalAdminRecordsPageSteps = new Steps.GoClinical_Admin_Records_PageSteps();
		GoClinicalAdminPageSteps.createpageconnections(GoClinicTest.excelSheetPath, GoClinicTest.Sheetname, GoClinicTest.WritePath);
		ReportingPages = new BasePages(GoClinicTest.HTMLReportPath, GoClinicTest.CurrentPageTestCaseName);
	}

	@Test(priority=0)
	public void AdminUserNewWorkOrder() throws BiffException, IOException, InterruptedException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <=GoClinicalAdminPageSteps.ExcelCount()-1; row++) {
			GoClinicalAdminPageSteps.LaunchUrl(row);
			GoClinicalAdminPageSteps.Login(row);
			GoClinicalAdminPageSteps.CreateNewWorkOrderRecord(row);
			GoClinicalAdminPageSteps.Logout(row);
		}
	}
	
	@Test(priority=1)
	public void NurseUserMYWorkOrdersAndRecords() throws BiffException, IOException, InterruptedException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <=GoClinicalAdminPageSteps.ExcelCount()-1; row++) {
			GoClinicalNursePageSteps.Login(row);
			GoClinicalNursePageSteps.CreateWorkOrderAutomationTestingRecord(row);
			GoClinicalNursePageSteps.ReviewRecords(row);
			GoClinicalNursePageSteps.Logout(row);
			}			
		}
	
	@Test(priority=2)
	public void AdminUserSubmitRecords() throws BiffException, IOException, InterruptedException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <=GoClinicalAdminPageSteps.ExcelCount()-1; row++) {
			GoClinicalAdminRecordsPageSteps.Login(row);
			GoClinicalAdminRecordsPageSteps.ReviewAndSubmitRecords(row);
			GoClinicalAdminRecordsPageSteps.Logout(row);
			}
		}
	
	@AfterTest
	public static void CloseBroswer() throws IOException {
		ReportingPages.CloseBrowser();

	}
}
