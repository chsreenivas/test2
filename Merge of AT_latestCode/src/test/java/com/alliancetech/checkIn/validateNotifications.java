package com.alliancetech.checkIn;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class validateNotifications extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"), groups={"checkin","RunAll"})

	public void validate_Notifications(String VerificationEnabled,String logoutButton,String RegEmailAddress1,String RegEmailAddress2,String RegEmailAddress3) throws Throwable {
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
					Reporters.SuccessReport("Login into Survey Application", "login is successfull");
					logIn++;
				}

				else{
					Reporters.failureReport("Login into Survey Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		if(navigateToCheckinXML())
		{
			Reporters.SuccessReport("Navigate to Checkin XML Site", "Successfully navigated to Checkin XML Site");
		}
		else
		{
			Reporters.failureReport("Navigate to Checkin XML Site", "Faile to navigate to Checkin XML Site");
		}

		switchToFrameByIndex(0);

		if(removeCheckinNotificationTag(By.xpath("//span[text()='notifications'][1]/preceding-sibling::span[text()='<'][1]"),By.xpath("//span[text()='notifications'][2]/following-sibling::span[text()='>'][1]")))
		{
			Reporters.SuccessReport("Remove Notification tag in Checkin XML", "Notification tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove Notification tag in Checkin XML", "Notification tag is not removed");
		}

		if(insertCheckinNotificationTag())
		{
			Reporters.SuccessReport("Insert Notification tag in Checkin XML", "Notification tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Insert Notification tag in Checkin XML", "Notification tag is not removed");
		}

		if(removeTag(XML_OR.txtVerificationEnabledTag))
		{
			Reporters.SuccessReport("Remove Verification Enabled tag in Checkin XML", "Verification Enabled tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove Verification Enabled tag in Checkin XML", "Verification Enabled tag is not removed");
		}

		if(insertVerificationEnabledTag(VerificationEnabled))
		{
			Reporters.SuccessReport("Make Verification Enabled tag "+VerificationEnabled, "Successfully inserted new  Verification Enabled tag with value: "+VerificationEnabled);
		}
		else
		{
			Reporters.failureReport("Make Verification Enabled tag "+VerificationEnabled, "Failed to insert new Verification Enabled tag with value: "+VerificationEnabled);
		}

		if(removeTag(XML_OR.txtShowLogoutButtonForNonAdmin))
		{
			Reporters.SuccessReport("Remove Show-logout-button-non-admin tag in Checkin XML", "Show-logout-button-non-admin tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove Show-logout-button-non-admin tag in Checkin XML", "Show-logout-button-non-admin tag is not removed");
		}

		if(insertShowLogoutButtonNonAdmin(logoutButton))
		{
			Reporters.SuccessReport("Make Show-logout-button-non-admin "+logoutButton, "Successfully inserted new  Show-logout-button-non-admin tag with value: "+logoutButton);
		}
		else
		{
			Reporters.failureReport("Make Show-logout-button-non-admin "+logoutButton, "Failed to insert new Show-logout-button-non-admin tag with value: "+logoutButton);
		}

		clickSaveXMLFileButton();

		if(openSiteInNewWindow("Checkin - Kiosk"))
		{
			getCheckinURL();
			Reporters.SuccessReport("Open Survey Site in new window", "Survey Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open Survey Site in new window","Survey site has failed to open in new window");
		}

		if(checkInAdminLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
		click(CheckInOR.btnChoosePrinter, "Choose Printer Button");

		type(CheckInOR.txtAttendeeEmailAddress,RegEmailAddress1,"Email Address");
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(CommonOR.checkInAjax, "Loading");

		//checkin Registrant
		click(By.xpath("//a[text()='Check In']"), "Check in button");
		
		click(CheckInOR.btnConfirm, "Confirm");

		verifyTextPresent("Thank you  your check in is complete.");

		click(CheckInOR.btnDone,"Done");
		
		if(checkInSiteLogout())
		{
			Reporters.SuccessReport("Logout from Checkin Site", "Successfully logged out from Checkin site");
		}
		else
		{
			Reporters.failureReport("Logout from Checkin Site", "Failed to log out from check in site");
		}

		switchWindowByTitle("Reporting Site", 1);

		//Enable Notifications View By in Registrants
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		click(EMT_EmailCampignsOR.lnkTools,"Tools link");

		click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");

		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");

		if(setNotificationsViewBy())
		{
			Reporters.SuccessReport("Enable Notifications View By for Registrants", "Successfully enabled Notifications View By for Registrants");
		}
		else
		{
			Reporters.failureReport("Enable Notifications View By for Registrants", "Failed to enable Notifications view by for registrants");
		}

		//Verify SMS record in Notifications for Registrant

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Link"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants Page has displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants Page has not displayed");
		}

		if(search(RegEmailAddress1))
		{
			Reporters.SuccessReport("Search for the registrant "+RegEmailAddress1+" in EMT", "Successfully Searched for the registrant");
		}
		else
		{
			Reporters.failureReport("Search for the registrant "+RegEmailAddress1+" in EMT", "Failed to Search for the registrant");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//td[text()='"+RegEmailAddress1+"']"),"Registrant"))
		{
			Reporters.SuccessReport("View the Registrant", "Registrant details have been displayed");
			if(verifySMSRecordInNotifications())
			{
				Reporters.SuccessReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress1, "Successfully verified SMS Record in Notification section for registrant with mail id: "+RegEmailAddress1);
			}
			else
			{
				Reporters.failureReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress1, "Failed to verify SMS record in Notification section for registrant : "+RegEmailAddress1);
			}
			//delete the registrant
			waitForVisibilityOfElement(CommonOR.lnkDeleteThisRecord,
					"Delete This Registrant link");
			js_click(CommonOR.lnkDeleteThisRecord,
					"Delete This Registrant link");
			waitForVisibilityOfElement(
					EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			verifyTextPresent("Registrant has been deleted!");
		}
		
		switchWindowByTitle("Checkin - Admin", 2);
		
		if(checkInUserLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
		click(CheckInOR.btnChoosePrinter, "Choose Printer Button");

		type(CheckInOR.txtAttendeeEmailAddress,RegEmailAddress2,"Email Address");
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(CommonOR.checkInAjax, "Loading");

		//checkin Registrant
		click(CheckInOR.btnConfirm, "Confirm");

		verifyTextPresent("Thank you  your check in is complete.");

		click(CheckInOR.btnDone,"Done");
		
		switchWindowByTitle("Reporting Site", 1);

		//Enable Notifications View By in Registrants
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();

		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");

		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");

		if(setNotificationsViewBy())
		{
			Reporters.SuccessReport("Enable Notifications View By for Registrants", "Successfully enabled Notifications View By for Registrants");
		}
		else
		{
			Reporters.failureReport("Enable Notifications View By for Registrants", "Failed to enable Notifications view by for registrants");
		}

		//Verify SMS record in Notifications for Registrant

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Link"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants Page has displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants Page has not displayed");
		}

		if(search(RegEmailAddress2))
		{
			Reporters.SuccessReport("Search for the registrant "+RegEmailAddress2+" in EMT", "Successfully Searched for the registrant");
		}
		else
		{
			Reporters.failureReport("Search for the registrant "+RegEmailAddress2+" in EMT", "Failed to Search for the registrant");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//td[text()='"+RegEmailAddress2+"']"),"Registrant"))
		{
			Reporters.SuccessReport("View the Registrant", "Registrant details have been displayed");
			if(verifySMSRecordInNotifications())
			{
				Reporters.SuccessReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress2, "Successfully verified SMS Record in Notification section for registrant with mail id: "+RegEmailAddress2);
			}
			else
			{
				Reporters.failureReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress2, "Failed to verify SMS record in Notification section for registrant : "+RegEmailAddress2);
			}
			//delete the registrant
			js_click(CommonOR.lnkDeleteThisRecord,
					"Delete This Registrant link");
			waitForVisibilityOfElement(
					EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			verifyTextPresent("Registrant has been deleted!");
		}
		
		switchWindowByTitle("Checkin - Kiosk", 2);
		
		type(CheckInOR.txtAttendeeEmailAddress,RegEmailAddress3,"Email Address");
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(CommonOR.checkInAjax, "Loading");

		//checkin Registrant
		click(CheckInOR.btnConfirm, "Confirm");

		verifyTextPresent("Thank you  your check in is complete.");

		click(CheckInOR.btnDone,"Done");
		
		if(checkInSiteLogout())
		{
			Reporters.SuccessReport("Logout from Checkin Site", "Successfully logged out from Checkin site");
		}
		else
		{
			Reporters.failureReport("Logout from Checkin Site", "Failed to log out from check in site");
		}
		
		closeWindow("Checkin - Kiosk", 2);

		switchWindowByTitle("Reporting Site", 1);

		//Enable Notifications View By in Registrants
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();

		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");

		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");

		if(setNotificationsViewBy())
		{
			Reporters.SuccessReport("Enable Notifications View By for Registrants", "Successfully enabled Notifications View By for Registrants");
		}
		else
		{
			Reporters.failureReport("Enable Notifications View By for Registrants", "Failed to enable Notifications view by for registrants");
		}

		//Verify SMS record in Notifications for Registrant

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Link"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants Page has displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants Page has not displayed");
		}

		if(search(RegEmailAddress3))
		{
			Reporters.SuccessReport("Search for the registrant "+RegEmailAddress3+" in EMT", "Successfully Searched for the registrant");
		}
		else
		{
			Reporters.failureReport("Search for the registrant "+RegEmailAddress3+" in EMT", "Failed to Search for the registrant");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//td[text()='"+RegEmailAddress3+"']"),"Registrant"))
		{
			Reporters.SuccessReport("View the Registrant", "Registrant details have been displayed");
			if(verifySMSRecordInNotifications())
			{
				Reporters.SuccessReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress3, "Successfully verified SMS Record in Notification section for registrant with mail id: "+RegEmailAddress3);
			}
			else
			{
				Reporters.failureReport("Verify SMS Record in Notifications for Registrant: "+RegEmailAddress3, "Failed to verify SMS record in Notification section for registrant : "+RegEmailAddress3);
			}
			//delete the registrant
			js_click(CommonOR.lnkDeleteThisRecord,
					"Delete This Registrant link");
			waitForVisibilityOfElement(
					EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			verifyTextPresent("Registrant has been deleted!");
		}
		
		emtLogout=true;

		}
		/*
		 * Reading data from checkin.xls under TestData folder	
		 */

		@DataProvider
		public Object[][] getData() throws Exception {

			return Data_Provider.getTableArray("CheckinNotification", "Key_SMSNotification");  //returning data from "Registrant" sheet and "Key_User" as a reference to read data
		}
}
