package com.alliancetech.survey;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CompleteConferenceSurvey extends BusinessFunctions{
	public static int logIn=0;
	public static String id=null;
	@Test(dataProvider = ("getData"),groups= {"Survey", "RunAll","EMT"})
	public void completeConferenceSurvey(String RegEmailID,String survey_Name,String type) throws Throwable {

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
					Reporters.SuccessReport("Login into Survey Application", "login is successfull");
					logIn++;
				}

				else{
					Reporters.failureReport("Login into Survey Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		if(navigateToManageSurveysPage())
		{
			Reporters.SuccessReport("Navigate to Manage Surveys Page", "Successfully navigated to manage surveys page");
		}
		else
		{
			Reporters.failureReport("Navigate to Manage Surveys Page", "Failed to navigate to manage surveys page");
		}
		
		if(click(By.xpath("//td[text()='"+type+"']"),"Survey with flag "+type))
		{
			id=getSurveyID();
			click(SurveyOR.lnkCancel,"Cancel link");
			if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
			  {
					  Reporters.SuccessReport("Verify Page", "Administration page is displayed");
			  }
			  else
			  {
				  Reporters.failureReport("Verify Page", "Administration page is not displayed");
			  }
			  
			  if(navigateToSurveyXML())
			  {
				  Reporters.SuccessReport("Navigate to Survey XML Site", "Successfully navigated to Survey XML Site");
			  }
			  else
			  {
				  Reporters.failureReport("Navigate to Survey XML Site", "Faile to navigate to Survey XML Site");
			  }
			  
			  switchToFrameByIndex(0);
			  
			  if(removeSurveyIDTag(XML_OR.tagsurveys_search_setting))
			  {
				  Reporters.SuccessReport("Remove surveys-search-setting Tag for session type "+type, "surveys-search-setting tag is removed successfully");
			  }
			  else
			  {
				  Reporters.failureReport("Remove surveys-search-setting Tag for session type "+type, "surveys-search-setting Tag is not removed");
			  }
			  
			  if(insertConferenceSurveyID(type,id))
			  {
				  Reporters.SuccessReport("Make surveys-search-setting tag type as "+type, "Successfully inserted new  surveys-search-setting tag with type: "+type);
			  }
			  else
			  {
				  Reporters.failureReport("Make surveys-search-setting tag type as "+type, "Failed to insert new  surveys-search-setting tag with type: "+type);
			  }
			  
			  clickSaveXMLFileButton();
			  
			  if(openSiteInNewWindow("Survey site for Testing NowNext for Innovate"))
				{
					getSurveyURL();
					Reporters.SuccessReport("Open Survey Site in new window", "Survey Site has been opened in new window");
				}
				else
				{
					Reporters.failureReport("Open Survey Site in new window","Survey site has failed to open in new window");
				}

				if(surveyLogIn(RegEmailID))
				{
					Reporters.SuccessReport("Login into Survey Application", "login is successfull");
				}

				else{
					Reporters.failureReport("Login into Survey Application", "login is not successfull");
				}
				
				click(SurveyOR.lnkConferenceSession,"Conference Session");
				
				if(takeSurvey("", ""))
				{
					Reporters.SuccessReport("Take Survey for session of type "+type, "Successfully taken survey for session of type "+type);
				}
				else
				{
					Reporters.failureReport("Take Survey for session of type "+type, "Failed to complete survey for session of type "+type);
				}
				
				click(SurveyOR.lnkConferenceSession,"Conference Session");
				
				if(validateSurveyAnswers())
				{
					Reporters.SuccessReport("Verify survey answers", "All survey answers are saved successfully");
				}
				else
				{
					Reporters.failureReport("Verify survey answers", "All survey answers are failed to save");
				}
				
				if(surveyLogOut())
				{
					Reporters.SuccessReport("Logout from survey site", "Logged out from survey site successfully");
				}
				else
				{
					Reporters.failureReport("Logout from survey site","Failed to log out from survey site");
				}
				
				driver.close();
				switchWindowByTitle("Reporting Site", 1);
				
				//Clear and Delete Conference Survey
				if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
				{
					Reporters.SuccessReport("Verify Page", "Administration page is displayed");
				}
				else
				{
					Reporters.failureReport("Verify Page", "Administration page is not displayed");
				}

				if(navigateToManageSurveysPage())
				{
					Reporters.SuccessReport("Navigate to Manage Surveys Page", "Successfully navigated to manage surveys page");
				}
				else
				{
					Reporters.failureReport("Navigate to Manage Surveys Page", "Failed to navigate to manage surveys page");
				}
				
				if(click(By.xpath("//td[text()='"+survey_Name+"']"), survey_Name))
				{
					if (clearSurvey())
					{
						Reporters.SuccessReport("Clear Survey " + survey_Name,"Survey has been successfully cleared");
					} 
					else {
						Reporters.failureReport("Clear Survey " + survey_Name,"Survey has failed to clear");
					}
					
					waitForElementPresent(SurveyOR.lnkCancel);
					js_click(SurveyOR.lnkCancel, "Cancel link");

					if (deleteSurvey(survey_Name))
					{
						Reporters.SuccessReport("Delete Survey  "+ survey_Name, "Survey with flag " + survey_Name+ " has been deleted");
					} else {
						Reporters.failureReport("Delete Survey "+ survey_Name, "Survey with flag " + survey_Name+ " has not deleted");
					}

					if (!verify_In_Table(SurveyOR.lstSearchResults, survey_Name)) 
					{
						Reporters.SuccessReport("Verify survey " + survey_Name+ " in table after deleting the survey","Survey is not available in table");
					} 
					else 
					{
						Reporters.failureReport("Verify survey " + survey_Name+ " in table after deleting the survey","Survey is still available in table");
					}
				}
				
		}
		
		getEMTURL();
		emtLogout=true;
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Survey_TakeSurvey", "Key_TakeConferenceSurvey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
	
	
}
