package com.alliancetech.mobile;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.businessfunctions.BusinessFunctionsM;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.accelerators.ActiondriverM;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class changeSurveyWhileTakingSurvey extends BusinessFunctions{
  @Test(dataProvider = ("getData"),groups={"MobileSurvey","RunAll"})
  public void chanfeSurveyWhileTakingMobileSurvey(String RegEmailID,String SessionID,String SessionTitle,String survey_Name,String type,String tableName) throws Throwable {
	  try{
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

				else {
					Reporters.failureReport("Login into the Application", "login is not successfull");
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
		
		DesiredCapabilities des = new DesiredCapabilities();
		des.setCapability("deviceName", "DROID MAXX");
		des.setCapability("platformName", "Android");
		des.setCapability("version", "4.4");
		des.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		driverM = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), des);
		driverM.get("http://qa4-automation.survey.ieventstest.com/");
		if(BusinessFunctionsM.surveyLogIn(RegEmailID))
		{
			Reporters.SuccessReport("Login into Survey Application", "login is successfull");
		}

		else{
			Reporters.failureReport("Login into Survey Application", "login is not successfull");
		}
		Thread.sleep(2000);

		if(ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnSessionName(SessionID, SessionTitle), SessionTitle))
			{
				ActiondriverM.click(SurveyOR.m_btnSessionName(SessionID, SessionTitle), SessionTitle);
				Thread.sleep(2000);
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

				//Delete the current question that the survey user is viewing. And See if the user is able to navigate to next page with or without answering the question
				if(BusinessFunctionsM.deleteAPage())
				{
					Reporters.SuccessReport("Delete a page with a survey question","Page not successfully deleted");
				}
				else
				{
					Reporters.failureReport("Delete a page with a survey question", "Page has failed to delete");
				}
				//Move the current question position to display as next question and see if the survey user is able to complete
				if(BusinessFunctionsM.moveCurrentQuestionToNextPosition())
				{
					Reporters.SuccessReport("Move the current question position to display as next question", "Successfully moved current survey question to display as next position");
				}
				else
				{
					Reporters.failureReport("Move the current question position to display as next question", "Failed to move current survey question to display as next position");
				}
				//Move the previous answered question position to display as next question and see if the survey user is able to complete
				//Remove the answer that is selected by survey user for the current question and verify the behaviour when he presses on Next button
				//Remove the answer that is selected by survey user for the previous answered question and verify the behaviour when he presses on Next button
				//Rename the Answer text that is selected by survey user for the current question and verify the behaviour and review the answers
				//Rename the Answer text that is selected by survey user for the previous answered question and verify the behaviour and review the answers
				//Rename the question text that is selected by survey user for the current question and verify the behaviour and review the answers.
				//Rename the question text that is selected by survey user for the previous answered question and verify the behaviour and review the answers
				//Make the survey question as optional that is selected by survey user for the current question and verify the behaviour and review the answers.
				//
				if(BusinessFunctionsM.modificationsInSurvey())
				{
					Reporters.SuccessReport("Remove answer seleted for survey questions,Rename answer text,question text of previously selected and current survey question,Make previouly answered and current survey questions optional", "All the validations are done successfully");
				}
				else
				{
					Reporters.failureReport("Remove answer seleted for survey questions,Rename answer text,question text of previously selected and current survey question,Make previouly answered and current survey questions optional","Failed to perform above steps");
				}

			}
		
	}
	

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Survey_TakeSurvey", "Key_TakeSurvey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}


}
