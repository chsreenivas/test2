package com.cigniti.test;

import com.cigniti.automation.accelerators.Actiondriver;

public class BF extends Actiondriver {
	public static boolean getEmtURL() {
		driver.get(configProps.getProperty("EMT_URL"));
		return true;
	}

	public static boolean emtLogin() throws Throwable {

		type(OR.txtUsername, configProps.getProperty("EMT_Username"),
				"User Name");
		type(OR.txtPassword, configProps.getProperty("EMT_Password"),
				"Pass word");
		click(OR.btnSignIn, "sigin");

		return true;

	}
	/*public static boolean linkAdmin() throws Throwable
	{
		click(OR.linkAdmin,"Admin");
		return true;
		
		
	}
	public static boolean linkAddUser() throws Throwable
	{
		click(OR.linkAddUser,"add new user");
		return true;
		
	}*/

	public static boolean linkViewMore() throws Throwable
	{
		js_click(OR.lickViewMOre,"Click on View MOre ....");
		return true;
		

	}
	public static boolean selectoption() throws Throwable
	{
		switchToFrameByIndex(0);
		click(OR.selectMenu, "select Admin");
		driver.switchTo().defaultContent();
		return true;
		
	}
	
	public static boolean addNewUser() throws Throwable
	{
		click(OR.addNewUser,"add new user");
		return true;
		
	}
	public static boolean enterTextData(String fn,String ln,String email,String login,String password) throws Throwable
	{
		type(OR.FirstName,fn , "first name");
		type(OR.FirstName,ln , "Last name");
		type(OR.FirstName,email , "email");
		type(OR.FirstName,login , "login id");
		type(OR.FirstName,password , "password");
		return true;
		
	}
	
}
