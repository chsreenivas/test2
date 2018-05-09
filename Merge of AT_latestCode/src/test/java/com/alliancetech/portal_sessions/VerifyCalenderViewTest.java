package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies
 * i) Calendar is displayed if Calendar View button is selected
 * ii) Sessions Tab is displayed if Back to List link is selected
 * iii) Sessions Tab is displayed if a Filter link is selected
 * iv) Sessions Details pop-up is displayed if a session is selected in Calendar Graph
 * reference sheet- iConnect-Sessions(Calender View)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class VerifyCalenderViewTest extends BusinessFunctions{

	@Test
	public void verify_calender_View() throws Throwable 
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

		if(calenderViewBtn())
		{
			Reporters.SuccessReport("Verification of Calender View button", "Calendar is displayed with Back to List View link,List View button,Filter links,Calendar graph");
		}
		else{
			Reporters.failureReport("Verification of Calender View button", "Calendar is not displayed with Back to List View link,List View button,Filter links,Calendar graph");
		}

		if(backToListViewLnk())
		{
			Reporters.SuccessReport("Verification of Back To List View link", "Sessions Tab is dislpayed");
		}
		else{
			Reporters.failureReport("Verification of Back To List View link", "Sessions Tab is not dislpayed");
		}

		if(backToListBtn())
		{
			Reporters.SuccessReport("Verification of Back To List Button", "Sessions Tab is dislpayed");
		}
		else{
			Reporters.failureReport("Verification of Back To List Button", "Sessions Tab is not dislpayed");
		}

		if(filterLnk())
		{
			Reporters.SuccessReport("Verification of Filter Link", "Calendar graph displays only the sessions related to the filter selected.");
		}
		else{
			Reporters.failureReport("Verification of Filter Link", "Calendar graph displays sessions not related to the filter selected.");
		}

		if(session_Details())
		{
			Reporters.SuccessReport("Verification of Session Details", "Session Details pop-up is displayed.");
		}
		else{
			Reporters.failureReport("Verification of Session Details", "Session Details pop-up is not displayed.");
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
