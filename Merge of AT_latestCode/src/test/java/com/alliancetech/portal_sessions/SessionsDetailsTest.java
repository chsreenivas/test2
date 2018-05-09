package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet - iConnect-Sessions(Session Details)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class SessionsDetailsTest extends BusinessFunctions {

	@Test
	public void AddAndRemoveAgenda() throws Throwable {

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

		if(sessionDetailsVerification())
		{
			Reporters.SuccessReport("Verification of Session Details", "Session Details, View Session Details and Add to Agenda are displayed");
		}
		else{
			Reporters.failureReport("Verification of Session Details", "Session Details are not displayed");
		}
		if(AddAgendaClick())

		{	
			Reporters.SuccessReport("Session Added to Agenda ", "Session Added to Agenda Message is Displayed");
		}
		else{
			Reporters.failureReport("Session Added to Agenda ", "Session Added to Agenda Message is not Displayed");
		}

		if(StatusConfirmed())
		{	
			Reporters.SuccessReport("Session Added to Agenda and Status confirmed", "Status confirmed Message is Displayed");
		}
		else{
			Reporters.failureReport("Session Added to Agenda and Status confirmed", "Status confirmed is not Displayed");
		}

		if(RemoveFromAgenda())
		{	
			Reporters.SuccessReport("Session Removed from Agenda ", "Session Removed From MyAgenda message is Displayed");
		}

		else{
			Reporters.failureReport("Session Added to Agenda and Status confirmed", "Session Removed From MyAgenda not Displayed");
		}


		/* if(NoStatus())
	 {	
        Reporters.SuccessReport("No Status message after Session Removed from Agenda ", "Session Removed From MyAgenda and No Status message is Displayed");
		}

   else{
    Reporters.failureReport("No Status message after Session Removed from Agenda", "Failed to Remove Session From MyAgenda");
		}*/

		if(SessoinsDetailsPopup())
		{	
			Reporters.SuccessReport("View Sessions Details Button clicked", "Session Details popup window is Displayed");
		}
		else{
			Reporters.failureReport("View Sessions Details Button clicked", "Session Details popup window is not Displayed");
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

