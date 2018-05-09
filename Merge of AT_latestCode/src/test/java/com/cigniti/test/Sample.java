package com.cigniti.test;





import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.alliancetech.businessfunctions.BusinessFunctions;

public class Sample extends BusinessFunctions{
  @Test
  public void f() throws Throwable {
	  
	  driver.get("http://www.hdfcbank.com/");
	  Thread.sleep(1000);
	  driver.findElement(By.id("loginsubmit")).click();
	  Thread.sleep(2000);
	  switchWindowByTitle("HDFC Bank NetBanking - Internet Banking Services by HDFC Bank", 2);
	  driver.findElement(By.xpath("//div[@id='wrapper']/div[@class='button']/a")).click();
	  
	  /*List<WebElement> elements=driver.findElements(By.xpath("//select[@id='sortOptions']/option"));
	  System.out.println(elements.size());
	 	  for (int i = 1; i <= elements.size(); i++) {
		  String text=getText(By.xpath("//select[@id='sortOptions']/option["+(i)+"]"), "");
		  System.out.println(text);
		  String originalSortOrder=getText(By.xpath("//select[@id='sortOptions']/option[@title='ORIGINAL_SORT_ORDER']"), "");
		  System.out.println(originalSortOrder);
		  if(!(text.equalsIgnoreCase(originalSortOrder)))
		  {
			  click(By.xpath("//select[@id='sortOptions']/option["+(i+1)+"]"),"");
			  Thread.sleep(2000);
		  }
	}*/
	 	  
	 	  
	}
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
  /*driver.get("http://www.jabong.com/men/clothing/new-products/?source=topnav_men");
  new Actions(driver).moveToElement(driver.findElement(By.xpath("//ul[@id='productsCatalog']/li[1]/a/span/span/img[@class='itm-img']"))).perform();
  click(By.xpath("//div[@id='qa-quick-view-btn']"),"");
  Thread.sleep(2000);
  List<WebElement> images=driver.findElements(By.xpath("//div[@class='vSlider-in pos-abs']/span"));
  System.out.println(images.size());
  for (int i = 1; i <=images.size(); i++) {
	
	  click(By.xpath("//div[@class='vSlider-in pos-abs']/span["+i+"]"), "");
	  String thumb_nail_name=getAttribute(By.xpath("//span[@class='selected']/img"),"alt", "");
	  System.out.println(thumb_nail_name);
	  String mainimgsrc=getAttribute(By.xpath("//div[@class='quick-image-view fl pos-rel']/img"), "src", "");
	  System.out.println(mainimgsrc);
	  if(thumb_nail_name.equalsIgnoreCase(mainimgsrc))
	  {
		  Reporters.SuccessReport("", "");
	  }*/
	  
	  
	  
  }

