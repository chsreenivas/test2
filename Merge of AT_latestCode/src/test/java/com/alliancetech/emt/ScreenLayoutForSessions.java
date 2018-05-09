package com.alliancetech.emt;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class ScreenLayoutForSessions extends BusinessFunctions{
  @Test(groups={"EMT","RunAll","Survey"})
  public void screenLayoutForSessions() throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Sessions page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Sessions page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButton);
		
		click(ScreenLayoutOR.lnkEditButton, "Edit Icon");
		
		if(setAddLayout("Sessions",0))
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
			if (clickTabFromViewMore(EMT_SessionsOR.tabSessions, "Sessions tab")) {
				Reporters.SuccessReport("Navigate to Sessions Page","Required steps have been performed above successfully");
			} 
			else {
				Reporters.failureReport("Navigate to Sessions Page","Unable to Perform all the required steps");
			}
			if (NavigateToAddSessionsPage()) {
				Reporters.SuccessReport("Navigate to Add Sessions Page","Steps to navigate to add sessions page have been performed");
			} 
			else {
				Reporters.failureReport("Navigate to Add Sessions Page","Unable to perform all the required steps to navigate");
			}
			verifyText(EMT_SessionsOR.txtPageTitle, "Add New Session","Add Session Page");
			Thread.sleep(2000);
			if (verifyScreenLayoutInAddNewPage()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Sessions Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Sessions Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		
		Thread.sleep(2000);
		
		if(clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Sessions page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Sessions page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools,"Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButton);
		
		click(ScreenLayoutOR.lnkEditButton, "Edit Icon");
		
		if(setAddLayout("Sessions",0))
		{
			js_click(ScreenLayoutOR.btnSaveChanges,"Save Section Button");
			Reporters.SuccessReport("Set Screen Layout in Detail Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Detail Layout", "Screen layout has failed");
		}
		
		if(verifyScreenLayout())
		{
			Reporters.SuccessReport("Verify screen layout is saved", "All fields are saved successfully");
		}
		else
		{
			Reporters.failureReport("Verify screen layout is saved", "Failed to save fields");
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
		
		if(enableViewBys("Speaker Details"))
		{
			Reporters.SuccessReport("Enable Speaker Details View By", "Successfully enabled Enable Speaker Details View By");
		}
		else
		{
			Reporters.failureReport("Enable Speaker Details View By", "Failed to Enable Speaker Details View By");
		}
		
		Thread.sleep(2000);
		emtLogout=true;
  }
}
