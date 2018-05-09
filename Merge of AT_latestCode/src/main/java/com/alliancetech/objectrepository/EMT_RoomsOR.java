package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_RoomsOR extends Actiondriver{
	
	public static By lnkViewMore=By.xpath("//a[text()='view more...']");
  
	public static By tabRooms=By.xpath("//h2[text()='Rooms']");
	
	public static By txtRoomsTitle=By.xpath("//h1[text()='Rooms']");
	
	public static By lnkAddNewRecord=By.xpath("//span[text()='Add New Record?']");
	
	public static By btnAdd=By.xpath("//div[@id='add-button-container']/input[@type='button']");
	
	public static By txtName=By.xpath("//table[@class='details-table']/tbody/tr[2]/td[2]/input");
	
	public static By txtCapacity=By.xpath("//table[@class='details-table']/tbody/tr[2]/td[4]/input");
	
	public static By ddLocation=By.xpath("//table[@class='details-table']/tbody/tr[3]/td[4]/div[3]/select");
	
	public static By btnSubmit=By.xpath("//input[@type='submit']");
	
	public static By lnkSearch=By.xpath("//*[@id='search-button-magnify']/span");
	
	public static By txtSearch=By.xpath("//*[@id='search-terms']");
	
	public static By btnSearch=By.xpath("//*[@id='search-submit']");
	
	public static By lstRooms=By.xpath("//div[@class='container d1 border-top']/div/table");
	
	public static By lblAddNewRoom = By.xpath("//h1[text()='Add New Room']");
	
	public static By lnkDeleteThisRoom=By.xpath("//span[text()='Delete This Room?']");
	
	public static By txtDeleteMsg=By.id("server-msg-5000");
	
	public static By verifyRoom(String name,String capacity){
		
		return By.xpath("//tr[td[text()='"+name+"'] and td[text()='"+capacity+"']]");
	}
	
	public static By openRoom(String name)
	{
		return By.xpath("//table[@class='a2']/tbody/tr/td[text()='"+name+"']");
	}
}
