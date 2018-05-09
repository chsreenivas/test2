package com.alliancetech.emt;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_LockAndUnlockUserAccount;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class lockTheUserAccount extends BusinessFunctions{
  @Test(dataProvider = ("getAnswers"),groups= {"EMT", "RunAll"},priority=0)
  public void lockTheAccount(String loginId,String Password,String Message) throws Throwable {
	  if(getEMTURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch");
		}
	  if(lockAccount(loginId,Password,Message))
	  {
		  Reporters.SuccessReport("Lock the user account", "Successfully locked the user "+loginId+" account");
	  }
	  else
	  {
		  Reporters.failureReport("Lock the user account", "Failed to lock user "+loginId+" account");
	  }
  }
  
  @DataProvider
	public Object[][] getAnswers() throws Exception {

		return Data_Provider.getTableArray("EMTAddUserInManageUsers", "Key_LockUser");  //returning data from "EMTAddUserInManageUsers" sheet and "Key_AddUser" as a reference to read data
	}
  
  @Test(dataProvider = ("getLockedAccountDetails"),groups= {"EMT", "RunAll"},priority=1)
  public void verifyUserLockedAccount(String FirstName, String LastName, String Email, String LoginId, String profileType,String Password) throws Throwable
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
	    }
		else
		{
			Reporters.failureReport("Login into the Application", "login is not successfull");
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
		
		click(EMT_LockAndUnlockUserAccount.lnkNumberOfRecords,"Number of Records");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		
		click(EMT_LockAndUnlockUserAccount.lnk500Records,"Number of Records to display");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		
		click(EMT_LockAndUnlockUserAccount.viewUserDetails(LoginId),"User with login id "+LoginId);
		
		if(verifyUserLoginInfoAfterAccountLocked())
		{
			Reporters.SuccessReport("Verify user "+LoginId+" login information after his account is locked", "Successfully verified the login information");
		}
		else
		{
			Reporters.failureReport("Verify user "+LoginId+" login information after his account is locked", "Failed to verify the login information");
		}
		
		getEMTURL();
		emtLogout=true;
  }
  
  @DataProvider
	public Object[][] getLockedAccountDetails() throws Exception {

		return Data_Provider.getTableArray("EMTAddUserInManageUsers", "Key_AddUser");  //returning data from "EMTAddUserInManageUsers" sheet and "Key_AddUser" as a reference to read data
	}
}
