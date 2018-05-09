package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class CreateNewFieldUsingImportOR extends Actiondriver{
	
	public static By lnkManageField=By.xpath("//div[h3[text()='Configure']]/following-sibling::ul/li/a[text()='Manage Fields']");
	
	public static By txtManageFields=By.xpath("//h1[text()='Manage Fields']");
	
	public static By ddTabFilter=By.xpath("//select[@id='tab-id-filter']");
	
	public static By txtFieldNameInResultsTable(String fieldName)
	{
		return By.xpath("//table[@id='viewby-search-result']/tbody/tr/td[text()='"+fieldName+"']");
	}
	
	public static By btnDeleteForField(String fieldName)
	{
		return By.xpath("//table[@id='viewby-search-result']/tbody/tr/td[text()='"+fieldName+"']/preceding-sibling::td/div[@class='delete-row']");
	}
	
	public static By btnOkOfConfirmPopUp=By.xpath("//div[@id='popup_panel']/input[@id='popup_ok']");
	
	public static By lnkAddNewField=By.xpath("//div[@id='add-field-tab']");
	
	public static By modalPopUpAddNewField=By.xpath("//div[@id='modalBoxBody']");
	
	public static By txtLabel=By.xpath("//div[@id='modalBox']//input[@id='label']");
	
	public static By ddType=By.xpath("//div[@id='modalBox']//select[@id='type']");
	
	public static By txtUsage=By.xpath("//div[@id='modalBox']//input[@id='internal-name']");
	
	public static By txtFieldWidth=By.xpath("//div[@id='modalBox']//input[@id='width']");
	
	public static By btnAddField=By.xpath("//div[@id='modalBox']//input[@id='add-field']");
	
	public static By txtSuccessMessage=By.xpath("//div[text()='Your changes have been saved!']");
	
	public static By btnEdit=By.xpath("//div[h3[text()='Registrant Information']]/preceding-sibling::div//a[@oldtitle='Edit this section']");
	
	public static By btnViewByOptions=By.xpath("//a[@id='viewby-options-btn']");
	
	public static By lnkCreateView=By.xpath("//a[text()='Create View']");
	
	public static By txtCreateViewBy=By.xpath("//h1[text()='Create View By']");
	
	public static By txtViewByName=By.xpath("//input[@id='viewby-name']");
	
	public static By btnSave=By.xpath("//input[@value='Save']");
	
	public static By ddViewBy=By.xpath("//select[@id='selectList']");
	
	public static By txtDeleteFieldErrorMessageForViewBy=By.xpath("//div[text()='The field is being used by at least one View By']");
	
	public static By lnkRemoveViewBy=By.xpath("//a[text()='Remove']");
	
	public static By txtDeleteFieldErrorMessageForScreenLayout=By.xpath("//div[text()='The field is being used by at least one Application Screen Layout - C4P, Check-In, or Signup!']");

	
   
}
