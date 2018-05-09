package com.alliancetech.checkIn;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class setScreenLayoutForAddRegistrantInCheckIn extends BusinessFunctions{
	public static int logIn=0;
	@Test(groups={"EMT","RunAll"})
	public void setScreenLayoutForAddRegistrant() throws Throwable {
		eReader = new ExcelReader("TestData//TestData.xls", "ScreenLayout", 1);
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
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")){
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		click(ScreenLayoutOR.lnkCheckIn,"CheckIn link");

		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Add Reg CheckIn Confirm", "Tab Filter");

		if(setAddLayout("AddRegistrantFormInCheckIn",6))
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

		if(emtLogOut())
		{
			Reporters.SuccessReport("Logout from EMT Site", "Logged out successfully");
		}
		else
		{
			Reporters.failureReport("Logout from EMT Site", "Failed to log out");
		}

		if (!getTabStatusMessage().contains("No Unused field")) {
			if(openSiteInNewWindow("Checkin - Kiosk")){
				getCheckinURL();
				Reporters.SuccessReport("Open CheckIn Site in New window", "Successfully launched Checkin site in new Window");
			}
			else{
				Reporters.failureReport("Open CheckIn Site in New window", "Failed to launch Checkin site in new Window");
			}

			if(checkInSiteLogIn(configProps.getProperty("CheckInAdmin_Username"),configProps.getProperty("CheckInAdmin_Password")))
			{
				Reporters.SuccessReport("Login into the checkin Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the checkin Application", "login is not successfull");
			}

			selectByIndex(CheckInOR.ddPrinters, 1, "Printers Drop down");

			click(CheckInOR.btnChoosePrinter,"Choose Printer");

			click(CheckInOR.btnAddRegistrant,"Add Registrant button");

			Thread.sleep(2000);
			if (verifyScreenLayoutInCheckIn()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Registrant Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Registrants Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		
		click(CheckInOR.btnTools,"Tools button");
		
		js_click(CheckInOR.lnkLogout,"Logout");
	
	}
}
