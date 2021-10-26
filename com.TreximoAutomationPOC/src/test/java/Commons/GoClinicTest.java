package Commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import ExecutionClasses.FullTestSuiteScenario;
import ExecutionClasses.Onetest;
import ExecutionClasses.Onetest2;
import ExecutionClasses.Onetest3;

public class GoClinicTest {
	
	public static Properties TestSettingsObjects = null;
	public static FileInputStream TestSettingsfile = null;
	public static String excelSheetPath;
	
	public static String Sheetname;
	public static String WritePath;
	public static String HTMLReportPath;
	public static String CurrentPageTestCaseName;
	public static String ScreenshotPath;
	//public static String TestMethodName1;
	public static String TestMethodName2;
	public static String TestMethodName3;
	public static String TestMethodName4;


	
	public GoClinicTest( String PropertyFileName) throws IOException {
		TestSettingsObjects = new Properties();
		if(PropertyFileName.equalsIgnoreCase("AdminNew")) {
			TestSettingsfile = new FileInputStream(System.getProperty("user.dir") + "\\AdminNew.properties");
			TestSettingsObjects.load(TestSettingsfile);
			excelSheetPath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			Sheetname = "My Work Orders";
			
			WritePath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			CurrentPageTestCaseName = "GoClinical Admin User New Work Order";
			HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\GoClinical_AdminUser_NewWorkOrders.html";
			ScreenshotPath= System.getProperty("user.dir") + "\\Resources\\Screenshots\\New Work Order";
			//TestMethodName1=Onetest.TestMethodName;
		}
		
		if(PropertyFileName.equalsIgnoreCase("NurseMyWorkOrders")) {
			TestSettingsfile = new FileInputStream(System.getProperty("user.dir") + "\\NurseMyWorkOrders.properties");
			TestSettingsObjects.load(TestSettingsfile);
			excelSheetPath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			Sheetname = "My Work Orders";
			
			WritePath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			CurrentPageTestCaseName = "GoClinical Nurse User My Work Orders";
			HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\GoClinical_NurseUser_MyWorkOrders.html";
			ScreenshotPath= System.getProperty("user.dir") + "\\Resources\\Screenshots\\My Work Orders";
			TestMethodName2=Onetest2.TestMethodName;
		}
		
		
		if(PropertyFileName.equalsIgnoreCase("AdminRecords")) {
			TestSettingsfile = new FileInputStream(System.getProperty("user.dir") + "\\AdminRecords.properties");
			TestSettingsObjects.load(TestSettingsfile);
			excelSheetPath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			Sheetname = "My Work Orders";
			
			WritePath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			CurrentPageTestCaseName = "GoClinical Admin User Records";
			HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\GoClinical_AdminUser_Records.html";
			ScreenshotPath= System.getProperty("user.dir") + "\\Resources\\Screenshots\\Submit Records";
			TestMethodName3=Onetest3.TestMethodName;
		}
		
		if(PropertyFileName.equalsIgnoreCase("GoClinicalWorkOrder")) {
			TestSettingsfile = new FileInputStream(System.getProperty("user.dir") + "\\GoClinicalWorkOrder.properties");
			TestSettingsObjects.load(TestSettingsfile);
			excelSheetPath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			Sheetname = "My Work Orders";
			
			WritePath = System.getProperty("user.dir")
					+ "\\Resources\\TestData\\MyWorkOrders_AutoTestingForm_TestData.xls";
			CurrentPageTestCaseName = "GoClinical Work Order Test Suite";
			HTMLReportPath =System.getProperty("user.dir") + "\\Resources\\Reports\\Full Execution\\GoClinical Work Order Test Suite.html";
			ScreenshotPath= System.getProperty("user.dir") + "\\Resources\\Test Suite Screenshots";
			TestMethodName4=FullTestSuiteScenario.TestMethodName;
		}
	}


}
