package com.alliancetech.emt;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class ScreenLayoutForSpeakers extends BusinessFunctions{
	
  @Test(groups={"EMT","RunAll"})
  public void screenLayoutForSpeakers() throws Throwable {
	  eReader = new ExcelReader("TestData//TestData.xls", "ScreenLayout", 1);
	  
	  if(getURL(configProps.getProperty("ScreenLayout_URL")))
		{
			Reporters.SuccessReport("Launch Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch Application", "Application URL failed to launch ");
		}

		if(emtLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkSpeakers, "Speakers Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Speakers page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Speakers page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButtonInCategories);
		
		click(ScreenLayoutOR.lnkEditButtonInCategories, "Edit Icon");
		
		waitForElementPresent(ScreenLayoutOR.lnkCloseButton);
		
		if(setAddLayout("Speakers",3))
		{
			js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button");
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}
		
		if(verifyScreenLayout())
		{
			Reporters.SuccessReport("Verify whether changes are saved in Manage Screen layout", "All fields are saved successfully");
		}
		else
		{
			Reporters.failureReport("Verify whether changes are saved in Manage Screen layout", "Failed to save all given fields");
		}
		
		if (!getTabStatusMessage().contains("No Unused field")) {
			if(clickTabFromViewMore(CommonOR.lnkSpeakers,"Speakers Tab")){
				Reporters.SuccessReport("Verify Page", "Speakers page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Speakers page is not displayed");
			}
			if(verifySpeakerIcon()){
				Reporters.SuccessReport("Verify the speaker icon", "Successfully verified");
			}
			else{
				Reporters.failureReport("Verify the speaker icon", "Unable to verify");
			}
			if(addNewRecord()){
				Reporters.SuccessReport("Select the Speakers from dropDown", "Successfully selected the Speakers");
			}
			else{
				Reporters.failureReport("Select the Speakers from dropDown", "Unable to select Speakers");
			}
			Thread.sleep(2000);
			if (verifyScreenLayoutInAddNewPage()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Speaker Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Speaker Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		
		Thread.sleep(3000);
		
		if(clickTabFromViewMore(CommonOR.lnkSpeakers, "Speakers Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Speakers page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Speakers page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButtonInCategories);
		
		click(ScreenLayoutOR.lnkEditButtonInCategories, "Edit Icon");
		
		if(setAddLayout("Speakers",3))
		{
			js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button");
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}
		
		if(verifyScreenLayout())
		{
			Reporters.SuccessReport("Verify screen layout is saved", "All fields are saved successfully");
		}
		else
		{
			Reporters.failureReport("Verify screen layout is saved", "Failed to save fields");
		}
		Thread.sleep(3000);
		emtLogout=true;

}
}
