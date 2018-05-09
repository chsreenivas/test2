package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_LogInOR extends Actiondriver{
  
	public static By txtusername=By.xpath("//input[@id='login-email']");
	
	public static By txtPassword=By.id("login-password");
	
	public static By btnLogin=By.xpath("//button[@type='submit']");
	
	
}
