package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class MyAgendaOR extends Actiondriver {
	
	public static By txtUserName =By.id("login-id"); 
	
	public static By txtPassword =By.id("password"); 
	
	public static By btnSignIn =By.xpath("//*[@id='login-form']/div/div[4]/a");
	
	public static By btnNoThanksInTwitterPopUp=By.xpath("//a[text()='No thanks']");
	
	public static By btnAddToAgenda=By.xpath("//a[text()='Add To Agenda']");
	
	public static By btnRemoveFromAgenda=By.xpath("//a[text()='Remove From Agenda']");
	
	public static By btnViewSessionDetails=By.xpath("//div[text()='View Session Details']");
	
	public static By txtSuccessMessageOfAddSession=By.xpath("//div[text()='Session added to agenda!!']");
	
	public static By txtSuccessMessageOfRemoveSession=By.xpath("//div[text()='Session removed from agenda!!']");
	
	public static By txtAdvancedSearchErrorMessage=By.id("popup_message");
	
	public static By btnErrorPopUpOK=By.xpath("//input[@id='popup_ok']");
	
	public static By txtSessionTitleFromSessionDetailsPopUp=By.xpath("//div[@id='session-info']/h3");
	
	public static By txtSessionDate=By.xpath("//div[@class='session-date']");
	
	public static By btnPrintIconInMyAgenda=By.id("print_my_agenda");
	
	public static By txtPrintPopUpTitleInMyAgenda=By.xpath("//h3[text()='Full Or Summary Version']");
	
	public static By chckboxFullVersionOfPrintPopUpInMyAgenda=By.xpath("//input[@id='print-type-full']");
	
	public static By chckboxSummaryOfPrintPopUpInMyAgenda=By.xpath("//input[@id='print-type-summary']");
	
	public static By btnSendOfPrintPopUpInMyAgenda=By.xpath("//a[@id='but_send']");
	
	public static By btnCloseOfPrintPopUpInMyAgenda=By.xpath("//h3[text()='Full Or Summary Version']/following-sibling::img[@title='Click the X to Close']");
	
	public static By btnEmailIconInMyAgenda=By.id("email_my_agenda");
	
	public static By txtEmailPopUpTitleInMyAgenda=By.xpath("//h3[text()='Select preferred email address']");
	
	public static By chckboxEmailPopUpIDInMyAgenda=By.xpath("//input[@id='"+configProps.getProperty("Portal_Username")+"' and @checked='checked']");
	
	public static By btnDownloadIconInMyAgenda=By.id("download_my_agenda");
	
	public static By btnExcelIconInMyAgenda=By.id("excel_my_agenda");
	
	public static By txtDetailsInSessionDetailsPopUp=By.xpath("//h4[text()='Details']");
	
	public static By txtDescriptionInSessionDetailsPopUp=By.xpath("//h4[text()='Description']");
	
	public static By lnkEmailInSessionDetailsPopUp=By.xpath("//a[@id='email']");
	
	public static By lnkPrintInSessionDetailPopUp=By.xpath("//a[@id='print']");
	
	public static By lnkDownloadSessionDetailInPopUp=By.xpath("//a[@id='download']");
	
	public static By btnAddToAgendaInSessionDetailsPopUp=By.xpath("//a[text()='Add To Agenda']");
	
	public static By btnCloseForSessionDetailsPopUp=By.xpath("//div[@class='heading']/div[@class='close']");
	
	public static By frameSessionDetailsPopUp=By.xpath("//div[@id='modal']/iframe");
	 	
	//public static By myAgendaLink =By.xpath("html/body/div[1]/div[3]/div/dl[1]/dd[1]/h3/a");
	public static By lnkMyAgendaIcon =By.xpath("//h3/a[text()='My Agenda']");

	public static By tabMealHours =By.linkText("Meal Hours");
	
	public static By tabExpoHours =By.linkText("Expo Hours");
	
	public static By tabGeneralSessions =By.linkText("General Sessions & Keynotes");
	
	public static By chckFilterSessions =By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[2]/div[1]/label");
	
	public static By lnkAddSessions =By.xpath("//*[@id='calendar']/div[1]/table/tbody/tr/td[2]/div/a");
	
	public static By imgPrint =By.xpath("//*[@id='print_my_agenda']");
	
	public static By imgEmail =By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/a[2]");
	
	public static By imgDownload =By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[4]/a[3]");
	
    public static By txtDayView =By.xpath("html/body/div[1]/div[2]/div[2]/div[3]/div[1]/table/tbody/tr/td[4]/div[2]/a");
	
    public static By txtWeekView =By.xpath("html/body/div[1]/div[2]/div[2]/div[3]/div[1]/table/tbody/tr/td[4]/div[1]/a");
    
	public static By txtMyPersonalAgenda =By.xpath("html/body/div[1]/div[2]/div[1]/div[1]/div/ul/li[1]/a/span");
	
	public static By txtExibhitorAgenda =By.xpath("html/body/div[1]/div[2]/div[1]/div[1]/div/ul/li[2]/a/span");
	
	public static By chckFilterMeetings =By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/table/tbody/tr/td[2]/div[2]/label");
	
	public static By lnkAddMeetings =By.xpath("html/body/div[1]/div[2]/div[2]/div[3]/div[1]/table/tbody/tr/td[2]/div[2]/a");
	
	
	public static By calender =By.xpath("//div[@id='calendar']/div[2]");
	
	public static By tabMealHour =By.linkText("Meal Hours");
	
	public static By txtBreakfast =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[1]/ul/li[1]/span");
	
	public static By txtLunch =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[1]/ul/li[2]/span");
	
	public static By txtReception =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[1]/ul/li[3]/span");
	
	public static By tabExpoHour =By.linkText("Expo Hours");
	
	public static By txtExpoAcess =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[2]/ul/li[1]/span");
	
	public static By txtExpoFullySatisfied =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[2]/ul/li[2]/span");
	
	
	public static By tabGeneralSession =By.linkText("General Sessions & Keynotes");
	
	public static By satgeneral =By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[3]/ul/li[1]/span");
	
	public static By chckSession =By.xpath("//input[@id='chkSession']/following-sibling::span");
	
	public static By lnkLogOut=By.xpath("//a[text()='Logout']");
	
}
