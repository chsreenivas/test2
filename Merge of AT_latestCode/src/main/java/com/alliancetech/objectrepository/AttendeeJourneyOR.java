package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class AttendeeJourneyOR extends Actiondriver{
	
  public static By ddSelectAViewBy=By.xpath("//select[@id='view-by-select']");
  
  public static By ddLeadCaptures=By.xpath("//select[@id='view-by-select']/option[text()='Lead Captures']");
  
  public static By txtSuccessMessage=By.xpath("//div[@id='saved']");
  
  public static By sectionLeadCaptures=By.xpath("//ul[@id='view-bys']//li/div/h3[text()='Lead Captures']");
  
  public static By txtLead=By.xpath("//h1[text()='Lead']");
  
  public static By txtExhibitor=By.xpath("//h1[text()='Exhibitor']");
  
  public static By txtLeadCaptures=By.xpath("//h2[text()='Lead Captures']");
  
  public static By txtAttendeeID=By.xpath("//div[h2[text()='Lead Captures']]/following-sibling::div//div/table//tbody/tr/td");
  
  public static By txtUsername=By.xpath("//input[@type='email']");
  
  public static By txtPassword=By.xpath("//input[@type='password']");
  
  public static By btnSignIn=By.xpath("//div[@class='login']");
  
  public static By txtSearch=By.xpath("//div[@class='right']/input[@type='text']");
  
  public static By btnSearch=By.xpath("//div[@class='right']/span");
  
  public static By loading=By.xpath("//li[@class='loading']");
  
  public static By txtAttendeeName=By.xpath("//table[@id='info']/tbody/tr//td/h2");
  
  public static By btnSHowMoreDetail=By.xpath("//ul[@id='attendee']//li/div/span[text()='Show More Detail']");
  
  public static By btnEmailAttendee=By.xpath("//div[@id='connect']/a[text()='Email Attendee']");
  
  public static By sectionSessionAttendance=By.xpath("//ul[@id='attendance']/li/h1[text()='Session Attendance']");
  
  public static By sectionLeadScans=By.xpath("//ul[@id='lead-scans']/li/h1[text()='Lead Scans']");
  
  public static By lnkLogout=By.xpath("//a[text()='Logout']");
  
  public static By txtSessionTitle(String sessionTitle)
  {
	  return By.xpath("//ul[@id='attendance']//li[@class='expand']/ul/li/table/tbody/tr//td[text()='"+sessionTitle+"']");
  }
  
  public static By txtExhibitorName=By.xpath("//div[h2[text()='Lead Captures']]/following-sibling::div//div/table/tbody/tr/td[1]");
  
  public static By txtLeadScans=By.xpath("//ul[@id='lead-scans']//li[@class='expand']/ul/li/table/tbody/tr//td[2]");
  
  public static By imgAttendee(String attendeeid)
  {
	  return By.xpath("//img[contains(@src,'"+attendeeid+".jpg')]");
  }
  
  public static By lnkLeads=By.xpath("//ul[@id='all-tabs']/li/a[text()='Leads ']");
}
