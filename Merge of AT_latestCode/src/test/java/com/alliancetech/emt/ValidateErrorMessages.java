package com.alliancetech.emt;

import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddAssociationsOR;
import com.alliancetech.objectrepository.EMT_AddSpeaker_OR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.EMT_SessionsOR;
import com.cigniti.automation.utilities.Reporters;

public class ValidateErrorMessages extends BusinessFunctions{
	public static int logIn=0;
  @Test(groups={"EMT","RunAll"})
  public void MandatoryFields() throws Throwable {
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

				else{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");
		
		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");
		
		js_TriggerOnClickEvent("addNewRecord();", "Add Button");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		if(validateErrorMessagesInRegistrantPage())
		{
			Reporters.SuccessReport("Validation of error messages in Registrant page", "All the error messages has been displayed successfully");
		}
		else
		{
			Reporters.failureReport("Validation of error messages in Registrant page", "Error messages has failed to display");
		}
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Attendee #"),"78906","Attendee Number");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("First"), "This is a required field", "First Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Last"), "This is a required field", "Last Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Login Id"), "This is a required field", "Login ID Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("First"),"Ravali","First Name");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Last"), "This is a required field", "Last Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Login Id"), "This is a required field", "Login ID Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Last"),"Juluri","Last Name");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Login Id"), "This is a required field", "Login ID Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		selectByVisibleText(CommonOR.ddLabel("Status"), "Accepted", "Status Drop Down");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Login Id"), "This is a required field", "Login ID Text Box Error Message Displayed");
		
		verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Login Id"), "ravali.juluri@cigniti.com", "Login Id Text Box");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Password"), "This is a required field", "Password Text Box Error Message Displayed");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Password"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Password"), "test", "Password Text Box");
		
		type(EMT_RegistrantsOR.txtConfirmPassword, "test", "Confirm Password");
		
		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");
		
		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}
		
		logout();
		emtLogIn();
		if(clickTabFromViewMore(EMT_SessionsOR.tabSessions,"Sessions tab")){
			Reporters.SuccessReport("Navigate to Sessions Page", "Required steps have been performed above successfully");
		}
		else{
			Reporters.failureReport("Navigate to Sessions Page", "Unable to Perform all the required steps");
		}

		if(NavigateToAddSessionsPage())
		{
			Reporters.SuccessReport("Navigate to Add Sessions Page", "Steps to navigate to add sessions page have been performed");
		}
		else
		{
			Reporters.failureReport("Navigate to Add Sessions Page", "Unable to perform all the required steps to navigate");
		}

		verifyText(EMT_SessionsOR.txtPageTitle, "Add New Session", "Add Session Page");

		Thread.sleep(2000);
		
		click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		if(validateErrorMessagesInSessionsPage())
		{
			Reporters.SuccessReport("Validation of error messages in Sessions page", "All the error messages has been displayed successfully");
		}
		else
		{
			Reporters.failureReport("Validation of error messages in Sessions page", "Error messages has failed to display");
		}
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Status"), "Error Messages got disappeared");
		
		type(EMT_SessionsOR.txtTitle,"Validate Session Error messages"," title for the session");
		
		click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Status"), "This is a required field", "Status Drop Down Error message");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Status"), "Error Messages got disappeared");
		
		selectByVisibleText(EMT_SessionsOR.ddStatus, "Accepted", "Status Drop Down");
		
		click(EMT_SessionsOR.btnSubmit, "Submit Button");
		
		verifyTextPresent("Session has been added! ");
		
		emtLogOut();
		emtLogIn();
		
		if(clickTabFromViewMore(CommonOR.lnkSpeakers,"Speakers Tab")){
			Reporters.SuccessReport("Verify Page", "Speakers page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Speakers page is not displayed");
		}
		if(verifySpeakerIcon()){
			Reporters.SuccessReport("Verify the speaker icon", "Successfully verified");
		}
		else{
			Reporters.failureReport("Verify the speaker icon", "Unable to verify");
		}
		if(addNewRecord()){
			Reporters.SuccessReport("Select the Speakers from dropDown", "Successfully selected the Speakers");
		}
		else{
			Reporters.failureReport("Select the Speakers from dropDown", "Unable to select Speakers");
		}
		
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		
		if(validateErrorMessagesInSpeakersPage())
		{
			Reporters.SuccessReport("Validation of error messages in Speakers page", "All the error messages has been displayed successfully");
		}
		else
		{
			Reporters.failureReport("Validation of error messages in Speakers page", "Error messages has failed to display");
		}
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Email"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("First"),"Satya","First Text Box");
		
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Last"), "This is a required field", "Last Text Box Error message");
		
		verifyText(CommonOR.txtErrorMessage("Company"), "This is a required field", "Comapny Text Box Error message");
		
		verifyText(CommonOR.txtErrorMessage("Email"), "This is a required field", "Email Text Box Error message");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Email"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Last"),"Chamarthi","Last Text Box");
		
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Company"), "This is a required field", "Comapny Text Box Error message");
		
		verifyText(CommonOR.txtErrorMessage("Email"), "This is a required field", "Email Text Box Error message");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Email"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Company"),"Cigniti","Company Text Box");
		
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		
		verifyText(CommonOR.txtErrorMessage("Email"), "This is a required field", "Email Text Box Error message");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Email"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("Email"),"satya.chamarthi@cigniti.com","Email Text Box");
		
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		
		verifyTextPresent("Speaker has been added! ");
		
		logout();
		emtLogIn();
		
		if(clickTabFromViewMore(CommonOR.lnkAssociations, "Associations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Associations page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Associations page is not displayed");
		}

		js_click(EMT_AddAssociationsOR.lnkAddNewRecord, "Add New Record Link");

		js_TriggerOnClickEvent("addNewRecord();","Add Button");
		
		click(EMT_AddAssociationsOR.btnSubmit,"Submit");
		
		if(validateErrorMessagesInAssociationsPage())
		{
			Reporters.SuccessReport("Validation of error messages in Associations page", "All the error messages has been displayed successfully");
		}
		else
		{
			Reporters.failureReport("Validation of error messages in Associations page", "Error messages has failed to display");
		}
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Attendee #"), "Error Messages got disappeared");
		
		type(CommonOR.txtBox("EPC"), "0007996500", "EPC Text Box");
		
		click(EMT_AddAssociationsOR.btnSubmit,"Submit");
		
		verifyText(CommonOR.txtErrorMessage("Attendee #"), "This is a required field", "First Text Box Error message");
		
		waitForInVisibilityOfElement(CommonOR.txtErrorMessage("Attendee #"), "Error Messages got disappeared");
		
		emtLogout=true;
  }
}
