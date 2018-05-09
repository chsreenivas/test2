package com.cigniti.test;

import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Appium {
	
	AppiumDriver<IOSElement> wd;
  @Test
  public void f() throws Exception {
	  
	  
	  //File appDir=new File("Users/alliancetech/Library/Developer/Xcode/DerivedData/UICatalog-fsnnbwbsdlnknpacpwbiiuugtcyt/Build/Products/Debug-iphoneos/UICatalog.app");
	  		DesiredCapabilities capabilities = new DesiredCapabilities();
	  		capabilities.setCapability("appium-version", "1.0");
	  		capabilities.setCapability("platformName", "iOS");
	  		capabilities.setCapability("platformVersion", "8.4");
	  		capabilities.setCapability("deviceName", "iPhone");
	  	//	capabilities.setCapability("app",appDir.getAbsolutePath() );
	  		
	  	
	  		//capabilities.setCapability("udid", "cbe611b0a74d4850f169ffe2c6f737b420683f37");
	  		//capabilities.setCapability("bundleId", "apple-samplecode.UICatalog");
	  		
	  		
	  		wd=new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			//wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[1]")).click();
			wd.findElement(By.name("Alert Controller")).click();
			wd.findElement(By.name("Okay / Cancel")).click();
			wd.switchTo().alert().accept();
			wd.switchTo().defaultContent();
			wd.findElement(By.name("Sliders")).click();
			wd.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[2]/UIATableCell[2]/UIASlider[1]")).setValue("80");;
			
			wd.close();
	  		
  }
}




