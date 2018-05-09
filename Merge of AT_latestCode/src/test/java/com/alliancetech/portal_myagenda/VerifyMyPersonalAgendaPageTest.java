package com.alliancetech.portal_myagenda;
/**
 * This Test Case covers all the validations mentioned in 
 * reference sheet- iConnect-My Agenda(My Personal Agenda)
 */

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class VerifyMyPersonalAgendaPageTest extends BusinessFunctions{

	@Test
	public void verifyMyPersonalAgendaPage() throws Throwable
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

		if(sessionFilterUncheckSession())
		{
			Reporters.SuccessReport("verification of sessionFilter unselection ", "Sessions are not displayed in the calendar graph");
		}
		else{
			Reporters.failureReport("verification of sessionFilter unselection ", "sessionFilter unselection is failed");
		}

		if(sessionFilterCheckSession())
		{
			Reporters.SuccessReport("verification of sessionFilter selection ", "Sessions are displayed in the calendar graph");
		}
		else{
			Reporters.failureReport("verification of sessionFilter selection ", "sessions are not displayed in Calender Graph");
		}

		if(addSession())
		{
			Reporters.SuccessReport("Verification of Add Session icon", "Session page is opened");
		}
		else{
			Reporters.failureReport("Verification of Add Session icon", "Session page is not opened");
		}

		if(addSessionDisplay())
		{
			Reporters.SuccessReport("Verification of a session that is added to My Agenda in Sessions Tab ", "Session that is added is displayed in Calender graph");
		}
		else{
			Reporters.failureReport("Verification of a session that is added to My Agenda in Sessions Tab ", "Session that is added is not displayed in Calender graph");
		}

		if(removeSession())
		{
			Reporters.SuccessReport("Verification of a session that is removed from My Agenda in Sessions Tab", "Sessions that is removed is not displayed in Calender Graph");
		}
		else{
			Reporters.failureReport("Verification of a session that is removed from My Agenda in Sessions Tab", "Sessions that is removed is still displaying in Calender Graph");
		}

		if(printOperation())
		{
			Reporters.SuccessReport("Verification of print icon", "Print pop-up is displayed with Full Or Summary Version title,Full Version and Summary radio buttons and Print button");
		}
		else{
			Reporters.failureReport("Verification of print icon", "Print pop-up is displayed with Full Or Summary Version title,Full Version and Summary radio buttons and Print button");
		}

		if(printFullVersion())
		{
			Reporters.SuccessReport("Verification of Full Version in Print Pop Up", "PDF with full version of the items added in the calendar graph is printed.");
		}
		else
		{
			Reporters.failureReport("Verification of Full Version in Print Pop Up", "PDF with full version of the items added in the calendar graph is not printed.");
		}

		if(printSummary())
		{
			Reporters.SuccessReport("Verification of Summary in Print Pop Up", "PDF with summary version of the items added in the calendar graph is printed.");
		}
		else
		{
			Reporters.failureReport("printing summary is", "PDF with summary version of the items added in the calendar graph is not printed.");
		}

		if(emailOperation())
		{
			Reporters.SuccessReport("Verification of email icon", "Email pop-up is displayed with Select preferred email address title,User email, Full Version and Summary radio buttons and Send Button");
		}
		else
		{
			Reporters.failureReport("Verification of email icon", "Email pop-up is not displayed with Select preferred email address title,Full Version and Summary radio buttons and Send Button");
		}

		if(emailFullVersion())
		{
			Reporters.SuccessReport("Verification of Full Version in Email Pop Up", "PDF with full version of the items added in the calendar graph is send.");
		}
		else
		{
			Reporters.failureReport("Verification of Full Version in Email Pop Up", "PDF with full version of the items added in the calendar graph is not sent.");
		}

		if(emailSummary())
		{
			Reporters.SuccessReport("Verification of Summary in Email Pop Up", "PDF with summary version of the items added in the calendar graph is send.");
		}
		else
		{
			Reporters.failureReport("Verification of Summary in Email Pop Up", "PDF with summary version of the items added in the calendar graph is not sent.");
		}

		if(downloadOperation())
		{
			Reporters.SuccessReport("Verification of Download icon", "Download pop-up is displayed with Download Scheduled Event title,Download To Calendar and Email as an Attachment buttons");
		}
		else
		{
			Reporters.failureReport("Verification of Download icon", "Download pop-up is not displayed with Download Scheduled Event title,Download To Calendar and Email as an Attachment buttons");
		}

		if(downloadToCalender())
		{
			Reporters.SuccessReport("Verification of Download To Calender in Download Pop Up", "Calendar graph is downloaded to the system calendar.");
		}
		else
		{
			Reporters.failureReport("Verification of Download To Calender in Download Pop Up", "Calendar graph is not downloaded to the system calendar.");
		}

		if(emailAsAttachemntOperation())
		{
			Reporters.SuccessReport("Verification of emailAsAttachemnt button", "'To' section is displayed with User email added by default,Click here to add additional link and Send Event");
		}
		else
		{
			Reporters.failureReport("Verification of emailAsAttachemnt button", "'To' section is not displayed with User email added by default,Click here to add additional link and Send Event");
		}

		if(emailAsAttachmentToCalender())
		{
			Reporters.SuccessReport("Verification of Email As An Attachement after completion of 'To' section", "Calendar graph is send to the email provided.");
		}
		else
		{
			Reporters.failureReport("Email As An Attachment is after completion of 'To' section", "Calendar graph is not sent to the email provided.");
		}


		if(weekVerification())
		{
			Reporters.SuccessReport("Verification of Week", "Calendar graph is displayed the elements of all the week.");
		}
		else
		{
			Reporters.failureReport("Verification of Week", "Calendar graph is not displayed the elements of all the week.");
		}

		if(dayVerification())
		{
			Reporters.SuccessReport("Verification of day", "Calendar graph is displayed with the elements of the day.");
		}
		else
		{
			Reporters.failureReport("Verification of day", "Calendar graph is not displayed with the elements of the day.");
		}

		if(sessionDetailsPopUp())
		{
			Reporters.SuccessReport("Verification of session selected from the calender graph", "Session Details pop-up is displayed.");
		}
		else
		{
			Reporters.failureReport("Verification of session selected from the calender graph", "Session Details pop-up is not displayed.");
		}	

		if(emtLogOut())
		{
			Reporters.SuccessReport("Verification of log out", "Application logged out successfully");
		}
		else{
			Reporters.failureReport("Verification of log out", "Application failed to log out");
		}
	}
}
