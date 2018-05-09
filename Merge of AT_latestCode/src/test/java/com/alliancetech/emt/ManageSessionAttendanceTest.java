package com.alliancetech.emt;
/**
 * This Test Case verifies Sessions attendance
 * reference Test rail-iConnect_EMT(Session Attendance)
 */
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class ManageSessionAttendanceTest extends BusinessFunctions{
	public static int logIn=0;

	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void EMT_ManageSessionAttendance(String title,String Date,String StartTime,String EndTime,String Registrant) throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Session page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Session page is not displayed");
		}

		verifyText(EMT_SessionsOR.txtSessionTitle, "Sessions", "Session page title");

		if(search(title))
		{
			Reporters.SuccessReport("Verification of Searching for added Session",title+"Session displayed");
		}
		else
		{
			Reporters.failureReport("Verification of Searching for added Session",title+"Session is not displayed");
		}
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Sessions"), "Loading");
		if(click(By.xpath("//tr/td[text()='"+title+"']"), title))
		{
			if (!verifySessionAttendance(Registrant)) {
				if (sessionAttendance(Registrant)) {
					Reporters.SuccessReport("Verification of Session attendance",
							"Sessions attendance is done successfully");
				} else {
					Reporters.failureReport("Verification of Session attendance",
							"Sessions attendance is failed");
				}
				if (verifySessionAttendance(Registrant)) {
					Reporters.SuccessReport("Verification of Session attendance",
							"Sessions attendance verification for registrant "
									+ Registrant + " is done successfully");
				} else {
					Reporters.failureReport("Verification of Session attendance",
							"Sessions attendance verification " + Registrant
							+ " is failed");
				}
			}
			else
			{
				Reporters.SuccessReport("Verification of Session attendance", "Registrant has already attended for the session "+title);
			}
		}
		else
		{
			Reporters.failureReport("View Details for session "+title, "Unable to click on session "+title);
		}

		getEMTURL();
		emtLogout=true;

	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTManageSessionAttendance", "Key_SessionAttendance");  //returning data from "EMTManageSessionAttendance" sheet and "Key_SessionAttendance" as a reference to read data
	}

}

