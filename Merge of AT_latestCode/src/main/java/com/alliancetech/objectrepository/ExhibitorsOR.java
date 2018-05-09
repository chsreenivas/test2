package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class ExhibitorsOR  {
	public static By ExhiLogin=By.xpath("//td/input[@name='login']");

	public static By Leadimage=By.xpath("//td[@align='center']/img[@title='Read Only Access']");

	public static By logoutExhi=By.xpath("//a[@title='Logout']/img[@src='/images/logout.png']");
	 
	public static By editevent=By.xpath("//div[@id='navAdmin']/ul/li/a[text()='Edit']");
	 
	public static By ddAcessLevel=By.xpath("//td/select[@name='accesslevel']");
	 
	public static By Fullaccess=By.xpath("//td[@class='data_table_data_even']/select[@name='accesslevel']/option[@value='Full']");
	 
	public static By Leadformimage=By.xpath("//a[@title='Enter Manual Lead(s)']/img[@src='/images/manual_lead_form.png']");
	 
	public static By clearBtn=By.xpath("//a[@href='?page=clearEvent']");
	 
	public static By lnkEventName(String eventName)
	{
	return By.xpath("//td/a[text()='"+eventName+"']");
	}
	
}
