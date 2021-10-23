package com.AdminUser.CommonPages;

import java.io.IOException;

import com.AdminUser.Pages.AdminUser_Login;
import com.AdminUser.Pages.AdminUser_Records;

import jxl.read.biff.BiffException;

public class GoClinical_AdminUser_ReviewRecords {
	
	public static void AdminUser_LaunchURL() throws IOException, InterruptedException {
		AdminUser_Login getPage_PageLaunchUrl = new AdminUser_Login();
		getPage_PageLaunchUrl.LaunchURL();
	}
	
	public static void AdminUser_Login(int row) throws IOException, InterruptedException, BiffException {
		AdminUser_Login getPage_PageLogin = new AdminUser_Login();
		getPage_PageLogin.login(row);
	}

	public static void Review_Records(int row)
			throws IOException, InterruptedException, BiffException {
		AdminUser_Records getPage_PageLogin = new AdminUser_Records();
		getPage_PageLogin.Records(row);
	}
	
	public static void Review_AutomationTesting_Records(int row)
			throws IOException, InterruptedException, BiffException {
		AdminUser_Records getPage_PageLogin = new AdminUser_Records();
		getPage_PageLogin.AutomationTestingRecords(row);
	}
	
	public static void GoCliniCal_AdminUser_Menu_ClickLogout() throws IOException, InterruptedException {
		GoClinical_AdminUser_Menu getPage_PageLogout = new GoClinical_AdminUser_Menu();
		getPage_PageLogout.ClickWelcomUser();
		getPage_PageLogout.ClickLogout();
	}
}