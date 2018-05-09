package com.alliancetech.emt;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class ScreenLayoutForExhibitors extends BusinessFunctions{
  @Test(groups={"EMT","RunAll"})
  public void screenLayoutForExhibitors() throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButtonInCategories);
		
		click(ScreenLayoutOR.lnkEditButtonInCategories, "Edit Icon");
		
		waitForElementPresent(ScreenLayoutOR.lnkCloseButton);
		
		if(setAddLayout("Exhibitors",2))
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
			if(clickTabFromViewMore(CommonOR.lnkExhibitors,"Exhibitors Tab")){
				Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
			}
			
			verifyText(EMT_AddExhibitorsOR.txtExhibitorTitle, "Exhibitors", "Exhibitors Page");
			
			js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Record Link");
			
			js_TriggerOnClickEvent("addNewRecord();", "Add Button");
			Thread.sleep(2000);
			if (verifyScreenLayoutInAddNewPage()) {
				Reporters.SuccessReport("Verify Screen Layout in Add New Exhibitors Page","Displayed New Screen Layout");
			} 
			else {
				Reporters.failureReport("Verify Screen Layout in Add New Exhibitors Page","Failed to display New Screen Layout");
			}
		}
		else{
			System.out.println(getTabStatusMessage());
			Reporters.SuccessReport("There are no unused fields", "Hence Does not Require Any Validation");
		}
		
		Thread.sleep(3000);
		
		if(clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
		
		js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
		
		click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
		
		waitForElementPresent(ScreenLayoutOR.lnkEditButtonInCategories);
		
		click(ScreenLayoutOR.lnkEditButtonInCategories, "Edit Icon");
		
		if(setAddLayout("Exhibitors",2))
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
		Thread.sleep(3000);
		emtLogout=true;

}
}

