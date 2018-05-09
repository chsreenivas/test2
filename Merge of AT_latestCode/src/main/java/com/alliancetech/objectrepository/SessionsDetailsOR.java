package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class SessionsDetailsOR extends Actiondriver  {
	
	public static By lnkSessoinsicon=By.xpath("//*[@id='content']/div/dl[2]/dd[1]/div/a");
	public static By btnSessoinsContinue=By.xpath("//*[@id='session-search-pop-filter']/input");
	public static By tblSessionsResults=By.id("sess-results");
	//public static By btnViewSessionsDetails=By.xpath("//*[@id='session-search']/div[5]/div[1]/table/tbody/tr/td[2]/div[1]");
	public static By btnViewSessionsDetails=By.xpath("//div[@id='sess-results']/div/table/tbody/tr/td[2]/div");
	public static By btnAddToAgenda=By.xpath("//div[@id='sess-results']/div[1]//a[text()='Add To Agenda']");
	public static By txtStatusConfirmed=By.xpath("//*[@id='20131219054552475037000000']/table/tbody/tr/td[2]/div[4]");
	public static By txtSessionAddedToAgenda=By.xpath("//*[@id='20131219054552475037000000']/div");
	public static By btnRemoveFromAgenda=By.xpath("//div[@id='sess-results']/div[1]//a[text()='Remove From Agenda']");
	public static By txtSessionRemoveFromAgenda=By.xpath("//div[@id='sess-results']/div/div");
	public static By txtSessionsDetailsPopup=By.xpath("//*[@id='modal']/div[1]/h2");
	
}
