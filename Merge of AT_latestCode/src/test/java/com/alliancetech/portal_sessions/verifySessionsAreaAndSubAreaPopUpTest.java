package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet- iConnect-Sessions(Sessions)
 */

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class verifySessionsAreaAndSubAreaPopUpTest extends BusinessFunctions {

	@Test
	public void verifySessionsAreaAndSubAreaPopUp() throws Throwable
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

		if(sessionsTab())
		{
			Reporters.SuccessReport("verification of Session icon ", "Session Tab is opened with Select Area pop-up displayed.");
		}
		else{
			Reporters.failureReport("verification of Session icon ", "Session Tab is not opened with Select Area pop-up displayed.");
		}

		if(selectAreaPopUpValidation())
		{
			Reporters.SuccessReport("verification of Select Area pop-up ", "Select Area pop-up is displayed with List of Areas with sub-areas,Continue without a selection button and Don't show this again checkbox.");
		}
		else{
			Reporters.failureReport("verification of Select Area pop-up ", "Select Area pop-up is not displayed with List of Areas with sub-areas,Continue without a selection button and Don't show this again checkbox.");
		}

		if(selectAreaAndSubArea())
		{
			Reporters.SuccessReport("verification of Selected sub area in Select Area pop-up ", "Sessions Tab is displayed with Sessions and Drop-In-Labs filter,Advanced Search link, You've Selected and Narrow Your Results sections,Calender View button and Sessions List with sessions matching with area and sub-area");
		}
		else{
			Reporters.failureReport("verification of Selected sub area in Select Area pop-up  ", "Sessions Tab is not displayed with Sessions and Drop-In-Labs filter,Advanced Search link, You've Selected and Narrow Your Results sections, Calender View button,Sessions List with sessions matching with area and sub-area");
		}

		if(clickContinueWithoutSelection())
		{
			Reporters.SuccessReport("verification of 'Continue Without A Selection' button ", "Sessions Tab is displayed with Sessions and Drop-In-Labs filter,Advanced Search link,You've Selected and Narrow Your Results sections,all sessions");
		}
		else{
			Reporters.failureReport("verification of 'Continue Without A Selection' button ", "Sessions Tab is not displayed with Sessions and Drop-In-Labs filter,Advanced Search link,You've Selected and Narrow Your Results sections,all sessions");
		}

		if(checkDontShowThisMessage())
		{
			Reporters.SuccessReport("verification of 'Dont Show This Message' check box ", "Select Area pop-up is not displayed");
		}
		else{
			Reporters.failureReport("verification of 'Dont Show This Message' check box ", "Select Area pop-up is displayed");
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
