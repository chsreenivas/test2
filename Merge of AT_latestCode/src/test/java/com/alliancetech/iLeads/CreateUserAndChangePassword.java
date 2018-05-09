package com.alliancetech.iLeads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CreateUserAndChangePassword extends BusinessFunctions{
  
	
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups={"iLeads","RunAll"})
	public void CreateUserAndChangePassword(String Username,String FirstName,String LastName,String Password,String PasswordConfirm,String SecurityRole) throws Throwable {
	
	try{
		if(logIn==0)
		{
			if(getiLeadsURL())
			{
				Reporters.SuccessReport("Launch iLeads Application", "Application URL launched successfully");
			}
			else
			{
				Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
			}

			if(iLeadsLogIn())
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
	
	if(Navigate_Manage_Users())
	{
		Reporters.SuccessReport("Naviagte to Manage users", "Naviagted Successfully");
	}
	else
	{
		Reporters.failureReport("Naviagte to Manage users", "Did not Naviagted Successfully");
	}
	
	if(create_Manage_User())
	{
		Reporters.SuccessReport("Verify Page", "Create Manage user button is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Create Manage user button is not displayed");
	}
 
	if(addUserInformation( Username, Password,PasswordConfirm,FirstName,LastName,SecurityRole))
	{
		Reporters.SuccessReport("Add User Details", "User "+FirstName+" details got added");
	}
	else{
		Reporters.failureReport("Add User Details", "User "+FirstName+" details is not added");
	}
	
	if(SelectSecurityRole(SecurityRole))
		
	{
		Reporters.SuccessReport("Add User Details", "User "+SecurityRole+" details got added");
	}
	else{
		Reporters.failureReport("Add User Details", "User "+SecurityRole+" details is not added");
	}
	
    /* if(UserAlreadyExists())
      {
		Reporters.SuccessReport("Verify the user exists", "Verified Successfully");
	}
	else
	{
		Reporters.failureReport("Verify the user exists", "Did not Verified Successfully");
	}
*/
	if(searchForUser(Username))
	{
		Reporters.SuccessReport("Search for user created ", "User with tag "+FirstName+" is available");
	}
	else{
		Reporters.failureReport("Search for user created", " User  with tag "+FirstName+" is not available");
	}
	
	if(renameThePassword(Password))
	{
		Reporters.SuccessReport("Change the password ", "User with tag "+Password+" is available");
	}
	else{
		Reporters.failureReport("Change the password", " User  with tag "+Password+" is not available");
	} 
	if(renameThePasswordconfirm(PasswordConfirm))
	{
		Reporters.SuccessReport("Change the password ", "User with tag "+PasswordConfirm+" is available");
	}
	else{
		Reporters.failureReport("Change the password", " User  with tag "+PasswordConfirm+" is not available");
	}
	
	if(iLeadsLogOut())
		{
			Reporters.SuccessReport("Log out from iLeads application", "Logged out from the application successfuly");
		}
		else{
			Reporters.failureReport("Log out from iLeads application", "Failed to log out from the application");
		}
		
			
}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("IL_CreateUserAnsPassword", "Key_Create_User_And_Change_Password");  //returning data from "CreateAndDeleteAnEvent" sheet and "Key_Create_And_Delete_An_Event" as a reference to read data
	}
}
	
	



	


