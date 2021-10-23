package com.AdminUser.CommonPages;

import java.io.IOException;

import com.AdminUser.Pages.AdminUser_Login;
import com.AdminUser.Pages.AdminUser_NewWorkOrder;

import jxl.read.biff.BiffException;

import com.AdminUser.CommonPages.GoClinical_AdminUser_Menu;

public class GoClinical_AdminUser_NewWorkOrder {
	
	public static void AdminUser_LaunchURL() throws IOException, InterruptedException {
		AdminUser_Login getPage_PageLaunchUrl = new AdminUser_Login();
		getPage_PageLaunchUrl.LaunchURL();
	}

	public static void AdminUser_Login(int row) throws IOException, InterruptedException, BiffException {
		AdminUser_Login getPage_PageLogin = new AdminUser_Login();
		getPage_PageLogin.login(row);
	}

	public static void Create_NewWorkOrder(int row)
			throws Exception {
		AdminUser_NewWorkOrder getPage_PageLogin = new AdminUser_NewWorkOrder();
		getPage_PageLogin.WorkOrders_New(row);
	}
	
	public static void GoCliniCal_AdminUser_Menu_ClickLogout() throws IOException, InterruptedException {
		GoClinical_AdminUser_Menu getPage_PageLogout = new GoClinical_AdminUser_Menu();
		getPage_PageLogout.ClickWelcomUser();
		getPage_PageLogout.ClickLogout();
	}
	
	public static void GoCliniCal_PageRefresh() throws IOException, InterruptedException {
		GoClinical_AdminUser_Menu getPage_PageRefresh = new GoClinical_AdminUser_Menu();
		getPage_PageRefresh.refreshPage();
		
	}
	
}