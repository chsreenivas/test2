package com.alliancetech.mobile;

import io.appium.java_client.android.AndroidDriver;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.businessfunctions.BusinessFunctionsM;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.accelerators.ActiondriverM;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class completeMobileSurvey extends BusinessFunctions{

	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"MobileSurvey", "RunAll"})
	public void takeMobileSurvey(String RegEmailID,String SessionID,String SessionTitle,String survey_Name,String type,String TableName) throws Throwable{

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
		//des.setCapability("browserName", "Chrome");
		des.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

		/*des.setCapability("appActivity", "");
		des.setCapability("appPackage", "");
		 */
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
		if(BusinessFunctionsM.navigateToAddSessionSurveys())
		{
			Reporters.SuccessReport("Navigate to Add Session Surveys page", "Successfully navigated to Add Session Surveys page");
		}
		else
		{
			Reporters.failureReport("Navigate to Add Session Surveys page", "Failed to navigate to Add Session Surveys Page");
		}
		Thread.sleep(2000);
		if(BusinessFunctionsM.searchForSession(SessionID,SessionTitle))
		{
			Reporters.SuccessReport("Search for session "+SessionTitle, "Session is successfully available in Results List");
			ActiondriverM.click(SurveyOR.m_btnSessionName(SessionID, SessionTitle), SessionTitle);
			
			if(BusinessFunctionsM.takeSurvey(SessionID, SessionTitle))
			{
				Reporters.SuccessReport("Take Survey for session"+SessionTitle, "Successfully taken survey for session "+SessionTitle);
			}
			else
			{
				Reporters.failureReport("Take Survey for session"+SessionTitle, "Failed to complete survey for session "+SessionTitle);
			}
			//ActiondriverM.click(SurveyOR.lnkMySessionEvaluations,"My Session Evaluations");

			ActiondriverM.click(SurveyOR.m_btnBacktoList, "Back to List");
			ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
			ActiondriverM.click(SurveyOR.m_btnSearchSessionButton, "Seach Session plus Button");

			if(BusinessFunctionsM.searchForDidNotAttendOrAlreadyTakenSession(SessionTitle))
			{
				Reporters.SuccessReport("Search for the session "+SessionTitle+" again", "As the session's survey is already taken,it has not appeared in search results");
			}
			else
			{
				Reporters.failureReport("Search for the session "+SessionTitle+" again", "Session has displayed even though it's survey has taken");
			}

			ActiondriverM.click(SurveyOR.m_btnBacktoList, "Back to List");
			ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
			ActiondriverM.click(SurveyOR.m_btnMenu, "Show Menu Button");
			ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnSearchSessionButton, "Seach Session plus Button");
			ActiondriverM.click(SurveyOR.lnkMySessionEvaluations,"My Session Evaluations");

			ActiondriverM.waitForVisibilityOfElement(SurveyOR.tickMark(SessionID,SessionTitle),"Tick mark under status column");
			ActiondriverM.isEnabled(SurveyOR.tickMark(SessionID,SessionTitle), "Tick mark under status column");

			BusinessFunctionsM.checkSurveyLookUpType(SessionID,SessionTitle,survey_Name, type);
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

			waitForElementPresent(By.xpath("//input[@type='submit']"));

			if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
				Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
			}
			else{
				Reporters.failureReport("Verify Page", "Registrants page is not displayed");
			}

			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");

			verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

			if(search(RegEmailID))
			{
				Reporters.SuccessReport("Search for newly added Registrant with attendee "+RegEmailID+"", "Registrant with attendee "+RegEmailID+" is displayed");
			}
			else{
				Reporters.failureReport("Search for newly added Registrant with attendee "+RegEmailID+"", "Registrant with attendee "+RegEmailID+" is not displayed");
			}
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");
			if(verifyInTable("Registrants", RegEmailID))
			{
				Reporters.SuccessReport("Verify Registrant in search results","Registrant has been displayed in search results");
			}
			else
			{
				Reporters.failureReport("Verify Registrant in search results", "Registrant has not been displayed in search results");
			}

			click(By.xpath("//td[text()='"+RegEmailID+"']"),"Registrant");

			if(verifyInTable(TableName, SessionTitle))
			{
				Reporters.SuccessReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has been displayed under Manage Session Attendance list");
			}
			else
			{
				Reporters.failureReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has failed to display under Manage Session Attendance list");
			}

			getEMTURL();
		}
		else
		{
			Reporters.failureReport("Search for session "+SessionTitle, "Session is not available in Results List");
		}

		driverM.quit();
		emtLogout=true;
		System.out.println("Reached Last Statement");

	}

	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Survey_TakeSurvey", "Key_TakeSurvey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
}
