package com.alliancetech.emt;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class EditSurvey extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups={"EMT","RunAll","Survey"})
	public void edit_Survey(String survey_Name,String RegEmailID,String type) throws Throwable {
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

		if(removeTag(XML_OR.txtSurveyTagToRemove(type)))
		{
			Reporters.SuccessReport("Remove default-session-survey-type Tag for "+type+"", "default-session-survey-type tag is removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove default-session-survey-type Tag for "+type+"", "default-session-survey-type Tag is not removed");
		}

		if(insertSurveyLookUpTypesTag(type))
		{
			Reporters.SuccessReport("Make default-session-survey-type tag type as "+type, "Successfully inserted new  default-session-survey-type tag with type: "+type);
		}
		else
		{
			Reporters.failureReport("Make default-session-survey-type tag type as "+type, "Failed to insert new  default-session-survey-type tag with type: "+type);
		}

		clickSaveXMLFileButton();

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
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

		click(CommonOR.openSurvey(survey_Name),"Survey "+survey_Name);

		verifyText(CommonOR.txtEditSurvey, "Edit Survey", "Edit Survey page");

		//isVisible(CommonOR.txtWarning, "Warning message is present");

		if(editSurveyQuestions("Matrix / One Answer"))
		{
			Reporters.SuccessReport("Edit "+survey_Name, survey_Name+" has been successfully edited");
		}
		else
		{
			Reporters.failureReport("Survey "+survey_Name, "Failed to edit Survey "+survey_Name);
		}

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

		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		
		click(SurveyOR.btnEditSurvey,"Edit Survey Button");

		if(validateSurveyQuestions())
		{
			Reporters.SuccessReport("Verify survey questions in Survey site", "Edited survey has been displayed in survey site");
		}
		else
		{
			Reporters.failureReport("Verify survey questions in Survey site", "Edited survey has not displayed in survey site");
		}

		if(surveyLogOut())
		{
			Reporters.SuccessReport("Logout from survey site", "Logged out from survey site successfully");
		}
		else
		{
			Reporters.failureReport("Logout from survey site","Failed to log out from survey site");
		}

		if(closeWindow("Survey site for Testing NowNext for Innovate",1)){
			Reporters.SuccessReport("Close Survey site window", "Survey site window has been closed successfully");
		}
		else
		{
			Reporters.failureReport("Close Survey site window", "Survey site window has failed to close");
		}

		switchWindowByTitle("", 1);

		if(!deleteASurveyQuestion())
		{
			Reporters.SuccessReport("Delete A Survey Question", "Survey question successfully deleted");
		}
		else
		{
			Reporters.failureReport("Delete A Survey Question", "Failed to delete the survey question");
		}
		if(clearSurvey())
		{
			Reporters.SuccessReport("Clear Survey "+survey_Name, "Survey has been successfully cleared");
		}
		else
		{
			Reporters.failureReport("Clear Survey "+survey_Name, "Survey has failed to clear");
		}

		click(SurveyOR.lnkCancel,"Cancel link");

		getEMTURL();

		emtLogout=true;
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTAddSurvey", "Key_EditSurvey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}

}
