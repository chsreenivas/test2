package com.alliancetech.portal_myagenda;

/**
 * This Test Case verifies the My Agenda screen elements
 * reference sheet- iConnect-My Agenda(My Agenda)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class VerifyMyAgendaScreenTest extends BusinessFunctions{

	@Test(dataProvider = ("getAnswers"))
	public void verify_MyAgendaScreen() throws Throwable
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

		if(verifyMyAgendaScreen())
		{
			Reporters.SuccessReport("verification of My Agenda screen displays ", "Agenda Screen is displayed with all elements: Meal Hours, Expo Hours and General Sessions & Keynotes tabs. Sessions, Meeting and Personal Events filters. Add Sessions, Add Meeting, Add Personal Events, Print, Email and Download icons. Week and Day options. My Personal Agenda (selected by default) and Exhibitors Agenda Items links. Calendar graph.");
		}
		else{
			Reporters.failureReport("verification of My Agenda screen not displays ", "Agenda Screen is displayed with all elements: Meal Hours, Expo Hours and General Sessions & Keynotes tabs. Sessions, Meeting and Personal Events filters. Add Sessions, Add Meeting, Add Personal Events, Print, Email and Download icons. Week and Day options. My Personal Agenda (selected by default) and Exhibitors Agenda Items links. Calendar graph.");
		}
		if(verifyMyAgendaMealHours())
		{
			Reporters.SuccessReport("verification of My Agenda Meal Hours ", "Meal Hour displays: Breakfast, Lunch and Receptions");
		}
		else{
			Reporters.failureReport("verification of My Agenda Meal Hours ", "Meal Hour displays: Breakfast, Lunch and Receptions");
		}
		if(verifyMyAgendaExpoHours())
		{
			Reporters.SuccessReport("verification of My Agenda Expo Hours", "Expo Hour displays:'EXPO is accessible' and 'EXPO is fully staffed'");
		}
		else{
			Reporters.failureReport("verification of My Agenda Expo Hours ", "Expo Hour displays:'EXPO is accessible' and 'EXPO is fully staffed'");
		}
		if(verifyMyAgendaGeneralSessions())
		{
			Reporters.SuccessReport("verification of My Agenda General Sessions ", "General Sessions displays: Hour and title information of the sessions");
		}
		else{
			Reporters.failureReport("verification of My Agenda General Sessions ", "General Sessions displays: Hour and title information of the sessions");
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
