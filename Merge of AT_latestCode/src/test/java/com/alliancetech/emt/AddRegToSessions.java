package com.alliancetech.emt;
/**
 * This Test Case is used to enroll one attendee to multiple sessions - Reads Data from Sheet: Attendee
 * Takes one attendee and enrolls him for all the sessions mentioned in the EMTAddSessions Sheet
 * And Repeats the same for all the attendees mentioned in the sheet: Attendee
 * reference sheet-iConnect-EMT(Registrant Enrollment to a session)
 */

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Reporters;

public class AddRegToSessions extends BusinessFunctions{
	Sheet AttendeeSheet;
	Sheet SessionSheet;
	Workbook wb;
	@Test(groups= {"EMT", "RunAll"})
	public void EMT_AddRegToSessions() throws Throwable {

		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		AttendeeSheet = wb.getSheet("AddRegToSession");
		SessionSheet = wb.getSheet("EMTAddSession");

		if(getEMTURL()){
			Reporters.SuccessReport("Navigate to EMT", "Navigated to EMT Site");
		}
		else{
			Reporters.failureReport("Navigate to EMT", "Unable to Navigate to EMT");
		}
		
		if(emtLogIn()){
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		
		clickTabFromViewMore(EMT_RegistrantsOR.tabRegistrants, "Registrants Tab");
		addRegToSessions1(AttendeeSheet,SessionSheet);
		wb.close();
		getEMTURL();
		emtLogout=true;
	}
	
}
