package com.cigniti.test;

import org.openqa.selenium.By;

public class OR {
	// Username
	public static By txtUsername = By.xpath("//input[@id='login-email']");
	//Password
	public static By txtPassword = By.xpath("//input[@id='login-password']");
	//Button
	public static By btnSignIn = By.xpath("//input[@id='login-btn']");
	/*//LInk
	public static By linkAdmin = By.linkText("Admin");
	//click on Add new User
    public static By linkAddUser=By.linkText("Add New User");
	
*/
	
   public static By lickViewMOre=By.xpath("//a[text()='view more...']");
   
   public static By selectMenu=By.xpath("//h2[text()='Administration']");
   
   //add new user link
   public static By addNewUser=By.linkText("Add New User");
   //first user
   public static By FirstName=By.xpath("//input[@id='user-firstname']");
   public static By LastName=By.xpath("//input[@id='user-lastname']");
   public static By EmailId=By.xpath("//input[@id='user-email']");
   public static By LoginId=By.xpath("//input[@id='user-loginid']");
   public static By Password=By.xpath("//input[@id='user-password']");
   
}
