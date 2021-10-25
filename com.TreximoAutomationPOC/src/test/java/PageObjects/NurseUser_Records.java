package PageObjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.Utils.BasePages;
import jxl.read.biff.BiffException;

public class NurseUser_Records extends BasePages {

	// Objects from Application
	// Records

	@FindBy(xpath = "//li//a[@href='/records' and text()='Records']")
	public WebElement lnk_Records;

	@FindBy(xpath = "//div[@id='records_index']//h1[contains(text(),'Records')]")
	public WebElement readonly_RecordsHeader;

	public WebElement readonly_GetLatestRecord_CreatedDate(String WorkOrder, String Protocol, String PatientId,
			String Visit) {
		String WorkOrderNumber = WorkOrder.substring(11);
		return driver.findElement(By.xpath("//table[@class='table table-condensed table-hover']//td[text()='"
				+ WorkOrderNumber + "']//..//td[text()='" + Protocol + "']//..//td[text()='" + PatientId
				+ "']//..//td[text()='" + Visit + "']//..//td[text()='REVIEW']//..//td[6]"));

	}

	public void ClickRecords(int row) throws IOException, BiffException {
		try {
			clickOnLink(lnk_Records, "Records ");
			WriteTestReportinExcel("Click Records link", "Create Records link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Records link", "Create Records link should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ValidateRecordsHeader(int row, String ExpectedRecordsHeader) throws IOException, BiffException {
		try {
			verifyCorrectPageHeading(readonly_RecordsHeader, ExpectedRecordsHeader);
			WriteTestReportinExcelWithScreenShot("Verify Records Header", "Records Header should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Records Header", "Records Header should be verified successfully", "FAIL", row);
		}
		

	}

	public void ValidateLatestRecord(int row, String ExpectedLatestRecord, String WorkOrder, String Protocol,
			String PatientId, String Visit) throws IOException, BiffException {
		try {
			verifyTextEqual(readonly_GetLatestRecord_CreatedDate(WorkOrder, Protocol, PatientId, Visit),
					ExpectedLatestRecord, "Validate latest Record");
			WriteTestReportinExcelWithScreenShot("Verify Latest Record", "Latest Record should be verified successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Verify Latest Record", "Latest Record should be verified successfully", "FAIL", row);
		}
		

	}

	public String GetLtstRcrdCrtdDate(int row ,String WorkOrder, String Protocol, String PatientId, String Visit ) throws IOException, BiffException {
		String LatestRecordCreateDate = null;
		try {
			LatestRecordCreateDate = readonly_GetLatestRecord_CreatedDate(WorkOrder, Protocol, PatientId, Visit).getText();
//			data.setData("Latest Record Created Date", row, LatestRecordCreateDate);
			WriteTestReportinExcel("Get Latest Record Create Date", "Latest Record Create Date should be retrieved successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Get Latest Record Create Date", "Latest Record Create Date should be retrieved successfully", "FAIL", row);
		}
		
		return LatestRecordCreateDate;

	}


}