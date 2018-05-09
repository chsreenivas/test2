package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class CalenderGraphOR extends Actiondriver
{
	public static By txtDetails=By.xpath("//div[@id='session-info']/h4[1]");
	
	public static By txtDescription=By.xpath("//div[2]/h4[2]");
	
	public static By txtSpeakers=By.xpath("//div[@class='border']/h3[1]");
	
	public static By btnRemoveFromAgenda=By.xpath("//div[@class='border first']/a[1]");
	
	public static By lnkEmailSessionDetail=By.id("email");
	
	public static By lnkPrintSessionDetail=By.id("print");
	
	public static By lnkDownloadSessionDetail=By.id("download");
	
	public static By btnClosePopUp=By.xpath("//*[@id='modal']/div[1]/div");
 
}
