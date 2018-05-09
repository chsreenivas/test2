package com.alliancetech.businessfunctions;




import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.sikuli.script.Screen;

import com.alliancetech.objectrepository.AttendeeJourneyOR;
import com.alliancetech.objectrepository.BadgePrintOR;
import com.alliancetech.objectrepository.CalenderGraphOR;
import com.alliancetech.objectrepository.CalenderViewOR;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.DataImports;
import com.alliancetech.objectrepository.EMT_AddAssociationsOR;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_AddSpeaker_OR;
import com.alliancetech.objectrepository.EMT_AddUserInManageUsers_OR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_LockAndUnlockUserAccount;
import com.alliancetech.objectrepository.EMT_LogInOR;
import com.alliancetech.objectrepository.EMT_LogOutOR;
import com.alliancetech.objectrepository.EMT_MyTabsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.EMT_RoomsOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.alliancetech.objectrepository.EMT_ViewOrEditUserOR;
import com.alliancetech.objectrepository.IL_CreateAndDeleteAnEventOR;
import com.alliancetech.objectrepository.IL_ImportExhibitorFile;
import com.alliancetech.objectrepository.MyAgendaOR;
import com.alliancetech.objectrepository.NarrowYourResultsOR;
import com.alliancetech.objectrepository.PersonalAgendaOR;
import com.alliancetech.objectrepository.SessionDetailsPopUPOR;
import com.alliancetech.objectrepository.SessionsDetailsOR;
import com.alliancetech.objectrepository.SessionsFilterOR;
import com.alliancetech.objectrepository.SessionsListOR;
import com.alliancetech.objectrepository.SessionsOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.alliancetech.objectrepository.YouHaveSelectedOR;
import com.cigniti.automation.accelerators.Actiondriver;
import com.cigniti.automation.accelerators.ActiondriverM;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;
import com.csvreader.CsvWriter;

public class BusinessFunctionsM extends ActiondriverM {

	public static List<WebElement> sessionElements;
	public static List<WebElement> totalelements;
	public static List<WebElement> unused;
	public static String te;
	public static String text;
	public static int recordnum;
	static Screen s=new Screen();
	public  static ExcelReader eReader;
	public static ArrayList<String> columnData;
	public static ArrayList<String> leftRightFields;
	public static ArrayList<String> unusedFieldNames;
	public static ArrayList<String> listItems;
	public static Robot robot;
	public static CsvWriter csvOutput;
	static String survey_answer="Answer testing";
	
	/**
	 * This function is used to Log-in into Portal application
	 * @param uname(username)- reads username from the Excel file
	 * @param password(password)- reads password from the Excel file
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean logIn(String uname, String password) throws Throwable
	{

		boolean flag= true;
		if(isElementPresent(MyAgendaOR.txtUserName, "User Name Text Box")){
			type(MyAgendaOR.txtUserName, uname, "User Name");
			type(MyAgendaOR.txtPassword, password, "Password");
			click(MyAgendaOR.btnSignIn, "SignIn");
		}
		else{
			flag=false;
		}
		return flag;
	}


	/**
	 * This function is used to Refresh the web page
	 */

	public static void refresh(){

		driverM.findElement(By.xpath("//html")).sendKeys(Keys.F5);
	}

	public static boolean verifyPrintIconInMyAgenda() throws Throwable
	{
		boolean flag=true;
		if(!isElementPresent(MyAgendaOR.btnPrintIconInMyAgenda, "Print icon"))
		{
			flag=false;
		}
		if(!click(MyAgendaOR.btnPrintIconInMyAgenda,"Print Icon"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.txtPrintPopUpTitleInMyAgenda, "Print pop up Title"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.chckboxFullVersionOfPrintPopUpInMyAgenda, "Full Version Check box"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.chckboxSummaryOfPrintPopUpInMyAgenda, "Summary check box"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.btnSendOfPrintPopUpInMyAgenda, "Send Button"))
		{
			flag=false;
		}
		if(!click(MyAgendaOR.btnCloseOfPrintPopUpInMyAgenda,"Close Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyEmailIconInMyAgenda() throws Throwable
	{
		boolean flag=true;
		if(!isElementPresent(MyAgendaOR.btnEmailIconInMyAgenda, "Email Icon"))
		{
			flag=false;
		}
		if(!click(MyAgendaOR.btnEmailIconInMyAgenda,"Email Icon"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.chckboxEmailPopUpIDInMyAgenda, "Email id checkbox"))
		{
			flag=false;
		}
		//few more validations need to be added
		return flag;
	}

	static String sessionStartDate;
	static String calendergraphdate;
	static String year;

	public static boolean getCalenderGraphDate() throws Throwable
	{
		boolean flag=true;
		//SimpleDateFormat formatter=new SimpleDateFormat("d,MM,yyyy");
		SimpleDateFormat Format=new SimpleDateFormat("E, MMM d yyyy");
		calendergraphdate=getText(By.xpath("//tr[@class='fc-first fc-last']/th[2]/a"), "First date in calender graph");
		String dateInCalendergraph=driverM.findElement(By.xpath("//tr[@class='fc-first fc-last']/th[2]/a")).getAttribute("href");
		String[] dateOnWebPage=dateInCalendergraph.split(",");
		System.out.println(dateOnWebPage[2].replace(")", ""));
		year = dateOnWebPage[2].replace(")", "");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String dateInString = calendergraphdate+" "+year;
			Date date = Format.parse(dateInString);
			sessionStartDate=output.format(date);
			System.out.println(sessionStartDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * This function is used to verify My Agenda Screen elements
	 * MEAL HOURS,EXPO HOURS,General Sessions & Key notes tabs
	 * Sessions,Meeting and Personal Events Filter
	 * Add Sessions, Add Meeting, Add Personal Events, Print, Email and Download icons.
	 * Week and Day options.
	 * My Personal Agenda (selected by default) and Exhibitors Agenda Items links.
	 * Calendar graph
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean verifyMyAgendaScreen() throws Throwable 
	{

		click(MyAgendaOR.lnkMyAgendaIcon, "");
		waitForElementPresent(MyAgendaOR.tabMealHours);
		verifyText(MyAgendaOR.tabMealHours,  "Meal Hours", "MEALHOURS PRESENT");
		verifyText(MyAgendaOR.tabExpoHours,  "Expo Hours", "ExpoHOURS PRESENT");
		verifyText(MyAgendaOR.tabGeneralSessions,  "General Sessions & Keynotes", "GENERALSESSIONS PRESENT");
		verifyText(MyAgendaOR.chckFilterSessions,  "Sessions", "Sessions Present");
		verifyText(MyAgendaOR.lnkAddSessions,  "Sessions", "AddSessions Present");
		verifyText(MyAgendaOR.chckFilterMeetings,  "Meeting", "Meeting Present");
		verifyText(MyAgendaOR.lnkAddMeetings,  "Meeting", "AddMeeting Present");
		verifyTextPresent("Open my full agenda (pdf).");
		verifyTextPresent("Email me my full agenda!");
		verifyTextPresent("Download full agenda in an .ics calendar file! Exchange users have to save and import this file.");
		verifyText(MyAgendaOR.txtDayView,  "Day", "Day Present");
		verifyTextPresent("Week");
		verifyText(MyAgendaOR.txtMyPersonalAgenda,  "My Personal Agenda", "MyPersonalAgenda Present");
		verifyText(MyAgendaOR.txtExibhitorAgenda,  "Exhibitor Agenda Items", "ExhibitorAgenda Present");

		if(!driverM.findElement(MyAgendaOR.calender).isDisplayed())
		{
			Reporters.failureReport("calendar", "not displayed");
		}

		return true;
	}


	/**
	 * This function is used to verify Meal Hours tab
	 * Displays Hours details information for Breakfast, Lunch and Receptions.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean verifyMyAgendaMealHours() throws Throwable 
	{
		click(MyAgendaOR.tabMealHour, "Meal Hour present");
		verifyText(MyAgendaOR.txtBreakfast, "Breakfast", "Breakfast present");
		// To Scroll  Down
		Actions action=new Actions(driverM);
		action.sendKeys(Keys.PAGE_DOWN).perform();
		verifyText(MyAgendaOR.txtLunch, "Lunch", "Lunch present");
		verifyText(MyAgendaOR.txtReception, "Receptions", "Receptions present");
		return true;
	}

	/**
	 * This Function is used to verify Expo Hours tab
	 * Displays Hours details information for 'EXPO is accessible' and 'EXPO is fully staffed'
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean verifyMyAgendaExpoHours() throws Throwable 
	{
		Actions action=new Actions(driverM);
		click(MyAgendaOR.tabExpoHour, "Expo Hour clicked");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		verifyText(MyAgendaOR.txtExpoAcess, "EXPO is accessible", "Lunch present");
		verifyText(MyAgendaOR.txtExpoFullySatisfied, "EXPO is fully staffed", "Receptions present");
		return true;
	}

	/** 
	 * This Function is used to verify General Sessions & Key notes Tab
	 * Displays Hour and title information of the sessions
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean verifyMyAgendaGeneralSessions() throws Throwable 
	{
		Actions action=new Actions(driverM);
		click(MyAgendaOR.tabGeneralSession, "General Sessions & Keynotes clicked");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		verifyText(MyAgendaOR.satgeneral, "Saturday", "Saturday present");
		action.sendKeys(Keys.PAGE_UP).perform();
		click(MyAgendaOR.tabGeneralSession, "General Sessions & Keynotes clicked");

		return true;
	}

	/**
	 * This function is used to verify session filter when sessions are unchecked
	 * Sessions should not be displayed in the calendar graph
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean sessionFilterUncheckSession() throws Throwable {

		click(MyAgendaOR.lnkMyAgendaIcon, "MyAgenda");
		ImplicitWait();
		sessionElements = driverM.findElements(PersonalAgendaOR.session);
		totalelements = driverM.findElements(PersonalAgendaOR.totalelement);
		click(MyAgendaOR.chckSession, "Unckeck sessions");
		Thread.sleep(2000);
		List<WebElement> updatedElements = driverM
				.findElements(PersonalAgendaOR.totalelement);
		if ((totalelements.size() - sessionElements.size()) == updatedElements
				.size()) {
			Reporters.SuccessReport("Sessions are ", "not available");
		}
		return true;
	}


	/**
	 * This function is used to verify session filter when sessions are checked
	 * Sessions should be displayed in the calendar graph
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sessionFilterCheckSession() throws Throwable {

		click(MyAgendaOR.chckSession, "Sessions");

		List<WebElement> updatedElements = driverM
				.findElements(PersonalAgendaOR.totalelement);
		Thread.sleep(5000);
		BusinessFunctionsM.refresh();
		updatedElements = driverM.findElements(PersonalAgendaOR.totalelement);
		if ((totalelements.size()) == updatedElements.size()) 
		{
			Reporters.SuccessReport("Sessions are ", "not available");
		}
		return true;

	}


	/**
	 * Verification of Add Sessions icon
	 * Sessions Page should open
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean clickAddSessionIconInMyAgenda() throws Throwable {

		driverM.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		waitForElementPresent(PersonalAgendaOR.addAgenda);
		click(PersonalAgendaOR.addAgenda, "Add '+' icon");
		click(PersonalAgendaOR.btnclose, "Close");
		return true;

	}

	public static boolean advanceSearchForaSession(String Title) throws Throwable
	{
		boolean flag=true;
		if(!click(XML_OR.lnkAdvanceSearch, "Advance Settings link"))
		{
			flag=false;
		}
		if(!type(XML_OR.txtAdvanceSearch,Title,"Session title"))
		{
			flag=false;
		}
		if(!click(XML_OR.btnSearch,"Search Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean advancedSearchErrorMessageValidation() throws Throwable
	{
		boolean flag=true;
		
		if(!click(XML_OR.lnkAdvanceSearch, "Advance Settings link"))
		{
			flag=false;
		}
		if(!type(XML_OR.txtAdvanceSearch,"a","Session title"))
		{
			flag=false;
		}
		if(!click(XML_OR.btnSearch,"Search Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(MyAgendaOR.txtAdvancedSearchErrorMessage, "Error Message");
		/*if(!verifyText(MyAgendaOR.txtAdvancedSearchErrorMessage, "Keyword search must be greater or equal to 2 characters", "Error Pop Up window"));
		{
			flag=false;
		}*/
		if(!click(MyAgendaOR.btnErrorPopUpOK,"OK Button in Advanced Search Error Pop Up"))
		{
			flag=false;
		}
		if(!click(CommonOR.tabToBeClicked("Sessions"),"Sessions tab"))
		{
			flag=false;
		}
		if(!click(PersonalAgendaOR.btnclose, "Close"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean addSessionToMyAgenda(String Title,String StartTime) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(MyAgendaOR.btnAddToAgenda,"Add To Agenda Button"))
		{
			flag=false;
		}
		if(!click(MyAgendaOR.btnAddToAgenda,"Add To Agenda"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(MyAgendaOR.txtSuccessMessageOfAddSession, "Session added to agenda!!"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(CommonOR.tabToBeClicked("My Agenda"), "My Agenda"))
		{
			flag=false;
		}
		if(!js_click(CommonOR.tabToBeClicked("My Agenda"),"My Agenda Tab"))
		{
			flag=false;
		}
		Thread.sleep(5000);
		waitForVisibilityOfElement(By.id("calendar"), "Calendar");
		String[] starttime=StartTime.split(" ");
		if(!isElementPresent(By.xpath("//a[@title=' "+Title+"']/span[contains(text(),'"+starttime[0]+"')]"), "Session"))
		{
			flag=false;
		}
		if(!click(By.xpath("//a[@title=' "+Title+"']/span[contains(text(),'"+starttime[0]+"')]"),"Session"))
		{
			flag=false;
		}
		return flag;
	}



	public static boolean removeSessionFromMyAgenda(String Title,String StartTime) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(MyAgendaOR.btnRemoveFromAgenda,"Remove From Agenda Button"))
		{
			flag=false;
		}
		if(!click(MyAgendaOR.btnRemoveFromAgenda,"Remove From Agenda"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(MyAgendaOR.txtSuccessMessageOfRemoveSession, "Session removed from agenda!!"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(CommonOR.tabToBeClicked("My Agenda"), "My Agenda"))
		{
			flag=false;
		}
		if(!js_click(CommonOR.tabToBeClicked("My Agenda"),"My Agenda Tab"))
		{
			flag=false;
		}
		Thread.sleep(5000);
		waitForVisibilityOfElement(By.id("calendar"), "Calendar");
		driverM.getPageSource().contains(Title);
		return flag;
	}

	public static boolean verifyViewSessionDetails(String Title,String StartTime,String EndTime) throws Throwable
	{
		boolean flag=true;

		if(!waitForFrameToLoadAndSwitchToIt(MyAgendaOR.frameSessionDetailsPopUp,"wait for frame to load and switch to it" ))
		{
			flag=false;
		}
		if(!waitForElementPresent(MyAgendaOR.txtDetailsInSessionDetailsPopUp))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.txtDetailsInSessionDetailsPopUp, "Details"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.txtDescriptionInSessionDetailsPopUp, "Description"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.lnkEmailInSessionDetailsPopUp, "Email Session Details link"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.lnkPrintInSessionDetailPopUp, "Print Session Details link"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.lnkDownloadSessionDetailInPopUp, "Download Session details link"))
		{
			flag=false;
		}
		if(!isElementPresent(MyAgendaOR.btnAddToAgendaInSessionDetailsPopUp, "Add To Agenda button in Session Details Pop Up"))
		{
			flag=false;
		}
		String sessionTitle=getText(MyAgendaOR.txtSessionTitleFromSessionDetailsPopUp, "").trim();
		System.out.println(sessionTitle);
		System.out.println(Title);
		if(sessionTitle.equals(Title))
		{
			Reporters.SuccessReport("Verify Session Title in Session Details Pop Up", "Session Title is matched");
		}
		else
		{
			Reporters.failureReport("Verify Session Title in Session Details Pop Up", "Session Title is not matched");
		}
		String session_date_time=getText(MyAgendaOR.txtSessionDate, "Session Date and Time").trim();
		System.out.println(session_date_time);
		String [] timeInSessionDetailsPopUp=session_date_time.split("\n");   
		System.out.println(timeInSessionDetailsPopUp[1]); 
		String actualTime=StartTime+" "+"-"+" "+EndTime;
		System.out.println(actualTime);
		if(timeInSessionDetailsPopUp[1].equals(actualTime))
		{
			Reporters.SuccessReport("Verify Session Time in Session Details Pop Up", "Session time is matched");
		}
		else
		{
			Reporters.failureReport("Verify Session Time in Session Details Pop Up", "Session Time is not matched");
		}
		System.out.println(timeInSessionDetailsPopUp[0]);
		System.out.println(calendergraphdate);
		String actualdate=calendergraphdate+","+" "+year;
		System.out.println(actualdate);
		if(timeInSessionDetailsPopUp[0].equals(actualdate))
		{
			Reporters.SuccessReport("Verify Session Date in Session Details Pop Up", "Session date is matched");
		}
		else
		{
			Reporters.failureReport("Verify Session date in Session Details Pop Up", "Session Date is not matched");
		}
		driverM.switchTo().defaultContent();
		if(!click(MyAgendaOR.btnCloseForSessionDetailsPopUp,"Close Button"))
		{
			flag=false;
		}
		return flag;
	}
	/**
	 * Verify if a session is added to My Agenda in Sessions Tab
	 * Session should display in the Calendar graph.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean addSessionDisplay() throws Throwable {

		te = driverM.findElement(PersonalAgendaOR.agendaName).getText().toString();
		click(PersonalAgendaOR.addSessionToAgenda,"'Add To Agenda' button");
		click(PersonalAgendaOR.myAgenda, "Go to My Agenda");
		sessionElements = driverM.findElements(PersonalAgendaOR.session);
		checkSession("added");
		click(PersonalAgendaOR.addAgenda, "Add '+' icon");
		click(PersonalAgendaOR.btnclose, "Close");
		return true;

	}

	/**
	 * Verify if a session is removed from My Agenda in Sessions Tab
	 * Session should not display in the Calendar graph.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean removeSession() throws Throwable {

		te = driverM.findElement(PersonalAgendaOR.agendaName).getText().toString();
		click(PersonalAgendaOR.removeAgenda, "Remove session");
		click(PersonalAgendaOR.myAgenda, "Go to My Agenda Agenda");
		sessionElements = driverM.findElements(PersonalAgendaOR.session);
		checkSession("remove");
		return true;

	}

	/**
	 * Verification of Print Icon
	 * Print pop up is displayed with elements
	 * "Full Or Summary Version" title.
	 * Full Version and Summary radio buttons.
	 * Print button.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean printOperation() throws Throwable {

		click(MyAgendaOR.imgPrint, "Print");
		waitForElementPresent(PersonalAgendaOR.printPopupName);
		getText(PersonalAgendaOR.printPopupName, "Full Or Summary Version");
		getText(PersonalAgendaOR.fullVersion, "Full Version");
		getText(PersonalAgendaOR.Summary, "Summary");
		getText(PersonalAgendaOR.printButton, "Print");
		return true;

	}

	/**
	 * Verification of Full Version in Print Pop up
	 * PDF with full version of the items added in the calendar graph should be printed
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean printFullVersion() throws Throwable {

		waitForElementPresent(PersonalAgendaOR.printButton);
		click(PersonalAgendaOR.printButton,"print button ");
		s.click("Logos\\1392359627312.png",50);
		return true;
	}

	/**
	 * Verification of Summary in Print Pop up
	 * PDF with summary version of the items added in the calendar graph should be printed
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean printSummary() throws Throwable {

		click(MyAgendaOR.imgPrint, "Print");
		click(PersonalAgendaOR.Summary,"Summary in print pop up");
		click(PersonalAgendaOR.printButton,"print button ");
		s.click("Logos\\1392359627312.png", 50);
		return true;

	}

	/**
	 * Verification of Email icon
	 * Email pop up is displayed with elements
	 * "Select preferred email address" title
	 * User email, Full Version and Summary radio buttons.
	 * Send button
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean emailOperation() throws Throwable
	{
		click(PersonalAgendaOR.emailOperation,"email");
		getText(PersonalAgendaOR.emailPopUpName, "Select preferred email address");
		getText(PersonalAgendaOR.emailuserEmail, "jseifert@alliancetech.com");
		getText(PersonalAgendaOR.emailFullVersion, "Full Version");
		getText(PersonalAgendaOR.emailSummary, "Summary");
		getText(PersonalAgendaOR.emailSend, "Send");

		return true;
	}

	/**
	 * Verification of Full Version in Email Pop up
	 * PDF with full version of the items added in the calendar graph should be sent.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean emailFullVersion() throws Throwable
	{
		waitForElementPresent(PersonalAgendaOR.emailFullVersion);
		click(PersonalAgendaOR.emailFullVersion,"Full Version in email Pop Up");
		waitForElementPresent(PersonalAgendaOR.emailSend);
		click(PersonalAgendaOR.emailSend,"Send");
		s.doubleClick("Logos\\email.png", 50);
		Thread.sleep(3000);
		return true;
	}

	/**
	 * Verification of Summary in Email Pop up
	 * PDF with summary version of the items added in the calendar graph should be sent.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean emailSummary() throws Throwable
	{

		click(PersonalAgendaOR.emailOperation,"email");
		waitForElementPresent(PersonalAgendaOR.emailSummary);
		click(PersonalAgendaOR.emailSummary,"Summary in email Pop up");
		waitForElementPresent(PersonalAgendaOR.emailSend);
		click(PersonalAgendaOR.emailSend,"Send");
		s.doubleClick("Logos\\email.png", 50);
		Thread.sleep(3000);
		return true;

	}

	/**
	 * Verification of Download icon
	 * Download pop up is displayed with elements
	 * "Download Scheduled Event" title.
	 * Download To Calendar and Email as an Attachment buttons.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean downloadOperation() throws Throwable
	{
		waitForElementPresent(PersonalAgendaOR.downloadButton);
		click(PersonalAgendaOR.downloadButton,"Download icon ");
		getText(PersonalAgendaOR.downloadPopUpName,"Download Scheduled Event");
		getText(PersonalAgendaOR.downloadToCalenderButton,"Download To Calendar");
		getText(PersonalAgendaOR.emailAsAttachmentButton,"Email as an Attachment");
		return true;
	}


	/**
	 * Verification of Download to Calender in Download Pop up
	 * Calendar graph should download to the system calendar
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean downloadToCalender() throws Throwable
	{

		click(PersonalAgendaOR.downloadToCalenderButton,"Download To Calendar");
		s.click("Logos\\1392359627312.png", 50);
		return true;
	}


	/**
	 * Verification of Email As An Attachment in Download Pop up
	 * 'To' section is displayed with elements
	 * User email added by default.
	 * Click here to add additional link
	 * Send Event button
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean emailAsAttachemntOperation() throws Throwable
	{
		click(PersonalAgendaOR.downloadButton,"Download icon");
		click(PersonalAgendaOR.emailAsAttachmentButton,"Email As Attachment button");
		getText(PersonalAgendaOR.downloadScheduledEventPopUpName, "Download Scheduled Event");
		getText(PersonalAgendaOR.emailAsAttachmentUserEmail,"jseifert@alliancetech.com");
		getText(PersonalAgendaOR.addAdditionalLink,"Click here to add additional");
		getText(PersonalAgendaOR.sendEvent,"Send Event");
		return true;
	}


	/**
	 * Verification of 'To' section in Email As An Attachment
	 * Calendar graph should be sent to the email provided.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean emailAsAttachmentToCalender() throws Throwable
	{

		click(PersonalAgendaOR.sendEvent,"Send Event");
		s.doubleClick("Logos\\email.png", 50);
		try{
			WindowsUtils.killByName("OUTLOOK.EXE*32");
		}
		catch(Exception e)
		{
		}

		return true;
	}


	/**
	 * Verification of Week option
	 * Calendar graph should display the elements of all the week.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean weekVerification() throws Throwable
	{
		boolean val;
		int i,count=0;
		click(PersonalAgendaOR.myAgenda, "Go to My Agenda");

		List<WebElement> cols=driverM.findElements(By.xpath("//tr[@class='fc-first fc-last']"));
		for(WebElement c : cols){
			List<WebElement> colItems=c.findElements(By.tagName("th"));   
			for(i=0;i<7;i++)
			{
				String dayCheck= colItems.get(i+1).getText(); 
				System.out.println(dayCheck);
				count++;
			}
		}
		if(count==7)
		{
			val=true;
		}
		else
		{
			val=false;
		}
		return val;
	}

	/**
	 * Verification of Day option
	 * Calendar graph should display the elements of all the day.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean dayVerification() throws Throwable
	{
		boolean val;
		int i,count=0;
		waitForElementPresent(PersonalAgendaOR.daySelection);
		click(PersonalAgendaOR.daySelection,"Day selection");
		WebElement table=driverM.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div[3]/div[2]/div[2]/div[1]/table/tbody"));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		for(WebElement r : rows){

			List<WebElement> colItems=r.findElements(By.tagName("th"));   
			for(i=0;i<1;i++)
			{
				String dayCheck= colItems.get(i+1).getText(); 
				System.out.println(dayCheck);
				count++;
			}

		}

		if(count==1)
		{
			val=true;
		}
		else
		{
			val=false;
		}
		return val;
	}

	/**
	 * Verification of Session selected from Calendar graph
	 * Session Details pop-up should be displayed
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean sessionDetailsPopUp() throws Throwable
	{
		click(MyAgendaOR.lnkMyAgendaIcon, "MyAgenda");
		waitForElementPresent(PersonalAgendaOR.sessionList);
		List<WebElement> sessionLists = driverM.findElements(PersonalAgendaOR.sessionList);
		if(sessionLists.size()>0)
		{
			click(PersonalAgendaOR.weekSelection,"Week");
			waitForElementPresent(PersonalAgendaOR.lnkSessionDetails);
			click(PersonalAgendaOR.lnkSessionDetails, "Session in calender graph");
			waitForElementPresent(PersonalAgendaOR.sessionDetailsPopUpName);
			getText(PersonalAgendaOR.sessionDetailsPopUpName, "Session Details");
		}

		else
		{
			System.out.println("Sessions are not present in calender graph");
		}
		return true;
	}


	/**
	 * Verification of Session Details pop up
	 * Session Details pop-up is displayed with elements
	 * Session Information (details, description, speakers)
	 * Remove from Agenda button.
	 * Email Session Detail, Print Session Detail and Download Session Detail links.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sessionDetailsPopUpValidation() throws Throwable
	{
		switchToFrameByIndex(0);
		getText(CalenderGraphOR.txtDetails, "Details");
		getText(CalenderGraphOR.txtDescription,"Description");
		getText(CalenderGraphOR.txtSpeakers,"Speaker(s)");
		getText(CalenderGraphOR.btnRemoveFromAgenda,"Remove From Agenda");
		getText(CalenderGraphOR.lnkEmailSessionDetail,"Email Session Detail");
		getText(CalenderGraphOR.lnkPrintSessionDetail,"Print Session Detail");
		getText(CalenderGraphOR.lnkDownloadSessionDetail,"Download Session Detail");
		driverM.switchTo().defaultContent();
		click(CalenderGraphOR.btnClosePopUp,"Close pop up");
		return true;
	}


	//iConnect-Sessions(Sessions)
	/**
	 * This function verifies Session Icon
	 * Session Tab is opened with Select Area pop-up displayed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sessionsTab() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions Tab");
		getText(SessionsOR.txtSelectAreaPopUp,"Select Area pop up is displayed");
		return true;
	}


	/**
	 * This function verifies Session tab when opened First Time
	 * Select Area pop-up is displayed with elements
	 * List of Areas with sub-areas.
	 * Continue without a selection button.
	 * Don't show this again checkbox. 
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean selectAreaPopUpValidation() throws Throwable
	{
		getText(SessionsOR.txtLeftArea, "Left area is displayed");
		getText(SessionsOR.txtLeftSubArea, "Left sub area is displayed");
		getText(SessionsOR.txtRightArea, "Right area is displayed");
		getText(SessionsOR.txtRightSubArea, "Right sub area is displayed");
		getText(SessionsOR.chckDontShowThisMessage, "Dont show this message check box displayed");
		getText(SessionsOR.btnContinueWithoutSelection, "Continue Without Selection displayed");
		return true;
	}

	/**
	 * This function verifies Area and Sub area in Select Area Pop Up
	 * Sessions Tab is displayed with elements
	 * Sessions and Drop-In-Labs filter.
	 * Advanced Search link.
	 * You've Selected and Narrow Your Results sections.
	 * Calendar View button.
	 * Sessions List with sessions matching with area and sub-area.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean selectAreaAndSubArea() throws Throwable
	{

		click(SessionsOR.selectSubArea,"Sub Area under an area");
		String sub_area=getText(SessionsOR.selectSubArea, "sub area in Select Area pop up");
		getText(SessionsOR.txtSessions,"Sessions");
		getText(SessionsOR.txtDropInLabs,"Drop-In-Labs");
		getText(SessionsOR.txtAdvancedSearch,"Advanced Search");
		getText(SessionsOR.txtYouveSelected,"You've Selected");
		getText(SessionsOR.txtNarrowYourResults,"Narrow Your Results");
		getText(SessionsOR.btnCalenderView, "calender View button");
		waitForElementPresent(SessionsOR.txtSubArea);
		String Sub_area_name= getText(SessionsOR.txtSubArea, "Selected sub area name under You've selected section");
		if(Sub_area_name.contains(sub_area))
		{
			System.out.println("Sessions displayed related to selected sub-area");
		}
		else
		{
			return false;
		}
		click(SessionsOR.lnkClearAll,"Clear All link beside You've Selected section");
		return true;
	}

	/**
	 * This function verifies Continue without a selection button in Select Area pop-up
	 * Sessions Tab is displayed with elements
	 * Sessions and Drop-In-Labs filter
	 * Advanced Search link
	 * You've Selected and Narrow Your Results sections.
	 *  Sessions List with all sessions registered in the server
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean clickContinueWithoutSelection() throws Throwable
	{
		click(SessionsOR.lnkSessionsTab,"Sessions tab at the top of the page");
		waitForElementPresent(SessionsOR.btnContinueWithoutSelection);
		click(SessionsOR.btnContinueWithoutSelection, " Continue Without Selection");
		getText(SessionsOR.txtSessions,"Sessions");
		getText(SessionsOR.txtDropInLabs,"Drop-In-Labs");
		getText(SessionsOR.txtAdvancedSearch,"Advanced Search");
		getText(SessionsOR.txtYouveSelected,"You've Selected");
		getText(SessionsOR.txtNarrowYourResults,"Narrow Your Results");
		getText(SessionsOR.listSessions, "All sessions displayed");
		return true;
	}

	/**
	 * This function verifies Don't show this again checkbox in Select Area pop-up
	 * Select Area pop-up should not be displayed the next time when user go to Sessions Tab.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean checkDontShowThisMessage() throws Throwable
	{
		click(SessionsOR.lnkSessionsTab,"Sessions tab at the top of the page");
		click(SessionsOR.chckDontShowThisMessage, "Dont show this message check box");
		click(SessionsOR.lnkSessionsTab,"Sessions tab on the top");
		verifyTextNotPresent("Start by selecting an area to search by...");
		return true;
	}

	//iConnect-Sessions(Sessions Filters)
	/**
	 * This function verifies the Session Filter
	 * Sessions Tab should display the sessions list according to the actual search.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean selectSessionsFilter() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		click(PersonalAgendaOR.btnclose, "Close");
		waitForElementPresent(SessionsOR.listSessions);
		getText(SessionsOR.listSessions, "All sessions displayed");
		return true;
	}

	/**
	 * This function verifies the Drop-In-Labs Filter
	 * Sessions Tab should display the Drop-In-Labs list.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean selectDropInLabsFilter() throws Throwable
	{
		click(SessionsOR.txtDropInLabs,"Drop-In-Labs Filters under Sessions section");
		waitForElementPresent(SessionsFilterOR.listDropInLabs);
		getText(SessionsFilterOR.listDropInLabs, "Drop-In-Labs List displayed");
		return true;
	}


	/**
	 * This function verifies the Advanced Search Link
	 * Advance Search Tab should be displayed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean selectAdvancedSearchFilter() throws Throwable
	{
		waitForElementPresent(SessionsFilterOR.lnkAdvancedSearch);
		click(SessionsFilterOR.lnkAdvancedSearch,"Advanced Search Filters under Sessions section");
		waitForElementPresent(SessionsFilterOR.advancedSearchTab);
		getText(SessionsFilterOR.advancedSearchTab, "Advanced Search tab is displayed");
		return true;
	}

	//iConnect-Sessions(You've Selected)


	/*public static boolean sameSessionSearch() throws Throwable
		{
			return true;
		}*/


	/**
	 * This function verifies 'x' icon
	 * You've Selection section should be empty and Sessions List should display all sessions registered in the server.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean select_x_icon() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		click(PersonalAgendaOR.btnclose, "Close");
		getText(YouHaveSelectedOR.txtEmptyYouHaveSelected, "You've Selected section is empty");
		getText(SessionsOR.listSessions, "All sessions displayed");
		return true;
	}


	/**
	 * This function verifies Clear All link
	 * You've Selection section should be empty and Sessions List should display all sessions registered in the server.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean selectClearAll() throws Throwable
	{
		click(SessionsOR.lnkSessionsTab,"Sessions tab at the top of the page");
		click(SessionsOR.selectSubArea,"Sub Area is selected under an area");
		waitForElementPresent(SessionsOR.lnkClearAll);
		click(SessionsOR.lnkClearAll,"Clear All link beside You've Selected section");
		getText(YouHaveSelectedOR.txtEmptyYouHaveSelected, "You've Selected section is empty");
		getText(SessionsOR.listSessions, "All sessions displayed");
		return true;
	}

	//iConnect-Sessions(Narrow your Results)
	/**
	 * This function verifies Narrow Your Results section
	 * Sessions List should display sessions matching with the selection made in Narrow Your Results
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean updatedYouHaveSelected() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		click(PersonalAgendaOR.btnclose, "Close");
		click(NarrowYourResultsOR.lnkNarrowYourResultsOption,"Select an option under Narrow your results section");
		waitForElementPresent(NarrowYourResultsOR.lnkNarrowYourResultsOption);
		String option=getText(NarrowYourResultsOR.lnkNarrowYourResultsOption, "returns option name selected under Narrow your results section");
		waitForElementPresent(SessionsOR.txtSubArea);
		String option_name=getText(SessionsOR.txtSubArea, "option name updated in You've Have Selected section");
		if(option_name.contains(option))
		{
			System.out.println("You've selected section got updated");
		}
		else
		{
			return false;
		}
		return true;
	}


	/**
	 * This function verifies the updation of You've Selected section
	 * You've Selected section should show new search executed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean updatedSessionList() throws Throwable
	{
		click(SessionsOR.lnkClearAll,"Clear All link beside You've Selected section");
		click(SessionsOR.lnkSessionsTab,"Sessions tab at the top of the page");
		click(PersonalAgendaOR.btnclose, "Close");
		click(NarrowYourResultsOR.lnkNarrowYourResultsOption,"Select an option under Narrow your results section");
		getText(NarrowYourResultsOR.lstSession, "Sessions got updated");
		return true;
	}

	//iConnect-Sessions(Sessions List)
	/**
	 * This function verifies the Sessions List elements
	 * Sessions List is displayed with elements
	 * Display N records option
	 * Previous and Next links
	 * Page N of N option
	 * Sessions details
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sessionListElements() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		click(PersonalAgendaOR.btnclose, "Close");
		getNRecords();
		getText(SessionsListOR.txtNRecords, "Display N records option");
		getText(SessionsListOR.lnkPrevious, "Previous link");
		getText(SessionsListOR.lnkNext, "Next link");
		getText(SessionsListOR.txtPageOption, "Page N of N option");
		getText(SessionsListOR.lstSessionsDetails, "Session details");
		return true;
	}

	/**
	 * This function verifies Display N records option
	 * When Display N records option is updated, the change should be reflected in the Sessions List.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean nRecordsDisplay() throws Throwable
	{
		click(SessionsListOR.ddRecords,"Drop down icon");
		click(SessionsListOR.nRecords,"Choose the number of records to display");
		int num=getNRecords();			
		int sessioncount=driverM.findElements(By.xpath("//div[@id='sess-results']/div")).size();
		if(sessioncount<=num)
		{
			System.out.println("Sessions list is updated");
		}
		else
		{
			return false;
		}
		return true;
	}

	/**
	 * This function returns the number of records
	 * @return integer(Number of Records)
	 * @throws Throwable
	 */
	public static int getNRecords() throws Throwable{
		waitForElementPresent(SessionsListOR.txtNRecords);
		String number=getText(SessionsListOR.txtNRecords, "Returns number of records displayed");
		recordnum=Integer.parseInt(number);
		return recordnum; 
	}

	/**
	 * This function verifies the Next button
	 * When Next button is selected, the change should be reflected in the Sessions List.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean nextButton() throws Throwable
	{
		String page_num_1=getAttribute(SessionsListOR.valuePageNumber, "value", "returns current page number");
		click(SessionsListOR.lnkNext,"Click on Next Button");
		String page_num_2=getAttribute(SessionsListOR.valuePageNumber, "value", "returns current page number");
		int first=Integer.parseInt(page_num_1);
		int second=Integer.parseInt(page_num_2);
		getNRecords();
		if(first<=second)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This function verifies the Previous button
	 * When Previous button is selected, the change should be reflected in the Sessions List.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean previousButton() throws Throwable
	{

		String page_num_1=getAttribute(SessionsListOR.valuePageNumber, "value", "returns current page number");
		click(SessionsListOR.lnkPrevious,"Click on previous Button");
		String page_num_2=getAttribute(SessionsListOR.valuePageNumber, "value", "returns current page number");
		int first=Integer.parseInt(page_num_1);
		int second=Integer.parseInt(page_num_2);
		getNRecords();
		if(first>=second)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * This function verifies Page N of N option
	 * When Page N of N option is updated, the change should be reflected in the Sessions List.
	 * @param pageOption(Page number to be updated)
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean updatePageOption(String pageOption) throws Throwable
	{

		click(SessionsListOR.valuePageNumber,"Page option");
		type(SessionsListOR.valuePageNumber,pageOption, "Update page option");
		Actions action = new Actions(driverM);
		action.sendKeys(Keys.RETURN);
		action.perform();
		int num=getNRecords();
		int sessioncount=driverM.findElements(By.xpath("//div[@id='sess-results']/div")).size();
		if(sessioncount<=num)
		{
			System.out.println("Sessions list is updated");
		}
		else
		{
			return false;
		}
		return true;

	}

	//iConnect-Sessions(Session Details Pop Up)

	public static boolean ClickSessionIconInPortal() throws Throwable{

		click(SessionsOR.lnkSessoinsicon, "Click on session icon");
		verifyTextPresent("Start by selecting an area to search by...");
		click(SessionsOR.btnContinueWithoutSelection, "Continue Without Selection");
		// waitForElementPresent(By.xpath("//span[@class='bold']"));
		return true;

	}
	/**
	 * This function verifies Sessions Details elements
	 * Sessions Details are displayed with elements
	 * Session Information (details, description, speakers)
	 * Add to Agenda button.
	 *  Email Session Detail, Print Session Detail and Download Session Detail links.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sessionDetails() throws Throwable
	{
		boolean val=false;
		int i,countval=0;
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		click(PersonalAgendaOR.btnclose, "Close");
		waitForElementPresent(SessionDetailsPopUPOR.txtSession);
		String var1=getText(SessionDetailsPopUPOR.txtSessionTitle, "get session tile");
		List<WebElement> speakers=driverM.findElements(By.xpath("//*[@id='session-search']/div[5]/div[2]/table/tbody/tr/td/div[4]/span"));   
		int x=speakers.size();
		String[] SpeakersNames = new String[x-1];
		for( i = 0;i<x-1;i++)
		{
			SpeakersNames[i]=speakers.get(i+1).getText(); 
			System.out.println(SpeakersNames[i]);
		}
		//String SessionCode=getText(SessionDetailsPopUPOR.txtSessionCode, "get Session code");
		click(SessionDetailsPopUPOR.txtSession,"Selected session");
		switchToFrameByIndex(0);
		String var2=getText(SessionDetailsPopUPOR.txtSessionPopUpTitle, "get pop up session title");
		if(var1.equalsIgnoreCase(var2))
		{
			System.out.println("Details are verified");

		}
		else
		{
			System.out.println("Deatils are not verified");
			countval++;
			Reporters.failureReport("details not ","present");

		}


		/*String SessionPopUPCode=getText(SessionDetailsPopUPOR.txtSessionPopUpCode,"get pop up session code tile");
				if(SessionCode.equalsIgnoreCase(SessionPopUPCode))
				{
					System.out.println("session pop up code is verified");
				}
				else
				{
					System.out.println("session pop up code is not verified");
					countval++;
				}*/

		String speakersList=getText(SessionDetailsPopUPOR.tblSpeakers, "check speaker's table");
		if(speakersList.equalsIgnoreCase("Speaker(s)"))
		{		
			List<WebElement> tableCount=driverM.findElements(By.xpath("//*[@id='content']/div[1]/div[2]/table"));
			int count=tableCount.size();
			String[] SpeakersPopUpNames = new String[count];

			for(i=0;i<count;i++)
			{
				SpeakersPopUpNames[i]=driverM.findElement(By.xpath("//*[@id='content']/div[1]/div[2]/table[count]/tbody/tr/td[2]/h4")).getText();
				System.out.println(SpeakersPopUpNames[i]);
				if(SpeakersPopUpNames[i].equalsIgnoreCase(SpeakersNames[i]))
				{
					System.out.println(SpeakersPopUpNames[i]+ "is verified");
				}
				else
				{
					System.out.println(SpeakersPopUpNames[i]+ "is not verified");
					countval++;
				}

			}	
		}
		else{
			System.out.println("Speakers details are not present");
		}

		List<WebElement> description=driverM.findElements(By.xpath("//*[@id='session-info']/div"));
		int descCount=description.size();
		if(descCount==10)
		{
			System.out.println("description is verified");
		}
		else
		{
			System.out.println("description details are not present");
		}

		getText(SessionDetailsPopUPOR.btnAddToAgenda, "Add To Agenda button is present");
		getText(SessionDetailsPopUPOR.lnkEmail,"Email Session Detail link is present");
		getText(SessionDetailsPopUPOR.lnkDownload,"Download Session Detail link is present");
		getText(SessionDetailsPopUPOR.lnkPrint,"Print Session Detail link is present");		

		if(countval==0)
		{
			val=true;
		}
		else
		{
			val=false;
		}
		return val;
	}

	/**
	 * This function verifies Add to Agenda button
	 * "Session added to agenda!!" message should be displayed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean addToAgendaMessage() throws Throwable
	{

		click(SessionDetailsPopUPOR.btnAddToAgenda,"Add To Agenda button");
		getText(SessionDetailsPopUPOR.txtSessionAdded, "Session added to agenda!!");
		return true;

	}

	/** 
	 * This function verifies the Session status after adding a session from agenda
	 * Session Status should be displayed as: Confirmed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */

	public static boolean statusMessage() throws Throwable
	{

		getText(SessionDetailsPopUPOR.txtStatus, "Status: Confirmed");
		return true;

	}

	/**
	 * This function verifies Remove from Agenda button
	 * "Session removed from agenda!!" message should be displayed.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean removeFromAgendaMessage() throws Throwable
	{

		click(SessionDetailsPopUPOR.btnRemoveFromAgenda,"Remove From Agenda button");
		getText(SessionDetailsPopUPOR.txtSessionAdded, "Session removed from agenda!!");
		return true;

	}

	/**
	 * This function verifies the Session status after removing a session from agenda
	 * Session Status should be displayed as: NA.
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean statusMessageAfterRemoveFromAgenda() throws Throwable
	{

		verifyTextNotPresent("Status: Confirmed");
		return true;

	}

	/**
	 * This function verifies Email link
	 * Email pop-up is displayed with elements
	 * "Select preferred email address" in the header.
	 * Actual user email address
	 *  Send and Close buttons
	 * @return
	 * @throws Throwable
	 */
	public static boolean emailPopUpValidation() throws Throwable
	{
		click(SessionDetailsPopUPOR.lnkEmail,"Email Session Detail link");
		getText(SessionDetailsPopUPOR.txtEmailPopUpTitle, "Select preferred email address");
		getText(SessionDetailsPopUPOR.txtUserEmailAddress, "User Email Address is displayed");
		getText(SessionDetailsPopUPOR.btnEmailSend, "Send");
		getText(SessionDetailsPopUPOR.btnEmailClose, "Close button is displayed");
		return true;

	}

	/**
	 * This function verifies the Send button
	 * An info pop-up is displayed with elements
	 * "Info" in the header.
	 * "Session details have been sent to the email provided!" message
	 *  OK button
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sendButtonValidation() throws Throwable
	{
		click(SessionDetailsPopUPOR.btnEmailSend,"Send button in Email pop up");
		getText(SessionDetailsPopUPOR.txtInfoPopUpTitle, "Info");
		getText(SessionDetailsPopUPOR.txtInfoPopUpMessage, "Session details have been sent to the email provided!");
		getText(SessionDetailsPopUPOR.btnInfoOk, "OK");
		click(SessionDetailsPopUPOR.btnInfoOk,"OK button in info pop up");
		Thread.sleep(3000);
		return true;

	}


	public static boolean printSession() throws Throwable
	{
		click(SessionDetailsPopUPOR.lnkPrint,"Print session detail lnk");
		s.click("Logos\\1392359627312.png", 50);
		return true;

	}

	/**
	 * 
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean downloadSessionValidation() throws Throwable
	{
		click(SessionDetailsPopUPOR.lnkDownload,"Download session detail lnk");
		getText(SessionDetailsPopUPOR.btnDownloadToCalender, "Download To Calendar");
		getText(SessionDetailsPopUPOR.btnEmailAsAnAttachment, "Email as an Attachment");
		return true;

	}

	public static boolean downloadToCalenderInSessionDetails() throws Throwable
	{
		click(SessionDetailsPopUPOR.btnDownloadToCalender,"Download To Calender button");
		s.click("Logos\\1392359627312.png", 50);
		try{
			WindowsUtils.killByName("OUTLOOK.EXE*32");
		}
		catch(Exception e)
		{
		}
		return true;

	}

	public static boolean emailAsAnAttachmentValidation() throws Throwable
	{
		click(SessionDetailsPopUPOR.lnkDownload,"Download session detail lnk");
		click(SessionDetailsPopUPOR.btnEmailAsAnAttachment,"Email As An Attachment button");
		getText(SessionDetailsPopUPOR.txtCurrentEmail, "Current email is present");
		getText(SessionDetailsPopUPOR.txtAdditionalEmails, "Click here to add additional");
		return true;

	}

	public static boolean sendEvent() throws Throwable
	{
		click(SessionDetailsPopUPOR.btnSendEvent,"Send Event button");
		getText(SessionDetailsPopUPOR.txtInfoPopUpTitle, "Info");
		getText(SessionDetailsPopUPOR.txtInfoPopUpMessage, "Events have been sent to the email provided!");
		getText(SessionDetailsPopUPOR.btnInfoOk, "OK");
		click(SessionDetailsPopUPOR.btnInfoOk,"OK button in info pop up");
		Thread.sleep(3000);
		return true;

	}

	//iConnect-Sessions(Calendar View)
	public static boolean calenderViewBtn() throws Throwable
	{
		click(SessionsOR.lnkSessoinsicon,"Sessions icon");
		waitForElementPresent(PersonalAgendaOR.btnclose);
		click(PersonalAgendaOR.btnclose, "Close");
		waitForElementPresent(CalenderViewOR.txtOption);
		click(CalenderViewOR.txtOption, "Select an option in Narrow Your Results section");
		waitForElementPresent(CalenderViewOR.btnCalenderview);
		click(CalenderViewOR.btnCalenderview,"Calender View button");
		getText(CalenderViewOR.lnkBackToListView, "Back To List View link is present");
		getText(CalenderViewOR.btnListView, "List View Button is present");
		getText(CalenderViewOR.lnkFilterLink, "Filter Link is present");
		getText(CalenderViewOR.lstCalenderGraph, "Calender Graph is present");
		return true;

	}

	public static boolean backToListViewLnk() throws Throwable
	{
		click(CalenderViewOR.lnkBackToListView,"Back To List View Link");
		waitForElementPresent(CalenderViewOR.tabSessions);
		getText(CalenderViewOR.tabSessions, "Directed to Sessions Tab");
		return true;

	}

	public static boolean backToListBtn() throws Throwable
	{
		click(CalenderViewOR.btnCalenderview,"Calender View button");
		click(CalenderViewOR.btnListView,"Back To List View Link");
		waitForElementPresent(CalenderViewOR.tabSessions);
		getText(CalenderViewOR.tabSessions, "Directed to Sessions Tab");
		return true;

	}

	public static boolean filterLnk() throws Throwable
	{
		click(CalenderViewOR.btnCalenderview,"Calender View button");
		click(CalenderViewOR.lnkFilterSession,"Any filter link");
		String var1=getText(CalenderViewOR.lnkFilterSession, "Get the filter name");
		String var2=getText(CalenderViewOR.txtFilterDay, "Gets the Displayed filter Day name ");

		if(var1.contains(var2))
		{
			System.out.println(var2);
			System.out.println("sessions related to the filter selected.");
			return true;
		}
		else
		{
			return false;
		}

	}

	public static boolean session_Details() throws Throwable
	{
		boolean flag=true;
		String var=getText(CalenderViewOR.txtSession, "gets the session title");
		if(!click(CalenderViewOR.txtSession,"Calender View button"))
		{
			flag=false;
		}
		waitForElementPresent(CalenderViewOR.txtSessionDetailsPopUpTitle);
		getText(CalenderViewOR.txtSessionDetailsPopUpTitle,"Session Details");
		//waitForElementPresent(CalenderViewOR.txtSessionTitle);
		switchToFrameByIndex(0);
		String var2=getText(CalenderViewOR.txtSessionTitle, "returns session title");
		if(var.equalsIgnoreCase(var2))
		{
			driverM.switchTo().defaultContent();
			click(CalenderViewOR.btnClosePopUp,"Close button of SEssion Details Pop Up");
			return flag;
		}
		else
		{
			flag=false;
		}
		return flag;
	}


	//iConnect-Sessions(Session Details)
	public static boolean sessionDetailsVerification() throws Throwable {
		boolean flag=true;
		waitForElementPresent(SessionsDetailsOR.lnkSessoinsicon);
		if(!click(SessionsDetailsOR.lnkSessoinsicon,"Session"))
		{
			flag=false;
		}
		waitForElementPresent(SessionsDetailsOR.btnSessoinsContinue);
		if(!click(SessionsDetailsOR.btnSessoinsContinue,"Session continue"))
		{
			flag=false;
		}
		waitForElementPresent(SessionsDetailsOR.tblSessionsResults);
		waitForElementPresent(SessionsDetailsOR.btnViewSessionsDetails);
		waitForElementPresent(SessionsDetailsOR.btnAddToAgenda);
		return flag;
	}

	public static boolean AddAgendaClick() throws Throwable {
		boolean flag=true;
		if(!click(SessionsDetailsOR.btnAddToAgenda, "Add to Agenda Button"))
		{
			flag=false;
		}
		waitForElementPresent(SessionsDetailsOR.txtSessionAddedToAgenda);
		return flag;

	}

	public static boolean StatusConfirmed() throws Throwable {
		waitForElementPresent(SessionsDetailsOR.txtStatusConfirmed);
		return true;

	}

	public static boolean RemoveFromAgenda() throws Throwable{
		boolean flag=true;
		if(!click(SessionsDetailsOR.btnRemoveFromAgenda,"Remove From MyAgenda Button"))
		{
			flag=false;
		}
		String str=getText(SessionsDetailsOR.txtSessionRemoveFromAgenda,"Session removed from agenda!!");
		System.out.println(str);
		return flag;
	}
	public static boolean NoStatus() throws Throwable{
		verifyTextNotPresent("Status: Confirmed");
		return true;
	}

	public static boolean SessoinsDetailsPopup() throws Throwable{
		boolean flag=true;
		waitForElementPresent(SessionsDetailsOR.btnViewSessionsDetails);
		if(!click(SessionsDetailsOR.btnViewSessionsDetails, "View Sessions Details button"))
		{
			flag=false;
		}
		switchToFrameByIndex(0);
		waitForElementPresent(SessionsDetailsOR.txtSessionsDetailsPopup);
		return flag;
	}

	public static boolean logOut() throws Throwable{
		boolean flag=true;
		if(!click(MyAgendaOR.lnkLogOut,"logout link"))
		{
			flag=false;
		}
		return flag;
	}

	//iConnect-Sessions(XML-<activity-settings>)


	public static boolean removeTag(By locator) throws Throwable{
		boolean flag=true;
		Actions builder = new Actions(driverM);
		if(isElementPresent(locator,"Tag to modify")){
			//builder.doubleClick(driverM.findElement(locator)).build().perform();
			/*builder.doubleClick(driverM.findElement(locator)).build().perform();
			Thread.sleep(2000);
			builder.click(driverM.findElement(locator)).build().perform();*/
			driverM.findElement(locator).click();
			builder.sendKeys(Keys.chord(Keys.SHIFT,Keys.END)).build().perform();
			System.out.println("");
		}
		else{
			flag=false;
		}
		return flag;
	}

	public static boolean removeSurveyIDTag(By locator) throws Throwable{
		boolean flag=true;
		WebElement id=driverM.findElement(locator);
		Actions builder = new Actions(driverM);
		if(isElementPresent(locator,"Tag to modify")){
			id.click();
			builder.click().doubleClick().release().build().perform();
		}
		else{
			flag=false;
		}
		return flag;
	}

	public static boolean insertActiveFalseTag(String type){

		String Key=type;
		switch (Key) {
		case "myagenda":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_01.png\"  icon-text=\"My Agenda\"  type=\"myagenda\"  active=\"false\"  order=\"7\" />").perform();

			break;
		case "surveys":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_11.png\"  icon-text=\"Surveys\"  type=\"surveys\"  active=\"false\"  order=\"2\" />").perform();

			break;
		case "expo":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_03.png\"  icon-text=\" Expo\"  type=\" expo\"  active=\"false\"  order=\"3\" />").perform();

			break;
		case "materials":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_42.png\"  icon-text=\"Presentations\"  type=\"materials\"  active=\"false\"  order=\"5\"/>").perform();

			break;
		case "highlight":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\"News\"  type=\"highlight\"  active=\"false\"  order=\"6\"/>").perform(); 
			break;
		case "sessions":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\"Sessions\"  type=\"sessions\"  active=\"false\"  order=\"4\"/>").perform(); 
			break;
		case "altus":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_41.png\"  icon-text=\"Session Replays\"  type=\" altus\"  active=\"false\"  order=\"6\"/>").perform(); 
			break;
		case "people":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_31.png\"  icon-text=\" People\"  type=\"people\"  active=\"false\"  order=\"8\"/>").perform(); 
			break;
		case "staticpages1":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_09.png\"  icon-text=\"Communities\"  type=\"staticpages1\"  active=\"false\"  order=\"9\"/>").perform(); 
			break;
		case "messages":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_07.png\"  icon-text=\"Messages\"  type=\"messages\"  active=\"false\"  order=\"10\"/>").perform();
			break;
		case "staticpages2":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_54.png\"  icon-text=\"Guides &amp; Roadmaps\"  type=\"staticpages2\"  active=\"false\"  order=\"11\"/>").perform();
			break;
		case "dailypoll":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_06.png\"  icon-text=\"DailyPoll\"  type=\"dailypoll\"  active=\"false\"  order=\"12\"/>").perform();
			break;
		case "customlink":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"http://www.ibm.com/events/literature/iod\" image=\"n_39.png\"  icon-text=\"e-Literature\"  type=\"customlink\"  active=\"false\"  order=\"13\"/>").perform();
			break;
		case "topics":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\"Topics\"  type=\"topics\"  active=\"false\"  order=\"14\"/>").perform();
			break;
		default: 
			break;
		}

		return true;

	}

	public static boolean insertActiveTrueTag(String type){
		String Key=type;
		System.out.println();
		switch (Key) {
		case "myagenda":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_01.png\"  icon-text=\"My Agenda\"  type=\"myagenda\"  active=\"true\"  order=\"7\" />").perform();

			break;
		case "surveys":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_11.png\"  icon-text=\"Surveys\"  type=\"surveys\"  active=\"true\"  order=\"2\" />").perform();

			break;
		case "expo":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_03.png\"  icon-text=\" Expo\"  type=\" expo\"  active=\"true\"  order=\"3\" />").perform();

			break;
		case "materials":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_42.png\"  icon-text=\"Presentations\"  type=\"materials\"  active=\"true\"  order=\"5\"/>").perform();

			break;
		case "highlight":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\"News\"  type=\"highlight\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;
		case "sessions":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\" Sessions\"  type=\"sessions\"  active=\"true\"  order=\"4\"/>").perform(); 
			break;
		case "altus":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_41.png\"  icon-text=\"Session Replays\"  type=\" altus\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;
		case "people":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_31.png\"  icon-text=\" People\"  type=\"people\"  active=\"true\"  order=\"8\"/>").perform(); 
			break;
		case "staticpages1":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_09.png\"  icon-text=\" Communities\"  type=\"staticpages1\"  active=\"true\"  order=\"9\"/>").perform(); 
			break;
		case "messages":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_07.png\"  icon-text=\"Messages\"  type=\"messages\"  active=\"true\"  order=\"10\"/>").perform();
			break;
		case "staticpages2":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_54.png\"  icon-text=\"Guides &amp; Roadmaps\"  type=\"staticpages2\"  active=\"true\"  order=\"11\"/>").perform();
			break;
		case "dailypoll":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_06.png\"  icon-text=\"DailyPoll\"  type=\"dailypoll\"  active=\"true\"  order=\"12\"/>").perform();
			break;
		case "customlink":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"http://www.ibm.com/events/literature/iod\" image=\"n_39.png\"  icon-text=\"e-Literature\"  type=\"customlink\"  active=\"false\"  order=\"13\"/>").perform();
			break;
		case "topics":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\"Topics\"  type=\"topics\"  active=\"true\"  order=\"14\"/>").perform();
			break;
		default: 
			break;
		}

		return true;

	}

	public static boolean insertDetailSetting(){
		new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"true\"  usage=\"Speaker\"  displaytext=\"Speakers:\"  type=\"sessions,materials\"  seperatortype=\"0\"  order=\"1\"/>").perform();
		return true;
	}

	public static boolean insertActivitySettingsIconText(String iconText,String type){

		String Key=type;
		System.out.println();
		switch (Key) {
		case "myagenda":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_01.png\"  icon-text=\""+iconText+"\"  type=\"myagenda\"  active=\"true\"  order=\"7\" />").perform();

			break;
		case "surveys":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_11.png\"  icon-text=\""+iconText+"\"  type=\"surveys\"  active=\"true\"  order=\"2\" />").perform();

			break;
		case "expo":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_03.png\"  icon-text=\""+iconText+"\"  type=\" expo\"  active=\"true\"  order=\"3\" />").perform();

			break;
		case "materials":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_42.png\"  icon-text=\""+iconText+"\"  type=\"materials\"  active=\"true\"  order=\"5\"/>").perform();

			break;
		case "highlight":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+iconText+"\"  type=\"highlight\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;
		case "sessions":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+iconText+"\"  type=\"sessions\"  active=\"true\"  order=\"4\"/>").perform(); 
			break;
		case "altus":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+iconText+"\"  type=\" altus\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;

		case "Attendee":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_31.png\"  icon-text=\""+iconText+"\"  type=\"people\"  active=\"true\"  order=\"8\"/>").perform(); 
			break;
		case "staticpages1":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_09.png\"  icon-text=\""+iconText+"\"  type=\"staticpages1\"  active=\"true\"  order=\"9\"/>").perform(); 
			break;
		case "messages":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_07.png\"  icon-text=\""+iconText+"\"  type=\"messages\"  active=\"true\"  order=\"10\"/>").perform();
			break;
		case "Guides &amp; Roadmaps":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_54.png\"  icon-text=\""+iconText+"\"  type=\"staticpages2\"  active=\"true\"  order=\"11\"/>").perform();
			break;
		case "DailyPoll":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_06.png\"  icon-text=\""+iconText+"\"  type=\"dailypoll\"  active=\"true\"  order=\"12\"/>").perform();
			break;
		case "e-Literature":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_39.png\"  icon-text=\""+iconText+"\"  type=\"customlink\"  active=\"true\"  order=\"13\"/>").perform();
			break;
		case "Topics":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\""+iconText+"\"  type=\"topics\"  active=\"true\"  order=\"14\"/>").perform();
			break;
		default: 
			break;
		}

		return true;

	}

	public static boolean insertActivitySettingsDefaultIconText(String originalIconText,String type){

		String Key=type;
		System.out.println();
		switch (Key) {
		case "myagenda":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_01.png\"  icon-text=\""+originalIconText+"\"  type=\"myagenda\"  active=\"true\"  order=\"7\" />").perform();

			break;
		case "surveys":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_11.png\"  icon-text=\""+originalIconText+"\"  type=\"surveys\"  active=\"true\"  order=\"2\" />").perform();

			break;
		case "expo":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_03.png\"  icon-text=\""+originalIconText+"\"  type=\" expo\"  active=\"true\"  order=\"3\" />").perform();

			break;
		case "materials":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_42.png\"  icon-text=\""+originalIconText+"\"  type=\"materials\"  active=\"true\"  order=\"5\"/>").perform();

			break;
		case "highlight":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+originalIconText+"\"  type=\"highlight\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;
		case "sessions":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+originalIconText+"\"  type=\"sessions\"  active=\"true\"  order=\"4\"/>").perform(); 
			break;
		case "altus":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_13.png\"  icon-text=\""+originalIconText+"\"  type=\" altus\"  active=\"true\"  order=\"6\"/>").perform(); 
			break;

		case "Attendee":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_31.png\"  icon-text=\""+originalIconText+"\"  type=\"people\"  active=\"true\"  order=\"8\"/>").perform(); 
			break;
		case "staticpages1":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_09.png\"  icon-text=\""+originalIconText+"\"  type=\"staticpages1\"  active=\"true\"  order=\"9\"/>").perform(); 
			break;
		case "messages":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_07.png\"  icon-text=\""+originalIconText+"\"  type=\"messages\"  active=\"true\"  order=\"10\"/>").perform();
			break;
		case "Guides &amp; Roadmaps":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_54.png\"  icon-text=\""+originalIconText+"\"  type=\"staticpages2\"  active=\"true\"  order=\"11\"/>").perform();
			break;
		case "DailyPoll":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_06.png\"  icon-text=\""+originalIconText+"\"  type=\"dailypoll\"  active=\"true\"  order=\"12\"/>").perform();
			break;
		case "e-Literature":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_39.png\"  icon-text=\""+originalIconText+"\"  type=\"customlink\"  active=\"true\"  order=\"13\"/>").perform();
			break;
		case "Topics":
			new Actions(driverM).sendKeys("    <activity-setting login-required=\"true\"  URL=\"\" image=\"n_12.png\"  icon-text=\""+originalIconText+"\"  type=\"topics\"  active=\"true\"  order=\"14\"/>").perform();
			break;
		default: 
			break;
		}

		return true;

	}
	public static boolean insertSessionCodeDisplayTextTag(String displayText){
		new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"true\"  usage=\"Session Code\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"0\"  order=\"1\"/>").perform();
		return true;

	}

	public static boolean insertLeftNavigationSettingsText(String navigationText,String nav_val){
		String Key=nav_val;
		System.out.println();
		switch (Key) {
		case "portal/sessions/jsp/default":
			new Actions(driverM).sendKeys("     <left-nav-item activity-type=\"sessions\"  nav-text=\""+navigationText+"\"  nav-type=\"customlink\"  nav-value=\"portal/sessions/jsp/default\"  default=\"false\" />").perform();

			break;
		case "portal/sessions/jsp/drop-in-lab":
			new Actions(driverM).sendKeys("     <left-nav-item activity-type=\"sessions\"  nav-text=\" "+navigationText+"\"  nav-type=\"customlink\"  nav-value=\"portal/sessions/jsp/drop-in-lab\"  default=\"false\" />").perform();

			break;
		case "portal/search/jsp/session_search":
			new Actions(driverM).sendKeys("     <left-nav-item activity-type=\"sessions\"  nav-text=\""+navigationText+"\"  nav-type=\"customlink\"  nav-value=\"portal/search/jsp/session_search\"  default=\"false\" />").perform();

			break;
		}
		return true;		

	}

	//iConnect-Sessions(XML-<advance-session-search-settings>- Edit Display Text)
	public static boolean insertAdvanceSearchSettingsDisplayText(String displayText,String usage){

		String Key=usage;
		System.out.println();
		switch (Key) {
		case "Program":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Program\"  displaytext=\""+displayText+"\"  order=\"1\" />").perform();
			break;
		case "Track":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Track\"  displaytext=\""+displayText+"\"  order=\"2\" />").perform();
			break;
		case "Content Category":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Content Category\"  displaytext=\""+displayText+"\"  order=\"3\" />").perform();
			break;
		case "Topic Tag":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Topic Tag\"  displaytext=\""+displayText+"\"  order=\"4\" />").perform();
			break;
		case "Industry":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Industry\"  displaytext=\""+displayText+"\"  order=\"5\" />").perform();
			break;
		case "Audience":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Audience\"  displaytext=\""+displayText+"\"  order=\"6\" />").perform();
			break;
		case "Content Level":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Content Level\"  displaytext=\""+displayText+"\"  order=\"7\" />").perform();
			break;
		}

		return true;
	}

	//iConnect-Sessions(XML-<advance-session-search-settings>- Edit Order)
	public static boolean insertAdvanceSearchSettingsOrder(String usage,String Order){

		String Key=usage;
		System.out.println();
		switch (Key) {
		case "Program":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Program\"  displaytext=\"Program\"  order=\""+Order+"\" />").perform();
			break;
		case "Track":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Track\"  displaytext=\"Track\"  order=\""+Order+"\" />").perform();
			break;
		case "Content Category":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Content Category\"  displaytext=\"Special Interest Area\"  order=\""+Order+"\" />").perform();
			break;
		case "Topic Tag":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Topic Tag\"  displaytext=\"Topic\"  order=\""+Order+"\" />").perform();
			break;
		case "Industry":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\" Industry\"  displaytext=\" Industry\"  order=\""+Order+"\" />").perform();
			break;
		case "Audience":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\" Audience\"  displaytext=\"Role\"  order=\""+Order+"\" />").perform();
			break;
		case "Content Level":
			new Actions(driverM).sendKeys("    <advance-session-search-setting usage=\"Content Level\"  displaytext=\"Level\"  order=\""+Order+"\" />").perform();
			break;
		}

		return true;
	}

	public static boolean verifyOrderOfAdvanceSessionSearchSetting(Sheet ASS_OrderSheet) throws Throwable
	{
		boolean flag=true;
		List<WebElement> all = driverM.findElements(By.xpath("//div[@class='session_search_box_narrow_body']/div[contains(@style,'font-weight: bold')]"));
		for (int i = 1; i < ASS_OrderSheet.getRows(); i++) {
			System.out.println(all.get(Integer.parseInt(ASS_OrderSheet.getCell(1, i).getContents())).getText());
			System.out.println(ASS_OrderSheet.getCell(2, i).getContents());
			if(all.get(Integer.parseInt(ASS_OrderSheet.getCell(1, i).getContents())).getText().equalsIgnoreCase(ASS_OrderSheet.getCell(2, i).getContents())){
				Reporters.SuccessReport("Verify Order of advance-session-search-setting Tag for usage value "+ASS_OrderSheet.getCell(0, i).getContents()+"", "Order "+ASS_OrderSheet.getCell(1, i).getContents()+" got updated successfully");
			}
			else{
				Reporters.failureReport("Verify Order of advance-session-search-setting Tag for usage value "+ASS_OrderSheet.getCell(0, i).getContents()+"", "Order "+ASS_OrderSheet.getCell(1, i).getContents()+" is not updated");
				flag=false;
			}

		}
		return flag;
	}

	//iConnect-Sessions(XML-<session-detail-settings>- Edit Visible)
	public static boolean insertSessionDetailSettingVisibleFalse(String usage){

		String Key=usage;
		System.out.println(usage);
		switch(Key){

		case "Speaker":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"true\"  usage=\"Speaker\"  displaytext=\"Speakers:\"  type=\"sessions,materials\"  seperatortype=\"0\"  order=\"3\"/>").perform();
			break;
		case "Session Code":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"true\"  usage=\"Session Code\"  displaytext=\"Session Code:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"4\"/>").perform();
			break;
		case "Program":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\"Program\"  displaytext=\"Program:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"2\"/>").perform();
			break;
		case "Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\"Track\"  displaytext=\"Track:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"1\"/>").perform();
			break;
		case "Sub-Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\"Sub-Track\"  displaytext=\"Subtrack:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"6\"/>").perform();
			break;
		case "Topic Tag":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\"Topic Tag\"  displaytext=\"Topic:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"7\"/>").perform();
			break;
		case "Industry":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\" Industry\"  displaytext=\" Industry:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"0\"/>").perform();
			break;
		case "Content Level":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"false\"  promoted=\"false\"  usage=\"Content Level\"  displaytext=\"Level:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"8\"/>").perform();
			break;
		}
		return true;
	}

	public static boolean insertSessionDetailSettingVisibleTrue(String usage){

		String Key=usage;
		System.out.println(usage);
		switch(Key){

		case "Speaker":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"true\"  usage=\"Speaker\"  displaytext=\"Speakers:\"  type=\"sessions,materials\"  seperatortype=\"0\"  order=\"3\"/>").perform();
			break;
		case "Session Code":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"true\"  usage=\"Session Code\"  displaytext=\"Session Code:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"4\"/>").perform();
			break;
		case "Program":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Program\"  displaytext=\"Program:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"2\"/>").perform();
			break;
		case "Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Track\"  displaytext=\"Track:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"1\"/>").perform();
			break;
		case "Sub-Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Sub-Track\"  displaytext=\"Subtrack:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"6\"/>").perform();
			break;
		case "Topic Tag":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Topic Tag\"  displaytext=\"Topic:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"7\"/>").perform();
			break; 
		case "Industry":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\" Industry\"  displaytext=\" Industry:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"0\"/>").perform();
			break;
		case "Content Level":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Content Level\"  displaytext=\"Level:\"  type=\"sessions\"  seperatortype=\"1\"  order=\"8\"/>").perform();
			break;
		}
		return true;
	}

	public boolean clickSaveXMLFileButton() throws Throwable{
		boolean flag=true;

		driverM.switchTo().defaultContent();

		if(isElementPresent(XML_OR.btnSaveXMLFile, "Save XML File Button")){

			click(XML_OR.btnSaveXMLFile, "Save XML File Button");
			return flag;
		}
		else{
			flag=false;
		}
		return flag;
	}


	public static boolean openSiteInNewWindow(String title) throws Throwable
	{
		Actions builder = new Actions(driverM);
		builder.sendKeys(Keys.chord(Keys.CONTROL,"n")).perform();
		switchWindowByTitle(title, 2);
		//getPortalURL();
		return true;
	}

	public static boolean verifyTabNameInPortal(String TabName){
		boolean flag=true;

		List<WebElement> tabNames=driverM.findElements(By.xpath("//h3[@class='title']/a"));

		for (WebElement tabname : tabNames) {
			if(tabname.getText().equalsIgnoreCase(TabName)){

				flag=true;
				break;
			}

			else{
				flag=false;
			}
		}

		return flag;
	}

	public static boolean verifyIconInPortal(By locator) throws Throwable
	{
		boolean flag=true;


		if(isVisible(XML_OR.txtSessionsInPortal, "Sessions text should not be present")){
			flag=false;
		}
		else{
			flag=true;
		}

		return flag;
	}

	public static boolean logoutFromPortal() throws Throwable{
		boolean flag=true;

		if(isElementPresent(CommonOR.lnkLogoutPortal, "Log Out Link")){
			if(!click(CommonOR.lnkLogoutPortal, "Log Out Link"))
				flag=false;
		}
		else{
			flag=false;
		}
		Thread.sleep(2000);
		return flag;
	}

	public static boolean closeWindow(String title,int position) throws Throwable
	{
		boolean flag=true;
		try {
			driverM.close();
			switchWindowByTitle(title, position);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	public static void checkSession(String str) throws Throwable {
		for (int i = 0; i < sessionElements.size(); i++) {
			text = sessionElements.get(i).getText();
			String[] c = text.split("\\r?\\n");
			for (int j = 0; j < c.length; j++) {
				if (c[j].equals(te)) {
					Reporters.SuccessReport("Event" + str, "Sucessfully");
					break;
				}

			}
		}
	}

	//opens EMT URL
	public static boolean getEMTURL(){
		boolean flag=true;

		driverM.get(configProps.getProperty("EMT_URL"));

		return flag;
	}

	//opens CheckIn URL
	public static boolean getCheckinURL()
	{
		boolean flag=true;
		driverM.get(configProps.getProperty("CheckIn_URL"));
		return flag;
	}

	//opens Survey URL
	public static boolean getSurveyURL()
	{
		boolean flag=true;
		driverM.get(configProps.getProperty("Survey_URL"));
		return flag;
	}

	//Opens Portal URL
	public static boolean getPortalURL(){
		boolean flag=true;

		driverM.get(configProps.getProperty("Portal_URL"));

		return flag;
	}

	//Opens Attendee Journey URL
	public static boolean getAttendeeJourneyURL(){
		boolean flag=true;

		driverM.get(configProps.getProperty("AttendeeJourney_URL"));

		return flag;
	}

	//iConnect-EMT
	public static boolean clickTabFromViewMore(By locator,String locatorname) throws Throwable
	{
		boolean flag=true;

		if(!js_click(EMT_RegistrantsOR.lnkViewMore, "View More Link")){
			flag=false;
		}
		if(!waitForFrameToLoadAndSwitchToIt(CommonOR.frameTab, "View More Tabs Frame"))
		{
			flag=false;
		}
		if(!js_click(locator,locatorname)){
			flag=false;
		}
		Thread.sleep(2000);
		return flag;
	}

	/*public static boolean emtLogIn(String uname, String password) throws Throwable
	{
		boolean flag=true;

		if(!type(EMT_LogInOR.txtusername, uname, "User Name"))
		{
			flag=false;
		}
		if(!type(EMT_LogInOR.txtPassword, password, "Password"))
		{
			flag=false;
		}
		if(!click(EMT_LogInOR.btnLogin, "Login"))
		{
			flag=false;
		}
		return flag;
	}*/

	public static boolean emtLogIn() throws Throwable
	{
		boolean flag=true;

		if(!type(EMT_LogInOR.txtusername, configProps.getProperty("EMT_Username"), "User Name"))
		{
			flag=false;
		}
		if(!type(EMT_LogInOR.txtPassword, configProps.getProperty("EMT_Password"), "Password"))
		{
			flag=false;
		}
		if(!js_click(EMT_LogInOR.btnLogin, "Login"))
		{
			flag=false;
		}

		return flag;
	}

	public static boolean attendeeJourneyLogIn() throws Throwable
	{
		boolean flag=true;

		if(!type(AttendeeJourneyOR.txtUsername, configProps.getProperty("AttendeeJourney_Username"), "User Name"))
		{
			flag=false;
		}
		if(!type(AttendeeJourneyOR.txtPassword, configProps.getProperty("AttendeeJourney_Password"), "Password"))
		{
			flag=false;
		}
		if(!click(AttendeeJourneyOR.btnSignIn, "Login"))
		{
			flag=false;
		}

		return flag;
	}

	public static boolean checkInSiteLogIn(String username,String password) throws Throwable
	{
		boolean flag=true;
		if(!type(CheckInOR.txtEmailAddress, username, "Email Address"))
		{
			flag=false;
		}
		if(!type(CheckInOR.txtPAssword, password, "Password"))
		{
			flag=false;
		}
		if(!click(CheckInOR.btnSignIn,"Sign In Button"))
		{
			flag=false;
		}
		return flag;
	}
	public static boolean surveyLogIn(String RegEmailID) throws Throwable
	{
		boolean flag=true;

		if(!type(SurveyOR.txtEmail, RegEmailID, "User Name"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnLookMeUp,"Look Me Up"))
		{
			flag=false;
		}
		return flag;
	}


	/*	public static boolean EMT_Login() throws Throwable{
		boolean flag= true;

		if(!type(EMT_LogInOR.txtusername, configProps.getProperty("EMT_Username"), "User Name")){
			flag=false;
		}
		if(!type(EMT_LogInOR.txtPassword, configProps.getProperty("EMT_Password"), "Password")){
			flag=false;
		}
		if(!click(EMT_LogInOR.btnLogin, "Login")){
			flag=false;
		}

		return flag;
	}*/

	public static boolean Portal_Login() throws Throwable{
		boolean flag= true;

		if(isElementPresent(MyAgendaOR.txtUserName, "User Name Text Box")){

			if(!type(MyAgendaOR.txtUserName, configProps.getProperty("Portal_Username"), "User Name")){
				flag=false;
			}
			if(!type(MyAgendaOR.txtPassword, configProps.getProperty("Portal_Password"), "Password")){
				flag=false;
			}
			if(!click(MyAgendaOR.btnSignIn, "Sign In")){
				flag=false;
			}
			Thread.sleep(2000);
			if(waitForVisibilityOfElement(MyAgendaOR.btnNoThanksInTwitterPopUp, "Twitter Pop Up")){
				if(!click(MyAgendaOR.btnNoThanksInTwitterPopUp,"No Thanks"))
				{
					flag=false;
				}
			}
		}
		return flag;
	}

	public static boolean emtLogOut() throws Throwable
	{
		boolean flag= true;

		if(isElementPresent(EMT_LogOutOR.lnkAdmin,"Alliance Tech Master Admin Drop Down")){
			if(!js_click(EMT_LogOutOR.lnkAdmin,"Alliance Tech Master Admin Drop Down"))
			{
				flag=false;
			}
			if(!click(EMT_LogOutOR.lnkLogOut,"Log Out"))
			{
				flag=false;
			}
		}

		else{
			flag=false;
		}

		return flag;
	}

	public static boolean attendeeJourneyLogout() throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(AttendeeJourneyOR.loading, "Loading");
		if(!js_click(AttendeeJourneyOR.lnkLogout, "Logout"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(AttendeeJourneyOR.btnSignIn, "Sign In button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean editRegistrantsInformation(String prefix,String attendee,String first,String CustomerRegistrantID,
			String last,String status,String AttendeeType,String SubAttendeeType) throws Throwable
			{

		boolean flag=true;
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		if(!prefix.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Prefix"),"Prefix"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Prefix"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!first.isEmpty()){
			if(!click(CommonOR.txtBoxData("First"),"First"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("First"),first,"Enter First name"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("First"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!last.isEmpty()){
			if(!click(CommonOR.txtBoxData("Last"),"First"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Last"),last," last Name"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Last"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status drop down")){
				if(!click(CommonOR.txtBoxData("Status"),"Status drop down"))
					flag=false;
				waitForVisibilityOfElement(CommonOR.txtChoose, "Choose");
				if(!click(CommonOR.ddRemoveValue("Status"),"Status Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Status"), status, "Status Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Status"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!AttendeeType.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Attendee Type"), "Attendee Type drop down")){
				if(!click(CommonOR.txtBoxData("Attendee Type"),"Attendee Type drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Attendee Type"),"Attendee Type Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Attendee Type", AttendeeType)){
					if(!selectByVisibleText(CommonOR.ddLabel("Attendee Type"), AttendeeType, "AttendeeType Drop Down")){
						flag=false;
					}

				}
				else{
					if(!type(CommonOR.txtAddNew("Attendee Type"), AttendeeType, "AttendeeType Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Attendee Type"), "Plus Button of AttendeeType")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Attendee Type"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!SubAttendeeType.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Sub Attendee Type"), "Sub Attendee Type drop down")){
				if(!click(CommonOR.txtBoxData("Sub Attendee Type"),"Sub Attendee Type drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Sub Attendee Type"),"Sub Attendee Type Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Sub Attendee Type", SubAttendeeType)){
					if(!selectByVisibleText(CommonOR.ddLabel("Sub Attendee Type"), SubAttendeeType, "SubAttendeeType Drop Down")){
						flag=false;
					}

				}
				else{
					if(!type(CommonOR.txtAddNew("Sub Attendee Type"), SubAttendeeType, "SubAttendeeType Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Sub Attendee Type"), "Plus Button of SubAttendeeType")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Sub Attendee Type"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		return flag;
			}

	public static boolean addRegistrantInfoInEMT(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,String Phone,String Mobile,String AltPhone,String Fax,
			String Company,String Address1,String Address2,String county,String region,String City,String CountryCode,
			String Country,String ZipCode,String PersonalEmail,String Email,String LoginID,String Password,String ConfirmPassword) throws Throwable
			{
		boolean flag=true;
		if(!prefix.isEmpty())
		{
			if(!type(CommonOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
				flag=false;
		}
		if(!attendee.isEmpty()){
			if(!type(CommonOR.txtBox("Attendee #"),attendee," attendee number"))
				flag=false;
		}
		if(!first.isEmpty()){
			if(!type(CommonOR.txtBox("First"),first,"Enter First name"))
				flag=false;
		}
		if(!last.isEmpty()){
			if(!type(CommonOR.txtBox("Last"),last," last Name"))
				flag=false;
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status drop down")){
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Status"), status, "Status Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
			}
		}

		if(!AttendeeType.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Attendee Type"), "Attendee Type drop down")){
				if(verifyInDropDownList("Attendee Type", AttendeeType)){
					if(!selectByVisibleText(CommonOR.ddLabel("Attendee Type"), AttendeeType, "AttendeeType Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Attendee Type"), AttendeeType, "AttendeeType Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Attendee Type"), "Plus Button of AttendeeType")){
						flag=false;
					}
				}
			}
		}

		if(!Title.isEmpty())
		{
			if(!type(CommonOR.txtBox("Title"), Title, "Title Text Box"))
				flag=false;
		}
		if(!Phone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Phone"), Phone, "Phone Text Box"))
				flag=false;	
		}
		if(!Mobile.isEmpty())
		{
			if(!type(CommonOR.txtBox("Mobile Phone"), Mobile, "Mobile Phone Text Box"))
				flag=false;
		}
		if(!AltPhone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Alt Phone"), AltPhone, "Alt Phone Text Box"))
				flag=false;
		}
		if(!Fax.isEmpty())
		{
			if(!type(CommonOR.txtBox("Fax"), Fax, "Fax Text Box"))
				flag=false;
		}
		if(!Company.isEmpty())
		{
			if(!type(CommonOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
		}
		if(!Address1.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address1"), Address1, "Address1 Text Box"))
				flag=false;
		}
		if(!Address2.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address2"), Address2, "Address2 Text Box"))
				flag=false;
		}
		if(!county.isEmpty())
		{
			if(!type(CommonOR.txtBox("County"), county, "County Text Box"))
				flag=false;
		}
		if(!region.isEmpty())
		{
			if(!type(CommonOR.txtBox("Region"), region, "Region Text Box"))
				flag=false;
		}
		if(!City.isEmpty())
		{
			if(!type(CommonOR.txtBox("City"), City, "City Text Box"))
				flag=false;
		}
		if(!CountryCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
		}
		if(!Country.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Country"), "Country Drop Down"))
			{
				if(verifyInDropDownList("Country", Country)){
					if(!selectByVisibleText(CommonOR.ddLabel("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Country"), Country, "Country Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Country"), "Plus Button of Country")){
						flag=false;
					}
				}
			}
		}
		if(!ZipCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;	
		}
		if(!PersonalEmail.isEmpty())
		{
			if(!type(CommonOR.txtBox("Personal Email"), PersonalEmail, "Personal Email Text Box"))
				flag=false;	
		}
		if(!Email.isEmpty())
		{
			if(!type(CommonOR.txtBox("Email"), Email, "Email Text Box"))
				flag=false;	
		}
		if(!LoginID.isEmpty())
		{
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
		}
		if(!Password.isEmpty())
		{
			if(!type(CommonOR.txtBox("Password"), Password, "Password Text Box"))
				flag=false;
		}
		if(!ConfirmPassword.isEmpty())
		{
			if(!type(EMT_RegistrantsOR.txtConfirmPassword, ConfirmPassword, "Confirm Password"))
				flag=false;
		}
		return flag;
			}

	public static boolean addRegistrantsInformation(String prefix,String attendee,String first,String CustomerRegistrantID,
			String last,String status,String AttendeeType,String SubAttendeeType) throws Throwable
			{

		boolean flag=true;
		if(!prefix.isEmpty())
		{
			if(!type(CommonOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
				flag=false;
		}
		if(!attendee.isEmpty()){
			if(!type(CommonOR.txtBox("Attendee #"),attendee," attendee number"))
				flag=false;
		}
		if(!first.isEmpty()){
			if(!type(CommonOR.txtBox("First"),first,"Enter First name"))
				flag=false;
		}
		if(!CustomerRegistrantID.isEmpty()){
			if(!type(CommonOR.txtBox("Customer Registrant Id"),CustomerRegistrantID,"s Customer Registrant Id"))
				flag=false;
		}
		if(!last.isEmpty()){
			if(!type(CommonOR.txtBox("Last"),last," last Name"))
				flag=false;
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status drop down")){
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Status"), status, "Status Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
			}
		}

		if(!AttendeeType.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Attendee Type"), "Attendee Type drop down")){
				if(verifyInDropDownList("Attendee Type", AttendeeType)){
					if(!selectByVisibleText(CommonOR.ddLabel("Attendee Type"), AttendeeType, "AttendeeType Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Attendee Type"), AttendeeType, "AttendeeType Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Attendee Type"), "Plus Button of AttendeeType")){
						flag=false;
					}
				}
			}
		}
		if(!SubAttendeeType.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Sub Attendee Type"), "Sub Attendee Type drop down")){
				if(verifyInDropDownList("Sub Attendee Type", SubAttendeeType)){
					if(!selectByVisibleText(CommonOR.ddLabel("Sub Attendee Type"), SubAttendeeType, "SubAttendeeType Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Sub Attendee Type"), SubAttendeeType, "SubAttendeeType Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Sub Attendee Type"), "Plus Button of SubAttendeeType")){
						flag=false;
					}
				}
			}
		}
		return flag;
			}

	public static boolean editRegistrantBio(String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM,String AboutMe) throws Throwable
	{
		boolean flag=true;
		if(!WebURL.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Web URL"),"Web URL"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Web URL"),WebURL,"Web URL Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Web URL"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Blog.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Blog"),"Blog"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Blog"), Blog, "Blog Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Blog"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!LinkedInURL.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("LinkedIn URL"),"LinkedIn URL"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("LinkedIn URL"), LinkedInURL, "LinkedIn URL Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("LinkedIn URL"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!FacebookURL.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Facebook URL"),"Facebook URL"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Facebook URL"), FacebookURL, "Facebook URL Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Facebook URL"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!TwitterAcnt.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Twitter Account"),"Twitter Account"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Twitter Account"), TwitterAcnt, "Twitter Account Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Twitter Account"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!IM.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("IM"),"IM"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("IM"), IM, "IM Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("IM"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!AboutMe.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("About Me"),"About Me"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtAboutMe, AboutMe, "About Me Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("About Me"),"Save button"))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		return flag;
	}

	public static boolean addRegistrantBio(String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM,String AboutMe) throws Throwable
	{
		boolean flag=true;
		if(!WebURL.isEmpty())
		{
			if(!type(CommonOR.txtBox("Web URL"),WebURL,"Web URL Text Box"))
				flag=false;
		}
		if(!Blog.isEmpty())
		{
			if(!type(CommonOR.txtBox("Blog"), Blog, "Blog Text Box"))
				flag=false;
		}
		if(!LinkedInURL.isEmpty())
		{
			if(!type(CommonOR.txtBox("LinkedIn URL"), LinkedInURL, "LinkedIn URL Text Box"))
				flag=false;
		}
		if(!FacebookURL.isEmpty())
		{
			if(!type(CommonOR.txtBox("Facebook URL"), FacebookURL, "Facebook URL Text Box"))
				flag=false;
		}
		if(!TwitterAcnt.isEmpty())
		{
			if(!type(CommonOR.txtBox("Twitter Account"), TwitterAcnt, "Twitter Account Text Box"))
				flag=false;
		}
		if(!IM.isEmpty())
		{
			if(!type(CommonOR.txtBox("IM"), IM, "IM Text Box"))
				flag=false;
		}
		if(!AboutMe.isEmpty())
		{
			if(!type(CommonOR.txtAboutMe, AboutMe, "About Me Text Box"))
				flag=false;
		}

		return flag;
	}

	public static boolean editRegistrantContactInformation(String FullName,String BadgeFirstName,String Title,String Phone,String Mobile,String AltPhone,String Fax,
			String Company,String Address1,String Address2,String county,String region,String City,String CountryCode,
			String Country,String ZipCode,String PersonalEmail,String Email) throws Throwable
			{
		boolean flag=true;
		if(!FullName.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Full Name"),"Full Name"))
			{
				flag=false;
			}
			if(!type(CommonOR.txtBox("Full Name"), FullName, "Full Name Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Full Name"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		/*if(!BadgeFirstName.isEmpty())
		{
			if(!type(CommonOR.txtBox("Badge First Name"), BadgeFirstName, "Badge First Name Text Box"))
				flag=false;
		}*/
		if(!Title.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Title"),"Title"))
				flag=false;
			if(!type(CommonOR.txtBox("Title"), Title, "Title Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Title"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Phone.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Phone"),"Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Phone"), Phone, "Phone Text Box"))
				flag=false;	
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Mobile.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Mobile Phone"),"Mobile Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Mobile Phone"), Mobile, "Mobile Phone Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Mobile Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!AltPhone.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Alt Phone"),"Alt Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Alt Phone"), AltPhone, "Alt Phone Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Alt Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Fax.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Fax"),"Fax"))
				flag=false;
			if(!type(CommonOR.txtBox("Fax"), Fax, "Fax Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Fax"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Company.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Company"),"Company"))
				flag=false;
			if(!type(CommonOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Company"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Address1.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Address1"),"Address1"))
				flag=false;
			if(!type(CommonOR.txtBox("Address1"), Address1, "Address1 Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Address1"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Address2.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Address2"),"Address2"))
				flag=false;
			if(!type(CommonOR.txtBox("Address2"), Address2, "Address2 Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Address2"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!county.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("County"),"County"))
				flag=false;
			if(!type(CommonOR.txtBox("County"), county, "County Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("County"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!region.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Region"),"Region"))
				flag=false;
			if(!type(CommonOR.txtBox("Region"), region, "Region Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Region"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!City.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("City"),"City"))
				flag=false;
			if(!type(CommonOR.txtBox("City"), City, "City Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("City"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!CountryCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Country Code"),"Country Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Country Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Country.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Country"), "Country Drop Down"))
			{
				if(!click(CommonOR.txtBoxData("Country"),"Country drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!js_click(CommonOR.ddRemoveValue("Country"),"Country Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Country", Country)){
					if(!selectByVisibleText(CommonOR.ddLabel("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Country"), Country, "Country Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Country"), "Plus Button of Country")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Country"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!ZipCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Zip Code"),"Zip Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;	
			waitForVisibilityOfElement(CommonOR.btnSave("Zip Code"), "Save button");
			if(!click(CommonOR.btnSave("Zip Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!PersonalEmail.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Personal Email"),"Personal Email"))
				flag=false;
			if(!type(CommonOR.txtBox("Personal Email"), PersonalEmail, "Personal Email Text Box"))
				flag=false;	
			waitForVisibilityOfElement(CommonOR.btnSave("Personal Email"), "Save button");
			if(!click(CommonOR.btnSave("Personal Email"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Email.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Email"),"Email"))
				flag=false;
			if(!type(CommonOR.txtBox("Email"), Email, "Email Text Box"))
				flag=false;	
			waitForVisibilityOfElement(CommonOR.btnSave("Email"), "Save button");
			if(!click(CommonOR.btnSave("Email"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
			}

	public static boolean addRegistrantContactInformation(String FullName,String BadgeFirstName,String Title,String Phone,String Mobile,String AltPhone,String Fax,
			String Company,String Address1,String Address2,String county,String region,String City,String CountryCode,
			String Country,String ZipCode,String PersonalEmail,String Email) throws Throwable
			{
		boolean flag=true;
		if(!FullName.isEmpty())
		{
			if(!type(CommonOR.txtBox("Full Name"), FullName, "Full Name Text Box"))
				flag=false;
		}
		/*if(!BadgeFirstName.isEmpty())
		{
			if(!type(CommonOR.txtBox("Badge First Name"), BadgeFirstName, "Badge First Name Text Box"))
				flag=false;
		}*/
		if(!Title.isEmpty())
		{
			if(!type(CommonOR.txtBox("Title"), Title, "Title Text Box"))
				flag=false;
		}
		if(!Phone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Phone"), Phone, "Phone Text Box"))
				flag=false;	
		}
		if(!Mobile.isEmpty())
		{
			if(!type(CommonOR.txtBox("Mobile Phone"), Mobile, "Mobile Phone Text Box"))
				flag=false;
		}
		if(!AltPhone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Alt Phone"), AltPhone, "Alt Phone Text Box"))
				flag=false;
		}
		if(!Fax.isEmpty())
		{
			if(!type(CommonOR.txtBox("Fax"), Fax, "Fax Text Box"))
				flag=false;
		}
		if(!Company.isEmpty())
		{
			if(!type(CommonOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
		}
		if(!Address1.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address1"), Address1, "Address1 Text Box"))
				flag=false;
		}
		if(!Address2.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address2"), Address2, "Address2 Text Box"))
				flag=false;
		}
		if(!county.isEmpty())
		{
			if(!type(CommonOR.txtBox("County"), county, "County Text Box"))
				flag=false;
		}
		if(!region.isEmpty())
		{
			if(!type(CommonOR.txtBox("Region"), region, "Region Text Box"))
				flag=false;
		}
		if(!City.isEmpty())
		{
			if(!type(CommonOR.txtBox("City"), City, "City Text Box"))
				flag=false;
		}
		if(!CountryCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
		}
		if(!Country.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Country"), "Country Drop Down"))
			{
				if(verifyInDropDownList("Country", Country)){
					if(!selectByVisibleText(CommonOR.ddLabel("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Country"), Country, "Country Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Country"), "Plus Button of Country")){
						flag=false;
					}
				}
			}
		}
		if(!ZipCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;	
		}
		if(!PersonalEmail.isEmpty())
		{
			if(!type(CommonOR.txtBox("Personal Email"), PersonalEmail, "Personal Email Text Box"))
				flag=false;	
		}
		if(!Email.isEmpty())
		{
			if(!type(CommonOR.txtBox("Email"), Email, "Email Text Box"))
				flag=false;	
		}
		return flag;
			}

	public static boolean editRegistrantOptInInformation(String OptInAttendeeSrch,String OptInTicklerMessages,String OptInTwitterSessSelection,
			String OptInSessAttendance,String OptInRFIDAttendance) throws Throwable
			{
		boolean flag=true;
		if(OptInAttendeeSrch.equals("false"))
		{
			if(!click(CommonOR.txtBoxData("OptIn Attendee Srch"),"OptIn Attendee Srch check box"))
				flag=false;
			if(!click(CommonOR.chckBox("OptIn Attendee Srch"), "OptIn Attendee Srch check box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("OptIn Attendee Srch"), "Save button");
			if(!click(CommonOR.btnSave("OptIn Attendee Srch"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(OptInTicklerMessages.equals("false"))
		{
			if(!click(CommonOR.txtBoxData("OptIn Tickler Messages"),"OptIn Tickler Messages check box"))
				flag=false;
			if(!click(CommonOR.chckBox("OptIn Tickler Messages"),"OptIn Tickler Messages Check box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("OptIn Tickler Messages"), "Save button");
			if(!click(CommonOR.btnSave("OptIn Tickler Messages"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(OptInTwitterSessSelection.equals("false"))
		{
			if(!click(CommonOR.txtBoxData("OptIn Twitter Sess Selection"),"OptIn Twitter Sess Selection check box"))
				flag=false;
			if(!click(CommonOR.chckBox("OptIn Twitter Sess Selection"),"OptIn Twitter Sess Selection Check Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("OptIn Twitter Sess Selection"), "Save button");
			if(!click(CommonOR.btnSave("OptIn Twitter Sess Selection"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(OptInSessAttendance.equals("false"))
		{
			if(!click(CommonOR.txtBoxData("OptIn Sess Attendance"),"OptIn Sess Attendance check box"))
				flag=false;
			if(!click(CommonOR.chckBox("OptIn Sess Attendance"),"OptIn Sess Attendance Check Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("OptIn Sess Attendance"), "Save button");
			if(!click(CommonOR.btnSave("OptIn Sess Attendance"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(OptInRFIDAttendance.equals("false"))
		{
			if(!click(CommonOR.txtBoxData("OptIn RFID Attendance"),"OptIn RFID Attendance check box"))
				flag=false;
			if(!click(CommonOR.chckBox("OptIn RFID Attendance"),"OptIn RFID Attendance check box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("OptIn RFID Attendance"), "Save button");
			if(!click(CommonOR.btnSave("OptIn RFID Attendance"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
			}

	public static boolean addRegistrantOptInInformation(String OptInAttendeeSrch,String OptInTicklerMessages,String OptInTwitterSessSelection,
			String OptInSessAttendance,String OptInRFIDAttendance) throws Throwable
			{
		boolean flag=true;
		if(OptInAttendeeSrch.equals("true"))
		{
			if(!click(CommonOR.chckBox("OptIn Attendee Srch"), "OptIn Attendee Srch check box"))
				flag=false;
		}
		if(OptInTicklerMessages.equals("true"))
		{
			if(!click(CommonOR.chckBox("OptIn Tickler Messages"),"OptIn Tickler Messages Check box"))
				flag=false;
		}
		if(OptInTwitterSessSelection.equals("true"))
		{
			if(!click(CommonOR.chckBox("OptIn Twitter Sess Selection"),"OptIn Twitter Sess Selection Check Box"))
				flag=false;
		}
		if(OptInSessAttendance.equals("true"))
		{
			if(!click(CommonOR.chckBox("OptIn Sess Attendance"),"OptIn Sess Attendance Check Box"))
				flag=false;
		}
		if(OptInRFIDAttendance.equals("true"))
		{
			if(!click(CommonOR.chckBox("OptIn RFID Attendance"),"OptIn RFID Attendance check box"))
				flag=false;
		}
		return flag;
			}

	public static boolean editRegistrantLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Login Id"),"Login Id Textbox"))
				flag=false;
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("Login Id"), "Save button");
			if(!click(CommonOR.btnSave("Login Id"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		/*if(!Password.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Password"),"Password check box"))
				flag=false;
			if(!type(CommonOR.txtBox("Password"), Password, "Password Text Box"))
				flag=false;
		}
		if(!ConfirmPassword.isEmpty())
		{
			if(!type(EMT_RegistrantsOR.txtConfirmPassword, ConfirmPassword, "Confirm Password"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("Login Id"), "Save button");
			if(!click(CommonOR.btnSave("Login Id"),"Save button"))
				flag=false;
		}*/
		return flag;
	}

	public static boolean addRegistrantLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
		}
		if(!Password.isEmpty())
		{
			if(!type(CommonOR.txtBox("Password"), Password, "Password Text Box"))
				flag=false;
		}
		if(!ConfirmPassword.isEmpty())
		{
			if(!type(EMT_RegistrantsOR.txtConfirmPassword, ConfirmPassword, "Confirm Password"))
				flag=false;
		}
		return flag;
	}

	public static boolean addRegistrantImage(String image)
	{
		boolean flag=true;
		if(!image.isEmpty())
		{
			driverM.findElement(CommonOR.txtBox("Registrant Image")).sendKeys(image);
		}
		return flag;
	}

	public static boolean editRegistrantCategories(String Industry,String Product,String JobRole,String Division,String Education,
			String Certifications,String Region,String Market,String CompanySize,String AnnualSales,
			String RegPrimaryCode) throws Throwable
			{
		boolean flag=true;
		if(!Industry.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop down")){
				if(!click(CommonOR.txtBoxData("Industry"),"Industry drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Industry"),"Industry Value"))
					flag=false;
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Industry"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Product.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Product"), "Product Drop down")){
				if(!click(CommonOR.txtBoxData("Product"),"Product drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Product"),"Product Value"))
					flag=false;
				if(verifyInDropDownList("Product", Product)){
					if(!selectByVisibleText(CommonOR.ddLabel("Product"), Product, "Product Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Product"), Product, "Product Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Product"), "Plus Button of Product")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Product"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "Job Role Drop down")){
				if(!click(CommonOR.txtBoxData("Job Role"),"Job Role drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Job Role"),"Job Role Value"))
					flag=false;
				if(verifyInDropDownList("Job Role", JobRole)){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"), JobRole, "JobRole Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Job Role"), JobRole, "JobRole Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Job Role"), "Plus Button of JobRole")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Job Role"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Division.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop down")){
				if(!click(CommonOR.txtBoxData("Division"),"Division drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Division"),"Division Value"))
					flag=false;
				if(verifyInDropDownList("Division", Division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), Division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), Division, "Division Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Division"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Education.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop down")){
				if(!click(CommonOR.txtBoxData("Education"),"Education drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Education"),"Education Value"))
					flag=false;
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education, "Education Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Education"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Certifications.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop down")){
				if(!click(CommonOR.txtBoxData("Certifications"),"Certifications drop down"))
					flag=false;
				if(!waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value"))
				{
					flag=false;
				}
				if(!click(CommonOR.ddRemoveValue("Certifications"),"Certifications Value"))
					flag=false;
				if(verifyInDropDownList("Certifications", Certifications)){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"), Certifications, "Certifications Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Certifications"), Certifications, "Certifications Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Certifications"), "Plus Button of Certifications")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Certifications"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Market.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Market"), "Market Drop down")){
				if(!click(CommonOR.txtBoxData("Market"),"Market drop down"))
					flag=false;
				if(!waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value"))
				{
					flag=false;
				}
				if(!click(CommonOR.ddRemoveValue("Market"),"Market Value"))
					flag=false;
				if(verifyInDropDownList("Market", Market)){
					if(!selectByVisibleText(CommonOR.ddLabel("Market"), Market, "Market Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Market"), Market, "Market Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Market"), "Plus Button of Market")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Market"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!CompanySize.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Company Size"), "Company Size Drop down")){
				if(!click(CommonOR.txtBoxData("Company Size"),"Company Size drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Company Size"),"Company Size Value"))
					flag=false;
				if(verifyInDropDownList("Company Size", CompanySize)){
					if(!selectByVisibleText(CommonOR.ddLabel("Company Size"), CompanySize, "CompanySize Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Company Size"), CompanySize, "CompanySize Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Company Size"), "Plus Button of CompanySize")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Company Size"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!AnnualSales.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Annual Sales"), "Annual Sales Drop down")){
				if(!click(CommonOR.txtBoxData("Annual Sales"),"Annual Sales drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Annual Sales"),"Annual Sales Value"))
					flag=false;
				if(verifyInDropDownList("Annual Sales", AnnualSales)){
					if(!selectByVisibleText(CommonOR.ddLabel("Annual Sales"), AnnualSales, "AnnualSales Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Annual Sales"), AnnualSales, "AnnualSales Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Annual Sales"), "Plus Button of AnnualSales")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Annual Sales"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!RegPrimaryCode.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Reg Primary Code"), "Reg Primary Code Drop down")){
				if(!click(CommonOR.txtBoxData("Reg Primary Code"),"Reg Primary Code drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Reg Primary Code"),"Reg Primary Code Value"))
					flag=false;
				if(verifyInDropDownList("Reg Primary Code", RegPrimaryCode)){
					if(!selectByVisibleText(CommonOR.ddLabel("Reg Primary Code"), RegPrimaryCode, "RegPrimaryCode Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Reg Primary Code"), RegPrimaryCode, "RegPrimaryCode Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Reg Primary Code"), "Plus Button of RegPrimaryCode")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Reg Primary Code"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		return flag;

			}
	public static boolean addRegistrantCategories(String Industry,String Product,String JobRole,String Division,String Education,
			String Certifications,String Region,String Market,String CompanySize,String AnnualSales,
			String RegPrimaryCode) throws Throwable
			{
		boolean flag=true;
		if(!Industry.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop down")){
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
			}
		}
		if(!Product.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Product"), "Product Drop down")){
				if(verifyInDropDownList("Product", Product)){
					if(!selectByVisibleText(CommonOR.ddLabel("Product"), Product, "Product Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Product"), Product, "Product Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Product"), "Plus Button of Product")){
						flag=false;
					}
				}
			}
		}
		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "Job Role Drop down")){
				if(verifyInDropDownList("Job Role", JobRole)){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"), JobRole, "JobRole Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Job Role"), JobRole, "JobRole Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Job Role"), "Plus Button of JobRole")){
						flag=false;
					}
				}
			}
		}
		if(!Division.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop down")){
				if(verifyInDropDownList("Division", Division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), Division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), Division, "Division Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
			}
		}
		if(!Education.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop down")){
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education, "Education Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
			}
		}
		if(!Certifications.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop down")){
				if(verifyInDropDownList("Certifications", Certifications)){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"), Certifications, "Certifications Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Certifications"), Certifications, "Certifications Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Certifications"), "Plus Button of Certifications")){
						flag=false;
					}
				}
			}
			/*if(!Region.isEmpty())
		{
			if(verifyInDropDownList("Region", Region)){
				if(!selectByVisibleText(CommonOR.ddLabel("Region"), Region, "Region Drop Down")){
					flag=false;
				}
			}
			else{
				if(!type(CommonOR.txtAddNew("Region"), Region, "Region Add New Text Box")){
					flag=false;
				}
				if(!click(CommonOR.btnPlus("Region"), "Plus Button of Region")){
					flag=false;
				}
			}
		}*/
			if(!Market.isEmpty())
			{
				if(isElementPresent(CommonOR.ddLabel("Market"), "Market Drop down")){
					if(verifyInDropDownList("Market", Market)){
						if(!selectByVisibleText(CommonOR.ddLabel("Market"), Market, "Market Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Market"), Market, "Market Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Market"), "Plus Button of Market")){
							flag=false;
						}
					}
				}
			}
			if(!CompanySize.isEmpty())
			{
				if(isElementPresent(CommonOR.ddLabel("Company Size"), "Company Size Drop down")){
					if(verifyInDropDownList("Company Size", CompanySize)){
						if(!selectByVisibleText(CommonOR.ddLabel("Company Size"), CompanySize, "CompanySize Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Company Size"), CompanySize, "CompanySize Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Company Size"), "Plus Button of CompanySize")){
							flag=false;
						}
					}
				}
			}
			if(!AnnualSales.isEmpty())
			{
				if(isElementPresent(CommonOR.ddLabel("Annual Sales"), "Annual Sales Drop down")){
					if(verifyInDropDownList("Annual Sales", AnnualSales)){
						if(!selectByVisibleText(CommonOR.ddLabel("Annual Sales"), AnnualSales, "AnnualSales Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Annual Sales"), AnnualSales, "AnnualSales Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Annual Sales"), "Plus Button of AnnualSales")){
							flag=false;
						}
					}
				}
			}
			if(!RegPrimaryCode.isEmpty())
			{
				if(isElementPresent(CommonOR.ddLabel("Reg Primary Code"), "Reg Primary Code Drop down")){
					if(verifyInDropDownList("Reg Primary Code", RegPrimaryCode)){
						if(!selectByVisibleText(CommonOR.ddLabel("Reg Primary Code"), RegPrimaryCode, "RegPrimaryCode Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Reg Primary Code"), RegPrimaryCode, "RegPrimaryCode Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Reg Primary Code"), "Plus Button of RegPrimaryCode")){
							flag=false;
						}
					}
				}
			}
		}
		return flag;

			}

	public static boolean editRegistrantAdditionalInfo(String Demo1,String Demo2,String Demo3,String Demo4,String Demo5,String Demo6,String Demo7,String Demo8,String Demo9,String Demo10,String AccessFlag,String ExecSummitFlag,String HotelConfirmation,String HotelSelection,
			String MealTypeFlag,String NickName,String PromotionCode,String RevRecFlag,String StaffMealFlag,String AddInfo4,String AddInfo5,String AddInfo1,String AddInfo2,String AddInfo3) throws InterruptedException, Throwable
			{
		boolean flag=true;
		if(!AddInfo1.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 1"),"Additional Info 1 Text Box"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 1"), AddInfo1, "Additional Info 1 Text Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("Additional Info 1"), "Save button");
			if(!click(CommonOR.btnSave("Additional Info 1"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!AddInfo2.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 2"),"Additional Info 2 Text Box"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 2"), AddInfo2, "Additional Info 2 Text Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("Additional Info 2"), "Save button");
			if(!click(CommonOR.btnSave("Additional Info 2"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!AddInfo3.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 3"),"Additional Info 3 Text Box"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 3"), AddInfo3, "Additional Info 3 Text Box"))
				flag=false;
			waitForVisibilityOfElement(CommonOR.btnSave("Additional Info 3"), "Save button");
			if(!click(CommonOR.btnSave("Additional Info 3"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(isElementPresent(CommonOR.txtArea("Additional Info 4"), "Add4"))
		{
			if(!AddInfo4.isEmpty())
			{
				if(!click(CommonOR.txtBoxData("Additional Info 4"),"Additional Info 4 Text Box"))
					flag=false;
				if(!type(CommonOR.txtArea("Additional Info 4"), AddInfo4, "Additional Info 4 Text Box"))
					flag=false;
				waitForVisibilityOfElement(CommonOR.btnSave("Additional Info 4"), "Save button");
				if(!click(CommonOR.btnSave("Additional Info 4"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(isElementPresent(CommonOR.txtArea("Additional Info 5"), "Add5"))
		{
			if(!AddInfo5.isEmpty())
			{
				if(!click(CommonOR.txtBoxData("Additional Info 5"),"Additional Info 5 Text Box"))
					flag=false;
				if(!type(CommonOR.txtArea("Additional Info 5"), AddInfo5, "Additional Info 5 Text Box"))
					flag=false;
				waitForVisibilityOfElement(CommonOR.btnSave("Additional Info 5"), "Save button");
				if(!click(CommonOR.btnSave("Additional Info 5"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo1.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 1"), "Demo 1"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 1"),"Demographic 1 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 1"),"Demographic 1 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 1", Demo1)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 1"), Demo1, "Demo1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 1"), Demo1, "Demo1 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 1"), "Plus Button of Demo1")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 1"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo2.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 2"), "Demo 2"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 2"),"Demographic 2 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 2"),"Demographic 2 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 2", Demo2)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 2"), Demo2, "Demo2 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 2"), Demo2, "Demo2 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 2"), "Plus Button of Demo2")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 2"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo3.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 3"), "Demo 3"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 3"),"Demographic 3 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 3"),"Demographic 3 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 3", Demo3)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 3"), Demo3, "Demo3 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 3"), Demo3, "Demo3 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 3"), "Plus Button of Demo3")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 3"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo4.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 4"), "Demo 4"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 4"),"Demographic 4 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 4"),"Demographic 4 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 4", Demo4)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 4"), Demo4, "Demo4 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 4"), Demo4, "Demo4 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 4"), "Plus Button of Demo4")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 4"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo5.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 5"), "Demo 5"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 5"),"Demographic 5 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 5"),"Demographic 5 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 5", Demo5)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 5"), Demo5, "Demo5 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 5"), Demo5, "Demo5 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 5"), "Plus Button of Demo5")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 5"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Demo6.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 6"), "Demo 6"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 6"),"Demographic 6 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 6"),"Demographic 6 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 6", Demo6)){
					System.out.println(Demo6);
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 6"), Demo6, "Demo6 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 6"), Demo6, "Demo6 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 6"), "Plus Button of Demo6")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 6"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Demo7.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 7"), "Demo 7"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 7"),"Demographic 7 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 7"),"Demographic 7 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 7", Demo7)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 7"), Demo7, "Demo7 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 7"), Demo7, "Demo7 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 7"), "Plus Button of Demo7")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 7"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Demo8.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 8"), "Demo 8"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 8"),"Demographic 8 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 8"),"Demographic 8 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 8", Demo8)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 8"), Demo8, "Demo8 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 8"), Demo8, "Demo8 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 8"), "Plus Button of Demo8")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 8"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Demo9.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 9"), "Demo 9"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 9"),"Demographic 9 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 9"),"Demographic 9 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 9", Demo9)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 9"), Demo9, "Demo9 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 9"), Demo9, "Demo9 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 9"), "Plus Button of Demo9")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 9"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Demo10.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 10"), "Demo 10"))
			{
				if(!click(CommonOR.txtBoxData("Demographic 10"),"Demographic 10 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 10"),"Demographic 10 Value"))
					flag=false;
				if(verifyInDropDownList("Demographic 10", Demo10)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 10"), Demo10, "Demo10 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 10"), Demo10, "Demo10 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 10"), "Plus Button of Demo10")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Demographic 10"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!AccessFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Access Flag"), "Access Flag"))
			{
				if(!click(CommonOR.txtBoxData("Access Flag"),"Access Flag drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Access Flag"),"Access Flag Value"))
					flag=false;
				if(verifyInDropDownList("Access Flag", AccessFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Access Flag"), AccessFlag, "Access Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Access Flag"), AccessFlag, "Access Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Access Flag"), "Plus Button of Access Flag")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Access Flag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!ExecSummitFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Exec Summit Flag"), "Exec Summit Flag"))
			{
				if(!click(CommonOR.txtBoxData("Exec Summit Flag"),"Exec Summit Flag drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Exec Summit Flag"),"Exec Summit Flag Value"))
					flag=false;
				if(verifyInDropDownList("Exec Summit Flag", ExecSummitFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Exec Summit Flag"), ExecSummitFlag, "Exec Summit Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Exec Summit Flag"), ExecSummitFlag, "Exec Summit Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Exec Summit Flag"), "Plus Button of Exec Summit Flag")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Exec Summit Flag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}}
		if(!HotelConfirmation.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Hotel Confirmation"), "Hotel Confirmation"))
			{
				if(!click(CommonOR.txtBoxData("Hotel Confirmation"),"Hotel Confirmation drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Hotel Confirmation"),"Hotel Confirmation Value"))
					flag=false;
				if(verifyInDropDownList("Hotel Confirmation", HotelConfirmation)){
					if(!selectByVisibleText(CommonOR.ddLabel("Hotel Confirmation"), HotelConfirmation, "Hotel Confirmation Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Hotel Confirmation"), HotelConfirmation, "Hotel Confirmation Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Hotel Confirmation"), "Plus Button of Hotel Confirmation")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Hotel Confirmation"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!HotelSelection.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Hotel Selection"), "Hotel Selection"))
			{
				if(!click(CommonOR.txtBoxData("Hotel Selection"),"Hotel Selection drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Hotel Selection"),"Hotel Selection Value"))
					flag=false;
				if(verifyInDropDownList("Hotel Selection", HotelSelection)){
					if(!selectByVisibleText(CommonOR.ddLabel("Hotel Selection"),HotelSelection , "Hotel Selection Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Hotel Selection"), HotelSelection, "Hotel Selection Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Hotel Selection"), "Plus Button of Hotel Selection")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Hotel Selection"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!MealTypeFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Meal Type Flag"), "Meal Type Flag"))
			{
				if(!click(CommonOR.txtBoxData("Meal Type Flag"),"Hotel Selection drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Meal Type Flag"),"Meal Type Flag Value"))
					flag=false;
				if(verifyInDropDownList("Meal Type Flag",MealTypeFlag )){
					if(!selectByVisibleText(CommonOR.ddLabel("Meal Type Flag"),MealTypeFlag , "Meal Type Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Meal Type Flag"), MealTypeFlag, "Meal Type Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Meal Type Flag"), "Plus Button of Meal Type Flag")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Meal Type Flag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!NickName.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Nick Name"), "Nick Name"))
			{
				if(!click(CommonOR.txtBoxData("Nick Name"),"Nick Name drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Nick Name"),"Nick Name Flag Value"))
					flag=false;
				if(verifyInDropDownList("Nick Name",NickName )){
					if(!selectByVisibleText(CommonOR.ddLabel("Nick Name"), NickName, "Nick Name Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Nick Name"),NickName , "Nick Name Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Nick Name"), "Plus Button of Nick Name")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Nick Name"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!PromotionCode.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Promotion Code"), "Promotion Code"))
			{
				if(!click(CommonOR.txtBoxData("Promotion Code"),"Promotion Code drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Promotion Code"),"Promotion Code Value"))
					flag=false;
				if(verifyInDropDownList("Promotion Code", PromotionCode)){
					if(!selectByVisibleText(CommonOR.ddLabel("Promotion Code"),PromotionCode , "Promotion Code Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Promotion Code"), PromotionCode, "Promotion Code Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Promotion Code"), "Plus Button of Promotion Code")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Promotion Code"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!RevRecFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Rev Rec Flag"), "Rev Rec Flag"))
			{
				if(!click(CommonOR.txtBoxData("Rev Rec Flag"),"Rev Rec Flag drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Rev Rec Flag"),"Rev Rec Flag Value"))
					flag=false;
				if(verifyInDropDownList("Rev Rec Flag", RevRecFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Rev Rec Flag"),RevRecFlag , "Rev Rec Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Rev Rec Flag"), RevRecFlag, "Rev Rec Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Rev Rec Flag"), "Plus Button of Rev Rec Flag")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Rev Rec Flag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!StaffMealFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Staff Meal Flag"), "Staff Meal Flag"))
			{
				if(!click(CommonOR.txtBoxData("Staff Meal Flag"),"Staff Meal Flag drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Staff Meal Flag"),"Staff Meal Flag Value"))
					flag=false;
				if(verifyInDropDownList("Staff Meal Flag", StaffMealFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Staff Meal Flag"),StaffMealFlag , "Staff Meal Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Staff Meal Flag"), StaffMealFlag, "Staff Meal Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Staff Meal Flag"), "Plus Button of Staff Meal Flag")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Staff Meal Flag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		return flag;
			}

	public static boolean addRegistrantAdditionalInfo(String Demo1,String Demo2,String Demo3,String Demo4,String Demo5,String Demo6,String Demo7,String Demo8,String Demo9,String Demo10,String AccessFlag,String ExecSummitFlag,String HotelConfirmation,String HotelSelection,
			String MealTypeFlag,String NickName,String PromotionCode,String RevRecFlag,String StaffMealFlag,String AddInfo4,String AddInfo5,String AddInfo1,String AddInfo2,String AddInfo3) throws InterruptedException, Throwable
			{
		boolean flag=true;
		if(!AddInfo1.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 1"), AddInfo1, "Additional Info 1 Text Box"))
				flag=false;
		}
		if(!AddInfo2.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 2"), AddInfo2, "Additional Info 2 Text Box"))
				flag=false;
		}
		if(!AddInfo3.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 3"), AddInfo3, "Additional Info 3 Text Box"))
				flag=false;
		}
		if(!AddInfo4.isEmpty())
		{
			if(isElementPresent(CommonOR.txtArea("Additional Info 4"), "Add4"))
			{
				if(!type(CommonOR.txtArea("Additional Info 4"), AddInfo4, "Additional Info 4 Text Box"))
					flag=false;
			}
		}

		if(!AddInfo5.isEmpty())
		{
			if(isElementPresent(CommonOR.txtArea("Additional Info 5"), "Add5"))
			{
				if(!type(CommonOR.txtArea("Additional Info 5"), AddInfo5, "Additional Info 5 Text Box"))
					flag=false;
			}
		}

		if(!Demo1.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 1"), "DEmo 1"))
			{
				if(verifyInDropDownList("Demographic 1", Demo1)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 1"), Demo1, "Demo1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 1"), Demo1, "Demo1 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 1"), "Plus Button of Demo1")){
						flag=false;
					}
				}
			}
		}

		if(!Demo2.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 2"), "Demo 2"))
			{
				if(verifyInDropDownList("Demographic 2", Demo2)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 2"), Demo2, "Demo2 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 2"), Demo2, "Demo2 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 2"), "Plus Button of Demo2")){
						flag=false;
					}
				}
			}
		}

		if(!Demo3.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 3"), "Demo 3"))
			{
				if(verifyInDropDownList("Demographic 3", Demo3)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 3"), Demo3, "Demo3 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 3"), Demo3, "Demo3 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 3"), "Plus Button of Demo3")){
						flag=false;
					}
				}
			}
		}

		if(!Demo4.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 4"), "Demo 4"))
			{
				if(verifyInDropDownList("Demographic 4", Demo4)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 4"), Demo4, "Demo4 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 4"), Demo4, "Demo4 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 4"), "Plus Button of Demo4")){
						flag=false;
					}
				}
			}
		}

		if(!Demo5.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 5"), "Demo 5"))
			{
				if(verifyInDropDownList("Demographic 5", Demo5)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 5"), Demo5, "Demo5 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 5"), Demo5, "Demo5 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 5"), "Plus Button of Demo5")){
						flag=false;
					}
				}
			}
		}
		if(!Demo6.isEmpty())
			if(isElementPresent(CommonOR.ddLabel("Demographic 6"), "Demo 6"))
			{

				{
					if(verifyInDropDownList("Demographic 6", Demo6)){
						System.out.println(Demo6);
						if(!selectByVisibleText(CommonOR.ddLabel("Demographic 6"), Demo6, "Demo6 Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Demographic 6"), Demo6, "Demo6 Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Demographic 6"), "Plus Button of Demo6")){
							flag=false;
						}
					}
				}
			}

		if(!Demo7.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 7"), "Demo 7"))
			{
				if(verifyInDropDownList("Demographic 7", Demo7)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 7"), Demo7, "Demo7 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 7"), Demo7, "Demo7 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 7"), "Plus Button of Demo7")){
						flag=false;
					}
				}
			}
		}
		if(!Demo8.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 8"), "Demo 8"))
			{

				if(verifyInDropDownList("Demographic 8", Demo8)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 8"), Demo8, "Demo8 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 8"), Demo8, "Demo8 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 8"), "Plus Button of Demo8")){
						flag=false;
					}
				}
			}
		}
		if(!Demo9.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 9"), "Demo 9"))
			{

				if(verifyInDropDownList("Demographic 9", Demo9)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 9"), Demo9, "Demo9 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 9"), Demo9, "Demo9 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 9"), "Plus Button of Demo9")){
						flag=false;
					}
				}
			}
		}
		if(!Demo10.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Demographic 10"), "Demo 10"))
			{

				if(verifyInDropDownList("Demographic 10", Demo10)){
					if(!selectByVisibleText(CommonOR.ddLabel("Demographic 10"), Demo10, "Demo10 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Demographic 10"), Demo10, "Demo10 Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Demographic 10"), "Plus Button of Demo10")){
						flag=false;
					}
				}
			}
		}
		if(!AccessFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Access Flag"), "Access Flag"))
			{

				if(verifyInDropDownList("Access Flag", AccessFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Access Flag"), AccessFlag, "Access Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Access Flag"), AccessFlag, "Access Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Access Flag"), "Plus Button of Access Flag")){
						flag=false;
					}
				}
			}
		}
		if(!ExecSummitFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Exec Summit Flag"), "Exec Summit Flag"))
			{

				if(verifyInDropDownList("Exec Summit Flag", ExecSummitFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Exec Summit Flag"), ExecSummitFlag, "Exec Summit Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Exec Summit Flag"), ExecSummitFlag, "Exec Summit Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Exec Summit Flag"), "Plus Button of Exec Summit Flag")){
						flag=false;
					}
				}
			}
		}
		if(!HotelConfirmation.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Hotel Confirmation"), "Hotel Confirmation"))
			{

				if(verifyInDropDownList("Hotel Confirmation", HotelConfirmation)){
					if(!selectByVisibleText(CommonOR.ddLabel("Hotel Confirmation"), HotelConfirmation, "Hotel Confirmation Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Hotel Confirmation"), HotelConfirmation, "Hotel Confirmation Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Hotel Confirmation"), "Plus Button of Hotel Confirmation")){
						flag=false;
					}
				}
			}
		}
		if(!HotelSelection.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Hotel Selection"), "Hotel Selection"))
			{

				if(verifyInDropDownList("Hotel Selection", HotelSelection)){
					if(!selectByVisibleText(CommonOR.ddLabel("Hotel Selection"),HotelSelection , "Hotel Selection Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Hotel Selection"), HotelSelection, "Hotel Selection Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Hotel Selection"), "Plus Button of Hotel Selection")){
						flag=false;
					}
				}
			}
		}
		if(!MealTypeFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Meal Type Flag"), "Meal Type Flag"))
			{

				if(verifyInDropDownList("Meal Type Flag",MealTypeFlag )){
					if(!selectByVisibleText(CommonOR.ddLabel("Meal Type Flag"),MealTypeFlag , "Meal Type Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Meal Type Flag"), MealTypeFlag, "Meal Type Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Meal Type Flag"), "Plus Button of Meal Type Flag")){
						flag=false;
					}
				}
			}
		}
		if(!NickName.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Nick Name"), "Nick Name"))
			{
				if(verifyInDropDownList("Nick Name",NickName )){
					if(!selectByVisibleText(CommonOR.ddLabel("Nick Name"), NickName, "Nick Name Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Nick Name"),NickName , "Nick Name Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Nick Name"), "Plus Button of Nick Name")){
						flag=false;
					}
				}
			}
		}
		if(!PromotionCode.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Promotion Code"), "Promotion Code"))
			{
				if(verifyInDropDownList("Promotion Code", PromotionCode)){
					if(!selectByVisibleText(CommonOR.ddLabel("Promotion Code"),PromotionCode , "Promotion Code Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Promotion Code"), PromotionCode, "Promotion Code Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Promotion Code"), "Plus Button of Promotion Code")){
						flag=false;
					}
				}
			}
		}

		if(!RevRecFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Rev Rec Flag"), "Rev Rec Flag"))
			{
				if(verifyInDropDownList("Rev Rec Flag", RevRecFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Rev Rec Flag"),RevRecFlag , "Rev Rec Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Rev Rec Flag"), RevRecFlag, "Rev Rec Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Rev Rec Flag"), "Plus Button of Rev Rec Flag")){
						flag=false;
					}
				}
			}
		}
		if(!StaffMealFlag.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Staff Meal Flag"), "Staff Meal Flag"))
			{
				if(verifyInDropDownList("Staff Meal Flag", StaffMealFlag)){
					if(!selectByVisibleText(CommonOR.ddLabel("Staff Meal Flag"),StaffMealFlag , "Staff Meal Flag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Staff Meal Flag"), StaffMealFlag, "Staff Meal Flag Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Staff Meal Flag"), "Plus Button of Staff Meal Flag")){
						flag=false;
					}
				}
			}
		}
		return flag;
			}

	public static boolean addRegistrantBadgePrinting(String PrintBadgeOnSubmit,String BadgePrinter) throws Throwable
	{
		boolean flag=true;
		if(PrintBadgeOnSubmit.equals("true"))
		{
			if(!click(CommonOR.chckPrintBadgeOnSubmit,"PrintBadgeOnSubmit"))
				flag=false;
		}
		if(!BadgePrinter.isEmpty())
		{
			if(!selectByVisibleText(CommonOR.ddBadgePrinter, BadgePrinter, "BadgePrinter Drop Down")){
				flag=false;
			}
		}
		return flag;
	}

	public static boolean successMessage() throws Throwable
	{
		boolean flag=true;
		if(isElementPresent(EMT_RegistrantsOR.txtMessage, "Message"))
		{
			String msg=getText(EMT_RegistrantsOR.txtMessage, "Message");
			System.out.println(msg);
		}
		else
		{
			flag=false;
		}
		return flag;
	}

	public static boolean clickRegistrant(String attendee,String first,String last,String company) throws Throwable
	{	
		boolean flag=true;

		if(!waitForElementPresent(EMT_RegistrantsOR.verifyRegistrant(attendee, first, last, company))){
			flag=false;
		}
		if(!click(EMT_RegistrantsOR.verifyRegistrant(attendee, first, last, company),"Registrant "+attendee))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyRegistrantInformation(String prefix,String attendee,String first,String CustomerRegistrantID,
			String last,String status,String AttendeeType,String SubAttendeeType) throws Throwable{
		boolean flag=true;

		if(!prefix.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Prefix"), prefix, "Prefix")){
				flag=false;
			}
		}
		if(!attendee.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Attendee #"), attendee, "Attendee #")){
				flag=false;
			}
		}
		if(!first.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("First"), first, "First")){
				flag=false;
			}
		}
		if(!CustomerRegistrantID.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Customer Registrant Id"), CustomerRegistrantID, "Customer Registrant Id")){
				flag=false;
			}
		}
		if(!last.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Last"), last, "Last")){
				flag=false;
			}
		}
		if(!status.isEmpty()){
			if(!verifyText(CommonOR.lblddValues("Status"), status, "Status")){
				flag=false;
			}
		}
		if(!AttendeeType.isEmpty()){
			if(!verifyText(CommonOR.lblddValues("Attendee Type"), AttendeeType, "Attendee Type")){
				flag=false;
			}
		}
		if(!SubAttendeeType.isEmpty()){
			if(!verifyText(CommonOR.lblddValues("Sub Attendee Type"), SubAttendeeType, "Sub Attendee Type")){
				flag=false;
			}
		}

		return flag;
	}

	public static boolean verifyRegistrantBio(String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM,String AboutMe) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Web URL"), WebURL, "Web URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Blog"), Blog, "Blog"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("LinkedIn URL"), LinkedInURL, "LinkedIn URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Facebook URL"), FacebookURL, "Facebook URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Twitter Account"), TwitterAcnt, "Twitter Account"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("IM"), IM, "IM"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("About Me"), AboutMe, "About Me"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyRegistrantContactInformation(String FullName,String BadgeFirstName,String Title,String Phone,String Mobile,String AltPhone,String Fax,
			String Company,String Address1,String Address2,String county,String region,String City,String CountryCode,
			String Country,String ZipCode,String PersonalEmail,String Email) throws Throwable
			{
		boolean flag=true;
		if(!FullName.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Full Name"), FullName, "Full Name"))
			{
				flag=false;
			}
		}
		/*if(!BadgeFirstName.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Badge First Name"), BadgeFirstName, "Badge First Name"))
			{
				flag=false;
			}
		}*/
		if(!Title.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Title"), Title, "Title"))
			{
				flag=false;
			}
		}
		if(!Phone.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Phone"), Phone, "Phone"))
			{
				flag=false;
			}
		}
		if(!Mobile.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Mobile Phone"), Mobile, "Mobile Phone"))
			{
				flag=false;
			}
		}
		if(!AltPhone.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Alt Phone"), AltPhone, "Alt Phone"))
			{
				flag=false;
			}
		}
		if(!Fax.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Fax"), Fax, "Fax"))
			{
				flag=false;
			}
		}
		if(!Company.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Company"), Company, "Company"))
			{
				flag=false;
			}
		}
		if(!Address1.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Address1"), Address1, "Address1"))
			{
				flag=false;
			}
		}
		if(!Address2.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Address2"), Address2, "Address2"))
			{
				flag=false;
			}
		}
		if(!county.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("County"),county, "County"))
			{
				flag=false;
			}
		}
		if(!region.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Region"), region, "Region"))
			{
				flag=false;
			}
		}
		if(!City.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("City"), City, "City"))
			{
				flag=false;
			}
		}
		if(!CountryCode.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Country Code"), CountryCode, "Country Code"))
			{
				flag=false;
			}
		}
		if(!Country.isEmpty()){
			if(!verifyText(CommonOR.lblddValues("Country"), Country, "Country"))
			{
				flag=false;
			}
		}
		if(!ZipCode.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Zip Code"), ZipCode, "Zip Code"))
			{
				flag=false;
			}
		}
		if(!PersonalEmail.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Personal Email"), PersonalEmail, "Personal Email"))
			{
				flag=false;
			}
		}
		if(!Email.isEmpty()){
			if(!verifyText(CommonOR.lbltxtValues("Email"), Email, "Email"))
			{
				flag=false;
			}
		}
		return flag;

			}

	public static boolean verifyRegistrantLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Login Id"),LoginID , "Login Id"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyRegistrantCategories(String Industry,String Product,String JobRole,String Division,String Education,
			String Certifications,String Region,String Market,String CompanySize,String AnnualSales,
			String RegPrimaryCode) throws Throwable
			{
		boolean flag=true;
		if(!verifyText(CommonOR.lblddValues("Industry"), Industry, "Industry"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Product"), Product, "Product"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Job Role"),JobRole, "Job Role"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Division"), Division, "Division"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Education"),Education, "Education"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Certifications"), Certifications, "Certifications"))
		{
			flag=false;
		}
		/*if(!verifyText(CommonOR.lblddValues("Region"), Region, "Region"))
		{
			flag=false;
		}*/
		if(!verifyText(CommonOR.lblddValues("Market"), Market, "Market"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Company Size"), CompanySize, "Company Size"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Annual Sales"), AnnualSales, "Annual Sales"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Reg Primary Code"), RegPrimaryCode, "Reg Primary Code"))
		{
			flag=false;
		}
		return flag;
			}

	public static boolean verifyRegistrantAdditionalInfo(String Demo1,String Demo2,String Demo3,String Demo4,String Demo5,
			String Demo6,String Demo7,String Demo8,String Demo9,String Demo10,String AccessFlag,String ExecSummitFlag,String HotelConfirmation,String HotelSelection,
			String MealTypeFlag,String NickName,String PromotionCode,String RevRecFlag,String StaffMealFlag,String AddInfo4,String AddInfo5,String AddInfo1,String AddInfo2,String AddInfo3) throws Throwable
			{
		boolean flag=true;
		if(!verifyText(CommonOR.lblddValues("Demographic 1"), Demo1, "Demographic 1"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 2"), Demo2, "Demographic 2"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 3"), Demo3, "Demographic 3"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 4"), Demo4, "Demographic 4"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 5"), Demo5, "Demographic 5"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 6"), Demo6, "Demographic 6"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 7"), Demo7, "Demographic 7"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 8"), Demo8, "Demographic 8"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 9"), Demo9, "Demographic 9"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Demographic 10"), Demo10, "Demographic 10"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 1"), AddInfo1, "Additional Info 1"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 2"), AddInfo2, "Additional Info 2"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 3"), AddInfo3, "Additional Info 3"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 4"), AddInfo4, "Additional Info 4"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 5"), AddInfo5, "Additional Info 5"))
		{
			flag=false;
		}
		if (!AccessFlag.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Access Flag"), AccessFlag,
					"Access Flag")) {
				flag = false;
			}
		}
		if (!ExecSummitFlag.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Exec Summit Flag"),
					ExecSummitFlag, "Exec Summit Flag")) {
				flag = false;
			}
		}
		if (!HotelConfirmation.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Hotel Confirmation"),
					HotelConfirmation, "Hotel Confirmation")) {
				flag = false;
			}
		}
		if (!HotelSelection.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Hotel Selection"),
					HotelSelection, "Hotel Selection")) {
				flag = false;
			}
		}
		if (!MealTypeFlag.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Meal Type Flag"),
					MealTypeFlag, "Meal Type Flag")) {
				flag = false;
			}
		}
		if (!NickName.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Nick Name"), NickName,
					"Nick Name")) {
				flag = false;
			}
		}
		if (!PromotionCode.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Promotion Code"),
					PromotionCode, "Promotion Code")) {
				flag = false;
			}
		}
		if (!RevRecFlag.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Rev Rec Flag"), RevRecFlag,
					"Rev Rec Flag")) {
				flag = false;
			}
		}
		if (!StaffMealFlag.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Staff Meal Flag"),
					StaffMealFlag, "Staff Meal Flag")) {
				flag = false;
			}
		}
		return flag;
			}

	public static boolean registrantVerification(String first) throws Throwable
	{
		boolean flag=true;
		waitForElementPresent(CommonOR.lnkSearch);
		if(!click(CommonOR.lnkSearch,"Search Link"))
		{
			flag=false;
		}
		if(!type(CommonOR.txtSearch,first," First Name"))
		{
			flag=false;
		}
		if(!click(CommonOR.btnSearch,"Search Button"))
		{
			flag=false;
		}
		waitForElementPresent(EMT_RegistrantsOR.tblResults);
		getText(EMT_RegistrantsOR.tblResults, "results are displayed");
		return flag;
	}

	//EMT-Exhibitors
	public static boolean AddExhibitorInformation(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,String URL,String FacebookPage,String Description) throws Throwable
	{
		boolean flag=true;
		if(!ExhibitName.isEmpty())
		{
			if(!type(CommonOR.txtExhibitName, ExhibitName, "Exhibit Name"))
				flag=false;
		}
		if(!CustomerExhibitID.isEmpty())
		{
			if(!type(CommonOR.txtBox("Customer Exhibit Id"), CustomerExhibitID, "Customer Exhibit Id Text Box"))
				flag=false;
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Status"), "status Drop Down")){
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Status"), status, "status Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Status"), "Plus Button of status")){
						flag=false;
					}
				}
			}
		}
		if(!MsgExhibitEmail.isEmpty())
		{
			if(!type(CommonOR.txtBox("Msg Exhibit Email"), MsgExhibitEmail, "Msg Exhibit Email Text Box"))
				flag=false;
		}
		if(!Company.isEmpty())
		{
			if(!type(CommonOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
		}
		if(!Booth.isEmpty())
		{
			if(!type(CommonOR.txtBox("Booth #"),Booth , "Booth # Text Box"))
				flag=false;
		}
		if(!URL.isEmpty())
		{
			if(!type(CommonOR.txtBox("URL"), URL, "URL Text Box"))
				flag=false;
		}
		if(!FacebookPage.isEmpty())
		{
			if(!type(CommonOR.txtBox("Facebook Page"), FacebookPage, "FaceBook Page Text Box"))
				flag=false;
		}
		if(!Description.isEmpty())
		{
			if(!type(CommonOR.txtDescription, Description, "Description Text Box"))
				flag=false;
		}

		return flag;
	}

	public static boolean verifyExhibitorInformation(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,String URL,String FacebookPage,String Description) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Exhibit Name"), ExhibitName, "Exhibit Name"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Customer Exhibit Id"), CustomerExhibitID, "Customer Exhibit Id"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Status"), status, "Status"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Msg Exhibit Email"), MsgExhibitEmail, "Msg Exhibit Email"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Company"), Company, "Company"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Booth #"), Booth, "Booth #"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("URL"), URL, "URL"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Facebook Page"), FacebookPage, "Facebook Page"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Description"), Description, "Description"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean AddExhibitorContactInformation(String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode) throws Throwable
	{
		boolean flag=true;
		if(!ContactFirst.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact First"), ContactFirst, "Contact First Text Box"))
				flag=false;
		}
		if(!ContactCompany.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Company"), ContactCompany, "Contact Company Text Box"))
				flag=false;
		}
		if(!ContactLast.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Last"), ContactLast, "Contact Last Text Box"))
				flag=false;
		}
		if(!Email.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Email"), Email, "Contact Email Text Box"))
				flag=false;
		}
		if(!Phone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Phone"), Phone, "Contact Phone Text Box"))
				flag=false;
		}
		if(!AltPhone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Alt Phone"), AltPhone, "Contact Alt Phone Text Box"))
				flag=false;
		}
		if(!ContactAddress1.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Address 1"), ContactAddress1, "Contact Address 1 Text Box"))
				flag=false;
		}
		if(!City.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact City"), City, "Contact City"))
				flag=false;
		}
		if(!ContactAddress2.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Address 2"), ContactAddress2, "Contact Address 2 Text Box"))
				flag=false;
		}
		if(!Region.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Region"), Region, "Contact Region Text Box"))
				flag=false;
		}
		if(!ZipCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Zip Code"), ZipCode, "Contact Zip Code Text Box"))
				flag=false;
		}
		if(!CountryCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Contact Country Code"), CountryCode, "Contact Country Code Text Box"))
				flag=false;
		}
		return flag;
	}

	public static boolean verifyExhibitorContactInformation(String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact First"), ContactFirst, "Contact First"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Company"), ContactCompany, "Contact Company"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Last"), ContactLast, "Contact Last"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Email"), Email, "Contact Email"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Phone"), Phone, "Contact Phone"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Alt Phone"), AltPhone, "Contact Alt Phone"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Address 1"), ContactAddress1, "Contact Address 1"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact City"), City, "Contact City"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Address 2"), ContactAddress2, "Contact Address 2"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Region"), Region, "Contact Region"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Zip Code"), ZipCode, "Contact Zip Code"))
		{
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Contact Country Code"), CountryCode, "Contact Country Code"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean AddExhibitorLoginInformation(String LoginID,String password,String confirmpassword) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
		}
		if(!password.isEmpty())
		{
			if(!type(CommonOR.txtBox("Password"), password, "Password text Box"))
				flag=false;
		}
		if(!confirmpassword.isEmpty())
		{
			if(!type(EMT_AddExhibitorsOR.txtConfirmPassword, confirmpassword, "Confirm Password Text Box"))
				flag=false;
		}
		return flag;
	}

	public static boolean verifyExhibitorLoginInformation(String LoginID) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Login Id"),LoginID , "Login Id"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean AddExhibitorCategories(String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY) throws Throwable
	{
		boolean flag=true;
		if(!Category.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Category"), "Category Drop Down")){
				if(verifyInDropDownList("Category", Category)){
					if(!selectByVisibleText(CommonOR.ddLabel("Category"), Category, "Category Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Category"), Category, "Category Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Category"), "Plus Button of Category")){
						flag=false;
					}
				}
			}
		}
		if(!FocusArea.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Focus Area"), "FocusArea Drop Down")){
				if(verifyInDropDownList("Focus Area", FocusArea)){
					if(!selectByVisibleText(CommonOR.ddLabel("Focus Area"), FocusArea, "FocusArea Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Focus Area"), FocusArea, "FocusArea Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Focus Area"), "Plus Button of FocusArea")){
						flag=false;
					}
				}
			}
		}
		if(!Industry.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop Down")){
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
			}
		}
		if(!IndustryMarket.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry Market"), "IndustryMarket Drop Down")){
				if(verifyInDropDownList("Industry Market", IndustryMarket)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry Market"), IndustryMarket, "IndustryMarket Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry Market"), IndustryMarket, "IndustryMarket Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry Market"), "Plus Button of IndustryMarket")){
						flag=false;
					}
				}
			}
		}
		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "JobRole Drop Down")){
				if(verifyInDropDownList("Job Role", JobRole)){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"), JobRole, "JobRole Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Job Role"), JobRole, "JobRole Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Job Role"), "Plus Button of JobRole")){
						flag=false;
					}
				}
			}
		}
		if(!SpecialityProduct.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Specialty Product"), "SpecialityProduct Drop Down")){
				if(verifyInDropDownList("Specialty Product", SpecialityProduct)){
					if(!selectByVisibleText(CommonOR.ddLabel("Specialty Product"), SpecialityProduct, "SpecialityProduct Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Specialty Product"), SpecialityProduct, "SpecialityProduct Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Specialty Product"), "Plus Button of SpecialityProduct")){
						flag=false;
					}
				}
			}
		}
		if(!Education.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop Down")){
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education, "Education Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
			}
		}
		if(!Certifications.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop Down")){
				if(verifyInDropDownList("Certifications", Certifications)){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"), Certifications, "Certifications Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Certifications"), Certifications, "Certifications Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Certifications"), "Plus Button of Certifications")){
						flag=false;
					}
				}
			}
		}
		if(!Division.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop Down")){
				if(verifyInDropDownList("Division", Division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), Division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), Division, "Division Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
			}
		}
		if(!SalesRegion.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Sales Region"), "SalesRegion Drop Down")){
				if(verifyInDropDownList("Sales Region", SalesRegion)){
					if(!selectByVisibleText(CommonOR.ddLabel("Sales Region"), SalesRegion, "SalesRegion Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Sales Region"), SalesRegion, "SalesRegion Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Sales Region"), "Plus Button of SalesRegion")){
						flag=false;
					}
				}
			}
		}
		if(!MapHeight.isEmpty())
		{
			if(!type(CommonOR.txtBox("Map Height"), MapHeight, "Map Height Text Box"))
				flag=false;
		}
		if(!MapWidth.isEmpty())
		{
			if(!type(CommonOR.txtBox("Map Width"), MapWidth, "Map Width text Box"))
				flag=false;
		}
		if(!MapX.isEmpty())
		{
			if(!type(CommonOR.txtBox("Map X"), MapX, "Map X Text Box"))
				flag=false;
		}
		if(!MapY.isEmpty())
		{
			if(!type(CommonOR.txtBox("Map Y"), MapY, "Map Y text Box"))
				flag=false;
		}
		if(!MApRotation.isEmpty())
		{
			if(!type(CommonOR.txtBox("Map Rotation"), MApRotation, "Map Rotation text Box"))
				flag=false;
		}
		return flag;
	}

	public static boolean verifyExhibitorCategories(String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lblddValues("Category"), Category, "Category"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Focus Area"), FocusArea, "Focus Area"))
		{
			flag=false;
		}
		if (!Industry.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Industry"), Industry,
					"Industry")) {
				flag = false;
			}
		}
		if (!IndustryMarket.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Industry Market"),
					IndustryMarket, "Industry Market")) {
				flag = false;
			}
		}
		if (!JobRole.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Job Role"), JobRole,
					"Job Role")) {
				flag = false;
			}
		}
		if (!SpecialityProduct.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Specialty Product"),
					SpecialityProduct, "Specialty Product")) {
				flag = false;
			}
		}
		if (!Education.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Education"), Education,
					"Education")) {
				flag = false;
			}
		}
		if (!Certifications.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Certifications"),
					Certifications, "Certifications")) {
				flag = false;
			}
		}
		if (!Division.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Division"), Division,
					"Division")) {
				flag = false;
			}
		}
		if (!SalesRegion.isEmpty()) {
			if (!verifyText(CommonOR.lblddValues("Sales Region"), SalesRegion,
					"Sales Region")) {
				flag = false;
			}
		}
		if (MapHeight.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lbltxtValues("Map Height"),
					MapHeight, "Map Height")) {
				flag = false;
			}
		}
		if (MapWidth.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lbltxtValues("Map Width"), MapWidth,
					"Map Width")) {
				flag = false;
			}
		}
		if (MapX.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lbltxtValues("Map X"), MapX, "Map X")) {
				flag = false;
			}
		}
		if (MapY.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lbltxtValues("Map Y"), MapY, "Map Y")) {
				flag = false;
			}
		}
		if (MApRotation.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lbltxtValues("Map Rotation"),
					MApRotation, "Map Rotation")) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean AddExhibitorLogo(String image)
	{
		boolean flag=true;
		if(!image.isEmpty())
		{
			driverM.findElement(CommonOR.txtBox("Logo Name")).sendKeys(image);
		}
		return flag;
	}

	public static boolean exhibitorSuccessMessage() throws Throwable
	{
		boolean flag=true;
		if(isElementPresent(EMT_AddExhibitorsOR.txtSuccessMessage, "Success Message"))
		{
			getText(EMT_AddExhibitorsOR.txtSuccessMessage, "Success Message");
		}
		else
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyExhibitor(String exhibitName,String Booth,String Company) throws Throwable
	{

		waitForElementPresent(EMT_AddExhibitorsOR.verifyExhibitor(exhibitName,Booth,Company));
		if(click(EMT_AddExhibitorsOR.verifyExhibitor(exhibitName,Booth,Company),exhibitName)){
			Reporters.SuccessReport("Verify Page", "Navigated to Exhibitor details page");
		}
		return true;
	}
	//EMT-Sessions
	public static boolean NavigateToAddSessionsPage() throws Throwable
	{
		boolean flag=true;
		if(!js_click(EMT_SessionsOR.lnkAddNewRecord, "Add New Record Link")){
			flag=false;
		}

		if(!js_TriggerOnClickEvent("addNewRecord();", "Add Button")){
			flag=false;
		}
		return flag;
	}

	public static boolean addSession() throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_SessionsOR.lnkViewMore,"View More link"))
		{
			flag=false;
		}
		if(!switchToFrameByIndex(0))
		{
			flag=false;
		}
		waitForElementPresent(EMT_SessionsOR.tabSessions);
		if(!click(EMT_SessionsOR.tabSessions,"Sessions tab"))
		{
			flag=false;
		}
		waitForElementPresent(EMT_SessionsOR.lnkAddNewRecord);
		if(!click(EMT_SessionsOR.lnkAddNewRecord,"Add new Record link"))
		{
			flag=false;
		}
		waitForElementPresent(EMT_SessionsOR.btnAdd);
		if(!click(EMT_SessionsOR.btnAdd,"Add button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean sessionVerification(String Title) throws Throwable
	{
		boolean flag=true;
		waitForElementPresent(EMT_SessionsOR.lnkSearch);
		if(!click(EMT_SessionsOR.lnkSearch,"Search link"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtSearch,Title,"Type session Title"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSearch,"Search button"))
		{
			flag=false;
		}

		return flag;
	}

	/**
	 * This function is used to add Session Details
	 * @param Title- reads Session Title from excel file 
	 * @param Status- reads Session Status from excel file 
	 * @param Room- reads Room allocated for the session from excel file
	 * @param date- reads Date for the session from excel file
	 * @param StartTime- reads StartTime for the session from excel file
	 * @param EndTime- reads EndTime for the session from excel file
	 * @param ScanStartTime- reads ScanStartTime for the session from excel file
	 * @param ScanEndTime- reads ScanEndTime for the session from excel file
	 * @param Survey- reads Survey details for the session from excel file
	 * @return boolean(True or False)
	 * @throws Throwable
	 */

	public static boolean addSessionDetails(String Title,String Status,String Room,String date,String StartTime,String EndTime,
			String ScanStartTime,String ScanEndTime,String Survey) throws Throwable
			{

		boolean flag=true;
		waitForElementPresent(EMT_SessionsOR.txtTitle);
		if(!type(EMT_SessionsOR.txtTitle,Title," title for the session"))
		{
			flag=false;
		}
		if(!selectByVisibleText(EMT_SessionsOR.ddStatus, Status, "Status drop down"))
		{
			flag=false;
		}
		if(verifyInDropDownList("Room", Room)){
			if(!selectByVisibleText(EMT_SessionsOR.ddRoom, Room, "Select a room for session"))
			{
				flag=false;
			}
		}
		else{
			if(!type(EMT_SessionsOR.txtAddNew("Room"), Room,"Add New Text Box"))
			{
				flag=false;
			}
			if(!click(EMT_SessionsOR.btnPlus("Room"), "Plus Button of Room"))
			{
				flag=false;
			}
		}
		if(!selectByVisibleText(EMT_SessionsOR.ddDate, date, "Select Date for session"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtStartTime, StartTime, " Start Time"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtBox("End Time"), EndTime, " End Time"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtScanStartTime,ScanStartTime, " Scan Start Time"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtScanEndTime, ScanEndTime, " Scan End Time"))
		{
			flag=false;
		}
		if(!selectByVisibleText(EMT_SessionsOR.ddSurvey, Survey, "Select a survey"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSubmit,"Submit button"))
		{
			flag=false;
		}
		return flag;
			}

	/**
	 * This Function is used to add Session Information
	 * @param Session- Reads Session# number from Excel file
	 * @param CustomerSessionId- Reads CustomerSessionId number from Excel File
	 * @param Title- Reads Sessions Title from excel file
	 * @param Status- Reads Status of the Session from Excel file
	 * @param Location- Reads Location from Excel File
	 * @param Room- Reads Room from Excel File
	 * @param Active- Reads Active state of Session from Excel File
	 * @param Enrollable- Checks the Session Enrollment state
	 * @param Date- Reads Date of the Session from Excel file
	 * @param Capacity-Reads Capacity from Excel file
	 * @param StartTime-Reads Session Start Time from Excel file
	 * @param EndTime- Reads Session End Time from Excel file
	 * @param ScanStartTime- Reads Session Scan StartTime from Excel File
	 * @param ScanEndTime- Reads Session Scan End Time from Excel file
	 * @param Survey- Reads Survey from Excel file
	 * @param HashTag- Reads Hashtag from Excel file
	 * @param Description- Reads Description from Excel file
	 * @param PresentationURL-Reads PresentationURL from Excel file
	 * @return boolean (True or false)
	 * @throws Throwable
	 */
	public static boolean addSessionInformation(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL) throws Throwable{
		boolean flag=true;

		if(!Session.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("Session #"),Session,"Session # Text Box")){
				flag=false;
			}
		}
		if(!CustomerSessionId.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("Customer Session Id"),CustomerSessionId,"Customer Session Id Text Box")){
				flag=false;
			}
		}
		if(!Title.isEmpty()){
			if(!type(EMT_SessionsOR.txtTitle,Title," title for the session")){
				flag=false;
			}
		}
		if(!Status.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status Drop down")){
				if(verifyInDropDownList("Status", Status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), Status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Status"), Status,"Status Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
			}
		}
		if(!Location.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Location"), "Location Drop down")){
				if(verifyInDropDownList("Location", Location)){
					if(!selectByVisibleText(CommonOR.ddLabel("Location"), Location, "Location Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Location"), Location,"Location Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Location"), "Plus Button of Location")){
						flag=false;
					}
				}
			}
		}

		if(!Room.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Room"),"Room Drop Down")){
				if(verifyInDropDownList("Room", Room)){
					if(!selectByVisibleText(EMT_SessionsOR.ddRoom, Room, "Room Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Room"), Room,"Room Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Room"), "Plus Button of Room")){
						flag=false;
					}
				}
			}
		}
		if(!Active.isEmpty()){
			if(!click(EMT_SessionsOR.chckActive,"Active Check Box")){
				flag=false;
			}
		}

		if(!Enrollable.isEmpty()){
			if(!click(EMT_SessionsOR.chckEnrollable,"Enrollable Check Box")){
				flag=false;
			}
		}

		if(!Date.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Date"), "Date Drop Down")){
				if(verifyInDropDownList("Date", Date)){
					if(!selectByVisibleText(CommonOR.ddLabel("Date"), Date, "Date Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Date"), Date, "Date Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Date"), "Plus Button of Date")){
						flag=false;
					}
				}
			}
		}


		if(!Capacity.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("Capacity"),Capacity,"Capacity Text Box")){
				flag=false;
			}
		}

		if(!StartTime.isEmpty()){
			Thread.sleep(1000);
			if(!js_type(EMT_SessionsOR.startTime, StartTime, StartTime))
			{
				flag=false;
			}
		}
		if(!EndTime.isEmpty()){
			if(!js_type(EMT_SessionsOR.endTime, EndTime, EndTime))
			{
				flag=false;
			}
		}
		if(!ScanStartTime.isEmpty()){
			if(!js_type(EMT_SessionsOR.scanStartTime, ScanStartTime, ScanStartTime))
			{
				flag=false;
			}
		}

		if(!ScanEndTime.isEmpty()){
			if(!js_type(EMT_SessionsOR.scanEndTime, ScanEndTime, ScanEndTime))
			{
				flag=false;
			}
		}

		if(!Survey.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Survey"), "Survey Drop Down")){
				if(verifyInDropDownList("Survey", Survey)){
					if(!selectByVisibleText(CommonOR.ddLabel("Survey"), Survey, "Survey Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Survey"), Survey, "Survey Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Survey"), "Plus Button of Survey")){
						flag=false;
					}
				}
			}
		}

		if(!HashTag.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("Hashtag"), HashTag, "Hashtag Text Box")){
				flag=false;
			}
		}
		if(!Description.isEmpty()){
			if(!type(EMT_SessionsOR.txtDescription,Description,"Description Text Area Box")){
				flag=false;
			}
		}
		if(!PresentationURL.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("Presentation URL"),PresentationURL,"PresentationURL Text Box")){
				flag=false;
			}
		}
		return flag;
	}

	public static boolean editSessionInformation(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL) throws Throwable{
		boolean flag=true;
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		if(!Title.isEmpty()){
			if(!click(CommonOR.txtBoxData("Title"),"Title Text Box Data"))
				flag=false;
			if(!type(EMT_SessionsOR.txtTitle,Title," title for the session"))
				flag=false;
			if(!click(CommonOR.btnSave("Title"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Status.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status Drop down")){
				if(!click(CommonOR.txtBoxData("Status"),"Status drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Status"),"Status Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(verifyInDropDownList("Status", Status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), Status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Status"), Status,"Status Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Status"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Status"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.btnSave("Status"),"Save button");
			}
		}
		if(!Location.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Location"), "Location Drop down")){
				if(!click(CommonOR.txtBoxData("Location"),"Location drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Location"),"Location Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(verifyInDropDownList("Location", Location)){
					if(!selectByVisibleText(CommonOR.ddLabel("Location"), Location, "Location Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Location"), Location,"Location Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Location"), "Plus Button of Location")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Location"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Location"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.btnSave("Location"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!Room.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Room"),"Room Drop Down")){
				if(!click(CommonOR.txtBoxData("Room"),"Location drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Room"),"Room Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(verifyInDropDownList("Room", Room)){
					if(!selectByVisibleText(EMT_SessionsOR.ddRoom, Room, "Room Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Room"), Room,"Room Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Room"), "Plus Button of Room")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Room"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Room"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Active.isEmpty()){
			if(!click(EMT_SessionsOR.chckActive,"Active Check Box")){
				flag=false;
			}
		}

		if(!Enrollable.isEmpty()){
			if(!click(EMT_SessionsOR.chckEnrollable,"Enrollable Check Box")){
				flag=false;
			}
		}

		if(!Date.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Date"), "Date Drop Down")){
				if(!click(CommonOR.txtBoxData("Date"),"Date drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Date"),"Date Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(verifyInDropDownList("Date", Date)){
					if(!selectByVisibleText(CommonOR.ddLabel("Date"), Date, "Date Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Date"), Date, "Date Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Date"), "Plus Button of Date")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Date"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Date"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}


		if(!Capacity.isEmpty()){
			if(!click(CommonOR.txtBoxData("Capacity"),"Capacity"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("Capacity"),Capacity,"Capacity Text Box")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Capacity"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}

		if(!StartTime.isEmpty()){
			if(!click(CommonOR.txtBoxData("Start Time"),"Start Time"))
				flag=false;
			if(!type(EMT_SessionsOR.txtStartTime, StartTime, "Start Time Box")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Start Time"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!EndTime.isEmpty()){
			if(!click(CommonOR.txtBoxData("End Time"),"End Time"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("End Time"), EndTime, "End Time Box")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("End Time"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!ScanStartTime.isEmpty()){
			if(!click(CommonOR.txtBoxData("Scan Start Time"),"Scan Start Time"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("Scan Start Time"), ScanStartTime, "Scan Start Time Box")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Scan Start Time"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}

		if(!ScanEndTime.isEmpty()){
			if(!click(CommonOR.txtBoxData("Scan End Time"),"Scan End Time"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("Scan End Time"), ScanEndTime, "Scan End Time Box")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Scan End Time"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}

		if(!Survey.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Survey"), "Survey Drop Down")){
				if(!click(CommonOR.txtBoxData("Survey"),"Survey drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Survey"),"Survey Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(verifyInDropDownList("Survey", Survey)){
					if(!selectByVisibleText(CommonOR.ddLabel("Survey"), Survey, "Survey Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Survey"), Survey, "Survey Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Survey"), "Plus Button of Survey")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Survey"),"Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Survey"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!HashTag.isEmpty()){
			if(!click(CommonOR.txtBoxData("Hashtag"),"Hashtag"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("Hashtag"), HashTag, "Hashtag Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Hashtag"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Description.isEmpty()){
			if(!click(CommonOR.txtBoxData("Description"),"Description"))
				flag=false;
			if(!type(EMT_SessionsOR.txtDescription,Description,"Description Text Area Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Description"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!PresentationURL.isEmpty()){
			if(!click(CommonOR.txtBoxData("Presentation URL"),"Presentation URL"))
				flag=false;
			if(!type(EMT_SessionsOR.txtBox("Presentation URL"),PresentationURL,"PresentationURL Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Presentation URL"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		return flag;
	}

	public static boolean addSessionInfo(String Title,String Status,String Active,String StartTime,String EndTime) throws Throwable
	{
		boolean flag=true;
		if(!Title.isEmpty()){
			if(!type(EMT_SessionsOR.txtTitle,Title," title for the session")){
				flag=false;
			}
		}
		if(!Status.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddStatus, "Status")){
				if(verifyInDropDownList("Status", Status)){
					if(!selectByVisibleText(EMT_SessionsOR.ddStatus, Status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Status"), Status,"Status Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
			}
		}

		if(verifyInDropDownList("Date", sessionStartDate)){
			if(!selectByVisibleText(EMT_SessionsOR.ddDate, sessionStartDate, "Date Drop Down")){
				flag=false;
			}
		}
		else{
			if(!type(EMT_SessionsOR.txtAddNew("Date"), sessionStartDate, "Date Add New Text Box")){
				flag=false;
			}
			if(!click(EMT_SessionsOR.btnPlus("Date"), "Plus Button of Date")){
				flag=false;
			}
		}

		if(!Active.isEmpty()){
			if(!click(EMT_SessionsOR.chckActive,"Active Check Box")){
				flag=false;
			}
		}

		if(!StartTime.isEmpty()){
			if(!type(EMT_SessionsOR.txtStartTime, StartTime, "Start Time Text Box")){
				flag=false;
			}
		}
		if(!EndTime.isEmpty()){
			if(!type(EMT_SessionsOR.txtBox("End Time"), EndTime, "End Time Text Box")){
				flag=false;
			}
		}
		return flag;
	}

	/**
	 * This Function is used to add Session Categories
	 * @param Program- Reads program from Excel file
	 * @param Track- Reads Track from Excel file
	 * @param SubTrack- Reads SubTrack from Excel file
	 * @param TargetRole- Reads TargetRole from Excel file
	 * @param Audience- Reads audience from Excel file
	 * @param ByInviteOnly- Reads ByInviteOnly from Excel file
	 * @param Timeslot- Reads TimeSlot from Excel file
	 * @param Market- Reads Market from Excel file
	 * @param Industry- Reads Industry from Excel file
	 * @param Product- Reads Product from Excel file
	 * @param ContentCategory- Reads ContentCategory from Excel file
	 * @param SessionTheme- Reads SessionTheme from Excel file
	 * @param ContentLevel- Reads ContentLevel from Excel file
	 * @param SessionType- Reads SessionType from Excel file
	 * @return boolean (true or false)
	 * @throws Throwable
	 */
	public static boolean addCategories(String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType) throws Throwable{
		boolean flag=true;

		if(!Program.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Program"), "Program")){
				if(verifyInDropDownList("Program", Program)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Program"), Program, "Program Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Program"), Program, "Program Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Program"), "Plus Button of Program")){
						flag=false;
					}
				}
			}
		}


		if(!Track.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Track"), "Track")){
				if(verifyInDropDownList("Track", Track)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Track"), Track, "Track Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Track"), Track, "Track Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Track"), "Plus Button of Track")){
						flag=false;
					}
				}
			}
		}

		if(!SubTrack.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Sub-Track"), "Sub-Track Drop Down")){
				if(verifyInDropDownList("Sub-Track", SubTrack)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Sub-Track"), SubTrack, "Sub-Track Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Sub-Track"), SubTrack, "Sub-Track Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Sub-Track"), "Plus Button of Sub-Track")){
						flag=false;
					}
				}
			}
		}

		if(!TargetRole.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Target Role"), "Target Role Drop Down")){
				if(verifyInDropDownList("Target Role", TargetRole)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Target Role"), TargetRole, "Target Role Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Target Role"), TargetRole, "Target Role Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Target Role"), "Plus Button of Target Role")){
						flag=false;
					}
				}
			}
		}

		if(!Audience.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Audience"), "Audience Drop Down")){
				if(verifyInDropDownList("Audience", Audience)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Audience"), Audience, "Audience Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Audience"), Audience, "Audience Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Audience"), "Plus Button of Audience")){
						flag=false;
					}
				}
			}
		}

		if(!ByInviteOnly.isEmpty()){
			if(verifyInDropDownList("By Invite Only", ByInviteOnly)){
				if(!selectByVisibleText(EMT_SessionsOR.ddLabel("By Invite Only"), ByInviteOnly, "By Invite Only Drop Down")){
					flag=false;
				}
			}
			else{
				if(!type(EMT_SessionsOR.txtAddNew("By Invite Only"), ByInviteOnly, "By Invite Only Add New Text Box")){
					flag=false;
				}
				if(!click(EMT_SessionsOR.btnPlus("By Invite Only"), "Plus Button of By Invite Only")){
					flag=false;
				}
			}
		}

		/*if(!Timeslot.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Timeslot"), "Timeslot Drop Down")){
				if(verifyInDropDownList("Timeslot", Timeslot)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Timeslot"), Timeslot, "Timeslot Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Timeslot"), Timeslot, "Timeslot Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Timeslot"), "Plus Button of Timeslot")){
						flag=false;
					}
				}
			}
		}*/

		if(!Market.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Market"), "Market Drop Down")){
				if(verifyInDropDownList("Market", Market)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Market"), Market, "Market Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Market"), Market, "Market Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Market"), "Plus Button of Market")){
						flag=false;
					}
				}
			}
		}

		if(!Industry.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Industry"), "Industry Drop Down")){
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
			}
		}

		if(!Product.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Product"), "Product Drop Down")){
				if(verifyInDropDownList("Product", Product)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Product"), Product, "Product Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Product"), Product, "Product Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Product"), "Plus Button of Product")){
						flag=false;
					}
				}
			}
		}

		if(!ContentCategory.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Content Category"), "Content Category Drop Down")){
				if(verifyInDropDownList("Content Category", ContentCategory)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Content Category"), ContentCategory, "Content Category Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Content Category"), ContentCategory, "Content Category Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Content Category"), "Plus Button of Content Category")){
						flag=false;
					}
				}
			}
		}

		if(!SessionTheme.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Theme"), "Session Theme Drop Down")){
				if(verifyInDropDownList("Session Theme", SessionTheme)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Theme"), SessionTheme, "Session Theme Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Theme"), SessionTheme, "Session Theme Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Theme"), "Plus Button of Session Theme")){
						flag=false;
					}
				}
			}
		}

		if(!ContentLevel.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Content Level"), "Content Level Drop Down")){
				if(verifyInDropDownList("Content Level", ContentLevel)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Content Level"), ContentLevel, "Content Level Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Content Level"), ContentLevel, "Content Level Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Content Level"), "Plus Button of Content Level")){
						flag=false;
					}
				}
			}
		}

		if(!SessionType.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Type"), "Session Type Drop Down")){
				if(verifyInDropDownList("Session Type", SessionType)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Type"), SessionType, "Session Type Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Type"), SessionType, "Session Type Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Type"), "Plus Button of Session Type")){
						flag=false;
					}
				}
			}
		}

		return flag;
	}

	public static boolean addAdditionalInformation(String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub,String Keywords,String ImportIntCustomSQLEnabled,String Paper_Type,String NowNextIPAddress,String Field2014) throws Throwable
			{
		boolean flag=true;
		if(!Additional_Info_1.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 1"), Additional_Info_1, "Additional Info 1 Text Area")){
				flag=false;
			}
		}
		if(!Additional_Info_2.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 2"), Additional_Info_2, "Additional Info 2 Text Area")){
				flag=false;
			}
		}
		if(!Additional_Info_3.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 3"), Additional_Info_3, "Additional Info 3 Text Area")){
				flag=false;
			}
		}
		if(!Additional_Info_4.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 4"), Additional_Info_4, "Additional Info 4 Text Area")){
				flag=false;
			}
		}
		if(!Additional_Info_5.isEmpty())
		{
			if(!type(CommonOR.txtArea("Additional Info 5"), Additional_Info_5, "Additional Info 5 Text Area")){
				flag=false;
			}
		}
		if(!Keywords.isEmpty())
		{
			if(!type(CommonOR.txtArea("Keywords"), Keywords, "Keywords Text Area")){
				flag=false;
			}
		}
		if(!Demo1.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 1"), "Demographic 1 Drop Down")){
				if(verifyInDropDownList("Demographic 1", Demo1)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 1"), Demo1, "Demographic 1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 1"), Demo1, "Demographic 1 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 1"), "Plus Button of Demographic 1")){
						flag=false;
					}
				}
			}
		}
		if(!Demo3.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 3"), "Demographic 3 Drop Down")){
				if(verifyInDropDownList("Demographic 3", Demo3)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 3"), Demo3, "Demographic 3 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 3"), Demo3, "Demographic 3 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 3"), "Plus Button of Demographic 3")){
						flag=false;
					}
				}
			}
		}
		if(!Demo4.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 4"), "Demographic 4 Drop Down")){
				if(verifyInDropDownList("Demographic 4", Demo4)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 4"), Demo4, "Demographic 4 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 4"), Demo4, "Demographic 4 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 4"), "Plus Button of Demographic 4")){
						flag=false;
					}
				}
			}
		}
		if(!Demo7.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 7"), "Demographic 7 Drop Down")){
				if(verifyInDropDownList("Demographic 7", Demo7)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 7"), Demo7, "Demographic 7 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 7"), Demo7, "Demographic 7 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 7"), "Plus Button of Demographic 7")){
						flag=false;
					}
				}
			}
		}
		if(!Demo9.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 9"), "Demographic 9 Drop Down")){
				if(verifyInDropDownList("Demographic 9", Demo9)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 9"), Demo9, "Demographic 9 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 9"), Demo9, "Demographic 9 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 9"), "Plus Button of Demographic 9")){
						flag=false;
					}
				}
			}
		}
		if(!Demo11.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 11"), "Demographic 11 Drop Down")){
				if(verifyInDropDownList("Demographic 11", Demo11)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 11"), Demo11, "Demographic 11 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 11"), Demo11, "Demographic 11 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 11"), "Plus Button of Demographic 11")){
						flag=false;
					}
				}
			}
		}
		if(!Demo12.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 12"), "Demographic 12 Drop Down")){
				if(verifyInDropDownList("Demographic 12", Demo12)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 12"), Demo12, "Demographic 12 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 12"), Demo12, "Demographic 12 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 12"), "Plus Button of Demographic 12")){
						flag=false;
					}
				}
			}
		}
		if(!Demo14.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 14"), "Demographic 14 Drop Down")){
				if(verifyInDropDownList("Demographic 14", Demo14)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 14"), Demo14, "Demographic 14 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 14"), Demo14, "Demographic 14 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 14"), "Plus Button of Demographic 14")){
						flag=false;
					}
				}
			}
		}
		if(!SessionCode.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Code"), "Session Code Drop Down")){
				if(verifyInDropDownList("Session Code", SessionCode)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Code"), SessionCode, "Session Code Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Code"), SessionCode, "Session Code Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Code"), "Plus Button of Session Code")){
						flag=false;
					}
				}
			}
		}
		if(!Topic_Tag.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Topic Tag"), "Topic Tag Drop Down")){
				if(verifyInDropDownList("Topic Tag", Topic_Tag)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Topic Tag"), Topic_Tag, "Topic Tag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Topic Tag"), Topic_Tag, "Topic Tag Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Topic Tag"), "Plus Button of Topic Tag")){
						flag=false;
					}
				}
			}
		}
		if(!CustomSql1.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("CustomSql1"), "CustomSql1 Drop Down")){
				if(verifyInDropDownList("CustomSql1", CustomSql1)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("CustomSql1"), CustomSql1, "CustomSql1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("CustomSql1"), CustomSql1, "CustomSql1 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("CustomSql1"), "Plus Button of CustomSql1")){
						flag=false;
					}
				}
			}
		}
		if(!ProgTrackSub.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("ProgTrackSub"), "CustomSql1 Drop Down")){
				if(verifyInDropDownList("ProgTrackSub", ProgTrackSub)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("ProgTrackSub"), ProgTrackSub, "CustomSql1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("ProgTrackSub"), ProgTrackSub, "ProgTrackSub Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("ProgTrackSub"), "Plus Button of ProgTrackSub")){
						flag=false;
					}
				}
			}
		}
		if(!ImportIntCustomSQLEnabled.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("ImportIntCustomSQLEnabled"), "ImportIntCustomSQLEnabled Drop Down")){
				if(verifyInDropDownList("ImportIntCustomSQLEnabled", ImportIntCustomSQLEnabled)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("ImportIntCustomSQLEnabled"), ImportIntCustomSQLEnabled, "ImportIntCustomSQLEnabled Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("ImportIntCustomSQLEnabled"), ImportIntCustomSQLEnabled, "ImportIntCustomSQLEnabled Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("ImportIntCustomSQLEnabled"), "Plus Button of ImportIntCustomSQLEnabled")){
						flag=false;
					}
				}
			}
		}
		if(!Paper_Type.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Paper Type"), "Paper_Type Drop Down")){
				if(verifyInDropDownList("Paper Type", Paper_Type)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Paper Type"), Paper_Type, "Paper_Type Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Paper Type"), Paper_Type, "Paper_Type Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Paper Type"), "Plus Button of Paper_Type")){
						flag=false;
					}
				}
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		}
		if(!NowNextIPAddress.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Now Next IP Address"), "NowNextIPAddress Drop Down")){
				if(verifyInDropDownList("Now Next IP Address", NowNextIPAddress)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Now Next IP Address"), NowNextIPAddress, "NowNextIPAddress Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Now Next IP Address"), NowNextIPAddress, "NowNextIPAddress Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Now Next IP Address"), "Plus Button of NowNextIPAddress")){
						flag=false;
					}
				}
			}
		}
		if(!Field2014.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("2014"), "2014 Drop Down")){
				if(verifyInDropDownList("2014", Field2014)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("2014"), Field2014, "2014 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("2014"), Field2014, "2014 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("2014"), "Plus Button of 2014")){
						flag=false;
					}
				}
			}
		}
		return flag;
			}


	public static boolean verifyInDropDownList(String LabelText, String TextToVerifyInSelect) throws InterruptedException{

		boolean flag=false;
		try {
			List<WebElement> listItems=driverM.findElements(By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//select/option"));
			//List<WebElement> listItems=driverM.findElements(By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//select/option"));
			System.out.println(listItems.size());
			for (WebElement searchInList : listItems) {
				if(searchInList.getText().equalsIgnoreCase(TextToVerifyInSelect)){
					flag = true;
					break;
				}
				else{
					flag=false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean verifyTextInList(String TextToVerifyInList)
	{
		boolean flag=true;
		try {
			List<WebElement> listItems=driverM.findElements(By.xpath("//p[@class='error']/following-sibling::ul/li"));
			System.out.println(listItems.size());
			for (WebElement searchInList : listItems) {
				if(searchInList.getText().equalsIgnoreCase(TextToVerifyInList)){
					flag = true;
					break;
				}
				else{
					flag=false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean verifyInDropDownList(By locator, String TextToVerifyInSelect) throws Throwable{

		boolean flag=false;
		try {
			waitForVisibilityOfElement(locator, "");
			List<WebElement> listItems=driverM.findElements(locator);
			System.out.println(listItems.size());
			for (WebElement searchInList : listItems) {
				if(searchInList.getText().equalsIgnoreCase(TextToVerifyInSelect)){
					flag = true;
					break;
				}
				else{
					flag=false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean verifyInTable(String LabelText, String TextToVerifyInSelect) throws InterruptedException{

		boolean flag=false;
		try {
			List<WebElement> listItems=driverM.findElements(By.xpath("//div[h2[text()='"+LabelText+"']]/following-sibling::div//tbody/tr"));
			System.out.println(listItems.size());
			for (WebElement searchInList : listItems) {
				Thread.sleep(2000);
				if(searchInList.getText().contains(TextToVerifyInSelect)){
					flag = true;
					break;
				}
				else{
					flag=false;
				}
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean verify_In_Table(By locator,String TextToVerifyIntable) throws InterruptedException
	{
		boolean flag=true;
		try {
			List<WebElement> listItems=driverM.findElements(locator);
			System.out.println(listItems.size());
			for (WebElement searchInList : listItems) {
				if(searchInList.getText().contains(TextToVerifyIntable)){
					flag = true;
					break;
				}
				else{
					flag=false;
				}
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean sessionDetailsVerification(String sessionTitle,String StartTime,String EndTime,String Date) throws Throwable
	{
		boolean flag=true;

		if(isElementPresent(EMT_SessionsOR.verifySession(sessionTitle, StartTime, EndTime, Date),"Element not present")){
			return flag;	
		}
		else{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySessionInformation(String Session,String CustomerSessionId, String Title,String Status,String Location,
			String Room, String Active,String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,
			String ScanEndTime,String Survey,String Hashtag,String Description,String PresentationURL) throws Throwable{
		boolean flag=true;

		if(!verifyText(EMT_SessionsOR.lbltxtValues("Session #"), Session, "Session #")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Customer Session Id"), CustomerSessionId, "Customer Session Id")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Title"), Title, "Title")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Status"), Status, "Status")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Location"), Location, "Location")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Room"), Room, "Room")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Active"), Active, "Active")){
			flag=false;
		}
		if(!Enrollable.isEmpty()){
			if(!verifyText(EMT_SessionsOR.lbltxtValues("Enrollable"), Enrollable, "Enrollable")){
				flag=false;
			}
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Date"), Date, "Date")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Capacity"), Capacity, "Capacity")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Start Time"), StartTime, "Start Time")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("End Time"), EndTime, "End Time")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Scan Start Time"), ScanStartTime, "Scan Start Time")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Scan End Time"), ScanEndTime, "Scan End Time")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Survey"), Survey, "Survey")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Hashtag"), Hashtag, "Hashtag")){
			Reporters.failureReport("", "We are unable to add Hash Tag even Manually");
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Description"), Description, "Description")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Presentation URL"), PresentationURL, "Presentation URL")){
			flag=false;
		}
		return flag;
	}

	public static boolean verifyCategoriesInformation(String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType) throws Throwable{
		boolean flag = true;

		if(!verifyText(EMT_SessionsOR.lblddValues("Target Role"), TargetRole, "Target Role")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Audience"), Audience, "Audience")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("By Invite Only"), ByInviteOnly,"By Invite Only")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Market"), Market,"Market")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Industry"), Industry,"Industry")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Product"), Product,"Product")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Content Category"), ContentCategory,"Content Category")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Session Theme"), SessionTheme,"Session Theme")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Content Level"), ContentLevel,"Content Level")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lblddValues("Session Type"), SessionType,"Session Type")){
			flag=false;
		}
		return flag;
	}

	public static boolean verifyAdditionalInformation(String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub,String Keywords,String ImportIntCustomSQLEnabled,String Paper_Type,String NowNextIPAddress,String Field2014) throws Throwable
			{
		boolean flag=true;
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 1"),Additional_Info_1 , "Additional Info 1")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 2"),Additional_Info_2 , "Additional Info 2")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 3"),Additional_Info_3 , "Additional Info 3")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 4"),Additional_Info_4 , "Additional Info 4")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Additional Info 5"),Additional_Info_5 , "Additional Info 5")){
			flag=false;
		}
		if(!verifyText(EMT_SessionsOR.lbltxtValues("Keywords"),Keywords , "Keywords")){
			flag=false;
		}

		if (!Demo1.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 1"), Demo1,
					"Demographic 1")) {
				flag = false;
			}
		}
		if (!Demo3.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 3"), Demo3,
					"Demographic 3")) {
				flag = false;
			}
		}
		if (!Demo4.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 4"), Demo4,
					"Demographic 4")) {
				flag = false;
			}
		}
		if (!Demo7.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 7"), Demo7,
					"Demographic 7")) {
				flag = false;
			}
		}
		if (!Demo9.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 9"), Demo9,
					"Demographic 9")) {
				flag = false;
			}
		}
		if (!Demo11.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 11"),
					Demo11, "Demographic 11")) {
				flag = false;
			}
		}
		if (!Demo12.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 12"),
					Demo12, "Demographic 12")) {
				flag = false;
			}
		}
		if (!Demo14.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Demographic 14"),
					Demo14, "Demographic 14")) {
				flag = false;
			}
		}
		if (!SessionCode.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Session Code"),
					SessionCode, "Session Code")) {
				flag = false;
			}
		}
		if (!CustomSql1.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("CustomSql1"),
					CustomSql1, "CustomSql1")) {
				flag = false;
			}
		}
		if (!ProgTrackSub.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("ProgTrackSub"),
					ProgTrackSub, "ProgTrackSub")) {
				flag = false;
			}
		}
		if (!ImportIntCustomSQLEnabled.isEmpty()) {
			if (!verifyText(
					EMT_SessionsOR.lblddValues("ImportIntCustomSQLEnabled"),
					ImportIntCustomSQLEnabled, "ImportIntCustomSQLEnabled")) {
				flag = false;
			}
		}
		if (!Paper_Type.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Paper Type"),
					Paper_Type, "Paper Type")) {
				flag = false;
			}
		}
		if (!NowNextIPAddress.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("Now Next IP Address"),
					NowNextIPAddress, "Now Next IP Address")) {
				flag = false;
			}
		}
		if (!Field2014.isEmpty()) {
			if (!verifyText(EMT_SessionsOR.lblddValues("2014"), Field2014,
					"2014")) {
				flag = false;
			}
		}
		return flag;
			}

	public static boolean sessionEnrollment(String sessionTitle,String StartTime,String EndTime,String Date,String Registrant) throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_SessionsOR.verifySession(sessionTitle, StartTime, EndTime, Date) , sessionTitle))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_SessionsOR.lnkManageSessionEnrollment,"Session enrollment link"))
		{
			flag=false;
		}
		if(!js_click(EMT_SessionsOR.lnkManageSessionEnrollment,"Session enrollment link"))
		{
			flag=false;
		}
		if(!waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window, "Frame window"))
		{
			flag=false;
		}

		if(!waitForVisibilityOfElement(EMT_SessionsOR.txtSessionSearch, "Search box"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.txtSessionSearch,"Search text box"))
		{
			flag=false;
		}

		if(!type(EMT_SessionsOR.txtSessionSearch,Registrant," Registrant name"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSessionSearch,"Search button"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.chckSelect, ""+Registrant+" registrant"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnRegistrantAdd,"Add button"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSave,"Save button"))
		{
			flag=false;
		}
		driverM.switchTo().defaultContent();
		return flag;
	}

	public static boolean verifySessionEnrollment(String Registrant) throws Throwable
	{
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Session Enrollment"), "Loading");
		System.out.println(getText(EMT_SessionsOR.verifySessionEnrollment(Registrant), ""));
		System.out.println(Registrant+" Excel one");
		boolean flag=true;
		if(verifyInTable("Session Enrollment", Registrant))
		{
			flag=true;
		}
		else{
			flag=false;
		}

		return flag;
	}

	/*public static boolean deleteSessionEnrollment(String Registrant) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_SessionsOR.lnkManageSessionEnrollment, "Session enrollment link"))
		{
			flag=false;
		}
		if(!js_click(EMT_SessionsOR.lnkManageSessionEnrollment,"Session enrollment link"))
		{
			flag=false;
		}
		if(!waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window, "Frame window"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnDeleteRegistrant,"Delete the registrant"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSave,"Save button"))
		{
			flag=false;
		}
		if(!verifyTextPresent("No records to display."))
		{
			flag=false;
		}
		return flag;
	}*/

	//EMT-ManageSessionAttendance
	public static boolean sessionAttendance(String Registrant) throws Throwable
	{
		boolean flag=true;
		/*waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Sessions"), "Loading");
		if(!click(EMT_SessionsOR.verifySession(sessionTitle, StartTime, EndTime, Date) , ""+sessionTitle+" Session created"))
		{
			flag=false;
		}*/
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Manage Session Attendance"), "Loading");
		if(!waitForVisibilityOfElement(EMT_SessionsOR.lnkManageSessionAttendance, "Session attendance link"))
		{
			flag=false;
		}
		if(!js_click(EMT_SessionsOR.lnkManageSessionAttendance,"Session attendance link"))
		{
			flag=false;
		}
		if(!waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window, "Frame window"))
		{
			flag=false;
		}

		if(!waitForVisibilityOfElement(EMT_SessionsOR.txtSessionSearch, "Search box"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.txtSessionSearch,"search text box"))
		{
			flag=false;
		}
		if(!type(EMT_SessionsOR.txtSessionSearch,Registrant," Search data "+Registrant))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSessionSearch,"search button"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.chckSelect,"Check "+Registrant))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnRegistrantAdd,"Add button"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSave,"Save button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySessionAttendance(String Registrant) throws Throwable
	{
		Thread.sleep(3000);
		driverM.switchTo().defaultContent();
		waitForVisibilityOfElement(EMT_SessionsOR.verifySessionEnrollment(Registrant),"Existence of Registrant");
		return isElementPresent(EMT_SessionsOR.verifySessionEnrollment(Registrant), "Element not present");
	}

	/*	public static boolean deleteSessionAttendance(String Registrant) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_SessionsOR.lnkManageSessionAttendance,"session attendance link"))
		{
			flag=false;
		}
		if(!js_click(EMT_SessionsOR.lnkManageSessionAttendance,"session attendance link"))
		{
			flag=false;
		}
		if(!waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window, "Frame window"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnDeleteRegistrant,"Delete "+Registrant+" registrant"))
		{
			flag=false;
		}
		if(!click(EMT_SessionsOR.btnSave,"Save button"))
		{
			flag=false;
		}
		if(!verifyTextPresent("No records to display."))
		{
			flag=false;
		}
		return flag;
	}
	 */
	//EMT-Rooms
	public static boolean addRoom(String name,String capacity) throws Throwable
	{

		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_RoomsOR.lnkAddNewRecord, "Add new Record link"))
		{
			flag=false;
		}
		if(!js_click(EMT_RoomsOR.lnkAddNewRecord,"Add new Record link"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_RoomsOR.btnAdd, "Add button"))
		{
			flag=false;
		}
		if(!js_click(EMT_RoomsOR.btnAdd,"Add button"))
		{
			flag=false;
		}
		if(!type(EMT_RoomsOR.txtName,name," name for the room"))
		{
			flag=false;
		}
		if(!type(EMT_RoomsOR.txtCapacity,capacity," capacity for the room"))
		{
			flag=false;
		}
		/*click(EMT_RoomsOR.ddLocation,"Click on Location drop down");
		selectByIndex(EMT_RoomsOR.ddLocation, 0, "Choose location for room");*/
		if(!selectByVisibleText(EMT_RoomsOR.ddLocation, "location 1", "Select the zone"))
		{
			flag=false;
		}
		if(!click(EMT_RoomsOR.btnSubmit,"Submit button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean addEmailCampaignsType(String CampaignType,String ViewBy) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.lnkNewEmailCampign, "Start a new Email Campaign link"))
		{
			flag=false;
		}
		if(!js_click(EMT_EmailCampignsOR.lnkNewEmailCampign,"Start a new Email Campaign link"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.ddCampignType, "Select the campaign type")){
			flag=false;
		}
		if(!selectByVisibleText(EMT_EmailCampignsOR.ddCampignType, CampaignType, "Select the campaign type"))
		{
			flag=false;
		}
		if(!selectByVisibleText(EMT_EmailCampignsOR.ddViewBy, ViewBy, "Select view by option"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.btnAdd, "Add Button")){
			flag=false;
		}
		if(!js_click(EMT_EmailCampignsOR.btnAdd,"Add Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean chooseEmailCampaignsContent(String Content) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.ddContent, "Content Type"))
		{
			flag=false;
		}
		if(!selectByVisibleText(EMT_EmailCampignsOR.ddContent, Content, "Choose Content Type"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean addEmailCampaignsDetails(String Name) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.txtName,"Campaign name"))
		{
			flag=false;
		}
		if(!type(EMT_EmailCampignsOR.txtName,Name," Campaign name"))
		{
			flag=false;
		}
		if(!click(EMT_EmailCampignsOR.chckboxOption,"Select an option"))
		{
			flag=false;
		}
		if(!click(EMT_EmailCampignsOR.btnNext,"Next button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean confirmEmailCampaigns() throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_EmailCampignsOR.btnConfirm,"Confirm button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean redirectEmailCampaign() throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.txtTitle,"Page Title"))
		{
			flag=false;
		}
		getText(EMT_EmailCampignsOR.txtTitle, "Redirect message is displayed");
		return flag;
	}

	public static boolean verifyAddedEmailCampaign(String Name) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.btnRefresh,"Refresh Button"))
		{
			flag=false;
		}
		if(!js_click(EMT_EmailCampignsOR.btnRefresh,"Refresh button"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_EmailCampignsOR.verifyEmailcampaign(Name),"Email Campaign Added"))
		{
			flag=false;
		}
		return flag;
	}


	//EMT-My Tabs
	static String message="";

	public static boolean myTabs() throws Throwable
	{
		boolean flag=true;
		if(!clickTabFromViewMore(CommonOR.lnkAdmin, "Admin Tab"))
		{
			flag=false;
		}
		if(!js_click(EMT_MyTabsOR.lnkMyTabs,"Edit My Tabs link"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyMyTabs() throws Throwable
	{
		boolean flag=true;
		Thread.sleep(1000);
		if(!verifyTextPresent("My Tabs"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean setToVisible(String AvailableText) throws Throwable
	{
		message="";
		boolean flag=true;
		if(verifyInAvailableTabList(AvailableText)){
			clickAdd(AvailableText);
			message=AvailableText+" is set to visible";
		}
		else if(verifyInVisibleTabList(AvailableText)){
			message=AvailableText+" is already set to visible";		
		}
		else{
			message=AvailableText+" is not a valid string";
			flag=false;
		}
		return flag;
	}

	public static boolean setToAvailable(String VisibleText) throws Throwable
	{
		message="";
		boolean flag=true;
		if(verifyInAvailableTabList(VisibleText)){
			message=VisibleText+" is set to available";
		}
		else if(verifyInVisibleTabList(VisibleText)){
			clickRemove(VisibleText);
			message=VisibleText+" is already set to visible";		
		}
		else{
			message=VisibleText+" is not a valid string";
			flag=false;
		}
		return flag;

	}


	public static String getTabStatusMessage(){

		return message;
	}

	public static void clickAdd(String AvailableText) throws Throwable{
		selectByVisibleText(By.xpath("//select[@id='my-tabs-hidden']"),AvailableText , "");
		click(By.id("add-visisble-tab"), "");
	}

	public static void clickRemove(String AvailableText) throws Throwable{
		selectByVisibleText(By.xpath("//select[@id='my-tabs-visible']"),AvailableText , "");
		click(By.id("remove-visisble-tab"), "");
	}

	public static boolean verifyInAvailableTabList(String Text){

		boolean flag=true;
		List<WebElement> AvailableList=driverM.findElements(EMT_MyTabsOR.lstAvailableTabs);

		for (WebElement avail : AvailableList) {

			if(avail.getText().equalsIgnoreCase(Text)){

				flag=true;
				break;
			}

			else{
				flag=false;
			}
		}

		return flag;
	}

	public static boolean verifyInVisibleTabList(String Text){

		boolean flag=true;
		List<WebElement> VisibleList=driverM.findElements(EMT_MyTabsOR.lstVisibleTabs);

		for (WebElement vis : VisibleList) {

			if(vis.getText().equalsIgnoreCase(Text)){

				flag=true;
				break;
			}

			else{
				flag=false;
			}
		}

		return flag;
	}

	public static boolean clickSubmitBtn() throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_MyTabsOR.btnSubmit,"Submit button"))
		{
			flag=false;
		}
		return flag;
	}

	//iConnect-EMT(Manage Users)
	/**
	 * This function is used to navigate to Manage Users page
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean lnkManageUsers() throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_ViewOrEditUserOR.lnkManageUsers,"Manage Users"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_ViewOrEditUserOR.txtManageUsersTitle,"Manage Users Title"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to verify Users profile
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean verifyUserProfile() throws Throwable
	{
		boolean flag=true;
		String profile=getText(EMT_ViewOrEditUserOR.lnkUserProfile, "Gets the users profile");
		System.out.println(profile);
		if(!click(EMT_ViewOrEditUserOR.lnkUserFromTable, "A user from table"))
		{
			flag=false;
		}
		String user_profile=getText(EMT_ViewOrEditUserOR.txtProfileType, "Profile that is checked for selected user");
		System.out.println(user_profile);
		if(profile.equalsIgnoreCase(user_profile))
		{
			return flag;
		}
		else{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to verify Next Button
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean btnNext() throws Throwable
	{
		boolean flag=true;
		if(isElementPresent(EMT_ViewOrEditUserOR.btnNext, "Check for Next button"))
		{
			String loginID=getText(EMT_ViewOrEditUserOR.txtUserLoginId, "Gets the user log in id");
			click(EMT_ViewOrEditUserOR.btnNext,"Next link");
			String nxtUserLoginID=getText(EMT_ViewOrEditUserOR.txtUserLoginId, "Gets the next page user's login id ");
			if(!loginID.equalsIgnoreCase(nxtUserLoginID))
			{
				return flag;
			}
			else
			{
				flag=false;
			}
		}
		else
		{
			System.out.println("Next button is present: Means only one user is available in table");
			flag=true;
		}
		return flag;
	}

	/**
	 * This function is used to Filter the user Profiles with System Administration
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean filterProfile() throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_ViewOrEditUserOR.lnkManageUsers,"Manage Users"))
		{
			flag=false;
		}
		if(!click(EMT_ViewOrEditUserOR.ddProfile,"Profile drop down"))
		{
			flag=false;
		}
		if(!selectByVisibleText(EMT_ViewOrEditUserOR.ddProfile, "System Administrator", "Select System Admintrator from Profile Drop down"))
		{
			flag=false;
		}
		if(!click(EMT_ViewOrEditUserOR.btnFilter,"Filter Button"))
		{
			flag=false;
		}
		return flag;

	}

	/**
	 * This Function is used to verify the warning message in Login User Profile
	 * @param uname(Login Username)
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean loginUserProfile(String uname) throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_ViewOrEditUserOR.User(uname),"Login User Profile"))
		{
			flag=false;
		}
		if(!verifyTextNotPresent("This user can NOT be edited or deleted as they are a System Administrator."))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function is used to verify the warning message in User Profile
	 * @param user- reads user name from excel sheet
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean sysAdminWarningMessage(String user) throws Throwable
	{
		boolean flag=true;
		if(!click(EMT_ViewOrEditUserOR.lnkManageUsers,"Manage users link"))
		{
			flag=false;
		}
		if(!click(EMT_ViewOrEditUserOR.User(user),"A user"))
		{
			flag=false;
		}
		if(!verifyTextPresent("be edited or deleted as they are a System Administrator."))
		{
			flag=false;
		}
		return flag;
	}


	//EMT-Manage Users(Add User)
	public static boolean ManageUserLink() throws Throwable{

		boolean flag=true;
		if(!click(EMT_AddUserInManageUsers_OR.LnkManageUser, "Manage User Link"))
		{
			flag=false;
		}
		if(!verifyTextPresent("Manage Users"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean AddUserLink() throws Throwable{

		boolean flag=true;
		if(!click(EMT_AddUserInManageUsers_OR.LnkAddUser, "AddUser link"))
		{
			flag=false;
		}
		if(!verifyTextPresent("Add User"))
		{
			flag=false;
		}
		return flag;
	}



	public static boolean AddUserDetails(String FirstName, String LastName, String Email, String LoginId) throws Throwable{

		boolean flag=true;
		if(!type(EMT_AddUserInManageUsers_OR.txtFirstName, FirstName, "The User's First Name"))
		{
			flag=false;
		}
		if(!type(EMT_AddUserInManageUsers_OR.txtLastName, LastName, "The User's Last Name"))
		{
			flag=false;
		}
		if(!type(EMT_AddUserInManageUsers_OR.txtEmail, Email, "The email Address of the user"))
		{
			flag=false;
		}
		if(!type(EMT_AddUserInManageUsers_OR.txtLoginId, LoginId, "The LoginId is same as the Email of the user"))
		{
			flag=false;
		}
		/*if(!type(EMT_AddUserInManageUsers_OR.txtPassword, Password, "The password of the user"))
		{
			flag=false;
		}*/

		return flag;

	}

	public static boolean checkPasswords(String Password,String Message) throws Throwable
	{
		boolean flag=true;
		System.out.println(Message);
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		waitForElementPresent(EMT_AddUserInManageUsers_OR.txtPassword);
		if(!type(EMT_AddUserInManageUsers_OR.txtPassword, Password, "The password of the user"))
		{
			flag=false;
		}
		if(!click(EMT_AddUserInManageUsers_OR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		if (!Message.isEmpty()) {
			waitForVisibilityOfElement(
					EMT_AddUserInManageUsers_OR.txtMessage,
					"Error Message");
			String message = getText(
					EMT_AddUserInManageUsers_OR.txtMessage,
					"Error Message");
			String msg = message.replace("\n", "");
			System.out.println(msg);
			if (msg.equalsIgnoreCase(Message)) {
				Reporters.SuccessReport("Validate Error Message",
						"Valid error message got displayed");
			}
			else
			{
				Reporters.failureReport("Validate Error Message", "Error Message displayed is invalid");
			}
		}
		else
		{
			waitForVisibilityOfElement(
					EMT_AddUserInManageUsers_OR.txtMessage,
					"Error Message");
			waitForVisibilityOfElement(EMT_AddUserInManageUsers_OR.txtManageUsers, "Manage Users");
			Reporters.SuccessReport("Create User", "Successfully Created User");
		}
		return flag;
	}


	public static boolean SelectUserProfile(String profileName) throws Throwable{

		boolean flag=true;
		if(!click(EMT_AddUserInManageUsers_OR.getCheckbox(profileName), "Checkbox"))
		{
			flag=false;
		}
		return flag;

	}

	public static String getSuccessMessageOfAddedUser(String first,String last) throws Throwable
	{
		return getText(EMT_AddUserInManageUsers_OR.txtMessage(first, last), "Returns the success message");
		//return true;

	}

	public static String getActualMessage() throws Throwable{

		return getText(EMT_AddUserInManageUsers_OR.txtActualMessage,"");
	}

	public static boolean compareSuccessMessage(String first,String last) throws Throwable{

		boolean flag=true;

		text="";


		if(verifyTextNotPresent("Login Id already exists")){
			if(getActualMessage().equals("User \""+first+" "+last+"\" has been added.")){

				text=getActualMessage();
				return flag;
			}

			else{
				text=getActualMessage();
				flag=false;
			}
		}
		else{
			text="Data already exists";
			flag=false;
		}
		return flag;


	}


	public static boolean getEMT(){
		boolean flag=true;

		driverM.get(configProps.getProperty("EMT_URL"));

		return flag;
	}


	public static boolean getPortal(){
		boolean flag=true;

		driverM.get(configProps.getProperty("Portal_URL"));

		return flag;
	}


	public void addRegToSessions1(Sheet AttendeeSheet, Sheet SessionSheet) throws Throwable, IOException{

		for (int j = 1; j < AttendeeSheet.getRows(); j++) {

			waitForVisibilityOfElement(EMT_SessionsOR.lnkSearch, "Search link");

			click(EMT_SessionsOR.lnkSearch, "Search Box");

			waitForVisibilityOfElement(EMT_SessionsOR.txtSearch, "Search box");

			type(EMT_SessionsOR.txtSearch, AttendeeSheet.getCell(0, j).getContents(), "Search Box");

			driverM.findElement(EMT_SessionsOR.txtSearch).sendKeys(Keys.ENTER);
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");
			if(!verifyInTable("Registrants",AttendeeSheet.getCell(0, j).getContents()))
			{
				Reporters.failureReport("Availability of registrant", "Searched Registarant is not available in the table");
			}
			else
			{
				click(EMT_SessionsOR.getRegistrant(AttendeeSheet.getCell(0, j).getContents()), "Registrant Name");
				for (int i = 1; i < SessionSheet.getRows(); i++) {
					if(!SessionSheet.getCell(3, i).getContents().isEmpty()){
						Thread.sleep(2000);
						js_click(EMT_SessionsOR.lnkManageSessionEnrollment, "Manage Session Enrollment Link");
						waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window, "frame");
						type(EMT_SessionsOR.txtSessionSearch, SessionSheet.getCell(3, i).getContents(), "Session Search Box");
						click(EMT_SessionsOR.btnSessionSearch, "Search Button");
						new WebDriverWait(driverM, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-results")));
						Thread.sleep(2000);
						if(driverM.findElement(By.id("search-results-total")).getText().contains("0")){
							System.out.println("No Record");
							System.out.println(driverM.findElement(By.xpath("//div[@id='tabs-2']/p")).getText());
							driverM.switchTo().defaultContent();
							click(EMT_SessionsOR.btnCloseWindow, "Close Window Button");
						}
						else{
							if(addSessionAndSave()){
								Reporters.SuccessReport("Add Session "+SessionSheet.getCell(3, i).getContents()+" To Registrant "+AttendeeSheet.getCell(0, j).getContents(), "Session "+SessionSheet.getCell(3, i).getContents()+" has been added to the Registrant "+AttendeeSheet.getCell(0, j).getContents());
							}
							else{
								Reporters.failureReport("Add Session "+SessionSheet.getCell(3, i).getContents()+" To Registrant "+AttendeeSheet.getCell(0, j).getContents(), "Unable to add Session to the Registrant");
							}
							if(verifySessionInSessionEnrollment(SessionSheet.getCell(3, i).getContents())){
								Reporters.SuccessReport("Verify Session "+SessionSheet.getCell(3, i).getContents()+" in Session Enrollment List for the Registrant "+AttendeeSheet.getCell(0, j).getContents(), "Session "+SessionSheet.getCell(3, i).getContents()+" is found in the Session Enrollment List for the Registrant "+AttendeeSheet.getCell(0, j).getContents());
							}
							else{
								Reporters.failureReport("Verify Session "+SessionSheet.getCell(3, i).getContents()+" in Session Enrollment List for the Registrant "+AttendeeSheet.getCell(0, j).getContents(), "Session Not Found in the Session Enrollment List");
							}
						}
					}
					else{
						break;
					}
				}
			}
		}

	}

	public static boolean addSessionAndSave() throws Throwable{
		boolean returnValue=true;

		if(!click(EMT_SessionsOR.chckSelect, "Check Box")){
			returnValue=false;
		}
		if(!click(EMT_SessionsOR.btnRegistrantAdd, "Add Button")){
			returnValue=false;
		}

		if(!click(EMT_SessionsOR.btnSave, "Save Button")){
			returnValue=false;
		}
		Thread.sleep(3000);
		return returnValue;
	}

	public boolean  verifySessionInSessionEnrollment(String SessionName) throws Throwable {

		boolean flag=true;

		List<WebElement> SessionsInEnrollmentList = driverM.findElements(By.xpath("//parent::div[h2[text()='Session Enrollment']]/following-sibling::div//table/tbody/tr/td[2]"));

		for (WebElement sessionList : SessionsInEnrollmentList) {

			if(sessionList.getText().equalsIgnoreCase(SessionName)){

				flag=true;
				//Reporters.SuccessReport("Verify Session "+SessionName+" in Session Enrollment List", "Session "+SessionName+" is found in the Session Enrollment List");
				break;
			}
			else{
				flag=false;
			}
		}
		return flag;

	}



	//iConnect-Add Speakers To Sessions
	public static boolean addSpeakerToSession(Sheet SessionSheet) throws Throwable
	{
		boolean flag=true;
		for(int i=1;i<SessionSheet.getRows();i++)
		{
			System.out.println(SessionSheet.getCell(33, i).getContents());
			if (!(SessionSheet.getCell(33, i)==null)) {
				System.out.println(SessionSheet.getCell(33, i).getContents());
				js_click(CommonOR.lnkSearch, "Search link");
				waitForVisibilityOfElement(CommonOR.txtSearch, "Search Box");
				search(SessionSheet.getCell(33, i).getContents());
				click(EMT_AddSpeaker_OR.speakerEmailID(SessionSheet.getCell(33,
						i).getContents()), "Speaker");
				js_click(EMT_AddSpeaker_OR.lnkManageSessions,
						"Manage Sessions link");
				waitForFrameToLoadAndSwitchToIt(EMT_SessionsOR.frame_window,
						"frame");
				if (!SessionSheet.getCell(3, i).getContents().isEmpty()) {
					type(EMT_SessionsOR.txtSessionSearch,
							SessionSheet.getCell(1, i).getContents(),
							"Session Search Box");
					click(EMT_SessionsOR.btnSessionSearch, "Search Button");
					//new WebDriverWait(driverM, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-results")));
					waitForInVisibilityOfElement(By.id("loading-results"),
							"Loading Icon");
					Thread.sleep(1000);
					if (driverM.findElement(By.id("search-results-total"))
							.getText().contains("0")) {
						System.out.println("No Record");
						System.out.println(driverM.findElement(
								By.xpath("//div[@id='tabs-2']/p")).getText());
						driverM.switchTo().defaultContent();
						click(EMT_SessionsOR.btnCloseWindow,
								"Close Window Button");
					} else {
						if (addSessionAndSave()) {
							Reporters.SuccessReport(
									"Add Session "
											+ SessionSheet.getCell(3, i)
											.getContents()
											+ " To speaker "
											+ SessionSheet.getCell(33, i)
											.getContents(), "Session "
													+ SessionSheet.getCell(3, i)
													.getContents()
													+ " has been added to the speaker "
													+ SessionSheet.getCell(33, i)
													.getContents());
						} else {
							Reporters.failureReport(
									"Add Session "
											+ SessionSheet.getCell(3, i)
											.getContents()
											+ " To speaker "
											+ SessionSheet.getCell(33, i)
											.getContents(),
									"Unable to add Session to the speaker");
						}
						//emtLogOut();
						Thread.sleep(2000);
						waitForVisibilityOfElement(
								By.xpath("//div[h2[text()='Sessions']]/following-sibling::div//tbody/tr"),
								"Sessions List");
						if (verifySessionInManageSession(SessionSheet.getCell(
								3, i).getContents())) {
							Reporters
							.SuccessReport(
									"Verify Session "
											+ SessionSheet
											.getCell(3, i)
											.getContents()
											+ " in Manage Sessions List for the speaker "
											+ SessionSheet.getCell(33,
													i).getContents(),
													"Session "
															+ SessionSheet
															.getCell(3, i)
															.getContents()
															+ " is found in the Manage Sessions List for the Speaker "
															+ SessionSheet.getCell(33,
																	i).getContents());
						} else {
							Reporters
							.failureReport(
									"Verify Session "
											+ SessionSheet
											.getCell(3, i)
											.getContents()
											+ " in Manage Sessions List for the speaker "
											+ SessionSheet.getCell(33,
													i).getContents(),
									"Session Not Found in the Manage Sessions List");
						}
					}
				}
			}
		}

		return flag;
	}

	public static boolean  verifySessionInManageSession(String SessionName) throws Throwable {

		boolean flag=true;

		List<WebElement> ManageSessionList = driverM.findElements(By.xpath("//parent::div[h2[text()='Sessions']]/following-sibling::div//table/tbody/tr/td[2]"));

		for (WebElement sessionList : ManageSessionList) {

			if(sessionList.getText().equalsIgnoreCase(SessionName)){

				flag=true;
				//Reporters.SuccessReport("Verify Session "+SessionName+" in Session Enrollment List", "Session "+SessionName+" is found in the Session Enrollment List");
				break;
			}
			else{
				flag=false;
			}
		}
		return flag;

	}


	public static boolean verifySpeakerIcon() throws Throwable{

		boolean flag=true;

		if(isElementPresent(By.xpath("//div[@class='speaker-icon']"),"Check the SpeakerIcon")){;
		flag= true;


		}
		else{ 

			flag=false;
		}
		return flag;

	}

	public static boolean addNewRecord() throws Throwable{

		boolean flag=true;
		if(!js_click(EMT_AddSpeaker_OR.lnkAddNewRecord, "AddNewRecord"))
		{
			flag=false;
		}
		if(!js_TriggerOnClickEvent("addNewRecord();", "Add Button"))
		{
			flag=false;
		}
		return flag;


	}

	public static boolean addSpeakerInformation(String prefix,String CustomerParticipantID,String FirstName,String status,String LastName) throws Throwable{

		boolean flag=true;
		if(!prefix.isEmpty()){
			if(!type(CommonOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
			{
				flag=false;

			}
			if(!CustomerParticipantID.isEmpty()){
				if(!type(CommonOR.txtBox("Customer Participant Id"),CustomerParticipantID,"Customer Participant Id Text Box")){
					flag=false;
				}
			}
			if(!FirstName.isEmpty()){
				if(!type(CommonOR.txtBox("First"),FirstName,"First Text Box")){
					flag=false;
				}
			}
			if(!status.isEmpty()){
				if(isElementPresent(CommonOR.ddLabel("Status"), "Status Drop Down")){
					if(verifyInDropDownList("Status", status)){
						if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "Status Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(EMT_SessionsOR.txtAddNew("Status"), status,"Status Add New Text Box")){
							flag=false;
						}
						if(!click(EMT_SessionsOR.btnPlus("Status"), "Plus Button of Status")){
							flag=false;
						}
					}
				}
			}
			if(!LastName.isEmpty()){
				if(!type(CommonOR.txtBox("Last"),LastName,"Last Text Box")){
					flag=false;
				}
			}
		}
		return flag;

	}

	public static boolean addSpeakerBio(String bio,String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM) throws Throwable
	{
		boolean flag=true;
		if(!bio.isEmpty()){
			if(!type(CommonOR.txtBio,bio,"bio Text Area Box")){
				flag=false;
			}
		}

		if(!WebURL.isEmpty()){
			if(!type(CommonOR.txtBox("Web URL"),WebURL,"Web URL Text Box")){
				flag=false;
			}
		}

		if(!Blog.isEmpty()){
			if(!type(CommonOR.txtBox("Blog"),Blog,"Blog Text Box")){
				flag=false;
			}
		}

		if(!LinkedInURL.isEmpty())
		{
			if(!type(CommonOR.txtBox("LinkedIn URL"),LinkedInURL,"LinkedIn URL Text Box"))
				flag=false;
		}

		if(!FacebookURL.isEmpty())
		{
			if(!type(CommonOR.txtBox("Facebook URL"),FacebookURL,"Facebook URL text box"))
				flag=false;
		}

		if(!TwitterAcnt.isEmpty())
		{
			if(!type(CommonOR.txtBox("Twitter Account"),TwitterAcnt,"Twitter Account text box"))
				flag=false;
		}

		if(!IM.isEmpty())
		{
			if(!type(CommonOR.txtBox("IM"),IM,"IM text box"))
				flag=false;
		}
		return flag;
	}

	public static boolean addSpeakerContactInformation(String Fullname,String Nickname,String Title,String phone,String mobile,String alt,String fax,
			String Company,String Address1,String Address2,String County,String City,String Region,String Country,
			String CountryCode,String ZipCode,String Email,String personalEmail) throws Throwable

			{

		boolean flag=true;
		if(!Fullname.isEmpty())
		{
			if(!type(CommonOR.txtBox("Full Name"),Fullname,"Full Name Teext Box"))
				flag=false;
		}

		if(!Nickname.isEmpty())
		{
			if(!type(CommonOR.txtBox("Nick Name"),Nickname,"Nick Name Text Box"))
				flag=false;
		}

		if(!Title.isEmpty())
		{
			if(!type(CommonOR.txtBox("Title"),Title,"Title Text Box"))
				flag=false;
		}

		if(!phone.isEmpty())
		{
			if(!type(CommonOR.txtBox("Phone"), phone, "Phone Text Box"))
				flag=false;
		}

		if(!mobile.isEmpty())
		{
			if(!type(CommonOR.txtBox("Mobile Phone"),mobile,"Mobile Phone Text Box"))
				flag=false;
		}

		if(!alt.isEmpty())
		{
			if(!type(CommonOR.txtBox("Alt Phone"),alt,"Alt Phone Text Box"))
				flag=false;
		}

		if(!fax.isEmpty())
		{
			if(!type(CommonOR.txtBox("Fax"),fax,"Fax Text Box"))
				flag=false;
		}

		if(!Company.isEmpty())
		{
			if(!type(CommonOR.txtBox("Company"),Company,"Company Text Box"))
				flag=false;
		}

		if(!Address1.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address 1"),Address1,"Address 1 Text Box"))
				flag=false;
		}

		if(!Address2.isEmpty())
		{
			if(!type(CommonOR.txtBox("Address 2"),Address2,"Address 2 Text Box"))
				flag=false;
		}

		if(!County.isEmpty())
		{
			if(!type(CommonOR.txtBox("County"),County,"County Text Box"))
				flag=false;
		}

		if(!City.isEmpty())
		{
			if(!type(CommonOR.txtBox("City"),City,"City Text box"))
				flag=false;
		}

		if(!Region.isEmpty())
		{
			if(!type(CommonOR.txtBox("Region"),Region,"Region Text box"))
				flag=false;
		}

		if(!Country.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Country"), "Country Drop Down")){
				if(verifyInDropDownList("Country", Country)){
					if(!selectByVisibleText(CommonOR.ddLabel("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Country"), Country," Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Country"), "Plus Button of Country")){
						flag=false;
					}
				}
			}
		}

		if(!CountryCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
		}

		if(!ZipCode.isEmpty())
		{
			if(!type(CommonOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;
		}

		if(!Email.isEmpty())
		{
			if(!type(CommonOR.txtBox("Email"),Email, "Email Text Box"))
				flag=false;
		}

		if(!personalEmail.isEmpty())
		{
			if(!type(CommonOR.txtBox("Personal Email"), personalEmail, "Personal Email Text Box"))
				flag=false;
		}
		return flag;

			}

	public static boolean addSpeakerLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
		}

		if(!Password.isEmpty())
		{
			if(!type(CommonOR.txtBox("Password"), Password, "Password Text Box"))
				flag=false;
		}

		if(!ConfirmPassword.isEmpty())
		{
			if(!type(EMT_AddSpeaker_OR.txtConfirmPassword,ConfirmPassword , "Confirm Password Text Box"))
				flag=false;
		}
		return flag;

	}

	public static boolean addSpeakerCategories(String Industry,String ProductSpecality,String Certification,String division,String Education,String JobRole,String market,String territory) throws Throwable
	{
		boolean flag=true;
		if(!Industry.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop Down")){
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
			}
		}

		if(!ProductSpecality.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Product Specialty"), "Product Specialty Drop Down")){
				if(verifyInDropDownList("Product Specialty",ProductSpecality )){
					if(!selectByVisibleText(CommonOR.ddLabel("Product Specialty"),ProductSpecality, "Product Specialty Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Product Specialty"),ProductSpecality, "Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Product Specialty"),"Plus Button of Product Specialty"))
						flag=false;
				}
			}
		}

		if(!Certification.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop Down")){
				if(verifyInDropDownList("Certifications",Certification )){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"),Certification, "Certifications Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Certifications"),Certification, "Certifications Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Certifications"),"Plus Button of Certifications"))
						flag=false;
				}
			}
		}

		if(!division.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop Down")){
				if(verifyInDropDownList("Division", division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), division," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
			}
		}

		if(!Education.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop Down")){
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
			}
		}

		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "Job Role Drop Down")){
				if(verifyInDropDownList("Job Role",JobRole )){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"),JobRole, "Job Role Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Job Role"),JobRole, "Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Job Role"),"Plus Button of Job Role"))
						flag=false;
				}
			}
		}

		if(!market.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Market"), "Market Drop Down")){
				if(verifyInDropDownList("Market", market)){
					if(!selectByVisibleText(CommonOR.ddLabel("Market"), market, "Market Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Market"), market,"Market Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Market"), "Plus Button of market")){
						flag=false;
					}
				}
			}
		}

		if(!territory.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Territory"), "Territory Drop Down")){
				if(verifyInDropDownList("Territory", territory)){
					if(!selectByVisibleText(CommonOR.ddLabel("Territory"), territory, "Territory Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Territory"), territory," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Territory"), "Plus Button of Territory")){
						flag=false;
					}
				}
			}
		}

		return flag;

	}

	public static boolean addSpeakerImage(String image) throws Throwable
	{
		boolean flag=true;
		if(!image.isEmpty())
		{
			driverM.findElement(CommonOR.txtBox("Speaker Image")).sendKeys(image);
		}
		return flag;
	}

	public static boolean verifySpeakerInformation(String prefix,String CustomerParticipantID,String FirstName,String status,String LastName) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Prefix"), prefix, "Prefix"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Customer Participant Id"), CustomerParticipantID, "Customer Participant Id"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("First"), FirstName, "First"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Status"), status, "Status"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Last"), LastName, "Last"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySpeakerBio(String bio,String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Bio"), bio, "Bio"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Web URL"), WebURL, "Web URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Blog"), Blog, "Blog"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("LinkedIn URL"), LinkedInURL, "LinkedIn URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Facebook URL"), FacebookURL, "Facebook URL"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Twitter Account"), TwitterAcnt, "Twitter Account"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("IM"), IM, "IM"))
		{
			flag=false;
		}

		return flag;
	}

	public static boolean verifySpeakerContactInformation(String Fullname,String Nickname,String Title,String phone,String mobile,String alt,String fax,
			String Company,String Address1,String Address2,String County,String City,String Region,String Country,
			String CountryCode,String ZipCode,String Email,String personalEmail) throws Throwable
			{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Full Name"), Fullname, "Full Name"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Nick Name"), Nickname, "Nick Name"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Title"), Title, "Title"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Phone"), phone, "Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Mobile Phone"), mobile, "Mobile Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Alt Phone"), alt, "Alt Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Fax"), fax, "Fax"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Company"), Company, "Company"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address 1"), Address1, "Address 1"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address 2"), Address2, "Address 2"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("County"), County, "County"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("City"), City, "City"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Region"), Region, "Region"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Country"), Country, "Country"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Country Code"), CountryCode, "Country Code"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Zip Code"), ZipCode, "Zip Code"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Email"), Email, "Email"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Personal Email"), personalEmail, "Personal Email"))
		{
			flag=false;
		}
		return flag;
			}

	public static boolean verifySpeakerLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Login Id"), LoginID, "Login Id"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySpeakerCategories(String Industry,String ProductSpecality,String JobRole,String division,String Education,String Certification,String territory,String market) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lblddValues("Industry"), Industry, "Industry"))
		{
			flag=false;
		}
		/*if(!verifyText(CommonOR.lblddValues("Product Specialty"), ProductSpecality, "Product Specialty"))
		{
			flag=false;
		}*/
		if(!verifyText(CommonOR.lblddValues("Job Role"), JobRole, "Job Role"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Division"), division, "Division"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Education"), Education, "Education"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Certifications"), Certification, "Certifications"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Territory"), territory, "Territory"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Market"), market, "Market"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean open_Speaker(String firstname,String lastname,String emailid) throws Throwable
	{
		boolean flag=true;
		if(click(By.xpath("//td[text()='"+emailid+"']"),"the Speaker"))
		{
			return flag;
		}
		else
		{
			flag=false;
		}
		return flag;
	}


	//iConnect-EMT(Add Associations)
	public static boolean addAssociationInformation(String EPC,String attendee,String AssociationStation) throws Throwable
	{
		boolean flag=true;
		if(!EPC.isEmpty())
		{
			if(!type(CommonOR.txtBox("EPC"), EPC, "EPC Text Box"))
				flag=false;
		}

		if(!attendee.isEmpty())
		{
			if(!type(CommonOR.txtBox("Attendee #"), attendee, "Attendee # Text Box"))
				flag=false;
		}

		if(!AssociationStation.isEmpty())
		{
			if(!type(CommonOR.txtBox("Association Station"), AssociationStation, "Association Station Text Box"))
				flag=false;
		}

		return flag;
	}

	public boolean  verifyAssociationInManageRFIDAssociation(String EPC) throws Throwable {

		boolean flag=true;

		List<WebElement> RFIDAssociationList = driverM.findElements(By.xpath("//parent::div[h2[text()='RFID Associations']]/following-sibling::div//table/tbody/tr/td[2]"));

		for (WebElement associationList : RFIDAssociationList) {

			if(associationList.getText().equalsIgnoreCase(EPC)){

				flag=true;
				//Reporters.SuccessReport("Verify Session "+SessionName+" in Session Enrollment List", "Session "+SessionName+" is found in the Session Enrollment List");
				break;
			}
			else{
				flag=false;
			}
		}
		return flag;

	}

	public boolean  verifyAssociationInManageRFIDAssociation(int x) throws Throwable {

		boolean flag=true;

		List<WebElement> RFIDAssociationList = driverM.findElements(By.xpath("//parent::div[h2[text()='RFID Associations']]/following-sibling::div//table/tbody/tr"));

		if(RFIDAssociationList.size()>=x){

			flag=true;
			//Reporters.SuccessReport("Verify Session "+SessionName+" in Session Enrollment List", "Session "+SessionName+" is found in the Session Enrollment List");
		}
		else{
			flag=false;
		}
		return flag;

	}

	public static boolean navigateToAddRoomspage() throws Throwable{

		boolean flag=true;
		if(isElementPresent(EMT_RoomsOR.lnkViewMore,"View more link")){

			if(!clickTabFromViewMore(CommonOR.lnkRooms, "Rooms Tab"))
			{
				flag=false;
			}
			if(!js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link"))
			{
				flag=false;
			}

			if(!js_TriggerOnClickEvent("addNewRecord();", "Add Button"))
			{
				flag=false;
			}
		}
		else{
			flag=false;
		}
		return flag;
	}

	public static boolean enterRoomDetails(String Name, String Capacity,String Description,String zones) throws Throwable{
		boolean flag=true;
		text="";

		if(isElementPresent(EMT_RoomsOR.lblAddNewRoom,"Add New Room Label")){
			if(!Name.isEmpty()){
				if(!type(EMT_RoomsOR.txtName,Name,"Name Text Box"))
				{
					flag=false;
				}
			}
			if(!Capacity.isEmpty()){
				if(!type(EMT_RoomsOR.txtCapacity,Capacity,"Capacity Text Box"))
				{
					flag=false;
				}
			}
			if(!Description.isEmpty())
			{
				if(!type(CommonOR.txtBox("Description"), Description, "Description Text Box"))
					flag=false;
			}
			if(!zones.isEmpty()){
				if(isElementPresent(CommonOR.ddLabel("Zones"), "Zones Drop Down")){
					if(verifyInDropDownList("Zones", zones)){
						if(!selectByVisibleText(CommonOR.ddLabel("Zones"), zones, "Zones Drop Down")){
							flag=false;
						}
					}
					else{
						if(!type(CommonOR.txtAddNew("Zones"), zones," Add New Text Box")){
							flag=false;
						}
						if(!click(CommonOR.btnPlus("Zones"), "Plus Button of zones")){
							flag=false;
						}
					}
				}
			}
			Thread.sleep(3000);
			if(!click(EMT_RoomsOR.btnSubmit,"Submit button"))
			{
				flag=false;
			}
		}

		else{
			text="Add New Room Label is not present";
			flag=false;
		}

		return flag;

	}

	public static boolean verifyRoomDetails(String Name, String Capacity,String Description,String zones) throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Name"), Name, "Name"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Capacity"), Capacity, "Capacity"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Description"), Description, "Description"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Zones"), zones, "Zones"))
		{
			flag=false;
		}
		return flag;
	}


	public static boolean search(String data) throws Throwable
	{
		boolean flag=true;

		if(!js_click(CommonOR.lnkSearch,"Search link"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(CommonOR.txtSearch, "Search Box"))
		{
			flag=false;
		}

		if(!type(CommonOR.txtSearch,data,"Search Box"))
		{
			flag=false;
		}
		//driverM.findElement(CommonOR.txtSearch).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		/*if(!sendEnter(CommonOR.txtSearch)){
			flag=false;
		}*/

		if(!click(By.id("search-submit"), "Search Submit")){
			flag=false;
		}
		return flag;
	}



	public static boolean insertSessionDetailSettingDisplayTextTag(String displayText,String usage){

		String Key=usage;
		switch (Key) {
		case "Session Code":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"true\"  usage=\"Session Code\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"0\"  order=\"1\"/>").perform();
			break;
		case "Speaker":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"true\"  usage=\"Speaker\"  displaytext=\""+displayText+"\"  type=\"sessions,materials\"  seperatortype=\"0\"  order=\"0\"/>").perform();
			break;
		case "Program":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Program\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"1\"/>").perform();
			break;
		case "Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Track\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"2\"/>").perform();
			break;
		case "Sub-Track":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Sub-Track\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"3\"/>").perform();
			break;
		case "Topic Tag":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Topic Tag\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"4\"/>").perform();
			break;
		case "Industry":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Industry\"  displaytext=\"" +displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"5\"/>").perform();
			break;
		case "Content Level":
			new Actions(driverM).sendKeys("    <session-detail-setting visible=\"true\"  promoted=\"false\"  usage=\"Content Level\"  displaytext=\""+displayText+"\"  type=\"sessions\"  seperatortype=\"1\"  order=\"6\"/>").perform();
			break;
		}
		return true;
	}


	public static boolean deleteAssociations(Sheet Associations) throws Throwable
	{
		boolean flag=true;

		for (int i = 1; i < Associations.getRows(); i++){

			if(!clickTabFromViewMore(CommonOR.lnkAssociations, "Associations Tab"))
			{
				flag=false;
			}

			if(search(Associations.getCell(1, i).getContents()))
			{
				Reporters.SuccessReport("Search Association with EPC "+Associations.getCell(1, i).getContents()+"", "Association got displayed");
			}
			else
			{
				Reporters.failureReport("Search Association with EPC "+Associations.getCell(1, i).getContents()+"", "Association is not displayed");
			}
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Associations"), "Loading");
			if(!waitForVisibilityOfElement(EMT_AddAssociationsOR.tblAssociations,"Associations table"))
			{
				flag=false;
			}

			if(verifyInTable("Associations",Associations.getCell(1, i).getContents())){


				if(!click(EMT_AddAssociationsOR.getAssociation(Associations.getCell(1, i).getContents()),"Association"))
				{
					flag=false;
				}
				if(!js_click(CommonOR.lnkDeleteThisRecord,"Delete This Association link"))
				{
					flag=false;
				}
				if(!waitForVisibilityOfElement(EMT_AddAssociationsOR.btnConfirm, "Confirm Button"))
				{
					flag=false;
				}
				if(!js_click(EMT_AddAssociationsOR.btnConfirm,"Confirm button"))
				{
					flag=false;
				}

				if(!waitForVisibilityOfElement(EMT_AddAssociationsOR.txtDeleteMessage,"Delete Message"))
				{
					flag=false;
				}
				emtLogOut();
				emtLogIn();
				if(!clickTabFromViewMore(CommonOR.lnkAssociations, "Associations Tab"))
				{
					flag=false;
				}

				if(search(Associations.getCell(1, i).getContents()))
				{
					Reporters.SuccessReport("Search Association with EPC "+Associations.getCell(1, i).getContents()+"", "Association got displayed");
				}
				else
				{
					Reporters.failureReport("Search Association with EPC "+Associations.getCell(1, i).getContents()+"", "Association is not displayed");
				}
				if(!waitForVisibilityOfElement(EMT_AddAssociationsOR.tblAssociations,"Associations table"))
				{
					flag=false;
				}
				if(!verifyTextPresent("No records to display."))
				{
					flag=false;
				}
			}
			else
			{
				Reporters.failureReport("Search for Association with EPC before performing delete "+Associations.getCell(1, i).getContents()+"", "Association with EPC "+Associations.getCell(1, i).getContents()+" is not available in Association list");
				flag=false;
			}
		}
		return flag;
	}

	public static boolean deleteRegistrants(Sheet Reg) throws Throwable
	{
		boolean flag=true;
		text="";
		for (int i = 1; i < Reg.getRows(); i++) {
			String attendee=Reg.getCell(2, i).getContents();
			if (!attendee.isEmpty()) {
				if (!search(attendee)) {
					flag = false;
				}
				waitForInVisibilityOfElement(
						CommonOR.loadingBySectionName("Registrants"), "Loading");
				if (!waitForVisibilityOfElement(
						CommonOR.lblSearchData("Registrants"),
						"Registrants Data in Search Results")) {
					flag = false;
				}
				if (verifyInTable("Registrants", attendee)) {
					Reporters.SuccessReport("Verify Registrant", "" + attendee
							+ " is available in Sessions list");
					if (!click(EMT_RegistrantsOR.openRegistrant(attendee),
							attendee)) {
						flag = false;
					}
					if (!js_click(CommonOR.lnkDeleteThisRecord,
							"Delete This Registrant link")) {
						flag = false;
					}
					if (!waitForVisibilityOfElement(
							EMT_RegistrantsOR.btnConfirm, "Confirm button")) {
						flag = false;
					}
					Thread.sleep(2000);
					if (!click(EMT_RegistrantsOR.btnConfirm, "Confirm button")) {
						flag = false;
					}
					waitForVisibilityOfElement(CommonOR.lblMessage, "Message");

					if (!verifyTextPresent("Registrant has been deleted!")) {
						Reporters.failureReport(
								"Print Actual Message on WebPage", driverM
								.findElement(CommonOR.lblMessage)
								.getText());
						flag = false;
					}
					emtLogOut();
					emtLogIn();
					if (!clickTabFromViewMore(EMT_RegistrantsOR.tabRegistrants,
							"Registrants Tab")) {
						flag = false;
					}

					if (!search(attendee)) {
						flag = false;
					}

					if (verifyInTable("Registrants", attendee)) {
						Reporters.failureReport(
								"Registrant verification after delete", ""
										+ attendee
										+ " is still available in table");
						flag = false;
					} else {
						Reporters
						.SuccessReport(
								"Registrant verification after delete",
								""
										+ attendee
										+ " is not available in table,hence successfully deleted");
					}
				} else {
					Reporters.failureReport(
							"Search for Registrant with attendee before performing delete "
									+ attendee + "",
									"Registrant with attendee " + attendee
									+ " is not available in Registrants list");
					flag = false;
				}
			}
			else
			{
				break;
			}
		}
		return flag;
	}

	public static boolean deleteRooms(Sheet Room) throws Throwable
	{
		boolean flag=true;
		for (int i = 1; i < Room.getRows(); i++) {

			String RoomName=Room.getCell(1, i).getContents();

			if(!search(RoomName))
			{
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Rooms"), "Rooms Section");
			if(verifyInTable("Rooms",RoomName)){
				Reporters.SuccessReport("Verify Room", ""+RoomName+" is available in Rooms list");
				if(!click(EMT_RoomsOR.openRoom(RoomName), "Name"))
				{
					flag=false;
				}
				if(!js_click(CommonOR.lnkDeleteThisRecord,"Delete This Room link"))
				{
					flag=false;
				}
				if(!waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm Button"))
				{
					flag=false;
				}
				Thread.sleep(2000);
				if(!js_click(EMT_RegistrantsOR.btnConfirm,"Confirm button"))
				{
					flag=false;
				}
				Thread.sleep(2000);
				if(!waitForVisibilityOfElement(EMT_RoomsOR.txtDeleteMsg, "Delete Room"))
				{
					flag=false;
				}
				if(!verifyTextPresent("Room has been deleted!"))
				{	Reporters.failureReport("Print Actual Message on WebPage", driverM.findElement(CommonOR.lblMessage).getText());
				flag=false;
				}
				emtLogOut();
				emtLogIn();
				if(!clickTabFromViewMore(CommonOR.lnkRooms,"Rooms Tab"))
				{
					flag=false;
				}

				if(!search(RoomName))
				{
					flag=false;
				}
				if(verifyInTable("Rooms",RoomName))
				{
					Reporters.failureReport("Room verification after delete", ""+RoomName+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Room verification after delete", ""+RoomName+" is not available in table");
				}
			}
			else{
				Reporters.failureReport("Search for Room before performing delete: "+RoomName+"", "Room is not available in rooms list");
			}
		}
		return flag;
	}

	public static boolean deleteSessions(Sheet Session) throws Throwable
	{
		boolean flag=true;
		for (int i = 1; i < Session.getRows(); i++) {
			String SessionTitle=Session.getCell(3, i).getContents();
			if (!SessionTitle.isEmpty()) {
				if (!search(SessionTitle)) {
					flag = false;
				}
				waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Sessions"), "Loading");
				deleteAndVerifyaSessionInEMT(SessionTitle);
			}
		}
		return flag;
	}

	public static boolean deleteAndVerifyaSessionInEMT(String Title) throws Throwable 
	{
		boolean flag=true;
		if(verifyInTable("Sessions",Title)){
			Reporters.SuccessReport("Verify Session", ""+Title+" is available in Sessions list");
			click(EMT_SessionsOR.openSessionTitle(Title), "title");
			js_click(CommonOR.lnkDeleteThisRecord,"Delete This Session link");
			waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			if(!verifyTextPresent("Session has been deleted!"))
			{
				Reporters.failureReport("Print Actual Message on WebPage", driverM.findElement(CommonOR.lblMessage).getText());
			}
			emtLogOut();
			emtLogIn();
			if(clickTabFromViewMore(EMT_SessionsOR.tabSessions,"Sessions tab")){
				Reporters.SuccessReport("Navigate to Sessions Page", "Required steps have been performed above successfully");
			}
			else{
				Reporters.failureReport("Navigate to Sessions Page", "Unable to Perform all the required steps");
			}

			search(Title);
			Thread.sleep(3000);
			if(verifyInTable("Sessions",Title))
			{
				Reporters.failureReport("Session verification after delete", ""+Title+" is still available in table");
			}
			else{
				Reporters.SuccessReport("Session verification after delete", ""+Title+" is not available in table");
			}
		}
		else
		{
			Reporters.failureReport("Search for Session before performing delete: "+Title+"", "Session is not available in sessions list");
		}
		return flag;
	}
	public static boolean deleteSpeakers(Sheet Speaker) throws Throwable
	{
		boolean flag=true;
		for (int i = 1; i < Speaker.getRows(); i++) {

			String speakerEmailID=Speaker.getCell(29, i).getContents();

			if(!search(speakerEmailID))
			{
				flag=false;
			}

			if(verifyInTable("Speakers",speakerEmailID)){
				Reporters.SuccessReport("Verify Speaker with email id "+speakerEmailID+"", ""+speakerEmailID+" is available in Speakers list");
				if(!js_click(By.xpath("//td[text()='"+speakerEmailID+"']"),"Speaker: "+speakerEmailID))
				{
					flag=false;
				}
				if(!js_click(CommonOR.lnkDeleteThisRecord,"Delete This Speaker link"))
				{
					flag=false;
				}
				if(!waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm Button"))
				{
					flag=false;	
				}
				Thread.sleep(2000);
				if(!js_click(EMT_RegistrantsOR.btnConfirm,"Confirm button"))
				{
					flag=false;
				}

				if(!waitForVisibilityOfElement(EMT_AddSpeaker_OR.txtSuccessMessage, "Delete Message"))
				{
					flag=false;
				}
				if(!verifyTextPresent("Speaker has been deleted!"))
				{
					Reporters.failureReport("Print Actual Message on WebPage", driverM.findElement(CommonOR.lblMessage).getText());
					flag=false;	
				}
				emtLogOut();
				emtLogIn();
				if(!clickTabFromViewMore(CommonOR.lnkSpeakers,"Speakers Tab"))
				{
					flag=false;
				}

				if(!search(speakerEmailID))
				{
					flag=false;
				}

				if(verifyInTable("Speakers",speakerEmailID))
				{
					Reporters.failureReport("Verification of a Speaker with email id after delete "+speakerEmailID+"", ""+speakerEmailID+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Verification of a Speaker with email id after delete "+speakerEmailID+"", ""+speakerEmailID+" is not available in table");
				}
			}
			else{
				Reporters.SuccessReport("Search for speaker with email id before performing delete "+speakerEmailID+"", ""+speakerEmailID+" is not available in rooms list");
			}
		}
		return flag;
	}

	//Validate Error Message in Registrant page
	public static boolean validateErrorMessagesInRegistrantPage() throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.txtErrorMessage("Attendee #"), "This is a required field", "Attendee # Text Box Error Message Displayed"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("First"), "This is a required field", "First Text Box Error Message Displayed"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Last"), "This is a required field", "Last Text Box Error Message Displayed"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error Message Displayed"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Login Id"), "This is a required field", "Login ID Text Box Error Message Displayed"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed"))
		{
			flag=false;
		}

		return flag;
	}

	public static boolean validateErrorMessagesInSessionsPage() throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.txtTitleErrorMessage, "This is a required field", "Title text Box Error Message"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error message"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean validateErrorMessagesInSpeakersPage() throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.txtErrorMessage("First"), "This is a required field", "First Text Box Error message"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Last"), "This is a required field", "Last Text Box Error message"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Company"), "This is a required field", "Company Text Box Error message"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Email"), "This is a required field", "Email Text Box Error message"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean validateErrorMessagesInAssociationsPage() throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.txtErrorMessage("EPC"), "This is a required field", "First Text Box Error message"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtErrorMessage("Attendee #"), "This is a required field", "First Text Box Error message"))
		{
			flag=false;
		}
		return flag;
	}

	//Import data functions
	public static boolean uploadFile(String filepath){
		boolean flag= true;
		try {
			driverM.findElement(DataImports.txtFile).sendKeys(filepath);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	//Import Exhibitor file in iLeads site
	public static boolean uploadExhibitorFile(String filepath) throws Throwable
	{
		boolean flag=true;
		try {
			driverM.findElement(IL_ImportExhibitorFile.btnBrowse).sendKeys(filepath);
			if(!click(IL_ImportExhibitorFile.btnUpload, "Upload Button"))
			{
				flag=false;
			}
			//new WebDriverWait(driverM, 10).until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//table[@id='progressBar']//span[@id='percentComplete']"), "Done!"));
			Thread.sleep(5000);
			refresh();
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}


	public static boolean mapFields() throws Throwable{
		boolean flag=true;
		try {
			Select input = new Select(driverM.findElement(By.xpath("//select[@id='input-fields-multi']")));
			Select available = new Select(driverM.findElement(By.xpath("//select[@id='available-fields-multi']")));
			List<WebElement> availableitems=available.getOptions();
			List<WebElement> options = input.getOptions();
			listItems=new ArrayList<>();
			for (WebElement option : availableitems) {
				listItems.add(option.getText());
			}
			for (WebElement input_field : options) {
				System.out.println(input_field.getText().trim());
				//&& driverM.findElements(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'Region')]")).size()>1
				if(input_field.getText().equalsIgnoreCase("Region")){
					if(verifyLsit("Region (String)")){
						new Actions(driverM).doubleClick(driverM.findElement(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'Region (String)')]"))).build().perform();
					}
					else{
						click(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'(Attribute)')]"), "Attribute");
						new Actions(driverM).doubleClick(driverM.findElement(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'(Attribute)')]"))).build().perform();
					}

				}
				else{

					if(verifyLsit(input_field.getText().trim()))
					{
						click(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'"+input_field.getText().trim()+"')]"), input_field.getText().trim());
						new Actions(driverM).doubleClick(driverM.findElement(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'"+input_field.getText().trim()+"')]"))).build().perform();
					}
					else{
						click(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'(Attribute)')]"), "Attribute");
						new Actions(driverM).doubleClick(driverM.findElement(By.xpath("//select[@id='available-fields-multi']/option[starts-with(text(),'(Attribute)')]"))).build().perform();
					}
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean verifyLsit(String text){
		boolean flag= true;

		for (String test : listItems) {
			if(test.contains(text)){
				flag = true;
				break;
			}
			else
				flag=false;			
		}

		return flag;
	}

	public static boolean validateDataAndProgressOperation() throws Throwable
	{
		boolean flag=true;
		if(!click(DataImports.btnNext, "Next button"))
		{
			flag=false;
		}
		Thread.sleep(5000);
		/*List <WebElement> checkboxes=driverM.findElements(DataImports.checkbox);
		for(WebElement chckbox:checkboxes)
		{
			if(!click(chckbox, "checkbox"))
			{
				flag=false;
			}
		}
		Thread.sleep(3000);*/
		waitForInVisibilityOfElement(By.xpath("//div[@id='validation-viewby-loading']"), "Loading");
		if(!click(DataImports.btnNext_DataValidation, "Next Button"))
		{
			flag=false;
		}
		new WebDriverWait(driverM, 30).until(ExpectedConditions.textToBePresentInElementLocated(By.id("progress-bar-text"), "100%"));
		return flag;
	}

	public static int getcsvnumber(ArrayList<String>[] test, String columnname){
		int count = 0;

		while(test[count].get(0)!= null ){

			if (test[count].get(0).equals(columnname)) {
				//System.out.println(count);
				break;
			}
			else
				count++;
		}

		return count;
	}

	public static boolean verifyCSVData(ArrayList<String>[] inputData,String columnName) throws Throwable{
		int j=0,i=0,region=0;
		boolean flag=true;
		System.out.println(inputData[0].size());
		System.out.println(inputData.length);
		for(j=0;j<(inputData[0].size())-1;j++) {
			region=0;
			if(search(inputData[getcsvnumber(inputData, columnName)].get(j+1)))
			{
				Reporters.SuccessReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(j+1) +"searched successfully");
			}
			else
			{
				Reporters.failureReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(j+1) +"searched successfully");
			}
			if(!click(By.xpath("//td[text()='"+inputData[getcsvnumber(inputData, columnName)].get(j+1)+"']"), inputData[getcsvnumber(inputData, columnName)].get(j+1)))
			{
				return false;
			}
			/*if(!click(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div//table/tbody/tr/td[text()='"+inputData[getcsvnumber(inputData, columnName)].get(j+1)+"']"), inputData[getcsvnumber(inputData, columnName)].get(j+1)))
			{
				return false;
			}*/
			Thread.sleep(3000);
			for(i=0;i<=(inputData.length)-1;i++)
			{
				if(j<=(inputData[0].size()-1)) {
					System.out.println(inputData[i].get(0));
					System.out.println(inputData[i].get(j+1));
					if(inputData[i].get(0).equalsIgnoreCase("Confirm Password") || inputData[i].get(0).equalsIgnoreCase("Password")||inputData[i].get(0).equalsIgnoreCase("Program") || inputData[i].get(0).equalsIgnoreCase("Track")|| inputData[i].get(0).equalsIgnoreCase("Sub-Track")|| inputData[i].get(0).equalsIgnoreCase("Time Slot")|| inputData[i].get(0).equalsIgnoreCase("ProgTrackSub"))
					{
						System.out.println("Validation not required");
					}
					else if(inputData[i].get(0).equalsIgnoreCase("Region"))
					{

						if(region==0){
							verifyText(By.xpath("//h3[text()='Contact Information']/ancestor::table//td[div[text()='Region']]/following-sibling::td[1]/div"),inputData[i].get(j+1),inputData[i].get(0));
							region++;
						}
						else{
							verifyText(By.xpath("//h3[text()='Categories']/ancestor::table//td[div[text()='Region']]/following-sibling::td[1]/div"),inputData[i].get(j+1),inputData[i].get(0));
						}
					}
					else 
					{
						verifyText(By.xpath("//div[text()='"+inputData[i].get(0).trim()+"']/parent::td/following-sibling::td[1]/div"), inputData[i].get(j+1), inputData[i].get(0));
					}
				}
			}
			Thread.sleep(3000);
		}

		return flag;
	}

	public static boolean verifyExhibitorValidationErrors(ArrayList<String>[] inputData,String columnName)
	{
		boolean flag=true;
		int j=0,i=0,region=0;
		System.out.println(inputData[0].size());
		int col=inputData.length;
		System.out.println(inputData.length);
		for (j = 0; j < (inputData[0].size())-1; j++) {
			//System.out.println(inputData[col-1].get(0));
			System.out.println(inputData[col-1].get(j+1));
			String errorMessage=inputData[col-1].get(j+1);

		}
		return flag;
	}

	public static boolean verifySurveyCSVData(String[][] inputData,String rowName) throws Throwable
	{
		boolean flag=true;
		System.out.println(inputData.length);
		System.out.println(inputData[0].length);
		String qtn1=inputData[0][2];
		System.out.println(inputData[0][2]);
		String qtn2=inputData[4][2];
		System.out.println(inputData[4][2]);
		String qtn3=inputData[9][2];
		System.out.println(inputData[9][2]);
		String qtn4=inputData[19][2];
		System.out.println(inputData[19][2]);
		String qtn5=inputData[26][2];
		System.out.println(inputData[26][2]);
		String qtn6=inputData[28][2];
		System.out.println(inputData[28][2]);
		String qtn7=inputData[29][2];
		System.out.println(inputData[29][2]);
		String qtn8=inputData[31][2];
		ArrayList<String> survey_qtns=new ArrayList<>();
		survey_qtns.add(qtn1);
		survey_qtns.add(qtn2);
		survey_qtns.add(qtn3);
		survey_qtns.add(qtn4);
		survey_qtns.add(qtn5);
		survey_qtns.add(qtn6);
		survey_qtns.add(qtn7);
		List<WebElement> surveyqtns=driverM.findElements(By.xpath("//div[@id='survey']//h4"));
		for (int i = 0; i < surveyqtns.size(); i++) {
			if(surveyqtns.get(i).getText().startsWith(survey_qtns.get(i)))
			{
				Reporters.SuccessReport("verify survey question "+survey_qtns.get(i)+" in survey Admin site", surveyqtns.get(i).getText()+" is available in Survey Admin Site");
			}
			else
			{
				Reporters.failureReport("verify survey question "+survey_qtns.get(i)+" in survey Admin site", surveyqtns.get(i).getText()+" is available in Survey Admin Site instaed of "+survey_qtns.get(i));
			}
		}
		String survey_paragraph_question=driverM.findElement( By.xpath("//div[@id='survey']//p")).getText();
		if(qtn8.startsWith(survey_paragraph_question))
		{
			Reporters.SuccessReport("verify survey question "+qtn8+" in survey Admin site", survey_paragraph_question+" is available in Survey Admin Site");
		}
		else
		{
			Reporters.failureReport("verify survey question "+qtn8+" in survey Admin site", survey_paragraph_question+" is available in Survey Admin Site instead of "+survey_paragraph_question);
		}

		return flag;
	}

	public static boolean verifySurveyRequiredQuestions(String[][] inputData) throws Throwable
	{
		boolean flag=true;
		//ArrayList<String> surveyQuestions=new ArrayList<>();
		for (int i = 0; i < inputData.length; i++) {
			if(inputData[i][0].equals("Q"))
			{
				System.out.println(i+" is the value of i");
				System.out.println("Question or Answer: "+inputData[i][0]);
				System.out.println("Survey Question: "+inputData[i][2]);
				System.out.println("0 or 1: "+inputData[i][4]);
				if (inputData[i][4].equals("1")) {
					System.out.println("//div[@id='survey']//h4[text()='"+ inputData[i][2] + " ']/span[@class='required']");
					if (isElementPresent(By.xpath("//div[@id='survey']//h4[text()='"+ inputData[i][2]+ " ']/span[@class='required']"),"Required Text")) {
						Reporters.SuccessReport("Verify survey question "+inputData[i][2]+" has required text", inputData[i][2]+" is marked as Mandatory question");
					}
					else
					{
						Reporters.failureReport("Verify survey question "+inputData[i][2]+" has required text", inputData[i][2]+" is not marked as Mandatory question");
					}
				}
			}
			else
			{
				System.out.println(inputData[i][0]);
			}
		}
		return flag;
	}

	public static boolean deleteImportData(ArrayList<String>[] inputData,String columnName) throws Throwable
	{
		boolean flag=true;

		for (int i=0;i<(inputData[0].size())-1;i++) {
			if(search(inputData[getcsvnumber(inputData, columnName)].get(i+1)))
			{
				Reporters.SuccessReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(i+1) +"searched successfully");
				if(!click(By.xpath("//td[text()='"+inputData[getcsvnumber(inputData, columnName)].get(i+1)+"']"), inputData[getcsvnumber(inputData, columnName)].get(i+1)))
				{
					return false;
				}
				if(!js_click(CommonOR.lnkDeleteThisRecord, "Delete This Record"))
				{
					flag=false;
				}
				if(!waitForElementPresent(EMT_RegistrantsOR.btnConfirm))
				{
					flag=false;
				}
				if(!js_click(EMT_RegistrantsOR.btnConfirm, "Confirm button"))
				{
					flag=false;
				}
				Thread.sleep(2000);
			}
			else
			{
				Reporters.failureReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(i+1) +"searched successfully");
			}

		}
		return flag;
	}


	public boolean setAddLayout(String columnName, int columnNumber) throws Throwable
	{
		boolean flag=true;
		/*if(!waitForElementPresent(By.xpath("//div[@class='modalboxStyleContainer_surface_right']")))
		{
			flag=false;
		}*/
		waitForVisibilityOfElement(By.xpath("//ul[@id='fields-not-used']/li"), "Fields Not Used");
		List<WebElement> fields_not_used=driverM.findElements(By.xpath("//ul[@id='fields-not-used']/li"));
		waitForVisibilityOfElement(By.xpath("//ul[@id='left-column' or @id='right-column']/li"), "Left and Right Fields");
		List<WebElement> leftandrightfields=driverM.findElements(By.xpath("//ul[@id='left-column' or @id='right-column']/li"));
		if(fields_not_used.size()>1){
			unusedFieldNames = new ArrayList<>();
			columnData = new ArrayList<>();
			leftRightFields=new ArrayList<>();
			unused=driverM.findElements(By.xpath("//li[contains(@id,'not_used')]"));

			for (WebElement unuse : unused) {
				unusedFieldNames.add(unuse.getText());
			}
			for (WebElement leftright : leftandrightfields) {
				leftRightFields.add(leftright.getText());
			}
			System.out.println(eReader.getRowCountByCol().length);
			System.out.println(eReader.getRowCountByCol()[columnNumber]);
			try{
				for (int col = 1; col < eReader.getRowCountByCol()[columnNumber]; col++) {
					if (!eReader.getColData(columnName, col).isEmpty()) {
						System.out.println(columnName + " And " + columnNumber);
						String s = eReader.getColData(columnName, col);
						if (!s.isEmpty())
							columnData.add(s);
						System.out.println(columnData.get(col - 1));
					}
				}

				int temp = 0;

				for (String column : columnData) {

					if(unusedFieldNames.contains(column)){

						System.out.println(driverM.findElement(By.xpath("//ul/li[contains(text(),'"+column+"')]")).getText());

						if(temp==0){
							draganddrop(By.xpath("//ul/li[starts-with(text(),'"+column+"')]"),By.xpath("//ul[@id='left-column']") , column);
							//new Actions(driverM).dragAndDrop(driverM.findElement(By.xpath("//ul/li[contains(text(),'"+column+"')]")), driverM.findElement(By.xpath("//ul[@id='left-column']"))).perform();
							temp++;
						}
						else{
							draganddrop(By.xpath("//ul/li[starts-with(text(),'"+column+"')]"),By.xpath("//ul[@id='right-column']"),column);
							//new Actions(driverM).dragAndDrop(driverM.findElement(By.xpath("//ul/li[contains(text(),'"+column+"')]")), driverM.findElement(By.xpath("//ul[@id='right-column']"))).perform();
							temp=0;
						}
					}
					else
					{
						if(leftRightFields.contains(column))
						{
							Reporters.SuccessReport(column+" is not available in unused fields list.Hence Checking for "+column+" field in left and right column fields list", column+" is available in either left or right columns fields list");
						}
						else
						{
							Reporters.failureReport("Check for "+column+" field in left and right column fields list", column+" is not present in either left or right column fields list,Hence "+column+" is an invalid field");
						}
					}

				}

				message="Successfully set screen layout";
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else{
			System.out.println("No Unused field");
			message="No Unused field available in the Layout";
			Reporters.SuccessReport("Check for required unused fields", "No Unused fields available in the Layout");
		}
		/*if(!js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button"))
		{
			flag=false;
		}*/

		return flag;
	}

	public boolean verifyScreenLayout() throws Throwable
	{
		boolean flag=true;
		Thread.sleep(2000);
		if(!getTabStatusMessage().contains("No Unused field")){      
			for (String newField : columnData) {
				System.out.println(newField);
				if(!isElementPresent(By.xpath("//td[normalize-space()='"+newField.trim()+"']"), newField)){
					flag=false;
				}
			}
		}
		else
		{
			System.out.println("No unused fields");
			Reporters.SuccessReport("No unused fields", "So no fields to set layout and verify that");
		}
		Thread.sleep(2000);
		return flag;
	}

	public boolean verifyScreenLayoutInAddNewPage() throws Throwable
	{
		boolean flag=true;

		for (String newField : columnData) {

			System.out.println(newField);

			if(!isElementPresent(By.xpath("//div[text()='"+newField.trim()+"']"), newField)){
				flag=false;
			}
		}
		return flag;
	}

	/**
	 * This Function verifies Screen Layout in Check in site's Add New Page
	 * @return
	 * @throws Throwable
	 */
	public boolean verifyScreenLayoutInCheckIn() throws Throwable
	{
		boolean flag=true;

		for (String newField : columnData) {

			System.out.println(newField);

			if(!isElementPresent(By.xpath("//label[starts-with(text(),'"+newField+"')]"), newField)){
				flag=false;
			}
		}
		return flag;
	}


	//survey
	/**
	 * This function adds survey information in Survey Admin Site
	 * @param Survey_Name- reads Survey Name from Test Data Excel sheet
	 * @param Survey_Status- reads Survey Status from Test Data Excel sheet
	 * @param Survey_Type- reads Survey Type from Test Data Excel sheet
	 * @param Survey_Flag- reads Survey Flag from Test Data Excel sheet
	 * @param Description- reads Description from Test Data Excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean addSurveyInformation(String Survey_Name,String Survey_Status,String Survey_Type,String Survey_Flag,String Description) throws Throwable
	{
		boolean flag=true;
		if(!Survey_Name.isEmpty())
		{
			if(!type(CommonOR.txtSurveyName("Survey Name"),Survey_Name,"Survey Name Text Box"))
				flag=false;
		}


		if(!Survey_Status.isEmpty())
		{
			if(verifyIndd(CommonOR.ddSurvey("Survey Status"), Survey_Status))
			{
				if(!selectByVisibleText(CommonOR.ddSurvey("Survey Status"), Survey_Status, "Survey Status Drop Down"))
				{
					flag=false;
				}
			}
			else
			{
				Reporters.failureReport("", "");
			}

		}
		if(!Survey_Type.isEmpty())
		{
			if(verifyIndd(CommonOR.ddSurvey("Survey Type"), Survey_Type)){
				if(!selectByVisibleText(CommonOR.ddSurvey("Survey Type"), Survey_Type, "Survey_Type Drop Down")){
					flag=false;
				}
			}
			else
			{
				Reporters.failureReport("", "");
			}

		}
		if(Survey_Type.equalsIgnoreCase("Session") && !Survey_Flag.isEmpty())
		{
			if(verifyIndd(By.xpath("//td[div[text()='Survey Flag']]/following-sibling::td//select"), Survey_Flag)){
				if(!selectByVisibleText(CommonOR.ddLabel("Survey Flag"), Survey_Flag, "Survey Flag Drop Down")){
					flag=false;
				}
			}
			else{
				if(!type(CommonOR.txtAddNew("Survey Flag"), Survey_Flag, "Status Add New Text Box")){
					flag=false;
				}
				if(!click(CommonOR.btnPlus("Survey Flag"), "Plus Button of Survey Flag")){
					flag=false;
				}
			}
		}
		if(!Description.isEmpty())
		{
			if(!type(CommonOR.txtSurveyDescription("Description"),Description,"Description Text Area"))
			{
				flag=false;
			}
		}

		return flag;
	}

	//Check-in
	public static boolean getMasterEMT(){

		try {
			driverM.get(configProps.getProperty("MasterEMT_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getSlave1EMT()
	{
		try {
			driverM.get(configProps.getProperty("Slave1EMT_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getSlave2EMT()
	{
		try {
			driverM.get(configProps.getProperty("Slave2EMT_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getMasterCheckinURL()
	{
		try {
			driverM.get(configProps.getProperty("MasterCheckIn_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getSlave1CheckIn(){

		try {
			driverM.get(configProps.getProperty("Slave1CheckIn_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getSlave2CheckIn(){

		try {
			driverM.get(configProps.getProperty("Slave2CheckIn_URL"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean checkInLogIn() throws Throwable
	{
		boolean flag=true;

		if(!type(CheckInOR.txtEmail, configProps.getProperty("SlaveAdminEmailAddress"), "Email Address"))
		{
			flag=false;
		}
		if(!type(CheckInOR.txtPwd, configProps.getProperty("SlaveAdminPassword"), "Password"))
		{
			flag=false;
		}
		if(!click(CheckInOR.btnSignIn, "Sign In Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean checkInUserLogIn() throws Throwable
	{
		boolean flag=true;

		if(!type(CheckInOR.txtEmail, configProps.getProperty("Slave1UserEmailAddress"), "Email Address"))
		{
			flag=false;
		}
		if(!type(CheckInOR.txtPwd, configProps.getProperty("Slave1UserPassword"), "Password"))
		{
			flag=false;
		}
		if(!click(CheckInOR.btnSignIn, "Sign In Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean removeCheckinStatus() throws Throwable{
		boolean flag=true;
		By.xpath("//td[div[text()='Checked-in']]/following-sibling::td");
		By checkedStatusText=By.xpath("//td[div[text()='Checked-in']]/following-sibling::td");

		System.out.println(getText(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td"), ""));

		if(verifyTextWithOutFailReport(checkedStatusText, "Yes", "Checked-In Status")){

			mouseover(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td/div"), "");
			waitForVisibilityOfElement(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td//div[contains(@class,'edit-icon')]"), "Edit Icon");

			if(!click(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td//div[contains(@class,'edit-icon')]"), "Edit Icon")){
				flag=false;
			}
			/*if(!click(checkedStatus, "yes")){
				flag=false;
			}*/
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "ajax loading icon");
			if(!click(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td//div[contains(@class,'new-field-value-delete')]"), "yes")){
				flag=false;
			}
			if(!click(EMT_RegistrantsOR.btnSave, "Save Button")){
				flag=false;
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "ajax loading icon");
			Reporters.SuccessReport("Validate Checked-In Status ", "Registrant is checked-in. Hence Removed the Status ");
		}
		else if(verifyTextWithOutFailReport(checkedStatusText, "", "Checked-In Status")){
			Reporters.SuccessReport("Validate Status ", "Registrant has not checked-in yet");
		}
		else{
			Reporters.failureReport("Remove CheckIn Status", "Unable to remove the status");
		}
		return flag;
	}


	public static boolean verifyAndCLickCheckIn(String Registrant) throws Throwable{
		boolean flag=false;

		List<WebElement> tr=driverM.findElements(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']"));
		System.out.println(tr.size());

		for (int i = 0; i < tr.size(); i++) {
			System.out.println(tr.get(i).findElement(By.xpath("//preceding-sibling::td[@align='center']/a")).getText());
			System.out.println(driverM.findElement(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']["+(i+1)+"]//td[not(contains(@align,'center'))][1]")).getText());
			if(driverM.findElement(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']["+(i+1)+"]//td[not(contains(@align,'center'))][1]")).getText().equalsIgnoreCase(Registrant.trim())){
				if(driverM.findElement(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']["+(i+1)+"]//a")).getText().equalsIgnoreCase("Check In")) {
					if (!click(driverM.findElement(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']["+(i+1)+"]//a")),"Check In Button of Attendee " + Registrant)) {
						flag = false;
					}
					flag=true;
					break;
				} else {
					Reporters.failureReport("Click Check-In Button","Unable to Click Check-In button and actual text in the button is "	+ driverM.findElement(By.xpath("//div[@id='view-by']/div[@class='table-container']/table/tbody/tr[@class='clickable']["+(i+1)+"]/preceding-sibling::td/a")).getText());
				}
			}
		}
		return flag;
	}

	static ArrayList<String> ar = new ArrayList<String>();

	public static boolean appendData() throws Throwable{

		boolean flag=true;

		//	ar = null;
		ar.clear();
		try {
			ar.add(driverM.findElement(CheckInOR.txtLabel("First")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Last")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Company")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Title")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("City")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Prefix: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Additional Info 3: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Additional Info 4: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Additional Info 1: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Additional Info 5: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Region: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("About Me: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Nick Name: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Full Name: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("County: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Additional Info 2: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Address2: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Address1: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));

			type(CheckInOR.txtLabel("First"), ar.get(0), "First");
			type(CheckInOR.txtLabel("Last"), ar.get(1), "Last");
			type(CheckInOR.txtLabel("Company"), ar.get(2), "Company");
			type(CheckInOR.txtLabel("Title"), ar.get(3), "Title");
			type(CheckInOR.txtLabel("City"), ar.get(4), "City");
			type(CheckInOR.txtLabel("Prefix: "), ar.get(5), "Prefix: ");
			type(CheckInOR.txtLabel("Additional Info 3: "), ar.get(6), "Additional Info 3: ");
			type(CheckInOR.txtLabel("Additional Info 4: "), ar.get(7), "Additional Info 4: ");
			type(CheckInOR.txtLabel("Additional Info 1: "), ar.get(8), "Additional Info 1: ");
			type(CheckInOR.txtLabel("Additional Info 5: "), ar.get(9), "Additional Info 5: ");
			type(CheckInOR.txtLabel("Region: "), ar.get(10), "Region: ");
			type(CheckInOR.txtLabel("About Me: "), ar.get(11), "About Me: ");
			type(CheckInOR.txtLabel("Nick Name: "), ar.get(12), "Nick Name: ");
			type(CheckInOR.txtLabel("Full Name: "), ar.get(13), "Full Name: ");
			type(CheckInOR.txtLabel("County: "), ar.get(14), "County: ");
			type(CheckInOR.txtLabel("Additional Info 2: "), ar.get(15), "Additional Info 2: ");
			type(CheckInOR.txtLabel("Address2: "), ar.get(16), "Address2: ");
			type(CheckInOR.txtLabel("Address1: "), ar.get(17), "Address1: ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}


		return flag;
	}

	public static boolean editCheckinData() throws Throwable{

		boolean flag=true;
		ar.clear();
		try {
			ar.add(driverM.findElement(CheckInOR.txtLabel("First")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Last")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Company")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Title")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("City")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Prefix: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Region: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("County: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Address2: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
			ar.add(driverM.findElement(CheckInOR.txtLabel("Address1: ")).getAttribute("value")+" "+configProps.getProperty("AppendWith"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type(CheckInOR.txtLabel("First"), ar.get(0), "First");
		type(CheckInOR.txtLabel("Last"), ar.get(1), "Last");
		type(CheckInOR.txtLabel("Company"), ar.get(2), "Company");
		type(CheckInOR.txtLabel("Title"), ar.get(3), "Title");
		type(CheckInOR.txtLabel("City"), ar.get(4), "City");
		type(CheckInOR.txtLabel("Prefix: "), ar.get(5), "Prefix: ");
		type(CheckInOR.txtLabel("Region: "), ar.get(6), "Region: ");
		type(CheckInOR.txtLabel("County: "), ar.get(7), "County: ");
		type(CheckInOR.txtLabel("Address2: "), ar.get(8), "Address2: ");
		type(CheckInOR.txtLabel("Address1: "), ar.get(9), "Address1: ");

		return flag;
	}
	public static boolean getURL(String URL){
		try {
			driverM.get(URL);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This function verifies the Registrant information in Check In Site
	 * @param URL- 
	 * @param Registrant
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean verifyCheckInData(String URL,String Registrant) throws Throwable{
		boolean flag=true;

		if(getURL(URL)){
			Reporters.SuccessReport("Launch URL "+URL, "Given URL has been launched successfully");
		}
		else{
			flag=false;
			Reporters.failureReport("Launch URL "+URL, "Unable to launch the given URL");
		}

		if(emtLogIn()){

		}
		else{
			flag=false;
		}
		if(clickTabFromViewMore(EMT_RegistrantsOR.tabRegistrants, "Registrants Tab")){

		}
		else{
			flag=false;
		}
		search(Registrant);
		waitForVisibilityOfElement(EMT_RegistrantsOR.tblResults, "Registrant results");
		//click(CommonOR.lblSearchData("Registrants"), Registrant);
		click(EMT_RegistrantsOR.openRegistrant(Registrant), Registrant);

		try {
			verifyText(CommonOR.lbltxtValues("First"), ar.get(0), "First");
			verifyText(CommonOR.lbltxtValues("Last"), ar.get(1), "Last");
			verifyText(CommonOR.lbltxtValues("Company"), ar.get(2), "Company");
			verifyText(CommonOR.lbltxtValues("Title"), ar.get(3), "Title");
			verifyText(CommonOR.lbltxtValues("City"), ar.get(4), "City");
			verifyText(CommonOR.lbltxtValues("Prefix"), ar.get(5), "Prefix");
			verifyText(CommonOR.lbltxtValues("Additional Info 3"), ar.get(6), "Additional Info 3");
			verifyText(CommonOR.lbltxtValues("Additional Info 4"), ar.get(7), "Additional Info 4");
			verifyText(CommonOR.lbltxtValues("Additional Info 1"), ar.get(8), "Additional Info 3");
			verifyText(CommonOR.lbltxtValues("Additional Info 5"), ar.get(9), "Additional Info 4");
			verifyText(CommonOR.lbltxtValues("Region"), ar.get(10), "Region");
			verifyText(CommonOR.lbltxtValues("About Me"), ar.get(11), "About Me");
			verifyText(CommonOR.lbltxtValues("Nick Name"), ar.get(12), "Nick Name");
			verifyText(CommonOR.lbltxtValues("Full Name"), ar.get(13), "Full Name");
			verifyText(CommonOR.lbltxtValues("County"), ar.get(14), "County");
			verifyText(CommonOR.lbltxtValues("Additional Info 2"), ar.get(15), "Additional Info 2");
			verifyText(CommonOR.lbltxtValues("Address1"), ar.get(17), "Address1");
			verifyText(CommonOR.lbltxtValues("Address2"), ar.get(16), "Address2");
		} catch (Exception e) {
			return false;
		}

		return flag;
	}

	/**
	 * This function verifies the updated Registrant data in EMT
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean verifyUpdatedRegistrantDataInEMT() throws Throwable
	{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("First"), ar.get(0), "First"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Last"), ar.get(1), "Last"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Company"), ar.get(2), "Company"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Title"), ar.get(3), "Title"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("City"), ar.get(4), "City"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Prefix"), ar.get(5), "Prefix"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Region"), ar.get(6), "Region"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("County"), ar.get(7), "County"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address2"), ar.get(8), "Address2"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address1"), ar.get(9), "Address1"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to get the iLeads URL
	 * @return boolean(true or false)
	 */

	public static boolean getiLeadsURL(){
		boolean flag=true;

		driverM.get(configProps.getProperty("iLeads_URL"));

		return flag;
	}

	/**
	 * This function is used to Login to iLeads application
	 * @return boolean (true or false)
	 * @throws Throwable
	 */

	public static boolean iLeadsLogIn() throws Throwable
	{
		boolean flag=true;

		if(!type(IL_CreateAndDeleteAnEventOR.txtUsername, configProps.getProperty("iLeads_Username"), "User Name"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtPassword, configProps.getProperty("iLeads_Password"), "Password"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnLogin, "Login"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to navigate to Add Event page after Login
	 * @return boolean (true or false)
	 * @throws Throwable
	 */
	public static boolean create_Event_Page() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabEvents,"Events tab"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnAdd,"Add option"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtAddEvent, "Add Event ", "Add Event Page"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to add Customer information in Add Event page
	 * @param FirstName-reads first name from excel sheet
	 * @param LastName-reads Last name from excel sheet
	 * @param Company-reads Company name from excel sheet
	 * @param Email-reads Email id from excel sheet
	 * @param password-reads password from excel sheet
	 * @param SecurityRole-reads SecurityRole from excel sheet
	 * @param PhoneNumber-reads Phone Number from excel sheet
	 * @param MobileNumber-reads Mobile Number from excel sheet
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean addCustomerInformation(String FirstName,String LastName,String Company,String Email,String password,String SecurityRole,String PhoneNumber,String MobileNumber) throws Throwable
	{
		boolean flag=true;
		if(!type(IL_CreateAndDeleteAnEventOR.txtFirstName,FirstName,"Customer's First Name"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtLastName,LastName,"Customer's Last Name"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtCompany,Company,"Company"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtEmailAddress,Email,"Customer's Email address"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtPass,password,"password"))
		{
			flag=false;
		}
		selectByValue(IL_CreateAndDeleteAnEventOR.securityRole,SecurityRole, "SECURITY ROLE");

		if(!click(IL_CreateAndDeleteAnEventOR.securityRole,"SECURITY ROLE Drop Down"))
		{
			flag=false;
		}

		if(!type(IL_CreateAndDeleteAnEventOR.txtPhoneNumber,PhoneNumber,"Phone Number"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtMobileNumber,MobileNumber,"Mobile Number"))
		{
			flag=false;
		}
		return flag;
	}

	public static String event_title="";
	/**
	 * This Function is used to add Event Information in Add Event page
	 * @param EventName-reads Event name from excel sheet
	 * @param EventTag-reads Event tag from excel sheet
	 * @param StartDate-reads event start date from excel sheet
	 * @param EndDate-reads event end date from excel sheet
	 * @return boolean (true or false)
	 * @throws Throwable
	 */
	public static boolean addEventInformation(String EventName,String EventTag,String StartDate,String EndDate) throws Throwable
	{
		boolean flag=true;
		if(!type(IL_CreateAndDeleteAnEventOR.txtEventName,EventName,"Event Name"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtEventTag,EventTag,"Event Tag"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtStartDate,StartDate,"Start Date"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtEndDate,EndDate,"End Date"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent,"Add button"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtEventAddedMsg, "Event added!", "Suucess Message displayed"))
		{
			flag=false;
		}
		event_title();
		return flag;
	}

	/**
	 * This function is used to get the Event title that is created
	 * @return String i.e Title of the event
	 * @throws Throwable
	 */
	public static String event_title() throws Throwable
	{
		event_title=getText(IL_CreateAndDeleteAnEventOR.txtEvent, "Event title");
		return event_title;
	}

	/**
	 * This function is used to search for the event created.
	 * @param EventTag- Title of the event that is created
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean searchForEventCreated(String EventTag) throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabLeadConfigWizard,"Lead Config Wizard Tab"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtEventFilter,EventTag,"Event Tag"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.lnkFilter,"Filter Link"))
		{
			flag=false;
		}
		selectByIndex(IL_CreateAndDeleteAnEventOR.drpEventsOption, 1, "Event");

		return flag;
	}

	/**
	 * This function is used to configure a new file to an event
	 * @param ConfigName-reads Config name from excel sheet
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean configNewFile(String ConfigName) throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.ddConfig,"Config Drop Down"))
		{
			flag=false;
		}

		selectBySendkeys(IL_CreateAndDeleteAnEventOR.ddNewConfigFile, "New	config file...", "New Config File");

		if(!click(IL_CreateAndDeleteAnEventOR.ddNewConfigFile,"New Config File option from drop down"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!click(IL_CreateAndDeleteAnEventOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtConfigSuccessMsg, "Creation of a new xml file was sucessful.", "Creation of a new xml file was sucessful."))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtCongifName,ConfigName,"Config Name"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnSave,"Save Button"))
		{ 
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtEditorInfoSuccessMsg, "Editor info saved!", "Success Message"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to add DB Fields to an event
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean addDBField() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.lnkDBFields,"DB Fields Link"))
		{
			flag=false;
		}
		selectBySendkeys(IL_CreateAndDeleteAnEventOR.txtBadgeId, "BADGEID", "Badge ID");
		if(!click(IL_CreateAndDeleteAnEventOR.txtBadgeId,"Badge ID"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnEditorInfoSave,"Save Button"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtDBFieldsSuccessMsg, "DBFields have been saved!", "DBFields have been saved!"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to add the badge type, layout of an event
	 * @param BadgeTypeCount- gives the number of badge types available in Type drop down
	 * @param badgetype- reads Badge type from the excel sheet
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean badgeLayout(int BadgeTypeCount,String badgetype) throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.lnkBadge,"Badge Link"))
		{
			flag=false;
		}
		Select se = new Select(driverM.findElement(By.id("badgeType")));
		List<WebElement> count_BadgeType = se.getOptions();
		BadgeTypeCount=count_BadgeType.size()-1;
		if(BadgeTypeCount!=0)
		{
			Reporters.SuccessReport("Number of Badge Types", +BadgeTypeCount+" Badge Types are available");
		}
		else{
			Reporters.SuccessReport("Number of Badge Types", "No Badge Types are available");
		}

		if(!click(By.id("badgeType"), "Badge Type Drop Down"))
		{
			flag=false;
		}
		selectBySendkeys(IL_CreateAndDeleteAnEventOR.badgeType(badgetype), badgetype, "Badge Type");
		if(!click(IL_CreateAndDeleteAnEventOR.badgeType(badgetype),badgetype+ " Badge Type "))
		{
			flag=false;
		}
		Thread.sleep(2000);
		selectBySendkeys(IL_CreateAndDeleteAnEventOR.ddBadgeLayoutField, "BADGEID", "Badge Layout Field");
		if(!click(IL_CreateAndDeleteAnEventOR.ddBadgeLayoutField,"Badge Layout Field"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!click(IL_CreateAndDeleteAnEventOR.btnBadgeSave,"Save Button"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtBadgeDataSuccessMsg, "Badge data saved!", "Badge Data Saved"))
		{
			flag=false;	
		}
		return flag;
	}

	/**
	 * This function is used to navigate to Clear event page
	 * @return boolean (true or false)
	 * @throws Throwable
	 */
	public static boolean event_clear_page() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabEvents,"EVENTS TAB"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnClear,"Clear option"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtClearEventPage, "Clear Event ", "Clear Event page"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to clear an event
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean clearEvent() throws Throwable
	{
		boolean flag=true;

		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to clear"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnClearEvent,"Clear Button"))
		{
			flag=false;
		}
		driverM.switchTo().alert().accept();
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtClearSuccessMsg, "Event cleared!", "Success Message of Clear operation"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to navigate to Remove event page
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean remove_Event_Page() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabEvents,"Events tab"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnDelete,"Remove option"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtRemoveEventPage, "Remove Event ", "Remove Event Page"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to remove an event
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean removeEvent() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventToRemove,"Events"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		if(!waitForElementPresent(IL_CreateAndDeleteAnEventOR.btnRemove))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnRemove,"REMOVE button"))
		{
			flag=false;
		}
		driverM.switchTo().alert().accept();//Handles alert windows and click on 'OK' button in Alert window
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtRemoveSuccessMsg, "Event removed!", "Success Message"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to log out from the iLeads application
	 * @return boolean(true or false)
	 * @throws Throwable
	 */
	public static boolean iLeadsLogOut() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabLogout,"Logout"))
		{
			flag=false;
		}
		if(!verifyText(IL_CreateAndDeleteAnEventOR.txtLogin, "Login", "Login Page"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * 
	 * @param linkby
	 * @param text1
	 * @param lbl
	 * @param text2
	 * @return boolean(true/false)
	 * @throws Throwable
	 */

	public static boolean integrationSetup(By linkby, String text1, By lbl, String text2) throws Throwable{

		boolean flag=true;
		waitForElementPresent(CheckInOR.lnkRegistration);
		if(!click(linkby, text1)){
			flag=false;
		}
		waitForElementPresent(CheckInOR.lnkCloseWindow);
		selectByValue(CheckInOR.ddIntegrationModule, "ALLIANCETECH", "Intergration Module");
		if(!type(CheckInOR.txtStartDate, configProps.getProperty("StartDate"), "Start Date")){
			flag=false;
		}
		if(!type(CheckInOR.txtEndDate, configProps.getProperty("EndDate"), "End Date")){
			flag=false;
		}
		sendEnter(CheckInOR.txtEndDate);

		if(!type(CheckInOR.txtIntegrationUserName, configProps.getProperty("EMT_Username"), "Username")){
			flag=false;
		}
		if(!type(CheckInOR.txtIntegrationPassword, configProps.getProperty("EMT_Password"), "Password")){
			flag=false;
		}
		if(!type(CheckInOR.txtIntegrationInterval, configProps.getProperty("Interval"), "Interval")){
			flag=false;
		}

		if(configProps.getProperty("Enabled").equalsIgnoreCase("true")){
			if(getText(lbl, text2).equalsIgnoreCase("false")){
				if(!click(CheckInOR.txtIntegrationEnabled, "Enabled Check box")){
					flag=false;
				}
			}
		}

		if(!type(CheckInOR.txtIntegrationURL, configProps.getProperty("MasterEMT_URL"), "Master URL")){
			flag=false;
		}
		if(!click(CheckInOR.btnIntegrationSubmit,"Save Edit Button")){
			flag=false;
		}
		Thread.sleep(2000);
		waitForElementPresent(CheckInOR.lblEnabled);


		return flag;
	}

	/**
	 * This Function verifies values in a drop down
	 * @param by- locator
	 * @param textToVerify- text we want to verify in drop down
	 * @return boolean(true/false)
	 */
	public static boolean verifyIndd(By by, String textToVerify){

		boolean flag = false;
		try {
			WebElement dropdown = driverM.findElement(by);
			Select s = new Select(dropdown);
			List<WebElement> options=s.getOptions();
			for (WebElement option : options) {
				if(option.getText().equals(textToVerify)){
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			return false;
		}
		return flag;
	}

	/**
	 * This function is used to drag survey questions from builder to Pages
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean dragAndDropSurveyQuestions() throws Throwable
	{
		boolean flag=true;
		try {
			List<WebElement> builder_links= driverM.findElements(By.xpath("//div[@id='builder']/div/a"));
			for (int link_count = 0; link_count < builder_links.size(); link_count++) {
				if(link_count==6){
					new Actions(driverM).sendKeys(Keys.PAGE_DOWN).release().build().perform();
					Thread.sleep(2000);
				}
				else
					Thread.sleep(1000);
				waitForVisibilityOfElement(SurveyOR.targetPosition, "Target position");
				draganddrop(builder_links.get(link_count),SurveyOR.targetPosition, builder_links.get(link_count).getText(), "Page  "+(link_count+1));
				Thread.sleep(2000);
				click(SurveyOR.btnSaveQuestion,"Save question");
				Thread.sleep(1000);
				builder_links= driverM.findElements(By.xpath("//div[@id='builder']/div/a"));
				Thread.sleep(1000);
				if(!(link_count==(builder_links.size()-1))){
					//driverM.findElement(SurveyOR.lastPosition).click();
					click(SurveyOR.lastPosition,"Add Page");
				}
			}
		} catch (Exception e) {
			flag= false;
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * This function submits a survey and verifies that survey in Survey Details table
	 * @param Survey_Name- Reads Survey Name from Test Data Excel sheet
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean submitAndverifySurvey(String Survey_Name) throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.btnSubmit,"Submit Button");
		if(!click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		if(!verifyTextPresent("Survey has been edited!"))
		{
			flag=false;
		}
		if(!click(SurveyOR.lnkCancel,"Cancel link"))
		{
			flag=false;
		}
		/*waitForVisibilityOfElement(By.id("records-to-display"),"Records to Display");
		if(!click(By.id("records-to-display"),"Records to Display"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records");
		if(!click(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records"))
		{
			flag=false;
		}*/
		Thread.sleep(2000);
		waitForVisibilityOfElement(SurveyOR.lstSearchResults, Survey_Name);
		if(!verify_In_Table(SurveyOR.lstSearchResults, Survey_Name))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function navigates to Add Session Page
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean navigateToAddSessionSurveys() throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.btnAddSurveys, "Add Surveys");
		if(!click(SurveyOR.btnAddSurveys,"Add Surveys"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.m_txtAddSurveys, "Add Surveys page");
		if(!verifyText(SurveyOR.m_txtAddSurveys, "Add Surveys", "Add Surveys page"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function searches for a session using Session ID
	 * @param SessionID- reads Session Id from Test Data Excel sheet
	 * @param SessionTitle- reads Session Title from Test Data Excel sheet
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean searchForSession(String SessionID,String SessionTitle) throws Throwable
	{
		boolean flag=true;
		if(!type(SurveyOR.txtSearchBox,SessionTitle,"Session ID"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnSearch,"Search  Button"))
		{
			flag=false;
		}
		waitForElementPresent(SurveyOR.txtSessionInSearchResults(SessionID, SessionTitle));
		Thread.sleep(2000);
		/*if(!verifyText(SurveyOR.txtSessionInSearchResults(SessionID, SessionTitle),SessionID+" "+SessionTitle,"Session"))
		{
			flag=false;
		}*/
		return flag;
	}

	/**
	 * This function is used to take survey for a session in Survey Site
	 * @param SessionID- Takes Session ID from the Test Data Excel sheet
	 * @param SessionTitle- Takes Session Title from the Test Data Excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean takeSurvey(String SessionID,String SessionTitle) throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.rdYes,"Yes radio button");
		if(!click(SurveyOR.rdYes,"Yes radio button"))
		{
			flag=false;
		}
		
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.txtVanilla, "Vanilla Checkbox");
		if(!click(SurveyOR.chck,"Check box"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.m_txtQualiy, "Quality of ingredients");
		if(!click(SurveyOR.m_chckExtremelyImp("Quality of ingredients"),"Quality of ingredients radio button"))
		{
			flag=false;
		}
		if(!click(SurveyOR.m_chckExtremelyImp("Flavor"),"Flavor radio button"))
		{
			flag=false;
		}
		if(!click(SurveyOR.m_chckExtremelyImp("Texture"),"Texture radio button"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.m_chckFamily("Me"),"Me Check box");
		if(!click(SurveyOR.m_chckFamily("Me"),"Me Check box"))
		{
			flag=false;
		}
		if(!click(SurveyOR.m_chckFamily("My Spouse"),"My Spouse Check box"))
		{
			flag=false;
		}
		if(!click(SurveyOR.m_chckFamily("My Children"),"My Children Check box"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.txtArea,"Text Area");
		if(!type(SurveyOR.txtArea,"testing","Text Area"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnFinish,"Finish Button");
		if(!click(SurveyOR.btnFinish,"Finish Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!verifyTextPresent("Thank you for your feedback!"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function validates the survey answers in Survey Site
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean validateSurveyAnswers() throws Throwable
	{
		boolean flag=true;
		if(!isChecked(SurveyOR.rdYes, "Yes Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.chck,"Check box"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.radioButtonMatrix("Quality of ingredients"),"Quality of ingredients radio button"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.radioButtonMatrix("Flavor"),"Flavor radio button"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.radioButtonMatrix("Texture"),"Texture radio button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.chckBoxMyFamily("Me"),"Me Check box"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.chckBoxMyFamily("My Spouse"),"My Spouse Check box"))
		{
			flag=false;
		}
		if(!isChecked(SurveyOR.chckBoxMyFamily("My Children"),"My Children Check box"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnFinish,"Finish Button");
		if(!click(SurveyOR.btnFinish,"Finish Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!verifyTextPresent("Thank you for your feedback!"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function is used to log out from survey site
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean surveyLogOut() throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.lnkNotYou, "Not You?");
		if(!click(SurveyOR.lnkNotYou,"Not You"))
		{
			flag=false;
		}
		if(!verifyTextPresent("Help us find your surveys..."))
		{
			flag=false;
		}
		Thread.sleep(2000);
		return flag;
	}

	/**
	 * This Function is used to navigate to Manage Survey Page
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean navigateToManageSurveysPage() throws Throwable
	{
		boolean flag=true;
		if(!click(CommonOR.lnkManageSurvey,"Manage Survey Link"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.txtManageSurveys, "Manage Surveys", "Manage Surveys Page"))
		{
			flag=false;
		}
		return flag;
	}

	static String question_Text;
	static String rating_text;
	/**
	 * This function is used to add a Survey Question for a survey in Survey Admin Site
	 * @param LabelText1- Type of survey question to be dragged from builder
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean editSurveyQuestions(String LabelText1) throws Throwable
	{	
		boolean flag=true;
		draganddrop(driverM.findElement(By.xpath("//a[text()='"+LabelText1+"']")), By.xpath("//div[@class='question droppable']"), LabelText1," Page1");
		Thread.sleep(2000);
		if(!click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		Thread.sleep(3000);
		new Actions(driverM).moveToElement(driverM.findElement(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div//h4[text()='Please rate the importance of the following qualities of ice cream. ']"))).doubleClick().build().perform();
		question_Text="Automation testing";
		if(!type(SurveyOR.txtSurveyQuestion, question_Text, "Survey question"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnAddForSurveyAnswer, "Add Button"))
		{
			flag=false;
		}
		survey_answer="Answer testing";
		if(!type(SurveyOR.txtNewSurveyAnswer, survey_answer, "Survey Answer"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnAddRatingsAnswer,"Add Button"))
		{
			flag=false;
		}
		rating_text="Rating Testing";
		if(!type(SurveyOR.txtNewRating, rating_text, "Ratings Answer"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnSaveQuestion,"Save Question Button"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}

		return flag;
	}

	/**
	 * This function verifies survey created in Survey Admin site in Survey site
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean validateSurveyQuestions() throws Throwable
	{
		boolean flag=true;
		/*if(!verifyTextPresent(question_Text))
		{
			flag=false;
		}
		if(!verifyTextPresent(survey_answer))
		{
			flag=false;
		}
		if(!verifyTextPresent(rating_text))
		{
			flag=false;
		}*/
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Quality of ingredients"),"Quality of ingredients Check box");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Quality of ingredients"),"Quality of ingredients Check box"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Flavor"),"Flavor Check box");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Flavor"),"Flavor Check box"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Texture"),"Texture Check box");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Texture"),"Texture Check box"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix(survey_answer),survey_answer+" Check box");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix(survey_answer),survey_answer+" Check box"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnFinish,"Finish Button");
		if(!ActiondriverM.click(SurveyOR.btnFinish,"Finish Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!ActiondriverM.verifyTextPresent("Thank you for your feedback!"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This function clears Survey in Survey Admin Site
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean clearSurvey() throws Throwable
	{
		boolean flag=true;
		if(!click(SurveyOR.lnkClearSurvey,"Clear Survey link"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm,"Confirm button");
		if(!js_click(EMT_RegistrantsOR.btnConfirm,"Confirm button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		return flag;
	}

	/**
	 * This Function deletes survey from Survey Admin Site
	 * @param Survey_flag
	 * @return
	 * @throws Throwable
	 */
	public static boolean deleteSurvey(String Survey_flag) throws Throwable
	{
		boolean flag=true;
		if(!click(SurveyOR.btnDeleteRow(Survey_flag),"Delete Survey"))
		{
			flag=false;
		}
		if(!js_click(SurveyOR.btnPopUpOK,"OK Button"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function deletes a page in Survey Admin
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean deleteAPage() throws Throwable
	{
		boolean flag=true;
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div"))).perform();
		String question = Actiondriver.getText(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div/h4"), "survey question");
		System.out.println(question);
		if(!Actiondriver.click(By.xpath("//div[@class='survey-button delete']"),"Delete button"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnPopUpOK,"OK Button"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		Thread.sleep(3000);
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext,"Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!ActiondriverM.verifyText(SurveyOR.txtRequiredQtnsError,"Required fields cannot be empty","Required fields cannot be empty"))
		{
			flag=false;
		}
		/*if(!click(By.xpath("//li[h4]/following-sibling::li[input[@type='radio']][1]"),"Answer"))
		{
			flag=false;
		}*/
		//BusinessFunctionsM.refresh();
		driverM.navigate().refresh();
		ActiondriverM.waitForVisibilityOfElement(By.xpath("//form/ul/li/h4"), "Question in Survey site");
		String survey_question=ActiondriverM.getText(By.xpath("//form/ul/li/h4"), "Question in Survey site");
		System.out.println(survey_question);
		if(!(survey_question.equalsIgnoreCase(question)))
		{
			Reporters.SuccessReport("Verify existence of deleted survey question "+question+" on Survey site",question+ " is not available on survey site");
			flag=true;
		}
		else
		{
			Reporters.failureReport("Verify existence of deleted survey question "+question+" on Survey site", question+ " is still available on survey site");
			flag=false;
		}
		if(!ActiondriverM.click(By.xpath("//label[text()='Vanilla']/preceding-sibling::input[@type='checkbox']"),"Check the survey question answer"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		return flag;
	}
	/**
	 * This function places the current survey question to next position
	 * @return boolean (true/false)
	 * @throws Throwable
	 */

	public static boolean moveCurrentQuestionToNextPosition() throws Throwable
	{
		boolean flag=true;
		Actions action=new Actions(driver);
		Actiondriver.waitForVisibilityOfElement(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div/h4"), "Survey Questyion");
		String text=Actiondriver.getText(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div/h4"), "survey qtn text");
		System.out.println(text);
		//swap pages- page 1 under page 2
		Actiondriver.draganddrop(By.xpath("//div[h3[text()='Page 1']]"), By.xpath("//div[div[h3[text()='Page 3']]]/preceding-sibling::div[1]/a[text()='+ Add A New Page']"), "");
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//h4[text()='Please rate the importance of the following qualities of ice cream. ']"))).doubleClick().build().perform();
		String rating=Actiondriver.getAttribute(By.xpath("//label[text()='Column Choices (Ratings)']/following-sibling::table/tbody/tr/td/input[@value='Important']"),"value", "rating name");
		System.out.println(rating);
		if(!Actiondriver.click(By.xpath("//td[input[@value='Important']]/following-sibling::td/div[@class='delete-row']"),"Delete icon of Important"))
		{
			flag=false;
		}
		Thread.sleep(1000);
		if(!Actiondriver.click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		//BusinessFunctionsM.refresh();
		driver.navigate().refresh();
		Thread.sleep(2000);
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnPrevious, "Previous button");
		if(!ActiondriverM.click(SurveyOR.btnPrevious,"Previous button"))
		{
			flag=false;
		}
		if(!ActiondriverM.isElementPresent(By.xpath("//label[text()='"+rating+"']"), "Rating"))
		{
			Reporters.SuccessReport("Verify deleted rating "+rating+" on Survey Site", "Does not display deleted rating: "+rating);
		}
		else
		{
			Reporters.failureReport("Verify deleted rating "+rating+" on Survey Site", "Still display deleted rating: "+rating);
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Quality of ingredients"),"Quality of ingredients radio button");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Quality of ingredients"),"Quality of ingredients radio button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Flavor"),"Flavor radio button");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Flavor"),"Flavor radio button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_radioButtonMatrix("Texture"),"Texture radio button");
		if(!ActiondriverM.click(SurveyOR.m_radioButtonMatrix("Texture"),"Texture radio button"))
		{
			flag=false;
		}
		Thread.sleep(1000);
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnNext, "Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		//verifyTextPresent(text);
		return flag;
	}

	/**
	 * This Function performs some modification in Survey like adding a survey question,deleting a survey answer,updating survey and answer,deleting the last survey question etc
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean modificationsInSurvey() throws Throwable
	{
		boolean flag=true;
		String survey_qtn="Current Testing";
		String ratingText="Me Testing";
		String delete_rating="Vanilla";
		Actions action=new Actions(driver);
		String text2=ActiondriverM.getText(By.xpath("//form//li/h4"), "Survey Question");
		System.out.println(text2);
		BusinessFunctionsM.waitForVisibilityOfElement(SurveyOR.btnNext, "Next Button");
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.m_chckBoxMyFamily("Me"),"Me Check box"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.m_chckBoxMyFamily("My Spouse"),"My Spouse Check box"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.m_chckBoxMyFamily("My Children"),"My Children Check box"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//h4[text()='My family likes the following flavors of ice cream. ']"))).doubleClick().build().perform();
		if(!Actiondriver.type(By.xpath("//input[@class='question-text']"),survey_qtn,"current survey question"))
		{
			flag=false;
		}
		if(!Actiondriver.click(By.xpath("//td[input[@value='"+delete_rating+"']]/following-sibling::td/div[@class='delete-row']"),"Delete "+delete_rating+" rating"))
		{
			flag=false;
		}
		if(!Actiondriver.type(By.xpath("//input[@value='Me']"),ratingText,"Change row choices text"))
		{
			flag=false;
		}
		if(!Actiondriver.click(By.xpath("//div[@class='required']/input[@type='checkbox']"),"Required check box"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		Thread.sleep(1000);
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		//BusinessFunctionsM.refresh();
		driverM.navigate().refresh();
		ActiondriverM.waitForVisibilityOfElement(By.xpath("//form/ul/li/h4[starts-with(text(),'"+survey_qtn+"')]"), "Updated Survey question");
		if(ActiondriverM.isElementPresent(By.xpath("//form/ul/li/h4[starts-with(text(),'"+survey_qtn+"')]"), "Updated Survey question"))
		{
			Reporters.SuccessReport("Verify whether updated survey question "+survey_qtn+" is reflected in Survey site or not", "Survey question "+survey_qtn+" got successfully updated");
		}
		else
		{
			Reporters.failureReport("Verify whether updated survey question "+survey_qtn+" is reflected in Survey site or not", "Survey question "+survey_qtn+" is failed to update");
		}
		if(ActiondriverM.isElementPresent(By.xpath("//form/ul/li/h4/span[@class='optional']"), "Optional text"))
		{
			Reporters.SuccessReport("Verify whether survey question made optional in EMT is reflecting on Survey site or not", "Survey question made optional successfully");
		}
		else
		{
			Reporters.failureReport("Verify whether survey question made optional in EMT is reflecting on Survey site or not", "Survey question is not optional");
		}
		if(ActiondriverM.isElementPresent(By.xpath("//li[1]/label[text()='"+ratingText+"']"), "Updated rating"))
		{
			Reporters.SuccessReport("Verify whether updated rating "+ratingText+" is reflected in Survey site or not", "Rating "+ratingText+" got successfully updated");
		}
		else
		{
			Reporters.failureReport("Verify whether updated rating "+ratingText+" is reflected in Survey site or not", "Rating "+ratingText+" is failed to update");
		}
		if(!ActiondriverM.isElementPresent(By.xpath("//li/label[text()='"+delete_rating+"']"), "Deleted Rating"))
		{
			Reporters.SuccessReport("Verify whether updated rating "+delete_rating+" is reflected in Survey site or not", "Rating "+delete_rating+" got successfully deleted");
		}
		else
		{
			Reporters.failureReport("Verify whether updated rating "+delete_rating+" is reflected in Survey site or not", "Rating "+delete_rating+" is failed to delete");
		}
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		Actiondriver.waitForVisibilityOfElement(By.xpath("//div[div[h3[text()='Page 5']]]/preceding-sibling::div[1]/a[text()='+ Add A New Page']"), "");
		Actiondriver.draganddrop(driver.findElement(By.xpath("//div[h3[text()='Page 3']]")), By.xpath("//div[div[h3[text()='Page 5']]]/preceding-sibling::div[1]/a[text()='+ Add A New Page']"), text, "");
		if(!BusinessFunctions.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		if(!ActiondriverM.type(SurveyOR.txtArea,"testing","Text Area"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		action.moveToElement(driver.findElement(By.xpath("//h4[text()='Please rate the importance of the following qualities of ice cream. ']"))).doubleClick().build().perform();
		if(!Actiondriver.type(By.xpath("//input[@class='question-text']"),"Automation Testing","previously answered survey question"))
		{
			flag=false;
		}
		if(!Actiondriver.click(By.xpath("//td[input[@value='Extremely Important']]/following-sibling::td/div[@class='delete-row']"),"Delete icon of Extremely Important"))
		{
			flag=false;
		}
		if(!Actiondriver.type(By.xpath("//input[@value='Quality of ingredients']"),"Quality of ingredients Testing","Quality of ingredients text box"))
		{
			flag=false;
		}
		if(!Actiondriver.click(By.xpath("//div[@class='required']/input[@type='checkbox']"),"Required check box"))
		{ 
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		Actiondriver.waitForVisibilityOfElement(By.xpath("//div[@class='survey-button delete']"),"Delete button");
		if(!Actiondriver.js_click(By.xpath("//div[@class='survey-button delete']"),"Delete button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!Actiondriver.js_click(SurveyOR.btnPopUpOK,"OK Button"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;	
		}
		Thread.sleep(1000);
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.btnPrevious,"Previous button");
		if(!ActiondriverM.click(SurveyOR.btnPrevious,"Previous button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		if(!Actiondriver.click(By.xpath("//div[h4[text()='This is a photo featuring delicious ice cream.']]/parent::div/following-sibling::div/a"),"New Page label"))
		{
			flag=false;
		}
		Actiondriver.draganddrop(driver.findElement(By.xpath("//a[text()='Multiple Choice / One Answer']")), By.xpath("//div[@class='page new-page ui-sortable']"), "Add new question", "New Page");
		if(!Actiondriver.click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!ActiondriverM.click(SurveyOR.rdYes,"Yes Button"))
		{
			flag=false;
		}
		if(!ActiondriverM.click(SurveyOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		//Change single text box survey question
		action.moveToElement(driver.findElement(By.xpath("//div[@id='survey']/div[@class='page ui-sortable'][last()]/div[2]"))).doubleClick().build().perform();;
		Thread.sleep(2000);
		String singleTextBoxSurveyqtn="Update Single text box survey question";
		if(!Actiondriver.type(By.xpath("//input[@class='question-text']"),singleTextBoxSurveyqtn,"current survey question"))
		{
			flag=false;
		}
		if(!Actiondriver.click(SurveyOR.btnSaveQuestion,"Save Question"))
		{
			flag=false;
		}
		Thread.sleep(1000);
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		//BusinessFunctionsM.refresh();
		driverM.navigate().refresh();
		ActiondriverM.verifyTextPresent(singleTextBoxSurveyqtn);
		Thread.sleep(1000);
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		//delete last qtn
		action.moveToElement(driver.findElement(By.xpath("//div[@id='survey']/div[@class='page ui-sortable'][last()]/div[2]"))).perform();
		Thread.sleep(2000);
		if(!Actiondriver.js_click(By.xpath("//div[@class='survey-button delete']"),"Delete button"))
		{
			flag=false;
		}
		if(!Actiondriver.js_click(SurveyOR.btnPopUpOK,"OK Button"))
		{
			flag=false;
		}
		Thread.sleep(2000);
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		/*if(!switchWindowByTitle("Survey site for Testing NowNext for Innovate", 2))
		{
			flag=false;
		}*/
		//BusinessFunctionsM.refresh();
		driverM.navigate().refresh();
		Thread.sleep(1000);
		ActiondriverM.verifyTextPresent("Thank you for your feedback!");
		ActiondriverM.click(SurveyOR.m_btnBacktoList, "Back to List");
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
		ActiondriverM.click(SurveyOR.m_btnMenu, "Show Menu Button");
		if(BusinessFunctionsM.surveyLogOut())
		{
			Reporters.SuccessReport("Logout from survey site", "Logged out from survey site successfully");
		}
		else
		{
			Reporters.failureReport("Logout from survey site","Failed to log out from survey site");
		}
		driverM.quit();
		/*if(!switchWindowByTitle("Reporting Site", 1))
		{
			flag=false;
		}*/
		List<WebElement> pages= driver.findElements(By.xpath("//div[@id='survey']/div[@class='page ui-sortable']"));
		for (int link_count = 0; link_count < pages.size(); link_count++) {
			Thread.sleep(2000);
			action.moveToElement(driver.findElement(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div"))).perform();
			Actiondriver.waitForVisibilityOfElement(By.xpath("//div[@class='survey-button delete']"),"Delete button");
			if(!Actiondriver.click(By.xpath("//div[@class='survey-button delete']"),"Delete button"))
			{
				flag=false;
			}
			if(!Actiondriver.click(SurveyOR.btnPopUpOK,"OK Button"))
			{
				flag=false;
			}
		}
		Thread.sleep(2000);
		if(!Actiondriver.click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		if(!BusinessFunctions.emtLogOut())
		{
			flag=false;
		}
		return flag;
	}
	/**
	 * This function makes the fields in Screen Layout editable
	 * @return boolean (true/false)
	 * @throws Throwable
	 */

	public static boolean makeFieldsEditable() throws Throwable
	{
		boolean flag=true;
		try {
			if (isElementPresent(By.xpath("//ul[@id='left-column' or @id='right-column']/li/div[@class='editable']/input[not(@checked)]"), "Editable Checkbox")) {
				List<WebElement> checkboxes = driverM
						.findElements(By
								.xpath("//ul[@id='left-column' or @id='right-column']/li/div[@class='editable']/input[not(@checked)]"));
				for (WebElement chckbox : checkboxes) {
					if (!click(chckbox, "checkbox")) {
						flag = false;
					}
				}
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function is used to logout from Checkin site
	 * @return oolean (true/false)
	 * @throws Throwable
	 */
	public static boolean checkInSiteLogout() throws Throwable
	{
		boolean flag=true;
		if(!click(CheckInOR.btnLogout, "Logout Button"))
		{
			flag=false;
		}

		if(!isElementPresent(CheckInOR.btnSignIn, "Sign In Button"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function adds Registrant in Checkin Site
	 * @param prefix- reads Prefix data from Test Data Excel sheet
	 * @param attendee- reads attendee data from Test Data Excel sheet
	 * @param first- reads first data from Test Data Excel sheet
	 * @param last- reads last data from Test Data Excel sheet
	 * @param status- reads status data from Test Data Excel sheet
	 * @param AttendeeType- reads AttendeeType data from Test Data Excel sheet
	 * @param Title- reads Title data from Test Data Excel sheet
	 * @param Phone- reads Phone data from Test Data Excel sheet
	 * @param Mobile- reads Mobile data from Test Data Excel sheet
	 * @param AltPhone- reads AltPhone data from Test Data Excel sheet
	 * @param Fax- reads Fax data from Test Data Excel sheet
	 * @param Company- reads Company data from Test Data Excel sheet
	 * @param Address1- reads Address1 data from Test Data Excel sheet
	 * @param Address2- reads Address2 data from Test Data Excel sheet
	 * @param County- reads County data from Test Data Excel sheet
	 * @param Region- reads Region data from Test Data Excel sheet
	 * @param City- reads City data from Test Data Excel sheet
	 * @param CountryCode- reads CountryCode data from Test Data Excel sheet
	 * @param Country- reads Country data from Test Data Excel sheet
	 * @param ZipCode- reads ZipCode data from Test Data Excel sheet
	 * @param PersonalEmail- reads PersonalEmail data from Test Data Excel sheet
	 * @param Email- reads Email data from Test Data Excel sheet
	 * @param LogInID- reads LogInID data from Test Data Excel sheet
	 * @param Pswd- reads Pswd data from Test Data Excel sheet
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean addRegistrantInfoInCheckIn(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,
			String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
			String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,String LogInID,String Pswd) throws Throwable
			{
		boolean flag=true;
		if(!prefix.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
				flag=false;
		}

		if(!attendee.isEmpty()){
			if(!type(CheckInOR.txtBox("Attendee #"),attendee," attendee number"))
				flag=false;
		}
		if(!first.isEmpty()){
			if(!type(CheckInOR.txtBox("First"),first,"Enter First name"))
				flag=false;
		}
		if(!last.isEmpty()){
			if(!type(CheckInOR.txtBox("Last"),last,"Enter Last name"))
				flag=false;
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CheckInOR.ddLabelText("Status"), "Status drop down")){
				if(verifyInDropDownList(CheckInOR.ddLabel("Status"), status)){
					if(!selectByVisibleText(CheckInOR.ddValue("Status"), status, "Status Drop Down")){
						flag=false;
					}
				}
			}
		}
		if(!AttendeeType.isEmpty())
		{
			if(isElementPresent(CheckInOR.ddLabelText("Attendee Type"), "Attendee Type drop down")){
				if(verifyInDropDownList(CheckInOR.ddLabel("Attendee Type"), AttendeeType)){
					if(!selectByVisibleText(CheckInOR.ddValue("Attendee Type"), AttendeeType, "AttendeeType Drop Down")){
						flag=false;
					}
				}
			}
		}
		if(!Title.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Title"), Title, "Title Text Box"))
				flag=false;
		}
		if(!Phone.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Phone"), Phone, "Phone Text Box"))
				flag=false;	
		}
		if(!Mobile.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Mobile Phone"), Mobile, "Mobile Phone Text Box"))
				flag=false;
		}
		if(!AltPhone.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Alt Phone"), AltPhone, "Alt Phone Text Box"))
				flag=false;
		}
		if(!Fax.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Fax"), Fax, "Fax Text Box"))
				flag=false;
		}
		if(!Company.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
		}
		if(!Address1.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Address1"), Address1, "Address1 Text Box"))
				flag=false;
		}
		if(!Address2.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Address2"), Address2, "Address2 Text Box"))
				flag=false;
		}
		if(!County.isEmpty())
		{
			if(!type(CheckInOR.txtBox("County"), County, "County Text Box"))
				flag=false;
		}
		if(!Region.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Region"), Region, "Region Text Box"))
				flag=false;
		}
		if(!City.isEmpty())
		{
			if(!type(CheckInOR.txtBox("City"), City, "City Text Box"))
				flag=false;
		}
		if(!CountryCode.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
		}
		if(!Country.isEmpty())
		{
			if(isElementPresent(CheckInOR.ddLabelText("Country"), "Country Drop Down"))
			{
				if(verifyInDropDownList(CheckInOR.ddLabel("Country"), Country)){
					if(!selectByVisibleText(CheckInOR.ddValue("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
			}
		}
		if(!ZipCode.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;	
		}
		if(!PersonalEmail.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Personal Email"), PersonalEmail, "Personal Email Text Box"))
				flag=false;	
		}
		if(!Email.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Email"), Email, "Email Text Box"))
				flag=false;	
		}
		if(!LogInID.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Login Id"), LogInID, "Login Id Text Box"))
				flag=false;
		}
		if(!Pswd.isEmpty())
		{
			if(!type(CheckInOR.txtBox("Password"), Pswd, "Password Text Box"))
				flag=false;
		}

		return flag;
			}
	/**
	 * This function verifies the Registrant details created in Checkin Site in EMT Site 
	 * @param prefix- reads prefix data from Test Data Excel sheet
	 * @param attendee- reads attendee data from Test Data Excel sheet
	 * @param first- reads first data from Test Data Excel sheet
	 * @param last- reads last data from Test Data Excel sheet
	 * @param status- reads status data from Test Data Excel sheet
	 * @param AttendeeType- reads AttendeeType data from Test Data Excel sheet
	 * @param Title- reads Title data from Test Data Excel sheet
	 * @param Phone- reads Phone data from Test Data Excel sheet
	 * @param Mobile- reads Mobile data from Test Data Excel sheet
	 * @param AltPhone- reads AltPhone data from Test Data Excel sheet
	 * @param Fax- reads Fax data from Test Data Excel sheet
	 * @param Company- reads Company data from Test Data Excel sheet
	 * @param Address1- reads Address1 data from Test Data Excel sheet
	 * @param Address2- reads Address2 data from Test Data Excel sheet
	 * @param county- reads county data from Test Data Excel sheet
	 * @param region- reads region data from Test Data Excel sheet
	 * @param City- reads City data from Test Data Excel sheet
	 * @param CountryCode- reads CountryCode data from Test Data Excel sheet
	 * @param Country- reads Country data from Test Data Excel sheet
	 * @param ZipCode- reads ZipCode data from Test Data Excel sheet
	 * @param PersonalEmail- reads PersonalEmail data from Test Data Excel sheet
	 * @param Email- reads Email data from Test Data Excel sheet
	 * @param LoginID- reads LoginID data from Test Data Excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */

	public static boolean verifyCheckinRegistrantDetailsInEMT(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,String Phone,String Mobile,String AltPhone,String Fax,
			String Company,String Address1,String Address2,String county,String region,String City,String CountryCode,
			String Country,String ZipCode,String PersonalEmail,String Email,String LoginID) throws Throwable
			{
		boolean flag=true;
		if(!verifyText(CommonOR.lbltxtValues("Prefix"), prefix, "Prefix")){
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Attendee #"), attendee, "Attendee #")){
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("First"), first, "First")){
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Last"), last, "Last")){
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Status"), status, "Status")){
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Attendee Type"), AttendeeType, "Attendee Type")){
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Title"), Title, "Title"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Phone"), Phone, "Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Mobile Phone"), Mobile, "Mobile Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Alt Phone"), AltPhone, "Alt Phone"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Fax"), Fax, "Fax"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Company"), Company, "Company"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address1"), Address1, "Address1"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Address2"), Address2, "Address2"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("County"),county, "County"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Region"), region, "Region"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("City"), City, "City"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Country Code"), CountryCode, "Country Code"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lblddValues("Country"), Country, "Country"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Zip Code"), ZipCode, "Zip Code"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Personal Email"), PersonalEmail, "Personal Email"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Email"), Email, "Email"))
		{
			flag=false;
		}
		if(!verifyText(CommonOR.lbltxtValues("Login Id"),LoginID , "Login Id"))
		{
			flag=false;
		}
		return flag;
			}

	/**
	 * This Function verifies the Checkin Status of a Registrant
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean verifyCheckInStatus() throws Throwable
	{
		boolean flag=true;
		if(!isElementPresent(By.xpath("//td[div[text()='Checked-in']]/following-sibling::td/div//div[text()='Yes']"), "Checked In Status"))
		{
			flag=false;
		}
		return flag;
	}

	/**
	 * This Function is used to edit Categories Information of a Session
	 * @param Program- reads Program Data from Test Data Excel Sheet
	 * @param Track- reads Track Data from Test Data Excel Sheet
	 * @param SubTrack- reads SubTrack Data from Test Data Excel Sheet
	 * @param TargetRole- reads TargetRole Data from Test Data Excel Sheet
	 * @param Audience- reads Audience Data from Test Data Excel Sheet
	 * @param ByInviteOnly- reads ByInviteOnly Data from Test Data Excel Sheet
	 * @param Timeslot- reads Timeslot Data from Test Data Excel Sheet
	 * @param Market- reads Market Data from Test Data Excel Sheet
	 * @param Industry- reads Industry Data from Test Data Excel Sheet
	 * @param Product- reads Product Data from Test Data Excel Sheet
	 * @param ContentCategory- reads ContentCategory Data from Test Data Excel Sheet
	 * @param SessionTheme- reads SessionTheme Data from Test Data Excel Sheet
	 * @param ContentLevel- reads ContentLevel Data from Test Data Excel Sheet
	 * @param SessionType- reads SessionType Data from Test Data Excel Sheet
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean editCategories(String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType) throws Throwable{
		boolean flag=true;

		/*if(!Program.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Program"), "Program")){
				if(!click(CommonOR.txtBoxData("Program"),"Program drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Program"),"Program Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Program", Program)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Program"), Program, "Program Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Program"), Program, "Program Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Program"), "Plus Button of Program")){
						flag=false;
					}
				}
				waitForVisibilityOfElement(CommonOR.btnSave("Program"), "Save button");
				if(!click(CommonOR.btnSave("Program"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.btnSave("Program"), "Save button");
			}
		}


		if(!Track.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Track"), "Track")){
				if(!click(CommonOR.txtBoxData("Track"),"Track drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Track"),"Track Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Track", Track)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Track"), Track, "Track Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Track"), Track, "Track Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Track"), "Plus Button of Track")){
						flag=false;
					}
				}
				waitForVisibilityOfElement(CommonOR.btnSave("Track"), "Save button");
				if(!click(CommonOR.btnSave("Track"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.btnSave("Track"), "Save button");
			}
		}

		if(!SubTrack.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Sub-Track"), "Sub-Track Drop Down")){
				if(!click(CommonOR.txtBoxData("Sub-Track"),"Sub-Track drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Sub-Track"),"Sub-Track Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Sub-Track", SubTrack)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Sub-Track"), SubTrack, "Sub-Track Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Sub-Track"), SubTrack, "Sub-Track Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Sub-Track"), "Plus Button of Sub-Track")){
						flag=false;
					}
				}
				waitForVisibilityOfElement(CommonOR.btnSave("Sub-Track"), "Save button");
				if(!click(CommonOR.btnSave("Sub-Track"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.btnSave("Sub-Track"), "Save button");
			}
		}*/

		if(!TargetRole.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Target Role"), "Target Role Drop Down")){
				if(!click(CommonOR.txtBoxData("Target Role"),"Target Role drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Target Role"),"Target Role Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Target Role", TargetRole)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Target Role"), TargetRole, "Target Role Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Target Role"), TargetRole, "Target Role Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Target Role"), "Plus Button of Target Role")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Target Role"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Target Role"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!Audience.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Audience"), "Audience Drop Down")){
				if(!click(CommonOR.txtBoxData("Audience"),"Audience drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Audience"),"Audience Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Audience", Audience)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Audience"), Audience, "Audience Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Audience"), Audience, "Audience Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Audience"), "Plus Button of Audience")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Audience"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Audience"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!ByInviteOnly.isEmpty()){
			if(!click(CommonOR.txtBoxData("By Invite Only"),"By Invite Only drop down"))
				flag=false;
			waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
			if(!click(CommonOR.ddRemoveValue("By Invite Only"),"By Invite Only Value"))
				flag=false;
			if(verifyInDropDownList("By Invite Only", ByInviteOnly)){
				Thread.sleep(1000);
				if(!selectByVisibleText(EMT_SessionsOR.ddLabel("By Invite Only"), ByInviteOnly, "By Invite Only Drop Down")){
					flag=false;
				}
			}
			else{
				if(!type(EMT_SessionsOR.txtAddNew("By Invite Only"), ByInviteOnly, "By Invite Only Add New Text Box")){
					flag=false;
				}
				if(!click(EMT_SessionsOR.btnPlus("By Invite Only"), "Plus Button of By Invite Only")){
					flag=false;
				}
			}
			//waitForVisibilityOfElement(CommonOR.btnSave("By Invite Only"), "Save button");
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			if(!click(CommonOR.btnSave("By Invite Only"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}



		if(!Market.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Market"), "Market Drop Down")){
				if(!click(CommonOR.txtBoxData("Market"),"Market drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Market"),"Market Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Market", Market)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Market"), Market, "Market Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Market"), Market, "Market Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Market"), "Plus Button of Market")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Market"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Market"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!Industry.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Industry"), "Industry Drop Down")){
				if(!click(CommonOR.txtBoxData("Industry"),"Industry drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Industry"),"Industry Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Industry"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Industry"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!Product.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Product"), "Product Drop Down")){
				if(!click(CommonOR.txtBoxData("Product"),"Product drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Product"),"Product Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Product", Product)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Product"), Product, "Product Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Product"), Product, "Product Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Product"), "Plus Button of Product")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Product"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Product"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!ContentCategory.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Content Category"), "Content Category Drop Down")){
				if(!click(CommonOR.txtBoxData("Content Category"),"Content Category drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Content Category"),"Content Category Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Content Category", ContentCategory)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Content Category"), ContentCategory, "Content Category Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Content Category"), ContentCategory, "Content Category Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Content Category"), "Plus Button of Content Category")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Content Category"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Content Category"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!SessionTheme.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Theme"), "Session Theme Drop Down")){
				if(!click(CommonOR.txtBoxData("Session Theme"),"Session Theme drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Session Theme"),"Session Theme Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Session Theme", SessionTheme)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Theme"), SessionTheme, "Session Theme Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Theme"), SessionTheme, "Session Theme Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Theme"), "Plus Button of Session Theme")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Session Theme"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Session Theme"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!ContentLevel.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Content Level"), "Content Level Drop Down")){
				if(!click(CommonOR.txtBoxData("Content Level"),"Content Level drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Content Level"),"Content Level Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Content Level", ContentLevel)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Content Level"), ContentLevel, "Content Level Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Content Level"), ContentLevel, "Content Level Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Content Level"), "Plus Button of Content Level")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Content Level"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Content Level"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		if(!SessionType.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Type"), "Session Type Drop Down")){
				if(!click(CommonOR.txtBoxData("Session Type"),"Session Type drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Session Type"),"Content Level Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Session Type", SessionType)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Type"), SessionType, "Session Type Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Type"), SessionType, "Session Type Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Type"), "Plus Button of Session Type")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Session Type"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Session Type"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}

		return flag;
	}

	/**
	 * This function is used to edit Additional Information of a Session
	 * @param Additional_Info_1- reads Additional Informational 1 data from Test Data Excel sheet
	 * @param Additional_Info_2- reads Additional Informational 2 data from Test Data Excel sheet
	 * @param Additional_Info_3- reads Additional Informational 3 data from Test Data Excel sheet
	 * @param Additional_Info_4- reads Additional Informational 4 data from Test Data Excel sheet
	 * @param Additional_Info_5- reads Additional Informational 5 data from Test Data Excel sheet
	 * @param Demo1- reads Demographic 1 data from Test Data Excel sheet
	 * @param Demo3- reads Demographic 3 data from Test Data Excel sheet
	 * @param Demo4- reads Demographic 4 data from Test Data Excel sheet
	 * @param Demo7- reads Demographic 7 data from Test Data Excel sheet
	 * @param Demo9- reads Demographic 9 data from Test Data Excel sheet
	 * @param Demo11- reads Demographic 11 data from Test Data Excel sheet
	 * @param Demo12- reads Demographic 12 data from Test Data Excel sheet
	 * @param Demo14- reads Demographic 14 data from Test Data Excel sheet
	 * @param SessionCode- reads Session Code Data from Test Data Excel sheet
	 * @param Topic_Tag- reads Topic Tag Data from Test Data Excel sheet
	 * @param CustomSql1- reads Custom Sql Data from Test Data Excel sheet
	 * @param ProgTrackSub- reads ProgTrackSub Data from Test Data Excel sheet
	 * @param Keywords- reads Keywords Data from Test Data Excel sheet
	 * @param ImportIntCustomSQLEnabled- reads ImportIntCustomSQLEnabled Data from Test Data Excel sheet
	 * @param Paper_Type- reads Paper_Type Data from Test Data Excel sheet
	 * @param NowNextIPAddress- reads NowNextIPAddress Data from Test Data Excel sheet
	 * @param Field2014- reads Field2014 Data from Test Data Excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean editAdditionalInformation(String Additional_Info1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub,String Keywords,String ImportIntCustomSQLEnabled,String Paper_Type,String NowNextIPAddress,String Field2014) throws Throwable
			{
		boolean flag=true;
		if(!Additional_Info1.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 1"),"Additional Info 1"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 1"), Additional_Info1, "Additional Info 1 Text Area")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Additional Info 1"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Additional_Info_2.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 2"),"Additional Info 2"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 2"), Additional_Info_2, "Additional Info 2 Text Area")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Additional Info 2"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Additional_Info_3.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 3"),"Additional Info 3"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 3"), Additional_Info_3, "Additional Info 3 Text Area")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Additional Info 3"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Additional_Info_4.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 4"),"Additional Info 4"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 4"), Additional_Info_4, "Additional Info 4 Text Area")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Additional Info 4"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Additional_Info_5.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Additional Info 5"),"Additional Info 5"))
				flag=false;
			if(!type(CommonOR.txtArea("Additional Info 5"), Additional_Info_5, "Additional Info 5 Text Area")){
				flag=false;
			}
			if(!click(CommonOR.btnSave("Additional Info 5"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Keywords.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Keywords"),"Keywords"))
				flag=false;
			if(!type(CommonOR.txtArea("Keywords"), Keywords, "Keywords Text Area"))
				flag=false;
			if(!click(CommonOR.btnSave("Keywords"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
		}
		if(!Demo1.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 1"), "Demographic 1 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 1"),"Demographic 1 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 1"),"Demographic 1 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 1", Demo1)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 1"), Demo1, "Demographic 1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 1"), Demo1, "Demographic 1 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 1"), "Plus Button of Demographic 1")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 1"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 1"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo3.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 3"), "Demographic 3 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 3"),"Demographic 3 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 3"),"Demographic 3 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 3", Demo3)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 3"), Demo3, "Demographic 3 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 3"), Demo3, "Demographic 3 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 3"), "Plus Button of Demographic 3")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 3"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 3"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo4.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 4"), "Demographic 4 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 4"),"Demographic 4 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 4"),"Demographic 4 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 4", Demo4)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 4"), Demo4, "Demographic 4 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 4"), Demo4, "Demographic 4 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 4"), "Plus Button of Demographic 4")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 4"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 4"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo7.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 7"), "Demographic 7 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 7"),"Demographic 7 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 7"),"Demographic 7 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 7", Demo7)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 7"), Demo7, "Demographic 7 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 7"), Demo7, "Demographic 7 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 7"), "Plus Button of Demographic 7")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 7"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 7"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo9.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 9"), "Demographic 9 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 9"),"Demographic 9 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 9"),"Demographic 9 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 9", Demo9)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 9"), Demo9, "Demographic 9 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 9"), Demo9, "Demographic 9 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 9"), "Plus Button of Demographic 9")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 9"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 9"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo11.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 11"), "Demographic 11 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 11"),"Demographic 11 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 11"),"Demographic 11 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 11", Demo11)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 11"), Demo11, "Demographic 11 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 11"), Demo11, "Demographic 11 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 11"), "Plus Button of Demographic 11")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 11"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 11"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo12.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 12"), "Demographic 12 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 12"),"Demographic 12 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 12"),"Demographic 12 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 12", Demo12)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 12"), Demo12, "Demographic 12 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 12"), Demo12, "Demographic 12 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 12"), "Plus Button of Demographic 12")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 12"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 12"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Demo14.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Demographic 14"), "Demographic 14 Drop Down")){
				if(!click(CommonOR.txtBoxData("Demographic 14"),"Demographic 14 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Demographic 14"),"Demographic 14 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Demographic 14", Demo14)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Demographic 14"), Demo14, "Demographic 14 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Demographic 14"), Demo14, "Demographic 14 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Demographic 14"), "Plus Button of Demographic 14")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Demographic 14"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Demographic 14"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!SessionCode.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Session Code"), "Session Code Drop Down")){
				if(!click(CommonOR.txtBoxData("Session Code"),"Session Code drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Session Code"),"Session Code Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Session Code", SessionCode)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Session Code"), SessionCode, "Session Code Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Session Code"), SessionCode, "Session Code Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Session Code"), "Plus Button of Session Code")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Session Code"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Session Code"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Topic_Tag.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Topic Tag"), "Topic Tag Drop Down")){
				if(!click(CommonOR.txtBoxData("Topic Tag"),"Topic Tag drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Topic Tag"),"Topic Tag Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Topic Tag", Topic_Tag)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Topic Tag"), Topic_Tag, "Topic Tag Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Topic Tag"), Topic_Tag, "Topic Tag Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Topic Tag"), "Plus Button of Topic Tag")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Topic Tag"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Topic Tag"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!CustomSql1.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("CustomSql1"), "CustomSql1 Drop Down")){
				if(!click(CommonOR.txtBoxData("CustomSql1"),"CustomSql1 drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("CustomSql1"),"CustomSql1 Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("CustomSql1", CustomSql1)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("CustomSql1"), CustomSql1, "CustomSql1 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("CustomSql1"), CustomSql1, "CustomSql1 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("CustomSql1"), "Plus Button of CustomSql1")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("CustomSql1"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("CustomSql1"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!ImportIntCustomSQLEnabled.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("ImportIntCustomSQLEnabled"), "ImportIntCustomSQLEnabled Drop Down")){
				if(!click(CommonOR.txtBoxData("ImportIntCustomSQLEnabled"),"ImportIntCustomSQLEnabled drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("ImportIntCustomSQLEnabled"),"ImportIntCustomSQLEnabled Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("ImportIntCustomSQLEnabled", ImportIntCustomSQLEnabled)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("ImportIntCustomSQLEnabled"), ImportIntCustomSQLEnabled, "ImportIntCustomSQLEnabled Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("ImportIntCustomSQLEnabled"), ImportIntCustomSQLEnabled, "ImportIntCustomSQLEnabled Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("ImportIntCustomSQLEnabled"), "Plus Button of ImportIntCustomSQLEnabled")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("ImportIntCustomSQLEnabled"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("ImportIntCustomSQLEnabled"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!Paper_Type.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Paper Type"), "Paper_Type Drop Down")){
				if(!click(CommonOR.txtBoxData("Paper Type"),"Paper Type drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Paper Type"),"Paper Type Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Paper Type", Paper_Type)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Paper Type"), Paper_Type, "Paper_Type Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Paper Type"), Paper_Type, "Paper_Type Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Paper Type"), "Plus Button of Paper_Type")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Paper Type"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Paper Type"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		if(!NowNextIPAddress.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("Now Next IP Address"), "NowNextIPAddress Drop Down")){
				if(!click(CommonOR.txtBoxData("Now Next IP Address"),"Now Next IP Address drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Now Next IP Address"),"Now Next IP Address Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Now Next IP Address", NowNextIPAddress)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("Now Next IP Address"), NowNextIPAddress, "NowNextIPAddress Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Now Next IP Address"), NowNextIPAddress, "NowNextIPAddress Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Now Next IP Address"), "Plus Button of NowNextIPAddress")){
						flag=false;
					}
				}
				//waitForVisibilityOfElement(CommonOR.btnSave("Now Next IP Address"), "Save button");
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
				if(!click(CommonOR.btnSave("Now Next IP Address"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading icon");
			}
		}
		/*if(!Field2014.isEmpty()){
			if(isElementPresent(EMT_SessionsOR.ddLabel("2014"), "2014 Drop Down")){
				if(verifyInDropDownList("2014", Field2014)){
					if(!selectByVisibleText(EMT_SessionsOR.ddLabel("2014"), Field2014, "2014 Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("2014"), Field2014, "2014 Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("2014"), "Plus Button of 2014")){
						flag=false;
					}
				}
			}
		}*/
		return flag;
			}

	/**
	 * This function is used to edit Room Details
	 * @param Name- reads Name from Test Data excel sheet
	 * @param Capacity- reads Capacity from Test Data excel sheet
	 * @param Description- reads Description from Test Data excel sheet
	 * @param zones- reads zones from Test Data excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean EditRoomDetails(String Name, String Capacity,String Description,String zones) throws Throwable{
		boolean flag=true;
		if(!Name.isEmpty()){
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Name"),"Name"))
				flag=false;
			if(!type(EMT_RoomsOR.txtName,Name,"Name Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Name"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Capacity.isEmpty()){
			if(!click(CommonOR.txtBoxData("Capacity"),"Capacity"))
				flag=false;
			if(!type(EMT_RoomsOR.txtCapacity,Capacity,"Capacity Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Capacity"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Description.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Description"),"Description"))
				flag=false;
			if(!type(CommonOR.txtBox("Description"), Description, "Description Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Description"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!zones.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Zones"), "Zones Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Zones"),"Zones drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Zones"),"Zones Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Zones", zones)){
					if(!selectByVisibleText(CommonOR.ddLabel("Zones"), zones, "Zones Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Zones"), zones," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Zones"), "Plus Button of zones")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Zones"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		Thread.sleep(2000);
		return flag;
	}
	/**
	 * This Function is used to edit information of an Association
	 * @param EPC- reads EPC number from Test Data Excel sheet
	 * @param attendee- reads attendee number from Test Data Excel sheet
	 * @param AssociationStation- reads AssociationStation from Test Data Excel sheet
	 * @return boolean(true/false)
	 * @throws Throwable
	 */

	public static boolean editAssociationInformation(String EPC,String attendee,String AssociationStation) throws Throwable
	{
		boolean flag=true;
		if(!EPC.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("EPC"),"EPC"))
				flag=false;
			if(!type(CommonOR.txtBox("EPC"), EPC, "EPC Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("EPC"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!attendee.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Attendee #"),"Attendee #"))
				flag=false;
			if(!type(CommonOR.txtBox("Attendee #"), attendee, "Attendee # Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Attendee #"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!AssociationStation.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Association Station"),"Association Station"))
				flag=false;
			if(!type(CommonOR.txtBox("Association Station"), AssociationStation, "Association Station Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Association Station"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		return flag;
	}

	/**
	 * This Function is used to edit Information of a Speaker
	 * @param prefix- reads prefix data from Test Data Excel sheet
	 * @param CustomerParticipantID- reads CustomerParticipantID data from Test Data Excel sheet
	 * @param FirstName- reads First Name data from Test Data Excel sheet
	 * @param status- reads status data from Test Data Excel sheet
	 * @param LastName- reads Last Name data from Test Data Excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */

	public static boolean editSpeakerInformation(String prefix,String CustomerParticipantID,String FirstName,String status,String LastName) throws Throwable{

		boolean flag=true;
		if(!prefix.isEmpty()){
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Prefix"),"Prefix"))
				flag=false;
			if(!type(CommonOR.txtBox("Prefix"),prefix,"Prefix Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Prefix"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!FirstName.isEmpty()){
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("First"),"First"))
				flag=false;
			if(!type(CommonOR.txtBox("First"),FirstName,"First Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("First"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!status.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Status"), "Status Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Status"),"Status drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Status"),"Status Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "Status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Status"), status,"Status Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Status"), "Plus Button of Status")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Status"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!LastName.isEmpty()){
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("Last"),"First"))
				flag=false;
			if(!type(CommonOR.txtBox("Last"),LastName,"Last Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Last"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		return flag;
	}

	/**
	 * This Function is used to edit Bio Information of a Speaker
	 * @param bio- reads Bio data from Test Data excel sheet
	 * @param WebURL- reads WebURL data from Test Data excel sheet
	 * @param Blog- reads Blog data from Test Data excel sheet
	 * @param LinkedInURL- reads LinkedInURL data from Test Data excel sheet
	 * @param FacebookURL- reads FacebookURL data from Test Data excel sheet
	 * @param TwitterAcnt- reads TwitterAcnt data from Test Data excel sheet
	 * @param IM- reads IM data from Test Data excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean editSpeakerBio(String bio,String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,String IM) throws Throwable
	{
		boolean flag=true;
		if(!bio.isEmpty()){
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Bio"),"Bio"))
				flag=false;
			if(!type(CommonOR.txtBio,bio,"bio Text Area Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Bio"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!WebURL.isEmpty()){
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("Web URL"),"Web URL"))
				flag=false;
			if(!type(CommonOR.txtBox("Web URL"),WebURL,"Web URL Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Web URL"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Blog.isEmpty()){
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("Blog"),"Blog"))
				flag=false;
			if(!type(CommonOR.txtBox("Blog"),Blog,"Blog Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Blog"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!LinkedInURL.isEmpty())
		{
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("LinkedIn URL"),"LinkedIn URL"))
				flag=false;
			if(!type(CommonOR.txtBox("LinkedIn URL"),LinkedInURL,"LinkedIn URL Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("LinkedIn URL"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!FacebookURL.isEmpty())
		{
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("Facebook URL"),"Facebook URL"))
				flag=false;
			if(!type(CommonOR.txtBox("Facebook URL"),FacebookURL,"Facebook URL text box"))
				flag=false;
			if(!click(CommonOR.btnSave("Facebook URL"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!TwitterAcnt.isEmpty())
		{
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("Twitter Account"),"Twitter Account"))
				flag=false;
			if(!type(CommonOR.txtBox("Twitter Account"),TwitterAcnt,"Twitter Account text box"))
				flag=false;
			if(!click(CommonOR.btnSave("Twitter Account"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!IM.isEmpty())
		{
			Thread.sleep(2000);
			if(!click(CommonOR.txtBoxData("IM"),"IM"))
				flag=false;
			if(!type(CommonOR.txtBox("IM"),IM,"IM text box"))
				flag=false;
			if(!click(CommonOR.btnSave("IM"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
	}

	/**
	 * This function is used to edit Contact information of a Speaker
	 * @param Fullname- reads Full Name data from Test Data excel sheet
	 * @param Nickname- reads Nick Name data from Test Data excel sheet
	 * @param Title- reads Title data from Test Data excel sheet
	 * @param phone- reads phone data from Test Data excel sheet
	 * @param mobile- reads mobile data from Test Data excel sheet
	 * @param alt- reads alt phone data from Test Data excel sheet
	 * @param fax- reads fax data from Test Data excel sheet
	 * @param Company- reads Company data from Test Data excel sheet
	 * @param Address1- reads Address 1 data from Test Data excel sheet
	 * @param Address2- reads Address 2 data from Test Data excel sheet
	 * @param County- reads County data from Test Data excel sheet
	 * @param City- reads City data from Test Data excel sheet
	 * @param Region- reads Region data from Test Data excel sheet
	 * @param Country- reads Country data from Test Data excel sheet
	 * @param CountryCode- reads Country Code data from Test Data excel sheet
	 * @param ZipCode- reads Zip Code data from Test Data excel sheet
	 * @param Email- reads Email data from Test Data excel sheet
	 * @param personalEmail- reads personal Email data from Test Data excel sheet
	 * @return boolean (true/False)
	 * @throws Throwable
	 */
	public static boolean editSpeakerContactInformation(String Fullname,String Nickname,String Title,String phone,String mobile,String alt,String fax,
			String Company,String Address1,String Address2,String County,String City,String Region,String Country,
			String CountryCode,String ZipCode,String Email,String personalEmail) throws Throwable

			{

		boolean flag=true;
		if(!Fullname.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Full Name"),"Full Name"))
				flag=false;
			if(!type(CommonOR.txtBox("Full Name"),Fullname,"Full Name Teext Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Full Name"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Nickname.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Nick Name"),"Nick Name"))
				flag=false;
			if(!type(CommonOR.txtBox("Nick Name"),Nickname,"Nick Name Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Nick Name"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Title.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Title"),"Title"))
				flag=false;
			if(!type(CommonOR.txtBox("Title"),Title,"Title Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Title"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!phone.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Phone"),"Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Phone"), phone, "Phone Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!mobile.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Mobile Phone"),"Mobile Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Mobile Phone"),mobile,"Mobile Phone Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Mobile Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!alt.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Alt Phone"),"Alt Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Alt Phone"),alt,"Alt Phone Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Alt Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!fax.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Fax"),"Fax"))
				flag=false;
			if(!type(CommonOR.txtBox("Fax"),fax,"Fax Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Fax"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Company.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Company"),"Company"))
				flag=false;
			if(!type(CommonOR.txtBox("Company"),Company,"Company Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Company"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Address1.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Address 1"),"Address 1"))
				flag=false;
			if(!type(CommonOR.txtBox("Address 1"),Address1,"Address 1 Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Address 1"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Address2.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Address 2"),"Address 2"))
				flag=false;
			if(!type(CommonOR.txtBox("Address 2"),Address2,"Address 2 Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Address 2"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!County.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("County"),"County"))
				flag=false;
			if(!type(CommonOR.txtBox("County"),County,"County Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("County"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!City.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("City"),"City"))
				flag=false;
			if(!type(CommonOR.txtBox("City"),City,"City Text box"))
				flag=false;
			if(!click(CommonOR.btnSave("City"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Region.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Region"),"Region"))
				flag=false;
			if(!type(CommonOR.txtBox("Region"),Region,"Region Text box"))
				flag=false;
			if(!click(CommonOR.btnSave("Region"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!Country.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Country"), "Country Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Country"),"Country drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Country"),"Country Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Country", Country)){
					if(!selectByVisibleText(CommonOR.ddLabel("Country"), Country, "Country Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(EMT_SessionsOR.txtAddNew("Country"), Country," Add New Text Box")){
						flag=false;
					}
					if(!click(EMT_SessionsOR.btnPlus("Country"), "Plus Button of Country")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Country"),"Save Button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!CountryCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Country Code"),"Country Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Country Code"), CountryCode, "Country Code Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Country Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!ZipCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Zip Code"),"Zip Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Zip Code"), ZipCode, "Zip Code Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Zip Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!personalEmail.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Personal Email"),"Personal Email"))
				flag=false;
			if(!type(CommonOR.txtBox("Personal Email"), personalEmail, "Personal Email Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Personal Email"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;

			}

	/**
	 * This function is used to edit Login information of a Speaker
	 * @param LoginID-reads Login ID data from Test Data excel sheet
	 * @param Password-reads Password data from Test Data excel sheet
	 * @param ConfirmPassword-reads Confirm Password data from Test Data excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */

	public static boolean editSpeakerLoginInformation(String LoginID,String Password,String ConfirmPassword) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Login Id"),"Login Id"))
				flag=false;
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Login Id"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
	}

	/**
	 * This function is used to edit the information of Categories Section of a Speaker
	 * @param Industry- reads Industry data from Test Data excel sheet
	 * @param ProductSpecality- reads Product Specality data from Test Data excel sheet
	 * @param Certification- reads Certification data from Test Data excel sheet
	 * @param division- reads division data from Test Data excel sheet
	 * @param Education- reads Education data from Test Data excel sheet
	 * @param JobRole- reads Job Role data from Test Data excel sheet
	 * @param market- reads market data from Test Data excel sheet
	 * @param territory- reads territory data from Test Data excel sheet
	 * @return boolean (true/False)
	 * @throws Throwable
	 */
	public static boolean editSpeakerCategories(String Industry,String ProductSpecality,String Certification,String division,String Education,String JobRole,String market,String territory) throws Throwable
	{
		boolean flag=true;
		if(!Industry.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Industry"),"Industry drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Industry"),"Industry Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Industry"),"Save Button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		/*if(!ProductSpecality.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Product Specialty"), "Product Specialty Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Product Specialty"),"Product Specialty drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Product Specialty"),"Product Specialty Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Product Specialty",ProductSpecality )){
					if(!selectByVisibleText(CommonOR.ddLabel("Product Specialty"),ProductSpecality, "Product Specialty Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Product Specialty"),ProductSpecality, "Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Product Specialty"),"Plus Button of Product Specialty"))
						flag=false;
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Product Specialty"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}

		}*/

		if(!Certification.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Certifications"),"Certifications drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Certifications"),"Certifications Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Certifications",Certification )){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"),Certification, "Certifications Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Certifications"),Certification, "Certifications Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Certifications"),"Plus Button of Certifications"))
						flag=false;
				}
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Certifications"),"Save Button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!division.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Division"),"Division drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Division"),"Division Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Division", division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), division," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Division"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!Education.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Education"),"Education drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Education"),"Education Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Education"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "Job Role Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Job Role"),"Job Role drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Job Role"),"Job Role Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Job Role",JobRole )){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"),JobRole, "Job Role Drop Down"))
						flag=false;
				}
				else
				{
					if(!type(CommonOR.txtAddNew("Job Role"),JobRole, "Add New Text Box"))
						flag=false;
					if(!click(CommonOR.btnPlus("Job Role"),"Plus Button of Job Role"))
						flag=false;
				}
			}
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.btnSave("Job Role"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		if(!market.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Market"), "Market Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Market"),"Market drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Market"),"Market Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Market", market)){
					if(!selectByVisibleText(CommonOR.ddLabel("Market"), market, "Market Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Market"), market,"Market Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Market"), "Plus Button of market")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Market"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		if(!territory.isEmpty()){
			if(isElementPresent(CommonOR.ddLabel("Territory"), "Territory Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Territory"),"Territory drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Territory"),"Territory Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Territory", territory)){
					if(!selectByVisibleText(CommonOR.ddLabel("Territory"), territory, "Territory Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Territory"), territory," Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Territory"), "Plus Button of Territory")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Territory"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}

		return flag;
	}

	/**
	 * This function is used to edit the information of an Exhibitor
	 * @param ExhibitName- reads Exhibit Name data from Test Data excel sheet
	 * @param CustomerExhibitID- reads Customer Exhibit ID data from Test Data excel sheet
	 * @param status- reads Status data from Test Data excel sheet
	 * @param MsgExhibitEmail- reads Msg Exhibit Name data from Test Data excel sheet
	 * @param Company- reads Company data from Test Data excel sheet
	 * @param Booth- reads Booth data from Test Data excel sheet
	 * @param URL- reads URL data from Test Data excel sheet
	 * @param FacebookPage- reads Facebook Page data from Test Data excel sheet
	 * @param Description- reads Description data from Test Data excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean editExhibitorInformation(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,String URL,String FacebookPage,String Description) throws Throwable
	{
		boolean flag=true;
		if(!ExhibitName.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Exhibit Name"),"Exhibit Name"))
				flag=false;
			if(!type(CommonOR.txtExhibitName, ExhibitName, "Exhibit Name"))
				flag=false;
			if(!click(CommonOR.btnSave("Exhibit Name"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!status.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Status"), "status Drop Down")){
				Thread.sleep(1000);
				if(!click(CommonOR.txtBoxData("Status"),"Status drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Status"),"Status Value"))
					flag=false;
				Thread.sleep(1000);
				if(verifyInDropDownList("Status", status)){
					if(!selectByVisibleText(CommonOR.ddLabel("Status"), status, "status Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Status"), status, "status Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Status"), "Plus Button of status")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Status"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!MsgExhibitEmail.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Msg Exhibit Email"),"Msg Exhibit Email"))
				flag=false;
			if(!type(CommonOR.txtBox("Msg Exhibit Email"), MsgExhibitEmail, "Msg Exhibit Email Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Msg Exhibit Email"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Company.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Company"),"Company"))
				flag=false;
			if(!type(CommonOR.txtBox("Company"), Company, "Company Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Company"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Booth.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Booth #"),"Booth #"))
				flag=false;
			if(!type(CommonOR.txtBox("Booth #"),Booth , "Booth # Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Booth #"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!URL.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("URL"),"URL"))
				flag=false;
			if(!type(CommonOR.txtBox("URL"), URL, "URL Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("URL"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!FacebookPage.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Facebook Page"),"Facebook Page"))
				flag=false;
			if(!type(CommonOR.txtBox("Facebook Page"), FacebookPage, "FaceBook Page Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Facebook Page"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Description.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Description"),"Description"))
				flag=false;
			if(!type(CommonOR.txtDescription, Description, "Description Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Description"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}

		return flag;
	}

	/**
	 * This function is used to edit the information of Contact Information section of an Exhibitor
	 * @param ContactFirst- reads Contact First data from Test Data excel sheet
	 * @param ContactCompany- reads Contact Company data from Test Data excel sheet
	 * @param ContactLast- reads Contact Last data from Test Data excel sheet
	 * @param Email- reads Contact Email data from Test Data excel sheet
	 * @param Phone- reads Phone data from Test Data excel sheet
	 * @param AltPhone- reads Alt Phone data from Test Data excel sheet
	 * @param ContactAddress1- reads Contact Address 1 data from Test Data excel sheet
	 * @param City- reads Contact City data from Test Data excel sheet
	 * @param ContactAddress2- reads Contact Address 2 data from Test Data excel sheet
	 * @param Region- reads Region data from Test Data excel sheet
	 * @param ZipCode- reads Zip Code data from Test Data excel sheet
	 * @param CountryCode- reads Country Code data from Test Data excel sheet
	 * @return boolean (true/false)
	 * @throws Throwable
	 */
	public static boolean EditExhibitorContactInformation(String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode) throws Throwable
	{
		boolean flag=true;
		if(!ContactFirst.isEmpty())
		{
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			if(!click(CommonOR.txtBoxData("Contact First"),"Contact First"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact First"), ContactFirst, "Contact First Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact First"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!ContactCompany.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Company"),"Contact Company"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Company"), ContactCompany, "Contact Company Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Company"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!ContactLast.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Last"),"Contact Last"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Last"), ContactLast, "Contact Last Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Last"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Email.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Email"),"Contact Email"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Email"), Email, "Contact Email Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Email"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Phone.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Phone"),"Contact Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Phone"), Phone, "Contact Phone Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!AltPhone.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Alt Phone"),"Contact Alt Phone"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Alt Phone"), AltPhone, "Contact Alt Phone Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Alt Phone"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!ContactAddress1.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Address 1"),"Contact Address 1"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Address 1"), ContactAddress1, "Contact Address 1 Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Address 1"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!City.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact City"),"Contact City"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact City"), City, "Contact City"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact City"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!ContactAddress2.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Address 2"),"Contact Address 2"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Address 2"), ContactAddress2, "Contact Address 2 Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Address 2"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!Region.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Region"),"Contact Region"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Region"), Region, "Contact Region Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Region"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!ZipCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Zip Code"),"Contact Zip Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Zip Code"), ZipCode, "Contact Zip Code Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Zip Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!CountryCode.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Contact Country Code"),"Contact Country Code"))
				flag=false;
			if(!type(CommonOR.txtBox("Contact Country Code"), CountryCode, "Contact Country Code Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Contact Country Code"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
	}

	public static boolean EditExhibitorLoginInformation(String LoginID) throws Throwable
	{
		boolean flag=true;
		if(!LoginID.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Login Id"),"Login Id"))
				flag=false;
			if(!type(CommonOR.txtBox("Login Id"), LoginID, "Login Id Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Login Id"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
	}

	/**
	 * This function is used to edit the information of Categories section  of an Exhibitor
	 * @param Category- reads Category from Test Data excel File
	 * @param FocusArea- reads Focus Area from Test Data excel File
	 * @param Industry- reads Industry from Test Data excel File
	 * @param IndustryMarket- reads Industry Market from Test Data excel File
	 * @param JobRole- reads Job Role from Test Data excel File
	 * @param SpecialityProduct- reads Speciality Product from Test Data excel File
	 * @param Education- reads Education from Test Data excel File
	 * @param Certifications- reads Certifications from Test Data excel File
	 * @param Division- reads Division from Test Data excel File
	 * @param SalesRegion- reads Sales Region from Test Data excel File
	 * @param MapHeight- reads Map Height from Test Data excel File
	 * @param MApRotation- reads Map Rotation from Test Data excel File
	 * @param MapWidth- reads Map Width from Test Data excel File
	 * @param MapX- reads Map X from Test Data excel File
	 * @param MapY- reads Map Y from Test Data excel File
	 * @return boolean(true/false)
	 * @throws Throwable
	 */
	public static boolean EditExhibitorCategories(String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY) throws Throwable
	{
		boolean flag=true;
		if(!Category.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Category"), "Category Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Category"),"Category drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Category"),"Category Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Category", Category)){
					if(!selectByVisibleText(CommonOR.ddLabel("Category"), Category, "Category Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Category"), Category, "Category Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Category"), "Plus Button of Category")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Category"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!FocusArea.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Focus Area"), "FocusArea Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Focus Area"),"Focus Area drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Focus Area"),"Focus Area Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Focus Area", FocusArea)){
					if(!selectByVisibleText(CommonOR.ddLabel("Focus Area"), FocusArea, "FocusArea Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Focus Area"), FocusArea, "FocusArea Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Focus Area"), "Plus Button of FocusArea")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Focus Area"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Industry.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry"), "Industry Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Industry"),"Industry drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Industry"),"Industry Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Industry", Industry)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry"), Industry, "Industry Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry"), Industry, "Industry Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry"), "Plus Button of Industry")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Industry"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!IndustryMarket.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Industry Market"), "IndustryMarket Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Industry Market"),"Industry Market drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Industry Market"),"Industry Market Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Industry Market", IndustryMarket)){
					if(!selectByVisibleText(CommonOR.ddLabel("Industry Market"), IndustryMarket, "IndustryMarket Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Industry Market"), IndustryMarket, "IndustryMarket Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Industry Market"), "Plus Button of IndustryMarket")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Industry Market"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!JobRole.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Job Role"), "JobRole Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Job Role"),"Job Role drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Job Role"),"Job Role Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Job Role", JobRole)){
					if(!selectByVisibleText(CommonOR.ddLabel("Job Role"), JobRole, "JobRole Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Job Role"), JobRole, "JobRole Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Job Role"), "Plus Button of JobRole")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Job Role"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!SpecialityProduct.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Specialty Product"), "SpecialityProduct Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Specialty Product"),"Specialty Product drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Specialty Product"),"Status Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Specialty Product", SpecialityProduct)){
					if(!selectByVisibleText(CommonOR.ddLabel("Specialty Product"), SpecialityProduct, "SpecialityProduct Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Specialty Product"), SpecialityProduct, "SpecialityProduct Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Specialty Product"), "Plus Button of SpecialityProduct")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Specialty Product"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Education.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Education"), "Education Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Education"),"Education drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Education"),"Education Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Education", Education)){
					if(!selectByVisibleText(CommonOR.ddLabel("Education"), Education, "Education Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Education"), Education, "Education Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Education"), "Plus Button of Education")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Education"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Certifications.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Certifications"), "Certifications Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Certifications"),"Certifications drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Certifications"),"Certifications Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Certifications", Certifications)){
					if(!selectByVisibleText(CommonOR.ddLabel("Certifications"), Certifications, "Certifications Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Certifications"), Certifications, "Certifications Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Certifications"), "Plus Button of Certifications")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Certifications"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!Division.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Division"), "Division Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Division"),"Division drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Division"),"Division Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Division", Division)){
					if(!selectByVisibleText(CommonOR.ddLabel("Division"), Division, "Division Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Division"), Division, "Division Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Division"), "Plus Button of Division")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Division"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!SalesRegion.isEmpty())
		{
			if(isElementPresent(CommonOR.ddLabel("Sales Region"), "SalesRegion Drop Down")){
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.txtBoxData("Sales Region"),"Sales Region drop down"))
					flag=false;
				waitForVisibilityOfElement(By.xpath("//div[@class='round-corner new-field-value-delete']"), "Drop Down Value");
				if(!click(CommonOR.ddRemoveValue("Sales Region"),"Sales Region Value"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(verifyInDropDownList("Sales Region", SalesRegion)){
					if(!selectByVisibleText(CommonOR.ddLabel("Sales Region"), SalesRegion, "SalesRegion Drop Down")){
						flag=false;
					}
				}
				else{
					if(!type(CommonOR.txtAddNew("Sales Region"), SalesRegion, "SalesRegion Add New Text Box")){
						flag=false;
					}
					if(!click(CommonOR.btnPlus("Sales Region"), "Plus Button of SalesRegion")){
						flag=false;
					}
				}
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
				if(!click(CommonOR.btnSave("Sales Region"),"Save button"))
					flag=false;
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
			}
		}
		if(!MapHeight.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Map Height"),"Map Height"))
				flag=false;
			if(!type(CommonOR.txtBox("Map Height"), MapHeight, "Map Height Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Map Height"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!MapWidth.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Map Width"),"Map Width"))
				flag=false;
			if(!type(CommonOR.txtBox("Map Width"), MapWidth, "Map Width text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Map Width"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!MapX.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Map X"),"Map X"))
				flag=false;
			if(!type(CommonOR.txtBox("Map X"), MapX, "Map X Text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Map X"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!MapY.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Map Y"),"Map Y"))
				flag=false;
			if(!type(CommonOR.txtBox("Map Y"), MapY, "Map Y text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Map Y"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		if(!MApRotation.isEmpty())
		{
			if(!click(CommonOR.txtBoxData("Map Rotation"),"Map Rotation"))
				flag=false;
			if(!type(CommonOR.txtBox("Map Rotation"), MApRotation, "Map Rotation text Box"))
				flag=false;
			if(!click(CommonOR.btnSave("Map Rotation"),"Save button"))
				flag=false;
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading..");
		}
		return flag;
	}

	public static boolean deleteASurveyQuestion() throws Throwable
	{
		boolean flag=true;
		if(!mouseover(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div[2]/h4[text()='Automation testing ']"), "Survey question"))
		{
			flag=false;
		}
		waitForElementPresent(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div[2]/div//div[text()='Delete']"));
		if(!click(By.xpath("//div[h3[text()='Page 1']]/following-sibling::div[2]/div//div[text()='Delete']"), "Delete button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(SurveyOR.btnPopUpOK, "Pop Up");
		if(!click(SurveyOR.btnPopUpOK,"OK Button"))
		{
			flag=false;
		}
		waitForInVisibilityOfElement(By.xpath("//div[@id='survey']/div[contains(@class,'ajax')]"), "Loading");
		if(!click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean navigateToSurveyXML() throws Throwable
	{
		boolean flag=true;
		if(!click(XML_OR.lnkSurvey,"Survey Link"))
		{
			flag=false;
		}
		if(!isElementPresent(XML_OR.txtSurveyXMLPageTitle, "Manage survey Configuration"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean insertSurveyLookUpTypesTag(String type){

		boolean flag = true;
		String Key=type;
		switch (Key) {
		case "attendance":
			new Actions(driverM).sendKeys("  <default-session-survey-type>attendance</default-session-survey-type>").perform();
			flag=true;
			break;

		case "enrollment":
			new Actions(driverM).sendKeys("  <default-session-survey-type>enrollment</default-session-survey-type>").perform();
			flag=true;
			break;

		default: 
			flag=false;
			break;
		}

		return flag;
	}

	//value can be either true or false
	public static boolean insertShowLogoutButtonNonAdmin(String value)
	{
		boolean flag=true;
		String Key=value;
		switch (Key) {
		case "true":
			new Actions(driverM).sendKeys("  <show-logout-button-non-admin>true</show-logout-button-non-admin>").perform();
			flag=true;
			break;

		case "false":
			new Actions(driverM).sendKeys("  <show-logout-button-non-admin>false</show-logout-button-non-admin>").perform();
			flag=true;
			break;

		default: 
			flag=false;
			break;
		}
		return flag;
	}

	//value can be either true or false
	public static boolean insertShowAddRegistrantAdmin(String value)
	{
		boolean flag=true;
		String Key=value;
		switch (Key) {
		case "true":
			new Actions(driverM).sendKeys("  <show-add-registrant-admin>true</show-add-registrant-admin>").perform();
			flag=true;
			break;

		case "false":
			new Actions(driverM).sendKeys("  <show-add-registrant-admin>false</show-add-registrant-admin>").perform();
			flag=true;
			break;

		default: 
			flag=false;
			break;
		}
		return flag;
	}

	public static boolean checkSurveyLookUpType(String SessionID,String SessionTitle,String survey_Name,String type) throws Throwable
	{
		boolean flag=true;
		if (type.equalsIgnoreCase("attendance")) {
			if (isElementPresent(SurveyOR.m_chckboxDidNotAttend(SessionID,SessionTitle),"Did Not Attend Checkbox")) {
				Reporters.SuccessReport("Check Look Up type for survey"+survey_Name, survey_Name+" has valid type that is "+type);
			} 
			else {
				Reporters.failureReport("Check Look Up type for survey"+survey_Name, survey_Name+" has invalid type that is "+type);
			}
		}
		if(type.equalsIgnoreCase("enrollment"))
		{
			if (waitForVisibilityOfElement(SurveyOR.m_chckboxDidNotAttend(SessionID,SessionTitle),"Did Not Attend Checkbox")) {
				Reporters.failureReport("Check Look Up type for survey"+survey_Name, survey_Name+" has invalid type that is "+type);
			} 
			else {
				Reporters.SuccessReport("Check Look Up type for survey"+survey_Name, survey_Name+" has valid type that is "+type);
			}
		}
		return flag;
	}

	public static boolean clickDidNotAttend(String SessionID,String sessiontitle) throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.m_chckboxDidNotAttend(SessionID,sessiontitle),"Did not attend check box");
		if(!click(SurveyOR.m_chckboxDidNotAttend(SessionID,sessiontitle),"Did not attend check box"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean searchForDidNotAttendOrAlreadyTakenSession(String sessiontitle) throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(SurveyOR.txtSearchBox, "Session Title");
		if(!type(SurveyOR.txtSearchBox,sessiontitle , "Session Title"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnSearch,"Search Button"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(By.xpath("//div[@class='survey-error']"), "No Session Surveys found."))
		{
			flag=false;
		}
		if(!verifyTextPresent("No Session Surveys found."))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySessionInManageSessionAttendanceList(String sessionTitle) throws Throwable
	{
		waitForVisibilityOfElement(SurveyOR.txtInManageSessionsTable("Manage Session Attendance"),"Existence of Registrant");
		return isElementPresent(SurveyOR.txtInManageSessionsTable("Manage Session Attendance"), "Element not present");
	}

	public static String getSurveyID()
	{
		String URL=driverM.getCurrentUrl();
		System.out.println(URL);
		String[] parts = URL.split("&");
		String part1 = parts[0];
		System.out.println(part1);
		String part2 = parts[1];
		System.out.println(part2);
		String[] id = part2.split("=");
		System.out.println(id[1]);
		return id[1];
	}

	public static String getExhibitorID()
	{
		String URL=driverM.getCurrentUrl();
		System.out.println(URL);
		String[] parts = URL.split("detailid");
		String part1 = parts[0];
		System.out.println(part1);
		String part2 = parts[1];
		System.out.println(part2);
		String[] id = part2.split("=");
		System.out.println(id[1]);
		return id[1];
	}

	public static boolean insertConferenceSurveyID(String type,String id){

		boolean flag = true;
		String Key=type;
		switch (Key) {
		case "Conference":
			new Actions(driverM).sendKeys(""+id+"").perform();
			flag=true;
			break;

		default: 
			flag=false;
			break;
		}

		return flag;
	}

	public static boolean navigateToBadgePrinter() throws Throwable
	{
		boolean flag=true;
		if(!click(BadgePrintOR.lnkBadgePrintAdmin,"Badge Print Admin link"))
		{
			flag=false;
		}
		if(!isElementPresent(BadgePrintOR.txtBadgePrinter, "Badge Printer Page"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean enablePDFPrint() throws Throwable
	{
		boolean flag=true;
		if(!waitForElementPresent(BadgePrintOR.chckboxEnablePDFPrint))
		{
			flag=false;
		}
		if(isChecked(BadgePrintOR.chckboxEnablePDFPrint, "Enable PDF Print"))
		{
			Reporters.SuccessReport("Enable PDF Print check box", "Enable PDF Print checkbox has already been enabled");
		}
		else
		{
			if (!click(BadgePrintOR.chckboxEnablePDFPrint,"Enable PDF Print check box")) {
				flag=false;
			}
		}

		return flag;
	}

	public static boolean disablePDFPrint() throws Throwable
	{
		boolean flag=true;
		if(!waitForElementPresent(BadgePrintOR.chckboxEnablePDFPrint))
		{
			flag=false;
		}
		if(!isChecked(BadgePrintOR.chckboxEnablePDFPrint, "Enable PDF Print"))
		{
			Reporters.SuccessReport("Disable PDF Print check box", "Enable PDF Print checkbox has already been disabled");
		}
		else
		{
			if (!click(BadgePrintOR.chckboxEnablePDFPrint,"Disable PDF Print check box")) {
				flag=false;
			}
		}

		return flag;
	}

	public static boolean navigateToBadgePrintingForRegistrants() throws Throwable
	{
		boolean flag=true;
		if(!js_click(BadgePrintOR.lnkTools,"Tools link"))
		{
			flag=false;
		}
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		if(!isElementPresent(BadgePrintOR.txtPrintBadges, "Print Badges Section"))
		{
			flag=false;
		}
		if(!click(BadgePrintOR.lnkCurrentPage,"Current Page Items"))
		{
			flag=false;
		}

		return flag;
	}

	public static boolean waitForDownloadToComplete() throws InterruptedException
	{
		boolean flag=true;
		try {
			File file=new File("Downloads");
			System.out.println(file.getAbsolutePath());
			File[] listofFiles=file.listFiles();
			Thread.sleep(1000);
			while(!(listofFiles.length==1)){
				Thread.sleep(2000);
				listofFiles = file.listFiles();
			}

		} catch (Exception e) {

			flag=false;
		}
		return flag;
	}

	public static boolean printPDFBadgesInBulk() throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		if(!selectByVisibleText(BadgePrintOR.ddPrinter, "Badge Print PDF", "Badge Print PDF"))
		{
			flag=false;
		}
		Thread.sleep(1000);
		if(!click(BadgePrintOR.chckboxAllAttendees,"All Attendees Check Box"))
		{
			flag=false;
		}
		if(!click(BadgePrintOR.btnNext,"Next Button"))
		{
			flag=false;
		}
		waitForVisibilityOfElement(BadgePrintOR.btnConfirm, "Confirm Button");
		if(!click(BadgePrintOR.btnConfirm,"Confirm Button"))
		{
			flag=false;
		}
		if(!waitForDownloadToComplete())
		{
			flag=false;
		}
		return flag;
	}

	public static boolean printPDFBadgeForOneRegistrant() throws Throwable
	{
		boolean flag=true;
		if(!click(BadgePrintOR.lnkBadgePrintPDFForOneRegistrant,"Badge Print PDF Link"))
		{
			flag=false;
		}
		if(!waitForDownloadToComplete())
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyBadgePDFPrintForSingleRegistrant() throws Throwable
	{
		boolean flag=true;
		if(!js_click(BadgePrintOR.lnkTools,"Tools link"))
		{
			flag=false;
		}
		if(!isElementPresent(BadgePrintOR.lnkBadgePrintPDFForOneRegistrant, "Badge Print PDF link for one registrant"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyFileType(String filetype) throws Throwable
	{
		boolean flag=true;
		File file=new File("Downloads");
		System.out.println(file.getAbsolutePath());
		try {
			File[] listofFiles=file.listFiles();
			if (listofFiles.length>0) {
				for (File checkFile : listofFiles) {
					if(checkFile.getName().endsWith(filetype))
					{
						Reporters.SuccessReport("Verify file "+checkFile.getName()+" with type "+filetype, "File "+checkFile.getName()+" is found with type "+filetype);
						Thread.sleep(1000);
						checkFile.delete();
						Thread.sleep(1000);
					}
					else
					{
						Reporters.failureReport("Verify file "+checkFile.getName()+" with type "+filetype, "File "+checkFile.getName()+" is found instead of file with type "+filetype);
						Thread.sleep(1000);
						checkFile.delete();
						Thread.sleep(1000);
					}
				}
			}
			else
			{
				Reporters.failureReport("Verify file with type "+filetype, "File with type "+filetype+" has failed to download");
			}
		} catch (Exception e) {

			return false;
		}
		return flag;
	}

	public static boolean lockAccount(String loginId,String Password,String Message) throws Throwable
	{
		boolean flag=true;
		waitForVisibilityOfElement(EMT_LogInOR.txtusername, "Email ID");

		if (!type(EMT_LogInOR.txtusername, loginId, "Login ID")) {
			flag = false;
		}

		if(!type(EMT_LogInOR.txtPassword, Password, "Password"))
		{
			flag=false;
		}

		if(!click(EMT_LogInOR.btnLogin,"Login Button"))
		{
			flag=false;
		}
		if (!Message.isEmpty()) {
			waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.txtErrorMessage, "Error Message");
			String message = getText(EMT_LockAndUnlockUserAccount.txtErrorMessage,
					"Error Message");
			System.out.println(message);
			if (message.equalsIgnoreCase(Message)) {
				Reporters.SuccessReport("Validate Error Message",
						"Valid error message got displayed");
			} else {
				Reporters.failureReport("Validate Error Message",
						"Invalid error message displayed");
			}
		}
		return flag;
	}

	public static boolean verifyUserLoginInfoAfterAccountLocked() throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		if (!waitForVisibilityOfElement(
				EMT_LockAndUnlockUserAccount.true_Account_Locked,
				"Account Locked field value")) {
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.txtFailedLoginCount, "Failed Login Count value"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.txtLockTime, "Lock Time Value"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean unlockTheUserAccount() throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		if (waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.true_Account_Locked,"Account Locked field value")) {
			if (isChecked(EMT_LockAndUnlockUserAccount.chckAccountLocked, "Account Locked check box")) {
				js_click(EMT_LockAndUnlockUserAccount.txtAccountLocked,"");
				if (!click(EMT_LockAndUnlockUserAccount.chckAccountLocked,"Account Locked check box")) {
					flag = false;
				}
				if(!js_click(EMT_LockAndUnlockUserAccount.btnSave,"Save Button"))
				{
					flag=false;
				}
			}

			if(!waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.false_Account_Locked, "Unlock account"))
			{
				flag=false;
			}

			refresh();

			String i=getText(EMT_LockAndUnlockUserAccount.txtFailedLoginCount, "Failed Login Count value");

			int k=Integer.parseInt(i);

			if(k!=0)
			{
				flag=false;
			}
			if(!waitForVisibilityOfElement(EMT_LockAndUnlockUserAccount.txtLockTime, "Lock Time Value"))
			{
				flag=false;
			}

		}

		return flag;
	}

	public static boolean verifyUserInHomePage(String FirstName,String LastName) throws Throwable
	{
		boolean flag=true;
		String text=getText(EMT_LockAndUnlockUserAccount.txtUserName, "User");
		System.out.println(text);
		String user=FirstName+" "+LastName;
		System.out.println(user);
		if(!user.equalsIgnoreCase(text))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean deleteTheRecord() throws Throwable
	{
		boolean flag=true;
		if(!js_click(CommonOR.lnkDeleteThisRecord,"Delete This Speaker link"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm Button"))
		{
			flag=false;    
		}
		Thread.sleep(2000);
		if(!js_click(EMT_RegistrantsOR.btnConfirm,"Confirm button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean setLeadCapturesViewBy() throws Throwable
	{
		boolean flag=true;
		if(!isElementPresent(AttendeeJourneyOR.sectionLeadCaptures, "Lead Captures View By"))
		{
			if(!js_click(AttendeeJourneyOR.ddSelectAViewBy,"Select a View By drop down"))
			{
				flag=false;
			}

			if(!click(AttendeeJourneyOR.ddLeadCaptures, "Lead Captures"))
			{
				flag=false;
			}

			if(!waitForElementPresent(AttendeeJourneyOR.txtSuccessMessage))
			{
				flag=false;
			}
		}
		else
		{
			Reporters.SuccessReport("Enable Lead Captures View By", "Lead Captures View By has already enabled");
		}
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		if(!isElementPresent(AttendeeJourneyOR.sectionLeadCaptures, "Lead Captures View By Section"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyLeadsCSVData(ArrayList<String>[] inputData,String columnName) throws Throwable{
		int j=0,i=0,region=0;
		boolean flag=true;
		System.out.println(inputData[0].size());
		System.out.println(inputData.length);
		for(j=0;j<(inputData[0].size())-1;j++) {
			region=0;
			if(search(inputData[getcsvnumber(inputData, columnName)].get(j+1)))
			{
				Reporters.SuccessReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(j+1) +"searched successfully");
			}
			else
			{
				Reporters.failureReport("Search ", inputData[getcsvnumber(inputData, columnName)].get(j+1) +"searched successfully");
			}
			if (!columnName.equalsIgnoreCase("Exhibitor Id")) {
				if (!click(By.xpath("//td[text()='"
						+ inputData[getcsvnumber(inputData, columnName)]
								.get(j + 1) + "']"),
								inputData[getcsvnumber(inputData, columnName)]
										.get(j + 1))) {
					return false;
				}
			}
			else
			{
				if (!js_click(By.xpath("//table[contains(@id,'search-table')]/tbody/tr[1]"),"Exhibitor")) {
					return false;
				}
			}
			Thread.sleep(3000);
			if (!isElementPresent(AttendeeJourneyOR.txtLead, "Leads Page")) {
				if (j <= (inputData[0].size() - 1)) {
					System.out.println(inputData[i].get(0));
					System.out.println(inputData[i].get(j + 1));
					if (isElementPresent(
							AttendeeJourneyOR.txtLeadCaptures,
							"Lead Captures")) {
						if (isElementPresent(AttendeeJourneyOR.txtExhibitor,
								"Exhibitors")) {
							String id = getText(
									AttendeeJourneyOR.txtAttendeeID,
									"attendee id");
							if (id.equalsIgnoreCase(inputData[i].get(j + 1))) {
								flag = true;
							}
						}
						if (!isElementPresent(
								By.xpath("//div[h2[text()='Lead Captures']]/following-sibling::div//div/table//tbody/tr"),
								"Lead Capture")) {
							flag = false;
						}
					}
				}
			}
			else
			{
				for(i=0;i<=(inputData.length)-1;i++)
				{
					if(j<=(inputData[0].size()-1)) {
						System.out.println(inputData[i].get(0));
						System.out.println(inputData[i].get(j+1));
						if (!((inputData[i].get(0)).equalsIgnoreCase("Registrant Number"))) {
							verifyText(
									By.xpath("//div[text()='"
											+ inputData[i].get(0).trim()
											+ "']/parent::td/following-sibling::td[1]/div"),
											inputData[i].get(j + 1), inputData[i]
													.get(0));
						}
						else
						{
							verifyText(By.xpath("//h3[text()='Lead Information']/ancestor::table//td[div[text()='Registrant Number Link']]/following-sibling::td[1]/div"),inputData[i].get(j+1),inputData[i].get(0));
						}
					}
				}
				Thread.sleep(3000);
			}
		}
		return flag;
	}

	public static boolean searchForAttendeeInAttendeeJourney(String attendeeid) throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(AttendeeJourneyOR.loading, "Loading");
		if(!type(AttendeeJourneyOR.txtSearch,attendeeid,"attendee id: "+attendeeid))
		{
			flag=false;
		}
		if(!click(AttendeeJourneyOR.btnSearch,"Search button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyAttendeeJourneyElements(String first,String last,String attendeeid) throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(AttendeeJourneyOR.loading, "Loading");
		if(!isElementPresent(AttendeeJourneyOR.btnEmailAttendee , "Email Attendee"))
		{
			flag=false;
		}
		if(!isElementPresent(AttendeeJourneyOR.btnSHowMoreDetail, "Show more details"))
		{
			flag=false;
		}
		if(!isElementPresent(AttendeeJourneyOR.sectionSessionAttendance, "Session Attendance"))
		{
			flag=false;
		}
		if(!isElementPresent(AttendeeJourneyOR.sectionLeadScans, "Lead scans"))
		{
			flag=false;
		}
		String attendee_name=first+" "+last;
		System.out.println(attendee_name);
		String attendeename=getText(AttendeeJourneyOR.txtAttendeeName, "Attendee Name");
		System.out.println(attendeename);
		if(attendeename.equalsIgnoreCase(attendee_name))
		{
			Reporters.SuccessReport("Verify attendee name after search", "Succesfully displayed valid Attendee profile");
		}
		else
		{
			Reporters.failureReport("Verify attendee name after search", "Failed to display valid attendee profile");
		}
		if(!isElementPresent(AttendeeJourneyOR.imgAttendee(attendeeid), "Attendee Image"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifySessionAttendanceInAttendeeJourney(String SessionTitle) throws Throwable
	{
		boolean flag=true;
		waitForInVisibilityOfElement(AttendeeJourneyOR.loading, "Loading");
		waitForVisibilityOfElement(AttendeeJourneyOR.txtSessionTitle(SessionTitle), "Session title");
		if(!isElementPresent(AttendeeJourneyOR.txtSessionTitle(SessionTitle), "Session title"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyLeadScansInAttendeeJourney(String exhibitorName) throws Throwable
	{
		boolean flag=true;
		String leadScanName=getText(AttendeeJourneyOR.txtLeadScans, "Lead Scan Names");
		System.out.println(leadScanName);
		if(!(leadScanName.equalsIgnoreCase(exhibitorName)))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyAllMandatoryfields() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button");
		if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
		{
			flag=false;
		}
		List<WebElement> elements=driverM.findElements(By.xpath("//p[@class='error']/following-sibling::ul/li"));
		for (int i = 1; i <= elements.size(); i++) {
			if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		if(flag)
		{
			Reporters.SuccessReport("Verify All Mandatory fields in Add Event Page", "Successfully displayed correct error message");
		}
		else
		{
			Reporters.failureReport("Verify All Mandatory fields in Add Event Page", "Failed to display correct error message");
		}
		if(type(IL_CreateAndDeleteAnEventOR.txtFirstName,eReader.getColData("First Name", 1),eReader.getColData("First Name", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify First Name Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify First Name Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtLastName,eReader.getColData("Last Name", 1),eReader.getColData("Last Name", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Last name Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Last name Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtCompany,eReader.getColData("Company", 1),eReader.getColData("Company", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("COMPANY is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Company Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Company Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtEmailAddress,eReader.getColData("Email Address", 1),eReader.getColData("Email Address", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("COMPANY is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EMAIL is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Email Address Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Email Address Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtEventName,eReader.getColData("Event Name", 1),eReader.getColData("Event Name", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("COMPANY is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EMAIL is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EVENTNAME is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Event name Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Event name Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtEventTag,eReader.getColData("Event Tag", 1),eReader.getColData("Event Tag", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("COMPANY is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EMAIL is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EVENTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EVENTTAG is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Event tag Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Event tag Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtStartDate,eReader.getColData("Start Date (MM/dd/yyyy)", 1),eReader.getColData("Start Date (MM/dd/yyyy)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			for (int i = 1; i <= elements.size(); i++) {
				if(!(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("FIRSTNAME is required.")||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("LASTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("COMPANY is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EMAIL is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EVENTNAME is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("EVENTTAG is required."))||(eReader.getColData("Expected Error Message", i).equalsIgnoreCase("STARTDATE is required."))))
				{
					if(verifyTextInList(eReader.getColData("Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Start date Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Start date Field", "Failed to display correct error message");
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtEndDate,eReader.getColData("End Date (MM/dd/yyyy)", 1),eReader.getColData("End Date (MM/dd/yyyy)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnAddEvent, "Add Button"))
			{
				flag=false;
			}
			if(!waitForInVisibilityOfElement(By.xpath("//p[@class='error']"), "Error Message"))
			{
				flag=false;
			}
			if(flag)
			{
				Reporters.SuccessReport("Verify Start date Field", "Successfully displayed correct error message");
			}
			else
			{
				Reporters.failureReport("Verify Start date Field", "Failed to display correct error message");
			}
			if(waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtEventAddedMsg, ""))
			{
				Reporters.SuccessReport("Verify Event Added", "Event Added successfully");
			}
			else
			{
				Reporters.failureReport("Verify Event Added", "Failed to add event");
			}
		}
		//event_title=eReader.getColData("Event Name", 1)+"-"+eReader.getColData("Event Tag", 1);
		event_title();
		return flag;
	}

	public static boolean edit_Event_Page() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_CreateAndDeleteAnEventOR.tabEdit,"Edit Tab"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtEditPageTitle, "Edit Page"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean verifyEventStartDateFormats() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		for (int i = 2; i < 20; i++) {
			try {
				if (!(eReader.getColData("Start Date (MM/dd/yyyy)", i).equals(""))) {
					if (!type(IL_CreateAndDeleteAnEventOR.txtStartDate,
							eReader.getColData("Start Date (MM/dd/yyyy)", i),
							eReader.getColData("Start Date (MM/dd/yyyy)", i))) {
						flag = false;
					}
					if (!click(IL_CreateAndDeleteAnEventOR.btnUpdate,
							"Update Button")) {
						flag = false;
					}
					if (!waitForVisibilityOfElement(
							IL_CreateAndDeleteAnEventOR.txtError, "Error Message")) {
						flag = false;
					}
					if (!isElementPresent(
							IL_CreateAndDeleteAnEventOR.txtStartDateErrorMessage(eReader
									.getColData("Start Date (MM/dd/yyyy)", i)),
							"Error Message")) {
						flag = false;
					}
				}
				else
				{
					flag=false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		return flag;
	}

	public static boolean verifyEventEndDateFormats() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		for (int i = 2; i < 20; i++) {
			try {
				if (!(eReader.getColData("End Date (MM/dd/yyyy)", i).equals(""))) {
					if (!type(IL_CreateAndDeleteAnEventOR.txtEndDate,
							eReader.getColData("End Date (MM/dd/yyyy)", i),
							eReader.getColData("End Date (MM/dd/yyyy)", i))) {
						flag = false;
					}
					if (!click(IL_CreateAndDeleteAnEventOR.btnUpdate,
							"Update Button")) {
						flag = false;
					}
					if (!waitForVisibilityOfElement(
							IL_CreateAndDeleteAnEventOR.txtError, "Error Message")) {
						flag = false;
					}
					if (!isElementPresent(
							IL_CreateAndDeleteAnEventOR.txtEndDateErrorMessage(eReader
									.getColData("End Date (MM/dd/yyyy)", i)),
							"Error Message")) {
						flag = false;
					}
				}
				else
				{
					flag=false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		return flag;
	}

	public static boolean verifyEventExpirationdateFormats() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		for (int i = 2; i < 20; i++) {
			try {
				if (!(eReader.getColData("Expiration Date (MM/dd/yyyy)", i).equals(""))) {
					if (!type(IL_CreateAndDeleteAnEventOR.txtExpirationDate,
							eReader.getColData("Expiration Date (MM/dd/yyyy)", i),
							eReader.getColData("Expiration Date (MM/dd/yyyy)", i))) {
						flag = false;
					}
					if (!click(IL_CreateAndDeleteAnEventOR.btnUpdate,
							"Update Button")) {
						flag = false;
					}
					if (!waitForVisibilityOfElement(
							IL_CreateAndDeleteAnEventOR.txtError, "Error Message")) {
						flag = false;
					}
					if (!isElementPresent(
							IL_CreateAndDeleteAnEventOR.txtExpirationDateErrorMessage(eReader
									.getColData("Expiration Date (MM/dd/yyyy)", i)),
							"Error Message")) {
						flag = false;
					}
				}
				else
				{
					flag=false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		return flag;
	}

	public static boolean verifyEmailAddress() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		for (int i = 2; i < 20; i++) {
			try {
				if (!(eReader.getColData("Email Address", i).equals(""))) {
					if (!type(IL_CreateAndDeleteAnEventOR.txtEmailAddress, eReader.getColData("Email Address", i), eReader.getColData("Email Address", i))) {
						flag = false;
					}
					if (!click(IL_CreateAndDeleteAnEventOR.btnUpdate,
							"Update Button")) {
						flag = false;
					}
					if (!waitForVisibilityOfElement(
							IL_CreateAndDeleteAnEventOR.txtError, "Error Message")) {
						flag = false;
					}
					if (!isElementPresent(IL_CreateAndDeleteAnEventOR.txtEmailErrorMessage, "Email Error Message")) {
						flag = false;
					}
				}
				else
				{
					flag=false;
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		return flag;
	}

	public static boolean verifyCRMIntegrationSettings() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		if(!selectByIndex(IL_CreateAndDeleteAnEventOR.ddIntegrationType,1, "Integration Type"))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
		{
			flag=false;
		}
		List<WebElement> elements=driverM.findElements(By.xpath("//p[@class='error']/following-sibling::ul/li"));

		if(type(IL_CreateAndDeleteAnEventOR.txtURL, eReader.getColData("URL", 1),eReader.getColData("URL", 1) ))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
			{
				flag=false;
			}
			for (int i = 1; i < elements.size(); i++) {
				if (!(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTURL (URL) is required."))) {
					if (verifyTextInList(eReader.getColData(
							"CRM Expected Error Message", i))) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		}

		if(type(IL_CreateAndDeleteAnEventOR.txtCRMUsername, eReader.getColData("Username (Basic Auth)", 1), eReader.getColData("Username (Basic Auth)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
			{
				flag=false;
			}
			for (int i = 1; i < elements.size(); i++) {
				if(!((eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTURL (URL) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTUSERNAME (Username) is required."))))
				{
					if(verifyTextInList(eReader.getColData("CRM Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
		}
		if(type(IL_CreateAndDeleteAnEventOR.txtCRMPassword, eReader.getColData("Password (Basic Auth)", 1),eReader.getColData("Password (Basic Auth)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
			{
				flag=false;
			}
			for (int i = 1; i < elements.size(); i++) {
				if(!((eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTURL (URL) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTUSERNAME (Username) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTPASSWORD (Password) is required."))))
				{
					if(verifyTextInList(eReader.getColData("CRM Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
		}
		if(type(IL_CreateAndDeleteAnEventOR.txtCRMStartTime,eReader.getColData("Start Time (MM/dd/yyyy hh:mm a)", 1),eReader.getColData("Start Time (MM/dd/yyyy hh:mm a)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
			{
				flag=false;
			}
			for (int i = 1; i < elements.size(); i++) {
				if(!((eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTURL (URL) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTUSERNAME (Username) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTPASSWORD (Password) is required."))||(eReader.getColData("CRM Expected Error Message", i).equalsIgnoreCase("CRMINTSTARTTIME (Start Time) is required."))))
				{
					if(verifyTextInList(eReader.getColData("CRM Expected Error Message", i)))
					{
						flag=true;
					}
					else
					{
						flag=false;
					}
				}
			}
		}
		if(type(IL_CreateAndDeleteAnEventOR.txtCRMStopTime,eReader.getColData("Stop Time (MM/dd/yyyy hh:mm a)", 1),eReader.getColData("Stop Time (MM/dd/yyyy hh:mm a)", 1)))
		{
			if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
			{
				flag=false;
			}
			if(!waitForInVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtError, "Error Message"))
			{
				flag=false;
			}
			if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtEventUpdatedSuccessMessage, "Event Updated"))
			{
				flag=false;
			}
		}

		return flag;
	}

	public static boolean verifyEventSpecificSettings() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "iLeads_MandatoryFilelds", 36);
		if(!click(IL_CreateAndDeleteAnEventOR.ddEventClear,"Event to edit dates"))
		{
			flag=false;
		}
		selectBySendkeys(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"), event_title, "EVENTS");
		if(!waitForElementPresent(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]")))
		{
			flag=false;
		}
		if(!click(By.xpath("//fieldset/select/option[contains(.,'"+event_title+"')]"),"Event"))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtDefaultDemographicAttribute, eReader.getColData("Default Demographic Attribute", 1),eReader.getColData("", 1)))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtDurationFilter, eReader.getColData("Duration Filter", 1), eReader.getColData("Duration Filter", 1)))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtTimezoneOffset, eReader.getColData("Timezone Offset", 1), eReader.getColData("Timezone Offset", 1)))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtVisitorDisplayStatus, eReader.getColData("Visitor Display Status", 1),eReader.getColData("Visitor Display Status", 1)))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtVisitorDisplayAddress, eReader.getColData("Visitor Display Address", 1), eReader.getColData("Visitor Display Address", 1)))
		{
			flag=false;
		}
		if(!type(IL_CreateAndDeleteAnEventOR.txtFirstVisitMode, eReader.getColData("First Visit Mode", 1), eReader.getColData("First Visit Mode", 1)))
		{
			flag=false;
		}
		if(!click(IL_CreateAndDeleteAnEventOR.btnUpdate,"Update Button"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtError, "Error Message"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtDefaultDemographicAttributeErrorMessage(eReader.getColData("Default Demographic Attribute", 1)), eReader.getColData("Default Demographic Attribute", 1)))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtDurationFilterErrorMessage(eReader.getColData("Duration Filter", 1)), eReader.getColData("Duration Filter", 1)))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtTimezoneOffsetErrorMessage(eReader.getColData("Timezone Offset", 1)), eReader.getColData("Timezone Offset", 1)))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtVisitorDisplayStatusErrorMessage, eReader.getColData("Visitor Display Status", 1)))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtVisitorDisplayAddressErrorMessage, eReader.getColData("Visitor Display Address", 1)))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.txtFirstVisitModeErrorMessage, eReader.getColData("First Visit Mode", 1)))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean Import_Exhibitor_Page() throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(IL_ImportExhibitorFile.tabImports, "Imports"))
		{
			flag=false;
		}
		if(!click(IL_ImportExhibitorFile.tabImports,"Imports"))
		{
			flag=false;
		}
		if(!click(IL_ImportExhibitorFile.btnExhibitors,"Exhibitors"))
		{
			flag=false;
		}
		if(!waitForVisibilityOfElement(IL_ImportExhibitorFile.txtImportExhibitor, "Import Exhibitor"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean select_exhibitor_file(String file) throws Throwable
	{
		boolean flag=true;
		if(!waitForVisibilityOfElement(IL_ImportExhibitorFile.chckExhibitorFile(file), "Exhibitor File: "+file))
		{

		}
		if(!click(IL_ImportExhibitorFile.chckExhibitorFile(file),"Exhibitor File: "+file))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean mapFieldsOfExhibitorIniLeads() throws Throwable
	{
		boolean flag=true;
		listItems=new ArrayList<>();
		waitForVisibilityOfElement(IL_ImportExhibitorFile.txtMapFieldsPage, "Map Fields Form");
		Select inputFileFormat=new Select(driverM.findElement(By.xpath("//select[@id='list1']")));
		Thread.sleep(1000);
		Select databaseFileFormat=new Select(driverM.findElement(By.xpath("//select[@id='list3']")));
		Thread.sleep(1000);
		List<WebElement> input=inputFileFormat.getOptions();
		List<WebElement> database=databaseFileFormat.getOptions();
		for (WebElement text : database) {
			System.out.println(text.getText());
			listItems.add(text.getText());
		}
		for (WebElement input_txt : input) {
			System.out.println(input_txt.getText());
			String inputText=input_txt.getText();
			String[] inputtxt=inputText.split("-");
			System.out.println(inputtxt[0]);
			if(inputtxt[0].equalsIgnoreCase("Exhibit Id"))
			{
				if (!click(By.xpath("//select[@id='list3']/option[starts-with(text(),'eventID')]"),
						"eventID")) {
					flag=false;
				}
			}

			else if(inputtxt[0].equalsIgnoreCase("Exhibit Name"))
			{
				if(!click(By.xpath("//select[@id='list3']/option[starts-with(text(),'eventName')]"),
						"eventName")){
					flag=false;
				}
			}
			else if(inputtxt[0].equalsIgnoreCase("Device Number"))
			{
				if(!click(By.xpath("//select[@id='list3']/option[starts-with(text(),'deviceID')]"),
						"deviceID"))
				{
					flag=false;
				}
			}
			else if(inputtxt[0].equalsIgnoreCase("Error"))
			{
				if(!click(By.xpath("//select[@id='list3']/option[starts-with(text(),'(SKIP)')]"), "(SKIP)"))
				{
					flag=false;
				}
			}
			else
			{
				if(!click(By.xpath("//select[@id='list3']/option[starts-with(text(),'"+inputtxt[0]+"')]"),inputtxt[0]))
				{
					flag=false;
				}
			}
		}

		return flag;
	}

	public static boolean completeImportProcess() throws Throwable
	{
		boolean flag=true;
		if(!click(IL_ImportExhibitorFile.btnImport, "Import button"))
		{
			flag=false;
		}
		if(!click(IL_ImportExhibitorFile.btnDone,"Done Button"))
		{
			flag=false;
		}
		return flag;
	}

	public static boolean validateErrorMessagesAfterImportProcess() throws Throwable
	{
		boolean flag=true;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "IL_ImportExhibitor", 37);
		int i=1;
		if(!waitForVisibilityOfElement(IL_ImportExhibitorFile.txtResults, "Results"))
		{
			flag=false;
		}
		List<WebElement> validationErrors=driverM.findElements(By.xpath("//span[@id='results']/br/following-sibling::a"));
		System.out.println(eReader.getLastRow());
		for (int j = 1; j < eReader.getLastRow(); j++) {
			while (i < ((validationErrors.size())-2)) {
				//mouseover(By.xpath("//span[@id='results']/br/following-sibling::a["+ i + "]"), "mouse over on " + i+ " validation error link");
				String error1 = getAttribute(
						By.xpath("//span[@id='results']/br/following-sibling::a["
								+ i + "]"), "onmouseover", "Error Message");
				if ((error1
						.contains("stm('Detailed Error Message:', '<p><ul><li>"))) {
					error1 = error1.replace(
							"stm('Detailed Error Message:', '<p><ul><li>", "");
					if (error1.contains("</li></ul></p>', Style[12]);")) {
						error1 = error1.replace("</li></ul></p>', Style[12]);",
								"");
					}
					if (error1.contains("</li><li>")) {
						error1 = error1.replace("</li><li>", "");
					}
					System.out.println("final: " + error1);
					System.out.println("Script: "+eReader.getColData("Error", j));
					String error=eReader.getColData("Error", j);
					if(error1.contains(error))
					{
						Reporters.SuccessReport("Validate Actual Error Message "+error1+" with expected message "+error, "Actual Error Message "+error1+" matches with expected message "+error);
						i++;
						break;
					}
					else
					{
						Reporters.failureReport("Validate Actual Error Message "+error1+" with expected message "+error, "Actual Error Message "+error1+" does not match with expected message "+error);
						i++;
						break;
					}
				}
			}
		}
		return flag;
	}

	public static boolean verifyEventsData() throws Throwable
	{
		boolean flag=true;
		int i=38;
		eReader=new ExcelReader("C:\\Users\\IN00493.CIGNITI\\Desktop\\Merge of AT_latestCode\\TestData\\TestData.xls", "IL_ImportExhibitor", 37);
		while (i < eReader.getLastRow()) {
			System.out.println(eReader.getColData("Exhibit Id", i));
			String id=eReader.getColData("Exhibit Id", i);
			if(remove_Event_Page())
			{
				Reporters.SuccessReport("Verify Page", "Remove Event Page is displayed");
			}
			else
			{
				Reporters.failureReport("Verify Page", "Remove Event Page is displayed");
			}
			String URL=driverM.getCurrentUrl();
			URL=URL+"&eventId="+id;
			driverM.get(URL);
			if(waitForVisibilityOfElement(IL_CreateAndDeleteAnEventOR.btnRemove, "Remove button"))
			{
				Reporters.SuccessReport("Verify whether event exists or not", "Event exists");
				System.out.println("No of colums: "+eReader.getLastColumn());
				innerloop:{
					for(int j=1;j<(eReader.getLastColumn()-1);j++)
					{
						System.out.println("Data from excel sheet: "+eReader.getRowData(i).get(j));
						System.out.println("colName: "+eReader.getRowData(0).get(j));
						String colName=eReader.getRowData(0).get(j);
						String data=eReader.getColData("expirationDate", j);
						if(colName.equalsIgnoreCase("password"))
						{
							System.out.println("Password");
						}
						else if (!((colName.equalsIgnoreCase("expirationDate"))&&(data.isEmpty()))) {
							if (!isElementPresent(By.xpath("//input[@value='"+eReader.getRowData(i).get(j)+"']"), eReader.getRowData(i).get(j))) {
								flag = false;
							}
						}
						else
						{
							String expDate=eReader.getRowData(i).get(j);
							String[] date=expDate.split("/");
							System.out.println(date[0]);
							int default_date=Integer.parseInt(date[0]+5);
							String finalDate=default_date+"/"+date[1]+"/"+date[2];
							System.out.println(finalDate);
							if (!isElementPresent(By.xpath("//input[@value='"+finalDate+"']"), finalDate)) {
								flag = false;
							}
						}
					}
					if(click(IL_CreateAndDeleteAnEventOR.btnRemove,"Remove Button" ))
					{
						driverM.switchTo().alert().accept();
						i++;
						break innerloop;
					}
				}
			}
			else
			{
				Reporters.failureReport("Verify whether event exists or not", "Event does not exists");
			}
		}
		return flag;
	}
	//Test BFs
	public static boolean createSurveyQuestions(String question_type,String page) throws Throwable
	{
		boolean flag=true;
		String qtntext=null;
		Actions builder = new Actions(driverM);
		builder.moveToElement(driverM.findElement(By.xpath("//div[@id='builder']/div/a[text()='"+question_type+"']"))).build().perform();
		Thread.sleep(2000);
		if(question_type.equalsIgnoreCase("Multiple Choice / Multiple Answers"))
		{
			qtntext=driverM.findElement(By.xpath("//div[h5[text()='Multiple Choice / Multiple Answer']]/div/h4")).getText();
			System.out.println(qtntext);
		}
		else
		{
			qtntext=driverM.findElement(By.xpath("//div[h5[text()='"+question_type+"']]/div/h4")).getText();
			System.out.println(qtntext);
		}
		if(!draganddrop(driverM.findElement(By.xpath("//div[@id='builder']/div/a[text()='"+question_type+"']")), By.xpath("//div[@class='page new-page ui-sortable']"), question_type, page))
		{
			flag=false;
		}
		Thread.sleep(4000);
		String page_num=driverM.findElement(By.xpath("//div[h4[text()='"+qtntext+" ']]/preceding-sibling::div/h3")).getText();
		System.out.println(page_num);
		if(!click(By.xpath("//div[h3[text()='"+page_num+"']]/parent::div/following-sibling::div[@class='dropzone']"),"+ Add New Page"))
		{
			flag=false;
		}
		if(!click(SurveyOR.btnSubmit,"Submit Button"))
		{
			flag=false;
		}
		return flag;
	}



	public static void REST_Paste(Robot robot){
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	}

	public static boolean REST_EnterBody(Sheet sheet, int col, int row) throws Exception{
		boolean flag=true;
		try {
			robot = new Robot();
			String localpath = sheet.getCell(col,row).getContents();
			StringSelection path = new StringSelection(localpath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
			for (int i = 0; i < 16; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);  
			}
			REST_Paste(robot);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean REST_Login() throws Exception{
		boolean flag=true;
		try {
			Thread.sleep(2000);
			robot = new Robot();
			String localpath = configProps.getProperty("EMT_Username");
			StringSelection path = new StringSelection(localpath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
			Thread.sleep(1000);
			REST_Paste(robot);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			localpath = configProps.getProperty("EMT_Password");
			path = new StringSelection(localpath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
			REST_Paste(robot);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean setTime(By locator,String time) throws Throwable
	{
		boolean flag=true;
		String[] time1 = time.split(" ");
		System.out.println(time1[0]);
		String[] hours=time1[0].split(":");
		System.out.println(hours[0]);
		int hour=Integer.parseInt(hours[0]);
		String minutes=hours[1];
		System.out.println(minutes);
		int minute=Integer.parseInt(minutes);
		try {
			driverM.findElement(locator).click();
			driverM.findElement(By.id("ui-datepicker-div"));
			if ((hour<13)&&(minute<60)) {
				String time2 = driverM.findElement(By.xpath("//div[@class='ui-timepicker-div']/dl/dd[@class='ui_tpicker_time']")).getText();
				System.out.println(time2);
				String[] times = time2.split(" ");
				System.out.println(times[0]);
				System.out.println(times[1]);
				if (time1[1].equalsIgnoreCase("pm")) {
					if (!(times[1].equalsIgnoreCase("pm"))) {

						slider(By.xpath("//dd[@class='ui_tpicker_hour']/div/a"),
								((hour + 2 + 12) * 5), 0, "");
					}
					String time3 = driverM.findElement(
							By.xpath("//dd[@class='ui_tpicker_time']")).getText();
					System.out.println(time3);
				}
				if (minute < 15) {
					slider(By.xpath("//dd[@class='ui_tpicker_minute']/div/a"),
							(minute * 2), 0, "");
				} else if ((minute >= 15) && (minute < 33)) {
					slider(By.xpath("//dd[@class='ui_tpicker_minute']/div/a"),
							((minute * 2) + 4), 0, "");
				} else {
					slider(By.xpath("//dd[@class='ui_tpicker_minute']/div/a"),
							((minute * 2) + 5), 0, "");
				}
				if (!(hour == 12) && (time1[1].equalsIgnoreCase("am"))) {
					if (hour >= 9) {
						slider(By.xpath("//dd[@class='ui_tpicker_hour']/div/a"),
								((hour + 1) * 5), 0, "");
					} else {
						slider(By.xpath("//dd[@class='ui_tpicker_hour']/div/a"),
								((hour) * 5), 0, "");
					}
				}
			}
			else
			{
				System.out.println("invalid time");
			}
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}


}
