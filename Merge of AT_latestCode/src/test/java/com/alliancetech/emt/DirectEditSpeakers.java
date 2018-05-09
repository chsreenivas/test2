package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddSpeaker_OR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DirectEditSpeakers extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
  public void CreateASpeaker(String prefix,String CustomerParticipantID,String FirstName,String status,
			String LastName,String bio,String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,
			String IM,String Fullname,String Nickname,String Title,String phone,String mobile,String alt,String fax,
			String Company,String Address1,String Address2,String County,String City,String Region,String Country,
			String CountryCode,String ZipCode,String Email,String personalEmail,String LoginID,String Password,String ConfirmPassword,
			String Industry,String ProductSpecality,String Certification,String division,String Education,String JobRole,
			String market,String territory,String image) throws Throwable {
	  if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
	  {
		  driver.unregister(myListener);
	  }
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
		if(addSpeakerInformation(prefix,CustomerParticipantID,FirstName,status,LastName)){
			Reporters.SuccessReport("Enter the speaker information", "Speaker "+FirstName+" added successfully");
		}
		else{
			Reporters.failureReport("Enter the details on the form", "Details not entered successfully");
		}
		if(addSpeakerBio(bio,WebURL,Blog,LinkedInURL,FacebookURL,TwitterAcnt,IM))
		{
			Reporters.SuccessReport("Enter bio information for the speaker", "Bio details for the Speaker "+FirstName+" added successfully");
		}
		else{
			Reporters.failureReport("Enter bio information for the speaker", "Bio details for the Speaker "+FirstName+" has failed to add");
		}
		if(addSpeakerContactInformation(Fullname,Nickname,Title,phone,mobile,alt,fax,Company,Address1,Address2,County,City,Country,Region,CountryCode,ZipCode,Email,personalEmail))
		{
			Reporters.SuccessReport("Enter contact information for the speaker", "contact information for the Speaker "+FirstName+" added successfully");
		}
		else{
			Reporters.failureReport("Enter contact information for the speaker", "contact information for the Speaker "+FirstName+" has failed to add");
		}
		if(addSpeakerLoginInformation(LoginID, Password, ConfirmPassword))
		{
			Reporters.SuccessReport("Enter login information for the speaker", "login information for the Speaker "+FirstName+" added successfully");
		}
		else{
			Reporters.failureReport("Enter login information for the speaker", "login information for the Speaker "+FirstName+" has failed to add");
		}
		if(addSpeakerCategories( Industry, ProductSpecality, Certification, division, Education, JobRole, market,territory))
		{
			Reporters.SuccessReport("Enter categories details for the speaker", "categories for the Speaker "+FirstName+" added successfully");
		}
		else{
			Reporters.failureReport("Enter categories details for the speaker", "categories for the Speaker "+FirstName+" has failed to add");
		}
		if(addSpeakerImage(image))
		{
			Reporters.SuccessReport("Upload image for the speaker", "Image for the Speaker "+FirstName+" uploaded successfully");
		}
		else
		{
			Reporters.failureReport("Upload image for the speaker", "Image for the Speaker "+FirstName+" has failed to upload");
		}
		Thread.sleep(3000);
		click(EMT_AddSpeaker_OR.btnSubmit, "Submit Button");
		verifyTextPresent("Speaker has been added! ");
		getEMTURL();
		if(search(Email)){
			Reporters.SuccessReport("Search the Speaker with the:"+Email, "Successfully searched the Speaker");
		}
		else{
			Reporters.failureReport("Search the Speaker with the:"+Email, "Unable to search the speaker");
		}
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Speakers"), "Loading");
		if(js_click(By.xpath("//tr/td[text()='"+Email+"']"),Email))
		{
			Reporters.SuccessReport("Open the speaker by clicking on it", "Speaker details displayed successfuly");
			waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
			if(verifySpeakerInformation(prefix, CustomerParticipantID, FirstName, status, LastName))
			{
				Reporters.SuccessReport("Verification of Speaker Information", "All the information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Information", "Information entered for speaker is invalid");
			}
			if(verifySpeakerBio(bio, WebURL, Blog, LinkedInURL, FacebookURL, TwitterAcnt, IM))
			{
				Reporters.SuccessReport("Verification of Speaker Bio information", "All the information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Bio information", "Bio Information entered for speaker is invalid");
			}
			if(verifySpeakerContactInformation(Fullname, Nickname, Title, phone, mobile, alt, fax, Company, Address1, Address2, County, City, Region, Country, CountryCode, ZipCode, Email, personalEmail))
			{
				Reporters.SuccessReport("Verification of Speaker Contact information", "All the contact information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Contact information", "Contact Information entered for speaker is invalid");
			}
			if(verifySpeakerLoginInformation(LoginID, Password, ConfirmPassword))
			{
				Reporters.SuccessReport("Verification of Speaker Login information", "All the Login information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Login information", "Login Information entered for speaker is invalid");
			}
			if(verifySpeakerCategories(Industry, ProductSpecality, JobRole, division, Education, Certification, territory, market))
			{
				Reporters.SuccessReport("Verification of Speaker Categories information", "All the Categories information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Categories information", "Categories Information entered for speaker is invalid");
			}
		}
		else
		{
			Reporters.failureReport("Open the speaker by clicking on it", "Speaker details failed to display");
		}
		
		getEMTURL();
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_AddSpeaker");  //returning data from "EMTAddSpeaker" sheet and "Key_AddSpeaker" as a reference to read data
	}
	
	@Test(dataProvider = ("getEditData"),groups= {"EMT", "RunAll"},priority=1)
	public void EditSpeaker(String prefix,String CustomerParticipantID,String FirstName,String status,
			String LastName,String bio,String WebURL,String Blog,String LinkedInURL,String FacebookURL,String TwitterAcnt,
			String IM,String Fullname,String Nickname,String Title,String phone,String mobile,String alt,String fax,
			String Company,String Address1,String Address2,String County,String City,String Region,String Country,
			String CountryCode,String ZipCode,String Email,String personalEmail,String LoginID,String Password,String ConfirmPassword,
			String Industry,String ProductSpecality,String Certification,String division,String Education,String JobRole,
			String market,String territory,String image) throws Throwable
	{
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
		if(search(Email)){
			Reporters.SuccessReport("Search the Speaker with the:"+Email, "Successfully searched the Speaker");
		}
		else{
			Reporters.failureReport("Search the Speaker with the:"+Email, "Unable to search the speaker");
		}
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Speakers"), "Loading");
		if(js_click(By.xpath("//tr/td[text()='"+Email+"']"),Email)){
			Reporters.SuccessReport("Open the speaker by clicking on it", "Speaker details displayed successfuly");
			if(editSpeakerInformation(prefix,CustomerParticipantID,FirstName,status,LastName)){
				Reporters.SuccessReport("Edit the speaker information", "Speaker "+FirstName+" edited successfully");
			}
			else{
				Reporters.failureReport("Edit the details on the form", "Details not edited successfully");
			}
			if(editSpeakerBio(bio,WebURL,Blog,LinkedInURL,FacebookURL,TwitterAcnt,IM))
			{
				Reporters.SuccessReport("Edit bio information for the speaker", "Bio details for the Speaker "+FirstName+" edited successfully");
			}
			else{
				Reporters.failureReport("Edit bio information for the speaker", "Bio details for the Speaker "+FirstName+" has failed to edit");
			}
			if(editSpeakerContactInformation(Fullname,Nickname,Title,phone,mobile,alt,fax,Company,Address1,Address2,County,City,Country,Region,CountryCode,ZipCode,Email,personalEmail))
			{
				Reporters.SuccessReport("Edit contact information for the speaker", "contact information for the Speaker "+FirstName+" edited successfully");
			}
			else{
				Reporters.failureReport("Edit contact information for the speaker", "contact information for the Speaker "+FirstName+" has failed to edit");
			}
			if(editSpeakerLoginInformation(LoginID, Password, ConfirmPassword))
			{
				Reporters.SuccessReport("Enter login information for the speaker", "login information for the Speaker "+FirstName+" edited successfully");
			}
			else{
				Reporters.failureReport("Enter login information for the speaker", "login information for the Speaker "+FirstName+" has failed to edit");
			}
			if(editSpeakerCategories( Industry, ProductSpecality, Certification, division, Education, JobRole, market,territory))
			{
				Reporters.SuccessReport("Edit categories details for the speaker", "categories for the Speaker "+FirstName+" edited successfully");
			}
			else{
				Reporters.failureReport("Edit categories details for the speaker", "categories for the Speaker "+FirstName+" has failed to edit");
			}
			refresh();
			if(verifySpeakerInformation(prefix, CustomerParticipantID, FirstName, status, LastName))
			{
				Reporters.SuccessReport("Verification of Speaker Information", "All the information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Information", "Information entered for speaker is invalid");
			}
			if(verifySpeakerBio(bio, WebURL, Blog, LinkedInURL, FacebookURL, TwitterAcnt, IM))
			{
				Reporters.SuccessReport("Verification of Speaker Bio information", "All the information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Bio information", "Bio Information entered for speaker is invalid");
			}
			if(verifySpeakerContactInformation(Fullname, Nickname, Title, phone, mobile, alt, fax, Company, Address1, Address2, County, City, Region, Country, CountryCode, ZipCode, Email, personalEmail))
			{
				Reporters.SuccessReport("Verification of Speaker Contact information", "All the contact information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Contact information", "Contact Information entered for speaker is invalid");
			}
			if(verifySpeakerLoginInformation(LoginID, Password, ConfirmPassword))
			{
				Reporters.SuccessReport("Verification of Speaker Login information", "All the Login information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Login information", "Login Information entered for speaker is invalid");
			}
			if(verifySpeakerCategories(Industry, ProductSpecality, JobRole, division, Education, Certification, territory, market))
			{
				Reporters.SuccessReport("Verification of Speaker Categories information", "All the Categories information entered for Speaker is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Speaker Categories information", "Categories Information entered for speaker is invalid");
			}
			
			if(js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link")){
				
				waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
				Thread.sleep(2000);
				
				click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
				
				emtLogOut();
				
				emtLogIn();
				
				clickTabFromViewMore(CommonOR.lnkSpeakers, "Speakers Tab");

				search(Email);

				if(verifyInTable("Speakers",Email))
				{
					Reporters.failureReport("Speakers verification after delete", ""+Email+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Speakers verification after delete", ""+Email+" is not available in table,hence successfully deleted");
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
		public Object[][] getEditData() throws Exception {

			return Data_Provider.getTableArray("EMT_DirectEdit", "Key_EditSpeaker");  //returning data from "EMT_DirectEdit" sheet and "Key_EditRegistrant" as a reference to read data
		}
		}