package com.alliancetech.checkIn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CheckIn_UserTest extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"), groups={"checkin","RunAll"})
  public void CheckIn_UserOnSlaveTest(String EmailAddress,String VerificationEnabled,String VerificationAttempts,String VerificationFieldName,String invalidSecurityAnswer,String validSecurityAnswer,String CheckinVerAttempts,String CheckinVerAttemptsValue) throws Throwable {
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
		
		if(removeTag(XML_OR.txtVerificationAttemptsTag))
		{
			Reporters.SuccessReport("Remove Verification Attempts tag in Checkin XML", "Verification Attempts tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove Verification Attempts tag in Checkin XML", "Verification Attempts tag is not removed");
		}

		if(insertVerificationAttemptsTag(VerificationAttempts))
		{
			Reporters.SuccessReport("Make Verification Attempts tag "+VerificationAttempts, "Successfully inserted new  Verification Attempts tag with value: "+VerificationAttempts);
		}
		else
		{
			Reporters.failureReport("Make Verification Attempts tag "+VerificationAttempts, "Failed to insert new Verification Attempts tag with value: "+VerificationAttempts);
		}
		
		if(removeTag(XML_OR.txtVerificationFieldNameTag))
		{
			Reporters.SuccessReport("Remove Verification Field name tag in Checkin XML", "Verification Field name tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove Verification Field name tag in Checkin XML", "Verification Field name tag is not removed");
		}

		if(insertVerificationFieldName(VerificationFieldName))
		{
			Reporters.SuccessReport("Make Verification Field Name tag "+VerificationFieldName, "Successfully inserted new  Verification Field Name tag with value: "+VerificationFieldName);
		}
		else
		{
			Reporters.failureReport("Make Verification Field Name tag "+VerificationFieldName, "Failed to insert new Verification Field Name tag with value: "+VerificationFieldName);
		}
		clickSaveXMLFileButton();
		
		if(openSiteInNewWindow("Checkin - Kiosk"))
		{
			getSlave1CheckIn();
			Reporters.SuccessReport("Open Survey Site in new window", "Survey Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open Survey Site in new window","Survey site has failed to open in new window");
		}
	 
		if(checkInUserLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
		click(CheckInOR.btnChoosePrinter, "Choose Printer Button");
		
		type(CheckInOR.txtAttendeeEmailAddress,EmailAddress,"Email Address");
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(CommonOR.checkInAjax, "Loading");
		//Validation
		if(verifySecurityQuestionPage())
		{
			Reporters.SuccessReport("Verify Security Question", "Successfully navigated to Security Question Page");
		}
		else
		{
			Reporters.failureReport("Verify Security Question", "Failed to navigate to security question page");
		}
		int count=Integer.parseInt(VerificationAttempts);
		if(validationOfNumberOfAttemtsWithMessages(invalidSecurityAnswer,count))
		{
			Reporters.SuccessReport("Validate Error Messages", "Successfully validated error messages");
		}
		else
		{
			Reporters.failureReport("Validate Error Messages", "Failed to validate error messages");
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		//Add CheckIn Ver Attempts Field to Screen Layout
		
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
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButton);
		
		click(ScreenLayoutOR.lnkEditButton, "Edit Icon");
		
		if(addCheckinVerAttemptsFieldToDetailLAyout(CheckinVerAttempts))
		{
			js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button");
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
			Reporters.SuccessReport("Set Screen Layout in Detail Layout", "Successfully set the Registrant detail layout");
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Detail Layout", "Screen layout has failed");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Link"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants Page has displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants Page has not displayed");
		}

		if(search(EmailAddress))
		{
			Reporters.SuccessReport("Search for the registrant "+EmailAddress+" in EMT", "Successfully Searched for the registrant");
		}
		else
		{
			Reporters.failureReport("Search for the registrant "+EmailAddress+" in EMT", "Failed to Search for the registrant");
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//td[text()='"+EmailAddress+"']"),"Registrant"))
		{
			Reporters.SuccessReport("View the Registrant", "Registrant details have been displayed");
			click(CheckInOR.txtCheckinVerAttemptsField,"Checkin Ver Attempts field");
			type(CheckInOR.txtCheckinVerAttempts, CheckinVerAttemptsValue, "Checkin Ver attempts value");
			click(CheckInOR.btnCheckinVerAttemptsSave,"Save");
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		}
		
		switchWindowByTitle("Checkin - Kiosk", 2);
		
		type(CheckInOR.txtAttendeeEmailAddress,EmailAddress,"Email Address");
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(CommonOR.checkInAjax, "Loading");
		if(verifySecurityQuestionPage())
		{
			Reporters.SuccessReport("Verify Security Question", "Successfully navigated to Security Question Page");
		}
		else
		{
			Reporters.failureReport("Verify Security Question", "Failed to navigate to security question page");
		}
		if(checkInRegistrantWithAppendingData(validSecurityAnswer))
		{
			Reporters.SuccessReport("Checkin a registrant with valid security key", "Successfully checked in registrant");
		}
		else
		{
			Reporters.failureReport("Checkin a registrant with valid security key", "Failed to check in a registrant");
		}
		
		verifyCheckInData(configProps.getProperty("Slave1EMT_URL"), EmailAddress);
		emtLogOut();
		
		Reporters.SuccessReport("Sleep For "+(Integer.parseInt(configProps.getProperty("Interval"))+1)+" Minutes", "Sleeping...........For integration to happen");
		TimeUnit.MINUTES.sleep(Integer.parseInt(configProps.getProperty("Interval")));
		
		verifyCheckInData(configProps.getProperty("MasterEMT_URL"), EmailAddress);
		emtLogOut();
		
		Reporters.SuccessReport("Sleep For "+(Integer.parseInt(configProps.getProperty("Interval"))+1)+" Minutes", "Sleeping...........For integration to happen");
		TimeUnit.MINUTES.sleep(Integer.parseInt(configProps.getProperty("Interval")));
		
		verifyCheckInData(configProps.getProperty("Slave2EMT_URL"), EmailAddress);
		emtLogOut();
  }
  
  /*
	 * Reading data from checkin.xls under TestData folder	
	 */

	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Checkin_Registrant", "Key_User");  //returning data from "Registrant" sheet and "Key_User" as a reference to read data
	}

	

}
