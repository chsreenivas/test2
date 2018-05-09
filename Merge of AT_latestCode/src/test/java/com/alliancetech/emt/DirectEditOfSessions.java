package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DirectEditOfSessions extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"},priority=0)
	public void createASessionInEMT(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL,String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType,
			String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub) throws Throwable {
		 if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
		  {
			  driver.unregister(myListener);
		  }
		try{
			if(logIn==0)
			{
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
					logIn++;
				}

				else{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
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

		click(EMT_SessionsOR.btnSubmit, "Submit Button");

		verifyTextPresent("Session has been added! ");

		getEMTURL();

		if(search(Title)){
			Reporters.SuccessReport("Search for Session having title"+Title, "Required Steps are performed Successfully");
		}
		else{
			Reporters.failureReport("Search for Session having title", "unable to perform all steps");
		}

		Thread.sleep(2000);

		if(sessionDetailsVerification(Title, StartTime, EndTime, Date)){	
			Reporters.SuccessReport("Verify Session on Search Results Page", "Found Session having title "+Title+"and "+Date);
		}
		else{
			Reporters.failureReport("Verify Session on Search Results Page", "No Session Found with Title"+Title);
		}

		if(click(By.xpath("//tr/td[text()='"+Session+"']"),Session)){
			Reporters.SuccessReport("Verify Page", "Navigated to session details page");
			verifySessionInformation(Session, CustomerSessionId, Title, Status, Location, Room, Active, Enrollable, Date, Capacity, StartTime, EndTime, ScanStartTime, ScanEndTime, Survey, HashTag, Description, PresentationURL);

			verifyCategoriesInformation(Program, Track, SubTrack, TargetRole, Audience, ByInviteOnly, Timeslot, Market, Industry, Product, ContentCategory, SessionTheme, ContentLevel, SessionType);

			verifyAdditionalInformation(Additional_Info_1, Additional_Info_2, Additional_Info_3, Additional_Info_4, Additional_Info_5, Demo1, Demo3, Demo4, Demo7, Demo9, Demo11, Demo12, Demo14, SessionCode, Topic_Tag, CustomSql1, ProgTrackSub);
		}

		getEMTURL();
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_AddSessions");  //returning data from "EMTAddSession" sheet and "Key_AddSessions" as a reference to read data
	}

	@Test(dataProvider = ("getEditData"),groups= {"EMT", "RunAll"},priority=1)
	public void EditSessions(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL,String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType,
			String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub) throws Throwable
			{

		if(clickTabFromViewMore(EMT_SessionsOR.tabSessions,"Sessions tab")){
			Reporters.SuccessReport("Navigate to Sessions Page", "Required steps have been performed above successfully");
		}
		else{
			Reporters.failureReport("Navigate to Sessions Page", "Unable to Perform all the required steps");
		}

		if(search(Session)){
			Reporters.SuccessReport("Search for Session having title"+Title, "Required Steps are performed Successfully");
		}
		else{
			Reporters.failureReport("Search for Session having title", "unable to perform all steps");
		}

		Thread.sleep(2000);
		if(click(By.xpath("//tr/td[text()='"+Session+"']"),Session)){
			Reporters.SuccessReport("Verify Page", "Navigated to session details page");

			if(editSessionInformation(Session, CustomerSessionId, Title, Status, Location, Room, Active, Enrollable, Date, Capacity, StartTime, EndTime, ScanStartTime, ScanEndTime, Survey, HashTag, Description, PresentationURL)){
				Reporters.SuccessReport("Edit Session Information details", "All Session Information Details are edited Successfully");
			}
			else{ 
				Reporters.failureReport("Edit Session Information details", "Unable to edit all session information");
			}
			if(editCategories(Program, Track, SubTrack, TargetRole, Audience, ByInviteOnly, Timeslot, Market, Industry, Product, ContentCategory, SessionTheme, ContentLevel, SessionType)){
				Reporters.SuccessReport("Select/Add Categories", "Selected/Added all the given categories");
			}
			else{
				Reporters.failureReport("Select/Add Categories", "Unable to select/add the given categories");
			}

			if(editAdditionalInformation(Additional_Info_1,Additional_Info_2,Additional_Info_3,Additional_Info_4,Additional_Info_5,Demo1,Demo3,Demo4,Demo7,Demo9,Demo11, Demo12, Demo14, SessionCode,
					Topic_Tag, CustomSql1, ProgTrackSub)){
				Reporters.SuccessReport("Select/Add additional information", "Selected/Added all the given additional information");
			}
			else{
				Reporters.failureReport("Select/Add additional information", "Unable to select/add the given additional information");
			}
			refresh();

			verifySessionInformation(Session, CustomerSessionId, Title, Status, Location, Room, Active, Enrollable, Date, Capacity, StartTime, EndTime, ScanStartTime, ScanEndTime, Survey, HashTag, Description, PresentationURL);

			verifyCategoriesInformation(Program, Track, SubTrack, TargetRole, Audience, ByInviteOnly, Timeslot, Market, Industry, Product, ContentCategory, SessionTheme, ContentLevel, SessionType);

			verifyAdditionalInformation(Additional_Info_1, Additional_Info_2, Additional_Info_3, Additional_Info_4, Additional_Info_5, Demo1, Demo3, Demo4, Demo7, Demo9, Demo11, Demo12, Demo14, SessionCode, Topic_Tag, CustomSql1, ProgTrackSub);

			if(js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link")){

				waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
				Thread.sleep(2000);

				click(EMT_RegistrantsOR.btnConfirm,"Confirm button");

				emtLogOut();

				emtLogIn();

				clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab");

				search(Session);

				if(verifyInTable("Sessions",Session))
				{
					Reporters.failureReport("Session verification after delete", ""+Session+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Session verification after delete", ""+Session+" is not available in table,hence successfully deleted");
				}
			}
		}

		getEMTURL();
		emtLogout=true;

			}


	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getEditData() throws Exception {

		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_EditSessions");  //returning data from "EMT_DirectEdit" sheet and "Key_EditRegistrant" as a reference to read data
	}
}
