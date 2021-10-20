package com.GoClinical_WorkOrder_ExecutionSuiteClasses;

	import java.io.IOException;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.Status;
	import com.AdminUser.CommonPages.GoClinical_AdminUser_NewWorkOrder;
	import com.AdminUser.CommonPages.GoClinical_AdminUser_ReviewRecords;
	import com.AdminUser.PagesData.AdminUser_LoginPageData;
	import com.NurseUser.CommonPages.GoClinical_NurseUser_MyWorkOrders;
	import com.NurseUser.PagesData.NurseUser_LoginPageData;
	//import com.NurseUser.PagesData.NurseUser_LoginPageData;
	import com.Utils.BasePages;
	import com.Utils.LaunchBrowser;
	import jxl.read.biff.BiffException;
	import jxl.write.WriteException;


	public class GoClinical_WorkOrder_VitalSigns_ExecutionSuite {
		
			
			private static String HTMLReportPAth = null;
			private static String CurrentPageTestCaseName = null;
			private static String ExcelSheetPath = null;
			private static String Excel_SheetName = null;
			private static String WritePath = null;
			private static BasePages ReportingPages = null;
			private static String ExcelSheetPath2 = null;
			private static String Excel_SheetName2 = null;
			private static String WritePath2 = null;
			private static String ExcelSheetPath3 = null;
			private static String Excel_SheetName3 = null;
			private static String WritePath3 = null;
			private static AdminUser_LoginPageData GoClinicalWorkOrder = null;
			private static NurseUser_LoginPageData NurseUserMyWorkOrders = null;
			//private static AdminUser_LoginPageData AdminUserReviewRecords = null;
			
			/*//This method used to add a time stamp to name of HTML report
				public static String DateTime() {
					Date date = new Date();
					DateFormat df = new SimpleDateFormat("MM_dd_yyyy hh_mm_ss");
					String timeStamp = df.format(date);
					return timeStamp;
				}*/
				
				@Parameters("browserName")
				@BeforeTest
				public void TestSetup(String browserName) throws IOException, BiffException {
					
					/*String Name = "GoClinical Work Order Automation Testing Test Suite";
					String timeStamp = DateTime();
					String ReportName = Name+"_"+ timeStamp+".html";*/
								
											HTMLReportPAth = System.getProperty("user.dir") + "\\Resources\\Reports\\Full Execution\\GoClinical Work Order Vital Signs Test Suite.html";
											CurrentPageTestCaseName = "GoClinical Work Order Vital Signs Test Suite";
											ReportingPages = new BasePages(HTMLReportPAth, CurrentPageTestCaseName);
					LaunchBrowser.LaunchBrowserapp(browserName);
				}

				@Test(priority=0)
				public static void AdminUserNewWorkOrder() throws IOException, InterruptedException, BiffException, WriteException {
					
					CurrentPageTestCaseName = "GoClinical Admin User Automation Testing New Work Orders";
					ExcelSheetPath = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					Excel_SheetName = "My Work Orders";
					WritePath = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					GoClinicalWorkOrder = new AdminUser_LoginPageData(ExcelSheetPath, Excel_SheetName, WritePath);
					BasePages.ResultsLog.ReportScriptStarted(CurrentPageTestCaseName);
					GoClinicalWorkOrder.ClearExistingStatus();
					
					
					for (int row = 1; row < GoClinicalWorkOrder.GetRows(); row++) {
						if (GoClinicalWorkOrder.Getdata("Patient ID", row).trim().length() > 2) {
							try {

								GoClinicalWorkOrder.setData("Admin New Work Order Execution Status", row, "Started");
														GoClinical_AdminUser_NewWorkOrder.AdminUser_LaunchURL();
														GoClinical_AdminUser_NewWorkOrder.AdminUser_Login(row);
														GoClinical_AdminUser_NewWorkOrder.Create_NewWorkOrder(row);
														GoClinical_AdminUser_NewWorkOrder.GoCliniCal_AdminUser_Menu_ClickLogout();
														GoClinical_AdminUser_NewWorkOrder.GoCliniCal_PageRefresh();
														GoClinicalWorkOrder.setData("Admin New Work Order Execution Status", row, "Executed");
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

								
							GoClinicalWorkOrder.setData("Admin New Work Order Execution Status", row, "Row Execution Not Completed");

							}
						}
					}
					
				}
				
				@Test(priority=1)
				public void NurseUserMyWorkOrders() throws IOException, InterruptedException, BiffException {
					
					CurrentPageTestCaseName = "GoClinical Nurse User Vital Signs My Work Order";
					ExcelSheetPath2 = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					Excel_SheetName2 = "My Work Orders";
					WritePath2 = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					NurseUserMyWorkOrders = new NurseUser_LoginPageData(ExcelSheetPath2, Excel_SheetName2, WritePath2);
					BasePages.ResultsLog.ReportScriptStarted(CurrentPageTestCaseName);
					NurseUserMyWorkOrders.ClearExistingStatus();
					
					
					for (int row = 1; row < NurseUserMyWorkOrders.GetRows(); row++) {
						if (NurseUserMyWorkOrders.Getdata("Patient ID", row).trim().length() > 2) {
							try {

								NurseUserMyWorkOrders.setData("Nurse User Execution Status", row, "Started");
								
										GoClinical_NurseUser_MyWorkOrders.NurseUser_Login(row);
										GoClinical_NurseUser_MyWorkOrders.MyWorkOrders_VitalSigns(row);
										GoClinical_NurseUser_MyWorkOrders.Records(row);
										GoClinical_NurseUser_MyWorkOrders.GoCliniCal_NurseUser_Menu_ClickLogout();
										GoClinical_NurseUser_MyWorkOrders.GoCliniCal_PageRefresh();
										NurseUserMyWorkOrders.setData("Nurse User Execution Status", row, "Executed");
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
								
							GoClinicalWorkOrder.setData("Nurse User Execution Status", row, "Row Execution Not Completed");

							}
						}
					}
					
				}
				
				
				@Test(priority=2)
				public void AdminUserReviewRecords() throws IOException, InterruptedException, BiffException, WriteException {
					
					CurrentPageTestCaseName = "GoClinical Admin User Vital Signs Review Records";
					ExcelSheetPath3 = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					Excel_SheetName3 = "My Work Orders";
					WritePath3 = System.getProperty("user.dir") + "\\Resources\\TestData\\MyWorkOrders_VitalAndSigns_TestData.xls";
					GoClinicalWorkOrder = new AdminUser_LoginPageData(ExcelSheetPath3, Excel_SheetName3, WritePath3);
					BasePages.ResultsLog.ReportScriptStarted(CurrentPageTestCaseName);
					GoClinicalWorkOrder.ClearExistingStatus();
					
					
					for (int row = 1; row < GoClinicalWorkOrder.GetRows(); row++) {
						if (GoClinicalWorkOrder.Getdata("Patient ID", row).trim().length() > 2) {
							try {

								GoClinicalWorkOrder.setData("Admin Records Execution Status", row, "Started");
													
													GoClinical_AdminUser_ReviewRecords.AdminUser_Login(row);
													GoClinical_AdminUser_ReviewRecords.Review_Records(row);
													GoClinical_AdminUser_NewWorkOrder.GoCliniCal_AdminUser_Menu_ClickLogout();
													GoClinical_AdminUser_NewWorkOrder.GoCliniCal_PageRefresh();
													GoClinicalWorkOrder.setData("Admin Records Execution Status", row, "Executed");

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

							GoClinicalWorkOrder.setData("Admin Records Execution Status", row, "Row Execution Not Completed");

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
