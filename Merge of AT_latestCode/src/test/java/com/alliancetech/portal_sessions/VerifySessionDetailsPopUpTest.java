package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet - iConnect-Sessions(Session Details PopUp)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class VerifySessionDetailsPopUpTest extends BusinessFunctions{

	@Test
	public void verify_sessionDetailsPopUp() throws Throwable{

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

		if(sessionDetails())
		{
			Reporters.SuccessReport("Verification of Session Details", "Session Details pop-up is displayed with Session Information(details,desc,speakers),Add to Agenda button,Email Session Detail, Print Session Detail and Download Session Detail links");
		}
		else{
			Reporters.failureReport("Verification of session details", "Session Details pop-up is not displayed with Session Information(details,desc,speakers),Add to Agenda button,Email Session Detail, Print Session Detail and Download Session Detail links");
		}

		if(addToAgendaMessage())
		{
			Reporters.SuccessReport("Verification of Add To Agenda Button", "'Session added to agenda!!' message is displayed.");
		}
		else{
			Reporters.failureReport("Verification of Add To Agenda Button", "'Session added to agenda!!' message is not displayed.");
		}

		if(statusMessage())
		{
			Reporters.SuccessReport("Verification of status", "Session Status displayed is: Confirmed.");
		}
		else{
			Reporters.failureReport("Verification of status", "Session Status displayed is not: Confirmed.");
		}

		if(removeFromAgendaMessage())
		{
			Reporters.SuccessReport("Verification of Remove From Agenda Button", "'Session removed from agenda!!' message is displayed.");
		}
		else{
			Reporters.failureReport("Verification of Add To Agenda Button", "'Session removed from agenda!!' message is not displayed.");
		}

		if(statusMessageAfterRemoveFromAgenda())
		{
			Reporters.SuccessReport("Verification of status", "Session Status displayed is: NA");
		}
		else{
			Reporters.failureReport("Verification of status", "Session Status displayed is not: NA");
		}

		if(emailPopUpValidation())
		{
			Reporters.SuccessReport("Verification of email link", "Email pop-up is displayed with 'Select preferred email address' in the header,Actual user email address,Send and Close buttons.");
		}
		else{
			Reporters.failureReport("Verification of email link", "Email pop-up is not displayed with 'Select preferred email address' in the header,Actual user email address,Send and Close buttons.");
		}

		if(sendButtonValidation())
		{
			Reporters.SuccessReport("Verification of send button", "An info pop-up is displayed with  'Info' in the header,'Session details have been sent to the email provided!' message,Ok button.");
		}
		else{
			Reporters.failureReport("Verification of email link", "An info pop-up is displayed with  'Info' in the header,'Session details have been sent to the email provided!' message,Ok button .");
		}

		if(printSession())
		{
			Reporters.SuccessReport("Verification of print link", "session details PDF is downloaded.");
		}
		else{
			Reporters.failureReport("Verification of email link", "session details PDF is not downloaded..");
		}

		if(downloadSessionValidation())
		{
			Reporters.SuccessReport("Verification of download link", "Download Scheduled Event pop-up is displayed with Download to Calendar and Email as an Attachment button");
		}
		else{
			Reporters.failureReport("Verification of download link", "Download Scheduled Event pop-up is not displayed with Download to Calendar and Email as an Attachment button");
		}

		if(downloadToCalenderInSessionDetails())
		{
			Reporters.SuccessReport("Verification of Download To Calender button", "Session Event is downloaded in ICS file format");
		}
		else{
			Reporters.failureReport("Verification of Download To Calender button", "Session Event is downloaded in ICS file format");
		}

		if(emailAsAnAttachmentValidation())
		{
			Reporters.SuccessReport("Verification of Email As An Attachment button", "To field is auto-filled with user email address and more email addresses can be added link is present");
		}
		else{
			Reporters.failureReport("Verification of Email As An Attachment button", "To field is not auto-filled with user email address and more email addresses can be added link is not present");
		}

		if(sendEvent())
		{
			Reporters.SuccessReport("Verification of Send Event button", "An info pop-up is displayed with 'Info' in the header,'Events have been sent to the email provided!!' message,Ok button");
		}
		else{
			Reporters.failureReport("Verification of Send Event button", "An info pop-up is not displayed with 'Info' in the header,'Events have been sent to the email provided!!' message,Ok button");
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
