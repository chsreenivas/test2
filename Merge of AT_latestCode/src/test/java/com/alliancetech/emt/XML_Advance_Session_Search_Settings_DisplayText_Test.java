package com.alliancetech.emt;
/**
 * This Test Case is used to verify that
 * if value of <display Text> attribute in <advance-session-search-settings> tag is edited, the change is reflected in Portal - Advanced Search - Sessions.
 * reference sheet- iConnect-Sessions(XML-<advance-session-search-settings>)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class XML_Advance_Session_Search_Settings_DisplayText_Test extends BusinessFunctions{

	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"Portal", "RunAll"})
	public void advance_Session_Search_Settings_DisplayText(String displayText,String usage,String originalDisplayText) throws Throwable {

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

		click(XML_OR.lnkPortal, "Portal Link");

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.txtTagAdvanceSearchSettings(usage))){
			Reporters.SuccessReport("Remove advance-session-search-setting Tag for usage value "+usage+"", "advance-session-search-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove advance-session-search-setting Tag for usage value "+usage+"", "advance-session-search-setting Tag is not removed");
		}

		if(insertAdvanceSearchSettingsDisplayText(displayText,usage)){
			Reporters.SuccessReport("Insert advance-session-search-setting Tag for usage value "+usage+" with display Text as "+displayText+"", "advance-session-search-setting tag is inserted successfully");
		}
		else
		{
			Reporters.failureReport("Insert advance-session-search-setting Tag for usage value "+usage+" with display Text as "+displayText+"", "advance-session-search-setting Tag is not inserted");
		}

		clickSaveXMLFileButton();

		verifyTextPresent("portal_settings.xml has been saved successfully!");

		if(openSiteInNewWindow("Intelligent CONNECT")){
			getPortalURL();
			Reporters.SuccessReport("Open New Tab and Navigate to Portal", "New Tab has been opened and Navigated to Portal Successfully");
		}
		else
		{
			Reporters.failureReport("Open New Tab and Navigate to Portal", "Unable to open new tab and navigate to portal");
		}

		verifyTextPresent("Sign In");

		if(Portal_Login()){
			Reporters.SuccessReport("Login into Portal Application", "User has been logged into Portal");
		}
		else{
			Reporters.failureReport("Login into Portal Application", "Unable to Login into Portal");
		}

		waitForVisibilityOfElement(XML_OR.txtPortalWelcome,"Welcome");

		verifyText(XML_OR.txtPortalWelcome, "Welcome, ", "Welcome");

		if(ClickSessionIconInPortal()){
			Reporters.SuccessReport("The Sessions page appears", "The sessions page successfully displayed");
		}
		else{

			Reporters.failureReport("The Sessions page appears ", "The sessions page is not displayed");
		}

		click(XML_OR.lnkAdvanceSearch, "Advance Settings link");

		verifyTextPresent(displayText);

		if(logoutFromPortal()){
			Reporters.SuccessReport("Verify Log out Status", "User has been Logged out of Portal Successfully");
		}
		else{
			Reporters.failureReport("Verify Log out Status", "Log out is unsuccessful");
		}

		if(closeWindow("Reporting Site",1))
		{
			Reporters.SuccessReport("Close the Portal Tab", "Portal Tab is closed");
		}
		else
		{
			Reporters.failureReport("Close the Portal Tab", "Unable to close the Portal Tab");
		}

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.txtTagAdvanceSearchSettings(originalDisplayText))){
			Reporters.SuccessReport("Remove advance-session-search-setting Tag for usage value "+usage+"", "advance-session-search-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove advance-session-search-setting Tag for usage value "+usage+"", "advance-session-search-setting Tag is not removed");
		}

		if(insertAdvanceSearchSettingsDisplayText(originalDisplayText,usage)){
			Reporters.SuccessReport("Insert advance-session-search-setting Tag for usage value "+usage+" with default display text as:"+originalDisplayText+"", "advance-session-search-setting tag is inserted successfully");
		}
		else
		{
			Reporters.failureReport("Insert advance-session-search-setting Tag for usage value "+usage+" with default display text as:"+originalDisplayText+"", "advance-session-search-setting Tag is not inserted");
		}

		clickSaveXMLFileButton();

		verifyTextPresent("portal_settings.xml has been saved successfully!");

		if(openSiteInNewWindow("Intelligent CONNECT")){
			Reporters.SuccessReport("Open New Tab and Navigate to Portal", "New Tab has been opened and Navigated to Portal Successfully");
		}
		else
		{
			Reporters.failureReport("Open New Tab and Navigate to Portal", "Unable to open new tab and navigate to portal");
		}

		verifyTextPresent("Sign In");

		if(Portal_Login()){
			Reporters.SuccessReport("Login into Portal Application", "User has been logged into Portal");
		}
		else{
			Reporters.failureReport("Login into Portal Application", "Unable to Login into Portal");
		}

		waitForElementPresent(XML_OR.txtPortalWelcome);

		verifyText(XML_OR.txtPortalWelcome, "Welcome, ", "Welcome");

		if(ClickSessionIconInPortal()){
			Reporters.SuccessReport("The Sessions page appears", "The sessions page successfully displayed");
		}
		else{

			Reporters.failureReport("The Sessions page appears ", "The sessions page is not displayed");
		}

		click(XML_OR.lnkAdvanceSearch, "Advance Settings link");

		verifyTextPresent(originalDisplayText);

		if(logoutFromPortal()){
			Reporters.SuccessReport("Verify Log out Status", "User has been Logged out of Portal Successfully");
		}
		else{
			Reporters.failureReport("Verify Log out Status", "Log out is unsuccessful");
		}

		if(closeWindow("Reporting Site",1))
		{
			Reporters.SuccessReport("Close the Portal Tab", "Portal Tab is closed");
		}
		else
		{
			Reporters.failureReport("Close the Portal Tab", "Unable to close the Portal Tab");
		}
		emtLogout=true;
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("XML_AdvSesSrch_Setting_Text", "Key_XML_AdvanceSessionSearchSetting_DisplayText");  //returning data from "XML_AdvSesSrch_Setting_Text" sheet and "Key_XML_AdvanceSessionSearchSetting_DisplayText" as a reference to read data
	}

}
