package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.alliancetech.objectrepository.EMT_RoomsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DirectEditRooms extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void CreateARoom(String Name,String capacity,String Description,String zones) throws Throwable {
		 if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
		  {
			  driver.unregister(myListener);
		  }
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
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
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Rooms"), "Loading");

		waitForVisibilityOfElement(EMT_RoomsOR.lstRooms, "Rooms List");

		if(click(By.xpath("//tr/td[text()='"+Name+"']"),"Room"))
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
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_AddRooms");  //returning data from "EMTRoom" sheet and "Key_AddRooms" as a reference to read data
	}

	@Test(dataProvider = ("getEditData"),groups= {"EMT", "RunAll"},priority=1)
	public void EditRooms(String Name,String capacity,String Description,String zones) throws Throwable
	{
		if(clickTabFromViewMore(CommonOR.lnkRooms,"Rooms Tab")){
			Reporters.SuccessReport("Navigate to Rooms Page", "Required steps have been performed above successfully");
		}
		else{
			Reporters.failureReport("Navigate to Rooms Page", "Unable to Perform all the required steps");
		}

		if(search(Name)){
			Reporters.SuccessReport("Search for Room "+Name, "Required Steps are performed Successfully");
		}
		else{
			Reporters.failureReport("Search for Room "+Name, "unable to perform all steps");
		}

		Thread.sleep(2000);
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Rooms"),"Rooms");
		waitForVisibilityOfElement(EMT_RoomsOR.lstRooms, "Rooms List");
		
		if(js_click(By.xpath("//tr/td[text()='"+Name+"']"),Name))
		{
			Thread.sleep(1000);
			
			if(EditRoomDetails( Name, capacity, Description, zones))
			{
				Reporters.SuccessReport("Edit Room Information details", "All Room Information Details are edited Successfully");
			}
			else
			{
				Reporters.failureReport("Edit Room Information details", "Unable to edit Room information");
			}
			
			refresh();
			
			if(verifyRoomDetails(Name, capacity, Description, zones))
			{
				Reporters.SuccessReport("Verification of Room details", "All the details entered for Room are valid");
			}
			else
			{
				Reporters.failureReport("Verification of Room Details", "Details entered for Room are invalid");
			}
			
			if(js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link")){
				
				waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
				Thread.sleep(2000);
				
				click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
				
				emtLogOut();
				
				emtLogIn();
				
				clickTabFromViewMore(CommonOR.lnkRooms, "Rooms Tab");

				search(Name);

				if(verifyInTable("Rooms",Name))
				{
					Reporters.failureReport("Room verification after delete", ""+Name+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Room verification after delete", ""+Name+" is not available in table,hence successfully deleted");
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

		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_EditRooms");  //returning data from "EMT_DirectEdit" sheet and "Key_EditRegistrant" as a reference to read data
	}
	}


