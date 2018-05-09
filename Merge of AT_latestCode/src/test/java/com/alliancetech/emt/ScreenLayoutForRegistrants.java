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

public class ScreenLayoutForRegistrants extends BusinessFunctions{
  @Test(groups={"EMT","RunAll","Survey"})
  public void screenLayoutForRegistrants() throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButton);
		
		click(ScreenLayoutOR.lnkEditButton, "Edit Icon");
		
		waitForElementPresent(ScreenLayoutOR.lnkCloseButton);
		
		if(setAddLayout("Registrants",1))
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
			if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
				Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Registrants page is not displayed");
			}

			verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");
			
			js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");
			
			js_TriggerOnClickEvent("addNewRecord();", "Add Button");
			Thread.sleep(2000);
			if (verifyScreenLayoutInAddNewPage()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Registrants Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Registrants Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		
		Thread.sleep(2000);
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout check box");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButton);
		
		click(ScreenLayoutOR.lnkEditButton, "Edit Icon");
		
		if(setAddLayout("Registrants",1))
		{
			js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button");
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
			Reporters.SuccessReport("Set Screen Layout in Detail Layout", "Successfully set the Registrant detail layout");
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Detail Layout", "Screen layout has failed");
		}
		
		if(verifyScreenLayout())
		{
			Reporters.SuccessReport("Verify whether changes are saved in Manage Screen layout", "All fields are saved successfully");
		}
		else
		{
			Reporters.failureReport("Verify whether changes are saved in Manage Screen layout", "Failed to save all given fields");
		}
		
		if(enableViewBys("Session Enrollment"))
		{
			Reporters.SuccessReport("Enable Session Enrollment View By", "Successfully enabled Enable Session Enrollment View By");
		}
		else
		{
			Reporters.failureReport("Enable Session Enrollment View By", "Failed to Enable Session Enrollment View By");
		}
		
		if(enableViewBys("Session Attendance"))
		{
			Reporters.SuccessReport("Enable Session Attendance View By", "Successfully enabled Enable Session Attendance View By");
		}
		else
		{
			Reporters.failureReport("Enable Session Attendance View By", "Failed to Enable Session Attendance View By");
		}
		
		if(enableViewBys("RFID Associations"))
		{
			Reporters.SuccessReport("Enable RFID Associations View By", "Successfully enabled Enable RFID Associations View By");
		}
		else
		{
			Reporters.failureReport("Enable RFID Associations View By", "Failed to Enable RFID Associations View By");
		}
		
		Thread.sleep(2000);
		emtLogout=true;

  }
}
