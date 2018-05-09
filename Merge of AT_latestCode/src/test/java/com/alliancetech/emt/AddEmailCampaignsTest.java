package com.alliancetech.emt;
/**
 * This Test Case adds Email Campaigns in the EMT site
 * Reads data from EMTEmailCampaigns Sheet
 * reference Test Rail- iConnect_EMT(Email Campaigns)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddEmailCampaignsTest extends BusinessFunctions {
	public static int logIn = 0;

	@Test(dataProvider = ("getData"), groups = { "EMT", "RunAll" })
	public void EMT_AddEmailCampaigns(String type, String ViewBy, String Name, String replyToEmailID, String subject,
			String loginID) throws Throwable {

		try {
			if (logIn == 0) {
				if (getEMTURL()) {
					Reporters.SuccessReport("Launch EMT Application", "Application URL launched successfully");
				} else {
					Reporters.failureReport("Launch EMT Application", "Application URL failed to launch ");
				}

				if (emtLogIn()) {
					Reporters.SuccessReport("Login into the Application", "login is successfull");
					logIn++;
				} else {
					Reporters.failureReport("Login into the Application", "login is not successfull");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

		if (clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")) {
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		} else {
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		// Manage Content

		if (navigateToManageContentPage()) {
			Reporters.SuccessReport("Navigate to Manage Content Page", "Successfully navigated to Manage Content page");
		} else {
			Reporters.failureReport("Navigate to Manage Content Page", "failed to navigate to Manage Content page");
		}

		if (addNewContentDetails(type, Name, replyToEmailID, subject)) {
			Reporters.SuccessReport("Add New Content Deatils", "Successfully added new Content details: " + Name);
		} else {
			Reporters.failureReport("Add New Content Deatils", "failed to add new content details: " + Name);
		}

		if (checkIsEnabledStatus()) {
			Reporters.SuccessReport("Validate Is Enabled? field status", "Is Enabled? is not checked");
		} else {
			Reporters.failureReport("Validate Is Enabled? field status", "Is Enabled? is checked");
		}

		click(EMT_EmailCampignsOR.btnSubmit, "Submit button");

		isElementPresent(EMT_EmailCampignsOR.txtMessage, "Success Message");

		if (clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab")) {
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		} else {
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}

		if (verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page")) {
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		} else {
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if (addEmailCampaignsType(type, ViewBy)) {
			Reporters.SuccessReport("Add Email Campaigns type", "" + type + " is added for Email Campaign");
		} else {
			Reporters.failureReport("Add Email Campaigns type", "" + type + " is not added for Email Campaign");
		}

		if (verifyContentCreatedInContentDropDownWhenDisabled(Name)) {
			Reporters.SuccessReport("Verify Content created " + Name + " in Content drop down",
					"Content " + Name + " is not available as Is Enabled? check box is not checked");
		} else {
			Reporters.failureReport("Verify Content created " + Name + " in Content drop down",
					"Content " + Name + " is available though Is Enabled? check box is not checked");
		}

		if (clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab")) {
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		} else {
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		if (navigateToManageContentPage()) {
			Reporters.SuccessReport("Navigate to Manage Content Page", "Successfully navigated to Manage Content page");
		} else {
			Reporters.failureReport("Navigate to Manage Content Page", "Failed to navigate to Manage Content page");
		}

		if (navigateToContentEmailCampaignPage(Name)) {
			Reporters.SuccessReport("Navigate to Email Campaign created page: " + Name,
					"Succesfully navigated to Email campaign created: " + Name);
		} else {
			Reporters.failureReport("Navigate to Email Campaign created page: " + Name,
					"Failed to navigate to email campaign created page: " + Name);
		}

		if (enableIsEnabledField()) {
			Reporters.SuccessReport("Enable Is Enabled? check box", "Successfully enabled Is Enabled? check box");
		} else {
			Reporters.failureReport("Enable Is Enabled? check box", "Failed to check Is Enabled? check box");
		}

		if (clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab")) {
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		} else {
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}

		if (verifyText(EMT_EmailCampignsOR.txtPageTitle, "Email Campaigns", "Email Campaigns page")) {
			Reporters.SuccessReport("Verification of Page Title", "Navigated to Email Campaign Page");
		} else {
			Reporters.failureReport("Verification of Page title", "Not Navigated to Email Campaigns page");
		}

		if (addEmailCampaignsType(type, ViewBy)) {
			Reporters.SuccessReport("Add Email Campaigns type", "" + type + " is added for Email Campaign");
		} else {
			Reporters.failureReport("Add Email Campaigns type", "" + type + " is not added for Email Campaign");
		}

		if (verifyContentCreatedInContentDropDownWhenEnabled(Name)) {
			Reporters.SuccessReport(
					"Verify Content " + Name + " in Content drop dowm after Is Enabled? check box is checked",
					"Content " + Name + " is visible under Content drop down");
		} else {
			Reporters.failureReport(
					"Verify Content " + Name + " in Content drop dowm after Is Enabled? check box is checked",
					"Content " + Name + " is not visible under Content drop down");
		}

		if (chooseOptionsAndRegistrantsInEmailCampaign(Name, loginID)) {
			Reporters.SuccessReport("Choose Options and Registrant in Email Campaigns page",
					"Successfully selected options and registrants with login ID " + loginID + " for email campaign");
		} else {
			Reporters.failureReport("Choose Options and Registrant in Email Campaigns page",
					"Failed to select options and registrants with login ID " + loginID + " for email campaign");
		}

		if (confirmEmailCampaign()) {
			Reporters.SuccessReport("Email Campaign Final Step: Confirmation",
					"Successfully completed Email Campaign Final Step: Confirmation");
		} else {
			Reporters.failureReport("Email Campaign Final Step: Confirmation",
					"Failed to complete Email Campaign Final Step: Confirmation");
		}

		if (clickTabFromViewMore(CommonOR.lnkEmailCampigns, "EmailCampigns Tab")) {
			Reporters.SuccessReport("Verify Page", "EmailCampigns page is displayed");
		} else {
			Reporters.failureReport("Verify Page", "EmailCampigns page is not displayed");
		}

		if (verifyEmailCampaign(Name)) {
			Reporters.SuccessReport("Verify status of Email Campaign created " + Name,
					"Status of Email Campaign created " + Name + " is Complete");
		} else {
			Reporters.failureReport("Verify status of Email Campaign created " + Name,
					"Status of Email Campaign created " + Name + " is Failed");
		}

		if (bulkDeleteEmailCampaign()) {
			Reporters.SuccessReport("Delete Email Campaign: " + Name,
					"Email Campaign " + Name + " got deleted successfully");
		} else {
			Reporters.failureReport("Delete Email Campaign: " + Name, "Email Campaign " + Name + " failed to delete");
		}

		getEMTURL();
		emtLogout = true;
	}

	/*
	 * Reading data from TestData.xls under TestData folder
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTEmailCampaigns", "Key_AddEmailCampaigns"); // returning
																							// data
																							// from
																							// "EMTEmailCampaigns"
																							// sheet
																							// and
																							// "EC"
																							// as
																							// a
																							// reference
																							// to
																							// read
																							// data
	}

}
