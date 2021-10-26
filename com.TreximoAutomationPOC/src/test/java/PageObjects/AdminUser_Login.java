package PageObjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import Steps.GoClinical_Admin_NewWorkOrder_PageSteps;
import jxl.read.biff.BiffException;

public class AdminUser_Login extends BasePages {
	
	GoClinical_Admin_NewWorkOrder_PageSteps goClinicalAdminPageNWOSteps;

	// Other way of Capturing the objects ID
	@FindBy(id = "user_email")
	public WebElement txtUserEmail;

	@FindBy(id = "user_password")
	public WebElement txtUserPassword;

	@FindBy(xpath = "//input[@value='Log in' and @type='submit']")
	public WebElement btnLog_In;

	@FindBy(xpath = "//div[@class='notice']")
	public WebElement readonly_SignInSuccessMsg;

	@FindBy(xpath = "//div[@class='col-xs-12']//h1")
	public WebElement readonly_AdminHomePageMsg;

	// Each Object Method

	public void EnterUserEmail(int row) throws IOException, BiffException {
		try {
				enterText(txtUserEmail, "Admin User Email", GoClinicTest.TestSettingsObjects.getProperty("AdminUserEmail"));
				
				WriteTestReportinExcel("Enter Admin User Email", "Admin User Email should be entered successfully", "PASS", row);
		} catch (Exception e) {
	// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Admin User Email", "Admin User Email should be entered successfully", "FAIL", row);
		}
		
	}

	public void EnterUserPassword(int row) throws IOException, BiffException {
		try {
			enterText(txtUserPassword, "Admin User Password",
					GoClinicTest.TestSettingsObjects.getProperty("AdminUserPassword"));
			
			WriteTestReportinExcelWithScreenShot("Enter Admin User Password", "Admin User Password should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Admin User Password", "Admin User Passoword should be entered successfully", "FAIL", row);
		}
		

	}

	public void ClickLogInButton(int row) throws IOException, BiffException {
		try {
			clickOnButton(btnLog_In, "Log In");
			
			WriteTestReportinExcel("Click LOG IN button", "LOG IN button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click LOG IN button", "LOG IN button should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ValidateSignInMsg(int row, String ExpectedSignInSuccessMsg) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_SignInSuccessMsg, ExpectedSignInSuccessMsg, "Validate Admin User Sign In Message");
			
			WriteTestReportinExcelWithScreenShot("Verify SIGN IN message", "SIGN IN message should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify SIGN IN message", "SIGN IN message should be verified successfully", "FAIL", row);
		}
		

	}

	public void ValidateAdminHomePageMsg(int row, String ExpectedAdminHomePageMsg) throws IOException, BiffException {
		try {			
			verifyCorrectPageHeading(readonly_AdminHomePageMsg, ExpectedAdminHomePageMsg);
			
			WriteTestReportinExcel("Verify Admin Home Page header", "Admin Home Page Header should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Admin Home Page header", "Admin Home Page Header should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void LaunchURL(int row) throws IOException, BiffException {		
		try {
			
			driver.get(GoClinicTest.TestSettingsObjects.getProperty("URL"));
			
			WriteTestReportinExcelWithScreenShot("Enter Application URL", "Application URL should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Application URL", "Application URL should be entered successfully", "FAIL", row);
		}
	}
	

}