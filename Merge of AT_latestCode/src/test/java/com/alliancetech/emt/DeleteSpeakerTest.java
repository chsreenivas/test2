package com.alliancetech.emt;
/**
 * This Test Case is used to delete a Speaker from EMT site
 * reference Sheet-iConnect-EMT(Delete Speaker)
 */

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddSpeaker_OR;
import com.cigniti.automation.utilities.Reporters;

public class DeleteSpeakerTest extends BusinessFunctions{

	Workbook wb;

	@Test(groups= {"EMT", "RunAll"})
	public void EMT_DeleteSpeakers() throws Throwable {

		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet Speaker = wb.getSheet("EMTAddSpeaker");
		if(getEMT()){
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

		if(clickTabFromViewMore(CommonOR.lnkSpeakers,"Speakers Tab")){
			Reporters.SuccessReport("Verify Page", "Speakers page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Speakers page is not displayed");
		}
		verifyText(EMT_AddSpeaker_OR.txtSpeakersTitle, "Speakers", "Speakers Page");
		deleteSpeakers(Speaker);		
		wb.close();
		
		getEMTURL();
		
		emtLogout=true;
	}
	
	
}
