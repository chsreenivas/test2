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
import com.cigniti.automation.utilities.Reporters;

public class AddNewRegistrant extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"checkin", "RunAll"})
	public void addNewRegistrantInCheckIn(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,
			String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
			String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,String LogInID,String Pswd,String ConfirmPassword) throws Throwable {

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

				else {
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")){
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		click(ScreenLayoutOR.lnkCheckIn,"CheckIn link");

		switchToFrameByIndex(0);

		if(removeTag(XML_OR.tagShowAddRegistrantAdmin))
		{
			Reporters.SuccessReport("Change value of show-add-registrant-admin tag", "Successfully changed the show-add-registrant-admin tag value to true");
		}
		else
		{
			Reporters.failureReport("Change value of show-add-registrant-admin tag", "Failed to change the show-add-registrant-admin tag value to true");
		}

		if(insertShowAddRegistrantAdmin("true"))
		  {
			  Reporters.SuccessReport("Change show-add-registrant-admin value to true", "Successfully Changed show-add-registrant-admin value to true");
		  }
		  else
		  {
			  Reporters.failureReport("Change show-add-registrant-admin value to true", "Failed to Change show-add-registrant-admin value to true");
		  }
		
		clickSaveXMLFileButton();
		
		Thread.sleep(2000);
		
		getEMTURL();
		
		if(emtLogOut())
		{
			Reporters.SuccessReport("Logout from EMT Site", "Logged out successfully");
		}
		else
		{
			Reporters.failureReport("Logout from EMT Site", "Failed to log out");
		}
		
		if(getCheckinURL())
		{
			Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
		}

		if(checkInSiteLogIn(configProps.getProperty("CheckInAdmin_Username"),configProps.getProperty("CheckInAdmin_Password")))
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		
		if (waitForVisibilityOfElement(By.xpath("//div[@id='printer']//div/select[@id='printers']"), "Select Printer Pop Up")) {
			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");
			click(CheckInOR.btnChoosePrinter, "Choose Printer");
		}
		click(CheckInOR.btnAddRegistrant,"Add Registrant button");

		Thread.sleep(2000);
		
		if(addRegistrantInfoInCheckIn( prefix, attendee, first, last, status, AttendeeType, Title,
				Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County,
				Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email, LogInID, Pswd))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else
		{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}

		click(CheckInOR.btnConfirm,"Confirm Button");

		click(CheckInOR.btnDone,"Done Button");

		if(checkInSiteLogout())
		{
			Reporters.SuccessReport("Logout from Checkin Site", "Logged out successfully");
		}
		else
		{
			Reporters.SuccessReport("Logout from Checkin Site", "Failed to log out");
		}

		if(openSiteInNewWindow("Reporting Site")){
			getEMTURL();
			Reporters.SuccessReport("Open EMT Site in New window", "Successfully launched EMT site in new Window");
		}
		else{
			Reporters.failureReport("Open EMT Site in New window", "Failed to launch EMT site in new Window");
		}

		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}

		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(search(attendee))
		{
			Reporters.SuccessReport("Search for newly added Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is displayed");
		}
		else{
			Reporters.failureReport("Search for newly added Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is not displayed");
		}

		waitForVisibilityOfElement(EMT_RegistrantsOR.tblResults, "Registrant results");

		if(click(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div//table/tbody/tr/td[text()='"+attendee+"']"),"Open registrant"))
		{
			if(verifyCheckinRegistrantDetailsInEMT(prefix, attendee, first, last, status, AttendeeType, Title, Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County, Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email, LogInID))
			{
				Reporters.SuccessReport("Verification of Registrant information", "All the information entered for registrant is valid ");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant information", "All the information entered for registrant is invalid ");
			}

			if(verifyCheckInStatus())
			{
				Reporters.SuccessReport("Verify Checkin Status", "Checkin Status is valid");
			}
			else
			{
				Reporters.failureReport("Verify Checkin Status", "Checkin Status is invalid");
			}
			
			if(verifyAssociationInManageRFIDAssociation(1))
			{
				Reporters.SuccessReport("Verify Association for Registrant "+attendee, "Association got added to Registrant");
			}
		}

		getEMTURL();
		emtLogout=true;
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CheckIn_AddNewRegistrant", "Key_AddRegistrants_Checkin");  //returning data from "CheckIn_AddNewRegistrant" sheet and "Key_AddRegistrants_Checkin" as a reference to read data
	}


}