package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_LockAndUnlockUserAccount extends Actiondriver{
  
	public static By txtErrorMessage=By.xpath("//h3[@id='error-message']");
	
	public static By lnkNumberOfRecords=By.id("records-to-display");
	
	public static By lnk500Records=By.xpath("//a[text()='Display 500 Records']");
	
	public static By viewUserDetails(String LoginID)
	{
		return By.xpath("//td[text()='"+LoginID+"']");
	}
	
	public static By true_Account_Locked=By.xpath("//tr/td[text()='Account Locked']/following-sibling::td/div[text()='true']");
	
	public static By false_Account_Locked=By.xpath("//tr/td[text()='Account Locked']/following-sibling::td/div[text()='false']");
	
	public static By txtFailedLoginCount=By.xpath("//td[text()='Failed Login Count']/following-sibling::td/div");
	
	public static By txtLockTime=By.xpath("//td[text()='Lock Time']/following-sibling::td/div");
	
	public static By chckAccountLocked=By.xpath("//input[@id='user-accountlocked-detail-input' and @type='checkbox']");
	
	public static By btnSave=By.xpath("//tr/td[text()='Account Locked']/following-sibling::td//div[@id='user-accountlocked-detail-buttons']/input[@value='Save']");
	
	public static By txtUserName=By.xpath("//span[@id='use-login-txt']");
	
	public static By txtAccountLocked=By.id("user-accountlocked-detail-td");
	
	public static By txtManageUsers=By.xpath("//h1[text()='Manage Users']");
	
	public static By txtMessage=By.id("server-msg-0");
}
