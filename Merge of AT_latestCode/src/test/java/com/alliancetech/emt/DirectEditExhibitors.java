package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class DirectEditExhibitors extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"},priority=0)
	public void CreateAExhibitor(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,
			String URL,String FacebookPage,String Description,String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,
			String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode,String LoginID,
			String pswrd,String confirmpassword,String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,
			String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY,String image) throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkExhibitors,"Exhibitors Tab")){
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}

		verifyText(EMT_AddExhibitorsOR.txtExhibitorTitle, "Exhibitors", "Exhibitors Page");

		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Record Link");

		js_TriggerOnClickEvent("addNewRecord();", "Add Button");

		if(AddExhibitorInformation(ExhibitName,CustomerExhibitID,status,MsgExhibitEmail,Company,Booth,URL,FacebookPage,Description))
		{
			Reporters.SuccessReport("Add Exhibitor information", ""+ExhibitName+" Exhibitor information got added");
		}
		else{
			Reporters.failureReport("Add Exhibitor information", ""+ExhibitName+" Exhibitor information is not added");
		}

		if(AddExhibitorContactInformation(ContactFirst, ContactCompany, ContactLast, Email, Phone, AltPhone, ContactAddress1, City, ContactAddress2, Region, ZipCode, CountryCode))
		{
			Reporters.SuccessReport("Add Exhibitor Contact information", ""+ExhibitName+" Exhibitor contact information got added");
		}
		else
		{
			Reporters.failureReport("Add Exhibitor contact information", ""+ExhibitName+" Exhibitor contact information is not added");
		}

		if(AddExhibitorLoginInformation(LoginID, pswrd, confirmpassword))
		{
			Reporters.SuccessReport("Add Exhibitor Login information", ""+ExhibitName+" Exhibitor Login information got added");
		}
		else
		{
			Reporters.failureReport("Add Exhibitor Login information", ""+ExhibitName+" Exhibitor login information is not added");
		}

		if(AddExhibitorCategories(Category, FocusArea, Industry, IndustryMarket, JobRole, SpecialityProduct, Education, Certifications, Division, SalesRegion, MapHeight, MApRotation, MapWidth, MapX, MapY))
		{
			Reporters.SuccessReport("Add Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details got added");
		}
		else
		{
			Reporters.failureReport("Add Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details are not added");
		}

		if(AddExhibitorLogo(image))
		{
			Reporters.SuccessReport("Add Exhibitor logo", ""+ExhibitName+" logo got added");
		}
		else
		{
			Reporters.failureReport("Add Exhibitor logo", ""+ExhibitName+" logo got added");
		}

		click(EMT_AddExhibitorsOR.btnSubmit,"Submit Button");

		if(exhibitorSuccessMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}

		getEMTURL();

		if(search(ExhibitName))
		{
			Reporters.SuccessReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is displayed");
		}
		else{
			Reporters.failureReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is not displayed");
		}
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Exhibitors"), "Loading");
		if(click(By.xpath("//tr/td[text()='"+ExhibitName+"']"),ExhibitName))
		{
			Reporters.SuccessReport("Verify Exhibitor", "Newly added Exhibitor "+ExhibitName+" is Displayed");

			verifyExhibitorInformation(ExhibitName, CustomerExhibitID, status, MsgExhibitEmail, Company, Booth, URL, FacebookPage, Description);

			verifyExhibitorContactInformation(ContactFirst, ContactCompany, ContactLast, Email, Phone, AltPhone, ContactAddress1, City, ContactAddress2, Region, ZipCode, CountryCode);

			verifyExhibitorLoginInformation(LoginID);

			verifyExhibitorCategories(Category, FocusArea, Industry, IndustryMarket, JobRole, SpecialityProduct, Education, Certifications, Division, SalesRegion, MapHeight, MApRotation, MapWidth, MapX, MapY);
		}
		else
		{
			Reporters.failureReport("Verify Exhibitor", "Newly added Exhibitor "+ExhibitName+" is not Displayed");
		}

		getEMTURL();
	}

	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_AddExhibitors");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}

	@Test(dataProvider = ("getEditData"),groups= {"EMT", "RunAll"},priority=1)
	public void EditExhibitor(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,
			String URL,String FacebookPage,String Description,String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,
			String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode,String LoginID,
			String pswrd,String confirmpassword,String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,
			String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY,String image) throws Throwable
			{
		if(clickTabFromViewMore(CommonOR.lnkExhibitors,"Exhibitors Tab")){
			Reporters.SuccessReport("Verify Page", "Exhibitors page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Exhibitors page is not displayed");
		}

		verifyText(EMT_AddExhibitorsOR.txtExhibitorTitle, "Exhibitors", "Exhibitors Page");

		if(search(ExhibitName))
		{
			Reporters.SuccessReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is displayed");
		}
		else{
			Reporters.failureReport("Search for newly added Exhibitor with exhibit name "+ExhibitName+"", "Registrant  is not displayed");
		}

		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Exhibitors"), "Loading");

		if(click(By.xpath("//tr/td[text()='"+ExhibitName+"']"),ExhibitName)){

			if(editExhibitorInformation(ExhibitName,CustomerExhibitID,status,MsgExhibitEmail,Company,Booth,URL,FacebookPage,Description))
			{
				Reporters.SuccessReport("Edit Exhibitor information", ""+ExhibitName+" Exhibitor information got edited");
			}
			else{
				Reporters.failureReport("Edit Exhibitor information", ""+ExhibitName+" Exhibitor information is not edited");
			}

			if(EditExhibitorContactInformation(ContactFirst, ContactCompany, ContactLast, Email, Phone, AltPhone, ContactAddress1, City, ContactAddress2, Region, ZipCode, CountryCode))
			{
				Reporters.SuccessReport("Edit Exhibitor Contact information", ""+ExhibitName+" Exhibitor contact information got edited");
			}
			else
			{
				Reporters.failureReport("Edit Exhibitor contact information", ""+ExhibitName+" Exhibitor contact information is not edited");
			}

			if(EditExhibitorLoginInformation(LoginID))
			{
				Reporters.SuccessReport("Edit Exhibitor Login information", ""+ExhibitName+" Exhibitor Login information got edited");
			}
			else
			{
				Reporters.failureReport("Edit Exhibitor Login information", ""+ExhibitName+" Exhibitor login information is not edited");
			}

			if(EditExhibitorCategories(Category, FocusArea, Industry, IndustryMarket, JobRole, SpecialityProduct, Education, Certifications, Division, SalesRegion, MapHeight, MApRotation, MapWidth, MapX, MapY))
			{
				Reporters.SuccessReport("Edit Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details got edited");
			}
			else
			{
				Reporters.failureReport("Edit Exhibitor Categories details", ""+ExhibitName+" Exhibitor Categories details are not edited");
			}

			refresh();

			verifyExhibitorInformation(ExhibitName, CustomerExhibitID, status, MsgExhibitEmail, Company, Booth, URL, FacebookPage, Description);

			verifyExhibitorContactInformation(ContactFirst, ContactCompany, ContactLast, Email, Phone, AltPhone, ContactAddress1, City, ContactAddress2, Region, ZipCode, CountryCode);

			verifyExhibitorLoginInformation(LoginID);

			verifyExhibitorCategories(Category, FocusArea, Industry, IndustryMarket, JobRole, SpecialityProduct, Education, Certifications, Division, SalesRegion, MapHeight, MApRotation, MapWidth, MapX, MapY);

			if(js_click(CommonOR.lnkDeleteThisRecord,"Delete This Registrant link")){
				
				waitForVisibilityOfElement(EMT_RegistrantsOR.btnConfirm, "Confirm button");
				Thread.sleep(2000);
				
				click(EMT_RegistrantsOR.btnConfirm,"Confirm button");
				
				emtLogOut();
				
				emtLogIn();
				
				clickTabFromViewMore(CommonOR.lnkExhibitors, "Exhibitors Tab");

				search(ExhibitName);

				if(verifyInTable("Exhibitors",ExhibitName))
				{
					Reporters.failureReport("Exhibitor verification after delete", ""+ExhibitName+" is still available in table");
				}
				else{
					Reporters.SuccessReport("Exhibitor verification after delete", ""+ExhibitName+" is not available in table,hence successfully deleted");
				}
			}

			getEMTURL();
			emtLogout=true;
		}
			}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getEditData() throws Exception {

		return Data_Provider.getTableArray("EMT_DirectEdit", "Key_EditExhibitors");  //returning data from "EMT_DirectEdit" sheet and "Key_EditRegistrant" as a reference to read data
	}
}
