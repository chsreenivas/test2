package com.alliancetech.emt;
/**
 * This Test Case is used to Add Sessions and then verify the sessions
 * Reads Data from EMTAddSession Sheet
 * reference Test rail-iConnect_EMT(Create Session)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddSessionsTest extends BusinessFunctions{
	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll","Survey"})
	public void EMT_AddSessions(String Session,String CustomerSessionId,String Title,String Status,String Location,String Room,String Active,
			String Enrollable,String Date,String Capacity,String StartTime,String EndTime,String ScanStartTime,String ScanEndTime,String Survey,String HashTag,String Description,
			String PresentationURL,String Program,String Track,String SubTrack,String TargetRole,String Audience,String ByInviteOnly,String Timeslot,
			String Market,String Industry,String Product,String ContentCategory,String SessionTheme,String ContentLevel,String SessionType,String speaker,
			String Additional_Info_1,String Additional_Info_2,String Additional_Info_3,String Additional_Info_4,String Additional_Info_5,
			String Demo1,String Demo3,String Demo4,String Demo7,String Demo9,String Demo11,String Demo12,String Demo14,String SessionCode,
			String Topic_Tag,String CustomSql1,String ProgTrackSub) throws Throwable {

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

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		Thread.sleep(3000);
		
		js_click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		verifyTextPresent("Session has been added! ");
		
		/*getEMTURL();
		
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
				
		if(click(EMT_SessionsOR.verifySession(Title, StartTime, EndTime, Date),Title)){
			Reporters.SuccessReport("Verify Page", "Navigated to session details page");
			verifySessionInformation(Session, CustomerSessionId, Title, Status, Location, Room, Active, Enrollable, Date, Capacity, StartTime, EndTime, ScanStartTime, ScanEndTime, Survey, HashTag, Description, PresentationURL);
			
			verifyCategoriesInformation(Program, Track, SubTrack, TargetRole, Audience, ByInviteOnly, Timeslot, Market, Industry, Product, ContentCategory, SessionTheme, ContentLevel, SessionType);
			
			verifyAdditionalInformation(Additional_Info_1, Additional_Info_2, Additional_Info_3, Additional_Info_4, Additional_Info_5, Demo1, Demo3, Demo4, Demo7, Demo9, Demo11, Demo12, Demo14, SessionCode, Topic_Tag, CustomSql1, ProgTrackSub);
		}*/
		
		getEMTURL();
		emtLogout=true;
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTAddSession", "Key_AddSessions");  //returning data from "EMTAddSession" sheet and "Key_AddSessions" as a reference to read data
	}
	
}

