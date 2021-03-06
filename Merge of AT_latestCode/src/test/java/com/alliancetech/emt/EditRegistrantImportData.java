package com.alliancetech.emt;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.DataImports;
import com.cigniti.automation.utilities.CSVFileIO;
import com.cigniti.automation.utilities.Reporters;

public class EditRegistrantImportData extends BusinessFunctions{
	ArrayList<String>[] inputData ;
  @Test(groups={"EMT","RunAll"})
  public void editImportDataOfRegistrants() throws Throwable {
	  if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
	  {
		  driver.unregister(myListener);
	  }
	  inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\EditImportRegData.csv").getAbsolutePath());
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
		}
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		click(DataImports.lnkImportRegistrantsData, "Import Registrants Data");
		
		if(uploadFile(new File("TestData\\EditImportRegData.csv").getAbsolutePath()))
		{
			Reporters.SuccessReport("Verify uploading of file", "Registrants csv file got successfully uploaded");
		}
		else
		{
			Reporters.failureReport("Verify uploading of file", "Registrants csv file failed to upload");
		}
		if(js_click(DataImports.btnUpload, "Upload Button"))
		{
			Reporters.SuccessReport("Verify Upload button", "Able to click on: Upload Button");
		}
		else
		{
			Reporters.failureReport("Verify Upload button","Not Able to click on: Upload Button");
		}
		
		waitForInVisibilityOfElement(By.id("status-msg-display"), "Auto Refresh");
		
		if(driver.getPageSource().contains("Confirm?")){
			click(DataImports.btnOK_confirmPopUp,"Ok button in Confirm Popup");
		}
		
		Thread.sleep(5000);
		if(mapFields())
		{
			Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
		}
		else
		{
			Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
		}
		
		if(validateDataAndProgressOperation())
		{
			Reporters.SuccessReport("Verify data validation and progress operation", "Data has been successfully validated and 100% progressed");
		}
		else
		{
			Reporters.failureReport("Verify data validation and progress operation", "Data has failed to validate and stopped progression");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		if(verifyCSVData(inputData,"Attendee #"))
		{
			Reporters.SuccessReport("Verification of Registrant details uploaded using csv file", "All the registrant information is valid");
		}
		else
		{
			Reporters.failureReport("Verification of Registrant details uploaded using csv file", "Registrant information is not valid");
		}
		
		if(deleteImportData(inputData,"Attendee #"))
		{
			Reporters.SuccessReport("Delete import data records", "Deleted all import records succcessfully");
		}
		else
		{
			Reporters.failureReport("Delete import data records", "Failed to delete all import records");
		}
		//driver.register(myListener);
		Thread.sleep(3000);
		emtLogout=true;
  }
	  
  }

