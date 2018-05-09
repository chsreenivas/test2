package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddSessionEmailCampaign extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
  public void sessionEmailCampaignForAttendeeSubType(String type,String ViewBy,String attendeeName,String replyToEmailID,String attendeeSubject,String attendeeID,String enrolleeName,String enrolleeSubject,String enrolleeID,String bothName,String bothSubject,String bothID) throws Throwable {
	  try
		{
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
				else
				{
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		//Manage Content
		
		if(navigateToManageContentPage())
		{
			Reporters.SuccessReport("Navigate to Manage Content Page","Successfully navigated to Manage Content page" );
		}
		else
		{
			Reporters.failureReport("Navigate to Manage Content Page", "failed to navigate to Manage Content page");
		}
		
		if(addSessionContentDetails(type, attendeeName, replyToEmailID, attendeeSubject,"Attendee"))
		{
			Reporters.SuccessReport("Add New Content Deatils", "Successfully added new Content details: "+attendeeName);
		}
		else
		{
			Reporters.failureReport("Add New Content Deatils", "failed to add new content details: "+attendeeName);
		}
		
		if(checkIsEnabledStatus())
		{
			Reporters.SuccessReport("Validate Is Enabled? field status", "Is Enabled? is not checked");
		}
		else
		{
			Reporters.failureReport("Validate Is Enabled? field status", "Is Enabled? is checked");
		}
		
		click(EMT_EmailCampignsOR.btnSubmit,"Submit button");
		
		isElementPresent(EMT_EmailCampignsOR.txtMessage, "Success Message");
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page"))
		{
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		}
		else
		{
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if(addEmailCampaignsType(type,ViewBy))
		{
			Reporters.SuccessReport("Add Email Campaigns type", ""+type+" is added for Email Campaign");
		}
		else
		{
			Reporters.failureReport("Add Email Campaigns type", ""+type+" is not added for Email Campaign");
		} 
		
		if(verifyContentCreatedInContentDropDownWhenDisabled(attendeeName))
		{
			Reporters.SuccessReport("Verify Content created "+attendeeName+" in Content drop down", "Content "+attendeeName+" is not available as Is Enabled? check box is not checked");
		}
		else
		{
			Reporters.failureReport("Verify Content created "+attendeeName+" in Content drop down", "Content "+attendeeName+" is available though Is Enabled? check box is not checked");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		if(navigateToManageContentPage())
		{
			Reporters.SuccessReport("Navigate to Manage Content Page","Successfully navigated to Manage Content page" );
		}
		else
		{
			Reporters.failureReport("Navigate to Manage Content Page", "Failed to navigate to Manage Content page");
		}
		
		if(navigateToContentEmailCampaignPage(attendeeName))
		{
			Reporters.SuccessReport("Navigate to Email Campaign created page: "+attendeeName, "Succesfully navigated to Email campaign created: "+attendeeName);
		}
		else
		{
			Reporters.failureReport("Navigate to Email Campaign created page: "+attendeeName, "Failed to navigate to email campaign created page: "+attendeeName);
		}
		
		if(enableIsEnabledField())
		{
			Reporters.SuccessReport("Enable Is Enabled? check box", "Successfully enabled Is Enabled? check box");
		}
		else
		{
			Reporters.failureReport("Enable Is Enabled? check box", "Failed to check Is Enabled? check box");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page"))
		{
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		}
		else
		{
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if(addEmailCampaignsType(type,ViewBy))
		{
			Reporters.SuccessReport("Add Email Campaigns type", ""+type+" is added for Email Campaign");
		}
		else
		{
			Reporters.failureReport("Add Email Campaigns type", ""+type+" is not added for Email Campaign");
		} 
		
		if(verifyContentCreatedInContentDropDownWhenEnabled(attendeeName))
		{
			Reporters.SuccessReport("Verify Content "+attendeeName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+attendeeName+" is visible under Content drop down");
		}
		else
		{
			Reporters.failureReport("Verify Content "+attendeeName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+attendeeName+" is not visible under Content drop down");
		}
		
		if(chooseOptionsAndRegistrantsInEmailCampaign(attendeeName,attendeeID))
		{
			Reporters.SuccessReport("Choose Options and Registrant in Email Campaigns page", "Successfully selected options and sessions with login ID "+attendeeID+" for email campaign");
		}
		else
		{
			Reporters.failureReport("Choose Options and Registrant in Email Campaigns page", "Failed to select options and sessions with login ID "+attendeeID+" for email campaign");
		}
		
		if(confirmEmailCampaign())
		{
			Reporters.SuccessReport("Email Campaign Final Step: Confirmation", "Successfully completed Email Campaign Final Step: Confirmation");
		}
		else
		{
			Reporters.failureReport("Email Campaign Final Step: Confirmation", "Failed to complete Email Campaign Final Step: Confirmation");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyEmailCampaign(attendeeName))
		{
			Reporters.SuccessReport("Verify status of Email Campaign created "+attendeeName, "Status of Email Campaign created "+attendeeName+" is Complete");
		}
		else
		{
			Reporters.failureReport("Verify status of Email Campaign created "+attendeeName, "Status of Email Campaign created "+attendeeName+" is Failed");
		}
		
		
		//Enrollee
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		//Manage Content
		
		if(navigateToManageContentPage())
		{
			Reporters.SuccessReport("Navigate to Manage Content Page","Successfully navigated to Manage Content page" );
		}
		else
		{
			Reporters.failureReport("Navigate to Manage Content Page", "failed to navigate to Manage Content page");
		}
		
		if(addSessionContentDetails(type, enrolleeName, replyToEmailID, enrolleeSubject,"Enrollee"))
		{
			Reporters.SuccessReport("Add New Content Deatils", "Successfully added new Content details: "+enrolleeName);
		}
		else
		{
			Reporters.failureReport("Add New Content Deatils", "failed to add new content details: "+enrolleeName);
		}
		
		click(By.xpath("//td[text()='Is Enabled?']/following-sibling::td/input[@name='email-content-enabled']"), "Is Enabled check box");
		
		click(EMT_EmailCampignsOR.btnSubmit,"Submit button");
		
		isElementPresent(EMT_EmailCampignsOR.txtMessage, "Success Message");
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page"))
		{
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		}
		else
		{
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if(addEmailCampaignsType(type,ViewBy))
		{
			Reporters.SuccessReport("Add Email Campaigns type", ""+type+" is added for Email Campaign");
		}
		else
		{
			Reporters.failureReport("Add Email Campaigns type", ""+type+" is not added for Email Campaign");
		} 
		
		if(verifyContentCreatedInContentDropDownWhenEnabled(enrolleeName))
		{
			Reporters.SuccessReport("Verify Content "+enrolleeName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+enrolleeName+" is visible under Content drop down");
		}
		else
		{
			Reporters.failureReport("Verify Content "+enrolleeName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+enrolleeName+" is not visible under Content drop down");
		}
		
		if(chooseOptionsAndRegistrantsInEmailCampaign(enrolleeName,enrolleeID))
		{
			Reporters.SuccessReport("Choose Options and Registrant in Email Campaigns page", "Successfully selected options and sessions with login ID "+enrolleeName+" for email campaign");
		}
		else
		{
			Reporters.failureReport("Choose Options and Registrant in Email Campaigns page", "Failed to select options and sessions with login ID "+enrolleeName+" for email campaign");
		}
		
		if(confirmEmailCampaign())
		{
			Reporters.SuccessReport("Email Campaign Final Step: Confirmation", "Successfully completed Email Campaign Final Step: Confirmation");
		}
		else
		{
			Reporters.failureReport("Email Campaign Final Step: Confirmation", "Failed to complete Email Campaign Final Step: Confirmation");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyEmailCampaign(enrolleeName))
		{
			Reporters.SuccessReport("Verify status of Email Campaign created "+enrolleeName, "Status of Email Campaign created "+enrolleeName+" is Complete");
		}
		else
		{
			Reporters.failureReport("Verify status of Email Campaign created "+enrolleeName, "Status of Email Campaign created "+enrolleeName+" is Failed");
		}
		
		//Both Attendee and Enrollee
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		//Manage Content
		
		if(navigateToManageContentPage())
		{
			Reporters.SuccessReport("Navigate to Manage Content Page","Successfully navigated to Manage Content page" );
		}
		else
		{
			Reporters.failureReport("Navigate to Manage Content Page", "failed to navigate to Manage Content page");
		}
		
		if(addSessionContentDetails(type, bothName, replyToEmailID, bothSubject,"Both"))
		{
			Reporters.SuccessReport("Add New Content Deatils", "Successfully added new Content details: "+bothName);
		}
		else
		{
			Reporters.failureReport("Add New Content Deatils", "failed to add new content details: "+bothName);
		}
		
		click(By.xpath("//td[text()='Is Enabled?']/following-sibling::td/input[@name='email-content-enabled']"), "Is Enabled check box");
		
		click(EMT_EmailCampignsOR.btnSubmit,"Submit button");
		
		isElementPresent(EMT_EmailCampignsOR.txtMessage, "Success Message");
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page"))
		{
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		}
		else
		{
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if(addEmailCampaignsType(type,ViewBy))
		{
			Reporters.SuccessReport("Add Email Campaigns type", ""+type+" is added for Email Campaign");
		}
		else
		{
			Reporters.failureReport("Add Email Campaigns type", ""+type+" is not added for Email Campaign");
		} 
		
		if(verifyContentCreatedInContentDropDownWhenEnabled(bothName))
		{
			Reporters.SuccessReport("Verify Content "+bothName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+bothName+" is visible under Content drop down");
		}
		else
		{
			Reporters.failureReport("Verify Content "+bothName+" in Content drop dowm after Is Enabled? check box is checked", "Content "+bothName+" is not visible under Content drop down");
		}
		
		if(chooseOptionsAndRegistrantsInEmailCampaign(bothName,bothID))
		{
			Reporters.SuccessReport("Choose Options and Registrant in Email Campaigns page", "Successfully selected options and sessions with login ID "+bothID+" for email campaign");
		}
		else
		{
			Reporters.failureReport("Choose Options and Registrant in Email Campaigns page", "Failed to select options and sessions with login ID "+bothID+" for email campaign");
		}
		
		if(confirmEmailCampaign())
		{
			Reporters.SuccessReport("Email Campaign Final Step: Confirmation", "Successfully completed Email Campaign Final Step: Confirmation");
		}
		else
		{
			Reporters.failureReport("Email Campaign Final Step: Confirmation", "Failed to complete Email Campaign Final Step: Confirmation");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab"))
		{
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}
		
		if(verifyEmailCampaign(bothName))
		{
			Reporters.SuccessReport("Verify status of Email Campaign created "+bothName, "Status of Email Campaign created "+bothName+" is Complete");
		}
		else
		{
			Reporters.failureReport("Verify status of Email Campaign created "+bothName, "Status of Email Campaign created "+bothName+" is Failed");
		}
		
		if(bulkDeleteEmailCampaign())
		{
			Reporters.SuccessReport("Delete Email Campaign", "Email Campaign got deleted successfully");
		}
		else
		{
			Reporters.failureReport("Delete Email Campaign", "Email Campaign failed to delete");
		}
		
		emtLogout=true;
  }
		/*
		 * Reading data from TestData.xls under TestData folder	
		 */
		@DataProvider
		public Object[][] getData() throws Exception {

			return Data_Provider.getTableArray("EMTEmailCampaigns", "Key_AddSessionEmailCampaigns");  //returning data from "EMTEmailCampaigns" sheet and "EC" as a reference to read data
		}
}
