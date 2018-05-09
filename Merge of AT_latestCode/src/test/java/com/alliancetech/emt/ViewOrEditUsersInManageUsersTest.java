package com.alliancetech.emt;
/**
 * This Test Case verifies
 * i) Manage Users Link
 * ii) User Profiles
 * iii) Next Link
 * iv) Profile Filter
 * v) Login user's profile
 * vi) Any System Admin user warning message 
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class ViewOrEditUsersInManageUsersTest extends BusinessFunctions{

	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void EMT_View_Edit_Users(String uname, String password,String user) throws Throwable {

		try{
			if(logIn==0)
			{
				if(getEMTURL())
				{
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
				}

				if(emtLogIn())
				{
					Reporters.SuccessReport("Login into the Application", "login is successfull");
					logIn++;
				}

				else{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}

			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin,"Admin Tab")){
			Reporters.SuccessReport("Verify Page", "Admin page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Admin page is not displayed");
		}

		verifyText(XML_OR.txtAdminPageTitle, "Admin Section", "Admin Page");

		if(lnkManageUsers())
		{
			Reporters.SuccessReport("Manage Users in Admin Tab", "Manage Users page displayed");
		}
		else
		{
			Reporters.failureReport("Manage Users in Admin Tab", "Manage Users page not displayed");
		}

		if(verifyUserProfile())
		{
			Reporters.SuccessReport("Verify a user profile in View/Edit User page", "User profile is verified successfully");
		}
		else
		{
			Reporters.failureReport("Verify a user profile in View/Edit User page", "User profile verification failed");
		}

		if(btnNext())
		{
			Reporters.SuccessReport("Verify next button", "Displayed Next User Profile Details");
		}
		else
		{
			Reporters.failureReport("Verify next button", "Failed to display Next User Profile Details");
		}

		if(filterProfile())
		{
			Reporters.SuccessReport("Verify next button", "Displayed Next User Profile Details");
		}
		else
		{
			Reporters.failureReport("Verify next button", "Failed to display Next User Profile Details");
		}

		if(loginUserProfile(uname))
		{
			Reporters.SuccessReport("Verify Login user profile "+uname+"", "Warning message is not displayed");
		}
		else
		{
			Reporters.failureReport("Verify Login user profile "+uname+"", "Warning message is displayed");
		}

		if(sysAdminWarningMessage(user))
		{
			Reporters.SuccessReport("Verify user "+user+" with System Administrator profile", "Warning message is displayed");
		}
		else
		{
			Reporters.failureReport("Verifyuser "+user+" with System Administrator profile", "Warning message is not displayed");
		}
	}


	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("EMTViewOrEditUser", "Key_ViewOrEditUsers"); //returning data from "EMTViewOrEditUser" sheet and "ProductFinder" as a reference to read data
	}

}



