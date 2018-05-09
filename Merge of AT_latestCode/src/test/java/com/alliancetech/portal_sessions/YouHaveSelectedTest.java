package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies all the validations mentioned in
 * sheet- iConnect-Sessions(You have Selected)
 */


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class YouHaveSelectedTest extends BusinessFunctions{

	@Test
	public void youSelected() throws Throwable {

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


		/*if(sessionSearch())
		{
			Reporters.SuccessReport("verification of 'x' icon", "You've Selection section becomes empty and Sessions List dislpays all sessions registered in the server");
		}
	    else{
			Reporters.failureReport("verification of 'x' icon", "You've Selection section is not empty and Sessions List dislpays all sessions registered in the server");
		}*/

		if(select_x_icon())
		{
			Reporters.SuccessReport("verification of 'x' icon", "You've Selection section becomes empty and Sessions List dislpays all sessions registered in the server");
		}
		else{
			Reporters.failureReport("verification of 'x' icon", "You've Selection section is not empty and Sessions List dislpays all sessions registered in the server");
		}

		if(selectClearAll())
		{
			Reporters.SuccessReport("verification of Clear All link", "You've Selection section becomes empty and Sessions List dislpays all sessions registered in the server.");
		}
		else{
			Reporters.failureReport("verification of Clear All link", "You've Selection section becomesis not empty and Sessions List dislpays all sessions registered in the server.");
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
