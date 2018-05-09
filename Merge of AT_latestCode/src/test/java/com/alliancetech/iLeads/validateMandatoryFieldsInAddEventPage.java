package com.alliancetech.iLeads;


import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Reporters;

public class validateMandatoryFieldsInAddEventPage extends BusinessFunctions{
	public static int logIn=0;
	//ExcelReader ereader=new ExcelReader("TestData.xls", "iLeads_MandatoryFilelds", 36);
  @Test(groups={"iLeads","RunAll"})
  public void validateMandatoryFields() throws Throwable {
	  
	  try{
			if(logIn==0)
			{
				if(getiLeadsURL())
				{
					Reporters.SuccessReport("Launch iLeads Application", "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
				}

				if(iLeadsLogIn())
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
		
		if(create_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Add Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Add Event Page page is not displayed");
		}
		
		if(verifyAllMandatoryfields())
		{
			Reporters.SuccessReport("Verify All Mandatory fields in Add Event Page", "Successfully verified all mandatory fields in Add Event page");
		}
		else
		{
			Reporters.failureReport("Verify All Mandatory fields in Add Event Page", "Failed to verify all mandatory fields in Add Event page");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyEventStartDateFormats())
		{
			Reporters.SuccessReport("verify Event Start Date Format", "Event Start date formats are successfully verified");
		}
		else
		{
			Reporters.failureReport("verify Event Start Date Format", "Accepting invalid Start Date format");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyEventEndDateFormats())
		{
			Reporters.SuccessReport("verify Event End Date Format", "Event End date formats are successfully verified");
		}
		else
		{
			Reporters.failureReport("verify Event End Date Format", "Accepting invalid End Date format");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyEventExpirationdateFormats())
		{
			Reporters.SuccessReport("verify Event Expiration Date Format", "Event Expiration date formats are successfully verified");
		}
		else
		{
			Reporters.failureReport("verify Event Expiration Date Format", "Accepting invalid Expiration Date format");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyEmailAddress())
		{
			Reporters.SuccessReport("Verify Email Address format", "Successfully verified invalid formats of email address");
		}
		else
		{
			Reporters.failureReport("Verify Email Address format", "Accepting invalid Email Address formats");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyCRMIntegrationSettings())
		{
			Reporters.SuccessReport("Verify CRM Integration Settings", "Verified CRM Integration Mandatory fields successfully");
		}
		else
		{
			Reporters.failureReport("Verify CRM Integration Settings", "Failed to verify CRM Integration mandatory fields");
		}
		
		if(edit_Event_Page())
		{
			Reporters.SuccessReport("Verify Page", "Edit Event Page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Edit Event Page is displayed");
		}
		
		if(verifyEventSpecificSettings())
		{
			Reporters.SuccessReport("Verify Event Specific Settings", "Successfully verified formats of Event Specific Settings");
		}
		else
		{
			Reporters.failureReport("Verify Event Specific Settings", "Event Specific Settings accepting invalid formats");
		}
		
		if(event_clear_page())
		{
			Reporters.SuccessReport("Verify Page", "Clear Event Page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Clear Event page is not displayed");
		}
		
		if(clearEvent())
		{
			Reporters.SuccessReport("Clear Event", "Event cleared Successfuly");
		}
		else{
			Reporters.failureReport("Clear Event", "Failed to clear the event");
		}
		
		if(remove_Event_Page())
		  {
			  Reporters.SuccessReport("Verify Page", "Remove Event Page is displayed");
		  }
		  else{
			  Reporters.failureReport("Verify Page", "Remove Event page is not displayed");
		  }
		  
		if(removeEvent())
		  {
			  Reporters.SuccessReport("Remove Event", "Event got deleted successfully");
		  }
		  else{
			  Reporters.failureReport("Remove Event", "Event failed to delete");
		  }
		  
		 if(iLeadsLogOut())
			{
				Reporters.SuccessReport("Log out from iLeads application", "Logged out from the application successfuly");
			}
			else{
				Reporters.failureReport("Log out from iLeads application", "Failed to log out from the application");
			}
		
  }
  
}
