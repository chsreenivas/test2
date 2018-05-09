package com.alliancetech.checkIn;


import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class RebadgeAndEditARegistrant extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups= {"checkin", "RunAll"})
  public void rebadge_and_edit_Registrant(String prefix,String attendee,String first,String last,String status,String AttendeeType,String Title,
			String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
			String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,String LogInID,String Pswd,String ConfirmPassword) throws Throwable {
	  
	  try{
			if(logIn==0)
			{
				if(getCheckinURL())
				{
					Reporters.SuccessReport("Launch Check in Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch Check in Application", "Application URL failed to launch ");
				}

				if(checkInSiteLogIn(configProps.getProperty("CheckIn_Username"),configProps.getProperty("CheckIn_Password")))
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

		if (isElementPresent(CheckInOR.ddPrinters, "Select Printer Pop Up")) {
			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");
			click(CheckInOR.btnChoosePrinter, "Choose Printer");
		}
		type(CheckInOR.txtRegistrant,attendee,"Attendee #");
		
		click(CheckInOR.btnNext,"Next button");
		
		click(CheckInOR.btnRebadge(attendee),"Rebadge button");
		
		click(CheckInOR.btnRebadgeInPopUp,"Rebadge button in Pop Up");
		
		if(editCheckinData())
		{
			Reporters.SuccessReport("Edit Registrant data in Checkin Site", "Successfully edited registrant data");
		}
		else
		{
			Reporters.failureReport("Edit Registrant data in Checkin Site", "Failed to edit registrant information in checkin site");
		}
		
		click(CheckInOR.btnConfirm,"Confirm button");
		
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

		if(click(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div//td[text()='"+attendee+"']"),"Attendee"))
		{
			if(verifyUpdatedRegistrantDataInEMT())
			{
				Reporters.SuccessReport("Verification of Registrant information", "All the information entered for registrant is valid ");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant information", "All the information entered for registrant is invalid ");
			}
			
			if(verifyAssociationInManageRFIDAssociation(2))
			{
				Reporters.SuccessReport("Verify Association for Registrant "+attendee, "Association got added to Registrant");
			}
			else
			{
				Reporters.failureReport("Verify Association for Registrant "+attendee, "Association is not added to Registrant when rebadge operation is performed");
			}
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
