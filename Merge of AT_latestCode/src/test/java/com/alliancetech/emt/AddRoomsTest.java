package com.alliancetech.emt;
/**
 * This Test Case verifies adding of Rooms
 * Reads Data from EMTRoom Sheet
 * reference Test rail-iConnect_EMT(Create Rooms)
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_RoomsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddRoomsTest extends BusinessFunctions{
	public static int logIn=0;

	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void EMT_AddRooms(String Name,String capacity,String Description,String zones) throws Throwable {

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
		if(navigateToAddRoomspage())
		{
			Reporters.SuccessReport("Navigation to Add Rooms Page", "Navigated to Add New Rooms successfully");
		}
		else
		{
			Reporters.failureReport("Navigation to Add Rooms Page", "Navigation to Add New Rooms failed");
		}

		verifyText(EMT_RoomsOR.lblAddNewRoom, "Add New Room", "Add New Room Page");

		if(enterRoomDetails(Name,capacity,Description,zones)){
			Reporters.SuccessReport("Enter Room Details", "Room details are successfully entered with Name"+Name+" and Capacity"+capacity);
		}
		else{
			Reporters.failureReport("Enter Room Details", "Unable to Enter room details");
		}

		verifyTextPresent("Room has been added!");
		
		getEMTURL();

		if(search(Name))
		{
			Reporters.SuccessReport("Search newly added room ", ""+Name+" room updated successfully");
		}
		else
		{
			Reporters.failureReport("Search newly added room", ""+Name+" room is not updated successfully");
		}

		waitForVisibilityOfElement(EMT_RoomsOR.lstRooms, "Rooms List");

		waitForElementPresent(EMT_RoomsOR.verifyRoom(Name, capacity));
		
		if(click(EMT_RoomsOR.verifyRoom(Name, capacity),"Room"))
		{
			if(verifyRoomDetails(Name, capacity, Description, zones))
			{
				Reporters.SuccessReport("Verification of Room details", "All the details entered for Room are valid");
			}
			else
			{
				Reporters.failureReport("Verification of Room Details", "Details entered for Room are invalid");
			}
			
		}
		
		getEMTURL();
		emtLogout=true;
		
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("EMTRoom", "Key_AddRooms");  //returning data from "EMTRoom" sheet and "Key_AddRooms" as a reference to read data
	}
	
	
}
