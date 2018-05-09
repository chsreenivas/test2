package com.cigniti.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.cigniti.automation.utilities.ExcelReader;



public class json_post {

	public static void main(String[] args) throws Throwable {
		
		ExcelReader reader = new ExcelReader("TestData\\TestData.xls", "POST", 2);
		
        URL url = new URL("http://qa4-nownext.ieventstest.com/restapi/registration");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String userCredentials = "devsupport@alliancetech.com:c0nfnav";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
        System.out.println(basicAuth);
        con.setRequestProperty ("Authorization", basicAuth);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        
       /* con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Tenant-Id", "oravivr1");
        con.setRequestProperty("Application-Key", "b328cd26-e47e-4d0b-a015-be01df926270");*/
        
        String data = reader.getColData("twitterAccount", 3);
        System.out.println(data);
        
        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
        wr.write(data);
        
        int responseCode = con.getResponseCode();
        System.out.println(responseCode);
        
        System.out.println(con.getContentType());
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
 
        JSONObject json = new JSONObject(new JSONTokener(response.toString()));
        
        if(json.get("status") == "")
        	System.out.println("Pass");
        else
        	System.out.println("Fail");
        	
        
        	
       /* JSONArray people = json.getJSONArray("");
        System.out.println(people.length());
        
        for(int i = 0 ; i < people.length() ; i++){
            JSONObject p = (JSONObject)people.get(i);
            String status = p.getString("status");
            System.out.println("status: " +status);
            
            System.out.println("--------------");
        }*/
        con.disconnect();
        //Session
        /* JsonObject industry = Json.createObjectBuilder().add("name","Industry").add("val", Industry).build();
		JsonObject track = Json.createObjectBuilder().add("name","Track").add("val", Track).build();
		
		JsonArray arr = Json.createArrayBuilder().add(industry).add(track).build();
		JsonObject arrlist = Json.createObjectBuilder().add("attrList", arr).build();
			
		JsonArray finalArray = Json.createArrayBuilder().add(arrlist).build();
		
		JsonObject finalObj = Json.createObjectBuilder().add("attrList", arr)
				.add("sessionNumber", SessionID)
				.add("sessionCode", sessionCode)
				.add("title", Title)
				.add("active", active)
				.add("date", date)
				.add("startTime", startTime)
				.add("endTime", endTime)
				.add("readerStartTime", readerStartTime)
				.add("readerEndTime", readerEndTime)
				.add("room", room)
				.add("status", status)
				.add("survey", survey)
				.build(); 
		JsonArray aa = Json.createArrayBuilder().add(finalObj).build();
		JsonObject aFinal=Json.createObjectBuilder().add("sessionList", aa).build();
				
				
		
		Map<String, Boolean> config = new HashMap<>();

		// Pretty printing feature is added.
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		// PrintWriter and JsonWriter is being created
		// in try-with-resources
		try (PrintWriter pw = new PrintWriter("TestData/API_SessionCreation.json");
				JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(pw)) {

		   // Json object is being sent into file system
		   jsonWriter.writeObject(aFinal);
		}
		
		 JsonReader reader = Json.createReader(new FileReader("TestData/API_SessionCreation.json"));
		 JsonObject obj = reader.readObject();
		  String JSON = obj.toString();
		  System.out.println(JSON);
		
		  URL url = new URL("http://qa4-automation.ieventstest.com/restapi/sessions");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        String userCredentials = "devsupport@alliancetech.com:c0nfnav";
	        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
	        System.out.println(basicAuth);
	        con.setRequestProperty ("Authorization", basicAuth);
	        con.setDoOutput(true);
	        con.setDoInput(true);
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        
	        OutputStream  wr= con.getOutputStream();
	       
	        wr.write(JSON.getBytes("UTF-8"));
	        
	        int responseCode = con.getResponseCode();
	        System.out.println(responseCode);
	        
	        
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	               response.append(inputLine);
	        }
	        in.close();

	        //print result
	        System.out.println(response.toString());
	 
	       JSONObject json = new JSONObject(new JSONTokener(response.toString()));
	       
	       JSONArray sessionResponseList = json.getJSONArray("sessionResponseList");
	       
	       for(int i = 0 ; i < sessionResponseList.length() ; i++){
	            JSONObject p = (JSONObject)sessionResponseList.get(i);
	           
	            String astatus = p.getString("status");
	            System.out.println("status " +astatus);
	        }
	        	
	        con.disconnect();*/
        //Registrant
        /*JsonObject attendeeStatus = Json.createObjectBuilder().add("name","Attendee Status").add("val", status).build();
		JsonObject attendeeType = Json.createObjectBuilder().add("name","Attendee Type").add("val", AttendeeType).build();
		
		JsonArray arr = Json.createArrayBuilder().add(attendeeStatus).add(attendeeType).build();
		JsonObject arrlist = Json.createObjectBuilder().add("attrList", arr).build();
			
		JsonArray finalArray = Json.createArrayBuilder().add(arrlist).build();
		
		JsonObject finalObj = Json.createObjectBuilder().add("attrList", arr)
				.add("num", attendeeID)
				.add("customerId", customerID)
				.add("first", first)
				.add("last", last)
				.add("lastModified", lastModified)
				.add("email", email)
				.add("optInAttendeeSearch", OptInAttendeeSrch)
				.add("loginId", LogInID)
				.add("password", Pswd)
				.build(); 
		JsonArray aa = Json.createArrayBuilder().add(finalObj).build();
		JsonObject aFinal=Json.createObjectBuilder().add("registrantList", aa).build();
				
				
		
		Map<String, Boolean> config = new HashMap<>();

		// Pretty printing feature is added.
		config.put(JsonGenerator.PRETTY_PRINTING, true);

		// PrintWriter and JsonWriter is being created
		// in try-with-resources
		try (PrintWriter pw = new PrintWriter("TestData/API_RegistrantCreation.json");
				JsonWriter jsonWriter = Json.createWriterFactory(config).createWriter(pw)) {

		   // Json object is being sent into file system
		   jsonWriter.writeObject(aFinal);
		}
		
		 JsonReader reader = Json.createReader(new FileReader("TestData/API_RegistrantCreation.json"));
		 JsonObject obj = reader.readObject();
		  String JSON = obj.toString();
		  System.out.println(JSON);
		
		  URL url = new URL("http://qa4-automation.ieventstest.com/restapi/registration");
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        String userCredentials = "devsupport@alliancetech.com:c0nfnav";
	        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
	        System.out.println(basicAuth);
	        con.setRequestProperty ("Authorization", basicAuth);
	        con.setDoOutput(true);
	        con.setDoInput(true);
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        
	        OutputStream  wr= con.getOutputStream();
	       
	        wr.write(JSON.getBytes("UTF-8"));
	        
	        int responseCode = con.getResponseCode();
	        System.out.println(responseCode);
	        
	        
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	               response.append(inputLine);
	        }
	        in.close();

	        //print result
	        System.out.println(response.toString());
	 
	       JSONObject json = new JSONObject(new JSONTokener(response.toString()));
	       
	       JSONArray registrantResponseList = json.getJSONArray("registrantResponseList");
	       
	       for(int i = 0 ; i < registrantResponseList.length() ; i++){
	            JSONObject p = (JSONObject)registrantResponseList.get(i);
	           
	            String astatus = p.getString("status");
	            System.out.println("status " +astatus);
	        }*/
	}
}
