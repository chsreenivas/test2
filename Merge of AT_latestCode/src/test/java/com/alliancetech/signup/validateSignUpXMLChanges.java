package com.alliancetech.signup;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.signUpOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class validateSignUpXMLChanges extends BusinessFunctions{
	public static int logIn=0;
  @Test(dataProvider = ("getData"),groups={"SignUp","RunAll","EMT"})
  public void validate_SignUpXMLChanges(String signup,String colorButton,String colorLink,String needHelp,String headerType,String updateSignUp,String updateNeedHelp,String Introdata,String UpdatedIntrodata) throws Throwable {
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
		
		switchToFrameByIndex(0);
		
		if(removeTag(signUpOR.txtEnableSignUp))
		{
			Reporters.SuccessReport("Remove <enable-signup> tag from Sign Up XML Page", "<enable-signup> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <enable-signup> tag from Sign Up XML Page", "<enable-signup> tag is failed to remove");
		}
		
		if(insertEnableSignUpTag(signup))
		{
			Reporters.SuccessReport("Insert <enable-signup> tag in Sign Up XML Page", "<enable-signup> tag got inserted successfully with value: "+signup);
		}
		else
		{
			Reporters.failureReport("Insert <enable-signup> tag in Sign Up XML Page", "Failed to insert <enable-signup> tag with value: "+signup);
		}
		
		if(removeTag(signUpOR.txtColorButton))
		{
			Reporters.SuccessReport("Remove <color-button> tag from Sign Up XML Page", "<color-button> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <color-button> tag from Sign Up XML Page", "<color-button> tag is failed to remove");
		}
		
		if(insertColorButton(colorButton))
		{
			Reporters.SuccessReport("Insert <color-button> tag in Sign Up XML Page", "<color-button> tag got inserted successfully with value: "+colorButton);
		}
		else
		{
			Reporters.failureReport("Insert <color-button> tag in Sign Up XML Page", "Failed to insert <color-button> tag with value: "+colorButton);
		}
		
		if(removeTag(signUpOR.txtColorLink))
		{
			Reporters.SuccessReport("Remove <color-link> tag from Sign Up XML Page", "<color-link> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <color-link> tag from Sign Up XML Page", "<color-link> tag is failed to remove");
		}
		
		if(insertColorLink(colorLink))
		{
			Reporters.SuccessReport("Insert <color-button> tag in Sign Up XML Page", "<color-button> tag got inserted successfully with value: "+colorLink);
		}
		else
		{
			Reporters.failureReport("Insert <color-button> tag in Sign Up XML Page", "Failed to insert <color-button> tag with value: "+colorLink);
		}
		
		if(removeTag(signUpOR.txtEnableNeedHelp))
		{
			Reporters.SuccessReport("Remove <enable-need-help> tag from Sign Up XML Page", "<enable-need-help> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <enable-need-help> tag from Sign Up XML Page", "<enable-need-help> tag is failed to remove");
		}
		
		if(insertEnableNeedHelp(needHelp))
		{
			Reporters.SuccessReport("Insert <enable-need-help> tag in Sign Up XML Page", "<enable-need-help> tag got inserted successfully with value: "+needHelp);
		}
		else
		{
			Reporters.failureReport("Insert <enable-need-help> tag in Sign Up XML Page", "Failed to insert <enable-need-help> tag with value: "+needHelp);
		}
		
		if(removeTag(signUpOR.txtHeaderType))
		{
			Reporters.SuccessReport("Remove <header-type> tag from Sign Up XML Page", "<header-type> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <header-type> tag from Sign Up XML Page", "<header-type> tag is failed to remove");
		}
		
		if(insertHeaderType(headerType))
		{
			Reporters.SuccessReport("Insert <header-type> tag in Sign Up XML Page", "<header-type> tag got inserted successfully with value: "+headerType);
		}
		else
		{
			Reporters.failureReport("Insert <header-type> tag in Sign Up XML Page", "Failed to insert <header-type> tag with value: "+headerType);
		}
		
		clickSaveXMLFileButton();
		
		if(openSiteInNewWindow("Event to be Moved | Sign up Form"))
		{
			getSignUpURL();
			Reporters.SuccessReport("Open SignUp Site in new window", "SignUp Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open SignUp Site in new window","SignUp site has failed to open in new window");
		}
		
		if(verifySignUpForm(signup))
		{
			Reporters.SuccessReport("Verify sign Up Form when <enable-signup> tag is: "+signup, "Successfully verified Sign Up form when <enable-signup> tag is: "+signup);
		}
		else
		{
			Reporters.failureReport("Verify sign Up Form when <enable-signup> tag is: "+signup, "Failed to verify sign up form when <enable-signup> tag is: "+signup);
		}
		
		if(verifyNeedHelp(needHelp))
		{
			Reporters.SuccessReport("Verify sign Up Form when <enable-need-help> tag is: "+needHelp, "Successfully verified Need Help link when <enable-need-help> tag is: "+needHelp);
		}
		else
		{
			Reporters.failureReport("Verify sign Up Form when <enable-need-help> tag is: "+needHelp, "Failed to verify Need Help link when <enable-need-help> tag is: "+needHelp);
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
		
		switchToFrameByIndex(0);
		
		if(removeTag(signUpOR.txtEnableSignUp))
		{
			Reporters.SuccessReport("Remove <enable-signup> tag from Sign Up XML Page", "<enable-signup> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <enable-signup> tag from Sign Up XML Page", "<enable-signup> tag is failed to remove");
		}
		
		if(insertEnableSignUpTag(updateSignUp))
		{
			Reporters.SuccessReport("Insert <enable-signup> tag in Sign Up XML Page", "<enable-signup> tag got inserted successfully with value: "+updateSignUp);
		}
		else
		{
			Reporters.failureReport("Insert <enable-signup> tag in Sign Up XML Page", "Failed to insert <enable-signup> tag with value: "+updateSignUp);
		}
		
		if(removeTag(signUpOR.txtEnableNeedHelp))
		{
			Reporters.SuccessReport("Remove <enable-need-help> tag from Sign Up XML Page", "<enable-need-help> tag got removed successfully");
		}
		else
		{
			Reporters.failureReport("Remove <enable-need-help> tag from Sign Up XML Page", "<enable-need-help> tag is failed to remove");
		}
		
		if(insertEnableNeedHelp(updateNeedHelp))
		{
			Reporters.SuccessReport("Insert <enable-need-help> tag in Sign Up XML Page", "<enable-need-help> tag got inserted successfully with value: "+updateNeedHelp);
		}
		else
		{
			Reporters.failureReport("Insert <enable-need-help> tag in Sign Up XML Page", "Failed to insert <enable-need-help> tag with value: "+updateNeedHelp);
		}
		
		clickSaveXMLFileButton();
		
		switchWindowByTitle("Event to be Moved | Sign up Form", 2);
		
		refresh();
		
		if(verifySignUpForm(updateSignUp))
		{
			Reporters.SuccessReport("Verify sign Up Form when <enable-signup> tag is: "+updateSignUp, "Successfully verified Sign Up form when <enable-signup> tag is: "+updateSignUp);
		}
		else
		{
			Reporters.failureReport("Verify sign Up Form when <enable-signup> tag is: "+updateSignUp, "Failed to verify sign up form when <enable-signup> tag is: "+updateSignUp);
		}
		
		if(verifyNeedHelp(updateNeedHelp))
		{
			Reporters.SuccessReport("Verify sign Up Form when <enable-need-help> tag is: "+updateNeedHelp, "Successfully verified Need Help link when <enable-need-help> tag is: "+updateNeedHelp);
		}
		else
		{
			Reporters.failureReport("Verify sign Up Form when <enable-need-help> tag is: "+updateNeedHelp, "Failed to verify Need Help link when <enable-need-help> tag is: "+updateNeedHelp);
		}
		
		if(verifyColor(signUpOR.btnSubmit,"background-color",colorButton))
		{
			Reporters.SuccessReport("Verify button color: "+colorButton, "Successfully verified button color : "+colorButton);
		}
		else
		{
			Reporters.failureReport("Verify button color: "+colorButton, "Failed to verify button color :"+colorButton);
		}
		
		if(verifyColor(signUpOR.lnkNeedHelp,"color",colorLink))
		{
			Reporters.SuccessReport("Verify link color: "+colorLink, "Successfully verified link color : "+colorLink);
		}
		else
		{
			Reporters.failureReport("Verify link color: "+colorLink, "Failed to verify link color :"+colorLink);
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(insertData(Introdata))
		{
			Reporters.SuccessReport("Insert Data into Intro.html ", "Successfully inserted data into Intro.html page");
		}
		else
		{
			Reporters.failureReport("Insert Data into Intro.html ", "Failed to insert data into Intro.html page");
		}
		
		clickSaveHTMLFileButton();
		
		switchWindowByTitle("Event to be Moved | Sign up Form", 2);
		
		refresh();
		
		verifyTextPresent(Introdata);
		
		switchWindowByTitle("Reporting Site", 1);
		
		if(insertData(UpdatedIntrodata))
		{
			Reporters.SuccessReport("Insert Data into Intro.html ", "Successfully inserted data into Intro.html page");
		}
		else
		{
			Reporters.failureReport("Insert Data into Intro.html ", "Failed to insert data into Intro.html page");
		}
		
		clickSaveHTMLFileButton();
		
		switchWindowByTitle("Event to be Moved | Sign up Form", 2);
		
		refresh();
		
		verifyTextPresent(UpdatedIntrodata);
		
		if(closeWindow("Event to be Moved | Sign up Form", 2))
		{
			Reporters.SuccessReport("Close Sign Up window", "Successfully closed sign up window");
		}
		else
		{
			Reporters.failureReport("Close Sign Up window", "Failed to close sign up window");
		}
		
		switchWindowByTitle("Reporting Site", 1);
		
		emtLogout=true;
		
  }

/*
 * Reading data from TestData.xls under TestData folder	
 */
@DataProvider
public Object[][] getData() throws Exception {

	return Data_Provider.getTableArray("validateSignUpXMLTags", "Key_SignUpXMLTags");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
}


}
