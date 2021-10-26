package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import com.Utils.LaunchBrowser;

import Commons.GoClinicTest;
import jxl.read.biff.BiffException;

public class NurseUser_MyWorkOrders_VitalSigns extends BasePages {

	public WebElement lnk_MyWrkOrdrsLtstRcrd(int row, String WorkOrder) {
		System.out.println(WorkOrder);
		String WorkOrderNumber = WorkOrder.substring(11);
		System.out.println(WorkOrderNumber);
		return driver.findElement(By.xpath("//a[@data-work-order-id='"+WorkOrderNumber+"' and @class='list-group-item work-order-without-record']"));
	}

	@FindBy(xpath = "//div[@class='col-xs-12']//h1")
	public WebElement readonly_VitalSignsHeader;

	@FindBy(xpath = "//form[@id='1']//p[contains(text(),'Patient ID: ')]//strong")
	public WebElement readonly_PatientID;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='text']")
	public WebElement txt_BloodPressure_Systolic_mm_Hg;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='number']")
	public WebElement txt_BloodPressure_Diastolic_mm_Hg;

	@FindBy(xpath = "//input[@class='form-control has-feedback' and @type='file']")
	public WebElement txt_HeartRate;

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
			clickMainMenu(lnk_MyWrkOrdrsLtstRcrd(row, WorkOrder));
			WriteTestReportinExcel("Click My Work orders latest record link", "My Work orders latest record link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click My Work orders latest record link", "My Work orders latest record link should be clicked successfully", "FAIL", row);
		}
		
	}

	public void Enter_BloodPressureSystolic_mm_Hg(int row, String BloodPressureSystolic_mm_Hg) throws IOException, BiffException {
		try {
			enterText(txt_BloodPressure_Systolic_mm_Hg, "BLOOD PRESSURE (SYSTOLIC MM HG)", BloodPressureSystolic_mm_Hg);
			WriteTestReportinExcel("Enter BLOOD PRESSURE (SYSTOLIC MM HG)", "BLOOD PRESSURE (SYSTOLIC MM HG) should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter BLOOD PRESSURE (SYSTOLIC MM HG)", "BLOOD PRESSURE (SYSTOLIC MM HG) should be entered successfully", "FAIL", row);
		}
		
	}

	public void Enter_BloodPressureDiastolic_mm_Hg(int row, String BloodPressureDiastolic_mm_Hg) throws IOException, BiffException {
		try {
			enterText(txt_BloodPressure_Diastolic_mm_Hg, "BLOOD PRESSURE (DIASTOLIC MM HG)", BloodPressureDiastolic_mm_Hg);
			WriteTestReportinExcel("Enter BLOOD PRESSURE (DIASTOLIC MM HG)", "BLOOD PRESSURE (DIASTOLIC MM HG) should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter BLOOD PRESSURE (DIASTOLIC MM HG)", "BLOOD PRESSURE (DIASTOLIC MM HG) should be entered successfully", "FAIL", row);
		}
		
	}

	public void Enter_HeartRate(int row, String HeartRate) throws IOException, InterruptedException, BiffException {
		try {
			enterText(txt_HeartRate, "HEART RATE", HeartRate);
			WriteTestReportinExcel("Enter HEART RATE", "HEART RATE should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter HEART RATE", "HEART RATE should be entered successfully", "FAIL", row);
		}
		
	}

	public void ClickContinue(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_Continue, "Continue");
			WriteTestReportinExcel("Click Continue button", "Continue button should be clicked successfully", "PASS", row);
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

	public void ValidateVitalSignsHeader(int row, String ExpectedAutoTestingForm1Header) throws IOException, BiffException {
		try {
			verifyCorrectPageHeading(readonly_VitalSignsHeader, ExpectedAutoTestingForm1Header);
			WriteTestReportinExcelWithScreenShot("Verify Vital Signs header", "Vital Signs header should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Vital Signs header", "Vital Signs header should be verified successfully", "FAIL", row);
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
			WriteTestReportinExcelWithScreenShot("Verify Record Submitted message", "Record Submitted message should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Record Submitted message", "Record Submitted message should be verified successfully", "FAIL", row);
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