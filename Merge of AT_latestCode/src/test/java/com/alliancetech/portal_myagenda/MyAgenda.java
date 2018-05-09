package com.alliancetech.portal_myagenda;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.alliancetech.objectrepository.MyAgendaOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class MyAgenda extends BusinessFunctions{
	public static int logIn=0;
	
  @Test(dataProvider = ("getData"),groups= {"Portal", "RunAll"})
  public void verificationsInMyAgenda(String Title,String Status,String Active,String StartTime,String EndTime) throws Throwable {
	 try{
			if(logIn==0)
			{
				if(getPortalURL())
				{
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
				}
				if(Portal_Login())
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
	  
	  js_click(CommonOR.lnkIconInHomePage("My Agenda"),"My Agenda icon in Home Page");
	  
	  if(verifyPrintIconInMyAgenda())
	  {
		  Reporters.SuccessReport("Verify Print icon present in My Agenda Screen", "Print pop up is displayed with all the elements verified above");
	  }
	  else
	  {
		  Reporters.failureReport("Verify Print icon present in My Agenda Screen", "Print pop up is failed to display the above elements");
	  }
	  
	  if(getCalenderGraphDate()){
		  	Reporters.SuccessReport("Reads date from My Agenda calender graph", "Successfully taken date from My Agenda Calender Graph and formatted it to required date fromat");
			  }
	  else
	  {
		  Reporters.failureReport("Reads date from My Agenda calender graph", "Failed to perform the above steps successfully");
	  }
	  
	  if(openSiteInNewWindow("Reporting Site")){
		  getEMTURL();
			Reporters.SuccessReport("Open New Tab and Navigate to Portal", "New Tab has been opened and Navigated to Portal Successfully");
		}
		else
		{
			Reporters.failureReport("Open New Tab and Navigate to Portal", "Unable to open new tab and navigate to portal");
		}

		verifyTextPresent("Forgot your password?");

		if(emtLogIn()){
			Reporters.SuccessReport("Login into Portal Application", "User has been logged into Portal");
		}
		else{
			Reporters.failureReport("Login into Portal Application", "Unable to Login into Portal");
		}
		
		Thread.sleep(2000); 
		
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
		
		if(addSessionInfo(Title, Status,Active,StartTime,EndTime)){
			Reporters.SuccessReport("Enter Session Information details", "All Session Information Details are entered Successfully");
		}
		else{ 
			Reporters.failureReport("Enter Session Information details", "Unable to enter all session information");
		}
		
		click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		verifyTextPresent("Session has been added! ");
		
		if(search(Title)){
			Reporters.SuccessReport("Search for Session having title"+Title, "Required Steps are performed Successfully");
		}
		else{
			Reporters.failureReport("Search for Session having title", "unable to perform all steps");
		}
		
		Thread.sleep(2000);
		
		if(closeWindow("Intelligent CONNECT",1))
		{
			Reporters.SuccessReport("Close the Portal Tab", "Portal Tab is closed");
		}
		else
		{
			Reporters.failureReport("Close the Portal Tab", "Unable to close the Portal Tab");
		}

		if(clickAddSessionIconInMyAgenda()){
			Reporters.SuccessReport("Verify '+' icon in My Agenda Page", "Switches to Portal site and successfully clicked on '+' icon");
		}
		else
		{
			Reporters.failureReport("Verify '+' icon in My Agenda Page", "Failed to perform the above operation");
		}
		
		if(advancedSearchErrorMessageValidation())
		{
			Reporters.SuccessReport("Verify the error message when advanced search is performed with only one keyword", "Displayed an error message successfully");
		}
		else
		{
			Reporters.failureReport("Verify the error message when advanced search is performed with only one keyword", "Failed to display an error message");
		}
		
		if(advanceSearchForaSession(Title))
		{
			Reporters.SuccessReport("Perform advance search for session "+Title, "Successfully searched for the session "+Title);
		}
		else
		{
			Reporters.failureReport("Perform advance search for session "+Title, "Failed to search for the session "+Title);
		}
		
		if(addSessionToMyAgenda(Title, StartTime))
		{
			Reporters.SuccessReport("Add session "+Title+" to My Agenda", "Session "+Title+" got successfuly added to My Agenda Calender Graph");
		}
		else
		{
			Reporters.failureReport("Add session "+Title+" to My Agenda", "Failed to add Session "+Title+" to My Agenda Calender Graph");
		}
		
		if(verifyViewSessionDetails(Title, StartTime, EndTime))
		{
			Reporters.SuccessReport("Verify Session Pop Up details from calender graph for session "+Title, "Successfully verified all the details for session "+Title+" in Session Detail pop up");
		}
		else
		{
			Reporters.failureReport("Verify Session Pop Up details from calender graph for session "+Title, "Failed to verify the above details for session "+Title+" in session details pop up");
		}
		
		click(CommonOR.tabToBeClicked("Sessions"),"Sessions Tab");
		
		if(removeSessionFromMyAgenda(Title, StartTime))
		{
			Reporters.SuccessReport("Remove Session "+Title+" from My Agenda", "Session "+Title+" got successfully removed from My Agenda");
		}
		else
		{
			Reporters.failureReport("Remove Session "+Title+" from My Agenda","Session "+Title+" is failed to remove from My Agenda");
		}
		
		js_click(CommonOR.tabToBeClicked("Sessions"),"Sessions tab");
		
		Thread.sleep(5000);
		
		waitForElementPresent(MyAgendaOR.btnViewSessionDetails);
		
	    click(MyAgendaOR.btnViewSessionDetails,"View Session Details Button");
		
		if(verifyViewSessionDetails(Title,StartTime,EndTime))
		{
			Reporters.SuccessReport("Verify Session Pop Up details for session "+Title, "Successfully verified all the details for session "+Title+" in Session Detail pop up");
		}
		else
		{
			Reporters.failureReport("Verify Session Pop Up details for session "+Title, "Failed to verify the above details for session "+Title+" in session details pop up");
		}
		logoutFromPortal();
		if(openSiteInNewWindow("Reporting Site")){
			getEMTURL();
			Reporters.SuccessReport("Open New Tab and Navigate to Portal", "New Tab has been opened and Navigated to Portal Successfully");
		}
		else
		{
			Reporters.failureReport("Open New Tab and Navigate to Portal", "Unable to open new tab and navigate to portal");
		}

		if(clickTabFromViewMore(EMT_SessionsOR.tabSessions,"Sessions tab")){
			Reporters.SuccessReport("Navigate to Sessions Page", "Required steps have been performed above successfully");
		}
		else{
			Reporters.failureReport("Navigate to Sessions Page", "Unable to Perform all the required steps");
		}
		
		verifyText(EMT_SessionsOR.txtSessionTitle, "Sessions", "Sessions Page");

		search(Title);
			
		if(deleteAndVerifyaSessionInEMT(Title))
		{
			Reporters.SuccessReport("Delete Session "+Title+" from EMT", "Session "+Title+" got deleted successfully");
		}
		else
		{
			Reporters.failureReport("Delete Session "+Title+" from EMT", "Unable to delete session "+Title+" from EMT");
		}
		
		getEMTURL();
		
		driver.close();
		
		switchWindowByTitle("Intelligent CONNECT",1);
		
}
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Portal_AddSessionToMyAgenda", "Key_Session");  //returning data from "Portal_AddSessionToMyAgenda" sheet and "Key_Session" as a reference to read data
	}
	
}
