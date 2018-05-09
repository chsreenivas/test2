package com.cigniti.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;



public class json_get {

	public static void main(String[] args) throws Throwable {
		
        URL url = new URL("http://qa4-nownext.ieventstest.com/restapi/sessions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String userCredentials = "devsupport@alliancetech.com:c0nfnav";
        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
        con.setRequestProperty ("Authorization", basicAuth);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
       /* con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Tenant-Id", "oravivr1");
        con.setRequestProperty("Application-Key", "b328cd26-e47e-4d0b-a015-be01df926270");*/
        
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
        	
        JSONArray people = json.getJSONArray("sessionList");
        System.out.println(people.length());
        
        for(int i = 0 ; i < people.length() ; i++){
            JSONObject p = (JSONObject)people.get(i);
            String id = p.getString("id");
            System.out.println("id: " +id);
            String title = p.getString("title");
            System.out.println("title " +title);
            String status = p.getString("status");
            System.out.println("status " +status);
            System.out.println("--------------");
        }
        con.disconnect();
	}
	
	
}
