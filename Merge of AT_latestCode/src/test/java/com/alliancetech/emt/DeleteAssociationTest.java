package com.alliancetech.emt;
/**
 * This Test Case is used to a delete an association from EMT site
 * reference sheet-iConnect-EMT(Delete Association)
 */

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class DeleteAssociationTest extends BusinessFunctions{
	public static int logIn=0;
	Workbook wb;

	@Test(groups= {"EMT", "RunAll"})
	public void EMT_DeleteAssociations() throws Throwable, IOException {

		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet Associations = wb.getSheet("EMTAddAssociation");
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
		deleteAssociations(Associations);
		wb.close();
		
		getEMTURL();
		emtLogout=true;
	}
	
}
