package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_AddExhibitorsOR extends Actiondriver{
	
	public static By txtExhibitorTitle=By.xpath("//h1[text()='Exhibitors']");
	
	public static By txtSuccessMessage=By.id("server-msg-10000");
	
	public static By tblExhibitorResults=By.xpath("//table[@id='viewby-search-result']/tbody/tr");
	
	public static By txtConfirmPassword=By.xpath("//div[text()='Confirm Password']/parent::td/following-sibling::td/input[2]");
	
	public static By btnSubmit=By.xpath("//div[@class='container']/div/input[@value='Submit']");
	
	public static By verifyExhibitor(String exhibitName,String Booth,String company){
		return By.xpath("//tr[td[text()='"+exhibitName+"'] and td[text()='"+Booth+"'] and td[text()='"+company+"'] ]");
	}

}
