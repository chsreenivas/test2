package com.alliancetech.emt;
/**
 * This Test Case is used to enroll one attendee to one session
 * Reads attendee data from EMTManageSessionEnrollment Sheet
 * Reads session data from EMTAddSessions Sheet
 * reference Test rail-iConnect_EMT(Session Enrollment)
 */
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class ManageSessionEnrollmentTest extends BusinessFunctions{
	public static int logIn=0;
	
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void EMT_ManageSessionEnrollment(String title,String Date,String StartTime,String EndTime,String Registrant) throws Throwable {

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

		if(sessionEnrollment(title,StartTime,EndTime,Date,Registrant))
		{
			Reporters.SuccessReport("Verification of Session enrollment", "Session enrollment is done successfully");
		}
		else
		{
			Reporters.failureReport("Verification of Session enrollment", "Session enrollment is failed");
		}

		if(verifySessionEnrollment(Registrant))
		{
			Reporters.SuccessReport("Verification of Session enrollment", "Session enrollment verification for registrant "+Registrant+" is done successfully");
		}
		else
		{
			Reporters.failureReport("Verification of Session enrollment", "Session enrollment verification for registrant "+Registrant+" is failed");
		}
		
		getEMTURL();
		
		emtLogout=true;

		/*if(deleteSessionEnrollment(Registrant))
		{
			Reporters.SuccessReport("Deletion of registrant from Session enrollment", "Registrant "+Registrant+" deleted successfully");
		}
		else
		{
			Reporters.failureReport("Deletion of registrant from Session enrollment", "Registrant "+Registrant+" deletion failed");
		}*/
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTManageSessionEnrollment", "Key_SessionEnrollment");  //returning data from "EMTManageSessionEnrollment" sheet and "Key_SessionEnrollment" as a reference to read data
	}
	
}
