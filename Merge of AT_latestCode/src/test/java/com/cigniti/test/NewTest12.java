package com.cigniti.test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_Lite;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class NewTest12 extends BusinessFunctions {
  @Test(dataProvider=("getData"))
  public void addAttendee(String First,String Last,String Email) throws Throwable {
	  
	  driver.get(configProps.getProperty("lite"));
	  if(type(EMT_Lite.LoginID, "himanshu.saraowgi8@gmail.com", "Login Id Text Box"))
		  Reporters.SuccessReport("Enter Login ID,", "Login ID Entered");
	  else
		  Reporters.failureReport("Enter Login ID,", "Login ID Not Entered");
	  
	  if(type(EMT_Lite.Password, "112233a", "Password Text Box"))
		  Reporters.SuccessReport("Enter Password,", "Password Entered");
	  else
		  Reporters.failureReport("Enter Password,", "Password Not Entered");
	  
	  if(click(EMT_Lite.LoginButton,"Logged In"))
		  Reporters.SuccessReport("Click Login,", "Login");
	  
	  else
		  Reporters.failureReport("Click Login,", "Login Failed");
	  
	  waitForVisibilityOfElement(EMT_Lite.Add_Reg_Button, "Exhibitor results");
	  if(click(EMT_Lite.Add_Reg_Button,"Ädd Attendee button"))
		  Reporters.SuccessReport("Click Add,", "Add");
	  else
		  Reporters.failureReport("Click Add,", "Add Failed");
	  
	  if(type(EMT_Lite.f("First Name"),First, "First name"))
		  Reporters.SuccessReport("Add Data,", "Add");
	  else
		  Reporters.failureReport("Add Data,", "Add Failed");
	  if(type(EMT_Lite.f("Last Name"),Last, "Last name"))
		  Reporters.SuccessReport("Add Data,", "Add");
	  else
		  Reporters.failureReport("Add Data,", "Add Failed");
	  if(type(EMT_Lite.f("Email"),Email, "Email name"))
		  Reporters.SuccessReport("Add Data,", "Add");
	  else
		  Reporters.failureReport("Add Data,", "Add Failed");
	  click(EMT_Lite.SubmitButton,"Sublit");
	  Thread.sleep(200);
	  click(EMT_Lite.DropDown,"DropDown");
	  click(EMT_Lite.Logout,"Logout");
	  
	  
	
	  
  }
  @DataProvider
  public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("TestSheet", "Himanshu");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}
  
  
  
}
