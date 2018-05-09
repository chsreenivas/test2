package com.cigniti.test;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.automation.utilities.Data_Provider;
import com.cigniti.automation.utilities.Reporters;

public class NewTest extends BF{
	
	
	@Test(dataProvider = ("getData"))
  public void addUserandDeleteUser() throws Throwable {
	  
	 /* //Open Webpage
      driver.get("http://qa4-automation.ieventstest.com");
      
      
      //Enter Username
      driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("devsupport@alliancetech.com");
      //Enter Password
      driver.findElement(By.xpath("//input[@id='login-password']")).sendKeys("c0nfnav");
      //click submit
      driver.findElement(By.xpath("//input[@id='login-btn']")).click();
      //click on adimin link
      driver.findElement(By.linkText("Admin")).click();
     //click on Add new User
      driver.findElement(By.linkText("Add New User")).click();
      //Enter First name
      driver.findElement(By.xpath("//input[@id='user-firstname']")).sendKeys("lucky");
      //Enter Last name
      driver.findElement(By.xpath("//input[@id='user-lastname']")).sendKeys("guduru");
      //.//*[@id='user-email']
      //Enter Email
      driver.findElement(By.xpath("//input[@id='user-email']")).sendKeys("lucky@gmail.com");
      //Login id 
      driver.findElement(By.xpath("//input[@id='user-loginid']")).sendKeys("lucky@cigniti.com");
      //Enter Password
      driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("guduru1");
      //check the userprofile
     // driver.findElement(By.xpath("//input[@class='checkbox-3']")).click();
      //Click on submit
      driver.findElement(By.xpath("//input[@class='button-small']")).click();
      
      
      //click on manage users
      driver.findElement(By.linkText("Manage Users")).click();
      
      //select column
      List<WebElement> e=driver.findElements(By.xpath("//td[2][@class='column-selected']"));
      
      
      for(WebElement we: e)
      {

    	  if(we.getText().equals("guduru, lucky"))
    	  {
    		  System.out.println(" Test Pass : user availble");
    	  }else
    	  {
    		  System.out.println(" Test failed : user does not exist");
    	  }
    	  
      }
      //delete user
      driver.findElement(By.linkText("View / Edit User")).click();
      //select user
      Select s=new Select(driver.findElement(By.xpath("//select[@id='selectList']")));
      s.selectByValue("lucky guduru");
      
      driver.findElement(By.xpath("//span[@id='detail-link']")).click();
      driver.findElement(By.xpath(".//*[@id='delete-button-container']/input")).click();
      
  }*/
	  
	  
	  if(getEmtURL())
	  {
		  Reporters.SuccessReport("Lunch EMT Application", "success to lucnch");
	  }
	  else
	  {
		  Reporters.failureReport("Lucnh EMT Apllication ", "failure to lunch");
	  }
	 
	  if( emtLogin())
	  {
		  Reporters.SuccessReport("Login into EMT", "success to login");
	  }else
	  {
		  Reporters.failureReport("Login into EMT", "failure to login");
	  }
	  
	 /*if(linkAdmin())
	 {
		 Reporters.SuccessReport("click on Admin link", "success to click Admin");
	 }else
	 {
		 Reporters.failureReport("click on Admin link", "failure to click Admin");
	 }
	 
	 if(linkAddUser())
	 {
		 Reporters.SuccessReport("click on Add new user link", "success to add new user");
	 }else
	 {
		 Reporters.failureReport("click on Add new user link link", "failure to add new user");
	 }*/
	 
	  
	  if(linkViewMore())
		 {
			 Reporters.SuccessReport("click on view more  link", "success to click viewmore");
		 }else
		 {
			 Reporters.failureReport("click on view more link", "failure to click viewmore");
		 }
	 
	  if(selectoption())
	  {
		  Reporters.SuccessReport("Select Option", "Admin selected successfully");
	  }else
	  {
		  Reporters.failureReport("Select Option", "Admin not selected");
	  }
		 

	  if(addNewUser())
	  {
		  Reporters.SuccessReport("Click on AddNewUser", "Add new User page successfully opened");
	  }else
	  {
		  Reporters.failureReport("Select Option", "Add new user page not opend");
	  }
	 
	  
  }
  @DataProvider
	public Object[][] getData() throws Exception {
		return Data_Provider.getTableArray("Pravalika_TestData", "Key");  //returning data from "EMTAddAssociation" sheet and "Key_AddAssociations" as a reference to read data
	}
	
}
