package com.alliancetech.emt;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class ScreenLayoutForHighlights extends BusinessFunctions{
  @Test(groups={"EMT","RunAll"})
  
  public void screenLayoutForHighlights() throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkHighlights, "Highlights Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Highlights page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Highlights page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButtonInHighlightInformation);
		
		click(ScreenLayoutOR.lnkEditButtonInHighlightInformation, "Edit Icon");
		
		waitForElementPresent(ScreenLayoutOR.lnkCloseButton);
		
		if(setAddLayout("Highlights",4))
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
			Reporters.failureReport("Verify whether changes are saved in Manage Screen layout", "Failed to save all the given fields");
		}
		
		if (!getTabStatusMessage().contains("No Unused field")) {
			if(clickTabFromViewMore(CommonOR.lnkHighlights,"Highlights Tab")){
				Reporters.SuccessReport("Verify Page", "Highlights page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Highlights page is not displayed");
			}
			
			js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Record Link");
			
			js_TriggerOnClickEvent("addNewRecord();", "Add Button");
			Thread.sleep(2000);
			if (verifyScreenLayoutInAddNewPage()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Highlights Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Highlights Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		Thread.sleep(2000);
		emtLogout=true;
  }
}
