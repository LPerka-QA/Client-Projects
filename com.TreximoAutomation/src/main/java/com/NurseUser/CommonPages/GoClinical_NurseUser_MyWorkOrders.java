package com.NurseUser.CommonPages;

import java.io.IOException;

import com.NurseUser.CommonPages.GoClinical_NurseUser_Menu;
import com.NurseUser.Pages.NurseUser_Login;
import com.NurseUser.Pages.NurseUser_MyWorkOrders;
import com.NurseUser.Pages.NurseUser_MyWorkOrders_AutomationTesting;
import com.NurseUser.Pages.NurseUser_Records;

import jxl.read.biff.BiffException;

public class GoClinical_NurseUser_MyWorkOrders {
	
	public static void NurseUser_LaunchURL() throws IOException, InterruptedException, BiffException {
		NurseUser_Login getPage_launchurl = new NurseUser_Login();
		getPage_launchurl.LaunchURL();
	}
	
	
	public static void NurseUser_Login(int row) throws IOException, InterruptedException, BiffException {
		NurseUser_Login getPage_PageLogin = new NurseUser_Login();
		getPage_PageLogin.login(row);
	}

	public static void MyWorkOrders_VitalSigns(int row)
			throws IOException, InterruptedException, BiffException {
		NurseUser_MyWorkOrders getPage_PageLogin = new NurseUser_MyWorkOrders();
		getPage_PageLogin.MyWorkOrders_VitalSigns(row);
	}
	
	public static void MyWorkOrders_AutoTestingForm1(int row)
			throws IOException, InterruptedException, BiffException {
		NurseUser_MyWorkOrders_AutomationTesting getPage_PageLogin = new NurseUser_MyWorkOrders_AutomationTesting();
		getPage_PageLogin.MyWorkOrders_AutomationTesting(row);
	}
	
	public static void Records(int row)
			throws IOException, InterruptedException, BiffException {
		NurseUser_Records getPage_PageLogin = new NurseUser_Records();
		getPage_PageLogin.Records(row);
	}
	
	public static void GoCliniCal_NurseUser_Menu_ClickLogout() throws IOException, InterruptedException {
		GoClinical_NurseUser_Menu getPage_PageLogout = new GoClinical_NurseUser_Menu();
		getPage_PageLogout.ClickWelcomUser();
		getPage_PageLogout.ClickLogout();
	}
	
	public static void GoCliniCal_PageRefresh() throws IOException, InterruptedException {
		GoClinical_NurseUser_Menu getPage_PageRefresh = new GoClinical_NurseUser_Menu();
		getPage_PageRefresh.refreshPage();
	
	}

}