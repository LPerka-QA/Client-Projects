package pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;
//import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	protected WebDriver webDriver; // SHould work on this browser
	public boolean switchSuccess;
	static HTMLReporter report;

	/**
	 * Page Name/Header
	 **/
	@FindBy(xpath = "//span[@id='pageTitle']")
	public WebElement pageName;

	/**
	 * Constructor for Page class
	 */
	protected BasePage() {
		webDriver.switchTo().defaultContent();
		waitForPageLoadToComplete();
		waitForAjax();
		PageFactory.initElements(webDriver, this);
		report = new HTMLReporter("C:\\Users\\parigue\\eclipse-workspace\\CH_MGS_App\\Reports"); // Should work on PAth -- Pradeep
		report.attachreporter("NewMember");
	}

	/**
	 * Constructor for Page class when content in frame
	 * 
	 * @param frameid
	 */
	protected BasePage(int frameid) {
		try {
			frameSwitch(frameid);
			PageFactory.initElements(webDriver, this);
		} catch (RuntimeException e) {
			System.out.println("Base Page: " + e.toString());
			//report.reportFailEvent("Page Constructor",
			//		report.addModal("Error loading page/elements EXCEPTION CAUGHT", e.toString()));
		}
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param element WebElement need to check
	 * @return True if present
	 */
	protected boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	/**
	 * Check if the element is present in the page
	 * 
	 * @param by By need to check
	 * @return True if present
	 */
	public boolean isElementPresent(By by) {
		try {
			return webDriver.findElement(by).isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex2) {
			return false;
		}
	}

	// *****************************************************************************************************************//
	// Windows or WebPages TODO
	// *****************************************************************************************************************//

	/**
	 * Method to wait until all javascript in a page has loaded completely
	 * 
	 * @author : Pradeep Kumar Arigue
	 */
	public void waitForPageLoadToComplete() {
		WebDriverWait wait = new WebDriverWait(webDriver, 300);
		try {
			ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};
			wait.until(jsLoad);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to wait for all Ajax calls to complete
	 * 
	 * @author : Pradeep Kumar Arigue
	 */
	public void waitForAjax() {
		WebDriverWait wait = new WebDriverWait(webDriver, 300);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};
		wait.until(jQueryLoad);
	}

	/***
	 * Method to switch into specified frame
	 * 
	 * @param : By for element
	 * @return : None
	 * @author : Pradeep Kumar Arigue
	 ***/
	public void windowSwitch(By by) {
		try {
			webDriver.switchTo().frame(webDriver.findElement(by));
		} catch (RuntimeException e) {
			report.reportFailEvent("Switching frame ", "Switching Frame failed" + e.getMessage());
		}
	}

	/***
	 * Method to switch into frame only when available
	 * 
	 * @param : Int index of the frame.
	 * @return : None
	 * @author : Pradeep Kumar Arigue
	 ***/
	public void frameSwitch(int frameid) {
		webDriver.switchTo().defaultContent();
		try {
			switchSuccess = waitForElement(webDriver.findElement(By.tagName("iframe")));
		} catch (Exception ex) {
			System.out.println("Before frame Switch" + ex.getMessage());
		}
		waitForPageLoadToComplete();
		waitForAjax();
		for (int i = 0; i < frameid; i++) {
			try {
				webDriver.switchTo().frame(webDriver.findElement(By.tagName("iframe")));
			} catch (Exception e2) {
				System.out.println("Inside loop: Iteration" + i + "  " + e2.getMessage());
			}
			waitForPageLoadToComplete();
			waitForAjax();
		}
	}

	/**
	 * Verify Application in Correct Page.
	 * 
	 * @param isValidPage
	 * @return Nil
	 */
	public void verifyInCorrectPage(boolean isValidPage) {
		if (!isValidPage) {
			String stepName = "Navigation to Page";
			String message = "The application is not in the expected page , current page: " + webDriver.getTitle()
					+ " Page.";
			report.reportFailEvent(stepName, message);
		}
	}

	/**
	 * Refresh the current page.
	 * 
	 * @return Nil
	 */
	public void refreshPage() {
		webDriver.navigate().refresh();
		waitForAjax();
		PageFactory.initElements(webDriver, this);
	}

	/***
	 * Method to navigate to default content
	 * 
	 * @param :
	 * @return :
	 * @author : Pradeep Kumar Arigue
	 ***/
	public void navigateToDefaultContent() {
		webDriver.switchTo().defaultContent();
	}
	/ *****************************************************************************************************************//
	// Reports TODO
	// *****************************************************************************************************************//

	/***
	* Method to update test log to show page is beginning
	* 
	* @param :
	*            Name of page to appear on report
	***/
	public void reportBeginPage(String pageName) {
	report.reportScreenCapture("****", "BEGIN: " + pageName);
	}

	/***
	* Method to update test log to show page is ended
	* 
	* @param :
	*            Name of page to appear on report
	***/
	public void reportEndPage(String pageName) {
	report.reportDoneEvent("****", "END: " + pageName);
	}

	/***
	* Method to update test log with screen shot
	* 
	* @param :
	*            Name of page to appear on report
	***/
	public void reportCaptureDataEntry(String pageName) {
	report.reportScreenCapture("Capture", " Post Data Entry: " + pageName);
	}
	
	// *****************************************************************************************************************//
	// Button TODO
	// *****************************************************************************************************************//

	/***
	* Method to click on a button
	* 
	* @param :
	*            WebElement
	***/
	public void clickOnButton(WebElement element, String btnName) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.click();
	waitForAjax();
	report.reportDoneEvent("Click On Button", "Successfully clicked on '" + btnName + "' button");
	}
	} catch (RuntimeException ex) {
	report.reportFailEvent("Click" + btnName,
	report.addModal(btnName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString()));
	}
	}

	/***
	* Method to click on a button with no reporting
	* 
	* @param :
	*            WebElement
	***/
	public void clickOnButtonNoReport(WebElement element, String btnName) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.click();
	waitForAjax();
	}
	} catch (RuntimeException ex) {
	report.reportFailEvent("Click" + btnName,
	report.addModal(btnName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString()));
	}
	}

	/***
	* Method to hover on one element then move to a child element and click
	* 
	* @param :
	*            WebElement - wait element
	* @param :
	*            WebElement - click element
	* @param :
	*            String - Button/link Name
	***/
	public void hoverThenClick(WebElement waitElement, WebElement clickElement, String btnName) {
	try {
	waitForElement(waitElement);
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	js.executeScript("arguments[0].setAttribute('style', 'visibility: visible;')", clickElement);
	clickElement.click();
	waitForAjax();
	report.reportDoneEvent("Hover then Click", "Successfully clicked on '" + btnName);

	} catch (RuntimeException ex) {
	report.reportFailEvent("Click" + btnName,
	report.addModal(btnName + " was NOT clicked successfully, EXCEPTION CAUGHT", ex.toString()));
	}
	}

	// *****************************************************************************************************************//
	// TextBox TODO
	// *****************************************************************************************************************//

	/***
	* Method to enter text in a textbox
	* 
	* @param :
	*            Element Name
	* @param :
	*            Field Name
	* @param :
	*            Text to Enter
	* @author : MBR
	***/
	public void enterText(WebElement element, String fieldName, String text) {
	if (text != "") {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.clear();
	waitForAjax();
	element.sendKeys(text);
	report.reportDoneEvent("Enter Text", "Successfully entered " + fieldName + ": " + text.trim());
	}
	waitForAjax();
	} catch (RuntimeException ex) {
	report.reportFailEvent("Enter Text", report.addModal(
	"NOT able to enter " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()));
	}
	}
	}

	/***
	* Method to update text in a textbox
	* 
	* @param :
	*            Element Name
	* @param :
	*            Field Name
	* @param :
	*            Text to Enter/Update
	* @author : MBR
	***/
	public void updateText(WebElement element, String fieldName, String text) {
	if (text.equalsIgnoreCase("BLANK")) {
	clearText(element, fieldName);
	} else {
	enterText(element, fieldName, text);
	}
	}

	/***
	* Method to enter text in a autofil textbox
	* 
	* @param :
	*            Element Name
	* @param :
	*            Field Name
	* @param :
	*            Text to Enter
	* @author : MBR
	***/
	public void enterAutoFillText(WebElement element, String fieldName, String text) {
	if (text.equalsIgnoreCase("BLANK")) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	js.executeScript("arguments[0].setAttribute('value', '')", element);
	report.reportDoneEvent("Clear Text", "Successfully cleared " + fieldName);
	}
	waitForAjax();
	} catch (RuntimeException ex) {
	report.reportFailEvent("Clear Text", report.addModal(
	"Error Clearing " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()));
	}
	} else if (text != "") {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	js.executeScript("arguments[0].setAttribute('value', '" + text + "')", element);
	report.reportDoneEvent("Enter Text", "Successfully entered " + fieldName + ": " + text);
	}
	waitForAjax();
	} catch (RuntimeException ex) {
	report.reportFailEvent("Enter Text", report.addModal(
	"NOT able to enter " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()));
	}
	}
	}

	/***
	* Method to enter secure text in a textbox
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam)
	***/
	public void enterSecureText(WebElement element, String fieldName, String text) {
	if (text != "") {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.clear();
	waitForAjax();
	element.sendKeys(text);
	report.reportDoneEvent("Enter Secure Text",
	"Successfully entered " + fieldName + ": " + text.replaceAll(".", "*"));
	}
	waitForAjax();
	} catch (RuntimeException ex) {
	report.reportFailEvent("Enter Secure Text", report.addModal(
	"NOT able to enter " + fieldName + " (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()));
	}
	}
	}

	/***
	* Method to clear text in a textbox
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : MBR
	***/
	public void clearText(WebElement element, String fieldName) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.clear();
	report.reportDoneEvent("Clear Text", "Successfully cleared text in " + fieldName);
	}
	} catch (RuntimeException ex) {
	report.reportFailEvent("Clear Text",
	report.addModal("NOT able to clear text in " + fieldName + " EXCEPTION CAUGHT", ex.toString()));
	}
	}

	/***
	* Method to verify textbox is displayed
	* 
	* @param :
	*            Element Name
	* @return :
	***/
	public void verifyTextBox(WebElement element) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	report.reportPassEvent("VerifyTextBoxPresence", "TextBox is displayed");
	report.reportDoneEvent("VerifyTextBoxPresence", "TextBox is displayed");
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("VerifyTextBoxPresence", "TextBox is not displayed", Status.SCREENSHOT);
	report.updateTestLog("VerifyTextBoxPresence", "TextBox is not displayed" + ex.getMessage(), Status.FAIL);
	}
	}
	
	// *****************************************************************************************************************//
	// DropDown TODO
	// *****************************************************************************************************************//
	/***
	* Method to select drop down by text
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam)
	***/
	public void selectByText(WebElement element, String fieldName, String text) {
	if (text != "") {
	try {
	waitForElement(element);
	Select sel = new Select(element);
	if (isElementPresent(element)) {
	if (!(text.isEmpty())) {
	sel.selectByVisibleText(text.trim());
	waitForAjax();
	report.reportDoneEvent("Select from DropDown",
	"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
	} else {
	report.reportDoneEvent("Select from DropDown", "Drop Down Value is not present " + fieldName);
	}
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Select from DropDown", "NOT able to select from the " + fieldName + " DropDown ("
	+ text.trim() + ") \n EXCEPTION CAUGHT : " + ex.getMessage(), Status.FAIL);
	}
	}
	}

	/***
	* Method to select drop down by text without a wait for Ajax
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Adam Klein
	***/
	public void selectByText_NoWait(WebElement element, String fieldName, String text) {
	if (text != "") {
	try {
	waitForElement(element);
	Select sel = new Select(element);
	if (isElementPresent(element)) {
	if (!(text.isEmpty())) {
	sel.selectByVisibleText(text.trim());
	report.reportDoneEvent("Select from DropDown",
	"Successfully selected from " + fieldName + " DropDown (" + text.trim() + ")");
	} else {
	report.reportDoneEvent("Select from DropDown", "Drop Down Value is not present " + fieldName);
	}
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Select from DropDown", "NOT able to select from the " + fieldName + " DropDown ("
	+ text.trim() + ") \n EXCEPTION CAUGHT : " + ex.getMessage(), Status.FAIL);
	}
	}
	}

	/***
	* Method to getSizeSelectCombo
	* 
	* @param :
	*            Details
	* @return : None
	* @author : Samjas Subair(Sam) Modified By : Adam Klein
	* @throws InterruptedException
	***/
	public int getSizeSelectCombo(WebElement selObj) {
	Select sel = new Select(selObj);
	List<WebElement> selSize = sel.getOptions();
	int size = selSize.size();
	return size;
	}

	/***
	* Method to Verify the selectComboOptions
	* 
	* @param :
	*            WebElement
	* @param :
	*            String list
	* @return : None
	* @author : Samjas Subair(Sam) Modified By : Adam Klein
	* @throws InterruptedException
	***/
	public void selectComboOptions(WebElement selObj, String[] expOption) {
	try {
	Select sel = new Select(selObj);
	String flag = null;
	flag = "true";
	for (int i = 0; i < sel.getOptions().size(); i++) {
	System.out.println(sel.getOptions().get(i).getText());
	if (sel.getOptions().get(i).getText().trim().equalsIgnoreCase(expOption[i])) {
	report.updateTestLog("VerifyDropdown :" + expOption[i], "Dropdown have the value ->" + expOption[i],
	Status.PASS);
	flag = "true";
	} else {
	flag = "False";
	report.reportDoneEvent("VerifyDropdown :" + expOption[i],
	"Dropdown does not have the value ->" + expOption[i]);
	}
	}
	if (flag == "False") {
	report.reportDoneEvent("selectComboOptions :", "Verification Failed some values missing");
	}
	} catch (NoSuchElementException ex) {
	report.reportFailEvent("selectComboOptions :", ex.getMessage());
	}
	}

	// *****************************************************************************************************************//
	// RadioButton TODO
	// *****************************************************************************************************************//

	/***
	* Method to click on a radio button(WebElement radio)
	* 
	* @param :
	*            Webelement
	* @param :
	*            radio button description
	* @param :
	*            option to click
	* @author : MBR
	***/
	public void clickOnRadioButton(WebElement element, String fieldName, String text) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	element.click();
	waitForAjax();
	report.reportDoneEvent("Click On Radio",
	"Successfully clicked on '" + fieldName + "' Radio Button (" + text + ")");
	}
	} catch (Exception ex) {
	report.reportFailEvent("Click On Radio", report
	.addModal("NOT able to click '" + fieldName + "' Radio Button. EXCEPTION CAUGHT", ex.toString()));
	}
	}

	// *****************************************************************************************************************//
	// Check Box TODO
	// *****************************************************************************************************************//

	/***
	* Method to click on a check box(WebElement checkbox)
	* 
	* @param :
	*            Element Name
	* @author : Samjas Subair(Sam)
	***/
	public void clickOnCheckbox(WebElement element, String fieldName) {
	try {
	waitForElement(element);
	if (isElementPresent(element)) {
	Actions action = new Actions(webDriver);
	action.moveToElement(element).build().perform();
	element.click();
	waitForAjax();
	report.reportDoneEvent("Click On CheckBox", "Successfully clicked on Check Box " + fieldName);
	}
	} catch (Exception ex) {
	report.reportFailEvent("Click On CheckBox",
	report.addModal("NOT able to click '" + fieldName + "' CheckBox. EXCEPTION CAUGHT", ex.toString()));
	}
	}

	// *****************************************************************************************************************//
	// Links TODO
	// *****************************************************************************************************************//

	/***
	* Method to click on a link(WebElement link)
	* 
	* @param :
	*            Element Name
	* @author : MBR
	***/
	public void clickOnLink(WebElement element, String linkName) {
	try {
	waitForElement(element);
	String linkText = element.getText();
	if (isElementPresent(element)) {
	element.click();
	waitForAjax();
	report.reportDoneEvent("Click On Link", "Successfully clicked on link: " + linkName);
	}
	} catch (Exception ex) {
	report.reportFailEvent("Click On Link",
	report.addModal("NOT able to click '" + linkName + "' link. EXCEPTION CAUGHT", ex.toString()));
	}
	}

	/**
	* Method to sroll down to find element
	* 
	* @param :
	*            Element Name
	*/
	public Object scrollElementIntoView(WebElement element) {
	return ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// *****************************************************************************************************************//
	// Selection from tabs or submenus TODO
	// *****************************************************************************************************************//

	/**
	* Method to click main Header navigation Menu Items
	* 
	* @param menuHeaderItem
	*/
	protected void clickMainMenu(WebElement element) {
	try {
	waitForElement(element);
	element.click();
	report.reportDoneEvent("Click Main Menu", element.getText().trim() + " clicked successfully.");
	} catch (RuntimeException ex) {
	report.reportFailEvent("Click Main Menu", report.addModal(
	"NOT able to click '" + element.getText().trim() + " . EXCEPTION CAUGHT : ", ex.toString()));
	}
	}

	/**
	* Method to click sub Header navigation Menu Items
	* 
	* @param menuHeaderItem
	*/
	protected void clickSubMenu(WebElement element) {
	try {
	 Actions actions = new Actions(webDriver);
	 actions.moveToElement(element).click().perform();
	element.click();
	report.reportDoneEvent("Click Sub Menu", element.getText().trim() + " clicked successfully.");
	} catch (RuntimeException ex) {
	report.reportFailEvent("Click Sub Menu", report.addModal(
	"NOT able to click '" + element.getText().trim() + " . EXCEPTION CAUGHT : ", ex.toString()));
	}
	}

	/**
	* Method to click sub menu items displayed after hovering on main menu
	* 
	* @param main
	*            menu
	* @param sessionID
	*            Item
	*/
	protected void clickMenuItem(WebElement mainMenu, WebElement menuItem) {
	String menu = mainMenu.getText().trim();
	String menuitem = menuItem.getText().trim();
	try {
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	js.executeScript("arguments[0].setAttribute('class', 'megaMenu visible'); arguments[1].click();", mainMenu,
	menuItem);
	report.reportDoneEvent("Click Menu Item", menuitem + " from " + menu + " menu clicked successfully.");
	} catch (RuntimeException ex) {
	report.reportFailEvent("Click Menu Item",
	report.addModal("NOT able to click '" + menuitem + " . EXCEPTION CAUGHT : ", ex.toString()));
	}
	}

	/**
	* Method to double click on a webelement
	* 
	* @param element
	* @return
	* @author : Mel Rethlake (MBR)
	*/
	protected void doubleClick(WebElement element) {
	try {
	waitForElement(element);
	if ((new TestSettings()).getBrowser().equalsIgnoreCase("iexplore")) {
	Actions builder = new Actions(webDriver);
	builder.doubleClick(element).build().perform();
	waitForAjax();
	report.reportDoneEvent("ClickOn" + element.getText().trim(),
	"Successfully clicked on " + element.getText().trim());
	} else {
	element.click();
	report.reportDoneEvent("ClickOn" + element.getText().trim(),
	"Successfully clicked on " + element.getText().trim());
	}
	} catch (RuntimeException ex) {
	report.reportDoneEvent("Click" + element.getText().trim(),
	element.getText().trim() + " is NOT clicked successfully, EXCEPTION CAUGHT : " + ex.getMessage());
	}
	}

	// *****************************************************************************************************************//
	// Waits TODO
	// *****************************************************************************************************************//

	/**
	* Method to wait for iframe to load in the page
	* 
	* @param WebElement
	*/
	protected Boolean waitForIframe() {
	try {
	new WebDriverWait(webDriver, 30).until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
	} catch (Exception ex) {
	return false;
	}
	return true;
	}

	/**
	* Method to wait for element to load in the page
	* 
	* @param WebElement
	*/
	protected Boolean waitForElement(WebElement we) {
	try {
	new WebDriverWait(webDriver, 60).until(ExpectedConditions.visibilityOf(we));
	return true;
	} catch (RuntimeException ex) {
	report.reportFailEvent("waitForElement",
	report.addModal("Element NOT visible, EXCEPTION CAUGHT", ex.toString()));
	return false;
	}
	}

	/**
	* Method to wait for element to load in the page with a specified timeout.
	* After timeout, proceed.
	* 
	* @param WebElement
	* @param Int
	*            timeout in seconds
	*/
	protected Boolean waitForElementAndProceed(WebElement we, int timeout) {
	try {
	new WebDriverWait(webDriver, timeout).until(ExpectedConditions.visibilityOf(we));
	return true;
	} catch (Throwable ex) {
	return false;
	}
	}

	/**
	* Method to wait for element to load in the page
	* 
	* @param WebElement
	*/
	protected Boolean longwaitForElement(WebElement element) {
	try {
	int count = 0;
	while (count < 20) {
	new WebDriverWait(webDriver, 50).until(ExpectedConditions.visibilityOf(element));
	if (isElementPresent(element)) {
	break;
	}
	count++;
	}
	return true;
	} catch (UnhandledAlertException ex) {
	report.reportFailEvent("AlertPresent", "Message is->" + ex.getAlertText());
	return false;
	} catch (Exception Ex) {
	return false;
	}
	}

	/**
	* @author MBR method to wait for title of the page
	*/

	public boolean waitForExpectedTitle(String TitleContains) {
	try {
	WebDriverWait wait = new WebDriverWait(webDriver, 20);
	Boolean S = wait.until(ExpectedConditions.titleContains(TitleContains));
	if (S) {
	return true;
	} else {
	return false;
	}
	} catch (Exception e) {
	e.printStackTrace();
	return false;
	}
	}
	
	// *****************************************************************************************************************//
	// General TODO
	// *****************************************************************************************************************//
	/***
	* Method to check if element is enabled(element_name)
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam)
	***/
	public void isElementEnabled(WebElement element) {
	try {
	if (isElementPresent(element)) {
	if (element.isEnabled()) {
	report.updateTestLog("IsElementEnabled", element.getText() + "is enabled", Status.SCREENSHOT);
	report.reportPassEvent("IsElementEnabled", element.getText() + "is enabled");
	} else {
	report.updateTestLog("IsElementEnabled", element.getText() + "is not enabled", Status.SCREENSHOT);
	report.updateTestLog("IsElementEnabled", element.getText() + "is not enabled", Status.FAIL);
	}

	}
	} catch (RuntimeException ex) {

	report.updateTestLog("ElementIsNotAvailable", element.getText() + "is not available", Status.FAIL);
	}
	}

	/***
	* Method to check if element is enabled(element_name)
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public void isTextBoxEnabled(WebElement element, String elementName) {
	try {
	if (isElementPresent(element)) {
	if (element.isEnabled()) {
	report.updateTestLog("IsElementEnabled", elementName + "is enabled", Status.SCREENSHOT);
	report.reportPassEvent("IsElementEnabled", elementName + "is enabled");
	} else {
	report.updateTestLog("IsElementEnabled", elementName + "is not enabled", Status.SCREENSHOT);
	report.updateTestLog("IsElementEnabled", elementName + "is not enabled", Status.FAIL);
	}

	}
	} catch (RuntimeException ex) {

	report.updateTestLog("ElementIsNotAvailable", elementName + "is not available", Status.FAIL);
	}
	}

	/***
	* Method to check if element is disabled(element_name)
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public void isElementDisabled(WebElement element) {
	try {
	if (isElementPresent(element)) {
	if (element.isEnabled()) {
	report.updateTestLog("isElementDisabled", element.getText() + "is not disabled", Status.SCREENSHOT);
	report.updateTestLog("isElementDisabled", element.getText() + "is not disabled", Status.FAIL);
	} else {
	report.updateTestLog("isElementDisabled", element.getText() + "is disabled", Status.SCREENSHOT);
	report.reportPassEvent("isElementDisabled", element.getText() + "is disabled");
	}

	}
	} catch (RuntimeException ex) {
	report.updateTestLog("ElementIsNotAvailable", element.getText() + "is not available", Status.FAIL);
	}
	}

	/***
	* Method to check if textbox is disabled(element_name)
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public void isTextBoxDisabled(WebElement element, String elementName) {
	try {
	if (isElementPresent(element)) {
	if (element.isEnabled()) {
	report.updateTestLog("isElementDisabled", elementName + "is not disabled", Status.SCREENSHOT);
	report.updateTestLog("isElementDisabled", elementName + "is not disabled", Status.FAIL);
	} else {
	report.updateTestLog("isElementDisabled", elementName + "is disabled", Status.SCREENSHOT);
	report.reportPassEvent("isElementDisabled", elementName + "is disabled");
	}

	}
	} catch (RuntimeException ex) {
	report.updateTestLog("ElementIsNotAvailable", elementName + "is not available", Status.FAIL);
	}
	}

	/**
	* method to make a thread sleep for customized time in milliseconds
	* 
	* @param milliseconds
	* @author : Samjas Subair(Sam)
	*/
	protected void sleep(int milliseconds) {
	try {
	Thread.sleep(milliseconds);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}
	}

	// *****************************************************************************************************************//
	// Date and Time TODO
	// *****************************************************************************************************************//
	/***
	* Method to Alter Today's date to specified date
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam)
	***/
	public Date getAlteredDate(Integer NoOfDays) {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, NoOfDays);
	return cal.getTime();
	}

	/***
	* Method to get current time stamp
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam)
	***/
	public String getCurrentTimeStamp() {
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	Date now = new Date();
	String strDate = sdfDate.format(now);
	return strDate;
	}

	/***
	* Method to get current time in minutes
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public int getCurrentTimeInMin() {
	String time = new SimpleDateFormat("HH:mm").format(new Date());
	String[] splitTime = time.split(":");
	int hr = Integer.parseInt(splitTime[0]);
	int mn = Integer.parseInt(splitTime[1].substring(0, 2));
	if (hr > 12) {
	hr = hr - 12;
	}
	int timStamp = (hr * 60) + mn;
	return timStamp;
	}

	/***
	* Method to get current time in minutes
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public int getTimeInMin(String time) {
	// String time=new SimpleDateFormat("HH:mm").format(new Date());
	String[] splitTime = time.split(":");
	int hr = Integer.parseInt(splitTime[0]);
	int mn = Integer.parseInt(splitTime[1].substring(0, 2));
	if (hr > 12) {
	hr = hr - 12;
	}
	int timStamp = (hr * 60) + mn;
	return timStamp;
	}

	/***
	* Method to get current time in minutes
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public String getTimeIntwelveHrFormat(String time) {
	// String time=new SimpleDateFormat("HH:mm").format(new Date());
	String[] splitTime = time.split(":");
	int hr = Integer.parseInt(splitTime[0]);
	int mn = Integer.parseInt(splitTime[1].substring(0, 2));
	if (hr > 12) {
	hr = hr - 12;
	}
	String timStamp = Integer.toString(hr) + ":" + Integer.toString(mn);
	return timStamp;
	}

	/***
	* Method to get current day
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public int getCurrentDay() {
	int day;
	String dayformat = null;
	dayformat = new SimpleDateFormat("dd").format(new Date());
	day = Integer.parseInt(dayformat);
	return day;
	}

	/***
	* Method to get current month
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public int getCurrentMonth() {
	int month;
	String monthformat = null;
	Calendar now = Calendar.getInstance();
	System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
	month = now.get(Calendar.MONTH) + 1;
	if (month < 10) {
	monthformat = '0' + Integer.toString(month);
	month = Integer.parseInt(monthformat);
	}
	return month;
	}

	/***
	* Method to get current year
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Samjas Subair(Sam) Modified By :
	***/
	public int getCurrentYear() {
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	return year;
	}

	/***
	* Method to hover on a link(WebElement link)
	* 
	* @param :
	*            Element Name
	* @author : Samjas Subair(Sam)
	***/
	public void hooverOnLink(WebElement link) {
	String linkText = link.getText();
	try {
	waitForElement(link);
	if (isElementPresent(link)) {
	Actions action = new Actions(webDriver);
	action.moveToElement(link);
	action.perform();
	this.sleep(2);
	report.updateTestLog("HoverOn" + linkText, "Successfully hovered on link " + link.getText().trim(),
	Status.PASS);
	report.updateTestLog("HoverOn" + linkText, "Successfully hovered on link " + link.getText().trim(),
	Status.SCREENSHOT);
	}
	} catch (RuntimeException ex) {
	report.reportDoneEvent("HoverOn" + linkText,
	linkText + " is NOT hovered successfully, EXCEPTION CAUGHT : " + ex.getMessage());
	}
	}

	/***
	* Method to scroll (WebElement link)
	* 
	* @param :
	*            Element Name
	* @author : Samjas Subair(Sam)
	***/
	public void scroll(WebElement link, int height, int width) {
	JavascriptExecutor js = (JavascriptExecutor) webDriver;
	// Ideally give (250,750)
	js.executeScript("javascript:window.scrollBy(height,width)", link);
	}

	public void scrollToElementandclick(WebElement we) {
	Actions builder = new Actions(webDriver);
	builder.moveToElement(we).click().perform();
	((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", we);
	}

	/**
	* Check if the element is present in the page
	* 
	* @param element
	*            WebElement need to check
	* @return True if present
	* @author : Samjas Subair(Sam)
	*/
	protected void isElementPresent_Report(WebElement element) {
	try {
	sleep(5000);
	waitForElement(element);
	element.isDisplayed();
	report.reportPassEvent("CheckElementPresent", element.getText() + ": is present");
	} catch (Exception ex) {
	report.reportFailEvent("" + ex.getMessage(), "Element is not present");
	}
	}

	// *****************************************************************************************************************//
	// Formatting data TODO
	// *****************************************************************************************************************//

	/***
	* Method to format string date split by "/"
	* 
	* @param :
	*            Date to be formatted as a String
	* @return : String[]
	* @author : Mel Rethlake (MBR)
	***/
	public String[] formatTextToDate(String textDate) {
	String[] dates = null;
	String date;
	try {
	// me;
	SimpleDateFormat desiredDateFormat = new SimpleDateFormat("mm/dd/yyyy");
	date = desiredDateFormat.format(desiredDateFormat.parse(textDate));
	System.out.println("Text Date: " + textDate);
	System.out.println("Formatted Date old way: " + date);
	if (date.contains("/00")) {
	System.out.println("Warning: Date may not be formatted correctly");
	if (date.contains("/000") || date.contains("/001") || date.contains("/002")) {
	date = date.replace("/00", "/20");
	} else {
	date = date.replace("/00", "/19");

	}
	}
	dates = date.split("/");
	} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

	} catch (DateTimeParseException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
	}
	return dates;
	}

	/***
	* Method to format string date return a string
	* 
	* @param :
	*            Date to be formatted as a String
	* @return : String
	* @author : Mel Rethlake (MBR)
	***/
	public String formatTextToDateString(String textDate) {
	String date = null;
	try {
	SimpleDateFormat desiredDateFormat = new SimpleDateFormat("mm/dd/yyyy");
	date = desiredDateFormat.format(desiredDateFormat.parse(textDate));
	System.out.println("Text Date: " + textDate);
	System.out.println("Formatted Date old way: " + date);
	if (date.contains("/00")) {
	System.out.println("Warning: Date may not be formatted correctly");
	if (date.contains("/000") || date.contains("/001") || date.contains("/002")) {
	date = date.replace("/00", "/20");
	} else {
	date = date.replace("/00", "/19");
	}
	}
	} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return date;
	}

	/***
	* Method to format SSN to array
	* 
	* @param :
	*            Element Name
	* @return : String[]
	* @author : Mel Rethlake (MBR)
	***/
	public String[] formatSSN(String SSN) {
	String[] arraySSN = new String[3];

	arraySSN[0] = SSN.substring(0, 3);
	arraySSN[1] = SSN.substring(3, 5);
	arraySSN[2] = SSN.substring(5, 9);

	return arraySSN;
	}

	/***
	* Method to generate SSN (or TIN) if does not exist
	* 
	* @return : SSN as a String
	* @author : Mel Rethlake(MBR) Modified By :
	***/
	public String generateSSN() {

	String newSSN;
	Random rand = new Random();
	int value1 = rand.nextInt(9) + 1;
	int value2 = rand.nextInt(9) + 1;
	int value3 = rand.nextInt(9) + 1;
	int value4 = rand.nextInt(9) + 1;
	int value5 = rand.nextInt(9) + 1;
	int value6 = rand.nextInt(9) + 1;
	int value7 = rand.nextInt(9) + 1;
	int value8 = rand.nextInt(9) + 1;
	int value9 = rand.nextInt(9) + 1;
	String num1 = Integer.toString(value1);
	String num2 = Integer.toString(value2);
	String num3 = Integer.toString(value3);
	String num4 = Integer.toString(value4);
	String num5 = Integer.toString(value5);
	String num6 = Integer.toString(value6);
	String num7 = Integer.toString(value7);
	String num8 = Integer.toString(value8);
	String num9 = Integer.toString(value9);
	newSSN = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9;
	report.reportDoneEvent("Generate SSN", "SSN not in datasheet.  Randomly generated as: " + newSSN);

	return newSSN;
	}

	/***
	* Method to format TIN to array
	* 
	* @param :
	*            TIN as a String
	* @return : String []
	* @author : Mel Rethlake(MBR) Modified By :
	***/
	public String[] formatTIN(String TIN) {
	String[] arrayTIN = new String[3];

	arrayTIN[0] = TIN.substring(0, 2);
	arrayTIN[1] = TIN.substring(2, 9);

	return arrayTIN;
	}

	/***
	* Method to resolve apostrophes in xpath
	* 
	* @param :
	*            Element Name
	* @return :
	* @author : Sam Modified By :
	***/
	public String resolveAprostophes(String item) {
	if (!item.contains("'")) {
	return "'" + item + "'";
	}
	StringBuilder finalString = new StringBuilder();
	finalString.append("concat('");
	finalString.append(item.replace("'", "',\"'\",'"));
	finalString.append("')");
	return finalString.toString();
	}

	/***
	* Method to split string phone number by "-"
	* 
	* @param :
	*            Phone number as a String
	* @return : String[]
	* @author : Mel Rethlake (MBR)
	***/
	public String[] formatPhoneNumber(String phoneNumber) {
	return phoneNumber.split("-");
	}

	/***
	* Method to split data by | - for use when indicating what fields to update
	* vs verify
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake (MBR)
	***/
	public String formatUpdateData(String data) {
	String text = "";
	try {
	if (data != "" && data.contains("|")) {
	String textArray[] = data.split("\\|");
	if (textArray.length == 2) {
	text = textArray[1];
	}
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Update Data",
	"Error Formatting (" + data + ") EXCEPTION CAUGHT : " + ex.getMessage(), Status.FAIL);
	}
	return text;
	}

	/***
	* Method to split data by | - for use when looping through multiple role
	* info in same excel cell
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake (MBR)
	***/
	public String formatRoleData(String data, int loop) {
	String text = "";
	try {
	if (data != "" && data.contains("|")) {
	String textArray[] = data.split("\\|");
	if (textArray.length < loop) {
	text = "";
	} else {
	text = textArray[loop];
	}
	} else {
	text = data;
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Update Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return text;
	}

	/***
	* Method to reformat numbers formatted as text to x,xxx.00
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake(MBR)
	***/
	public String formatTextDoubleCommasTwoDecimal(String text) {

	String formattedText = "";
	try {
	if (text != "") {
	double amount = Double.parseDouble(text);
	DecimalFormat df = new DecimalFormat("#,##0.00");
	formattedText = df.format(amount);
	System.out.println(formattedText);
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return formattedText;
	}

	/***
	* Method to reformat numbers formatted as text to x,xxx.00
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake(MBR)
	***/
	public String formatTextDoubleTwoDecimal(String text) {

	String formattedText = "";
	try {
	if (text != "") {
	double amount = Double.parseDouble(text);
	DecimalFormat df = new DecimalFormat("#.00");
	formattedText = df.format(amount);
	System.out.println(formattedText);
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return formattedText;
	}

	/***
	* Method to reformat numbers formatted as text to 0.00
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake(MBR)
	***/
	public String formatTextDoubleFourDecimal(String text) {

	String formattedText = "";
	try {
	if (text != "") {
	double amount = Double.parseDouble(text);
	DecimalFormat df = new DecimalFormat("0.0000");
	formattedText = df.format(amount);
	System.out.println(formattedText);
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return formattedText;
	}

	/***
	* Method to reformat numbers formatted as text to 0.00
	* 
	* @param :
	*            String
	* @return : String
	* @author : Mel Rethlake(MBR)
	***/
	public String formatTextDoubleThreeDecimal(String text) {

	String formattedText = "";
	try {
	if (text != "") {
	double amount = Double.parseDouble(text);
	DecimalFormat df = new DecimalFormat("0.000");
	formattedText = df.format(amount);
	System.out.println(formattedText);
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return formattedText;
	}

	/***
	* Method to reformat numbers formatted as text to xx.00
	* 
	* @param :
	*            String to be formatted
	* @return : double
	* @author : Mel Rethlake (MBR)
	***/
	public double formatTextToTwoDecimalDouble(String text) {
	double amount = 0;
	try {
	if (text != "") {
	text = text.replaceAll(",", "");
	text = text.replaceAll("$", "");
	amount = Double.parseDouble(text);

	DecimalFormat df = new DecimalFormat("#.00");
	}
	} catch (RuntimeException ex) {
	report.updateTestLog("Format Data",
	report.addModal("Error Formatting (" + text.trim() + ") EXCEPTION CAUGHT", ex.toString()),
	Status.FAIL);
	}
	return amount;
	}
	
	// *****************************************************************************************************************//
	// Asserts TODO
	// *****************************************************************************************************************//

	/**
	* Assert Element text equals expected - value of webelement
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyTextEqual(WebElement element, String expectedText, String stepName) {
	try {
	Assert.assertTrue(element.getAttribute("value").equalsIgnoreCase(expectedText));
	report.updateTestLog("Verify " + stepName, "Text matches: Actual Value(" + element.getAttribute("value")
	+ ") Expected Value (" + expectedText + ")", Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName, "Text does NOT match: Actual Value("
	+ element.getAttribute("value") + ") Expected Value (" + expectedText + ")", Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}
	protected void verifyErrorEqualwithText(WebElement element, String expectedText, String stepName) {
	try {
	Assert.assertTrue(element.getText().equalsIgnoreCase(expectedText));
	report.updateTestLog("Verify " + stepName, "Text matches: Actual Value(" + element.getText()
	+ ") Expected Value (" + expectedText + ")", Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName, "Text does NOT match: Actual Value("
	+ element.getText() + ") Expected Value (" + expectedText + ")", Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}

	/**
	* Assert Element text equals expected after update - value of webelement
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyUpdateTextEqual(WebElement element, String expectedText, String stepName) {
	if (expectedText.contains("|")) {
	String text = formatUpdateData(expectedText);
	if (text.equalsIgnoreCase("Blank")) {
	} else {
	verifyTextEqual(element, text, stepName);
	}
	} else {
	verifyTextEqual(element, expectedText, stepName);
	}
	}

	/**
	* Assert Element text contains expected - value of webelement
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyTextContains(WebElement element, String expectedText, String stepName) {
	try {
	Assert.assertTrue(element.getAttribute("value").contains(expectedText.toLowerCase()));
	report.updateTestLog("Verify " + stepName, "Text matches: Actual Value(" + element.getAttribute("value")
	+ ") Expected Value (" + expectedText + ")", Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName, "Text does NOT match: Actual Value("
	+ element.getAttribute("value") + ") Expected Value (" + expectedText + ")", Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}

	/**
	* Assert Element text contains expected - value of webelement
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyUpdateTextContains(WebElement element, String expectedText, String stepName) {
	if (expectedText.contains("|")) {
	String text = formatUpdateData(expectedText);
	verifyTextContains(element, text, stepName);
	} else {
	verifyTextContains(element, expectedText, stepName);
	}
	}

	/**
	* Assert Element text equals expected - get text from element
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyDisplayedText(WebElement element, String expectedText, String stepName) {
	try {
	waitForElementAndProceed(element, 10);
	Assert.assertTrue(element.getText().equalsIgnoreCase(expectedText));
	report.updateTestLog("Verify " + stepName,
	"Text matches: Actual Value(" + element.getText() + ") Expected Value (" + expectedText + ")",
	Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName, "Text does NOT match: Actual Value(" + element.getText()
	+ ") Expected Value (" + expectedText + ")", Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}

	/**
	* Assert Element text contains expected - get text from element
	* 
	* @param element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyDisplayedTextContains(WebElement element, String expectedText, String stepName) {
	try {
	Assert.assertTrue(element.getText().contains(expectedText));
	report.updateTestLog("Verify " + stepName,
	"Text matches: Actual Value(" + element.getText() + ") Expected Value (" + expectedText + ")",
	Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName, "Text does NOT match: Actual Value(" + element.getText()
	+ ") Expected Value (" + expectedText + ")", Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}

	/**
	* Assert Element text contains expected - get text from element
	* 
	* @param element
	* @param String
	*            Expected text
	* @param String
	*            stepName
	*/
	protected void verifyUpdateDisplayedText(WebElement element, String expectedText, String stepName) {
	if (expectedText.contains("|")) {
	String text = formatUpdateData(expectedText);
	if (text != "( choose one )") {
	verifyDisplayedText(element, text, stepName);
	} else {
	verifyDisplayedText(element, text, stepName);
	}
	} else {
	verifyDisplayedText(element, expectedText, stepName);
	}
	}

	/**
	* Assert Element values (From text) equals expected -with tolerance (Delta)
	* - get text from attribute
	* 
	* @param Webelement
	*            element
	* @param String
	*            Expected Text
	* @param String
	*            stepName
	*/
	protected void verifyValueFromTextWithDelta(WebElement element, String expectedText, String stepName,
	double Delta) {
	String actualText = element.getText();
	Double actualDouble = formatTextToTwoDecimalDouble(actualText);
	Double expectedDouble = formatTextToTwoDecimalDouble(expectedText);

	try {
	Assert.assertEquals(expectedDouble, actualDouble, Delta);
	report.updateTestLog("Verify " + stepName,
	"Value matches: Actual Value(" + actualText + ") Expected Value (" + expectedText + ")",
	Status.PASS);
	} catch (AssertionError e) {
	e.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	"Value does NOT match: Actual Value(" + actualText + ") Expected Value (" + expectedText + ")",
	Status.FAIL);
	} catch (Exception ex) {
	ex.printStackTrace();
	report.updateTestLog("Verify " + stepName,
	report.addModal("Error verifying: EXCEPTION CAUGHT", ex.toString()), Status.FAIL);
	}
	}
	}
