package com.alliancetech.checkIn;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class screenLayoutForAdminAndAttendeeCheckin extends BusinessFunctions{

	@Test(dataProvider = ("getData"),groups= {"checkin", "RunAll"})
	public void setScreenLayoutandVerifyForAdminAndAttendee(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,
			String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
			String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,String LogInID,String Pswd,String ConfirmPassword) throws Throwable {
		eReader = new ExcelReader("TestData//TestData.xls", "ScreenLayout", 1);
		if(getMasterEMT())
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
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");

		js_TriggerOnClickEvent("addNewRecord();", "Add Button");
		
		waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");

		if(addRegistrantInfoInEMT( prefix, attendee, first, last, status, AttendeeType, Title,
				Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County,
				Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email, LogInID, Pswd,ConfirmPassword))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else
		{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}

		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");

		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}

		getMasterEMT();

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")){
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		click(ScreenLayoutOR.lnkCheckIn,"CheckIn link");

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.tagShowLogoutButtonNonAdmin))
		{
			Reporters.SuccessReport("Change value of show-logout-button-non-admin tag", "Successfully changed the show-logout-button-non-admin tag value to true");
		}
		else
		{
			Reporters.failureReport("Change value of show-logout-button-non-admin tag", "Failed to change the show-logout-button-non-admin tag value to true");
		}

		if(insertShowLogoutButtonNonAdmin("true"))
		{
			Reporters.SuccessReport("Change show-logout-button-non-admin value to true", "Successfully Changed show-logout-button-non-admin value to true");
		}
		else
		{
			Reporters.failureReport("Change show-logout-button-non-admin value to true", "Failed to Change show-logout-button-non-admin value to true");
		}

		clickSaveXMLFileButton();

		Thread.sleep(2000);

		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AdminCheckInForm",8))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Attendee CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AttendeeCheckInForm",7))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		if(search(attendee))
		{
			Reporters.SuccessReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is displayed");
		}
		else
		{
			Reporters.failureReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is not displayed");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		click(By.xpath("//td[text()='"+attendee+"']"),attendee);

		if (!getTabStatusMessage().contains("No Unused field")) {
			if(openSiteInNewWindow("Checkin - Kiosk")){
				getMasterCheckinURL();
				Reporters.SuccessReport("Open CheckIn Site in New window", "Successfully launched Checkin site in new Window");
			}
			else{
				Reporters.failureReport("Open CheckIn Site in New window", "Failed to launch Checkin site in new Window");
			}

			//Log in as Admin in Master Checkin site
			if(checkInSiteLogIn(configProps.getProperty("MasterAdminEmailAddress"),configProps.getProperty("MasterAdminPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}
			
			if (waitForVisibilityOfElement(CheckInOR.ddPrinters, "Select Printer Pop Up")) {
				selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");
				click(CheckInOR.btnChoosePrinter, "Choose Printer");
			}
			type(CheckInOR.txtRegistrant, attendee, "Attendee # "+attendee);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			if (verifyAndCLickCheckIn(attendee)) {
				Reporters.SuccessReport("", "");
			}
			else
			{
				Reporters.failureReport("", "");
			}
			Thread.sleep(2000);
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");

			//Log in as User in Master Checkin site
			waitForVisibilityOfElement(CheckInOR.btnSignIn,"Sign In Button");
			if(checkInSiteLogIn(configProps.getProperty("MasterUserEmailAddress"),configProps.getProperty("MasterUserPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			if (waitForVisibilityOfElement(CheckInOR.ddPrinters, "Select Printer Pop Up")) {
				selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");
				click(CheckInOR.btnChoosePrinter, "Choose Printer");
			}
			type(CheckInOR.txtRegistrant, Email, "Emailid "+Email);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");	

			driver.close();

		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}

		if(switchWindowByTitle("Reporting Site", 1))
		{
			/*js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link");
			waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			if(!verifyTextPresent("Registrant has been deleted!"))
			{
				Reporters.failureReport("Print Actual Message on WebPage", driver.findElement(CommonOR.lblMessage).getText());
			}
		}*/

			if(emtLogOut())
			{
				Reporters.SuccessReport("Logout from EMT Site", "Logged out successfully");
			}
			else
			{
				Reporters.failureReport("Logout from EMT Site", "Failed to log out");
			}
		}

		//get Slave 1 URL
		/*if(getSlave1EMT())
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
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");

		js_TriggerOnClickEvent("addNewRecord();", "Add Button");
		
		waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");

		if(addRegistrantInfoInEMT( prefix, attendee, first, last, status, AttendeeType, Title,
				Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County,
				Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email, LogInID, Pswd,ConfirmPassword))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else
		{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}

		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");

		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}

		getSlave1EMT();

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")){
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		click(ScreenLayoutOR.lnkCheckIn,"CheckIn link");

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.tagShowLogoutButtonNonAdmin))
		{
			Reporters.SuccessReport("Change value of show-logout-button-non-admin tag", "Successfully changed the show-logout-button-non-admin tag value to true");
		}
		else
		{
			Reporters.failureReport("Change value of show-logout-button-non-admin tag", "Failed to change the show-logout-button-non-admin tag value to true");
		}

		if(insertShowLogoutButtonNonAdmin("true"))
		{
			Reporters.SuccessReport("Change show-logout-button-non-admin value to true", "Successfully Changed show-logout-button-non-admin value to true");
		}
		else
		{
			Reporters.failureReport("Change show-logout-button-non-admin value to true", "Failed to Change show-logout-button-non-admin value to true");
		}

		clickSaveXMLFileButton();

		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AdminCheckInForm",8))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Attendee CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AttendeeCheckInForm",7))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		if(search(attendee))
		{
			Reporters.SuccessReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is displayed");
		}
		else
		{
			Reporters.failureReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is not displayed");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		click(By.xpath("//td[text()='"+attendee+"']"),attendee);

		if (!getTabStatusMessage().contains("No Unused field")) {
			if(openSiteInNewWindow("Checkin - Kiosk")){
				getSlave1CheckIn();
				Reporters.SuccessReport("Open CheckIn Site in New window", "Successfully launched Checkin site in new Window");
			}
			else{
				Reporters.failureReport("Open CheckIn Site in New window", "Failed to launch Checkin site in new Window");
			}

			//Log in as Admin in Slave1 Checkin site
			if(checkInSiteLogIn(configProps.getProperty("Slave1AdminEmailAddress"),configProps.getProperty("Slave1AdminPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			if (waitForVisibilityOfElement(By.xpath("//div[@id='printer']//div/select[@id='printers']"), "Select Printer Pop Up")) {
				selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");
				click(CheckInOR.btnChoosePrinter, "Choose Printer");
			}
			type(CheckInOR.txtRegistrant, attendee, "Attendee # "+attendee);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			verifyAndCLickCheckIn(attendee);

			Thread.sleep(2000);
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");

			//Log in as User in Slave1 Checkin site
			waitForVisibilityOfElement(CheckInOR.btnSignIn,"Sign In Button");
			if(checkInSiteLogIn(configProps.getProperty("Slave1UserEmailAddress"),configProps.getProperty("Slave1UserPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");

			click(CheckInOR.btnChoosePrinter,"Choose Printer");

			type(CheckInOR.txtRegistrant, Email, "Emailid "+Email);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");	

			driver.close();

		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}

		if(switchWindowByTitle("Reporting Site", 1))
		{
			js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link");
			waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			if(!verifyTextPresent("Registrant has been deleted!"))
			{
				Reporters.failureReport("Print Actual Message on WebPage", driver.findElement(CommonOR.lblMessage).getText());
			}
		}

			if(emtLogOut())
			{
				Reporters.SuccessReport("Logout from EMT Site", "Logged out successfully");
			}
			else
			{
				Reporters.failureReport("Logout from EMT Site", "Failed to log out");
			}*/
		//}
		/*//get Slave 2 URL(This URL Does not exist-http://qa4_4.checkin.ieventstest.com)
		if(getSlave2EMT())
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
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");

		js_TriggerOnClickEvent("addNewRecord();", "Add Button");

		if(addRegistrantInfoInEMT( prefix, attendee, first, last, status, AttendeeType, Title,
				Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County,
				Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email, LogInID, Pswd,ConfirmPassword))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else
		{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}

		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");

		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}

		getSlave2EMT();

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")){
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		click(ScreenLayoutOR.lnkCheckIn,"CheckIn link");

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.tagShowLogoutButtonNonAdmin))
		{
			Reporters.SuccessReport("Change value of show-logout-button-non-admin tag", "Successfully changed the show-logout-button-non-admin tag value to true");
		}
		else
		{
			Reporters.failureReport("Change value of show-logout-button-non-admin tag", "Failed to change the show-logout-button-non-admin tag value to true");
		}

		if(insertShowLogoutButtonNonAdmin("true"))
		  {
			  Reporters.SuccessReport("Change show-logout-button-non-admin value to true", "Successfully Changed show-logout-button-non-admin value to true");
		  }
		  else
		  {
			  Reporters.failureReport("Change show-logout-button-non-admin value to true", "Failed to Change show-logout-button-non-admin value to true");
		  }

		clickSaveXMLFileButton();

		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AdminCheckInForm",8))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Attendee CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AttendeeCheckInForm",7))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsEditable())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");

		Thread.sleep(3000);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		if(search(attendee))
		{
			Reporters.SuccessReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is displayed");
		}
		else
		{
			Reporters.failureReport("Search for Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is not displayed");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		click(By.xpath("//td[text()='"+attendee+"']"),attendee);

		if (!getTabStatusMessage().contains("No Unused field")) {
			if(openSiteInNewWindow("Checkin - Kiosk")){
				getSlave2CheckIn();
				Reporters.SuccessReport("Open CheckIn Site in New window", "Successfully launched Checkin site in new Window");
			}
			else{
				Reporters.failureReport("Open CheckIn Site in New window", "Failed to launch Checkin site in new Window");
			}

			//Log in as Admin in Slave2 Checkin site

			if(checkInSiteLogIn(configProps.getProperty("Slave2AdminEmailAddress"),configProps.getProperty("Slave2AdminPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");

			click(CheckInOR.btnChoosePrinter,"Choose Printer");

			type(CheckInOR.txtRegistrant, attendee, "Attendee # "+attendee);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			verifyAndCLickCheckIn(attendee);

			Thread.sleep(2000);
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");

			//Log in as User in Slave1 Checkin site
			waitForVisibilityOfElement(CheckInOR.btnSignIn,"Sign In Button");
			if(checkInSiteLogIn(configProps.getProperty("Slave2UserEmailAddress"),configProps.getProperty("Slave2UserPassword")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");

			click(CheckInOR.btnChoosePrinter,"Choose Printer");

			type(CheckInOR.txtRegistrant, Email, "Emailid "+Email);
			click(CheckInOR.btnNext, "Next Button");

			waitForInVisibilityOfElement(By.xpath("//div[@class='ajax']"), "Loading");
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
			}

			click(CheckInOR.btnTools,"Tools button");

			js_click(CheckInOR.lnkLogout,"Logout");	

			driver.close();

		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}

		 *if(switchWindowByTitle("Reporting Site", 1))
		{
			js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link");
			waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
			Thread.sleep(2000);
			click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
			waitForVisibilityOfElement(CommonOR.lblMessage, "Message");
			if(!verifyTextPresent("Registrant has been deleted!"))
			{
				Reporters.failureReport("Print Actual Message on WebPage", driver.findElement(CommonOR.lblMessage).getText());
			}
		}

		if(emtLogOut())
		{
			Reporters.SuccessReport("Logout from EMT Site", "Logged out successfully");
		}
		else
		{
			Reporters.failureReport("Logout from EMT Site", "Failed to log out");
		}*/

	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CheckIn_AddNewRegistrant", "Key_AddRegistrants_Checkin");  //returning data from "CheckIn_AddNewRegistrant" sheet and "Key_AddRegistrants_Checkin" as a reference to read data
	}


}
