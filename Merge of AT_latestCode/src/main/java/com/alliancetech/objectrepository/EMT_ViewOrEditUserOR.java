package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class EMT_ViewOrEditUserOR {
  
	public static By lnkManageUsers=By.xpath("//a[text()='Manage Users']");
	
	public static By txtManageUsersTitle=By.xpath("//h1[text()='Manage Users']");
	
	public static By lnkUserFromTable=By.xpath("//table[@id='viewby-search-result']/tbody/tr");
	
	public static By lnkUserProfile=By.xpath("//table[@id='viewby-search-result']/tbody/tr/td[5]/ul/li/a"); 
	
	public static By txtProfileType=By.xpath("//input[@checked='checked']/following-sibling::label");
	
	public static By txtUserLoginId=By.xpath("//div[@id='user-loginid-detail-text']");
	
	public static By btnNext=By.xpath("//div[text()='Next']");
	
	public static By ddProfile=By.xpath("//select[@id='profile-id-filter']");
	
	public static By btnFilter=By.id("apply-filter");
	
	public static By txtMessage=By.xpath("//div[@id='non-editable-message']");
	
	public static By User(String userName)
	{
		return By.xpath("//tbody/tr/td[text()='"+userName+"']");
	}
	
	public static By systemAdmin()
	{
		return By.xpath("//a[text()='System Administrator']/ancestor::tr/td[1]");
	}
}
