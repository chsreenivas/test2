package com.alliancetech.emt;
/**
 * This Test Case is used to add associations in EMT site
 * Reads Data from EMTAddAssociation Sheet
 * reference Test rail-iConnect_EMT(Create Associations)
 */


import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.CommonOR;
import com.alliancetech.objectrepository.EMT_AddAssociationsOR;
import com.alliancetech.objectrepository.EMT_RegistrantsOR;
import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class AddAssociationsTest extends BusinessFunctions{
	public static int logIn=0;
	@Test(dataProvider = ("getData"),groups= {"EMT", "RunAll"})
	public void EMT_AddAssociations(String EPC,String attendee,String AssociationStation) throws Throwable, IOException{

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

		clickTabFromViewMore(CommonOR.lnkAssociations, "Associations Tab");

		js_click(EMT_AddAssociationsOR.lnkAddNewRecord, "Add New Record Link");

		js_TriggerOnClickEvent("addNewRecord();","Add Button");

		addAssociationInformation(EPC,attendee,AssociationStation);

		click(EMT_AddAssociationsOR.btnSubmit,"Submit");

		waitForElementPresent(EMT_AddAssociationsOR.txtSuccessMessage);
		
		getEMTURL();

		clickTabFromViewMore(CommonOR.lnkRegistrants, "Registrants Tab");

		search(attendee);

		waitForVisibilityOfElement(EMT_RegistrantsOR.tblResults, "Registrant results");

		click(EMT_RegistrantsOR.openRegistrant(attendee),"Registrant");

		if(verifyAssociationInManageRFIDAssociation(EPC))
		{
			Reporters.SuccessReport("Verify Association with EPC "+EPC+" in Registrant "+attendee, "Association got added to Registrant");
		}
		
		getEMTURL();
		emtLogout=true;
	}
	/*
	 * Reading data from TestData.xls under TestData folder	
	 */
	@DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("EMTAddAssociation", "Key_AddAssociations");  //returning data from "EMTAddAssociation" sheet and "Key_AddAssociations" as a reference to read data
	}
	
	
}
