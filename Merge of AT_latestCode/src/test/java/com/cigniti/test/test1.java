package com.cigniti.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;

public class test1 extends BusinessFunctions{
  @Test
  public void f() {
	  driver.get("http://qa4-move.signup.ieventstest.com/");
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  driver.findElement(By.cssSelector("input[type='email']")).sendKeys("asd");
	  Object s=js.executeScript("return document.getElementById(\"20120103030831260070000000\").validity.valid");
	  System.out.println(s);
	  driver.findElement(By.cssSelector("input[type='email']")).sendKeys("asd@gmail.com");
	  s=js.executeScript("return document.getElementById(\"20120103030831260070000000\").validity.valid");
	  System.out.println(s);
  }
}
