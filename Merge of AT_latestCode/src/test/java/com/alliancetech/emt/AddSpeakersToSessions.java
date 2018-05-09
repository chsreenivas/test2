package com.alliancetech.emt;
/**
 * This Test Case is used to add Speakers to a session
 * reference sheet-iConnect-EMT(Manage Speaker Details)
 */
import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.cigniti.automation.utilities.Reporters;
public class AddSpeakersToSessions extends BusinessFunctions{
	Workbook wb;
	@Test(groups= {"EMT", "RunAll"})
	public void EMT_AddSpeakerToSession() throws Throwable {

		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet SessionSheet = wb.getSheet("EMTAddSession");
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
		
		clickTabFromViewMore(CommonOR.lnkSpeakers, "Speakers Tab");

		addSpeakerToSession(SessionSheet);

		wb.close();
		
		getEMTURL();
		emtLogout=true;
	}
	
}
