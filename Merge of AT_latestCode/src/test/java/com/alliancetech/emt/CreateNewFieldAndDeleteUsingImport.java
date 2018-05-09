package com.alliancetech.emt;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.CreateNewFieldUsingImportOR;
import com.alliancetech.objectrepository.DataImports;
import com.alliancetech.objectrepository.EMT_EmailCampignsOR;
import com.alliancetech.objectrepository.ScreenLayoutOR;
import com.cigniti.automation.utilities.CSVFileIO;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class CreateNewFieldAndDeleteUsingImport extends BusinessFunctions{
	ArrayList<String>[] inputData ;
  @Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
  public void createNewField_Verify_DeleteFieldForRegistrant(String fieldName,String AgeField,String label,String type,String usage, String width,String name) throws Throwable {
	  
	  if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
	  {
		  driver.unregister(myListener);
	  }
	inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\RegistrantNewField.csv").getAbsolutePath());
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
	else{
		Reporters.failureReport("Login into the Application", "login is not successfull");
	}

	if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Administration page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Administration page is not displayed");
	}
	click(DataImports.lnkImportRegistrantsData, "Import Registrants Data");

	if(uploadFile(new File("TestData\\RegistrantNewField.csv").getAbsolutePath()))
	{
		Reporters.SuccessReport("Verify uploading of file", "Registrants csv file got successfully uploaded");
	}
	else
	{
		Reporters.failureReport("Verify uploading of file", "Registrants csv file failed to upload");
	}
	if(js_click(DataImports.btnUpload, "Upload Button"))
	{
		Reporters.SuccessReport("Verify Upload button", "Able to click on: Upload Button");
	}
	else
	{
		Reporters.failureReport("Verify Upload button","Not Able to click on: Upload Button");
	}

	waitForInVisibilityOfElement(By.id("status-msg-display"), "Auto Refresh");

	if(driver.getPageSource().contains("Confirm?")){
		click(DataImports.btnOK_confirmPopUp,"Ok button in Confirm Popup");
	}

	Thread.sleep(5000);
	if(mapFields())
	{
		Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
	}
	else
	{
		Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
	}

	if(validateDataAndProgressOperation())
	{
		Reporters.SuccessReport("Verify data validation and progress operation", "Data has been successfully validated and 100% progressed");
	}
	else
	{
		Reporters.failureReport("Verify data validation and progress operation", "Data has failed to validate and stopped progression");
	}
	
	if(emtLogOut())
	{
		Reporters.SuccessReport("Log Out from EMT Site", "logout is successfull");
	}
	else
	{
		Reporters.failureReport("Log Out from EMT Site", "logout is not successfull");
	}
	
	if(openSiteInNewWindow("Reload Cache"))
	{
		getReloadPropsURL();
		Reporters.SuccessReport("Open Reload Cache Site in new window", "Reload Cache Site has been opened in new window");
	}
	else
	{
		Reporters.failureReport("Open Reload Cache Site in new window","Reload Cache site has failed to open in new window");
	}
	
	click(CommonOR.btnReloadPropsOnThisServer,"Reload Props On this server button");
	
	//closeWindow("Reload Cache", 2);
	
	switchWindowByTitle("Reporting Site", 1);
	
	refresh();
	
	if(emtLogIn())
	{
		Reporters.SuccessReport("Log In to EMT Site", "login is successfull");
	}
	else
	{
		Reporters.failureReport("Log In to EMT Site", "login is not successfull");
	}
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
	
	js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Screen layout link");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Registrants", "Registrants");
	
	click(By.id("records-to-display"),"Records to Display");
	
	waitForVisibilityOfElement(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records");
	
	click(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records");
	
	if(verifyFieldInResultsList(fieldName))
	{
		Reporters.SuccessReport("Verify Field name "+fieldName+" in results list and delete it", "Field name "+fieldName+" is available in result list");
	}
	else
	{
		Reporters.failureReport("Verify Field name "+fieldName+" in results list and delete it", "Field name "+fieldName+" is not available in result list");
	}
	
	checkForDuplicateField(fieldName);
	
	if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Administration page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Administration page is not displayed");
	}
	
	click(DataImports.lnkImportRegistrantsData, "Import Registrants Data");
	
	inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\RegNewField.csv").getAbsolutePath());

	if(uploadFile(new File("TestData\\RegNewField.csv").getAbsolutePath()))
	{
		Reporters.SuccessReport("Verify uploading of file", "Registrants csv file got successfully uploaded");
	}
	else
	{
		Reporters.failureReport("Verify uploading of file", "Registrants csv file failed to upload");
	}
	if(js_click(DataImports.btnUpload, "Upload Button"))
	{
		Reporters.SuccessReport("Verify Upload button", "Able to click on: Upload Button");
	}
	else
	{
		Reporters.failureReport("Verify Upload button","Not Able to click on: Upload Button");
	}

	waitForInVisibilityOfElement(By.id("status-msg-display"), "Auto Refresh");

	if(driver.getPageSource().contains("Confirm?")){
		click(DataImports.btnOK_confirmPopUp,"Ok button in Confirm Popup");
	}

	Thread.sleep(5000);
	if(mapFields())
	{
		Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
	}
	else
	{
		Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
	}

	if(validateDataAndProgressOperation())
	{
		Reporters.SuccessReport("Verify data validation and progress operation", "Data has been successfully validated and 100% progressed");
	}
	else
	{
		Reporters.failureReport("Verify data validation and progress operation", "Data has failed to validate and stopped progression");
	}
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
	
	if(emtLogOut())
	{
		Reporters.SuccessReport("Log Out from EMT Site", "logout is successfull");
	}
	else
	{
		Reporters.failureReport("Log Out from EMT Site", "logout is not successfull");
	}
	
	switchWindowByTitle("Reload Cache", 2);
	
	click(CommonOR.btnReloadPropsOnThisServer,"Reload Props On this server button");
	
	closeWindow("Reload Cache", 2);
	
	Thread.sleep(2000);

	switchWindowByTitle("Reporting Site", 1);
	
	refresh();
	
	Thread.sleep(2000);
	
	if(emtLogIn())
	{
		Reporters.SuccessReport("Log In to EMT Site", "login is successfull");
	}
	else
	{
		Reporters.failureReport("Log In to EMT Site", "login is not successfull");
	}
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
	
	js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Field link");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Registrants", "Registrants");
	
	if(verifyFieldInResultsList(AgeField))
	{
		Reporters.SuccessReport("Verify Field name "+AgeField+" in results list and delete it", "Field name "+AgeField+" is available in result list");
	}
	else
	{
		Reporters.failureReport("Verify Field name "+AgeField+" in results list and delete it", "Field name "+AgeField+" is not available in result list");
	}
	
	checkForDuplicateField(AgeField);
	
	if(addNewField(label, type, usage, width))
	{
		Reporters.SuccessReport("Add New Field", "Successfully Created new field "+label+" of type "+type);
	}
	else
	{
		Reporters.failureReport("Add New Field", "Failed to create new field "+label+" of type "+type);
	}
	
	Thread.sleep(2000);
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading");
	
	waitForVisibilityOfElement(EMT_EmailCampignsOR.lnkTools,"Tools link");
	
	click(EMT_EmailCampignsOR.lnkTools,"Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(ScreenLayoutOR.lnkManageScreenLayouts)).perform();
	
	js_click(ScreenLayoutOR.lnkManageScreenLayouts,"Manage Screen layout link");
	
	click(ScreenLayoutOR.btnDetailLayout,"Detail Layout button");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	waitForElementPresent(CreateNewFieldUsingImportOR.btnEdit);
	
	click(CreateNewFieldUsingImportOR.btnEdit, "Edit Icon");
	
	if(placeNewFieldsInScreenLayout(fieldName,AgeField,label))
	{
		Reporters.SuccessReport("Set new fields "+fieldName+" "+AgeField+" "+label+" in Registrant Detail screen layout", "Successfully fields got added to screen layout");
	}
	else
	{
		Reporters.failureReport("Set new fields "+fieldName+" "+AgeField+" "+label+" in Registrant Detail screen layout", "Failed to add fields to screen layout");
	}
	
	if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
	{
		Reporters.SuccessReport("Verify Page", "Administration page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Administration page is not displayed");
	}

	if(navigateToCheckinXML())
	{
		Reporters.SuccessReport("Navigate to Checkin XML Site", "Successfully navigated to Checkin XML Site");
	}
	else
	{
		Reporters.failureReport("Navigate to Checkin XML Site", "Faile to navigate to Checkin XML Site");
	}
	
	click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

	waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

	selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");
	
	if(placeNewFieldsInScreenLayout(fieldName,AgeField,label))
	{
		Reporters.SuccessReport("Set new fields "+fieldName+" "+AgeField+" "+label+" in Registrant Detail screen layout", "Successfully fields got added to screen layout");
	}
	else
	{
		Reporters.failureReport("Set new fields "+fieldName+" "+AgeField+" "+label+" in Registrant Detail screen layout", "Failed to add fields to screen layout");
	}
	
	click(ScreenLayoutOR.btnSaveScreenLayout,"Save Screen layout button");

	verifyTextPresent("Your changes have been saved!");
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	if(navigateToCreateViewBypage())
	{
		Reporters.SuccessReport("Navigate to Create View By Page", "Successfully navigated to Create View By Page");
	}
	else
	{
		Reporters.failureReport("Navigate to Create View By Page", "Failed to navigate to Create View By Page");
	}
	
	if(createViewBy(name))
	{
		Reporters.SuccessReport("Create View By "+name, "Successfully created view By with name: "+name);
	}
	else
	{
		Reporters.failureReport("Create View By "+name, "Failed to create view By with name: "+name);
	}
	
	if(setFieldsInViewBy(fieldName, AgeField, label))
	{
		Reporters.SuccessReport("Set fields in view By", "Successfully placed fields in View By");
	}
	else
	{
		Reporters.SuccessReport("Set fields in view By", "Failed to set fields in View By");
	}
	
	click(CreateNewFieldUsingImportOR.btnSave,"Save button");
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
	
	js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Screen layout link");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Registrants", "Registrants");
	
	if(deleteField(AgeField))
	{
		Reporters.SuccessReport("Delete field "+AgeField+" from Manage fields", "Field "+AgeField+" deleted from Manage fields");
	}
	else
	{
		Reporters.failureReport("Delete field "+AgeField+" from Manage fieldst", "Field "+AgeField+" not deleted from Manage fields");
	}
	
	isElementPresent(CreateNewFieldUsingImportOR.txtDeleteFieldErrorMessageForViewBy, "The field is being used by at least one View By");
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	if(deleteViewBy(name))
	{
		Reporters.SuccessReport("Delete view By "+name+" from registrants", "View By "+name+" got deleted successfully");
	}
	else
	{
		Reporters.failureReport("Delete view By "+name+" from registrants", "View By "+name+" is not deleted");
	}
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
	
	js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Field link");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Registrants", "Registrants");
	
	if(deleteField(fieldName))
	{
		Reporters.SuccessReport("Delete field "+fieldName+" from Manage fields", "Field "+fieldName+" deleted from Manage fields");
	}
	else
	{
		Reporters.failureReport("Delete field "+fieldName+" from Manage fields", "Field "+fieldName+" not deleted from Manage fields");
	}
	
	isElementPresent(CreateNewFieldUsingImportOR.txtDeleteFieldErrorMessageForScreenLayout, "The field is being used by at least one Application Screen Layout - C4P, Check-In, or Signup!");
	
	if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administration"))
	{
		Reporters.SuccessReport("Verify Page", "Administration page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Administration page is not displayed");
	}

	if(navigateToCheckinXML())
	{
		Reporters.SuccessReport("Navigate to Checkin XML Site", "Successfully navigated to Checkin XML Site");
	}
	else
	{
		Reporters.failureReport("Navigate to Checkin XML Site", "Faile to navigate to Checkin XML Site");
	}
	
	click(ScreenLayoutOR.btnSetScreenLayout,"Set Screen Layout Button");

	waitForElementPresent(ScreenLayoutOR.ddCheckInPageFilter);

	selectByVisibleText(ScreenLayoutOR.ddCheckInPageFilter, "Admin CheckIn Confirm", "Tab Filter");
	
	if(removeFieldsFromScreenLayout(fieldName, AgeField, label))
	{
		Reporters.SuccessReport("Remove fields from screenlayout", "Fields got successfully removed from screenlayout");
	}
	else
	{
		Reporters.failureReport("Remove fields from screenlayout", "failed to remove fields from screenlayout");
	}
	
	if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
	{
		Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
	}
	else
	{
		Reporters.failureReport("Verify Page", "Registrants page is not displayed");
	}
	
	js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
	
	new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
	
	js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Fields link");
	
	waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
	
	//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Registrants", "Registrants");
	
	if(deleteField(fieldName))
	{
		Reporters.SuccessReport("Delete field "+fieldName+" from screenlayout", "Field "+fieldName+" deleted from screen layout");
	}
	else
	{
		Reporters.failureReport("Delete field "+fieldName+" from screenlayout", "Field "+fieldName+" not deleted from screen layout");
	}
	
	if(deleteField(AgeField))
	{
		Reporters.SuccessReport("Delete field "+AgeField+" from screenlayout", "Field "+AgeField+" deleted from screen layout");
	}
	else
	{
		Reporters.failureReport("Delete field "+AgeField+" from screenlayout", "Field "+AgeField+" not deleted from screen layout");
	}
	
	if(deleteField(label))
	{
		Reporters.SuccessReport("Delete field "+label+" from screenlayout", "Field "+label+" deleted from screen layout");
	}
	else
	{
		Reporters.failureReport("Delete field "+label+" from screenlayout", "Field "+label+" not deleted from screen layout");
	}
	
	
	if(emtLogOut())
	{
		Reporters.SuccessReport("Log Out from EMT Site", "logout is successfull");
	}
	else
	{
		Reporters.failureReport("Log Out from EMT Site", "logout is not successfull");
	}
	
  }
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CreateAndDeleteNewField", "Key_Registrant");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}
	
	@Test(dataProvider = ("getDataForSession"),groups= {"EMT", "RunAll"})
	  public void createNewField_Verify_DeleteFieldForSession(String fieldName) throws Throwable {
		  
		  if(configProps.getProperty("HighlightElements").equalsIgnoreCase("true"))
		  {
			  driver.unregister(myListener);
		  }
		inputData=new CSVFileIO().getAllRecordsAsArrayList(new File("TestData\\SessionNewField.csv").getAbsolutePath());
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
		else{
			Reporters.failureReport("Login into the Application", "login is not successfull");
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}
		click(DataImports.lnkImportSessionsData, "Import Sessions Data");

		if(uploadFile(new File("TestData\\SessionNewField.csv").getAbsolutePath()))
		{
			Reporters.SuccessReport("Verify uploading of file", "Registrants csv file got successfully uploaded");
		}
		else
		{
			Reporters.failureReport("Verify uploading of file", "Registrants csv file failed to upload");
		}
		if(js_click(DataImports.btnUpload, "Upload Button"))
		{
			Reporters.SuccessReport("Verify Upload button", "Able to click on: Upload Button");
		}
		else
		{
			Reporters.failureReport("Verify Upload button","Not Able to click on: Upload Button");
		}

		waitForInVisibilityOfElement(By.id("status-msg-display"), "Auto Refresh");

		if(driver.getPageSource().contains("Confirm?")){
			click(DataImports.btnOK_confirmPopUp,"Ok button in Confirm Popup");
		}

		Thread.sleep(5000);
		if(mapFields())
		{
			Reporters.SuccessReport("Verification of Fields mapping", "All fields are mapped correctly");
		}
		else
		{
			Reporters.failureReport("Verification of Fields mapping", "Fields are failed to map");
		}

		if(validateDataAndProgressOperation())
		{
			Reporters.SuccessReport("Verify data validation and progress operation", "Data has been successfully validated and 100% progressed");
		}
		else
		{
			Reporters.failureReport("Verify data validation and progress operation", "Data has failed to validate and stopped progression");
		}
		
		if(emtLogOut())
		{
			Reporters.SuccessReport("Log Out from EMT Site", "logout is successfull");
		}
		else
		{
			Reporters.failureReport("Log Out from EMT Site", "logout is not successfull");
		}
		
		if(openSiteInNewWindow("Reload Cache"))
		{
			getReloadPropsURL();
			Reporters.SuccessReport("Open Reload Cache Site in new window", "Reload Cache Site has been opened in new window");
		}
		else
		{
			Reporters.failureReport("Open Reload Cache Site in new window","Reload Cache site has failed to open in new window");
		}
		
		click(CommonOR.btnReloadPropsOnThisServer,"Reload Props On this server button");
		
		closeWindow("Reload Cache", 2);
		
		switchWindowByTitle("Reporting Site", 1);
		
		refresh();
		
		if(emtLogIn())
		{
			Reporters.SuccessReport("Log In to EMT Site", "login is successfull");
		}
		else
		{
			Reporters.failureReport("Log In to EMT Site", "login is not successfull");
		}
		
		if(clickTabFromViewMore(CommonOR.lnkSessions, "Sessions Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Sessions page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Sessions page is not displayed");
		}
		
		js_click(EMT_EmailCampignsOR.lnkTools, "Tools link");
		
		new Actions(driver).moveToElement(driver.findElement(CreateNewFieldUsingImportOR.lnkManageField)).perform();
		
		js_click(CreateNewFieldUsingImportOR.lnkManageField,"Manage Screen layout link");
		
		waitForInVisibilityOfElement(CommonOR.ajaxLoad, "Loading...");
		
		//selectByVisibleText(CreateNewFieldUsingImportOR.ddTabFilter, "Sessions", "Sessions");
		
		/*click(By.id("records-to-display"),"Records to Display");
		
		waitForVisibilityOfElement(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records");
		
		click(By.xpath("//a[text()='Display 500 Records']"),"Display 500 records");*/
		
		if(verifyFieldInResultsList(fieldName))
		{
			Reporters.SuccessReport("Verify Field name "+fieldName+" in results list and delete it", "Field name "+fieldName+" is available in result list");
		}
		else
		{
			Reporters.failureReport("Verify Field name "+fieldName+" in results list and delete it", "Field name "+fieldName+" is not available in result list");
		}
		
		if(deleteField(fieldName))
		{
			Reporters.SuccessReport("Delete field "+fieldName+" from screenlayout", "Field "+fieldName+" deleted from screen layout");
		}
		else
		{
			Reporters.failureReport("Delete field "+fieldName+" from screenlayout", "Field "+fieldName+" not deleted from screen layout");
		}
		
		emtLogout=true;
	  }
	  /*
		 * Reading data from TestData.xls under TestData folder	
		 */
		@DataProvider
		public Object[][] getDataForSession() throws Exception {

			return Data_Provider.getTableArray("CreateAndDeleteNewField", "Key_Session");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
		}
}
