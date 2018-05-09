package com.alliancetech.emt;
/**
 * This Test Case is used to add Registrants in EMT site
 * Reads Data from EMTReg Sheet
 * reference Test rail-iConnect_EMT(Create Registrant)
 */


import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddRegistrantsTest extends BusinessFunctions{
	public static int logIn=0;

	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll","Survey"})
	public void EMT_AddRegistrants(String prefix,String attendee,String first,String CustomerRegistrantID,String last,
			String status,String AttendeeType,String SubAttendeeType,String WebURL,String Blog,String LinkedInURL,
			String FacebookURL,String TwitterAcnt,String IM,String AboutMe,String FullName,String BadgeFirstName,String Title,
			String Phone,String Mobile,String AltPhone,String Fax,String Company,String Address1,String Address2,String County,
			String Region,String City,String CountryCode,String Country,String ZipCode,String PersonalEmail,String Email,
			String OptInAttendeeSrch,String OptInTicklerMsgs,String OptInTwitterSesSelection,String OptInSessAttendance,String OptInRFIDAttendance,
			String LogInID,String Pswd,String ConfirmPassword,String image,String Industry,String Product,String JobRole,String Division,
			String Education,String Certifications,String CatRegion,String Market,String CompanySize,String annualSales,
			String RegPrimaryCode,String Demo1,String Demo2,String Demo3,String Demo4,String Demo5,String Demo6,String Demo7,
			String Demo8,String Demo9,String Demo10,String AccessFlag,String ExecSummitFlag,String HotelConfirmation,String HotelSelection,
			String MealTypeFlag,String NickName,String PromotionCode,String RevRecFlag,String StaffMealFlag,String AddInfo4,String AddInfo5,String AddInfo1,String AddInfo2,String AddInfo3,String PrintBadgeOnSubmit,String BadgePrinter) throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkRegistrants,"Registrants Tab")){
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		verifyText(EMT_RegistrantsOR.txtRegistrantaTitle, "Registrants", "Registrants Page");

		js_click(EMT_RegistrantsOR.lnkAddNewRecord, "Add New Recoed Link");

		js_TriggerOnClickEvent("addNewRecord();", "Add Button");
		
		waitForInVisibilityOfElement(By.xpath("//div[contains(@class,'ajax-loader')]"), "Loading");

		if(addRegistrantsInformation(prefix,attendee,first,CustomerRegistrantID,last,status,AttendeeType,SubAttendeeType))
		{
			Reporters.SuccessReport("Add Registrant details", ""+first+" Registrant details got added");
		}
		else{
			Reporters.failureReport("Add Registrant details", ""+first+" Registrant details is not added");
		}

		if(addRegistrantBio(WebURL, Blog, LinkedInURL, FacebookURL, TwitterAcnt,IM, AboutMe))
		{
			Reporters.SuccessReport("Add Registrant information", ""+first+" Registrant information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant information", ""+first+" Registrant information is not added");
		}

		if(addRegistrantContactInformation(FullName, BadgeFirstName, Title, Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County, Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email))
		{
			Reporters.SuccessReport("Add Registrant Contact information", ""+first+" Registrant Contact information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Contact information", ""+first+" Registrant Contact information is not added");
		}

		if(addRegistrantOptInInformation(OptInAttendeeSrch, OptInTicklerMsgs, OptInTwitterSesSelection, OptInSessAttendance, OptInRFIDAttendance))
		{
			Reporters.SuccessReport("Add Registrant Opt In information", ""+first+" Registrant Opt In information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Opt In information", ""+first+" Registrant Opt In information is not added");
		}

		if(addRegistrantLoginInformation(LogInID, Pswd, ConfirmPassword))
		{
			Reporters.SuccessReport("Add Registrant Login information", ""+first+" Registrant Login information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Login information", ""+first+" Registrant Login information is not added");
		}

		if(addRegistrantImage(image))
		{
			Reporters.SuccessReport("Add Registrant Image", ""+first+" Registrant Image is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Image", ""+first+" Registrant Image is not added");
		}

		if(addRegistrantCategories(Industry, Product, JobRole, Division, Education, Certifications, CatRegion, Market, CompanySize, annualSales, RegPrimaryCode))
		{
			Reporters.SuccessReport("Add Registrant categories", ""+first+" Registrant categories are added");
		}
		else
		{
			Reporters.failureReport("Add Registrant categories", ""+first+" Registrant categories are not added");
		}

		if(addRegistrantAdditionalInfo(Demo1, Demo2, Demo3, Demo4, Demo5, Demo6, Demo7, Demo8, Demo9, Demo10, AccessFlag, ExecSummitFlag, HotelConfirmation, HotelSelection,
				MealTypeFlag, NickName, PromotionCode, RevRecFlag, StaffMealFlag, AddInfo4, AddInfo5, AddInfo1, AddInfo2, AddInfo3))
		{
			Reporters.SuccessReport("Add Registrant additional information", ""+first+" Registrant additional information is added");
		}
		else
		{
			Reporters.failureReport("Add Registrant additional information", ""+first+" Registrant additional information is not added");
		}

		if(addRegistrantBadgePrinting(PrintBadgeOnSubmit, BadgePrinter))
		{
			Reporters.SuccessReport("Add Registrant Badge printing details", ""+first+" Registrant Badge printing details are added");
		}
		else
		{
			Reporters.failureReport("Add Registrant Badge printing details", ""+first+" Registrant Badge printing details are not added");
		}

		click(EMT_RegistrantsOR.btnSubmit,"Submit Button");

		if(successMessage())
		{
			Reporters.SuccessReport("Verify Success message", "Success Message is Displayed");
		}
		else
		{
			Reporters.failureReport("Verify Success message", "Success Message is not Displayed");
		}

		getEMTURL();

		/*if(search(attendee))
		{
			Reporters.SuccessReport("Search for newly added Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is displayed");
		}
		else{
			Reporters.failureReport("Search for newly added Registrant with attendee "+attendee+"", "Registrant with attendee "+attendee+" is not displayed");
		}

		waitForVisibilityOfElement(EMT_RegistrantsOR.tblResults, "Registrant results");

		if(clickRegistrant(attendee,first,last,Company))
		{
			if(verifyRegistrantInformation(prefix,attendee,first,CustomerRegistrantID,last,status,AttendeeType,SubAttendeeType))
			{
				Reporters.SuccessReport("Verification of Registrant information", "All the information entered for registrant is valid ");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant information", "Information entered for the registrant is incorrect");
			}
			if(verifyRegistrantBio(WebURL, Blog, LinkedInURL, FacebookURL, TwitterAcnt,IM, AboutMe))
			{
				Reporters.SuccessReport("Verification of Registrant Bio information", "All the bio information entered for registrant is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant Bio information", "Bio Information entered for the registrant is incorrect");
			}
			if(verifyRegistrantContactInformation(FullName, "", Title, Phone, Mobile, AltPhone, Fax, Company, Address1, Address2, County, Region, City, CountryCode, Country, ZipCode, PersonalEmail, Email))
			{
				Reporters.SuccessReport("Verification of Registrant Contact information", "All the contact information entered for registrant is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant Contact information", "Contact Information entered for the registrant is incorrect");
			}
			if(verifyRegistrantLoginInformation(LogInID, Pswd, ConfirmPassword))
			{
				Reporters.SuccessReport("Verification of Registrant Login information", "All the Login information entered for registrant is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant Login information", "Login Information entered for the registrant is incorrect");
			}
			if(verifyRegistrantCategories(Industry, Product, JobRole, Division, Education, Certifications, CatRegion, Market, CompanySize, annualSales, RegPrimaryCode))
			{
				Reporters.SuccessReport("Verification of Registrant Categories information", "All the Categories information entered for registrant is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant Categories information", "Categories Information entered for the registrant is incorrect");
			}
			if(verifyRegistrantAdditionalInfo(Demo1, Demo2, Demo3, Demo4, Demo5, Demo6, Demo7, Demo8, Demo9, Demo10,AccessFlag, ExecSummitFlag, HotelConfirmation, HotelSelection,
					MealTypeFlag, NickName, PromotionCode, RevRecFlag, StaffMealFlag, AddInfo4, AddInfo5, AddInfo1, AddInfo2, AddInfo3))
			{				Reporters.SuccessReport("Verification of Registrant Additional information", "All the Additional information entered for registrant is valid");
			}
			else
			{
				Reporters.failureReport("Verification of Registrant Additional information", "Additional Information entered for the registrant is incorrect");
			}
		}

		getEMTURL();*/
		emtLogout=true;

	}


	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("EMTReg", "Key_AddRegistrants");  //returning data from "EMTReg" sheet and "Key_AddRegistrants" as a reference to read data
	}


}
