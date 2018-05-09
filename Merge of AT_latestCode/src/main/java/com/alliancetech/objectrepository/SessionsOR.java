package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class SessionsOR extends Actiondriver {
	
	public static By lnkSessoinsicon=By.xpath("//h3/a[text()='Sessions']");
 
	public static By txtSelectAreaPopUp=By.xpath("//*[@id='session-search-pop-filter']/div[1]/div[1]");
	
	public static By txtLeftArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div/h4");
	
	public static By txtRightArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div[2]/h4");
	
	public static By txtLeftSubArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div");
	
	public static By txtRightSubArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div[2]");
	
	public static By chckDontShowThisMessage=By.xpath("//*[@id='session-search-pop-filter']/div[6]/input");
	
	public static By btnContinueWithoutSelection=By.xpath("//*[@id='session-search-pop-filter']/input");
	
	public static By txtSessions=By.xpath("//*[@id='session-search']/div[1]/div/ul[1]/li[1]/a/span");
	
	public static By txtDropInLabs=By.xpath("//*[@id='session-search']/div[1]/div/ul[1]/li[2]/a/span");
	
	public static By txtAdvancedSearch=By.xpath("//*[@id='session-search']/div[1]/div/ul[1]/li[3]/a/span");
	
	public static By txtYouveSelected=By.xpath("//*[@id='session-search']/div[1]/div/div[2]");
	
	public static By txtNarrowYourResults=By.xpath("//*[@id='session-search']/div[1]/div/div[4]");
	
	public static By listSessions=By.xpath("//table[@class='session-table']");
	
	public static By lnkSessionsTab=By.xpath("//*[@id='main-nav']/li[7]/a");
	
	public static By selectSubArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div[1]/div[1]/a");
	
	public static By txtSubArea=By.xpath("//*[@id='you-selected']/li/a");
	
	public static By lnkClearAll=By.xpath("//*[@id='you-selected-clear-all']");
	
	public static By btnCalenderView=By.xpath("//*[@id='calendar-view']");
	
	//public static By txtSubArea=By.xpath("//*[@id='session-search-pop-filter']/div[3]/div[1]/div[1]/a");
	
}
