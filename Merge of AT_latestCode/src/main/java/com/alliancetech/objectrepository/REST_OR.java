package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class REST_OR extends Actiondriver
{
	public static By txtMethod=By.id("request-method");
	public static By txtURL=By.id("request-url");
	public static By btnSend=By.id("request-button");
	public static By lnkResponseBody = By.xpath("//a[text()='Response Body (Preview)']");
	public static By txtBodyArea = By.xpath("//div[@id='response-body-preview']//textarea");

	
 
}
