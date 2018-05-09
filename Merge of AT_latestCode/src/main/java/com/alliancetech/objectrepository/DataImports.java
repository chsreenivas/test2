package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class DataImports extends Actiondriver
{
	//public static By lnkImportRegistrantsData=By.xpath("//a[text()='Import Registrants Data']");
	public static By lnkImportRegistrantsData=By.xpath("//a[normalize-space(text())='Registrants Data']");
	public static By lnkImportRoomsData=By.xpath("//a[normalize-space(text())='Rooms Data']");
	public static By lnkImportSpeakersData=By.xpath("//a[normalize-space(text())='Speakers Data']");
	public static By lnkImportAssociationsData=By.xpath("//a[normalize-space(text())='Associations Data']");
	public static By lnkImportExhibitorsData=By.xpath("//a[normalize-space(text())='Exhibitors Data']");
	public static By lnkImportSessionsData=By.xpath("//a[normalize-space(text())='Sessions Data']");
	public static By lnkImportLeadsData=By.xpath("//a[normalize-space(text())='Leads Data']");
	public static By btnUpload = By.xpath("//input[@value='Upload']");
	
	public static By btnOK_confirmPopUp=By.id("popup_ok");
	public static By btnNext=By.xpath("//br[@class='clear-fix']/preceding-sibling::div/input[@id='next-btn']");
	public static By checkbox=By.xpath("//input[@type='checkbox']");
	public static By btnNext_DataValidation=By.xpath("//div[input[@value='Re-Validate']]/following-sibling::div//input[@id='next-btn']");
	
	
	
		
	//upload file
	public static By txtFile=By.name("file");
	
}
