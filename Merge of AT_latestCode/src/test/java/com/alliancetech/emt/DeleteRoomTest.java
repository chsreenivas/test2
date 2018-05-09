package com.alliancetech.emt;
/**
 * This Test Case is used to delete a Room from EMT site
 * reference sheet-iConnect-EMT(Delete Room)
 */

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RoomsOR;
import com.cigniti.automation.utilities.Reporters;

public class DeleteRoomTest extends BusinessFunctions{
	Workbook wb;
	@Test(groups= {"EMT", "RunAll"})
	public void EMT_DeleteRooms() throws Throwable{

		wb = Workbook.getWorkbook(new File(configProps.getProperty("TestData")+configProps.getProperty("ExcelFileName")));
		Sheet Room = wb.getSheet("EMTRoom");
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
		
		if(clickTabFromViewMore(CommonOR.lnkRooms,"Rooms Tab")){
			Reporters.SuccessReport("Verify Page", "Rooms page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Rooms page is not displayed");
		}
		
		verifyText(EMT_RoomsOR.txtRoomsTitle, "Rooms", "Rooms Page");
		
		deleteRooms(Room);
		
		wb.close();
		getEMTURL();
		emtLogout=true;
	}
	
}
