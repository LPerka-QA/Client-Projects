package com.AdminUser.CommonPages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Utils.BasePages;

public class GoClinical_AdminUser_Menu extends BasePages{
	
	public GoClinical_AdminUser_Menu() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

// Objects from Application
// Menu
	
	public static WebElement btn_WorkOrders() {
		return driver.findElement(By.xpath("//li[@class='dropdown']//a[@class='dropdown-toggle' and text()='Work Orders ']"));

	}
	
	public static WebElement btn_Records() {
		return driver.findElement(By.xpath("//li//a[@href='/records' and text()='Records']"));

	}

	public static WebElement btn_WelcomeUser() {
		return driver.findElement(By.xpath("//a[@class='dropdown-toggle' and contains(text(),'Welcome Lokendernath Perka ')]"));

	}

	

// Objects from Application
// Sub Menu

	public static WebElement btn_WorkOrders_New() {
		return driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li//a[@href='/work_orders/new' and text()='New...']"));

	}
	
	public static WebElement btn_Logout() {
		return driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a[@href='/users/sign_out' and text()='Logout']"));

	}


// Each Object Performance Method
// Menu
	
	
	public void ClickWorkOrders() throws IOException {
		clickOnButton(btn_WorkOrders(), "Work Orders ");

	}
	
	public void ClickRecords() throws IOException {
		clickOnButton(btn_Records(), "Records");

	}
	
	public void ClickWelcomUser() throws IOException {
		clickOnButton(btn_WelcomeUser(), "Welcome Lokendernath Perka");

	}


// Each Object Performance Method
// Sub Menu

	public void ClickWorkOrdersNew() throws IOException {
		clickOnButton(btn_WorkOrders_New(), "Work Orders - New");

	}
	
	public void ClickLogout() throws IOException {
		clickOnButton(btn_Logout(), "Logout");

	}
	
	/**
	 * Refresh the current page.
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