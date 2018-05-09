package com.alliancetech.emt;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.BadgePrintOR;
import com.alliancetech.objectrepository.CommonOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class BadgePrinting extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void printPDF(String Attendeeid) throws Throwable {
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

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		if(navigateToBadgePrinter())
		{
			Reporters.SuccessReport("Navigate to Badge printer Page", "Successfully navigated to Badge Printer Page");
		}
		else
		{
			Reporters.failureReport("Navigate to Badge printer Page", "Failed to navigate to Badge Printer page");
		}

		if(enablePDFPrint())
		{
			Reporters.SuccessReport("Enable PDF Print check box", "Enabled PDF Print check box");
		}
		else
		{
			Reporters.failureReport("Enable PDF Print check box", "Failed to enable PDF Print check box");
		}

		click(BadgePrintOR.btnSubmit,"Submit Button");

		waitForElementPresent(BadgePrintOR.txtBadgePrintSettingSuccessMsg);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		if(navigateToBadgePrintingForRegistrants())
		{
			Reporters.SuccessReport("Navigate To Badge Printing for Registrants Page", "Successfully navigated to Badge Printing for Registrants Page");
		}
		else
		{
			Reporters.failureReport("Navigate To Badge Printing for Registrants Page", "Failed to navigate to Badge Printing for Registrants Page");
		}
		
		if(verifyInDropDownList(BadgePrintOR.ddPrinterOption, "Badge Print PDF"))
		{
			Reporters.SuccessReport("Verify Badge Print PDF in Printer drop down", "Badge Print PDF option is availble in Printer drop down");
		}
		else
		{
			Reporters.failureReport("Verify Badge Print PDF in Printer drop down", "Badge Print PDF option is not availble in Printer drop down");
		}
		
		if(printPDFBadgesInBulk())
		{
			Reporters.SuccessReport("Print PDF Badges in Bulk", "Successfully printd PDF Badges in Bulk");
			if(verifyFileType("pdf"))
			{
				Reporters.SuccessReport("Verify whether pdf file got downloaded or not", "Valid file type has been downloaded");
			}
			else
			{
				Reporters.failureReport("Verify whether pdf file got downloaded or not", "Invalid file type has been downloaded");
			}
		}
		else
		{
			Reporters.failureReport("Print PDF Badges in Bulk", "Failed to Print PDF Badges in Bulk");
		}

		if(search(Attendeeid))
		{
			Reporters.SuccessReport("Search for Registrant with id "+Attendeeid, "Searched for registrant with id "+Attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for Registrant with id "+Attendeeid, "Failed to search for registrant with id "+Attendeeid);
		}
		
		waitForInVisibilityOfElement(CommonOR.loadingBySectionName("Registrants"), "Loading");

		if(click(By.xpath("//tr/td[text()='"+Attendeeid+"']"),Attendeeid))
		{
			if(verifyBadgePDFPrintForSingleRegistrant())
			{
				Reporters.SuccessReport("Verify Badge PDF Print for Registrant with id "+Attendeeid, "Badge PDF Print is enabled for single registrant");
			}
			else
			{
				Reporters.failureReport("Verify Badge PDF Print for Registrant with id "+Attendeeid, "Badge PDF Print is not enabled for single registrant");
			}
			
			if(printPDFBadgeForOneRegistrant())
			{
				Reporters.SuccessReport("Print PDF Badge for registrant with id "+Attendeeid, "Successfully printed PDF Badge");
				if(verifyFileType("pdf"))
				{
					Reporters.SuccessReport("Verify whether pdf file got downloaded or not", "Valid file type has been downloaded");
				}
				else
				{
					Reporters.failureReport("Verify whether pdf file got downloaded or not", "Invalid file type has been downloaded");
				}
			}
			else
			{
				Reporters.failureReport("Print PDF Badge for registrant with id "+Attendeeid, "Failed to print PDF Badge");
			}
		}

		if(clickTabFromViewMore(CommonOR.lnkAdmin, "Administrations Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Administration page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Administration page is not displayed");
		}

		if(navigateToBadgePrinter())
		{
			Reporters.SuccessReport("Navigate to Badge printer Page", "Successfully navigated to Badge Printer Page");
		}
		else
		{
			Reporters.failureReport("Navigate to Badge printer Page", "Failed to navigate to Badge Printer page");
		}

		if(disablePDFPrint())
		{
			Reporters.SuccessReport("Disable PDF Print check box", "Disabled PDF Print check box");
		}
		else
		{
			Reporters.failureReport("Disable PDF Print check box", "Failed to disable PDF Print check box");
		}

		click(BadgePrintOR.btnSubmit,"Submit Button");

		waitForElementPresent(BadgePrintOR.txtBadgePrintSettingSuccessMsg);

		if(clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab"))
		{
			Reporters.SuccessReport("Verify Page", "Registrants page is displayed");
		}
		else
		{
			Reporters.failureReport("Verify Page", "Registrants page is not displayed");
		}

		if(navigateToBadgePrintingForRegistrants())
		{
			Reporters.SuccessReport("Verify Badge PDF Print in Registrants", "PDF Print option is disabled");
		}
		else
		{
			Reporters.failureReport("Verify Badge PDF Print in Registrants", "PDF Print option is not disabled");
		}
		
		if(!verifyInDropDownList(BadgePrintOR.ddPrinterOption, "Badge Print PDF"))
		{
			Reporters.SuccessReport("Verify Badge Print PDF in Printer drop down", "Badge Print PDF option is not availble in Printer drop down");
		}
		else
		{
			Reporters.failureReport("Verify Badge Print PDF in Printer drop down", "Badge Print PDF option is availble in Printer drop down");
		}
		
		if(search(Attendeeid))
		{
			Reporters.SuccessReport("Search for Registrant with id "+Attendeeid, "Searched for registrant with id "+Attendeeid);
		}
		else
		{
			Reporters.failureReport("Search for Registrant with id "+Attendeeid, "Failed to search for registrant with id "+Attendeeid);
		}

		if(click(By.xpath("//tr/td[text()='"+Attendeeid+"']"),Attendeeid))
		{
			if(!verifyBadgePDFPrintForSingleRegistrant())
			{
				Reporters.SuccessReport("Verify Badge PDF Print for Registrant with id "+Attendeeid, "Badge PDF Print is disabled for single registrant");
			}
			else
			{
				Reporters.failureReport("Verify Badge PDF Print for Registrant with id "+Attendeeid, "Badge PDF Print is not disabled for single registrant");
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
		return Data_Provider.getTableArray("EMTReg", "Key_PrintPDFBadgeForSingleReg");  //returning data from "EMTReg" sheet and "Key_PrintPDFBadgeForSingleReg" as a reference to read data
	}


}
