package com.alliancetech.checkIn;
/**
 * This Test Case is used to add associations in EMT site
 * Reads Data from EMTAddAssociation Sheet
 * reference Test rail-iConnect_EMT(Create Associations)
 */

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CheckIn_OnSlaveTest extends BusinessFunctions{


	@Test(dataProvider = ("getData"), groups={"checkin","RunAll"})
	public void CheckIn_OnSlave(String Registrant) throws Throwable, IOException{

		if(getSlave1CheckIn())
		{
			Reporters.SuccessReport("Launch Slave Checkin Application "+configProps.getProperty("Slave1EMT_URL"), "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch Slave Checkin Application "+configProps.getProperty("Slave1EMT_URL"), "Application URL failed to launch ");
		}

		if(checkInLogIn())
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if (isElementPresent(By.xpath("//div[@id='printer']//div/select[@id='printers']"), "Select Printer Pop Up")) {
			selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
			click(CheckInOR.btnChoosePrinter, "Choose Printer Button");
		}
		type(CheckInOR.txtRegistrant, Registrant, "Attendee # "+Registrant);
		click(CheckInOR.btnNext, "Next Button");

		verifyAndCLickCheckIn(Registrant);

		verifyTextPresent("Please confirm the following information to check in and print your badge");

		if(appendData()){
			Reporters.SuccessReport("Append data to above fields", "Successfully appended data to above fields");
		}
		else{
			Reporters.failureReport("Append data to above fields", "Failed to append data to above fields");
		}

		click(CheckInOR.btnConfirm, "Confirm Button");

		verifyTextPresent("Thank you  your check in is complete.");

		click(CheckInOR.btnDone, "Done Button");
		click(CheckInOR.btnLogout, "Logout Button");

		verifyCheckInData(configProps.getProperty("Slave1EMT_URL"), Registrant);
		emtLogOut();
		
		Reporters.SuccessReport("Sleep For "+(Integer.parseInt(configProps.getProperty("Interval"))+1)+" Minutes", "Sleeping...........For integration to happen");
		TimeUnit.MINUTES.sleep(Integer.parseInt(configProps.getProperty("Interval")));

		verifyCheckInData(configProps.getProperty("MasterEMT_URL"), Registrant);
		emtLogOut();
		//verifyCheckInData(configProps.getProperty("Slave2EMT_URL"), Registrant);
		//emtLogOut();
		
		
		
	}


	/*
	 * Reading data from checkin.xls under TestData folder	
	 */

	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Checkin_Registrant", "Key_Registrant");  //returning data from "Registrant" sheet and "Key_Registrant" as a reference to read data
	}

	

}
