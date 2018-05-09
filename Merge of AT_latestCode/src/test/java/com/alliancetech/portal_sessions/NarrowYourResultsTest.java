package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet - iConnect-Sessions(Narrow your Results)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class NarrowYourResultsTest extends BusinessFunctions{

	@Test
	public void VerifyNarrowYourResults() throws Throwable {

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

		if(updatedYouHaveSelected())
		{
			Reporters.SuccessReport("Verification of an option selected in Narrow Your Results section", "Sessions List displays the sessions match with the selection made in Narrow Your Results.");
		}
		else{
			Reporters.failureReport("Verification of an option selected in Narrow Your Results", "Sessions List displays the sessions doesnot match with the selection made in Narrow Your Results.");
		}

		if(updatedSessionList())
		{
			Reporters.SuccessReport("Verification of an option selected in Narrow Your Results", "You've Selected section show the new search executed.");
		}
		else{
			Reporters.failureReport("Verification of an option selected in Narrow Your Results", "You've Selected section is not displaying the new search executed.");
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
