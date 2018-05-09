package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet - iConnect-Sessions(Session Filters)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class SessionsFilterTest extends BusinessFunctions{

	@Test
	public void sessionsFilter() throws Throwable {

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

		if(selectSessionsFilter())
		{
			Reporters.SuccessReport("verification of Session filter ", "Session Tab is opened with Select Area pop-up displayed.");
		}
		else{
			Reporters.failureReport("verification of Session icon ", "Session Tab is not opened with Select Area pop-up displayed.");
		}

		if(selectDropInLabsFilter())
		{
			Reporters.SuccessReport("verification of Drop-In-Labs filter ", "Drop-In-Labs list is displayed.");
		}
		else{
			Reporters.failureReport("verification of Drop-In-Labs filter ", "Drop-In-Labs list is not displayed.");
		}

		if(selectAdvancedSearchFilter())
		{
			Reporters.SuccessReport("verification of Advanced search link ", "Advanced Search Tab is displayed.");
		}
		else{
			Reporters.failureReport("verification of Advanced search link ", "Advanced Search Tab is not displayed.");
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



