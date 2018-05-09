package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class SessionDetailsPopUPOR extends Actiondriver{
	
	public static By txtSession=By.xpath("//*[@id='session-search']/div[5]/div[2]");
	public static By txtSessionTitle=By.xpath("//*[@id='session-search']/div[5]/div[2]/table/tbody/tr/td/h4");
	public static By txtSessionPopUpTitle=By.xpath("//*[@id='session-info']/h3") ;
	public static By tblSpeakers=By.xpath("//*[@id='content']/div[1]/div[2]/h3");
	public static By txtSessionSpeakers=By.xpath("//*[@id='session-search']/div[5]/div[2]/table/tbody/tr/td/div[4]");
	public static By txtSessionCode=By.xpath("//*[@id='session-search']/div[5]/div[2]/table/tbody/tr/td/div[5]/span[2]");
	public static By txtSessionPopUpCode=By.xpath("//*[@id='session-info']/div[4]/span[2]");
	public static By txtDescription=By.xpath("//*[@id='session-info']/div");
	public static By btnAddToAgenda=By.xpath("//div[@class='actions']/div/a[2]");
	public static By lnkEmail=By.xpath("//*[@id='email']");
	public static By lnkPrint=By.xpath("//*[@id='print']");
	public static By lnkDownload=By.xpath("//*[@id='download']");
	public static By txtSessionAdded=By.xpath("//*[@id='session-message']");
	public static By txtStatus=By.xpath("//*[@id='content']/div[1]/div[1]/div[3]");
	public static By btnRemoveFromAgenda=By.xpath("//div[@class='actions']/div/a[1]");
	public static By txtSessionRemoved=By.xpath("//*[@id='session-message']");
	public static By txtEmailPopUpTitle=By.xpath("//*[@id='email_box']/div[1]/h3");
	public static By txtUserEmailAddress=By.xpath("//*[@id='email_box']/div[2]/div[1]/label");
	public static By btnEmailSend=By.xpath("//div[@id='email_box']/div[3]/a");
	public static By btnEmailClose=By.xpath("//*[@id='email_box']/div[1]/img");
	public static By txtInfoPopUpTitle=By.xpath("//*[@id='popup_title']");
	public static By txtInfoPopUpMessage=By.xpath("//*[@id='popup_message']");
	public static By btnInfoOk=By.xpath("//*[@id='popup_ok']");
	public static By btnDownloadToCalender=By.xpath("//*[@id='ics_option_table']/tbody/tr[1]/td[1]/div/div");
	public static By btnEmailAsAnAttachment=By.xpath("//*[@id='email_ics_attachment_btn']/div");
	public static By txtCurrentEmail=By.xpath("//*[@id='current_email']");
	public static By txtAdditionalEmails=By.xpath("//*[@id='add_email_label']");
	public static By btnSendEvent=By.xpath("//*[@id='but_send_event_button']");
	
}


