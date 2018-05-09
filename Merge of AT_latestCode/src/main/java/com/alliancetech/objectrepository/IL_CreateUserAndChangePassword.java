package com.alliancetech.objectrepository;

import org.openqa.selenium.By;


public class IL_CreateUserAndChangePassword {
	public static By tabMangerusers=By.xpath("//a[@title='Manage Users']");
	public static By btnCreateuser=By.xpath("//a[@href='?action=addUser']");
	public static By lblTextBox(String labeltext)
	{
	return By.xpath("//th[text()='"+labeltext+"']/following-sibling::td/input[@type='text']"); 
	}
	public static By txtPassword=By.xpath("//th[label[text()='Password']]/following-sibling::td/input[@type='password']");
	public static By txtPasswordConfirm=By.xpath("//th[text()='Password Confirm']/following-sibling::td/input[@type='password']");
	public static By txtFirstName=By.xpath("//th[text()='First Name']/following-sibling::td/input[@type='text']");
	public static By txtLastName=By.xpath("//input[@id='LastName']");
	public static By ddSecurityRole=By.xpath("//select[@id='securityRole']");
	public static By ddOrganization=By.xpath("//select[@id='organizationId']");
	public static By btnAdd=By.xpath("//input[@value='Add']");
	public static By btnAlluser=By.xpath("//a[@href='?action=manageUsers']");
	public static By SearchFirstname=By.xpath("//input[@id='filter']");
	public static By btnSearch=By.xpath("//form/p/input[@value='Search']");
	public static By txtManageUserInformation=By.xpath("//legend[text()='Manage Users']");
	public static By txtCreateUser=By.xpath("//legend[text()='Add User']");
	public static By btnEdit=By.xpath("//td[@class='data_table_data_even']/input[@value='Edit']");
	public static By editfinal =By.xpath("//td[@align='center']/input[@value='Edit']");
	public static By Logout=By.xpath("//a/span[text()='Logout']");
}
