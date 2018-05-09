package com.alliancetech.objectrepository;

import org.openqa.selenium.By;


public class IL_AddUserToEventOR {
	public static By txtSearch=By.xpath("//input[@id='searchusers']");
	  public static By btnSearch=By.xpath("//input[@id='searchusers']/following-sibling::input[@type='button']");
	  public static By btnAdd=By.xpath("//select[@id='addusers']/following-sibling::input[@value='Add']");
	  public static By ddAddUSer=By.xpath("//select[@id='addusers']/option");
	  public static By txtUserInTable(String username)
	  {
	 return By.xpath("//option[@selected]/parent::select[contains(@id,'accesslevel')]/parent::td/preceding-sibling::td[text()='"+username+"']");
	  }
	  public static By ddAccesslevel=By.xpath("//select[contains(@id,'accesslevel')]/option[@selected]");
	  public static By updateuser=By.xpath("//input[@value='Update']");
	  public static By VerifyEventUpdate=By.xpath("//td[@align='left']/p[@class='info']");
	  public static By txtEventUpdate=By.xpath("//p[text()='Event updated!']");
}
