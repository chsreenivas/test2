package com.alliancetech.signup;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.alliancetech.objectrepository.signUpOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.ExcelReader;
import com.cigniti.automation.utilities.Reporters;

public class SetScreenLayoutAndValidateErrorMessagesForSignUp extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups={"SignUp","RunAll","EMT"})
  public void setAdminCheckinScreenLayoutAndValidateErrorMessagesForSignUp(String prefix,String attendeeid,String first,String last,String status,String attendeetype,String email,String aboutMe,String OptInSMS,String headerType,String fieldToBeOptional) throws Throwable {
	  eReader = new ExcelReader("TestData//TestData.xls", "ScreenLayout", 1);
	  try{
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
				}

				else {
					Reporters.failureReport("Login into the Application", "login is not successfull");
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
		
		if(navigateToSignUpXMLPage())
		{
			Reporters.SuccessReport("Navigate to Sign Up XML Page", "Successfully navigated to Sign Up XML Page");
		}
		else
		{
			Reporters.failureReport("Navigate to Sign Up XML Page", "Failed to navigate to Sign Up XML Page");
		}
		
		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Signup Form", "Tab Filter");
		
		if(setAddLayoutForSignUp("SignUpAdminCheckinForm",9))
		{
			Reporters.SuccessReport("Set Screen Layout in Add Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Set Screen Layout in Add Layout", "Screen layout has failed");
		}

		if(makeFieldsRequired())
		{
			Reporters.SuccessReport("Make All left and right column fields editable", "Successfully made fields editable");
		}
		else
		{
			Reporters.failureReport("Make All left and right column fields editable", "Failed to make the fields editable");
		}

		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");
		
		if(openSiteInNewWindow("Event to be Moved | Sign up Form"))
		{
			getSignUpURL();
			Reporters.SuccessReport("Open SignUp Site in new window", "SignUp Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open SignUp Site in new window","SignUp site has failed to open in new window");
		}
		
		if (verifyScreenLayoutInCheckIn())
		{
			Reporters.SuccessReport("Verify Screen Layout","Displayed New Screen Layout");
		} 
		else
		{
			Reporters.failureReport("Verify Screen Layout","Failed to display New Screen Layout");
		}
		
		if(addRegistrantInSignUp(prefix, attendeeid, first, last, status, attendeetype, email, aboutMe, OptInSMS))
		{
			Reporters.SuccessReport("Add Registrant in Sign Up", "Successfully created registrant in Sign Up");
		}
		else
		{
			Reporters.failureReport("Add Registrant in Sign Up", "Failed to create registrant in Sign Up");
		}
		
		click(signUpOR.btnSubmit,"Submit button");
		
		waitForInVisibilityOfElement(signUpOR.loading, "Loading");
		
		if(verifyConfirmationPage(email))
		{
			Reporters.SuccessReport("Verify Confirmation Page for attendee with email :"+email, "Successfully verified confirmation for registrant with email :"+email);
		}
		else
		{
			Reporters.failureReport("Verify Confirmation Page for attendee with email :"+email, "Failed to verify confirmation page for registrant: "+email);
		}
		
		if(verifyHeaderType(headerType))
		{
			Reporters.SuccessReport("Verify sign Up Form when <header-type> tag is: "+headerType, "Successfully verified Header Type when <header-type> tag is: "+headerType);
		}
		else
		{
			Reporters.failureReport("Verify sign Up Form when <header-type> tag is: "+headerType, "Failed to verify Header Type link when <header-type> tag is: "+headerType);
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");
		
		if(search(attendeeid))
		{
			Reporters.SuccessReport("Search for newly added Registrant with attendee "+attendeeid+"", "Registrant with attendee "+attendeeid+" is displayed");
		}
		else{
			Reporters.failureReport("Search for newly added Registrant with attendee "+attendeeid+"", "Registrant with attendee "+attendeeid+" is not displayed");
		}

		waitForVisibilityOfElement(EMT_RegistrantsOR.tblResults, "Registrant results");
		
		if(click(By.xpath("//td[text()='"+attendeeid+"']"),"Registrant with id: "+attendeeid))
		{
			if(verifySignUpRegistrantInfoInEMT(prefix, attendeeid, first, last, status, attendeetype, email, aboutMe, OptInSMS))
			{
				Reporters.SuccessReport("Verify Registrant "+attendeeid+" information in EMT which is created using Sign Up", "Successfully verified Registrant information in EMT");
			}
			else
			{
				Reporters.failureReport("Verify Registrant "+attendeeid+" information in EMT which is created using Sign Up", "Failed to verify registrant information in EMT");
			}
		}
		
		switchWindowByTitle("Event to be Moved | Sign up Form", 2);
		
		driver.navigate().back();
		
		click(signUpOR.btnSubmit,"Submit button");
		
		if(validateErrorMessagesInSignUp())
		{
			Reporters.SuccessReport("Validate error message when data is not provided to required fields", "Successfully validated error message");
		}
		else
		{
			Reporters.failureReport("Validate error message when data is not provided to required fields", "Failed to validate error message");
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		if(navigateToSignUpXMLPage())
		{
			Reporters.SuccessReport("Navigate to Sign Up XML Page", "Successfully navigated to Sign Up XML Page");
		}
		else
		{
			Reporters.failureReport("Navigate to Sign Up XML Page", "Failed to navigate to Sign Up XML Page");
		}
		
		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Signup Form", "Tab Filter");
		
		//un-check Prefix field required check box and verify whether that field is marked as required or not in Sign Up Site
		if(makeFieldOptional(fieldToBeOptional))
		{
			Reporters.SuccessReport("Make field optional "+fieldToBeOptional, "Field changed to optional "+fieldToBeOptional);
		}
		else
		{
			Reporters.failureReport("Make field optional "+fieldToBeOptional, "Failed to change the field to optional :"+fieldToBeOptional);
		}
		
		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");
		
		verifyTextPresent("Your changes have been saved!");
		
		switchWindowByTitle("Event to be Moved | Sign up Form", 2);
		
		refresh();
		
		//verify optional field
		if(verifyOptionalField(fieldToBeOptional))
		{
			Reporters.SuccessReport("Verify Optional Field in Sign Up home page: "+fieldToBeOptional, "field is made optional: "+fieldToBeOptional);
		}
		else
		{
			Reporters.failureReport("Verify Optional Field in Sign Up home page: "+fieldToBeOptional, "field is still Mandatory: "+fieldToBeOptional);
		}
		
		if(addRegistrantInSignUp(prefix, attendeeid, first, last, status, attendeetype, email, aboutMe, OptInSMS))
		{
			Reporters.SuccessReport("Add Registrant in Sign Up", "Successfully entered details for registrant in Sign Up");
		}
		else
		{
			Reporters.failureReport("Add Registrant in Sign Up", "Failed to enter detaild for registrant in Sign Up");
		}
		
		click(signUpOR.btnSubmit,"Submit button");
		
		verifyTextPresent("A record already exists with the email provided for this event!");
		
		if(closeWindow("Event to be Moved | Sign up Form", 2))
		{
			Reporters.SuccessReport("Close Sign Up window", "Successfully closed sign up window");
		}
		else
		{
			Reporters.failureReport("Close Sign Up window", "Failed to close sign up window");
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		
		if(navigateToSignUpXMLPage())
		{
			Reporters.SuccessReport("Navigate to Sign Up XML Page", "Successfully navigated to Sign Up XML Page");
		}
		else
		{
			Reporters.failureReport("Navigate to Sign Up XML Page", "Failed to navigate to Sign Up XML Page");
		}
		
		click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

		waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

		selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Signup Form", "Tab Filter");
		
		if(removeFieldsFromLayout("SignUpAdminCheckinForm",9))
		{
			Reporters.SuccessReport("Remove fields from Sign Up Screen Layout", getTabStatusMessage());
		}
		else
		{
			Reporters.failureReport("Remove fields from Sign Up Screen Layout", "Removing fields from Screen layout has failed");
		}
		
		click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

		verifyTextPresent("Your changes have been saved!");
		
		Thread.sleep(3000);
		
		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}
		
		if(search(attendeeid))
		{
			Reporters.SuccessReport("Search for registrant: "+attendeeid, "Successfully searched for registrant with id: "+attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for registrant: "+attendeeid, "Failed to search for registrant with id: "+attendeeid);
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");
		
		if(click(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div/div/table/tbody/tr/td[text()='"+attendeeid+"']"),attendeeid))
		{
			if(deleteRecord())
			{
				Reporters.SuccessReport("Delete registrant: "+attendeeid, "Successfully deleted attendee with id: "+attendeeid);
			}
			else
			{
				Reporters.failureReport("Delete registrant: "+attendeeid, "Failed to delete attendee with id: "+attendeeid);
			}
		}
		
		emtLogout=true;
  }
  /*
   * Reading data from TestData.xls under TestData folder	
   */
  @DataProvider
  public Object[][] getData() throws Exception {

  	return Data_Provider.getTableArray("addRegistrantInSignUp", "Key_AddReg");  //returning data from "addRegistrantInSignUp" sheet and "Key_AddReg" as a reference to read data
  }


  }
