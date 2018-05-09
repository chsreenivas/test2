package com.alliancetech.iLeads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DeleteTheEvent  extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups={"iLeads","RunAll"})
	
 
  public void DeleteTheEvent()throws Throwable{
		
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
	
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
@DataProvider
          public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CreateAndDeleteAnEvent", "Key_Create_And_Delete_An_Event");  //returning data from "CreateAndDeleteAnEvent" sheet and "Key_Create_And_Delete_An_Event" as a reference to read data
	}
}
