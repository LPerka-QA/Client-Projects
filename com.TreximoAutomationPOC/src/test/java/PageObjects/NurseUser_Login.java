package PageObjects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;

public class NurseUser_Login extends BasePages {

	// Other way of Capturing the objects ID
	@FindBy(id = "user_email")
	public WebElement txtUserEmail;

	@FindBy(id = "user_password")
	public WebElement txtUserPassword;

	@FindBy(xpath = "//input[@value='Log in' and @type='submit']")
	public WebElement btnLog_In;

	@FindBy(xpath = "//div[@class='notice']")
	public WebElement readonly_SignInSuccessMsg;

	@FindBy(xpath = "//div[@class='work_orders']//h2")
	public WebElement readonly_MyWorkOrders;

	// Each Object Method

	public void EnterUserEmail(int row) throws IOException, BiffException {
		String EmailUser = GoClinicTest.TestSettingsObjects.getProperty("NurseUserEmail");
		try {
			enterText(txtUserEmail, "Nurse User Email", EmailUser);
			WriteTestReportinExcel("Enter Nurse User Email: " + EmailUser, "Nurse User Email should be entered successfully", "PASS", row);
		} catch (Exception e) {
	// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Nurse User Email: " + EmailUser, "Nurse User Email should be entered successfully", "FAIL", row);
		}		
	}

	public void EnterUserPassword(int row) throws IOException, BiffException {
		try {
			enterText(txtUserPassword, "Nurse User Password", GoClinicTest.TestSettingsObjects.getProperty("NurseUserPassword"));
			
			WriteTestReportinExcelWithScreenShot("Enter Nurse User Password", "Nurse User Password should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Nurse User Password", "Nurse User Passoword should be entered successfully", "FAIL", row);
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
			
			WriteTestReportinExcelWithScreenShot("Verify SIGN IN message: " + ExpectedSignInSuccessMsg, "SIGN IN message should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify SIGN IN message: " + ExpectedSignInSuccessMsg, "SIGN IN message should be verified successfully", "FAIL", row);
		}
		

	}

	public void ValidateMyWorkOrdersPageMsg(int row, String ExpectedNurseHomePageMsg) throws IOException, BiffException {
		try {
			verifyCorrectPageHeading(readonly_MyWorkOrders, ExpectedNurseHomePageMsg);
			WriteTestReportinExcel("Verify My Work Orders Page Header: " + ExpectedNurseHomePageMsg, "My Work Orders Page Header should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify My Work Orders Page Header: " + ExpectedNurseHomePageMsg, "My Work Orders Page Header should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void LaunchURL(int row) throws IOException, BiffException {		
		String URL = GoClinicTest.TestSettingsObjects.getProperty("URL");
		try {			
			driver.get(URL);			
			WriteTestReportinExcel("Enter Application URL: " + URL, "Application URL should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Application URL: " + URL, "Application URL should be entered successfully", "FAIL", row);
		}
	}
	

}