package com.alliancetech.objectrepository;

import org.openqa.selenium.By;


public class CheckInOR
{
	public static By txtEmail=By.id("email");
	public static By txtPwd=By.id("password");
	
	public static By ddPrinter=By.id("printers");
	
	public static By txtRegistrant=By.id("key");
	public static By btnNext=By.id("continue");
	
	//confirm page
	public static By txtLabel(String LabelName){
		return By.xpath("//label[contains(text(),'"+LabelName+"')]/following-sibling::input");
	}
	
	public static By btnConfirm = By.xpath("//input[@id='confirm']");
	
	
	//Manage Integration
	public static By lnkManageIntegration = By.xpath("//a[text()='Manage Integration']");
	public static By lnkRegistration=By.xpath("//tr[@id='RegistrationIntegrationProps']/td[text()='Registration']");
	public static By lnkCloseWindow = By.xpath("//a[span[text()='Close Window']]");
	public static By txtStartDate=By.id("integrationStartDate-modal");
	public static By txtEndDate=By.id("integrationEndDate-modal");
	public static By txtIntegrationUserName=By.id("integrationUserName-modal");
	public static By txtIntegrationPassword=By.id("integrationPassword-modal");
	public static By txtIntegrationInterval=By.id("integrationInterval-modal");
	public static By txtIntegrationEnabled=By.id("integrationEnabled-modal");
	public static By txtIntegrationCheckDiff=By.id("integrationCheckDiff-modal");
	public static By txtIntegrationURL=By.id("integrationUrl-modal");	
	public static By ddIntegrationModule=By.xpath("//select[@id='integrationModuleType-modal']");
	public static By btnIntegrationSubmit=By.xpath("(//input[@id='submit-integration'])[2]");
	
	public static By lnkCheckIn=By.xpath("//tr[@id='CheckinIntegrationProps']/td[text()='Check-in']");
	
	public static By lblEnabled = By.xpath("//tr[@id='RegistrationIntegrationProps']/td[3]");
	public static By lblcheckinEnabled = By.xpath("//tr[@id='CheckinIntegrationProps']/td[3]");
	
	//user login
	

	public static By txtEmailAddress=By.id("email");
	
	public static By txtPAssword=By.id("password");
	
	public static By btnSignIn=By.id("sign-in");
	
	public static By ddPrinters=By.id("printers");
	
	public static By btnChoosePrinter=By.id("choose-printer");
	
	public static By btnAddRegistrant=By.id("register");
	
	public static By btnTools=By.xpath("//form/div/a/span[text()='Tools']");
	
	public static By lnkLogout=By.xpath("//div[@id='tools-menu']/ul/li[text()='Logout']");
	
	public static By btnDone=By.id("done");

	public static By btnRebadge(String attendeeid)
	{
		return By.xpath("//td[text()='"+attendeeid+"']/preceding-sibling::td/a[text()='Rebadge']");
	}
	
	public static By btnLogout=By.id("logout");
	
	public static By txtBox(String LabelText){
		return By.xpath("//td/label[starts-with(text(),'"+LabelText+"')]/following-sibling::input");
	}
	
	public static By ddLabel(String LabelText){
		return By.xpath("//td/label[starts-with(text(),'"+LabelText+"')]/following-sibling::select[1]/option");
	}
	
	public static By ddValue(String LabelText){
		return By.xpath("//td/label[starts-with(text(),'"+LabelText+"')]/following-sibling::select[1]");
	}
	
	public static By ddLabelText(String LabelText){
		return By.xpath("//td[@class='first' or @class='middle']/label[starts-with(text(),'"+LabelText+"')]");
	}
	
	public static By btnRebadgeInPopUp=By.xpath("//input[@value='Rebadge']");

	public static By fetch = By.xpath("//span[text()='enable-registration-fetch-on-rnf']/preceding-sibling::span[@class='whitespace'][1]");
	public static By lnkCheckinXML = By.xpath("//a[text()='checkin']");
	
	public static By txtAttendeeEmailAddress=By.id("key");
	
	public static By txtVerifySecurityQuestion=By.xpath("//p[text()='Security verification question']");
	
	public static By txtErrorMessage=By.xpath("//div[@id='system-message' and @class='error']");
	
	public static By txtInvalidVerificationKey=By.xpath("//div[text()='Invalid Verification Key. Please try again or see a registration representative for assistance.']");
	
	public static By txtSecurityAnswer=By.xpath("//input[@id='key' and @type='text']");
	
	public static By txtMaximumAttemptsReached=By.xpath("//div[contains(text(),'Maximum verification attempts exceeded.')]");
	
	public static By txtCheckinVerAttemptsField=By.xpath("//td[div[text()='Check-in Ver. Attempts']]/following-sibling::td/div[contains(@id,'detail-text')]");
	
	public static By txtCheckinVerAttempts=By.xpath("//td[div[text()='Check-in Ver. Attempts']]/following-sibling::td/div[contains(@id,'detail-text')]/following-sibling::input[contains(@id,'detail-input')]");
	
	public static By btnCheckinVerAttemptsSave=By.xpath("//td[div[text()='Check-in Ver. Attempts']]/following-sibling::td/div[contains(@id,'detail-buttons')]/input[@value='Save']");
	
	public static By ddNotifications=By.xpath("//select[@id='view-by-select']/option[text()='Notifications']");
	
	public static By sectionNotifications=By.xpath("//li[contains(@id,'view_by')]/div/h3[text()='Notifications']");
	
	public static By lnkNotifications=By.xpath("//div[@id='detail-links']/ul/li/a[text()='Notifications']");
	
	public static By txtStatusInNotifications=By.xpath("//h2[text()='Notifications']/parent::div/following-sibling::div/div/table/tbody/tr/td[text()='Sent']");
	
	public static By txtNoRecordsToDisplay=By.xpath("//div[h2[text()='Notifications']]/following-sibling::div/div/table/tbody/tr/td[text()='No records to display.']");
}
