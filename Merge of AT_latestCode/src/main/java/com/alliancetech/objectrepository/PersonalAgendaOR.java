package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class PersonalAgendaOR {

	public static By session =By.xpath("//div[@id='eventShort' and contains(@class,'fc_session')]");
	
	public static By totalelement=By.xpath("//div[@id='eventShort']");
	
	public static By refresh=By.xpath("//div[1]/div[1]");
	
	public static By sessionPage=By.id("session-search-pop-filter");

	public static By btnclose=By.xpath("//*[@id='session-search-pop-filter']/div[1]/div[2]");
	
	public static By addAgenda=By.xpath("//*[@id='calendar']/div[1]/table/tbody/tr/td[2]/div[1]/a");
	
	public static By myAgenda=By.linkText("My Agenda");
	
	public static By agendaName=By.xpath("//h4[@id='20131219054552475037000000']");
	
	public static By addSessionToAgenda=By.linkText("Add To Agenda");
	
	//remove hard coding
	public static By removeAgenda=By.linkText("Remove From Agenda");
	
	//Verification of Print PopUp
	public static By printPopupName=By.xpath("//*[@id='print-my-agenda']/div/h3");
	
	public static By fullVersion=By.xpath("//*[@id='print-my-agenda']/div[2]/label");
	
	public static By Summary=By.xpath("//*[@id='print-my-agenda']/div[2]/label[2]");
	
	public static By printButton=By.xpath("//div[@id='print-my-agenda']/div[3]/a");
	
		
	//Verification of Email Pop Up
	public static By emailOperation=By.id("email_my_agenda");
	
	public static By emailPopUp=By.id("email_box");
	
	public static By emailPopUpName=By.xpath("//div[@id='email_box']/div/h3");
	
	public static By emailuserEmail=By.xpath("//div[@class='emails']/label");
	
	public static By emailFullVersion=By.xpath("//div[@id='print_type']/label");
	
	public static By emailSummary=By.xpath("//div[@id='print_type']/label[2]");
	
	public static By emailSend=By.xpath("//div[@id='email_box']/div[3]/a");
	
	//Verification of Download Pop Up
	public static By downloadButton=By.id("download_my_agenda");
	
	public static By downloadPopUpName=By.xpath("//div[@id='ics_window']/div/div");
	
	public static By downloadToCalenderButton=By.xpath("//*[@id='ics_option_table']/tbody/tr/td/div/div");
	
	public static By emailAsAttachmentButton=By.xpath("//*[@id='email_ics_attachment_btn']/div");
	
	public static By closeDownloadPopUp=By.id("close_ics_button_img");
	
	// Verification of Email As Attachment button in Download Pop up after clicking Download icon 
	public static By downloadScheduledEventPopUpName=By.xpath("//*[@id='ics_window']/div/div");
	
	public static By emailAsAttachmentUserEmail=By.xpath("//*[@id='current_email']");
	
	public static By addAdditionalLink=By.id("add_email_label");
	
	public static By sendEvent=By.id("but_send_event_button");
	
	public static By daySelection=By.xpath("//*[@id='my-agenda-view']/div[2]/a");
	
	public static By weekSelection=By.xpath("//*[@id='my-agenda-view']/div[1]/a");
	
	public static By sessionDetailsPopUpName=By.xpath("//*[@id='modal']/div[1]/h2");
	
	//session details
	public static By sessionList=By.xpath("//div[@id='eventShort' and contains(@class,'fc_session')]");
	
	public static By lnkSessionDetails=By.xpath("//div[@id='eventShort' and contains(@class,'fc_session')][1]");
	
}
