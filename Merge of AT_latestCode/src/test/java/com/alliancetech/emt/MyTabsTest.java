package com.alliancetech.emt;
/**
 * This Test case includes 
 * i) Verification of Edit My Tab link
 * ii) Making Some Tabs Visible(Read those Tag names from excel sheet)
 * iii) Making Some tabs Available(Hidden)-(Read those tag names from excel sheet)
 * reference Test rail-iConnect_EMT(Edit My Tabs)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class MyTabsTest extends BusinessFunctions {
	public int logIn=0;

	@Test(dataProvider = ("getData"), priority=0,groups= {"EMT", "RunAll"})
	public void EMT_VisibleTabs(String AvailableTabText) throws Throwable {
		try
		{
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
				else
				{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}

		if(myTabs())
		{
			Reporters.SuccessReport("Verify Edit My Tabs link", "Edit My Tabs link is successfully verified");
		}
		else
		{
			Reporters.failureReport("Verify Edit My Tabs link", "Unable to verify");
		}
		if(verifyMyTabs())
		{
			Reporters.SuccessReport("Verify My Tabs page", "My Tabs page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify My Tabs page", "My Tabs page is not displayed");
		}
		
		if(!AvailableTabText.isEmpty()){
			if(setToVisible(AvailableTabText)){
				Reporters.SuccessReport("Make tab visible "+AvailableTabText, getTabStatusMessage());
			}
			else{
				Reporters.failureReport("Make tab visible "+AvailableTabText, getTabStatusMessage());
			}
		}
		
		clickSubmitBtn();
		
		if(verifyInVisibleTabList(AvailableTabText)){
			Reporters.SuccessReport("Verify "+AvailableTabText+" tab in visible tab list", "Found");
		}
		else{
			Reporters.failureReport("Verify "+AvailableTabText+" tab in visible tab list", "Not Found");
		}
	}


	@Test(dataProvider = ("getInfo"), priority=1, groups= {"EMT", "RunAll"})
	public void EMT_HiddenTabs(String VisibleTabText) throws Throwable {

		if(myTabs())
		{
			Reporters.SuccessReport("Verify Edit My Tabs link", "Edit My Tabs link is successfull");
		}
		else
		{
			Reporters.failureReport("Verify Edit My Tabs link", "Edit My Tabs link failed");
		}

		if(verifyMyTabs())
		{
			Reporters.SuccessReport("Verify My Tabs page title", "My Tabs page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify My Tabs page title", "My Tabs page is not displayed");
		}

		if(!VisibleTabText.isEmpty()){

			if(setToAvailable(VisibleTabText)){
				Reporters.SuccessReport("Make tab available(hidden)", getTabStatusMessage());
			}
			else{
				Reporters.failureReport("Make tab available(hidden)", getTabStatusMessage());
			}
		}

		clickSubmitBtn();		

		if(verifyInAvailableTabList(VisibleTabText)){
			Reporters.SuccessReport("Verify "+VisibleTabText+" tab in Available tab list", "Found");
		}
		else{
			Reporters.failureReport("Verify "+VisibleTabText+" tab in Available tab list", "Not Found ");
		}
	}


	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTMyTabs", "MT");  //returning data from "EMTMyTabs" sheet and "MT" as a reference to read data
	}

	@DataProvider
	public Object[][] getInfo() throws Exception {

		return Data_Provider.getTableArray("EMTMyTabs", "EMT");  //returning data from "EMTMyTabs" sheet and "EMT" as a reference to read data
	}
	
	
}
