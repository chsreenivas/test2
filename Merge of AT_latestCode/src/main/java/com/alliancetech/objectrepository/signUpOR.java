package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class signUpOR extends Actiondriver{
  
	public static By lnkSignUp=By.xpath("//a[text()='signup']");
	
	public static By txtManageSignupConfiguration=By.xpath("//h1[text()='Manage signup Configuration']");
	
	public static By txtEnableSignUp=By.xpath("//span[text()='enable-signup'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtColorButton=By.xpath("//span[text()='color-button'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtColorLink=By.xpath("//span[text()='color-link'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtEnableNeedHelp=By.xpath("//span[text()='enable-need-help'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtHeaderType=By.xpath("//span[text()='header-type'][1]/preceding-sibling::span[@class='whitespace'][1]");
	
	public static By txtNoSignUpForm=By.xpath("//div[@id='signup']/h3");
	
	public static By txtSignUpForm=By.xpath("//div[@id='signup']/form");
	
	public static By btnSubmit=By.xpath("//button[text()='Submit']");
	
	public static By lnkNeedHelp=By.xpath("//a[text()='Need Help?']");
	
	public static By imgLogo=By.xpath("//img[contains(@src,'logo.png')]");
	
	public static By imgBanner=By.xpath("//img[contains(@src,'banner.png')]");
	
	public static By txtEventName=By.xpath("//h2[text()='Event to be Moved']//following-sibling::h4");
	
	public static By btnBodyElement=By.xpath("//a[@title='body element']");
	
	public static By btnSaveHTMLFile=By.id("save-html-file");
	
	//public static By txtCheckinScreenLayoutPage=By.xpath("//h1[text()='Customize The Checkin Application']");
	
	public static By imgQRCode=By.xpath("//div[a[text()='Download Confirmation']]/preceding-sibling::div/img");
	
	public static By txtAttendeeEmailInConfirmationPage(String email)
	{
		return By.xpath("//strong[text()='"+email+"']");
	}
	
	public static By btnDownloadConfirmation=By.xpath("//a[text()='Download Confirmation']");
	
	public static By btnPrintConfirmation=By.xpath("//a[text()='Print Confirmation']");
	
	public static By chckboxOptInSMS=By.xpath("//td[div[text()='OptIn SMS']]/following-sibling::td/input[@checked='checked']");
	
	public static By txtBox(String label)
	{
		return By.xpath("//label[starts-with(text(),'"+label+"')]/following-sibling::div/input");
	}
	
	public static By AttendeeType=By.xpath("//input[@type='search']");
	
	public static By optionAttendeeType(String option)
	{
		return By.xpath("//span[@class='select2-results']/ul/li[text()='"+option+"']");
	}
	
	public static By txtArea(String label)
	{
		return By.xpath("//label[starts-with(text(),'"+label+"')]/following-sibling::div/textarea");
	}
	
	public static By ddLabel(String label)
	{
		return By.xpath("//label[starts-with(text(),'"+label+"')]/following-sibling::div/select");
	}
	
	public static By loading=By.xpath("//div[@class='wait']");
	
	public static By txtErrorMessageForRequiredFields=By.xpath("//div[text()='There are empty form fields that are required']");
	
	public static By chckBoxFieldToMakeOptional(String label)
	{
		return By.xpath("//li[text()='"+label+"']/div/input[@checked='checked']");
	}
	
	public static By verifyOptionalField(String field)
	{
		return By.xpath("//label[starts-with(text(),'"+field+"')]/following-sibling::div/input[not(contains(@class,'required'))]");
	}
}
