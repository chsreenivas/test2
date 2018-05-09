package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class EMT_AddSpeaker_OR {

public static By lnkAddNewRecord=By.xpath("//span[@id='add-link']");

public static By txtSpeakersTitle=By.xpath("//h1[text()='Speakers']");

public static By txtPageTitle=By.xpath("//h1[text()='Speakers']");

public static By selectElementDropDown=By.xpath("//select[@id='add-record']");

public static By btnAddNewRecord=By.xpath("//input[@class='button-small']");

public static By txtFirstName=By.xpath("//*[@id='20111222153347619458000000-detail-input']");

public static By selectStatus=By.xpath("//select[@class='attribute-select']");

public static By txtLastName=By.xpath("//*[@id='20111222153347807554000000-detail-input']");

public static By txtCompany=By.xpath("//*[@id='20111222153348697373000000-detail-input']");

public static By txtEmail=By.xpath("//*[@id='20111222153347270047000000-detail-input']"); 

public static By txtLoginID=By.xpath("//*[@id='20111230023455162580000000-detail-input']");

public static By txtPassword=By.xpath("//*[@id='20111230023731984457000000-detail-input']");

public static By txtSuccessMessage=By.id("server-msg-5000");

public static By btnSubmit=By.xpath("//div[@class='container']/div/input[@value='Submit']");

public static By btnsearch= By.xpath("//div[@id='search-button-magnify']");

public static By txtSearchbox=By.xpath("//input[@id='search-terms']");

public static By lnkDeleteThisSpeaker=By.xpath("//span[text()='Delete This Speaker?']");

public static By lnkManageSessions=By.xpath("//a[text()='Manage Sessions']");

public static By txtConfirmPassword=By.xpath("//div[text()='Confirm Password']/parent::td/following-sibling::td/input[2]");

public static By verifySpeaker(String firstname,String lastname,String emailid){
	
	return By.xpath("//tr[td[text()='"+firstname+"'] and td[text()='"+lastname+"'] and td[text()='"+emailid+"']]");
}

public static By speakerEmailID(String emailid)
{
	return By.xpath("//tr[td[text()='"+emailid+"']]");
}

}
