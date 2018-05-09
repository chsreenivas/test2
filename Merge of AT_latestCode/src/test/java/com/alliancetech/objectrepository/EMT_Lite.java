package com.alliancetech.objectrepository;

import org.openqa.selenium.By;


import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_Lite extends Actiondriver{
// public static By Add_Reg_Button=By.xpath("//a[contains(text(),'Ädd Attendee')]");
	public static By Add_Reg_Button=By.xpath("//a[@href='?nurl=emt-lite/attendee/details&type=add']");
 public static By LoginID=By.id("email");
 public static By Password=By.id("password-login");
 public static By LoginButton=By.id("login-btn");
 public static By SubmitButton=By.xpath("//button[@type='submit']");
 public static By DropDown=By.xpath("//a[@class='dropdown-toggle']");
 public static By Logout=By.xpath("//a[@href='?action=logout']");
  public static By f(String a) {
	  return By.xpath("//label[contains(text(),'"+a+"')]/following-sibling::input");
  }
  public static By SessionTab=By.xpath("//a[@class='sessions']");
public static By Add_Ses_Button=By.xpath("//a[@href='?nurl=emt-lite/session/details&type=add']");
public static By Add_Detail=By.xpath("//td[@class='cke_show_borders']");



  
}
