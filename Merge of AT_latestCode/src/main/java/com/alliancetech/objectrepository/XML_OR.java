package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class XML_OR {
  
	public static By lnkPortal=By.xpath("//a[text()='portal']");
	
	public static By txtAdminPageTitle=By.xpath("//h1[text()='Admin Section']");
	
	//public static By txtTagToRemove=By.xpath("//span[text()='activity-setting '][1]/following-sibling::span[contains(text(),'sessions')][1]");
	//public static By txtTagToRemove=By.xpath("//span[text()='activity-setting '][1]/following-sibling::span[contains(text(),'sessions')][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By btnSaveXMLFile=By.xpath("//input[@value='Save XML File']");
	
	public static By txtPortalWelcome=By.xpath("//p[@id='welcome-txt']/span");
	
	public static By txtSessionsInPortal=By.xpath("//h3[@class='title']/a[text()='Sessions']");
	
	public static By txtAdvanceSearch=By.id("attsrch-keyword-session");
	
	public static By btnSearch=By.xpath("//div[text()='Search']");
	
	public static By btnViewSessionDetails=By.xpath("//div[text()='View Session Details']");
	
	public static By btnCloseSessionPopUp=By.xpath("//div[@class='popup box-shadow']/div[@class='heading']/div[@class='close']");
	
	public static By txtOtherActions=By.xpath("//h3[text()='Other Actions']");
	
	public static By txtSessionDetailDisplaytext(String usage)
	{
		System.out.println("//span[contains(text(),'"+usage+"')]/preceding-sibling::span[text()='session-detail-setting '][1]/preceding-sibling::span[@class='whitespace'][1]");
		return By.xpath("//span[contains(text(),'"+usage+"')]/preceding-sibling::span[text()='session-detail-setting '][1]/preceding-sibling::span[@class='whitespace'][1]");
	}
	
	public static By txtVerifyXMLMessage=By.id("server-msg-0");
	
	public static By txtTagToRemove(String type)
	{
		
		return By.xpath("//span[contains(text(),'"+type+"')]/preceding-sibling::span[text()='activity-setting '][1]/preceding-sibling::span[@class='whitespace'][1]");
	}
	
	public static By txtTagAdvanceSearchSettings(String usage)
	{
		return By.xpath("//span[contains(text(),'"+usage+"')]/preceding-sibling::span[text()='advance-session-search-setting '][1]/preceding-sibling::span[@class='whitespace'][1]");
	}

	public static By lnkAdvanceSearch=By.xpath("//span[text()='Advanced Search']");
	
	public static By txtTagNavItem(String nav_val)
	{
		return 	By.xpath("//span[text()='left-nav-item '][1]/following-sibling::span[contains(text(),'"+nav_val+"')][1]/preceding-sibling::span[@class='whitespace'][1]");
	}
	
	public static By txtSessionDetailSettingVisible(String usage)
	{
		return By.xpath("//span[contains(text(),'"+usage+"')]/preceding-sibling::span[text()='session-detail-setting '][1]/preceding-sibling::span[@class='whitespace'][1]");
		
	}
	
	public static By txtSurveyTagToRemove(String type)
	{
		return By.xpath("//span[text()='default-session-survey-type'][1]/preceding-sibling::span[@class='whitespace'][1]");
		//return By.xpath("//span[@class='xml-text' and text()='attendance' or text()='enrollment']");
		
	}
	
	public static By lnkSurvey=By.xpath("//a[text()='survey']");
	
	public static By txtSurveyXMLPageTitle=By.xpath("//h1[text()='Manage survey Configuration']");
	
	public static By tagsurveys_search_setting=By.xpath("//span[text()='surveyid'][2]/following-sibling::span[@class='xml-attribute']");
	
	public static By tagShowLogoutButtonNonAdmin=By.xpath("//span[text()='show-logout-button-non-admin'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By tagShowAddRegistrantAdmin=By.xpath("//span[text()='show-add-registrant-admin'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtVerificationEnabledTag= By.xpath("//span[text()='verification-enabled'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtVerificationAttemptsTag=By.xpath("//span[text()='verification-attempts'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtVerificationFieldNameTag=By.xpath("//span[text()='verification-field-name'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By lnkCheckin=By.xpath("//a[text()='checkin']");
	
	public static By txtManageCheckinConfiguration=By.xpath("//h1[text()='Manage checkin Configuration']");
	
	public static By txtNotification=By.xpath("//span[text()='notification'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtShowLogoutButtonForNonAdmin=By.xpath("//span[text()='show-logout-button-non-admin'][1]/preceding-sibling::span[@class='whitespace'][1]");
}
