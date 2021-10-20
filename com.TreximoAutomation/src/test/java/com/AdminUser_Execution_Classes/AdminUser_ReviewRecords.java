package com.AdminUser_Execution_Classes;

import java.io.IOException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AdminUser.CommonPages.GoClinical_AdminUser_NewWorkOrder;
import com.AdminUser.CommonPages.GoClinical_AdminUser_ReviewRecords;
import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;
import com.aventstack.extentreports.Status;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class AdminUser_ReviewRecords {
	
	private static String HTMLReportPAth = null;
	private static String CurrentPageTestCaseName = null;
	private static String ExcelSheetPath = null;
	private static String Excel_SheetName = null;
	private static String WritePath = null;
	private static BasePages ReportingPages = null;
	private static AdminUser_LoginPageData AdminUserReviewRecords = null;
		
	/*//This method used to add a time stamp to name of HTML report
		public static String DateTime() {
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("MM_dd_yyyy hh_mm_ss");
			String timeStamp = df.format(date);
			return timeStamp;
		}*/
		
	@Parameters("browserName")
		@BeforeTest
		public void TestSetup(@Optional String browserName) throws IOException, BiffException {					
			
			/*String Name = "Admin User Review Records Vital Signs";
			String timeStamp = DateTime();
			String ReportName = Name+"_"+ timeStamp+".html";*/
						
									HTMLReportPAth = System.getProperty("user.dir") + "\\Resources\\Reports\\Daily Execution\\Admin User Review Records Vital Signs.html";
									CurrentPageTestCaseName = "Admin User Review Records";
									ExcelSheetPath = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
									Excel_SheetName = "My Work Orders";
									ReportingPages = new BasePages(HTMLReportPAth, CurrentPageTestCaseName);
									WritePath = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
									AdminUserReviewRecords = new AdminUser_LoginPageData(ExcelSheetPath, Excel_SheetName, WritePath);

									BasePages.ResultsLog.ReportScriptStarted(CurrentPageTestCaseName);
									AdminUserReviewRecords.ClearExistingStatus();
									LaunchBrowser.LaunchBrowserapp(browserName);
		}

		@Test
		public void LaunchGoClinicalAPP() throws IOException, InterruptedException, BiffException, WriteException {
			for (int row = 1; row < AdminUserReviewRecords.GetRows(); row++) {
				if (AdminUserReviewRecords.Getdata("Blood Pressure Systolic mm Hg", row).trim().length() > 2) {
					try {

											AdminUserReviewRecords.setData("Admin Records Execution Status", row, "Started");
											GoClinical_AdminUser_NewWorkOrder.AdminUser_LaunchURL();
											GoClinical_AdminUser_ReviewRecords.AdminUser_Login(row);
											GoClinical_AdminUser_ReviewRecords.Review_Records(row);
											GoClinical_AdminUser_NewWorkOrder.GoCliniCal_AdminUser_Menu_ClickLogout();
			
											AdminUserReviewRecords.setData("Admin Records Execution Status", row, "Executed");

						}
					catch (Exception e) {
											System.out.println("In Catch Main");
											BasePages.ResultsLog.logger.log(Status.FAIL, e.toString() + " Row " + row);
											
					try {

							
						} 
					catch (Exception e1) {
											BasePages.ResultsLog.logger.log(Status.FAIL, "Error message not displayed");
											BasePages.ExecutionPageExceptionErrorCapture(
											CurrentPageTestCaseName + " Data Row Number, " + row, e1.toString());
											

						}

											AdminUserReviewRecords.setData("Admin Records Execution Status", row, "Row Execution Not Completed");

					}
				}
			}
			
		}

		@AfterTest
		public void CloseTest()  throws IOException {
											//close Browser
											ReportingPages.CloseBrowser();
		}

}