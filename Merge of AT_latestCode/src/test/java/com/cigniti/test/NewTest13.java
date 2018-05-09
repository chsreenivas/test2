package com.cigniti.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_Lite;
import com.cigniti.automation.utilities.Reporters;

public class NewTest13 extends BusinessFunctions {
  @Test
  public void AddSession() throws Throwable {
	  driver.get(configProps.getProperty("lite"));
	  if(type(EMT_Lite.LoginID, "himanshu.saraowgi6@gmail.com", "Login Id Text Box"))
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
	  if(click(EMT_Lite.SessionTab,"Session Tab"))
		  Reporters.SuccessReport("Click Session,", "Session Tab");
	  else
		  Reporters.failureReport("Click Session,", "SessionTabFail");
	  
	  if(click(EMT_Lite.Add_Ses_Button,"Ädd session button"))
		  Reporters.SuccessReport("Click Add,", "Add");
	  else
		  Reporters.failureReport("Click Add,", "Add Failed");
	  
	  if(type(EMT_Lite.f("Title"),"Title", "Title"))
		  Reporters.SuccessReport("Add Data,", "Add");
	  else
		  Reporters.failureReport("Add Data,", "Add Failed");
	  
	  
	  driver.switchTo().frame(0);
	  
	  if(type(EMT_Lite.Add_Detail,"Detail", "Detail"))
		  Reporters.SuccessReport("Add Data,", "Add");
	  else
		  Reporters.failureReport("Add Data,", "Add Failed");
	  waitForVisibilityOfElement(EMT_Lite.Add_Reg_Button, "Exhibitor results");
  }
}
