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

public class DidNotAttend extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"Survey", "RunAll","EMT"})
	public void Did_Not_Attend_Checkbox(String RegEmailID,String SessionID,String SessionTitle,String survey_Name,String type) throws Throwable {

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

		//switchToFrameByIndex(0);
		
		waitForFrameToLoadAndSwitchToIt(By.xpath("//div[@class='CodeMirror-wrapping']/iframe"), "Frame");

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

		if(clickDidNotAttend(SessionID,SessionTitle))
		{
			Reporters.SuccessReport("Check Did Not Attend check box for session"+SessionTitle, "Successfully checked the Did Not Attend checkbox for session"+SessionTitle);
		}
		else
		{
			Reporters.failureReport("Check Did Not Attend check box for session"+SessionTitle, "Failed to check the Did Not Attend checkbox for session"+SessionTitle);
		}

		if(searchForDidNotAttendOrAlreadyTakenSession(SessionTitle))
		{
			Reporters.SuccessReport("Search for Session whose Did Not Attend checkbox has checked", "As Session's Did Not Attend check box has checked,it did not display in search results");
		}
		else
		{
			Reporters.failureReport("Search for Session whose Did Not Attend checkbox has checked", "Session still appeared in search results though its Did Not Attend check box has checked");
		}

		click(SurveyOR.lnkMySessionEvaluations,"My Session Evaluations link");

		switchWindowByTitle("Reporting Site", 1);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Link"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants Page has displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants Page has not displayed");
		}

		if(search(RegEmailID))
		{
			Reporters.SuccessReport("Search for the registrant "+RegEmailID+" in EMT", "Successfully Searched for the registrant");
		}
		else
		{
			Reporters.failureReport("Search for the registrant "+RegEmailID+" in EMT", "Failed to Search for the registrant");
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//td[text()='"+RegEmailID+"']"),"Registrant"))
		{
			Reporters.SuccessReport("View the Registrant", "Registrant details have been displayed");
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Session Attendance"), "Session Attendance Loading...");
			if(!verifyInTable("Session Attendance", SessionTitle))
			{
				Reporters.SuccessReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has been displayed under Manage Session Attendance list");
			}
			else
			{
				Reporters.failureReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has failed to display under Manage Session Attendance list");
			}
			switchWindowByTitle("Survey Site", 2);
			clickDidNotAttend(SessionID,SessionTitle);
			waitForInVisibilityOfElement(By.xpath("//li[@class='disabled']"), "checked tick mark");
			if(!isChecked(SurveyOR.chckboxDidNotAttend(SessionID, SessionTitle), "Did not Attend Checkbox")){
				Reporters.SuccessReport("Is checked", "Successfully unchecked the Did Not Attend checkbox for session"+SessionTitle);
			}
			else{
				Reporters.failureReport("Is checked", "failed to uncheck the Did Not Attend checkbox for session"+SessionTitle);
			}
			if(surveyLogOut())
			{
				Reporters.SuccessReport("Logout from Survey Site", "Logged out from survey site successfully");
			}
			else
			{
				Reporters.failureReport("Logout from Survey Site", "Failed to log out from Survey site");
			}
			driver.close();
			//closeCurrentWindow();
			switchWindowByTitle("Reporting Site", 1);
			refresh();
			waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Session Attendance"), "Session Attendance Loading...");
			if(verifyInTable("Session Attendance", SessionTitle))
			{
				Reporters.SuccessReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has been displayed under Manage Session Attendance list");
			}
			else
			{
				Reporters.failureReport("Verify session "+SessionTitle+" in Manage Session Attendance section", "Session "+SessionTitle+" has failed to display under Manage Session Attendance list");
			}
		}
		else
		{
			Reporters.failureReport("View the Registrant", "Failed to display registrant details");
		}
		getEMTURL();
		emtLogout=true;
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Survey_TakeSurvey", "Key_DidNotAttend");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}


}
