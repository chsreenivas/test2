package com.alliancetech.emt;
/**
 * This Test Case is used to verify that
 * if value of <nav-text> attribute in <left-nav-settings> tag is edited, the change is reflected in Portal - Sessions.
 * reference sheet- iConnect-Sessions(XML-<left-nav-settings>)
 */
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class XML_Left_Nav_Settings_NavText_Test extends BusinessFunctions{
	public static int logIn=0;
	
	@Test(dataProvider=("getData"),groups= {"Portal", "RunAll"})

	public void XML_editNavigationText(String navigationText,String nav_val,String originalNavigationText) throws Throwable {

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

		if(removeTag(XML_OR.txtTagNavItem(nav_val))){
			Reporters.SuccessReport("Remove left-nav-settings Tag for sessions", "left-nav-settings tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove left-nav-settings Tag for sessions", "left-nav-settings Tag is not removed");
		}

		if(insertLeftNavigationSettingsText(navigationText,nav_val)){
			Reporters.SuccessReport("Insert left-nav-settings Tag for "+originalNavigationText+" with navigation Text as "+navigationText+"", "left-nav-settings tag is inserted successfully");
		}
		else
		{
			Reporters.failureReport("Insert left-nav-settings Tag for "+originalNavigationText+" with navigation Text as "+navigationText+"", "left-nav-settings Tag is not inserted");
		}

		clickSaveXMLFileButton();

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

		verifyTextPresent(navigationText);

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

		if(removeTag(XML_OR.txtTagNavItem(nav_val))){
			Reporters.SuccessReport("Remove left-nav-settings Tag for "+navigationText+"", "left-nav-settings tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove left-nav-settings Tag for "+navigationText+"", "left-nav-settings Tag is not removed");
		}

		if(insertLeftNavigationSettingsText(originalNavigationText,nav_val)){
			Reporters.SuccessReport("Insert left-nav-settings Tag for "+navigationText+" with default navigation text as "+originalNavigationText+"", "left-nav-settings tag is inserted successfully");
		}

		else
		{
			Reporters.failureReport("Insert left-nav-settings Tag for "+navigationText+" with default navigation text as "+originalNavigationText+"", "left-nav-settings tag is not inserted successfully");
		}

		clickSaveXMLFileButton();

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

		//verifyTextPresent(originalNavigationText);

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

		return Data_Provider.getTableArray("XML_Left_Nav_Text", "Key_XML_Left_Nav_Text");  //returning data from "XML_Left_Nav_Text" sheet and "Key_XML_Left_Nav_Text" as a reference to read data
	}
	
}
