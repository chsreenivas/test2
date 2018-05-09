package com.alliancetech.emt;

import javax.json.JsonObject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.cigniti.automation.utilities.Data_Provider;

public class RESTAddSession extends BusinessFunctions{
  @Test(dataProvider = ("getData"))
  public void addSessionUsingREST(String Industry, String Track, String SessionID, String sessionCode, String Title, String active, String date, String startTime, String endTime, String readerStartTime
		  , String readerEndTime, String room, String status, String survey, String speaker,String URL) throws Throwable {
	 
	  JsonObject afinal= addSessionAttributesAndValues(Industry, Track, SessionID, sessionCode, Title, active, date, startTime, endTime, readerStartTime, readerEndTime, room, status, survey, speaker);
	  writeIntoJSONFileAndRead(afinal,"API_SessionCreation","sessions","sessionResponseList",URL);
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {

		return Data_Provider.getTableArray("RESTAddSessions", "Key_AddSessions");  //returning data from "EMTAddSession" sheet and "Key_AddSessions" as a reference to read data
	}
}
