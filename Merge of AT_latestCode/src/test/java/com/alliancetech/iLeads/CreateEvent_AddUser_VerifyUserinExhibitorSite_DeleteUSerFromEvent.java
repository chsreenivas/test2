package com.alliancetech.iLeads;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CreateEvent_AddUser_VerifyUserinExhibitorSite_DeleteUSerFromEvent extends BusinessFunctions{

	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups={"iLeads","RunAll"})

	public void CreateEvent(String FirstName,String LastName,String Company,String Email,String EventName,
			String EventTag,String StartDate,String EndDate,String ConfigName,String BadgeType) throws Throwable {
		int BadgeTypeCount=0;
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

		if(addCustomerInformation(FirstName,LastName,Company,Email))
		{
			Reporters.SuccessReport("Add event details", "Customer "+FirstName+" details got added");
		}
		else{
			Reporters.failureReport("Add event details", "Customer "+FirstName+" details is not added");
		}

		if(addEventInformation(EventName,EventTag,StartDate,EndDate))
		{
			Reporters.SuccessReport("Add event information", "Event information got added");
		}
		else{
			Reporters.failureReport("Add event information", "Event information is not added");
		}

		if(searchForEventCreated(EventTag))
		{
			Reporters.SuccessReport("Search for event created", "Event with tag "+EventTag+" is available");
		}
		else{
			Reporters.failureReport("Search for event created", "Event with tag "+EventTag+" is not available");
		}

		if(configNewFile(ConfigName))
		{
			Reporters.SuccessReport("Config New File to event created", "Successfully configured new file to event");
		}
		else{
			Reporters.failureReport("Config New File to event created", "Failed to configure new file to event");
		}

		if(addDBField())
		{
			Reporters.SuccessReport("Verify that DB Fields are added", "DB Fields added successfully");
		}
		else{
			Reporters.failureReport("Verify that DB Fields are added", "Failed to add DB Fields");
		}

		if(badgeLayout(BadgeTypeCount, BadgeType))
		{
			Reporters.SuccessReport("Verify that Badge Layout is added", "Badge Layout added successfully");
		}
		else{
			Reporters.failureReport("Verify that Badge Layout is added", "Failed to add Badge Layout");
		}
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider

	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CreateAndDeleteAnEvent", "Key_Create_And_Delete_An_Event");  //returning data from "CreateAndDeleteAnEvent" sheet and "Key_Create_And_Delete_An_Event" as a reference to read data
	}


	@Test(dataProvider = ("getData1"),groups={"iLeads","RunAll"},priority=1)

	public void AddUserToTheEvent(String Username) throws Throwable {

		if(selectEventFromDropDown())
		{
			Reporters.SuccessReport("Select Event From The dropdown", "Event Selected Successfully");
		}
		else
		{
			Reporters.failureReport("Select Event From The dropdown", "Did not Select The Event Successfully");
		}


		if(addUserToEventAndVerify(Username))
		{
			Reporters.SuccessReport("Add User to the Event and Verify","User is Added and Verified Successfully");
		}
		else
		{
			Reporters.failureReport("Add User to the Event and Verify", "User is not Added and not Verified Successfully");
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
	public Object[][] getData1() throws Exception {

		return Data_Provider.getTableArray("IL_CreateUserAnsPassword", "Key_Add_User_To_The_Event");  //returning data from "CreateAndDeleteAnEvent" sheet and "Key_Create_And_Delete_An_Event" as a reference to read data

	}
	@Test(dataProvider = ("getData2"),groups={"iLeads","RunAll"},priority=2)

	public void verifyEventInExhibitorPageAndDeleteUserFromEvent(String Username,String EventName,String password) throws Throwable {
		{
			if(getileadsExhibitorURL())
			{
				Reporters.SuccessReport("Launch iLeads Exhibitor Application", "Application URL launched successfully");
			}
			else
			{
				Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
			}

			if(iLeadsExhibitorLogIn(Username,password))
			{
				Reporters.SuccessReport("Login into the Application", "login is successfull");
			}

			else{
				Reporters.failureReport("Login into the Application", "login is not successfull");
			}
		}
		if(verifyLeadimageInExhibiotrs())
		{
			Reporters.SuccessReport("Verify the lead image icon", "lead image icon is verified");
		}

		else{
			Reporters.failureReport("Verify the lead image icon", "lead image icon is not verified");
		}
		if(iLeadsExhibitorLogOut1())
		{
			Reporters.SuccessReport("Log out from Exhibitor application", "Log out sucessfull");
		}
		else
		{
			Reporters.failureReport("Log out from Exhibitor application", "Log out is not succesfull");
		}

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

		}

		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(selectEventFromDropDown())
		{
			Reporters.SuccessReport("Select Event From The dropdown", "Event Selected Successfully");
		}
		else
		{
			Reporters.failureReport("Select Event From The dropdown", "Did not Select The Event Successfully");
		}

		if(selectDropInAcessLevel())
		{
			Reporters.SuccessReport("Select the access level","Fullaccess is selected");
		}
		else
		{
			Reporters.failureReport("Select the access level", "Fullaccess is not selected");
		}

		if(iLeadsLogOut())
		{
			Reporters.SuccessReport("Log out from iLeads application", "Logged out from the application successfuly");
		}
		else{
			Reporters.failureReport("Log out from iLeads application", "Failed to log out from the application");
		}

		if(getileadsExhibitorURL())
		{
			Reporters.SuccessReport("Launch iLeads Exhibitor Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
		}

		if(iLeadsExhibitorLogIn(Username,password))
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}

		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(verifyLeadformInExhibiotrsFullacess())
		{
			Reporters.SuccessReport("Verify the lead image icon", "lead image icon is verified");
		}

		else{
			Reporters.failureReport("Verify the lead image icon", "lead image icon is not verified");
		}

		if(iLeadsExhibitorLogOut1())
		{
			Reporters.SuccessReport("Log out from Exhibitor application", "Log out sucessfull");
		}
		else
		{
			Reporters.failureReport("Log out from Exhibitor application", "Log out is not succesfull");
		}
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

		}

		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}
		if(selectEventFromDropDown())
		{
			Reporters.SuccessReport("Select Event From The dropdown", "Event Selected Successfully");
		}
		else
		{
			Reporters.failureReport("Select Event From The dropdown", "Did not Select The Event Successfully");
		}

		if(deleteTheUserFrmTheEvent(Username))
		{
			Reporters.SuccessReport("Delete the user", "User is deleted succesfully");
		}
		else
		{
			Reporters.failureReport("Delete the user", "User is not deleted succesfully");
		}
		if(iLeadsLogOut())
		{
			Reporters.SuccessReport("Log out from iLeads application", "Logged out from the application successfuly");
		}
		else{
			Reporters.failureReport("Log out from iLeads application", "Failed to log out from the application");
		}
		if(getileadsExhibitorURL())
		{
			Reporters.SuccessReport("Launch iLeads Exhibitor Application", "Application URL launched successfully");
		}
		else
		{
			Reporters.failureReport("Launch iLeads Application", "Application URL failed to launch ");
		}

		if(iLeadsExhibitorLogIn(Username,password))
		{
			Reporters.SuccessReport("Login into the Application", "login is successfull");
		}

		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(verifyEventNameForTheExhibitor(EventName))
		{
			Reporters.SuccessReport("Verify the Eventname", "Eve is verified");
		}

		else{
			Reporters.failureReport("Verify the lead image icon", "lead image icon is not verified");
		}
		
		if(iLeadsExhibitorLogOut1())
		{
			Reporters.SuccessReport("Log out from Exhibitor application", "Log out sucessfull");
		}
		else
		{
			Reporters.failureReport("Log out from Exhibitor application", "Log out is not succesfull");
		}

}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData2() throws Exception {

		return Data_Provider.getTableArray("IL_CreateUserAnsPassword", "Key_Exhibitors");  //returning data from "CreateAndDeleteAnEvent" sheet and "Key_Exhibiots" as a reference to read data
	}
}








