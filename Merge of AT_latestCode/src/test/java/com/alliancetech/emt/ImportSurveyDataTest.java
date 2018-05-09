package com.alliancetech.emt;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.cigniti.automation.utilities.CSVFileIO;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class ImportSurveyDataTest extends BusinessFunctions{
	String[][] inputData1 ;
  @Test(dataProvider = ("getData"),groups= {"Survey", "RunAll"})
  public void importSurveyCSVFileAndVerifyData(String Survey_Name,String Survey_Status,String Survey_Type,String Survey_Flag,String Description) throws Throwable {
	  inputData1=new CSVFileIO().getAllRecordsAs2DArray(new File("TestData\\survey.csv").getAbsolutePath());
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
		
		click(CommonOR.lnkAddANewSurvey,"Add  a new survey link");
		
		verifyText(CommonOR.txtAddSurvey,"Add Survey", "Add Survey page is displayed");
		
		if(addSurveyInformation(Survey_Name, Survey_Status, Survey_Type,Survey_Flag, Description))
		{
			Reporters.SuccessReport("Add Survey Information", Survey_Name+" survey information added successfully");
		}
		else
		{
			Reporters.failureReport("Add Survey Information", Survey_Name+" survey information has not added");
		}
	  
		if(uploadFile(new File("TestData\\survey.csv").getAbsolutePath()))
		{
			Reporters.SuccessReport("Verify uploading of file", "survey csv file got successfully uploaded");
		}
		else
		{
			Reporters.failureReport("Verify uploading of file", "survey csv file failed to upload");
		}
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyTextPresent("Survey has been added!");
		
		if(verifySurveyCSVData(inputData1, ""))
		{
			Reporters.SuccessReport("Verify all the survey questions", "All Survey questions have been verified successfully");
		}
		else
		{
			Reporters.failureReport("Verify all the survey questions", "Failed to verify survey question because of the above failed survey question");
		}
		
		if(verifySurveyRequiredQuestions(inputData1))
		{
			Reporters.SuccessReport("Verify whether survey questions are marked as Required or not", "Above survey questions are successfully marked as mandatory");
		}
		else
		{
			Reporters.failureReport("Verify whether survey questions are marked as Required or not", "Failed to make above survey questions as mandatory");
		}
		
		click(SurveyOR.lnkCancel,"Cancel link");
		
		if(deleteSurvey(Survey_Flag))
		{
			Reporters.SuccessReport("Delete Survey with flag "+Survey_Flag, "Survey with flag "+Survey_Flag+" has been deleted");
		}
		else
		{
			Reporters.failureReport("Delete Survey with flag "+Survey_Flag, "Survey with flag "+Survey_Flag+" has not deleted");
		}
		
		if(!verify_In_Table(SurveyOR.lstSearchResults, Survey_Name))
		{
			Reporters.SuccessReport("Verify survey "+Survey_Name+" in table after deleting the survey", "Survey is not available in table");
		}
		else
		{
			Reporters.failureReport("Verify survey "+Survey_Name+" in table after deleting the survey", "Survey is still available in table");
		}
		getEMTURL();
		emtLogout=true;
 }
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMT_UploadSurvey", "Key_UploadSurvey");  //returning data from "EMT_UploadSurvey" sheet and "Key_UploadSurvey" as a reference to read data
	}
	
	
}
