package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class IL_ImportExhibitorFile extends Actiondriver{
  
	public static By tabImports=By.xpath("//span[text()='Imports']");
	
	public static By btnExhibitors=By.xpath("//div[@id='navAdmin']/ul/li/a[text()='Exhibitor']");
	
	public static By btnUpload=By.xpath("//input[@id='submitID']");
	
	public static By chckExhibitorFile(String file)
	{
		return By.xpath("//td[starts-with(text(),'"+file+"')]/preceding-sibling::td/input[@type='radio']");
	}
	
	public static By btnContinue=By.xpath("//input[@name='continue']");
	
	public static By txtImportExhibitor=By.xpath("//fieldset/legend[text()='Import Exhibitor  (Create Events)']");
	
	public static By btnImport=By.xpath("//input[@id='import']");
	
	public static By btnDone=By.xpath("//input[@value='Done']");
	
	public static By txtResults=By.xpath("//span[@id='results']");
	
	public static By txtMapFieldsPage=By.xpath("//form[@id='fields']");
	
	//Browse button
	public static By btnBrowse=By.xpath("//input[@id='txtFile']");
}
