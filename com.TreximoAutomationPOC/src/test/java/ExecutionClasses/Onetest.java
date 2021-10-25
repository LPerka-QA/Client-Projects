package ExecutionClasses;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;



public class Onetest  {
	


	Object methodname;
	Steps.GoClinical_Admin_NewWorkOrder_PageSteps GoClinicalAdminPageSteps;
	static com.Utils.BasePages ReportingPages;
	public static String TestMethodName;
		
	
	
	@BeforeTest
	public void launchBrowser() throws IOException {
		GoClinicTest GoClinicTest = new GoClinicTest("AdminNew");
		LaunchBrowser.LaunchBrowserapp();
		GoClinicalAdminPageSteps = new Steps.GoClinical_Admin_NewWorkOrder_PageSteps();
		GoClinicalAdminPageSteps.createpageconnections(GoClinicTest.excelSheetPath, GoClinicTest.Sheetname, GoClinicTest.WritePath);
		ReportingPages = new BasePages(GoClinicTest.HTMLReportPath, GoClinicTest.CurrentPageTestCaseName);
	
	}

	@Test
	public void AdminUserNewWorkOrder() throws BiffException, IOException, InterruptedException {
		methodname= new Object(){}.getClass().getEnclosingMethod().getName();
		TestMethodName = methodname.toString();
		for (int row = 1; row <=GoClinicalAdminPageSteps.ExcelCount()-1; row++) {	
			GoClinicalAdminPageSteps.Login(row);
			GoClinicalAdminPageSteps.CreateNewWorkOrderRecord(row);
			GoClinicalAdminPageSteps.Logout(row);
			
		}
	}
	
	@AfterTest
	public static void CloseBroswer() throws IOException {
		ReportingPages.CloseBrowser();

	}
}
