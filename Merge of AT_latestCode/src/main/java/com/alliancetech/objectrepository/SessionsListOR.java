package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class SessionsListOR extends Actiondriver{
  
	public static By txtNRecords=By.xpath("//div[@class='paging-left']/div[@class='item-num']/label[@id='item-to-head']");
	
	public static By lnkPrevious=By.xpath("//*[@id='previous-head']");
	
	public static By lnkNext=By.xpath("//*[@id='next-head']");
	
	public static By txtPageOption=By.xpath("//div[@class='paging-right']");
	
	public static By lstSessionsDetails=By.xpath("//div[@id='content']/div[2]");
	
	public static By ddRecords=By.xpath("//*[@id='item-per-page-header']");
	
	public static By lstSessions=By.xpath("//div[@id='sess-results']");
	
	public static By nRecords=By.xpath("//*[@id='rpp-head-20']");
	
	public static By valuePageNumber=By.id("page-number-header-txt");
	
	
}
