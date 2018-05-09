package com.alliancetech.checkIn;



import java.io.IOException;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.cigniti.automation.utilities.Reporters;

public class IntegrationSetUpTest extends BusinessFunctions{

	@Test(groups={"checkin","RunAll"})
	public void EMT_IntegrationSetUp() throws Throwable, IOException{

		if(getURL(configProps.getProperty("Slave1EMT_URL")))
		{
			Reporters.SuccessReport("Launch Slave EMT Application "+configProps.getProperty("Slave1EMT_URL"), "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch Slave EMT Application "+configProps.getProperty("Slave1EMT_URL"), "Application URL failed to launch ");
		}

		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");

		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		clickTabFromViewMore(CommonOR.lnkAdmin, "Administration Tab");

		click(CheckInOR.lnkManageIntegration, "Manage Integration");
		
		
		integrationSetup(CheckInOR.lnkRegistration, "Registrantion link", CheckInOR.lblEnabled, "Registration Enabled Status");
		
		if(verifyText(CheckInOR.lblEnabled, configProps.getProperty("Enabled"), "Enabled Status")){
			Reporters.SuccessReport("Integration SetUp", "Integration Setup is done successfully ");
		}
		else{
			Reporters.failureReport("Integration SetUp", "Integration Setup is not done successfully ");
		}
		
		integrationSetup(CheckInOR.lnkCheckIn, "Checkin link", CheckInOR.lblcheckinEnabled, "CheckIn Enabled Status");
		
		if(verifyText(CheckInOR.lblcheckinEnabled, configProps.getProperty("Enabled"), "Enabled Status")){
			Reporters.SuccessReport("Integration SetUp", "Integration Setup is done successfully ");
		}
		else{
			Reporters.failureReport("Integration SetUp", "Integration Setup is not done successfully ");
		}

		if(emtLogOut())
		{
			Reporters.SuccessReport("Log out from Application", "Application logged out successfully");
		}
		else{
			Reporters.failureReport("Log out from Application", "Application failed to log out");
		}

		if(verifyTextPresent("Forgot your password?")){
			Reporters.SuccessReport("Verify Log Out", "User has been Logged Out Successfully From EMT");
		}
		else{
			Reporters.failureReport("Verify Log Out", "Log out verification has been failed ");
		}   
		
		driver.manage().deleteAllCookies();
		
		if(getURL(configProps.getProperty("Slave2EMT_URL")))
		{
			Reporters.SuccessReport("Launch Slave EMT Application "+configProps.getProperty("Slave2EMT_URL"), "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch Slave EMT Application "+configProps.getProperty("Slave2EMT_URL"), "Application URL failed to launch ");
		}

		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		clickTabFromViewMore(CommonOR.lnkAdmin, "Administration Tab");
		
		click(CheckInOR.lnkManageIntegration, "Manage Integration");

		integrationSetup(CheckInOR.lnkRegistration, "Registrantion link", CheckInOR.lblEnabled, "Registration Enabled Status");
		
		if(verifyText(CheckInOR.lblEnabled, configProps.getProperty("Enabled"), "Enabled Status")){
			Reporters.SuccessReport("Integration SetUp", "Integration Setup is done successfully ");
		}
		else{
			Reporters.failureReport("Integration SetUp", "Integration Setup is not done successfully ");
		}

		integrationSetup(CheckInOR.lnkCheckIn, "Checkin link", CheckInOR.lblcheckinEnabled, "CheckIn Enabled Status");
		
		if(verifyText(CheckInOR.lblcheckinEnabled, configProps.getProperty("Enabled"), "Checkin Enabled Status")){
			Reporters.SuccessReport("Integration SetUp", "Integration Setup is done successfully ");
		}
		else{
			Reporters.failureReport("Integration SetUp", "Integration Setup is not done successfully ");
		}

		if(emtLogOut())
		{
			Reporters.SuccessReport("Log out from Application", "Application logged out successfully");
		}
		else{
			Reporters.failureReport("Log out from Application", "Application failed to log out");
		}

		if(verifyTextPresent("Forgot your password?")){
			Reporters.SuccessReport("Verify Log Out", "User has been Logged Out Successfully From EMT");
		}
		else{
			Reporters.failureReport("Verify Log Out", "Log out verification has been failed");
		}   

	}

}
