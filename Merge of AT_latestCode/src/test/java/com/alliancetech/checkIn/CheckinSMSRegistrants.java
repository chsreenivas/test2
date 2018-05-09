package com.alliancetech.checkIn;

import javax.json.JsonObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;

public class CheckinSMSRegistrants extends BusinessFunctions{
  @Test(dataProvider = ("getData"))
  public void addRegistrantForCheckinSMS(String attendeeID,String first,String last,String status,String AttendeeType,String OptInAttendeeSrch,String lastModified,String email,String URL,String phone,String Company) throws Throwable {
  
	  JsonObject afinal= addRegistrantAttributesAndValues(attendeeID, first, last, status, AttendeeType, OptInAttendeeSrch, lastModified, email,phone,Company);
	  writeIntoJSONFileAndRead(afinal,"API_CheckinSMSRegistrant","registration",URL,"registrantResponseList");
  }
  @DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("CheckinSMSRESTRegistrant", "Key_AddRegistrants");  //returning data from "EMTAddSession" sheet and "Key_AddSessions" as a reference to read data
	}
}
