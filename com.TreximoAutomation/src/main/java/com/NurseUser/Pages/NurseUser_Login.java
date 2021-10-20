package com.NurseUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.NurseUser.PagesData.NurseUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import jxl.read.biff.BiffException;

public class NurseUser_Login extends BasePages {
	
	NurseUser_LoginPageData data = new NurseUser_LoginPageData();
	
	// Objects from Application

		public WebElement txtUserEmail() {

			return driver.findElement(By.id("user_email"));
		}

		public WebElement txtUserPassword() {

			return driver.findElement(By.id("user_password"));
		}

		public WebElement btnLog_In() {

			return driver.findElement(By.xpath("//input[@value='Log in' and @type='submit']"));
		}
		
		public static WebElement readonly_SignInSuccessMsg() {
			return driver.findElement(By.xpath("//div[@class='notice']"));

		}
		
		public static WebElement readonly_MyWorkOrders() {
			return driver.findElement(By.xpath("//div[@class='work_orders']//h2"));

		}

	// Each Object  Method	

		public void EnterUserEmail() throws IOException {

			enterText(txtUserEmail(), "Nurse User Email", LaunchBrowser.TestSettingsObjects.getProperty("NurseUserEmail"));
		}

		public void EnterUserPassword() {

			txtUserPassword().sendKeys(LaunchBrowser.TestSettingsObjects.getProperty("NurseUserPassword"));
		}

		public void ClickLogInButton() throws IOException {

			clickOnButton(btnLog_In(), "Log In");
		}
		
		public void ValidateSignInMsg(int row) throws IOException {
			String ExpectedSignInSuccessMsg = data.Getdata("Expected Signed In Message", row).trim();
			verifyTextEqual(readonly_SignInSuccessMsg(), ExpectedSignInSuccessMsg, "Validate Nurse User Sign In Message");

		}
		
		public void ValidateMyWorkOrdersPageMsg(int row) throws IOException {
			String ExpectedNurseHomePageMsg = data.Getdata("Expected Nurse Home Page", row).trim();
			verifyCorrectPageHeading(readonly_MyWorkOrders(), ExpectedNurseHomePageMsg);

		}
		
		public void LaunchURL() throws IOException {

			driver.get(LaunchBrowser.TestSettingsObjects.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

	// Actual Functional Method 

		public void login(int row) throws IOException, InterruptedException, BiffException {		
			EnterUserEmail();
			EnterUserPassword();
			ClickLogInButton();
			ValidateSignInMsg(row);
			ValidateMyWorkOrdersPageMsg(row);
		}

		// Negative Login Test -- User Name
		public void NegativeloginUserName() throws IOException {
			LaunchURL();
			enterText(txtUserEmail(), "Nurse User Email", "Testing@Test.com");
			txtUserPassword().sendKeys(LaunchBrowser.TestSettingsObjects.getProperty("NurseUserPassword"));
			clickOnButton(btnLog_In(), "Log In");
		}

		// Negative Login Test -- Password
		public void NegativeloginPassword() throws IOException {
			LaunchURL();
			enterText(txtUserEmail(), "Nurse User Email", LaunchBrowser.TestSettingsObjects.getProperty("NurseUserEmail"));
			txtUserPassword().sendKeys("Password");
			clickOnButton(btnLog_In(), "Log In");
		}

}