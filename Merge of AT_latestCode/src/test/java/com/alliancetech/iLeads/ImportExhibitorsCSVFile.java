package com.alliancetech.iLeads;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.IL_ImportExhibitorFile;
import com.cigniti.automation.utilities.CSVFileIO;
import com.cigniti.automation.utilities.Reporters;

public class ImportExhibitorsCSVFile extends BusinessFunctions{
	ArrayList<String>[] inputData ;
	public static int logIn=0;
	@Test(groups= {"iLeads", "RunAll"})
	public void uploadExhibitorsCSVFile() throws Throwable {
		inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\Ex.csv").getAbsolutePath());
		String file="Ex";
		try{
			if(logIn==0)
			{
				if(getiLeadsURL())
				{
					Reporters.SuccessReport("Launch iLeads Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
				}

				if(iLeadsLogIn())
				{
					Reporters.SuccessReport("Login into the Application", "login is successfull");
					logIn++;
				}

				else{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(Import_Exhibitor_Page())
		{
			Reporters.SuccessReport("Verify Import Exhibitors page title", "Successfully verified Import Exhibitors page title");
		}
		else
		{
			Reporters.failureReport("Verify Import Exhibitors page title", "Failed to verify Import Exhibitors Page title");
		}

		if(uploadExhibitorFile(new File("TestData\\Ex.csv").getAbsolutePath()))
		{
			Reporters.SuccessReport("Verify uploading of file", "Registrants csv file got successfully uploaded");
		}
		else
		{
			Reporters.failureReport("Verify uploading of file", "Registrants csv file failed to upload");
		}
		
		if(select_exhibitor_file(file))
		{
			Reporters.SuccessReport("Select exhibitor file from Exhibitor file list", "Successfully selected required Exhibitor file from list");
		}
		else
		{
			Reporters.failureReport("Select exhibitor file from Exhibitor file list", "Failed to select the Required exhibitor file from list");
		}
		
		if(click(IL_ImportExhibitorFile.btnContinue , "Continue button"))
		{
			Reporters.SuccessReport("Continue to Mapping fields operation", "Successfully navigated to Mapping Fields page");
		}
		else
		{
			Reporters.failureReport("Continue to Mapping fields operation", "Failed to navigate to mapping fields page");
		}
		
		if(mapFieldsOfExhibitorIniLeads())
		{
			Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
		}
		else
		{
			Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
		}
		
		if(completeImportProcess())
		{
			Reporters.SuccessReport("Complete Import Process", "Successfully completed import process");
		}
		else
		{
			Reporters.failureReport("Complete Import Process", "Failed to complete the Import process");
		}
		
		if(validateErrorMessagesAfterImportProcess())
		{
			Reporters.SuccessReport("Validate error message for each failed Event", "Successfully verified error message for each Failed event");
		}
		else
		{
			Reporters.SuccessReport("Validate error message for each failed Event", "Failed to validate the error messages");
		}
		
		if(verifyEventsData())
		{
			Reporters.SuccessReport("Verify Event data", "Successfully verified event data");
		}
		else
		{
			Reporters.failureReport("Verify Event data", "Failed to verify event data");
		}
		
		if(iLeadsLogOut())
		{
			Reporters.SuccessReport("Log out from iLeads application", "Logged out from the application successfuly");
		}
		else{
			Reporters.failureReport("Log out from iLeads application", "Failed to log out from the application");
		}
	}
		
}
