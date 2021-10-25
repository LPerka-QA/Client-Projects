package ExecutionClasses;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;



public class Onetest3 {
	
	
	Object methodname;
	Steps.GoClinical_Admin_Records_PageSteps GoClinicalAdminRecordsPageSteps;
	static com.Utils.BasePages ReportingPages;
	public static String TestMethodName;
	
	/*static String HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\GoClinical_AdminUser_SubmitRecords.html";
	static String CurrentPageTestCaseName = "GoClinical Admin User Submit Records";
	static com.Utils.BasePages ReportingPages2 = new BasePages(HTMLReportPath, CurrentPageTestCaseName);
	public static String excelSheetPath = System.getProperty("user.dir")
			+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
	public static String Sheetname = "My Work Orders";
	public static String WritePath = System.getProperty("user.dir")
			+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";*/

	@BeforeTest
	public void launchBrowser() throws IOException {
		GoClinicTest GoClinicTest = new GoClinicTest("AdminRecords");
		LaunchBrowser.LaunchBrowserapp();
		GoClinicalAdminRecordsPageSteps = new Steps.GoClinical_Admin_Records_PageSteps();
		GoClinicalAdminRecordsPageSteps.createpageconnections(GoClinicTest.excelSheetPath, GoClinicTest.Sheetname, GoClinicTest.WritePath);
		ReportingPages = new BasePages(GoClinicTest.HTMLReportPath, GoClinicTest.CurrentPageTestCaseName);
	}
	
	@Test
	public void AdminUserSubmitRecords() throws BiffException, IOException, InterruptedException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <= GoClinicalAdminRecordsPageSteps.ExcelCount()-1; row++) {
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
