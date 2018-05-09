package com.alliancetech.checkIn;
/**
 * This Test Case is used to add associations in EMT site
 * Reads Data from EMTAddAssociation Sheet
 * reference Test rail-iConnect_EMT(Create Associations)
 */

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CheckInOR;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class SingleFetchTest extends BusinessFunctions{

	@Test(dataProvider="getData",groups={"checkin","RunAll"})
	public void testSingleFetch(String Regsitrant) throws Throwable, IOException{
		
		if(getURL(configProps.getProperty("Slave1EMT_URL"))){
			Reporters.SuccessReport("Navigate to Slave1EMT "+configProps.getProperty("Slave1EMT_URL"), "Navigated Successfully");
		}
		else{
			Reporters.failureReport("Navigate to Slave1EMT "+configProps.getProperty("Slave1EMT_URL"), "Unable to Navigate");
		}
		emtLogIn();
		clickTabFromViewMore(CommonOR.lnkAdmin, "Administration Tab");
		click(CheckInOR.lnkCheckinXML, "Checkin xml link");
		
		switchToFrameByIndex(0);
		removeTag(CheckInOR.fetch);
		
		new Actions(driver).sendKeys("<enable-registration-fetch-on-rnf>true</enable-registration-fetch-on-rnf>").perform();
		driver.switchTo().defaultContent();
		click(XML_OR.btnSaveXMLFile, "Save Button");
		
		clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab");
		
		search(Regsitrant);
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Registrants Loading Icon");
		
		if(click(By.xpath("//td[text()='"+Regsitrant+"']"), Regsitrant)){
			js_click(EMT_RegistrantsOR.lnkDeleteThisRegistrant, "DeleteThisRegistrant link");
			js_click(EMT_RegistrantsOR.btnConfirm, "Confirm button");
		}
		
		search(Regsitrant);
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Registrants Loading Icon");
		
		verifyTextNotPresent(Regsitrant);
		
		emtLogOut();
		
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
		
		selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
		click(CheckInOR.btnChoosePrinter, "Choose Printer Button");

		type(CheckInOR.txtRegistrant, Regsitrant, "Attendee # "+Regsitrant);
		click(CheckInOR.btnNext, "Next Button");
		waitForInVisibilityOfElement(By.xpath("//div[@class='table-container']/div/div"), "Loading Icon");
		
		verifyAndCLickCheckIn(Regsitrant);
		
		verifyTextPresent("Please confirm the following information to check in and print your badge");
		click(CheckInOR.btnConfirm, "Confirm Button");
		
		verifyTextPresent("Thank you  your check in is complete.");
		click(CheckInOR.btnDone, "Done Button");
		click(CheckInOR.btnLogout, "Logout Button");
		
		if(getURL(configProps.getProperty("Slave1EMT_URL"))){
			Reporters.SuccessReport("Navigate to Slave1EMT "+configProps.getProperty("Slave1EMT_URL"), "Navigated Successfully");
		}
		else{
			Reporters.failureReport("Navigate to Slave1EMT "+configProps.getProperty("Slave1EMT_URL"), "Unable to Navigate");
		}
		emtLogIn();
		
		clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab");
		
		search(Regsitrant);
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Registrants Loading Icon");

		click(By.xpath("//td[text()='"+Regsitrant+"']"), Regsitrant);
		
		verifyText(CommonOR.lblddValues("Checked-in"), "Yes", "Checked In Status Validation");
		
		emtLogout = true;
		
		/*if(getSlave1CheckIn())
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

		selectByIndex(CheckInOR.ddPrinter, 1, "Select A Printer");
		click(CheckInOR.btnChoosePrinter, "Choose Printer Button");

		type(CheckInOR.txtRegistrant, Registrant, "Attendee # "+Registrant);
		click(CheckInOR.btnNext, "Next Button");

		verifyAndCLickCheckIn(Registrant);

		verifyTextPresent("Please confirm the following information to check in and print your badge");

		appendData();

		click(CheckInOR.btnConfirm, "Confirm Button");

		verifyTextPresent("Thank you  your check in is complete.");

		click(CheckInOR.btnDone, "Done Button");
		click(CheckInOR.btnLogout, "Logout Button");

		verifyCheckInData(configProps.getProperty("Slave1EMT_URL"), Registrant);
		emtLogOut();
		
		Reporters.SuccessReport("Sleep For "+(Integer.parseInt(configProps.getProperty("Interval"))+1)+" Minutes", "Sleeping...........For integration to happen");
		TimeUnit.MINUTES.sleep(Integer.parseInt(configProps.getProperty("Interval")));

		verifyCheckInData(configProps.getProperty("MasterEMT_URL"), Registrant);
		//emtLogOut();
		//verifyCheckInData(configProps.getProperty("Slave2EMT_URL"), Registrant);
		//emtLogOut();
*/		
		
	}

	/*
	 * Reading data from checkin.xls under TestData folder	
	 */

	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("CheckIn_AddNewRegistrant", "Key_Registrant");  //returning data from "Registrant" sheet and "Key_Registrant" as a reference to read data
	}

	

}
