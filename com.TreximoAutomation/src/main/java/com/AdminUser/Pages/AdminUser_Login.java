package com.AdminUser.Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.AdminUser.PagesData.AdminUser_LoginPageData;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

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

			return driver.findElement(By.xpath("//input[@value='Log in' and @type='submit']"));
		}
		
		public static WebElement readonly_SignInSuccessMsg() {
			return driver.findElement(By.xpath("//div[@class='notice']//p"));

		}
		
		public static WebElement readonly_AdminHomePageMsg() {
			return driver.findElement(By.xpath("//div[@class='col-xs-12']//h1"));
		}

	// Each Object  Method	

		public void EnterUserEmail() throws IOException {

			enterText(txtUserEmail(), "Admin User Email", LaunchBrowser.TestSettingsObjects.getProperty("AdminUserEmail"));
		}

		public void EnterUserPassword() {

			txtUserPassword().sendKeys(LaunchBrowser.TestSettingsObjects.getProperty("AdminUserPassword"));
		}

		public void ClickLogInButton() throws IOException {

			clickOnButton(btnLog_In(), "Log In");
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
		}				
				
	// Actual Functional Method 

		public void login(int row) throws IOException, InterruptedException {
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