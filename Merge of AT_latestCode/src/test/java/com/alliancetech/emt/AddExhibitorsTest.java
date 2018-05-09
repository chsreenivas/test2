package com.alliancetech.emt;
/**
 * This Test Case is used to add Exhibitors in EMT site
 * Reads Data from EMTAddExhibitor Sheet
 * reference Test rail-iConnect_EMT(Create Exhibitor)
 */

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddExhibitorsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddExhibitorsTest extends BusinessFunctions{
	public static int logIn=0;
	
  @Test(dataProvider=("getData"),groups= {"EMT", "RunAll"})
  public void EMT_AddExhibitors(String ExhibitName,String CustomerExhibitID,String status,String MsgExhibitEmail,String Company,String Booth,
		  				   String URL,String FacebookPage,String Description,String ContactFirst,String ContactCompany,String ContactLast,String Email,String Phone,
		  				   String AltPhone,String ContactAddress1,String City,String ContactAddress2,String Region,String ZipCode,String CountryCode,String LoginID,
		  				   String pswrd,String confirmpassword,String Category,String FocusArea,String Industry,String IndustryMarket,String JobRole,String SpecialityProduct,
		  				   String Education,String Certifications,String Division,String SalesRegion,String MapHeight,String MApRotation,String MapWidth,String MapX,String MapY,String image) throws Throwable {
	  
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
		
		waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");
		
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
		
		waitForVisibilityOfElement(EMT_AddExhibitorsOR.tblExhibitorResults, "Exhibitor results");
		
		if(verifyExhibitor(ExhibitName, Booth, Company))
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
		emtLogout=true;
  	}
  
  /*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTAddExhibitor", "Key_AddExhibitors");  //returning data from "EMTAddExhibitor" sheet and "Exhi" as a reference to read data
	}
	
	
}
