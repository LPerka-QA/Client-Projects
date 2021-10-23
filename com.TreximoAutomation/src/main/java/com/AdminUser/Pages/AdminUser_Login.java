package com.AdminUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import jxl.read.biff.BiffException;

public class AdminUser_Login extends BasePages {
	
	AdminUser_LoginPageData data = new AdminUser_LoginPageData();
	
	// Objects from Application

		public WebElement txtUserEmail() {

			return driver.findElement(By.id("user_email"));
		}

		public WebElement txtUserPassword() {

			return driver.findElement(By.id("user_password"));
		}

		public WebElement btnLog_In() {

			return driver.findElement(By.xpath("//input[@value='Login' and @type='submit']"));
		}
		
		public static WebElement readonly_SignInSuccessMsg() {
			return driver.findElement(By.xpath("//div[@class='notice']//p"));

		}
		
		public static WebElement readonly_AdminHomePageMsg() {
			return driver.findElement(By.xpath("//div[@class='col-xs-12']//h1"));
		}

	// Each Object  Method	

		public void EnterUserEmail() throws IOException, BiffException {			
			try {
				enterText(txtUserEmail(), "Admin User Email", LaunchBrowser.TestSettingsObjects.getProperty("AdminUserEmail"));
				data.writeData("2", "1", "Enter the Email", "Email should be entered successfully", "Pass");
			} catch (Exception e) {
				data.writeData("2", "1", "Enter the Email", "Email should be entered successfully", "Fail");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void EnterUserPassword() throws BiffException, IOException {			
			try {
				txtUserPassword().sendKeys(LaunchBrowser.TestSettingsObjects.getProperty("AdminUserPassword"));
				data.writeData("3", "2", "Enter the Password", "Password should be entered successfully", "Pass");
			} catch (Exception e) {
				data.writeData("3", "2", "Enter the Password", "Password should be entered successfully", "Fail");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void ClickLogInButton() throws IOException, BiffException {
			try {
				clickOnButton(btnLog_In(), "Log In");
				data.writeData("4", "3", "Click on LOG IN button", "LOG IN should be clicked successfully", "Pass");
			} catch (Exception e) {
				data.writeData("4", "3", "Click on LOG IN button", "LOG IN should be clicked successfully", "Fail");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void ValidateSignInMsg(int row) throws IOException {
			String ExpectedSignInSuccessMsg = data.Getdata("Expected Signed In Message", row).trim();
			verifyTextEqual(readonly_SignInSuccessMsg(), ExpectedSignInSuccessMsg, "Validate Sign In Message");

		}
		
		public void ValidateAdminHomePageMsg(int row) throws IOException {
			String ExpectedAdminHomePageMsg = data.Getdata("Expected Admin Home Page", row).trim();
			verifyCorrectPageHeading(readonly_AdminHomePageMsg(), ExpectedAdminHomePageMsg);

		}
		
		public void LaunchURL() throws IOException {

			driver.get(LaunchBrowser.TestSettingsObjects.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//CaptureScreenShot(driver,"ScreenshotName");
			
			//testresultdata.put("2", new Object[] {1d, "navigate to site and login", "site opens and login success","Pass"});
		}				
				
	// Actual Functional Method 

		public void login(int row) throws IOException, InterruptedException, BiffException {
			EnterUserEmail();
			EnterUserPassword();
			ClickLogInButton();
			ValidateSignInMsg(row);
			ValidateAdminHomePageMsg(row);
		}

		// Negative Login Test -- User Name
		public void NegativeloginUserName() throws IOException {
			LaunchURL();
			enterText(txtUserEmail(), "Admin User Email", "Testing@Test.com");
			txtUserPassword().sendKeys(LaunchBrowser.TestSettingsObjects.getProperty("AdminUserPassword"));
			clickOnButton(btnLog_In(), "Log In");

		}

		// Negative Login Test -- Password
		public void NegativeloginPassword() throws IOException {
			LaunchURL();
			enterText(txtUserEmail(), "Admin User Email", LaunchBrowser.TestSettingsObjects.getProperty("AdminUserEmail"));
			txtUserPassword().sendKeys("Password");
			clickOnButton(btnLog_In(), "Log In");

		}

}