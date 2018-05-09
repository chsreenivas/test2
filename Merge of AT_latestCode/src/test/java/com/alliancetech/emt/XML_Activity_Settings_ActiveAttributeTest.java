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

public class XML_Activity_Settings_ActiveAttributeTest extends BusinessFunctions {
	public static int logIn=0;

	@Test(dataProvider=("getData"),groups= {"Portal", "RunAll"})
	public void activitySettingsActiveAttribute(String type,String iconText) throws Throwable {
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
			Reporters.SuccessReport("Remove activity-setting Tag for "+type+"", "activity-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove activity-setting Tag for "+type+"", "activity-setting Tag is not removed");
		}

		if(insertActiveFalseTag(type)){
			Reporters.SuccessReport("Make Active Attribute value false for "+type+" to disappear on Portal", "Successfully inserted new  activity-setting tag with attribute value as false for "+type+" to disappear on Portal");
		}
		else
		{
			Reporters.failureReport("Make Active Attribute value false for "+type+" to disappear on Portal", "Unable to insert new tag");
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

		if(verifyTabNameInPortal(type)){
			Reporters.failureReport("Verify that tab "+type+" does not appear on Portal", ""+type+" tab appears on Portal. Hence failed");
		}
		else{
			Reporters.SuccessReport("Verify that tab "+type+" does not appear on Portal", ""+type+" tab has not been displayed on Portal Home Page");
		}

		if(logoutFromPortal()){
			Reporters.SuccessReport("Verify Log out Status", "User has been Logged out Successfully");
		}
		else{
			Reporters.failureReport("Verify Log out Status", "Log out is unsuccessful");
		}
		
		verifyTextPresent("Sign In");

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

		if(insertActiveTrueTag(type)){
			Reporters.SuccessReport("Make Active Attribute value True for "+type+" to appear on Portal", "Successfully inserted new  activity-setting tag with attribute value as True for "+type+" to Appear on Portal");
		}
		else
		{
			Reporters.failureReport("Make Active Attribute value True for "+type+" to appear on Portal", "Unable to insert new tag");
		}

		clickSaveXMLFileButton();

		//verifyTextPresent("portal_settings.xml has been saved successfully!");

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

		if(verifyTabNameInPortal(iconText)){
			Reporters.SuccessReport("Verify that tab "+iconText+" appears on Portal", ""+iconText+" tab has been displayed on Portal Home Page");
		}
		else{
			Reporters.failureReport("Verify that tab "+iconText+" appears on Portal", ""+iconText+" tab has not been displayed on Portal Home Page");
		}

		if(logoutFromPortal()){
			Reporters.SuccessReport("Verify Log out Status", "User has been Logged out of Portal Successfully");
		}
		else{
			Reporters.failureReport("Verify Log out Status", "Log out is unsuccessful");
		}

		verifyTextPresent("Sign In");

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
		return Data_Provider.getTableArray("XML_Sessions_Active", "Key_XML_Sessions_Active");  //returning data from "XML_Sessions_Active" sheet and "Key_XML_Sessions_Active" as a reference to read data
	}
	
}











