package com.alliancetech.emt;
/**
 * This Test Case verifies adding of Users in Manager Users
 * reference Test rail-iConnect_EMT(Manage Users)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddUserInManageUsersTest extends BusinessFunctions {

	public static int logIn=0;	 

	@Test(dataProvider = ("getAnswers"),groups= {"EMT", "RunAll"})
	public void EMT_AddUser(String FirstName, String LastName, String Email, String LoginId, String profileType,String Password) throws Throwable{

		try
		{
			if(logIn==0)
			{
				if(getEMTURL())
				{
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch");
				}

				if(emtLogIn())
				{
					Reporters.SuccessReport("Login into the Application", "login is successfull");
					logIn++;
				}
				else
				{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Admin Tab")){
			Reporters.SuccessReport("Verify Page", "Admin page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Admin page is not displayed");
		}

		verifyText(XML_OR.txtAdminPageTitle, "Admin Section", "Admin Page");

		if(ManageUserLink()){
			Reporters.SuccessReport("Verify Page", "Manage Page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Admin page is not displayed");
		}

		if(AddUserLink()){
			Reporters.SuccessReport("Verify Add User link", "Add New Speaker page is displayed");
		}

		else{
			Reporters.failureReport("Verify Add User link", "Add New Speaker page is not displayed");
		}

		if(AddUserDetails(FirstName, LastName, Email, LoginId)){
			Reporters.SuccessReport("Enter New Speaker details", "Speaker "+FirstName+" added successfully");
		}

		else{
			Reporters.failureReport("Enter New Speaker details", "Speaker "+FirstName+" is not added");
		}
		
		if(SelectUserProfile(profileType)){
			Reporters.SuccessReport("Select User Profile", ""+profileType+" is selected successfully");
		}
		else{
			Reporters.failureReport("Select User Profile", ""+profileType+" is not selected");
		}

		/*click(EMT_AddUserInManageUsers_OR.btnSubmit,"Submit Button");

		if(compareSuccessMessage(FirstName, LastName))
		{
			Reporters.SuccessReport("Verify the Success Message", "User is successfully created and Success message is "+text);
		}
		else
		{
			Reporters.failureReport("Verify the Message", "Unable to create the user and Error Message displayed is "+text);
		}
		
		getEMTURL();
		emtLogout=true;*/
	}


	@DataProvider
	public Object[][] getAnswers() throws Exception {

		return Data_Provider.getTableArray("EMTAddUserInManageUsers", "Key_AddUser");  //returning data from "EMTAddUserInManageUsers" sheet and "Key_AddUser" as a reference to read data
	}
	
	@Test(dataProvider = ("getPassword"),groups= {"EMT", "RunAll"},priority=1)
	public void VerifyPassword(String Password,String errorMessage) throws Throwable
	{
		if(checkPasswords(Password,errorMessage)){
			Reporters.SuccessReport("Enter Password", ""+Password+" in Password field");
		}
		else{
			Reporters.failureReport("Enter Password", ""+Password+" in Password field");
		}
		
		emtLogout=true;
	}
	
	@DataProvider
	public Object[][] getPassword() throws Exception {
		return Data_Provider.getTableArray("EMTAddUserInManageUsers", "Key_Passwords");  //returning data from "EMTAddUserInManageUsers" sheet and "Key_AddUser" as a reference to read data
	}
}
