package com.alliancetech.emt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_LockAndUnlockUserAccount;
import com.alliancetech.objectrepository.EMT_LogInOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class UnlockTheUserAccount extends BusinessFunctions{
	public static int logIn=0;	
  @Test(dataProvider="getAnswers",groups={"EMT","RunAll"})
  public void unlockUserAccount(String FirstName, String LastName, String Email, String LoginId, String profileType,String Password) throws Throwable {
	  
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
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		
		click(EMT_LockAndUnlockUserAccount.viewUserDetails(LoginId),"User with login id "+LoginId);
		
		if(unlockTheUserAccount())
		{
			Reporters.SuccessReport("Unlock the user account and verify", "Successfully unlocked user account");
		}
		else
		{
			Reporters.failureReport("Unlock the user account and verify", "Failed to unlock user account");
		}
		
		if(emtLogOut())
		{
			Reporters.SuccessReport("Log out from EMT Application", "Succesfully logged out");
		}
		else
		{
			Reporters.failureReport("Log out from EMT Application", "Failed to log out");
		}
		
		getEMTURL();
		
		type(EMT_LogInOR.txtusername,LoginId,"User name");
		
		type(EMT_LogInOR.txtPassword,Password,"Password");
		
		click(EMT_LogInOR.btnLogin,"Login Button");
		
		if(verifyUserInHomePage(FirstName, LastName))
		{
			Reporters.SuccessReport("Verify user name in Home page", "User succesfully logged in after unlocking his account");
		}
		else
		{
			Reporters.failureReport("Verify user name in Home page", "User failed to log in after unlocking his account");
		}
		
		getEMTURL();
		emtLogout=true;
  }

@DataProvider
public Object[][] getAnswers() throws Exception {

	return Data_Provider.getTableArray("EMTAddUserInManageUsers", "Key_AddUser");  //returning data from "EMTAddUserInManageUsers" sheet and "Key_AddUser" as a reference to read data
}
}