package com.Utils;
 
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Commons.GoClinicTest;
import ExecutionClasses.Onetest;
import ExecutionClasses.Onetest2;
import ExecutionClasses.Onetest3;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;

import java.time.format.DateTimeFormatter;

public class BasePages {
	protected static WebDriver driver;
	public static HTMLReporter ResultsLog;
	public static String EnteredCardNumber;
	ReadWriteDatatoExcel writetestreport;
	

	public BasePages(String ReportPath, String TestCaseName) {
		ResultsLog = new HTMLReporter(ReportPath);
		ResultsLog.attachreporter(TestCaseName);

	}

	ExtentHtmlReporter reporter;
	ExtentReports extent;
	public ExtentTest logger;

	public BasePages() {
		driver.switchTo().defaultContent();
		waitForPageLoadToComplete();
		waitForAjax();
		PageFactory.initElements(driver, this);

	}

	/**
	 * Method to wait for element to load in the page
	 * 
	 * @param WebElement
	 * @throws IOException
	 */
	protected static Boolean waitForElement(WebElement we) throws IOException {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(we));

			return true;
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "Element NOT visible," + we + " EXCEPTION CAUGHT", ex.toString());
			return false;
		}
	}

	protected static Boolean waitForElementFor10(WebElement we) throws IOException {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "Element NOT visible," + we + " EXCEPTION CAUGHT", ex.toString());
			return false;
		}
	}

	protected Boolean waitForErrorMessage(WebElement we) throws IOException {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "Element NOT visible," + we + " EXCEPTION CAUGHT", ex.toString());
			// should change it to Report . Info for error message -- Pradeep

			return false;
		}
	}

	protected Boolean waitForElementWithNoCatchLog(WebElement we) throws IOException {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element WebElement need to check
	 * @return True if present
	 */
	protected static boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	protected static boolean isElementPresentwithRequiuredText(WebElement element, String Text) {
		try {
			element.getText().equalsIgnoreCase(Text);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	/**
	 * Method to wait for all Ajax calls to complete
	 * 
	 * @author : Pradeep Kumar Arigue
	 */
	public static void waitForAjax() {

		WebDriverWait wait = new WebDriverWait(driver, 300);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driverr) {
				try {
					return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		wait.until(jQueryLoad);
	}

	/**
	 * Method to wait until all javascript in a page has loaded completely
	 * 
	 * @author : Pradeep Kumar Arigue
	 */
	public static void waitForPageLoadToComplete() {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		try {
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {

				public Boolean apply(WebDriver driverr) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};
			wait.until(jsLoad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clickOnButton(WebElement element, String btnName) throws IOException {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {
				element.click();
				waitForAjax();
				waitForPageLoadToComplete();
				ResultsLog.ReportDoneEvent("Successfully clicked on Button" + btnName);
			}
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, btnName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString());
		}
	}

	public static void clickOnCheckBox(WebElement element, String chkName) throws IOException {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {
				element.click();
				waitForAjax();
				waitForPageLoadToComplete();
				ResultsLog.ReportDoneEvent("Successfully clicked on CheckBox" + chkName);
			}
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, chkName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString());
		}
	}

	public static void clickOnEditIcon(WebElement element, String btnName) throws IOException {
		try {
			waitForElement(element);
			if (isElementPresent(element)) {

				Actions action = new Actions(driver);
				WebElement elementLocator = element;
				action.doubleClick(elementLocator).perform();
				waitForAjax();
				waitForPageLoadToComplete();
				ResultsLog.ReportDoneEvent("Successfully clicked on Edit" + btnName);
			}
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, btnName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString());
		}
	}

	/***
	 * Method to enter text in a textbox
	 * 
	 * @param : Element Name
	 * @param : Field Name
	 * @param : Text to Enter
	 * @author : Pradeep Kumar Arigue
	 * @throws IOException
	 ***/
	public static void enterText(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {
			try {
				waitForElement(element);
				if (isElementPresent(element)) {
					element.clear();
					waitForAjax();
					element.sendKeys(text);
					ResultsLog.ReportDoneEvent("Successfully entered " + fieldName + ": " + text.trim());
				}
				waitForAjax();
			} catch (RuntimeException ex) {
				ResultsLog.ReportFail(driver,
						"NOT able to enter " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString());

			}
		}
	}

	public static void enterTextWithoutClearing(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {
			try {
				waitForElement(element);
				if (isElementPresent(element)) {
					// element.clear();
					element.click();
					waitForAjax();
					element.sendKeys(text);
					ResultsLog.ReportDoneEvent("Successfully entered " + fieldName + ": " + text.trim());
				}
				waitForAjax();
			} catch (RuntimeException ex) {
				ResultsLog.ReportFail(driver,
						"NOT able to enter " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString());

			}
		}
	}

	/**
	 * Verify Application in Correct Page.
	 * 
	 * @param isValidPage
	 * @return Nil
	 * @throws IOException
	 */
	public void verifyCorrectPageHeading(WebElement element, String ExpectedHeading) throws IOException {
		waitForAjax();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		waitForElement(element);
		try {
			if (element.getText().equalsIgnoreCase(ExpectedHeading)) {
				ResultsLog.ReportPass(driver, "Successfully Navigated to" + ExpectedHeading, ExpectedHeading);
			} else if (!element.getText().equalsIgnoreCase(ExpectedHeading)) {
				ResultsLog.ReportFail(driver, "Application is not in correct page, Heading is", element.getText());
			}
		} catch (Exception e) {

		}
	}

	/***
	 * Method to click on a link(WebElement link)
	 * 
	 * @param : Element Name
	 * @author : Pradeep Kumar Arigue
	 * @throws IOException
	 ***/
	public static void clickOnLink(WebElement element, String linkName) throws IOException {
		try {
			waitForPageLoadToComplete();
			waitForElement(element);
			if (isElementPresent(element)) {
				element.click();
				waitForAjax();
				ResultsLog.ReportDoneEvent("Successfully clicked on link: " + linkName);
			}
		} catch (Exception ex) {
			ResultsLog.ReportFail(driver, "NOT able to click" + linkName + "EXCEPTION CAUGHT", ex.toString());

		}
	}

	public void clickOnLinkWithNoCatchLog(WebElement element, String linkName) throws IOException {
		try {
			waitForPageLoadToComplete();
			waitForElementWithNoCatchLog(element);
			if (isElementPresent(element)) {
				element.click();
				waitForAjax();
				ResultsLog.ReportDoneEvent("Successfully clicked on link: " + linkName);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	public void CloseBrowser() throws IOException {
		try {
			driver.close();
			ResultsLog.ReportDoneEvent("Successfully closed the browser");
		} catch (Exception ex) {

		}
	}

	protected void clickMainMenu(WebElement element) throws IOException {
		try {
			waitForElement(element);
			System.out.println(element.getAttribute("value"));
			if (element.getAttribute("value").equals("WC Members")) {
				waitForElement(element);
				element.click();
			} else {
				driver.findElement(By.xpath("//img[@class='allTabsArrow']")).click();
			}

			// ResultsLog.ReportDoneEvent("Click Main Menu" + " " + element.getText().trim()
			// + " clicked successfully.");
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "NOT able to click '" + element.getText().trim() + " . EXCEPTION CAUGHT : ",
					ex.toString());

		}
	}

	protected void clickMainMenuWithNoCatchLOG(WebElement element) throws IOException {
		try {
			waitForElementWithNoCatchLog(element);
			element.click();
			// ResultsLog.ReportDoneEvent("Click Main Menu" + " " + element.getText().trim()
			// + " clicked successfully.");
		} catch (RuntimeException ex) {

		}
	}

	// *****************************************************************************************************************//
	// DropDown TODO
	// *****************************************************************************************************************//
	/***
	 * Method to select drop down by text
	 * 
	 * @param : Element Name
	 * @return :
	 * @author :Pradeep Kumar Arigue
	 * @throws IOException
	 ***/
	public static void selectByText(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {
			try {
				waitForElement(element);
				Select sel = new Select(element);
				if (isElementPresent(element)) {
					if (!(text.isEmpty())) {
						sel.selectByVisibleText(text.trim());
						waitForAjax();
						waitForPageLoadToComplete();
						ResultsLog.ReportDoneEvent(
								"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
					} else {
						ResultsLog.ReportDoneEvent("Drop Down Value is not present " + fieldName);
					}
				}
			} catch (Exception ex) {
				System.out.println("Exception in Dropdrwon :");
				ex.printStackTrace();
				ResultsLog.ReportFail(driver, "NOT able to select from the " + fieldName + " DropDown (" + text.trim()
						+ ") \n EXCEPTION CAUGHT : ", ex.getMessage());
			}
		}
	}

	public static void selectDpdForLightning(String dpdText, String fieldName) throws IOException {
		try {
			System.out.println("//a[text()='" + dpdText + "']");
			driver.findElement(By.xpath("//a[text()='" + dpdText + "']")).click();
			waitForAjax();
			waitForPageLoadToComplete();
			ResultsLog.ReportDoneEvent("Successfully selected from " + fieldName);

		} catch (Exception ex) {
			System.out.println("Exception in Dropdrwon :");
			ex.printStackTrace();
			ResultsLog.ReportFail(driver, "NOT able to select from the " + fieldName + "\n EXCEPTION CAUGHT : ",
					ex.getMessage());
		}
	}

	public static void selectByTextwithoutReport(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {

			waitForElementforstale(element);
			Select sel = new Select(element);
			if (isElementPresent(element)) {
				if (!(text.isEmpty())) {
					sel.selectByVisibleText(text.trim());
					waitForAjax();
					ResultsLog.ReportDoneEvent(
							"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
				} else {
					ResultsLog.ReportDoneEvent("Drop Down Value is not present " + fieldName);
				}
			}

		}
	}

	public static void selectByValue(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {
			try {
				waitForElement(element);
				Select sel = new Select(element);
				if (isElementPresent(element)) {
					if (!(text.isEmpty())) {
						sel.selectByValue(text);
						waitForAjax();
						ResultsLog.ReportDoneEvent(
								"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
					} else {
						ResultsLog.ReportDoneEvent("Drop Down Value is not present " + fieldName);
					}
				}
			} catch (RuntimeException ex) {
				ResultsLog.ReportFail(driver, "NOT able to select from the " + fieldName + " DropDown (" + text.trim()
						+ ") \n EXCEPTION CAUGHT : ", ex.getMessage());
			}
		}
	}

	protected static Boolean waitForElementforstale(WebElement we) throws IOException {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(we));
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (RuntimeException ex) {

			ResultsLog.ReportDoneEvent(
					"Tried to click Stale element," + we + "Clicked Through Try Catch" + ex.toString());
			return false;
		}
	}

	/***
	 * Method to select drop down by text without a wait for Ajax
	 * 
	 * @param : Element Name
	 * @return :
	 * @author : Adam Klein
	 * @throws IOException
	 ***/
	public void selectByText_NoWait(WebElement element, String fieldName, String text) throws IOException {
		if (text != "") {
			try {
				waitForElement(element);
				Select sel = new Select(element);
				if (isElementPresent(element)) {
					if (!(text.isEmpty())) {
						sel.selectByVisibleText(text.trim());
						ResultsLog.ReportDoneEvent(
								"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
					} else {
						ResultsLog.ReportDoneEvent("Drop Down Value is not present " + fieldName);
					}
				}
			} catch (RuntimeException ex) {
				ResultsLog.ReportFail(driver, "NOT able to select from the " + fieldName + " DropDown (" + text.trim()
						+ ") \n EXCEPTION CAUGHT : ", ex.getMessage());
			}
		}
	}

	/***
	 * Method to generate Card Number if does not exist
	 * 
	 * @return : SSN as a String
	 * @author : Pradeep Kumar Arigue
	 ***/
	public static String cardnumber() {

		String newCN;
		Random rand = new Random();
		int value1 = rand.nextInt(9) + 1;
		int value2 = rand.nextInt(9) + 1;
		int value3 = rand.nextInt(9) + 1;
		int value4 = rand.nextInt(9) + 1;
		int value5 = rand.nextInt(9) + 1;
		int value6 = rand.nextInt(9) + 1;
		int value7 = rand.nextInt(9) + 1;
		int value8 = rand.nextInt(9) + 1;

		String num1 = Integer.toString(value1);
		String num2 = Integer.toString(value2);
		String num3 = Integer.toString(value3);
		String num4 = Integer.toString(value4);
		String num5 = Integer.toString(value5);
		String num6 = Integer.toString(value6);
		String num7 = Integer.toString(value7);
		String num8 = Integer.toString(value8);

		newCN = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8;
		ResultsLog.ReportDoneEvent("Card number not in datasheet.  Randomly generated as: " + newCN);

		EnteredCardNumber = newCN;
		return newCN;
	}

	/**
	 * Assert Element text equals expected - value of webelement
	 * 
	 * @param element
	 * @param String  Expected Text
	 * @param String  stepName
	 * @throws IOException
	 */
	protected static void verifyTextEqual(WebElement element, String expectedText, String stepName) throws IOException {
		if (expectedText != "") {
			try {
				waitForAjax();
				waitForElement(element);
				Assert.assertTrue(element.getText().trim().equalsIgnoreCase(expectedText));
				ResultsLog.ReportPass(driver, "Verify " + stepName + " " + "Text matches: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")", stepName);
			} catch (AssertionError e) {
				e.printStackTrace();
				ResultsLog.ReportFail(driver, "Verify " + stepName, "Text does NOT match: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")");
			} catch (Exception ex) {
				ex.printStackTrace();
				ResultsLog.ReportFail(driver, "Error verifying:" + stepName + "EXCEPTION CAUGHT", ex.toString());
			}
		}
	}

	protected static void verifyTextWithContains(WebElement element, String expectedText, String stepName)
			throws IOException {
		if (expectedText != "") {
			try {
				waitForAjax();
				waitForPageLoadToComplete();
				if (isElementPresent(element)) {
					waitForElement(element);
					Assert.assertTrue(element.getText().trim().contains(expectedText));
					ResultsLog.ReportPass(driver, "Verify " + stepName + " " + "Text matches: Actual Value("
							+ element.getText() + ") Expected Value (" + expectedText + ")", stepName);
				} else {
					ResultsLog.ReportFail(driver, "Verify " + stepName, "Expected Element Is Not Present");

				}
			} catch (AssertionError e) {
				e.printStackTrace();
				ResultsLog.ReportFail(driver, "Verify " + stepName, "Text does NOT match: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")");
			} catch (Exception ex) {
				ex.printStackTrace();
				ResultsLog.ReportFail(driver, "Error verifying:" + stepName + "EXCEPTION CAUGHT", ex.toString());
			}
		}
	}

	protected static void verifyTextEqualwithContains(WebElement element, String expectedText, String stepName)
			throws IOException {
		if (expectedText != "") {
			try {
				if (expectedText.equalsIgnoreCase("pickup")) {
					expectedText = "Pick-Up";

				}

				waitForAjax();
				waitForElement(element);
				Assert.assertTrue(element.getText().trim().equalsIgnoreCase(expectedText));
				ResultsLog.ReportPass(driver, "Verify " + stepName + " " + "Text matches: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")", stepName);
			} catch (AssertionError e) {
				e.printStackTrace();
				ResultsLog.ReportFail(driver, "Verify " + stepName, "Text does NOT match: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")");
			} catch (Exception ex) {
				ex.printStackTrace();
				ResultsLog.ReportFail(driver, "Error verifying:" + stepName + "EXCEPTION CAUGHT", ex.toString());
			}
		}
	}

	protected static void verifyTextEqualWithoutCatchLog(WebElement element, String expectedText, String stepName)
			throws IOException {
		if (expectedText != "") {
			try {
				waitForAjax();
				waitForElement(element);
				Assert.assertTrue(element.getText().trim().equalsIgnoreCase(expectedText));
				ResultsLog.ReportPass(driver, "Verify " + stepName + " " + "Text matches: Actual Value("
						+ element.getText() + ") Expected Value (" + expectedText + ")", stepName);
			} catch (AssertionError e) {
			} catch (Exception ex) {
			}
		}
	}

	protected void verifyTextEqualWithOutLog(WebElement element, String expectedText, String stepName)
			throws IOException {

		waitForElement(element);
		Assert.assertTrue(element.getText().trim().equalsIgnoreCase(expectedText));
		ResultsLog.ReportPass(driver, "Verify " + stepName + " " + "Text matches: Actual Value(" + element.getText()
				+ ") Expected Value (" + expectedText + ")", stepName);

	}

	protected String GetTextfromPage(WebElement element) throws IOException {
		try {
			waitForElement(element);
			element.getText();
		} catch (Exception e) {
			ResultsLog.ReportInfo(driver, "Element not Present:- " + element);
		}
		return element.getText();
	}

	/**
	 * Assert Element text equals expected - value of webelement
	 * 
	 * @param element
	 * @param String  Expected Text
	 * @param String  stepName
	 * @throws IOException
	 */
	protected void verifyTextEqualwithAttributeValue(WebElement element, String expectedText, String stepName)
			throws IOException {
		try {

			waitForElement(element);
			Assert.assertTrue(element.getAttribute("value").trim().equalsIgnoreCase(expectedText));
			ResultsLog.ReportPass(
					driver, "Verify " + stepName + " " + "Text matches: Actual Value("
							+ element.getAttribute("value").trim() + ") Expected Value (" + expectedText + ")",
					stepName);
		} catch (AssertionError e) {
			e.printStackTrace();
			ResultsLog.ReportFail(driver, "Verify " + stepName, "Text does NOT match: Actual Value("
					+ element.getAttribute("value").trim() + ") Expected Value (" + expectedText + ")");
		} catch (Exception ex) {
			ex.printStackTrace();
			ResultsLog.ReportFail(driver, "Error verifying:" + stepName + "EXCEPTION CAUGHT", ex.toString());

		}
	}

	/***
	 * Method to reformat expected amount fields
	 * 
	 * @param : String
	 * @return : String
	 * @author : Pradeep Kumar Arigue
	 * @throws IOException
	 ***/
	public static String formatAmountText(String text) throws IOException {

		String formattedAmountText = "";
		try {
			if (text != "") {
				formattedAmountText = ("$" + text);
			}
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "Error Formatting Amount Field (" + text.trim() + ") EXCEPTION CAUGHT",
					ex.toString());

		}
		return formattedAmountText;
	}

	public static String formatAmountTextwithDecimal(String text) throws IOException {

		String formattedAmountText = "";
		try {
			if (text != "") {
				formattedAmountText = ("$" + text + ".00");
			}
		} catch (RuntimeException ex) {
			ResultsLog.ReportFail(driver, "Error Formatting Amount Field (" + text.trim() + ") EXCEPTION CAUGHT",
					ex.toString());

		}
		return formattedAmountText;
	}

	public boolean waitForExpectedTitle(String TitleContains) {
		try {
			waitForAjax();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			Boolean S = wait.until(ExpectedConditions.titleContains(TitleContains));
			if (S) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void titlevalidation(String ExpectedTitleName) {
		waitForPageLoadToComplete();
		waitForAjax();
		driver.getTitle().equalsIgnoreCase(ExpectedTitleName);

	}

	/***
	 * Method to switch into frame only when available
	 * 
	 * @param : Int index of the frame.
	 * @return : None
	 * @author : Pradeep Kumar Arigue
	 * @throws InterruptedException
	 ***/
	public void frameSwitch(int frameid) throws InterruptedException {

		driver.switchTo().defaultContent();
		System.out.println("Driver to default frame");

		try {
			waitForElement(driver.findElement(By.tagName("iframe")));
		} catch (Exception ex) {
			System.out.println("Before frame Switch" + ex.getMessage());
		}
		waitForPageLoadToComplete();
		waitForAjax();
		for (int i = 0; i < frameid; i++) {
			try {
				driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
				System.out.println("Switched to Iframe - Tag Name");
			} catch (Exception e2) {
				System.out.println("Inside loop: Iteration" + i + "  " + e2.getMessage());
			}
			waitForPageLoadToComplete();
			waitForAjax();
		}
	}

	public void frameSwitch1(int frameid) throws InterruptedException {
		driver.switchTo().defaultContent();
		waitForPageLoadToComplete();
		waitForAjax();
		for (int i = 0; i < frameid; i++) {
			try {
				driver.switchTo().frame(frameid);
			} catch (Exception e2) {
				try {
					driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
				} catch (Exception e) {
					System.out.println("Inside loop: Iteration" + i + "  " + e2.getMessage());
				}

			}
			waitForPageLoadToComplete();
			waitForAjax();
		}
	}

	public void frameSwitch4(int frameid) throws InterruptedException {
		driver.switchTo().defaultContent();
		waitForPageLoadToComplete();
		waitForAjax();

		try {
			driver.switchTo().frame(frameid);
		} catch (Exception e2) {
			System.out.println("Expected Frame is not Available");
		}
		waitForPageLoadToComplete();
		waitForAjax();
	}

//	public void frameSwitchLoopForWOM(int frameid) throws InterruptedException {
//
//		for (int i = 0; i < frameid; i++) {
//			try {
//				driver.switchTo().defaultContent();
//				waitForPageLoadToComplete();
//				waitForAjax();
//				driver.switchTo().frame(i);
//				{
//					ClassicWCMemberValidationPage.readOnlyClassicWCWomOpenbottles().click();
//					i = 10;
//				}
//				waitForPageLoadToComplete();
//				waitForAjax();
//			} catch (Exception e2) {
//				System.out.println("Expected Frame is not Available");
//			}
//
//		}
//	}

	public static void ExecutionPageExceptionErrorCapture(String CurrentPageTestCaseName, String Exception) {

		ResultsLog.logger.log(Status.FAIL, CurrentPageTestCaseName);
		ResultsLog.logger.log(Status.WARNING, Exception);

	}

	protected static void verifyCheckBoxCheckedStatus(WebElement element, String expectedStatus, String stepName)
			throws IOException {
		if (expectedStatus != "") {
			try {
				waitForAjax();
				waitForElement(element);

				Assert.assertTrue(element.getAttribute("class").contains(expectedStatus));
				ResultsLog.ReportPass(
						driver, "Verify " + stepName + " " + "Status matches: Actual Value("
								+ element.getAttribute("class") + ") Expected Value (" + expectedStatus + ")",
						stepName);
			} catch (AssertionError e) {
				e.printStackTrace();
				ResultsLog.ReportFail(driver, "Verify " + stepName, "STatus does NOT match: Actual Value("
						+ element.getAttribute("class") + ") Expected Value (" + expectedStatus + ")");
			} catch (Exception ex) {
				ex.printStackTrace();
				ResultsLog.ReportFail(driver, "Error verifying:" + stepName + "EXCEPTION CAUGHT", ex.toString());
			}
		}
	}

	public String[] formatTextToDate(String textDate) {
		String[] dates = null;
		String date;
		try {
			// me;
			SimpleDateFormat desiredDateFormat = new SimpleDateFormat("mm/dd/yyyy");
			date = desiredDateFormat.format(desiredDateFormat.parse(textDate));
			System.out.println("Text Date: " + textDate);
			System.out.println("Formatted Date old way: " + date);
			if (date.contains("/00")) {
				System.out.println("Warning: Date may not be formatted correctly");
				if (date.contains("/000") || date.contains("/001") || date.contains("/002")) {
					date = date.replace("/00", "/20");
				} else {
					date = date.replace("/00", "/19");

				}
			}
			dates = date.split("/");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (DateTimeParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return dates;
	}

	public static String FormatDate1(String Date) {
		System.out.println("In FormatDate1 ");
		if (Date.startsWith("1/")) {
			String formatDate = "0" + Date;
			System.out.println(formatDate);
			return formatDate;
		} else {
			System.out.println("Regulare Date");
			return Date;

		}

	}

	public static String getCurrentDateStamp() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("M/d/yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getCurrentDateStampWithMM() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("MM-d-yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getCurrentDateStampWithYYYYMMDD() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("YYYY-MM-d");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getCurrentDateStampPlusRequiredDate(int duration) {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, duration);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		SimpleDateFormat sdfDate = new SimpleDateFormat("M/d/yyyy");
		String strDate = sdfDate.format(lastDayOfMonth);

		return strDate;
	}

	public static String getCurrentDateStampPlusRequiredDate2() {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		Date lastDayOfMonth = calendar.getTime();
		SimpleDateFormat sdfDate = new SimpleDateFormat("MM-d-yyyy");
		String strDate = sdfDate.format(lastDayOfMonth);

		return strDate;
	}

	public static String GetcuttentMonthminus1() {
		LocalDate now = LocalDate.now(); // 2015-11-24
		LocalDate earlier = now.minusMonths(1); // 2015-10-24
		earlier.getMonth();
		String strDate = earlier.getMonth().toString();
		return strDate;
	}

	public static String GetcurrentMonthminus1number() {
		LocalDate now = LocalDate.now(); // 2015-11-24
		LocalDate earlier = now.minusMonths(1); // 2015-10-24
		// earlier.getMonth();
		String intDate = String.valueOf(earlier.getMonthValue());
		return intDate;
	}

	public static String getCurrentmonth() {
		SimpleDateFormat DateMonth = new SimpleDateFormat("MM");
		Date Month = new Date();
		String Month0 = DateMonth.format(Month);
		if (Month0.contains("0")) {
			SimpleDateFormat Datemonth = new SimpleDateFormat("M");
			Date nowmonth = new Date();
			String month0 = Datemonth.format(nowmonth);
			return month0;
		} else {
			return Month0;
		}
	}

	public static String getCurrentday() {
		SimpleDateFormat DateMonth = new SimpleDateFormat("dd");
		Date date = new Date();
		String date00 = DateMonth.format(date);
		if (date00.contains("0")) {
			SimpleDateFormat Datemonth = new SimpleDateFormat("d");
			Date nowdate = new Date();
			String date0 = Datemonth.format(nowdate);
			return date0;
		} else {
			return date00;
		}
	}

	public static String getCurrentMonthNamepractice() {
		// SimpleDateFormat curentdate = new SimpleDateFormat("dd");
		// date = new Date();
		// String date00 = curentdate.format(date);

		Calendar c = Calendar.getInstance();
		String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
		return month;
	}
	
	/*******Treximo
	 * @throws ParseException ********/
	  
	public static String TreximoDateField1(String strDate) throws ParseException {
	DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("MMddyyyy", Locale.ENGLISH);
	        LocalDate date = LocalDate.parse(strDate, dtfInput);
	        System.out.println("date" + date);
	DateTimeFormatter dtfOutputEng = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
	        String formattedEng = dtfOutputEng.format(date);
	        System.out.println(formattedEng);
			return formattedEng;
	}
	
	public static String TreximoTimeField1(String strTime) {
		DateTimeFormatter dtfInput = DateTimeFormatter.ofPattern("HHmma", Locale.ENGLISH);
	        System.out.println("dtfInput"+dtfInput);
	        LocalTime date = LocalTime.parse(strTime, dtfInput);
	        System.out.println("date"+date);
	        String timefield = date.toString();
	        System.out.println("timefield"+timefield);
				return timefield;
		}
	
	public static String getCurrentYear() {
		SimpleDateFormat DateMonth = new SimpleDateFormat("yyyy");
		Date Year = new Date();
		String year = DateMonth.format(Year);
		return year;
	}

	public static String getCurrentMonthName() {
		Calendar c = Calendar.getInstance();
		String year = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
		return year;
	}

	public static String getCurrentdate() {

		String Datewithout0 = getCurrentmonth() + "/" + getCurrentday() + "/" + getCurrentYear();
		return Datewithout0;

	}

	public static void Scroll(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public static void robotClassActionForEnter() throws IOException, AWTException, InterruptedException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);

	}

	public static Object scrollElementIntoView(WebElement element) {
		return ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public int WriteTestreportCount() {

		return writetestreport.GetRowsReport();

	}

	public void WriteTestReportinExcel(String Action, String Expectedresults, String ActualResults, int row)
			throws BiffException, IOException {

		try {
			System.out.println(System.getProperty("user.dir")
					+ GoClinicTest.TestSettingsObjects.getProperty("ExcelReportWritePath"));
			writetestreport = new ReadWriteDatatoExcel(
					System.getProperty("user.dir")
							+ GoClinicTest.TestSettingsObjects.getProperty("ExcelReportWritePath"),
					GoClinicTest.TestSettingsObjects.getProperty("ExcelReportSheetName"));
			String Stepno = "Step No " + writetestreport.GetRowsReport();
			writetestreport.setReportData("StepNo", writetestreport.GetRowsReport(), Stepno);
			writetestreport.setReportData("Action", writetestreport.GetRowsReport(), Action);
			writetestreport.setReportData("ExpectedResult", writetestreport.GetRowsReport(), Expectedresults);
			writetestreport.setReportData("ActualResult", writetestreport.GetRowsReport(), ActualResults);
			writetestreport.setReportData("ScreenshotPath", writetestreport.GetRowsReport(), "N/A");
			// writetestreport.setReportData("ScreenshotPath",
			// writetestreport.GetRowsReport(),
			// System.getProperty("user.dir") + "\\Resources\\Screenshots");
		} catch (Exception e) {
			System.out.println("write test report excel error catch" + e);
		}

	}

	public void WriteTestReportinExcelWithScreenShot(String Action, String Expectedresults, String ActualResults,
			int row) throws BiffException, IOException {
		try {
			writetestreport = new ReadWriteDatatoExcel(
					System.getProperty("user.dir")
							+ GoClinicTest.TestSettingsObjects.getProperty("ExcelReportWritePath"),
					GoClinicTest.TestSettingsObjects.getProperty("ExcelReportSheetName"));
			String Stepno = "Step No " + writetestreport.GetRowsReport();
			writetestreport.setReportData("StepNo", writetestreport.GetRowsReport(), Stepno);
			writetestreport.setReportData("Action", writetestreport.GetRowsReport(), Action);
			writetestreport.setReportData("ExpectedResult", writetestreport.GetRowsReport(), Expectedresults);
			writetestreport.setReportData("ActualResult", writetestreport.GetRowsReport(), ActualResults);
			writetestreport.setReportData("ScreenshotPath", writetestreport.GetRowsReport(), GoClinicTest.ScreenshotPath);
			ResultsLog.CaptureScreenShotforExcelReport(driver, Stepno+"_" + GoClinicTest.testMethodName+"_" , GoClinicTest.ScreenshotPath);
		} catch (Exception e) {
			System.out.println("write test report excel error catch" + e);
		}

	}

}
