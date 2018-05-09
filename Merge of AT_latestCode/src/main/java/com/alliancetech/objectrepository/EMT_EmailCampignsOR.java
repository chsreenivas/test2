package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_EmailCampignsOR extends Actiondriver{
	
	public static By lnkViewMore=By.xpath("//a[text()='view more...']");
  
	public static By lnkEmailCampigns=By.xpath("//h2[text()='Email Campaigns']");
	
	public static By txtPageTitle=By.xpath("//h1[text()='Email Campaigns']");
	
	public static By lnkNewEmailCampign=By.xpath("//span[text()='Start a new Email Campaign?']");
	
	public static By ddCampignType=By.xpath("//select[@id='add-record']");
	
	public static By ddViewBy=By.xpath("//div[@class='container']/div[2]/select[@id='add-record']");
	
	public static By btnAdd=By.xpath("//input[@class='button-small']");
	
	public static By ddContent=By.xpath("//select[@id='email-content-id']");
	
	//public static By txtName=By.xpath("//input[@id='email-camp-name']");
	
	public static By chckboxOption=By.xpath("//table[@class='bulk-changes-table']/tbody/tr[3]/td/input");
	
	public static By btnNext=By.xpath("//input[@value='Next']");
	
	public static By btnConfirm=By.xpath("//input[@value='Confirm']");
	
	public static By txtTitle=By.xpath("//div[@class='icon-header']/h1");
	
	public static By btnRefresh=By.xpath("//input[@id='refresh']");
	
	public static By verifyEmailcampaign(String Name){
		
		return By.xpath("//tr[td[text()='"+Name+"']]");
		
	}
	
	public static By lnkManageContent=By.xpath("//a[contains(text(),'Manage Content')]");
	
	public static By txtManageEmailCampaignContent=By.xpath("//h1[text()='Manage Email Campaign Content']");
	
	public static By lnkAddNewContent=By.xpath("//span[@id='add-link']");
	
	public static By txtAddEmailContent=By.xpath("//h1[text()='Add Email Content']");
	
	public static By ddTemplate=By.xpath("//select[@id='template']");
	
	public static By ddType=By.xpath("//select[@id='type']");
	
	public static By txtName=By.xpath("//input[@name='email-content-name']");
	
	public static By txtReplyToEmail=By.xpath("//input[@id='sender']");
	
	public static By chckboxIsEnabled=By.xpath("//td[text()='Is Enabled?']/following-sibling::td/div[@id='email-campaign-enabled']/input[1]");
	
	//public static By chckboxIsEnabled=By.xpath("//div[@id='email-campaign-enabled']/input");
	
	public static By txtSubject=By.xpath("//input[@id='subject']");
	
	public static By txtMessage=By.xpath("//div[@id='server-msg-0']");
	
	public static By btnSubmit=By.xpath("//input[@name='submit-new-user']");
	
	public static By txtContentResultsInTable(String name)
	{
		return By.xpath("//table[@id='viewby-search-result']/tbody/tr/td[text()='"+name+"']");
	}
	
	public static By ddContentNameInContentDropDown(String name)
	{
		return By.xpath("//select[@id='email-content-id']/option[text()='"+name+"']");
	}
	
	public static By txtViewOrEditEmailCampaignContent=By.xpath("//h1[text()='View / Edit Email Campaign Content']");
	
	public static By txtEmailCampaignName=By.id("email-camp-name");
	
	public static By chckboxAttendeeID(String ID)
	{
		return By.xpath("//td[text()='"+ID+"']/preceding-sibling::td/input[@type='checkbox']");
	}
	
	public static By btnNextInEmailCampaign=By.xpath("//input[@name='Next']");
	
	//public static By btnEmailCampaignConfirm=By.xpath("//input[@name='Confirm']");
	
	public static By progressBar=By.xpath("//div[@class='ui-progressbar-value ui-widget-header ui-corner-left ui-corner-right']");
	
	public static By txtStatus(String name)
	{
		return By.xpath("//td[text()='"+name+"']/following-sibling::td[text()='Complete' or text()='Ready']");
	}
	
	public static By lnkTools=By.id("all-tools-btn");
	
	public static By lnkAllItemsInBulChanges=By.xpath("//h3[text()='Bulk Changes']/parent::div/following-sibling::ul/li[@id='all-items-btn']/a");
	
	public static By chckboxDeletePermanent=By.xpath("//td[text()='Delete (Permanent)']/preceding-sibling::td/input[@id='bulk-edit-operation']");
	
	public static By chckboxCheckAll=By.id("check-all");
	
	public static By btnNextInBulkOperations=By.xpath("//input[@name='Next']");
	
	public static By chckBoxRegistrants=By.id("registrants");
	
	public static By ddSelectOptions=By.xpath("//span[text()='Select options']");
	
	public static By ddAttendee= By.xpath("//span[text()='Attendee']/preceding-sibling::input");
	
	public static By ddEnrollee=By.xpath("//span[text()='Enrollee']/preceding-sibling::input");
	
	public static By ddRegistrantSelected=By.xpath("//button[@class='ui-multiselect ui-widget ui-state-default ui-corner-all ui-state-active']");
	
	
	
}
