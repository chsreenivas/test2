package com.alliancetech.emt;
/**
 * This Test Case is used to delete a Registrant from EMT site
 * reference sheet-iConnect-EMT(Delete Registrant)
 */

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Reporters;

public class DeleteRegistrantTest extends BusinessFunctions{
	Workbook wb;

	@Test(groups= {"EMT", "RunAll"})
	public void EMT_DeleteRegistrants() throws Throwable {
		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet Reg = wb.getSheet("EMTReg");
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

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		deleteRegistrants(Reg);
				
		wb.close();
		
		getEMTURL();
		
		emtLogout=true;
		
	}
	
}


