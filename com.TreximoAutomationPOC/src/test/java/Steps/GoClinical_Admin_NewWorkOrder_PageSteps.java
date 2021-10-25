package Steps;

import java.io.IOException;
import com.Utils.ReadWriteDatatoExcel;

import PageObjects.AdminUser_Login;
import PageObjects.AdminUser_NewWorkOrders;
import PageObjects.GoClinical_AdminUser_Menu;
import jxl.read.biff.BiffException;

public class GoClinical_Admin_NewWorkOrder_PageSteps {
	GoClinical_AdminUser_Menu goClinical_AdminUser_Menu;
	AdminUser_Login adminUser_Login;
	ReadWriteDatatoExcel readwritetoExcel;
	AdminUser_NewWorkOrders adminuser_NewWorkOrders;

	public void Login(int row) throws BiffException, IOException {
		adminUser_Login.LaunchURL(row);
		adminUser_Login.EnterUserEmail(row);
		adminUser_Login.EnterUserPassword(row);
		adminUser_Login.ClickLogInButton(row);
		adminUser_Login.ValidateSignInMsg(row, readwritetoExcel.Getdata("Expected Signed In Message", row).trim());
		adminUser_Login.ValidateAdminHomePageMsg(row, readwritetoExcel.Getdata("Expected Admin Home Page", row).trim());
	}

	public void CreateNewWorkOrderRecord(int row) throws IOException, InterruptedException, BiffException {
		goClinical_AdminUser_Menu.ClickWorkOrders(row);
		goClinical_AdminUser_Menu.ClickWorkOrdersNew(row);
		adminuser_NewWorkOrders.SelectSponsor(row, readwritetoExcel.Getdata("Sponsor", row).trim());
		adminuser_NewWorkOrders.SelectProtocol(row, readwritetoExcel.Getdata("Protocol", row).trim());

		System.out.println(readwritetoExcel.Getdata("Patient ID", row).trim());
		adminuser_NewWorkOrders.EnterPatientID(row, readwritetoExcel.Getdata("Patient ID", row).trim());

		adminuser_NewWorkOrders.SelectVisit(row, readwritetoExcel.Getdata("Visit", row).trim());
		adminuser_NewWorkOrders.EnterSrvcePrvdrEmail_RVSP(row,
				readwritetoExcel.Getdata("Service Provider Email RVSP", row).trim());
		adminuser_NewWorkOrders.SelectSndRcrdTo_SiteCntct(row,
				readwritetoExcel.Getdata("Send Record to Site Contact", row).trim());
		adminuser_NewWorkOrders.ClickCreateWorkOrder(row);
		adminuser_NewWorkOrders.ValidateCrtWrkOrdrSuccessMsg(row,
				readwritetoExcel.Getdata("Expected Work Order Created Message", row).trim());
		readwritetoExcel.setData("Work Order Number", row, adminuser_NewWorkOrders.GetWrkOrdrNmbr(row));
		readwritetoExcel.setData("Active Status", row, adminuser_NewWorkOrders.GetActiveStatus(row));
		readwritetoExcel.setData("Status", row, adminuser_NewWorkOrders.GetStatus(row));
		adminuser_NewWorkOrders.ValidatePatientID(row, readwritetoExcel.Getdata("Patient ID", row).trim());
		adminuser_NewWorkOrders.ValidateProtocol(row, readwritetoExcel.Getdata("Protocol", row).trim());
		adminuser_NewWorkOrders.ValidateVisit(row, readwritetoExcel.Getdata("Visit", row).trim());
		adminuser_NewWorkOrders.ValidateSrvcePrvdr(row,
				readwritetoExcel.Getdata("Service Provider Email RVSP", row).trim());
		adminuser_NewWorkOrders.ClickSendWorkOrdertoUser(row);
		adminuser_NewWorkOrders.ValidateNotifyMsg(row,
				readwritetoExcel.Getdata("Service Provider Email RVSP", row).trim());

	}

	public void Logout(int row) throws BiffException, IOException {
		goClinical_AdminUser_Menu.ClickWelcomUser(row);
		goClinical_AdminUser_Menu.ClickLogout(row);
	}

	public void createpageconnections(String excelSheetPath, String Sheetname, String WritePath) {
		readwritetoExcel = new ReadWriteDatatoExcel(excelSheetPath, Sheetname, WritePath);
		goClinical_AdminUser_Menu = new GoClinical_AdminUser_Menu();
		adminUser_Login = new AdminUser_Login();
		adminuser_NewWorkOrders = new AdminUser_NewWorkOrders();
	}

	public int ExcelCount() {
		return readwritetoExcel.GetRows();
	}

}
