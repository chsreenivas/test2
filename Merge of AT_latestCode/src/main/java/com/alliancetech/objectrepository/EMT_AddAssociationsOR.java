package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_AddAssociationsOR extends Actiondriver{
	
	public static By btnSubmit=By.xpath("//input[@name='Submit']");
	
	public static By txtSuccessMessage=By.id("server-msg-10000");
	
	public static By lnkAddNewRecord=By.id("add-link");
	
	public static By txtEPC=By.xpath("//input[@class='input_text detail-input']");
	
	public static By lnkSearch=By.xpath("//span[text()='Search']");
	
	public static By txtEPCInSearchBox=By.id("search-terms");
	
	public static By btnSearch=By.id("search-submit");
	
	public static By getAssociation(String EPC)
	{
		return By.xpath("//div[@class='table-container']/table/tbody/tr/td[2]");
	}
	
	public static By tblAssociations=By.xpath("//div[@class='table-container']/table");
	
	public static By lnkDeleteThisAssociation=By.xpath("//span[text()='Delete This Association?']");
	
	public static By btnConfirm=By.xpath("//input[@value='Confirm']");
	
	public static By txtDeleteMessage=By.id("server-msg-5000");
	
}
