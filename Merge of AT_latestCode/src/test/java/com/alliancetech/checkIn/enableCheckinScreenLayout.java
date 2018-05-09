package com.alliancetech.checkIn;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.Reporters;

public class enableCheckinScreenLayout extends BusinessFunctions{
	public static int logIn=0;
  @Test
  public void enableScreenLayoutForCheckin() throws Throwable {
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
		
		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");
		
		if(enableCheckInScreenLayout())
		{
			Reporters.SuccessReport("Add Few fields to Right and Left columns", "Successfully added few fields to right and left columns");
		}
		else
		{
			Reporters.failureReport("Add Few fields to Right and Left columns", "failed to add few fields to right and left columns");
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
		
		if(enableCheckInScreenLayout())
		{
			Reporters.SuccessReport("Add Few fields to Right and Left columns", "Successfully added few fields to right and left columns");
		}
		else
		{
			Reporters.failureReport("Add Few fields to Right and Left columns", "failed to add few fields to right and left columns");
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
		
		emtLogout=true;
  }
}
