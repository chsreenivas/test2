package com.alliancetech.portal_sessions;
/**
 * This Test Case verifies
 * i) Sessions List elements
 * ii) Updation of  N records
 * iii) Previous button 
 * iv) Next button 
 * v) Updation of Page N of N option
 * reference sheet- iConnect-Sessions(Sessions List)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class verifySessionPaginationTest extends BusinessFunctions{
	public static int logIn=0;
	int logOut=0;
	ExcelReader ereader=new ExcelReader(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName"), "Pagination",1 );
	int lastRowNum=ereader.getLastRow()-1;
	@Test(dataProvider = ("getAnswers"))
	public void verifySessionPagination(String pageOption) throws Throwable {
		try{
			if(logIn==0)
			{
				if(getPortalURL())
				{
					Reporters.SuccessReport("Open Portal Application", "Application URL has typed successfully");
				}
				else
				{
					Reporters.failureReport("Open Portal Application", "Application is failed to open ");
				}

				if(Portal_Login())
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

		if(sessionListElements())
		{
			Reporters.SuccessReport("Verification of session List", "Sessions List is displayed with "+recordnum+" records ,Previous,Next links,Page option, Sessions details.");
		}
		else{
			Reporters.failureReport("Verification of session List", "Sessions List is not displayed with "+recordnum+" records option,Previous,Next links,Page option, Sessions details.");
		}

		if(nRecordsDisplay())
		{
			Reporters.SuccessReport("Verify that Display "+recordnum+" records option is updated.", +recordnum+" records option is updated, "+getNRecords()+" records reflected in the Sessions List.");
		}
		else{
			Reporters.failureReport("Verify that Display "+recordnum+" records option is updated.", +recordnum+" records option is updated, "+getNRecords()+" records reflected in the Sessions List.");
		}

		if(nextButton())
		{
			Reporters.SuccessReport("Verification of Next button", "Next button is selected "+getNRecords()+" records reflected in the Sessions List.");
		}
		else{
			Reporters.failureReport("Verification of Next button", " Next button is not selected");
		}

		if(previousButton())
		{
			Reporters.SuccessReport("Verification of previous button", "Previous button is selected "+getNRecords()+" records reflected in the Sessions List.");
		}
		else{
			Reporters.failureReport("Verification of previous Records", " Previous button is not selected");
		}

		if(updatePageOption(pageOption))
		{
			Reporters.SuccessReport("Verification for updation of page option", " Page option is updated, "+getNRecords()+" records are reflected in the Sessions List.");
		}
		else{
			Reporters.failureReport("Verification for updation of page option", "  Page option is not updated");
		}

		logOut++;
		if(logOut==lastRowNum){
			if(logOut())
			{
				Reporters.SuccessReport("Verification of log out", "Application logged out successfully");
			}
			else{
				Reporters.failureReport("Verification of log out", "Application failed to log out");
			}
		}
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getAnswers() throws Exception {
		return Data_Provider.getTableArray("Pagination", "Key_PageNumber");  //returning data from "Answer" sheet and "Productfinder" as a reference to read data
	}


}
