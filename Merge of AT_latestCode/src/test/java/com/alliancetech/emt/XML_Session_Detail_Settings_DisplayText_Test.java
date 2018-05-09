package com.alliancetech.emt;
/**
 * This Test Case is used to verify that
 * if value of <display Text> attribute in <session-detail-settings> tag is edited, the change is reflected in Portal - Sessions Details pop-up.
 * reference sheet- iConnect-Sessions(XML-<session-detail-settings>)
 */
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class XML_Session_Detail_Settings_DisplayText_Test extends BusinessFunctions {

	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"Portal", "RunAll"})
	public void session_Detail_Settings_DisplayText_Test(String displayText,String usage,String originalDisplayText,String session) throws Throwable {
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
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
					logIn++;
				}
				else{
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch");
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

		if(removeTag(XML_OR.txtSessionDetailDisplaytext(usage))){
			Reporters.SuccessReport("Remove session-detail-setting Tag for "+usage+"", "session-detail-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove session-detail-setting Tag for "+usage+"", "session-detail-setting Tag is not removed");
		}

		if(insertSessionDetailSettingDisplayTextTag(displayText,usage)){
			Reporters.SuccessReport("insert session-detail-setting Tag for "+usage+" with display text as "+displayText+"", "session-detail-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove session-detail-setting Tag for "+usage+" with display text as "+displayText+"", "session-detail-setting Tag is not removed");
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
		
		click(XML_OR.lnkAdvanceSearch, "Advance Settings link");
		
		type(XML_OR.txtAdvanceSearch,session,"Session title");
		
		click(XML_OR.btnSearch,"Search Button");
		
		waitForVisibilityOfElement(XML_OR.btnViewSessionDetails,"View Session Details Button");
		
		click(XML_OR.btnViewSessionDetails,"View Session Details Button");
		
		verifyTextPresent("Session Details");
		
		switchToFrameByIndex(0);
		
		new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(By.id("session-info")));
		waitForElementPresent(XML_OR.txtOtherActions);
		
		verifyTextPresent(displayText);
		
		driver.switchTo().defaultContent();
		
		click(XML_OR.btnCloseSessionPopUp,"Close Session Pop Up");
		
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
		
		if(removeTag(XML_OR.txtSessionDetailDisplaytext(usage))){
			Reporters.SuccessReport("Remove session-detail-setting Tag for "+usage+"", "session-detail-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove session-detail-setting Tag for "+usage+"", "session-detail-setting Tag is not removed");
		}

		if(insertSessionDetailSettingDisplayTextTag(originalDisplayText,usage)){
			Reporters.SuccessReport("insert session-detail-setting Tag for "+usage+" with default display text as "+originalDisplayText+"", "session-detail-setting tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove session-detail-setting Tag for "+usage+" with default display text as "+originalDisplayText+"", "session-detail-setting Tag is not removed");
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
		
		click(XML_OR.lnkAdvanceSearch, "Advance Settings link");
		
		type(XML_OR.txtAdvanceSearch,session,"Session title");
		
		click(XML_OR.btnSearch,"Search Button");
		
		waitForElementPresent(XML_OR.btnViewSessionDetails);
		
		click(XML_OR.btnViewSessionDetails,"View Session Details Button");
		
		verifyTextPresent("Session Details");
		
		switchToFrameByIndex(0);
		
		new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOfElementLocated(By.id("session-info")));
		waitForElementPresent(XML_OR.txtOtherActions);
		
		verifyTextPresent(originalDisplayText);
		
		driver.switchTo().defaultContent();
		
		click(XML_OR.btnCloseSessionPopUp,"Close Session Pop Up");
		
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

		return Data_Provider.getTableArray("XML_Session_DetailSetting_Text", "Key_XML_Session_DetailSetting_DisplayText");  //returning data from "XML_Session_DetailSetting_Text" sheet and "Key_XML_Session_DetailSetting_DisplayText" as a reference to read data
	}
	
	
}
