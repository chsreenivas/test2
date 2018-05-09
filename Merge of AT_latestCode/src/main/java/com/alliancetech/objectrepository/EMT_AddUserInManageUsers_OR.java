package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_AddUserInManageUsers_OR extends Actiondriver {

	
	public static By lnkviewMore = By.xpath("//a[text()='view more...']");
	
	//public static By lnkAdmin = By.xpath("//h2[text()='Administration']");
	public static By lnkAdmin = By.xpath("//a[text()='Admin']");
	
	//public static By LnkManageUser = By.xpath("//a[text()='Manage Users']");
	public static By LnkManageUser = By.xpath("//a[contains(text(),'Manage Users')]");
	
	//public static By LnkAddUser = By.xpath("//a[text()='Add User']");
	//public static By LnkAddUser = By.xpath("//a[contains(text(), 'Add New User')]");
	public static By LnkAddUser = By.xpath("//span[@id='add-link']");
	public static By txtFirstName = By.xpath("//input[@id='user-firstname']");	
	
	public static By txtLastName = By.xpath("//input[@id='user-lastname']");
	
	public static By txtEmail = By.xpath("//input[@id='user-email']");
	
	public static By txtLoginId = By.xpath("//input[@id='user-loginid']");
	
	public static By txtPassword = By.xpath("//input[@id='user-password']");
	
	public static By getCheckbox(String profileName){
        return By.xpath("//label[text()='"+profileName+"']/preceding-sibling::input");
    }

	public static By txtMessage(String first,String last){
		return By.xpath("//div[text()='User \""+first+" "+last+"\"has been added.']");
	}
	
	public static By txtActualMessage= By.xpath("//div[@id='server-msg-0']");
	
	
	public static By btnSubmit= By.xpath("//input[@class='button-small']");	
	
	public static By txtMessage=By.id("server-msg-0");
	
	public static By txtManageUsers=By.xpath("//div[@class='icon-header']/h1[text()='Manage Users']");
	
	public static By lnkCancel=By.xpath("//a[contains(text(),'Cancel')]");
	
	
}
