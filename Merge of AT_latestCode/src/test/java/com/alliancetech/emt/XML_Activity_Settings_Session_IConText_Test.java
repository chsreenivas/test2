package com.alliancetech.emt;
/**
 * This Test Case is used to verify that
 * if value of <active> attribute in <activity-settings> tag is edited, the change is reflected in Portal - Sessions.
 * reference sheet- iConnect-Sessions(XML-<activity-settings>)
 */
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class XML_Activity_Settings_Session_IConText_Test extends BusinessFunctions {
	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"Portal", "RunAll"})
	public void activitySettingsIconText(String iconText,String type,String originalIconText) throws Throwable {

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

		if(removeTag(XML_OR.txtTagToRemove(type))){
			Reporters.SuccessReport("Remove activity-setting Tag for sessions", "activity-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove activity-setting Tag for sessions", "activity-setting Tag is not removed");
		}

		if(insertActivitySettingsIconText(iconText,type)){
			Reporters.SuccessReport("Make icon-text as "+iconText, "Successfully inserted new  activity-setting tag with icon-text attribute value as "+iconText+" for "+type+" value ");
		}
		else
		{
			Reporters.failureReport("Make icon-text as "+iconText, "Unable to insert new tag");
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


		if(verifyTabNameInPortal(iconText)){
			Reporters.SuccessReport("Verify that tab "+iconText+" name get updated with given name on Portal", " Given Name has been updated on Portal for "+originalIconText);
		}
		else{
			Reporters.failureReport("Verify that tab "+iconText+" name get updated with given name on Portal", "Unable to update the given name "+originalIconText);
		}

		if(logoutFromPortal()){
			Reporters.SuccessReport("Verify Log out Status", "User has been Logged out Successfully from Portal");
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

		if(removeTag(XML_OR.txtTagToRemove(type))){
			Reporters.SuccessReport("Remove activity-setting Tag for "+type+"", "activity-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove activity-setting Tag for "+type+"", "activity-setting Tag is not removed");
		}

		if(insertActivitySettingsDefaultIconText(originalIconText, type)){
			Reporters.SuccessReport("Change the Name of "+type+" to Default name "+originalIconText+" on XML", "Successfully inserted new  activity-setting tag with icon-text name as "+originalIconText+" that appears on Portal");
		}
		else
		{
			Reporters.failureReport("Change the Name of "+type+" to Default name "+originalIconText+" on XML", "Unable to insert new tag");
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

		if(verifyTabNameInPortal(originalIconText)){
			Reporters.SuccessReport("Verify that tab "+originalIconText+" appears on Portal", ""+originalIconText+" tab has been displayed on Portal Home Page");
		}
		else{
			Reporters.failureReport("Verify that tab "+originalIconText+" appears on Portal", ""+originalIconText+" tab has not been displayed on Portal Home Page");
		}

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
		return Data_Provider.getTableArray("XML_Session_InnerText", "Key_XML_Session_InnerText");  //returning data from "XML_Session_InnerText" sheet and "Key_XML_Session_InnerText" as a reference to read data
	}
	
}











