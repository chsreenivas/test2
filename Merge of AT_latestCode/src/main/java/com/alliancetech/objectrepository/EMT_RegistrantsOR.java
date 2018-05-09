package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class EMT_RegistrantsOR {
	
	public static By lnkViewMore=By.xpath("//a[text()='view more...']");
	
	public static By tabRegistrants=By.xpath("//h4[text()='Registrants']");
	
	public static By txtRegistrantaTitle=By.xpath("//h1[text()='Registrants']");
	
	public static By lnkAddNewRecord=By.xpath("//*[@id='add-link']");
	
	public static By btnAdd=By.xpath("//*[@id='add-button-container']/input");
	
	public static By txtAddNewRegistrant=By.xpath("//*[@id='content']/div[2]/div[1]/div[2]/h1");
	
	public static By txtAttendeeNum=By.xpath("//table[@class='details-table']/tbody/tr[2]/td[4]/input");
	
	public static By txtFirstName=By.xpath("//table[@class='details-table']/tbody/tr[3]/td[2]/input");
	
	public static By txtLastName=By.xpath("//table[@class='details-table']/tbody/tr[4]/td[2]/input");
	
	public static By ddStatus=By.xpath("//select[@class='attribute-select']");
	
	public static By  txtLoginId=By.xpath("//*[@id='20120103031727359037000000-detail-input']");
	
	public static By  txtPassword=By.xpath("//*[@id='20120103031558131192000000-detail-input']");
	
	public static By  txtFullName=By.xpath("//*[@id='20120103031019050393000000-detail-input']");
	
		public static By  txtBadgeFirstName=By.xpath("//*[@id='20120103031021260850000000-detail-input']");
	
	public static By  txtTitle=By.xpath("//*[@id='20120103031022347508000000-detail-input']");
	
	public static By  txtPhone=By.xpath("//*[@id='20120103031429534328000000-detail-input']");
	
	public static By  txtMobile=By.xpath("//*[@id='20120103031431629694000000-detail-input']");
	
	public static By  txtAltPhone=By.xpath("//*[@id='20120103031430644211000000-detail-input']");
	
	public static By  txtFax=By.xpath("//*[@id='20120103031433690906000000-detail-input']");
	
	public static By  txtCompany=By.xpath("//*[@id='20120103031023385751000000-detail-input']");
	
	public static By  txtAddress1=By.xpath("//*[@id='20120103031024454554000000-detail-input']");
	
	public static By  txtAddress2=By.xpath("//*[@id='20120103031025452027000000-detail-input']");
	
	public static By  txtCounty=By.xpath("//*[@id='20120103031424976919000000-detail-input']");
	
	public static By  txtRegion=By.xpath("//*[@id='20120103031425976540000000-detail-input']");
	
	public static By  txtCity=By.xpath("//*[@id='20120103031423915310000000-detail-input']");
	
	public static By  txtCöuntryCode=By.xpath("//*[@id='20120103031428502225000000-detail-input']");
	
	public static By  ddCountry=By.xpath("//*[@id='20120103031731240234000000-attribute-select']");
	
	public static By txtZipCode=By.xpath("//*[@id='20120103031427448632000000-detail-input']");
	
	public static By txtPersonalEmail=By.xpath("//*[@id='20120103030832429314000000-detail-input']");
	
	public static By txtEmail=By.xpath("//*[@id='20120103030831260070000000-detail-input']");
	
	public static By btnSubmit=By.xpath("//input[@value='Submit']");
	
	public static By lnkBackToRegistrant=By.xpath("//*[@id='detail-links']/ul/li[1]/a");
	
	public static By tblResults=By.xpath("//div[@class='container d1 border-top']/div/table/tbody/tr");
	
	public static By lnkDeleteThisRegistrant=By.xpath("//span[text()='Delete This Registrant?']");
	
	public static By btnConfirm=By.xpath("//input[@value='Confirm']");
	
	public static By txtMessage=By.id("server-msg-10000");
	
	public static By txtConfirmPassword=By.xpath("//div[text()='Confirm Password']/parent::td/following-sibling::td/input[2]");
	
	public static By openRegistrant(String attendee){
		
		return By.xpath("//table/tbody/tr/td[text()='"+attendee+"']");
	}
	
	public static By verifyRegistrant(String attendee,String first,String last,String company){
		return By.xpath("//tr[td[text()='"+attendee+"'] and td[text()='"+first+"'] and td[text()='"+last+"'] and td[text()='"+company+"']]");
	}
	
	public static By btnSave = By.xpath("//td[div[text()='Checked-in']]/following-sibling::td//input[@value='Save']");

	public static By confirm_Window=By.id("delete-button-container");
			
}
