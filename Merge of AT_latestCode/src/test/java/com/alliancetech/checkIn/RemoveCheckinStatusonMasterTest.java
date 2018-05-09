package com.alliancetech.checkIn;
/**
 * This Test Case is used to add associations in EMT site
 * Reads Data from EMTAddAssociation Sheet
 * reference Test rail-iConnect_EMT(Create Associations)
 */

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class RemoveCheckinStatusonMasterTest extends BusinessFunctions{
	public static int logIn=0;
	
	@Test(dataProvider = ("getData"), groups={"checkin"})
	public void EMT_RemoveCheckinStatus(String Registrant) throws Throwable, IOException{

		try{
			if(logIn==0)
			{
				if(getURL(configProps.getProperty("MasterEMT_URL")))
				{
					Reporters.SuccessReport("Launch Master EMT Application "+configProps.getProperty("MasterEMT_URL"), "Application URL launched successfully");
				}
				else
				{
					Reporters.failureReport("Launch Master EMT Application "+configProps.getProperty("MasterEMT_URL"), "Application URL failed to launch ");
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

		clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab");

		search(Registrant);
		
		//waitForVisibilityOfElement(CommonOR.lblSearchData("Registrants"), "Registrants Data in Search Results");
		
		waitForVisibilityOfElement(By.xpath("//div[h2[text()='Registrants']]/following-sibling::div//tbody/tr/td"),"Reagistrant Search results");
		
		if(verifyInTable("Registrants",Registrant)){
			Reporters.SuccessReport("Verify Registrant on Search Results Page", Registrant+" is available on Search Results Page");			
			click(EMT_RegistrantsOR.openRegistrant(Registrant), Registrant);
			removeCheckinStatus();
		}
		else{
			Reporters.failureReport("Verify Registrant on Results Page",Registrant+" is not available on Search Results Page");
		}
		
		emtLogout=true;
	}
	
	
	/*
	 * Reading data from checkin.xls under TestData folder	
	 */
	
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("Checkin_Registrant", "Key_Registrant");  //returning data from "Registrant" sheet and "Key_Registrant" as a reference to read data
	}
	

}
