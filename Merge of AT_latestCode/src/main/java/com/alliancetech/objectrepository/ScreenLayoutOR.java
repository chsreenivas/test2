package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class ScreenLayoutOR extends Actiondriver{
  
	public static By lnkManageScreenLayouts=By.xpath("//div[h3[text()='Configure']]/following-sibling::ul/li/a[text()='Manage Screen Layout']");
    public static By ddTabFilter = By.id("tab-id-filter");
    public static By btnDetailLayout = By.xpath("//li[text()='Details']");
    public static By btnAddLayout = By.xpath("//li[text()='Add']");
    public static By lnkCheckIn=By.xpath("//a[text()='checkin']");
    public static By btnSetScreenLayout=By.xpath("//a[text()='Set Screen Layout']");
    public static By ddCheckInPageFilter=By.id("page-filter");
    public static By btnSaveScreenLayout=By.xpath("//input[@value='Save Screen Layout']");
    
    //additional information section
    public static By lnkEditButton = By.xpath("//div[h3[text()='Additional Information']]/preceding-sibling::div//a[@oldtitle='Edit this section']");
    public static By lnkCloseButton = By.xpath("//span[text()='Close Window']");
    public static By btnSaveChanges=By.xpath("(//input[@value='Save Section'])[2]");
    
    //categories section in Highlights tab
    public static By lnkEditButtonInCategories=By.xpath("//div[h3[text()='Categories']]/preceding-sibling::div//a[@oldtitle='Edit this section']");
	
    //Highlight Information
    public static By lnkEditButtonInHighlightInformation=By.xpath("//div[h3[text()='Highlight Information']]/preceding-sibling::div//a[@oldtitle='Edit this section']");
    
    //Reports Information in Reports tab
    public static By lnkEditButtonInReportsInformation=By.xpath("//div[h3[text()='Report Information']]/preceding-sibling::div//a[@oldtitle='Edit this section']");
}
