package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.SurveyOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DeleteSurvey extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups= {"EMT", "RunAll","Survey"})
  public void deleteSurvey(String Survey_Name,String Survey_Status,String Survey_Type,String Survey_Flag,String Description) throws Throwable {
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
		
		if (!Survey_Flag.equals("")) {
			if (clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")) {
				Reporters.SuccessReport("Verify Page",
						"Administration page is displayed");
			} else {
				Reporters.failureReport("Verify Page",
						"Administration page is not displayed");
			}
			if (navigateToManageSurveysPage()) {
				Reporters.SuccessReport("Navigate to Manage Surveys Page",
						"Successfully navigated to manage surveys page");
			} else {
				Reporters.failureReport("Navigate to Manage Surveys Page",
						"Failed to navigate to manage surveys page");
			}
			if (click(By.xpath("//td[text()='" + Survey_Flag + "']"),
					"Survey with flag " + Survey_Flag)) {
				if (clearSurvey()) {
					Reporters.SuccessReport("Clear Survey " + Survey_Name,
							"Survey has been successfully cleared");
				} else {
					Reporters.failureReport("Clear Survey " + Survey_Name,
							"Survey has failed to clear");
				}
				
				waitForElementPresent(SurveyOR.lnkCancel);
				js_click(SurveyOR.lnkCancel, "Cancel link");

				if (deleteSurvey(Survey_Flag)) {
					Reporters.SuccessReport("Delete Survey with flag "
							+ Survey_Flag, "Survey with flag " + Survey_Flag
							+ " has been deleted");
				} else {
					Reporters.failureReport("Delete Survey with flag "
							+ Survey_Flag, "Survey with flag " + Survey_Flag
							+ " has not deleted");
				}
				
				waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
				if (!verify_In_Table(SurveyOR.lstSearchResults, Survey_Name)) {
					Reporters.SuccessReport("Verify survey " + Survey_Name
							+ " in table after deleting the survey",
							"Survey is not available in table");
				} else {
					Reporters.failureReport("Verify survey " + Survey_Name
							+ " in table after deleting the survey",
							"Survey is still available in table");
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

		return Data_Provider.getTableArray("EMTAddSurvey", "Key_Survey");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
	
	
}

