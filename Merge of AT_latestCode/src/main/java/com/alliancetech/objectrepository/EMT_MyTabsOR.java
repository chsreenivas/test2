package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class EMT_MyTabsOR {
	
	public static By lnkViewMore=By.xpath("//a[text()='view more...']");
  
	public static By tabAdmin=By.xpath("//h2[text()='Administration']");
	
	public static By lnkMyTabs=By.xpath("//a[text()='Edit My Tabs']");
	
	public static By btnAdd=By.xpath("//input[@id='add-visisble-tab']");
	
	public static By btnRemove=By.xpath("//input[text()='remove-visisble-tab']");
	
	public static By btnSubmit=By.xpath("//input[@value='Submit']");
	
	public static By lstAvailableTabs=By.xpath("//select[@id='my-tabs-hidden']/option");
	
	public static By lstVisibleTabs=By.xpath("//select[@id='my-tabs-visible']/option");
	
	public static By tabName(String TabName){
		
		return By.xpath("//h2[text()='"+TabName+"']") ;
	}
}
