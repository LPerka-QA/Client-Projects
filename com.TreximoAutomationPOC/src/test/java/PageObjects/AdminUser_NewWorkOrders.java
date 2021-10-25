package PageObjects;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import jxl.read.biff.BiffException;

public class AdminUser_NewWorkOrders extends BasePages {
	
	String PatientIDValue;

	@FindBy(id = "sponsor_id")
	public WebElement dpd_Sponsor;

	@FindBy(xpath = "//select[@id='work_order_protocol_id']")
	public WebElement dpd_Protocol;

	@FindBy(xpath = "//input[@id='work_order_patient_study_id' and @type='text']")
	public WebElement txt_PatientID;

	@FindBy(xpath = "//select[@id='work_order_visit_id']")
	public WebElement dpd_Visit;

	@FindBy(xpath = "//input[@id='work_order[rvsp_email]' and @type='text']")
	public WebElement txt_SrvcePrvdrEmail_RVSP;

	@FindBy(xpath = "//select[@id='work_order_site_user_id']")
	public WebElement dpd_SndRcrdTo_SiteCntct;

	@FindBy(xpath = "//div//input[@value='Create Work order' and @type='submit']")
	public WebElement btn_CreateWorkOrder;

	@FindBy(xpath = "//a[@href='/work_orders' and text()='Back']")
	public WebElement btn_Back;

	@FindBy(xpath = "//div[@class='notice']//p")
	public WebElement readonly_WrkOrdrCrteSuccessMsg;

	@FindBy(xpath = "//div[@class='col-xs-12' and @id='work_order_show']//h1")
	public WebElement readonly_WorkOrderNumber;

	@FindBy(xpath = "//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Active Status']//..//dd")
	public WebElement readonly_ActiveStatus;

	@FindBy(xpath = "//dt//..//span[@class='label label-primary']")
	public WebElement readonly_Status;

	@FindBy(xpath = "//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Patient']//..//dd[3]")
	public WebElement readonly_PatientID;

	@FindBy(xpath = "//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Protocol']//..//dd[4]")
	public WebElement readonly_Protocol;

	@FindBy(xpath = "//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Visit']//..//dd[5]")
	public WebElement readonly_Visit;

	@FindBy(xpath = "//div[@class='col-md-6']//dl[@class='dl-horizontal']//dt[text()='Service Provider']//..//em")
	public WebElement readonly_ServiceProvider;
	
	@FindBy(xpath = "//a[contains(@href,'/records/new') and text()='(Paper original?)']")
	public WebElement PaperOriginalLnk;
	
	@FindBy(xpath = "//input[@value='Send Work Order to Lokendernath-rvsp Perka' and @type='submit']")
	public WebElement SendWorktoUserBtn;
	
	@FindBy(xpath = "//a[contains(@href,'/work_orders/') and text()='Edit']")
	public WebElement EditLnk;
	
	@FindBy(xpath = "//a[@href='/work_orders' and text()='Back to Work Orders']")
	public WebElement BacktoWorkOrdersLnk;
	
	@FindBy(xpath = "//div[@id='notify']//p[@class='bg-success']")
	public WebElement readonly_NotifyMsg;
	

// Each Object Performance Method
// My Work Orders Info


	public void SelectSponsor(int row, String SponsorValue) throws IOException, InterruptedException, BiffException {
		try {
			selectByText(dpd_Sponsor, "Sponsor" , SponsorValue);
			WriteTestReportinExcel("Select Sponsor dropdown value", "Sponsor dropdown value should be selected successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select Sponsor dropdown value", "Sponsor dropdown value should be selected successfully", "FAIL", row);
		}
				
	}	

	public void SelectProtocol(int row, String ProtocolValue) throws IOException, InterruptedException, BiffException  {
		try {
			selectByText(dpd_Protocol, "Protocol", ProtocolValue);
			WriteTestReportinExcel("Select Protocol dropdown value", "Protocol dropdown value should be selected successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select Protocol dropdown value", "Protocol dropdown value should be selected successfully", "FAIL", row);
		}
		
	}	

	public void EnterPatientID(int row, String PatientIDValue) throws IOException, BiffException {
		try {
			enterText(txt_PatientID, "Patient ID", PatientIDValue);
			WriteTestReportinExcel("Enter Patient ID", "Patient ID should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Patient ID", "Patient ID should be entered successfully", "FAIL", row);
		}
		
	}

	public void SelectVisit(int row, String VisitValue) throws IOException, BiffException {
		try {
			selectByText(dpd_Visit, "Visit", VisitValue);
			WriteTestReportinExcel("Select Visit dropdown value", "Visit dropdown value should be selected successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select Visit dropdown value", "Visit dropdown value should be selected successfully", "FAIL", row);
		}
		
	}	
	
	public void EnterSrvcePrvdrEmail_RVSP(int row, String ServiceProviderEmail_RVSPValue) throws IOException, BiffException {
		try {
			enterText(txt_SrvcePrvdrEmail_RVSP, "Service Provider Email (RVSP)", ServiceProviderEmail_RVSPValue);
			WriteTestReportinExcel("Enter Service Provider Email (RVSP)", "Service Provider Email (RVSP) should be entered successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Enter Service Provider Email (RVSP)", "Service Provider Email (RVSP) should be entered successfully", "FAIL", row);
		}
		
	}

	public void SelectSndRcrdTo_SiteCntct(int row, String SendRecordtoSiteContactValue) throws IOException, BiffException {
		try {
			selectByText(dpd_SndRcrdTo_SiteCntct, "Send Record To (Site Contact)", SendRecordtoSiteContactValue);
			WriteTestReportinExcel("Select Send Record To (Site Contact) dropdown value", "Send Record To (Site Contact) dropdown value should be selected successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Select Send Record To (Site Contact) dropdown value", "Send Record To (Site Contact) dropdown value should be selected successfully", "FAIL", row);
		}
		
	}
	
	// Each Object Performance Method
	// Submit Section
		
	public void ClickCreateWorkOrder(int row) throws IOException, BiffException {
		try {
			scrollElementIntoView(btn_CreateWorkOrder);
			clickOnButton(btn_CreateWorkOrder, "Create Work order");
			WriteTestReportinExcelWithScreenShot("Click Create Work order button", "Create Work order button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Create Work order button", "Create Work order button should be clicked successfully", "FAIL", row);
		}
		
		
	}
	
	public void ClickSendWorkOrdertoUser(int row) throws IOException, BiffException {
		try {
			clickOnButton(SendWorktoUserBtn, "Send Work Order to User");
			WriteTestReportinExcelWithScreenShot("Click Send Work Order to User button", "Create Send Work Order to User button should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Send Work Order to User button", "Create Send Work Order to User button should be clicked successfully", "FAIL", row);
		}
		
		
	}
	
	// Each Object Performance Method
		// Verification Section

	public void ValidateCrtWrkOrdrSuccessMsg(int row, String ExpectedCrtWrkOrdrSuccessMsg) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_WrkOrdrCrteSuccessMsg, ExpectedCrtWrkOrdrSuccessMsg, "New Work Order with PatientID " + PatientIDValue);
			WriteTestReportinExcelWithScreenShot("Verify Created Work Order message", "Created Work Order message should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Created Work Order message", "Created Work Order message should be verified successfully", "FAIL", row);
		}
		
	}
	
	public String GetWrkOrdrNmbr(int row) throws IOException, BiffException {
		String WorkOrderRecordNumber = null;
		try {
			WorkOrderRecordNumber = readonly_WorkOrderNumber.getText();		
			//data.setData("Work Order Number", row, WorkOrderRecordNumber);
			WriteTestReportinExcel("Get Created Work Order Number", "Created Work Order Number should be retrieved successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Get Created Work Order Number", "Created Work Order Number should be retrieved successfully", "FAIL", row);
		}
		
		
		return WorkOrderRecordNumber;
		
	}
	
	public String GetActiveStatus(int row) throws IOException, BiffException {
		String ActiveStatus = null;
		try {
			ActiveStatus = readonly_ActiveStatus.getText();		
			//data.setData("Active Status", row, ActiveStatus);
			WriteTestReportinExcel("Get Active Status", "Active Status should be retrieved successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Get Active Status", "Active Status should be retrieved successfully", "FAIL", row);
		}
		
		return ActiveStatus;
		
	}
	
	public String GetStatus(int row) throws IOException, BiffException {
		String Status = null;
		try {
			 Status = readonly_Status.getText();		
			//data.setData("Status", row, Status);
			WriteTestReportinExcel("Get Status", "Status should be retrieved successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Get Status", "Status should be retrieved successfully", "FAIL", row);
		}		
		return Status;
	}
	
	public void ValidatePatientID(int row, String PatientId) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_PatientID, PatientId, "PatientID");
			WriteTestReportinExcelWithScreenShot("Verify Patient ID value", "PatientID value should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify PatientID value", "PatientID value should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void ValidateProtocol(int row, String ExpectedProtocol) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_Protocol, ExpectedProtocol, "Protocol");
			WriteTestReportinExcelWithScreenShot("Verify Protocol value", "Protocol value should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Protocol value", "Protocol value should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void ValidateVisit(int row, String ExpectedVisit) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_Visit, ExpectedVisit, "Visit");
			WriteTestReportinExcelWithScreenShot("Verify Visit value", "Visit value should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Visit value", "Visit value should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void ValidateSrvcePrvdr(int row, String ExpectedSrvcePrvdrEmail) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_ServiceProvider, ExpectedSrvcePrvdrEmail, "Service Provider Email RVSP");
			WriteTestReportinExcelWithScreenShot("Verify Service Provider Email RVSP value",
					"Service Provider Email RVSP value should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Service Provider Email RVSP value", "Service Provider Email RVSP value should be verified successfully", "FAIL", row);
		}
		
	}
	
	public void ValidateNotifyMsg(int row, String ExpectedSrvcePrvdrEmail) throws IOException, BiffException {
		try {
			if(readonly_NotifyMsg.getText().contains(ExpectedSrvcePrvdrEmail)== true)
			{
			String NotifyMsg = readonly_NotifyMsg.getText();
			WriteTestReportinExcelWithScreenShot("Verify Notify message", "Notify message should be verified successfully", "PASS", row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Notify message", "Notify message should be verified successfully", "FAIL", row);
		}
		
		
		
	}

}