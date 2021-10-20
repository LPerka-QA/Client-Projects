package com.Utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HTMLReporter {

	ExtentHtmlReporter reporter;
	ExtentReports extent;
	public ExtentTest logger;

	public HTMLReporter(String Reportpath) {
		reporter = new ExtentHtmlReporter(Reportpath);
		reporter.config().setDocumentTitle("HTML AUtomation Test Execution Report");
		reporter.config().setTheme(Theme.STANDARD);

//		// New Lines of Code
//		extent = new ExtentReports();
//		extent.attachReporter(reporter);
//		HTMLReporter.logger = logger;
//		logger = extent.createTest(TestName);
	}

	public void attachreporter(String TestName) {
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		logger = extent.createTest(TestName);
	}

	public void ReportScriptStarted(String Text) {
		logger.info(Text);
		extent.flush();
	}

	public void ReportPass(WebDriver driver, String testdescription, String ScreenshotName) throws IOException {
		CaptureScreenShot(driver, ScreenshotName + "Pass");
		logger.log(Status.PASS, testdescription);
		extent.flush();
	}

	public void ReportFail(WebDriver driver, String Desc, String Error) throws IOException {

		CaptureScreenShot(driver, Desc + "Fail");
		logger.log(Status.FAIL, Desc);
		logger.error(Error);
		extent.flush();
	}

	public void ReportScriptfailed(WebDriver driver, String TestCaseName, String Exception) {
		CaptureScreenShot(driver, TestCaseName);
		logger.error(TestCaseName);
		logger.log(Status.FATAL, Exception);
		extent.flush();
	}

	public void ReportScriptCompletedSucessfully(String Text) {
		logger.log(Status.PASS, Text);
		extent.flush();
	}

	public void ReportDoneEvent(String testdescription) {
		logger.pass(testdescription);
		extent.flush();

	}

	public void addModal(String testdescription, String error) {
		logger.log(Status.SKIP, error);
		extent.flush();

	}

//	public void ReportPass(String testdescription, String error) {
//		logger.log(Status.PASS, (Markup) logger.createNode("Test"));
//
//	}

	public void Flush() {

		extent.flush();
	}

	public String CaptureScreenShot(WebDriver driver, String screenShotName) {
		try {

			String timeStamp = customDate();
			String snapshotpath = "C:\\Users\\parigue\\eclipse-workspace\\CH_MGS_App\\ScreenShots" + "\\"
					+ screenShotName + timeStamp + ".png";

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File(snapshotpath);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}

	public String customDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy_MM_dd hh_mm_ss");
		String timeStamp = df.format(date);
		return timeStamp;
	}

	/*
	 * public void getResult(ITestResult result) throws Exception { if
	 * (result.getStatus() == ITestResult.FAILURE) { // MarkupHelper is used to
	 * display the output in different colors // To capture screenshot path and
	 * store the path of the screenshot in the string // "screenshotPath" // We do
	 * pass the path captured by this method in to the extent reports using //
	 * "logger.addScreenCapture" method. // String
	 * Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed"); // String
	 * screenshotPath = getScreenShot(LaunchBrowser.driver, // result.getName()); //
	 * To add it in the extent report //
	 * logger.fail("Test Case Failed Snapshot is below " + //
	 * logger.addScreenCaptureFromPath(screenshotPath)); } else if
	 * (result.getStatus() == ITestResult.SKIP) {
	 * HTMLReporter.logger.log(Status.SKIP,
	 * MarkupHelper.createLabel(result.getName() + " - Test Case Skipped",
	 * ExtentColor.ORANGE)); } else if (result.getStatus() == ITestResult.SUCCESS) {
	 * HTMLReporter.logger.log(Status.PASS,
	 * MarkupHelper.createLabel(result.getName() + " Test Case PASSED",
	 * ExtentColor.GREEN)); } LaunchBrowser.driver.quit(); }
	 */

}
