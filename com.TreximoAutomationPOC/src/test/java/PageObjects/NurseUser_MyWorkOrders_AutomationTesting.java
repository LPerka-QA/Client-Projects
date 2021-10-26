package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;

public class NurseUser_MyWorkOrders_AutomationTesting extends BasePages {

	public WebElement lnk_MyWrkOrdrsLtstRcrd(int row, String WorkOrder) {
		return driver.findElement(By.xpath("//a[@data-work-order-id='"+WorkOrder+"' and @class='list-group-item work-order-without-record']"));
		
	}

	public WebElement rdbtn_Radio1(int row, String Radio1) {
		return driver.findElement(By.xpath("//input[@value='"+Radio1+"' and @type='radio']"));
	}

	@FindBy(xpath = "//div[@class='col-xs-12']//h1")
	public WebElement readonly_AutoTestingForm1Header;

	@FindBy(xpath = "//form[@id='15']//p[contains(text(),'Patient ID: ')]//strong")
	public WebElement readonly_PatientID;

	@FindBy(xpath = "//form[@id='15']//..//strong[3]")
	public WebElement readonly_WorkOrderID;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='text']")
	public WebElement txt_TextField1;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='number']")
	public WebElement txt_NumberField1;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='file']")
	public WebElement txt_FileField1;

	@FindBy(xpath = "//input[@class='form-control' and @type='date']")
	public WebElement txt_DateField1;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='time']")
	public WebElement txt_TimeField1;

	@FindBy(xpath = "//select[@class='form-control has-feedback']")
	public WebElement dpd_Dropdown1;

	@FindBy(xpath = "//button[text()='Back' and @type='button']")
	public WebElement btn_Back;

	@FindBy(xpath = "//input[@id='password' and @type='password']")
	public WebElement txt_ReEnterPassword;

	@FindBy(xpath = "//button[@class='btn btn-primary pull-right' and text()='Send to Home Office']")
	public WebElement btn_SendtoHomeOffice;

	@FindBy(xpath = "//button[@class='btn btn-primary pull-right' and text()='Submit for Review']")
	public WebElement btn_SubmitforReview;

	@FindBy(xpath = "//button[@class='btn btn-primary' and text()='Edit']")
	public WebElement btn_Edit;

	@FindBy(xpath = "//div[@class='col-xs-12']//h1")
	public WebElement readonly_RecordSubmitted;

	@FindBy(xpath = "//div[@class='col-xs-12']//p")
	public WebElement readonly_RecordSubmittedMsg;

	@FindBy(xpath = "//button[text()='Continue' and @type='submit']")
	public WebElement btn_Continue;

// Each Object Performance Method
// My Work Orders Info

	public void ClickMyWrkOrdrLtstRcrd(int row, String WorkOrder) throws IOException, BiffException {
		try {
			scrollElementIntoView(lnk_MyWrkOrdrsLtstRcrd(row, WorkOrder));
			clickOnLink(lnk_MyWrkOrdrsLtstRcrd(row, WorkOrder), "Latest Work Order");
			WriteTestReportinExcel("Click My Work Order Latest Record link", "My Work Order Latest Record link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click My Work Order Latest Record link", "My Work Order Latest Record link should be clicked successfully", "FAIL", row);
		}
		
	}

	public void Enter_TextField1(int row, String TextField1) throws IOException, BiffException {
		try {
			enterText(txt_TextField1, "TEXT FIELD 1", TextField1);
			WriteTestReportinExcel("Enter TEXT FIELD 1", "TEXT FIELD 1 should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter TEXT FIELD 1", "TEXT FIELD 1 should be entered successfully", "FAIL", row);
		}
		
	}

	public void Enter_NumberField1(int row, String NumberField1) throws IOException, BiffException {
		try {
			enterText(txt_NumberField1, "NUMBER FIELD 1", NumberField1);
			WriteTestReportinExcel("Enter NUMBER FIELD 1", "NUMBER FIELD 1 should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter NUMBER FIELD 1", "NUMBER FIELD 1 should be entered successfully", "FAIL", row);
		}
		
	}

	public void UploadFile(int row) throws IOException, BiffException {
		try {
			// click on ‘Choose file’ to upload the desired file
			enterText(txt_FileField1, "File Field 1",
					System.getProperty("user.dir") + "\\Resources\\TestData\\File Upload Testing 01.png");
			WriteTestReportinExcel("Select UPLOAD FILE", "Selected UPLOAD FILE should be uploaded successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select UPLOAD FILE", "Selected UPLOAD FILE should be uploaded successfully", "FAIL", row);
		}
		
	}
	
	public void Enter_DateField1(int row, String DateField1) throws IOException, InterruptedException, BiffException {
		try {
			enterTextWithoutClearing(txt_DateField1, "DATE FIELD 1", DateField1);
			WriteTestReportinExcel("Enter DATE FIELD 1", "DATE FIELD 1 should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter DATE FIELD 1", "DATE FIELD 1 should be entered successfully", "FAIL", row);
		}
		
	}

	public void Enter_TimeField1(int row, String TimeField1) throws IOException, BiffException {
		try {
			scrollElementIntoView(txt_TimeField1);
			enterTextWithoutClearing(txt_TimeField1, "TIME FIELD 1", TimeField1);
			WriteTestReportinExcelWithScreenShot("Enter TIME FIELD 1", "TIME FIELD 1 should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter TIME FIELD 1", "TIME FIELD 1 should be entered successfully", "FAIL", row);
		}
		
	}

	public void SelectDropdown1(int row, String Dropdown1Value) throws IOException, InterruptedException, BiffException {
		try {
			selectByText(dpd_Dropdown1, "DROP DOWN 1", Dropdown1Value);
			WriteTestReportinExcel("Select DROP DOWN 1 value", "DROP DOWN 1 value should be selected successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select DROP DOWN 1 value", "DROP DOWN 1 value should be selected successfully", "FAIL", row);
		}
		
	}

	public void ClickRadio1(int row, String Radio1) throws IOException, BiffException {
		try {
			clickOnButton(rdbtn_Radio1(row, Radio1), "Radio1 Yes");
			WriteTestReportinExcel("Click Radio1 - Yes", "Radio1 - Yes should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Radio1 - Yes", "Radio1 - Yes button should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ClickContinue(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_Continue, "Continue");
			WriteTestReportinExcelWithScreenShot("Click Continue button", "Continue button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Continue button", "Continue button should be clicked successfully", "FAIL", row);
		}
		
	}

	public void EnterPassword(int row) throws IOException, BiffException {
		try {
			enterText(txt_ReEnterPassword, "Re Enter Password",	GoClinicTest.TestSettingsObjects.getProperty("NurseUserPassword"));
			WriteTestReportinExcel("Enter Password", "Password should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Password", "Password should be entered successfully", "FAIL", row);
		}
		
	}

	public void ClickSendtoHomeOffice(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_SendtoHomeOffice, "Send to Home Office");
			WriteTestReportinExcel("Click Send to Home Office button", "Send to Home Office button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Send to Home Office button", "Send to Home Office button should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ClickSubmitforReview(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_SubmitforReview, "Submit for Review");
			WriteTestReportinExcel("Click Submit for Review button", "Submit for Review button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Submit for Review button", "Submit for Review button should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ValidateAutoTestingForm1Header(int row, String ExpectedAutoTestingForm1Header) throws IOException, BiffException {
		try {
			verifyCorrectPageHeading(readonly_AutoTestingForm1Header, ExpectedAutoTestingForm1Header);
			WriteTestReportinExcelWithScreenShot("Verify AutoTestingForm1 header", "AutoTestingForm1 header should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify AutoTestingForm1 header", "AutoTestingForm1 header should be verified successfully", "FAIL", row);
		}
		

	}

	public void ValidatePatientID(int row, String ExpectedPatientID) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_PatientID, ExpectedPatientID, "Validate Patient ID");
			WriteTestReportinExcelWithScreenShot("Verify Patient ID value", "Patient ID value should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Patient ID value", "Patient ID value should be verified successfully", "FAIL", row);
		}
		

	}

	public void ValidateRecordSubmitted(int row, String ExpectedRecordSubmitted) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_RecordSubmitted, ExpectedRecordSubmitted, "Record Submitted Message");
			WriteTestReportinExcelWithScreenShot("Verify Notify message", "Notify message should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Notify message", "Notify message should be verified successfully", "FAIL", row);
		}
		

	}

	public String GetRecordSubmittedTxt(int row) throws IOException, BiffException {
		String RecordSubmittedTxt = null;
		try {
			RecordSubmittedTxt = readonly_RecordSubmittedMsg.getText();
			WriteTestReportinExcel("Get Record Submitted message", "Record Submitted message should be retrieved successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Get Record Submitted message", "Record Submitted message should be retrieved successfully", "FAIL", row);
		}
		
		return RecordSubmittedTxt;

	}

}