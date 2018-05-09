package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class CalenderViewOR extends Actiondriver{
	
	public static By txtOption=By.xpath("//*[@id='sub-narrow-3-0']/li/a");
	public static By btnCalenderview=By.xpath("//*[@id='calendar-view']");
	public static By lnkBackToListView=By.xpath("//*[@id='wrapper-header']/div[3]/div[1]/a");
	public static By btnListView=By.xpath("//*[@id='list-view']");
	public static By lstCalenderGraph=By.xpath("//*[@id='wrapper-header']");
	public static By lnkFilterLink=By.xpath("//*[@id='wrapper-header']/div[3]/ul");
	public static By tabSessions=By.xpath("//*[@id='session-search']/div/div/ul/li/a");
	public static By lnkFilterSession=By.xpath("//*[@id='wrapper-header']/div[3]/ul/li[2]/a");
	public static By txtFilterDay=By.xpath("//div[@id='content']/div[2]/table/thead[1]/tr/th[1]");
	public static By txtSession=By.xpath("//table/tbody/tr/td[2]/div/div[2]/div[1]");
	public static By txtSessionDetailsPopUpTitle=By.xpath("//*[@id='modal']/div[1]/h2");
	public static By txtSessionTitle=By.xpath("//*[@id='session-info']/h3");
	public static By btnClosePopUp=By.xpath("//*[@id='modal']/div[1]/div");
}
