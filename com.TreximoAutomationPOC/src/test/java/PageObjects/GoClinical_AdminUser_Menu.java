package PageObjects;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Utils.BasePages;

import jxl.read.biff.BiffException;

public class GoClinical_AdminUser_Menu extends BasePages {

// Objects from Application

	// Other way of Capturing the objects ID
	@FindBy(xpath = "//li[@class='dropdown']//a[@class='dropdown-toggle' and text()='Work Orders ']")
	public WebElement btn_WorkOrders;

	@FindBy(xpath = "//li//a[@href='/records' and text()='Records']")
	public WebElement btn_Records;

	@FindBy(xpath = "//a[@class='dropdown-toggle' and contains(text(),'Welcome Lokendernath Perka ')]")
	public WebElement btn_WelcomeUser;

	@FindBy(xpath = "//ul[@class='dropdown-menu']//li//a[@href='/work_orders/new' and text()='New...']")
	public WebElement btn_WorkOrders_New;

	@FindBy(xpath = "//ul[@class='dropdown-menu']//a[@href='/users/sign_out' and text()='Logout']")
	public WebElement btn_Logout;

	public void ClickWorkOrders(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_WorkOrders, "Work Orders ");
			WriteTestReportinExcel("Click Work Orders link", "Work Orders link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Work Orders link", "Work Orders link should be clicked successfully", "FAIL", row);
		}
		

	}

	public void ClickRecords(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_Records, "Records");
			WriteTestReportinExcel("Click Records link", "Records link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Records link", "Records link should be clicked successfully", "FAIL", row);
		}
		
	}

	public void ClickWelcomUser(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_WelcomeUser, "Welcome ");
			WriteTestReportinExcel("Click Welcome User link", "Welcome User link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Welcome User link", "Welcome User link should be clicked successfully", "FAIL", row);
		}
		

	}

	public void ClickWorkOrdersNew(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_WorkOrders_New, "Work Orders - New");
			WriteTestReportinExcel("Click Work Orders - New link", "Work Orders - New link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Work Orders - New link", "Work Orders - New link should be clicked successfully", "FAIL", row);
		}

	}

	public void ClickLogout(int row) throws IOException, BiffException {
		try {
			clickOnButton(btn_Logout, "Logout");
			WriteTestReportinExcelWithScreenShot("Click Logout link", "Logout link should be clicked successfully", "PASS", row);
		} catch (Exception e) {
			// TODO: handle exception
			WriteTestReportinExcelWithScreenShot("Click Logout link", "Logout link should be clicked successfully", "FAIL", row);
		}
		
	}

	/**
	 * Refresh the current page.
	 * 
	 * @return
	 * 
	 * @return Nil
	 */
	public void refreshPage() {
		driver.navigate().refresh();
		waitForAjax();
		PageFactory.initElements(driver, this);
	}

}