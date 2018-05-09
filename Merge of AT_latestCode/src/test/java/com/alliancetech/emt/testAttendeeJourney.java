package com.alliancetech.emt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.AttendeeJourneyOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.DataImports;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.CSVFileIO;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;
import com.csvreader.CsvWriter;


public class testAttendeeJourney extends BusinessFunctions{
	ArrayList<String>[] inputData ;
	public static String exhibitID=null;
	public static String exhibitorName=null;
	public static CsvWriter csvOutput;
  @Test(dataProvider = ("getData"),groups={"EMT","RunAll"},priority=0)
  public void createRegistrant(String prefix,String attendee,String first,String CustomerRegistrantID,String last,
		   String status,String AttendeeType,String SubAttendeeType,String WebURL,String Blog,String LinkedInURL,
		   String FacebookURL,String TwitterAcnt,String IM,String AboutMe,String FullName,String BadgeFirstName,String Title,
		   String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
		   String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,
		   String OptInAttendeeSrch,String OptInTicklerMsgs,String OptInTwitterSesSelection,String OptInSessAttendance,String OptInRFIDAttendance,
		   String LogInID,String Pswd,String ConfirmPassword,String image,String Industry,String Product,String JobRole,String Division,
		   String Education,String Certifications,String CatRegion,String Market,String CompanySize,String annualSales,
		   String RegPrimaryCode,String Demo1,String Demo2,String Demo3,String Demo4,String Demo5,String Demo6,String Demo7,
		   String Demo8,String Demo9,String Demo10,String AccessFlag,String ExecSummitFlag,String HotelConfirmation,String HotelSelection,
		   String MealTypeFlag,String NickName,String PromotionCode,String RevRecFlag,String StaffMealFlag,String AddInfo4,String AddInfo5,String AddInfo1,String AddInfo2,String AddInfo3,String PrintBadgeOnSubmit,String BadgePrinter) throws Throwable {
	  
	  
	  if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");
		
		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");
		
		js_TriggerOnClickEvent("addNewRecord();", "Add Button");
		
		waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");
		
		if(addRegistrantsInformation(prefix,attendee,first,CustomerRegistrantID,last,status,AttendeeType,SubAttendeeType))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}
		
		if(addRegistrantBio(WebURL, Blog, LinkedInURL, FacebookURL, TwitterAcnt,IM, AboutMe))
		{
			Reporters.SuccessReport("Add Registrant information", ""+first+" Registrant information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant information", ""+first+" Registrant information is not added");
		}
		
		if(addRegistrantContactInformation(FullName, BadgeFirstName, Title, Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County, Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email))
		{
			Reporters.SuccessReport("Add Registrant Contact information", ""+first+" Registrant Contact information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Contact information", ""+first+" Registrant Contact information is not added");
		}
		
		if(addRegistrantOptInInformation(OptInAttendeeSrch, OptInTicklerMsgs, OptInTwitterSesSelection, OptInSessAttendance, OptInRFIDAttendance))
		{
			Reporters.SuccessReport("Add Registrant Opt In information", ""+first+" Registrant Opt In information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Opt In information", ""+first+" Registrant Opt In information is not added");
		}
		
		if(addRegistrantLoginInformation(LogInID, Pswd, ConfirmPassword))
		{
			Reporters.SuccessReport("Add Registrant Login information", ""+first+" Registrant Login information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Login information", ""+first+" Registrant Login information is not added");
		}
		
		if(addRegistrantImage(image))
		{
			Reporters.SuccessReport("Add Registrant Image", ""+first+" Registrant Image is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Image", ""+first+" Registrant Image is not added");
		}
		
		if(addRegistrantCategories(Industry, Product, JobRole, Division, Education, Certifications, CatRegion, Market, CompanySize, annualSales, RegPrimaryCode))
		{
			Reporters.SuccessReport("Add Registrant categories", ""+first+" Registrant categories are added");
		}
		else
		{
			Reporters.failureReport("Add Registrant categories", ""+first+" Registrant categories are not added");
		}
		
		if(addRegistrantAdditionalInfo(Demo1, Demo2, Demo3, Demo4, Demo5, Demo6, Demo7, Demo8, Demo9, Demo10, AccessFlag, ExecSummitFlag, HotelConfirmation, HotelSelection,
				    MealTypeFlag, NickName, PromotionCode, RevRecFlag, StaffMealFlag, AddInfo4, AddInfo5, AddInfo1, AddInfo2, AddInfo3))
		{
			Reporters.SuccessReport("Add Registrant additional information", ""+first+" Registrant additional information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant additional information", ""+first+" Registrant additional information is not added");
		}
		
		if(addRegistrantBadgePrinting(PrintBadgeOnSubmit, BadgePrinter))
		{
			Reporters.SuccessReport("Add Registrant Badge printing details", ""+first+" Registrant Badge printing details are added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Badge printing details", ""+first+" Registrant Badge printing details are not added");
		}
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}
		
		getEMTURL();
		
  }
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("AttendeeJourney", "Key_AddReg");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
	
	@Test(dataProvider = ("getSessionData"),groups= {"EMT", "RunAll"},priority=1)
	public void EMT_AddSessions(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL,String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType,String speaker,
			String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub,String Keywords,String ImportIntCustomSQLEnabled,String Paper_Type,String NowNextIPAddress,String Field2014) throws Throwable {

		try{
			if(getEMTURL())
				{
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
				}
				
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(EMT_SessionsOR.tabSessions,"Sessions tab")){
			Reporters.SuccessReport("Navigate to Sessions Page", "Required steps have been performed above successfully");
		}
		else{
			Reporters.failureReport("Navigate to Sessions Page", "Unable to Perform all the required steps");
		}

		if(NavigateToAddSessionsPage())
		{
			Reporters.SuccessReport("Navigate to Add Sessions Page", "Steps to navigate to add sessions page have been performed");
		}
		else
		{
			Reporters.failureReport("Navigate to Add Sessions Page", "Unable to perform all the required steps to navigate");
		}

		verifyText(EMT_SessionsOR.txtPageTitle, "Add New Session", "Add Session Page");

		Thread.sleep(2000);
		
		if(addSessionInformation(Session, CustomerSessionId, Title, Status, Location, Room, Active, Enrollable, Date, Capacity, StartTime, EndTime, ScanStartTime, ScanEndTime, Survey, HashTag, Description, PresentationURL)){
			Reporters.SuccessReport("Enter Session Information details", "All Session Information Details are entered Successfully");
		}
		else{ 
			Reporters.failureReport("Enter Session Information details", "Unable to enter all session information");
		}

		if(addCategories(Program, Track, SubTrack, TargetRole, Audience, ByInviteOnly, Timeslot, Market, Industry, Product, ContentCategory, SessionTheme, ContentLevel, SessionType)){
			Reporters.SuccessReport("Select/Add Categories", "Selected/Added all the given categories");
		}
		else{
			Reporters.failureReport("Select/Add Categories", "Unable to select/add the given categories");
		}
		
		if(addAdditionalInformation(Additional_Info_1,Additional_Info_2,Additional_Info_3,Additional_Info_4,Additional_Info_5,Demo1,Demo3,Demo4,Demo7,Demo9,Demo11, Demo12, Demo14, SessionCode,
				 					Topic_Tag, CustomSql1, ProgTrackSub)){
			Reporters.SuccessReport("Select/Add additional information", "Selected/Added all the given additional information");
		}
		else{
			Reporters.failureReport("Select/Add additional information", "Unable to select/add the given additional information");
		}

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		Thread.sleep(3000);
		
		click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		verifyTextPresent("Session has been added! ");
		
		getEMTURL();
	}
	
	@DataProvider
	public Object[][] getSessionData() throws Exception {

		return Data_Provider.getTableArray("AttendeeJourney", "Key_AddSession");  //returning data from "EMTAddSession" sheet and "Key_AddSessions" as a reference to read data
	}
	
	@Test(dataProvider=("getExhibitorData"),groups= {"EMT", "RunAll"},priority=2)
	  public void EMT_AddExhibitors(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,
			  				   String URL,String FacebookPage,String Description,String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,
			  				   String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode,String LoginID,
			  				   String pswrd,String confirmpassword,String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,
			  				   String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY,String image) throws Throwable {
		  
		  try{
				if(getEMTURL())
					{
						Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
					}
					else
					{
						Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
					}

			}
		   catch(Exception e){
				System.out.println(e.getStackTrace());
			}

			if(clickTabFromViewMore(CommonOR.lnkExhibitors,"Exhibitors Tab")){
				Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
			}
			
			verifyText(EMT_AddExhibitorsOR.txtExhibitorTitle, "Exhibitors", "Exhibitors Page");
			
			js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Record Link");
			
			js_TriggerOnClickEvent("addNewRecord();", "Add Button");
			
			waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");
			
			if(AddExhibitorInformation(ExhibitName,CustomerExhibitID,status,MsgExhibitEmail,Company,Booth,URL,FacebookPage,Description))
			{
				Reporters.SuccessReport("Add Exhibitor information", ""+ExhibitName+" Exhibitor information got added");
			}
			else{
				Reporters.failureReport("Add Exhibitor information", ""+ExhibitName+" Exhibitor information is not added");
			}
			
			if(AddExhibitorContactInformation(ContactFirst, ContactCompany, ContactLast, Email, Phone, AltPhone, ContactAddress1, City, ContactAddress2, Region, ZipCode, CountryCode))
			{
				Reporters.SuccessReport("Add Exhibitor Contact information", ""+ExhibitName+" Exhibitor contact information got added");
			}
			else
			{
				Reporters.failureReport("Add Exhibitor contact information", ""+ExhibitName+" Exhibitor contact information is not added");
			}
			
			if(AddExhibitorLoginInformation(LoginID, pswrd, confirmpassword))
			{
				Reporters.SuccessReport("Add Exhibitor Login information", ""+ExhibitName+" Exhibitor Login information got added");
			}
			else
			{
				Reporters.failureReport("Add Exhibitor Login information", ""+ExhibitName+" Exhibitor login information is not added");
			}
			
			if(AddExhibitorCategories(Category, FocusArea, Industry, IndustryMarket, JobRole, SpecialityProduct, Education, Certifications, Division, SalesRegion, MapHeight, MApRotation, MapWidth, MapX, MapY))
			{
				Reporters.SuccessReport("Add Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details got added");
			}
			else
			{
				Reporters.failureReport("Add Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details are not added");
			}
			
			if(AddExhibitorLogo(image))
			{
				Reporters.SuccessReport("Add Exhibitor logo", ""+ExhibitName+" logo got added");
			}
			else
			{
				Reporters.failureReport("Add Exhibitor logo", ""+ExhibitName+" logo got added");
			}
			
			click(EMT_AddExhibitorsOR.btnSubmit,"Submit Button");
			
			if(exhibitorSuccessMessage())
			{
				Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
			}
			else
			{
				Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
			}
			
			getEMTURL();
			if(search(ExhibitName))
			{
				Reporters.SuccessReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is displayed");
			}
			else{
				Reporters.failureReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is not displayed");
			}
			
			waitForVisibilityOfElement(EMT_AddExhibitorsOR.tblExhibitorResults, "Exhibitor results");
			
			if(verifyExhibitor(ExhibitName, Booth, Company))
			{
				Reporters.SuccessReport("Verify Exhibitor", "Newly added Exhibitor "+ExhibitName+" is Displayed");
				exhibitID=getExhibitorID();
				System.out.println(exhibitID);
			}
			
			getEMTURL();
			emtLogOut();
			
	}
	@DataProvider
	public Object[][] getExhibitorData() throws Exception {

		return Data_Provider.getTableArray("AttendeeJourney", "Key_AddExhibitor");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}
	
	@Test(dataProvider=("getCSVData"),groups= {"EMT", "RunAll"},priority=3)
	public void writeToCSVFileAndUpload(String customerRegistrantID,String timeStamp,String timeZone,String device,String AttendeeID,String leadPriority) throws Throwable
	{
		try {
			// use FileWriter constructor that specifies open for appending
			csvOutput = new CsvWriter(new FileWriter("TestData\\leads.csv", true), ',');
			
			// write out a few records
			csvOutput.write(customerRegistrantID);
			csvOutput.write(exhibitID);
			csvOutput.write(timeStamp);
			csvOutput.write(timeZone);
			csvOutput.write(device);
			csvOutput.write(AttendeeID);
			csvOutput.write(leadPriority);
			
			csvOutput.endRecord();
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}
		
		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		click(DataImports.lnkImportLeadsData, "Import Leads Data");

		if(uploadFile(new File("TestData\\leads.csv").getAbsolutePath()))
		{
			Reporters.SuccessReport("Verify uploading of file", "Exhibitors csv file got successfully uploaded");
		}
		else
		{
			Reporters.failureReport("Verify uploading of file", "Exhibitors csv file failed to upload");
		}
		if(js_click(DataImports.btnUpload, "Upload Button"))
		{
			Reporters.SuccessReport("Verify Upload button", "Able to click on: Upload Button");
		}
		else
		{
			Reporters.failureReport("Verify Upload button","Not Able to click on: Upload Button");
		}

		waitForInVisibilityOfElement(By.id("status-msg-display"), "Auto Refresh");

		if(driver.getPageSource().contains("Confirm?")){
			click(DataImports.btnOK_confirmPopUp,"Ok button in Confirm Popup");
		}

		Thread.sleep(5000);

		if(mapFields())
		{
			Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
		}
		else
		{
			Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
		}
		
		if(validateDataAndProgressOperation())
		{
			Reporters.SuccessReport("Verify data validation and progress operation", "Data has been successfully validated and 100% progressed");
		}
		else
		{
			Reporters.failureReport("Verify data validation and progress operation", "Data has failed to validate and stopped progression");
		}
		
		getEMTURL();
	}
	
	@DataProvider
	public Object[][] getCSVData() throws Exception {

		return Data_Provider.getTableArray("AttendeeJourney", "Key_CSVData");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}
	
	@Test(groups= {"EMT", "RunAll"},priority=4)
	public void enableLeadCapturesViewBy() throws Throwable
	{
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		if(setLeadCapturesViewBy())
		{
			Reporters.SuccessReport("Enable Lead Captures View By For Registrants", "Lead Captures View By has been enabled for registrants");
		}
		else
		{
			Reporters.failureReport("Enable Lead Captures View By For Registrants", "Lead Captures has not enabled for registrants");
		}
		
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		if(setLeadCapturesViewBy())
		{
			Reporters.SuccessReport("Enable Lead Captures View By For Registrants", "Lead Captures View By has been enabled for registrants");
		}
		else
		{
			Reporters.failureReport("Enable Lead Captures View By For Registrants", "Lead Captures has not enabled for registrants");
		}
	}
	
	@Test(groups= {"EMT", "RunAll"},priority=5)
	public void verifyLeadCaptures() throws Throwable
	{
		inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\leads.csv").getAbsolutePath());
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		if(verifyLeadsCSVData(inputData, "Registrant Number"))
		{
			Reporters.SuccessReport("Verify Lead Captures in Registrant tab", "Lead captures have been successfully verified");
		}
		else
		{
			Reporters.failureReport("Verify Lead Captures in Registrant tab", "failed to verify lead captures in Registrants Tab");
		}
		
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}
		
		if(verifyLeadsCSVData(inputData, "Exhibitor Id"))
		{
			Reporters.SuccessReport("Verify Lead Captures in Exhibitor tab", "Lead captures have been successfully verified in Exhibitor tab");
		}
		else
		{
			Reporters.failureReport("Verify Lead Captures in Exhibitor tab", "failed to verify lead captures in Exhibitor Tab");
		}
		
		if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(clickTabFromViewMore(CommonOR.lnkLeads, "Leads Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Leads page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Leads page is not displayed");
		}
		
		if(verifyLeadsCSVData(inputData, "Registrant Number"))
		{
			Reporters.SuccessReport("Verify Lead Captures in Leads tab", "Lead captures have been successfully verified in Leads tab");
		}
		else
		{
			Reporters.failureReport("Verify Lead Captures in Leads tab", "failed to verify lead captures in Leads Tab");
		}
		
		getEMTURL();
		emtLogOut();
	}
	
	@Test(dataProvider=("getAttendeeJourneyData"),groups= {"EMT", "RunAll"},priority=6)
	public void verifyAttendeeJourney(String attendeeid,String first,String Last,String sessionid,String SessionTitle,String ExhibitName) throws Throwable
	{
		if(getAttendeeJourneyURL())
		{
			Reporters.SuccessReport("Launch Attendee Journey Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch Attendee Journey Application", "Application URL failed to launch ");
		}
		
		if(attendeeJourneyLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		if(searchForAttendeeInAttendeeJourney(attendeeid))
		{
			Reporters.SuccessReport("Search for an attendee with id "+attendeeid, "Successfully searched for attendee "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for an attendee with id "+attendeeid, "Failed to search for the attendee "+attendeeid);
		}
		
		if(verifyAttendeeJourneyElements(first,Last,attendeeid))
		{
			Reporters.SuccessReport("Verify attendee journey elements", "Successfully verified above elements");
		}
		else
		{
			Reporters.failureReport("Verify attendee journey elements", "Failed to verify the above elements");
		}
		
		if(openSiteInNewWindow("Reporting Site"))
		{
			getEMTURL();
			Reporters.SuccessReport("Open Survey Site in new window", "Survey Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open Survey Site in new window","Survey site has failed to open in new window");
		}
		
		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else 
		{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		if(search(attendeeid))
		{
			Reporters.SuccessReport("Search for attendee: "+attendeeid, "Successfully searched for attendee with id: "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for attendee: "+attendeeid, "Failed to search for attendee with id: "+attendeeid);
		}
		
		if(click(By.xpath("//td[text()='"+attendeeid+"']"),"attendee: "+attendeeid))
		{
			if (sessionAttendance(sessionid)) {
				Reporters.SuccessReport("Verification of Session attendance","Sessions attendance is done successfully");
			} 
			else {
				Reporters.failureReport("Verification of Session attendance","Sessions attendance is failed");
			}
			if (verifySessionAttendance(sessionid)) {
				Reporters.SuccessReport("Verification of Session attendance","Sessions attendance verification for registrant "+ sessionid + " is done successfully");
			} 
			else {
				Reporters.failureReport("Verification of Session attendance","Sessions attendance verification " + sessionid+ " is failed");
			}
			exhibitorName=getText(AttendeeJourneyOR.txtExhibitorName, "Exhibitor Name");
			System.out.println(exhibitorName);
		}
		
		switchWindowByTitle("", 1);
		
		if(attendeeJourneyLogout())
		{
			Reporters.SuccessReport("Log out from attendee journey site", "Succesfully logged out from attendee journey site");
		}
		else
		{
			Reporters.failureReport("Log out from attendee journey site", "Failed to log out from attendee journey site");
		}
		
		if(attendeeJourneyLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else
		{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		if(searchForAttendeeInAttendeeJourney(attendeeid))
		{
			Reporters.SuccessReport("Search for an attendee with id "+attendeeid, "Successfully searched for attendee "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for an attendee with id "+attendeeid, "Failed to search for the attendee "+attendeeid);
		}
		
		if(verifySessionAttendanceInAttendeeJourney(SessionTitle))
		{
			Reporters.SuccessReport("Verify Session Attendance section in Attendance Journey", "Succesfully verified session Attendance for attendee with id: "+attendeeid);
		}
		else
		{
			Reporters.SuccessReport("Verify Session Attendance section in Attendance Journey", "Session attendance verification failed for attendee with id: "+attendeeid);
		}
		
		if(verifyLeadScansInAttendeeJourney(exhibitorName))
		{
			Reporters.SuccessReport("Verify Lead Scans section in Attendance Journey", "Succesfully verified Lead Scans for attendee with id: "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Verify Lead Scans section in Attendance Journey", "Lead Scans verification failed for attendee with id: "+attendeeid);
		}
		
		if(attendeeJourneyLogout())
		{
			Reporters.SuccessReport("Log out from attendee journey site", "Succesfully logged out from attendee journey site");
		}
		else
		{
			Reporters.failureReport("Log out from attendee journey site", "Failed to log out from attendee journey site");
		}
		
		if(closeWindow("QA Automation",1)){
			Reporters.SuccessReport("Close Attendee Journey site window", "EMT site window has been closed successfully");
		}
		else
		{
			Reporters.failureReport("Close Attendee Journey site window", "EMT site window has failed to close");
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(clickTabFromViewMore(CommonOR.lnkLeads, "Leads Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Leads page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Leads page is not displayed");
		}
		
		if(search(attendeeid))
		{
			Reporters.SuccessReport("Search for attendee: "+attendeeid, "Successfully searched for attendee with id: "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for attendee: "+attendeeid, "Failed to search for attendee with id: "+attendeeid);
		}
		
		click(AttendeeJourneyOR.lnkLeads,"Leads link");
		
		if(click(By.xpath("//div[h2[text()='Leads']]/following-sibling::div/div/table/tbody/tr/td[text()='"+attendeeid+"']"),attendeeid))
		{
			if(deleteRecord())
			{
				Reporters.SuccessReport("Delete lead: "+attendeeid, "Successfully deleted lead with customaer id: "+attendeeid);
			}
			else
			{
				Reporters.failureReport("Delete lead: "+attendeeid, "Failed to delete lead with customer lead id: "+attendeeid);
			}
		}
		
		if(clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Sessions page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Sessions page is not displayed");
		}
		
		if(search(sessionid))
		{
			Reporters.SuccessReport("Search for session: "+sessionid, "Successfully searched for session with id: "+sessionid);
		}
		else
		{
			Reporters.failureReport("Search for session: "+sessionid, "Failed to search for session with id: "+sessionid);
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Sessions"), "Loading");
		
		if(click(By.xpath("//div[h2[text()='Sessions']]/following-sibling::div/div/table/tbody/tr/td[text()='"+sessionid+"']"),sessionid))
		{
			if(deleteRecord())
			{
				Reporters.SuccessReport("Delete session: "+sessionid, "Successfully deleted session with id: "+sessionid);
			}
			else
			{
				Reporters.failureReport("Delete session: "+sessionid, "Failed to delete session with id: "+sessionid);
			}
		}
		
		if(clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}
		
		if(search(ExhibitName))
		{
			Reporters.SuccessReport("Search for exhibitor: "+ExhibitName, "Successfully searched for exhibitor: "+ExhibitName);
		}
		else
		{
			Reporters.failureReport("Search for exhibitor: "+ExhibitName, "Failed to search for exhibitor: "+ExhibitName);
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Exhibitors"), "Loading");
		
		if(click(By.xpath("//div[h2[text()='Exhibitors']]/following-sibling::div/div/table/tbody/tr/td[text()='"+ExhibitName+"']"),ExhibitName))
		{
			if(deleteRecord())
			{
				Reporters.SuccessReport("Delete exhibitor: "+ExhibitName, "Successfully deleted exhibitor with id: "+ExhibitName);
			}
			else
			{
				Reporters.failureReport("Delete exhibitor: "+ExhibitName, "Failed to delete exhibitor with id: "+ExhibitName);
			}
		}
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		if(search(attendeeid))
		{
			Reporters.SuccessReport("Search for registrant: "+attendeeid, "Successfully searched for registrant with id: "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for registrant: "+attendeeid, "Failed to search for registrant with id: "+attendeeid);
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");
		
		if(click(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div/div/table/tbody/tr/td[text()='"+attendeeid+"']"),attendeeid))
		{
			if(deleteRecord())
			{
				Reporters.SuccessReport("Delete registrant: "+attendeeid, "Successfully deleted attendee with id: "+attendeeid);
			}
			else
			{
				Reporters.failureReport("Delete registrant: "+attendeeid, "Failed to delete attendee with id: "+attendeeid);
			}
		}
		
		getEMTURL();
		emtLogout=true;
		
	}
	@DataProvider
	public Object[][] getAttendeeJourneyData() throws Exception {

		return Data_Provider.getTableArray("AttendeeJourney", "Key_verifyAttendeeJourney");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}
 
	
}
