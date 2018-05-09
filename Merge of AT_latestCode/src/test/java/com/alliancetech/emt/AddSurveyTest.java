package com.alliancetech.emt;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddSurveyTest extends BusinessFunctions {
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups= {"EMT", "RunAll","Survey"})
  public void AddNewSurvey(String Survey_Name,String Survey_Status,String Survey_Type,String Survey_Flag,String Description) throws Throwable {
		try{
			if(logIn==0)
			{
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
					logIn++;
				}

				else {
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
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
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyTextPresent("Survey has been added!");
		
		if(dragAndDropSurveyQuestions())
		{
			Reporters.SuccessReport("Add all the survey questions required", "Required survey questions have been successfully added");
		}
		else
		{
			Reporters.failureReport("Add all the survey questions required", "Failed to add required survey questions");
		}
		if(submitAndverifySurvey(Survey_Name))
		{
			Reporters.SuccessReport("Submit survey"+Survey_Name, "Survey "+Survey_Name+" has been successfully displayed in Filter Survey table");
		}
		else
		{
			Reporters.failureReport("Submit survey"+Survey_Name, "Survey "+Survey_Name+" has failed to display in Filter survey table");
		}
		
		getEMTURL();
		emtLogout=true;
		
  }
  
  
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTAddSurvey", "Key_Survey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
	
	
}
