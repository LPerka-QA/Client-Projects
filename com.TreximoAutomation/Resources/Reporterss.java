package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class Reporterss extends HTMLReporter  {
	
public Reporterss(String Reportpath) {
		super(Reportpath);
		
			ResultsLog1 = new HTMLReporter("C:\\Users\\parigue\\eclipse-workspace\\CH_MGS_App\\Reports\\TestResults.html");
			ResultsLog1.attachreporter("NewMember");
	}


static HTMLReporter ResultsLog1;




	public static void testreport() throws IOException {
	
	
	}

}

// Address Information Section

public static WebElement txt_GiftStreet() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:homeSt'][@name='page:form:pb_member:pbs_addrSection:homeSt']"));
}

public static WebElement txt_GiftCity() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:homeCity'][@name='page:form:pb_member:pbs_addrSection:homeCity']"));
}

public static WebElement dpd_GiftState() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:homeState'][@name='page:form:pb_member:pbs_addrSection:homeState']"));
}

public static WebElement txt_GiftZipcode() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:homeZip'][@name='page:form:pb_member:pbs_addrSection:homeZip']"));
}

// Shipping Address Information Section
public static WebElement txt_GiftShipStreet() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:ShipSt'][@name='page:form:pb_member:pbs_addrSection:ShipSt']"));
}

public static WebElement txt_GiftShipCity() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:ShipCity'][@name='page:form:pb_member:pbs_addrSection:ShipCity']"));
}

public static WebElement dpd_GiftShipState() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:ShipState'][@name='page:form:pb_member:pbs_addrSection:ShipState']"));
}

public static WebElement txt_GiftSHipZipcode() {

	return driver.findElement(By.xpath(
			"//*[@id='page:form:pb_member:pbs_addrSection:ShipZip'][@name='page:form:pb_member:pbs_addrSection:ShipZip']"));
}


public static void EnterGiftStreet(int row) throws IOException {

	enterText(txt_GiftStreet(), "Home Street", data.Getdata("Home Street", row));
}

public static void EnterGiftCity(int row) throws IOException {

	enterText(txt_GiftCity(), "Home City", data.Getdata("Home City", row));
}

public static void SelectGiftState(int row) throws IOException {

	selectByText(dpd_GiftState(), "Home State/Province", data.Getdata("Home State/Province", row));
}

public static void EnterGiftZipcode(int row) throws IOException {

	enterText(txt_GiftZipcode(), "Home Zip/Postal Code", data.Getdata("Home Zip/Postal Code", row));
}

public static void EnterGiftShipStreet(int row) throws IOException {

	enterText(txt_GiftShipStreet(), "Home Street", data.Getdata("Home Street", row));
}

public static void EnterGiftShipCity(int row) throws IOException {

	enterText(txt_GiftShipCity(), "Home City", data.Getdata("Home City", row));
}

public static void SelectGiftShipState(int row) throws IOException {

	selectByText(dpd_GiftShipState(), "Home State/Province", data.Getdata("Home State/Province", row));
}

public static void EnterGiftShipZipcode(int row) throws IOException {

	enterText(txt_GiftSHipZipcode(), "Home Zip/Postal Code", data.Getdata("Home Zip/Postal Code", row));
}
