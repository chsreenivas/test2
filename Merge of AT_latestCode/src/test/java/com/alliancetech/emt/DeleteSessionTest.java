package com.alliancetech.emt;
/**
 * This Test Case is used to delete a session from EMT site
 * reference Sheet-iConnect-EMT(Delete Session)
 */

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Reporters;

public class DeleteSessionTest extends BusinessFunctions{

	Workbook wb;

	@Test(groups= {"EMT", "RunAll"})
	public void EMT_DeleteSessions() throws Throwable {
		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet Session = wb.getSheet("EMTAddSession");
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
		
		if(clickTabFromViewMore(CommonOR.lnkSessions,"Sessions Tab")){
			Reporters.SuccessReport("Verify Page", "Sessions page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Sessions page is not displayed");
		}
		verifyText(EMT_SessionsOR.txtSessionTitle, "Sessions", "Sessions Page");

		deleteSessions(Session);
			
		wb.close();
		
		getEMTURL();
		
		emtLogout=true;
	}
	
	
}
