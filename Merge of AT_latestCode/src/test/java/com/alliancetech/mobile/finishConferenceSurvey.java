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
import com.alliancetech.objectrepository.SurveyOR;
import com.alliancetech.objectrepository.XML_OR;
import com.cigniti.automation.accelerators.ActiondriverM;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class finishConferenceSurvey extends BusinessFunctions{
	public static int logIn=0;
	public static String id=null;
	@Test(dataProvider = ("getData"),groups= {"MobileSurvey", "RunAll"})
	public void takeMobileConferenceSurvey(String RegEmailID,String survey_Name,String type) throws Throwable {
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
			ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
			ActiondriverM.click(SurveyOR.m_btnMenu, "Show Menu Button");
			waitForVisibilityOfElement(SurveyOR.lnkConferenceSession, "Conference Session");
			BusinessFunctionsM.click(SurveyOR.lnkConferenceSession,"Conference Session");
			if(BusinessFunctionsM.takeSurvey("", ""))
			{
				Reporters.SuccessReport("Take Survey for session of type "+type, "Successfully taken survey for session of type "+type);
			}
			else
			{
				Reporters.failureReport("Take Survey for session of type "+type, "Failed to complete survey for session of type "+type);
			}
			
			ActiondriverM.waitForVisibilityOfElement(SurveyOR.m_btnMenu, "Show Menu Button");
			ActiondriverM.click(SurveyOR.m_btnMenu, "Show Menu Button");
			waitForVisibilityOfElement(SurveyOR.lnkConferenceSession, "Conference Session");
			BusinessFunctionsM.click(SurveyOR.lnkConferenceSession,"Conference Session");
			
			if(BusinessFunctionsM.validateSurveyAnswers())
			{
				Reporters.SuccessReport("Verify survey answers", "All survey answers are saved successfully");
			}
			else
			{
				//Reporters.failureReport("Verify survey answers", "All survey answers are failed to save");
			}
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
			driverM.close();

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
