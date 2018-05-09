package com.alliancetech.portal_myagenda;
/**
 * This Test Case verify 
 * Session Details pop-up is displayed with the correct elements when selected from the calender graph.
 * reference sheet- iConnect-MyAgenda(CalenderGraph)  
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class VerifyCalenderGraphPage_SessionPopUpTest extends BusinessFunctions{

	@Test
	public void verifyCalendarGraph() throws Throwable
	{

		if(getPortalURL())
		{
			Reporters.SuccessReport("Open Portal Application", "Application URL has typed successfully");
		}
		else
		{
			Reporters.failureReport("Open Portal Application", "Application is failed to open ");
		} 

		if(Portal_Login())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(sessionDetailsPopUp())
		{
			Reporters.SuccessReport("Verification of session Details pop up ", "Session Details Pop up is displayed");
		}
		else
		{
			Reporters.failureReport("Verification of session Details pop up", "Session Details Pop up is not displayed");
		}	

		if(sessionDetailsPopUpValidation())
		{
			Reporters.SuccessReport("Verification of session details pop up page elements", "All Elements are present");
		}
		else
		{
			Reporters.failureReport("Verification of session details pop up page elements", "Elements are missing");
		}

		if(logOut())
		{
			Reporters.SuccessReport("Verification of log out", "Application logged out successfully");
		}
		else{
			Reporters.failureReport("Verification of log out", "Application failed to log out");
		}
	}

}
