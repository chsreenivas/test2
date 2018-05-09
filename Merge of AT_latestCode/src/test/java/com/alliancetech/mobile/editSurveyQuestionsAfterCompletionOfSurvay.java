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

public class editSurveyQuestionsAfterCompletionOfSurvay extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups={"EMT","RunAll"})
  public void editSurvey(String survey_Name,String RegEmailID,String type) throws Throwable {
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

		/*if(openSiteInNewWindow("Survey site for Testing NowNext for Innovate"))
		{
			getSurveyURL();
			Reporters.SuccessReport("Open Survey Site in new window", "Survey Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open Survey Site in new window","Survey site has failed to open in new window");
		}*/

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

		ActiondriverM.waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
		
		ActiondriverM.click(SurveyOR.m_editSurvey,"");

		if(BusinessFunctionsM.validateSurveyQuestions())
		{
			Reporters.SuccessReport("Verify survey questions in Survey site", "Edited survey has been displayed in survey site");
		}
		else
		{
			Reporters.failureReport("Verify survey questions in Survey site", "Edited survey has not displayed in survey site");
		}
		
		ActiondriverM.click(SurveyOR.m_btnBacktoList, "Back to List");
		ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
		ActiondriverM.click(SurveyOR.m_btnMenu, "Show Menu Button");
		if(BusinessFunctionsM.surveyLogOut())
		{
			Reporters.SuccessReport("Logout from survey site", "Logged out from survey site successfully");
		}
		else
		{
			Reporters.failureReport("Logout from survey site","Failed to log out from survey site");
		}

		driverM.quit();

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
