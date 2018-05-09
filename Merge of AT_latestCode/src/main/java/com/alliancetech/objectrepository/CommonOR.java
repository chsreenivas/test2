package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

public class CommonOR {
  
	public static By lnkViewMore=By.xpath("//a[text()='view more...']");
	
	public static By lnkAdmin=By.xpath("//h4[text()='Administration']");
	
	public static By lnkLogoutPortal= By.xpath("//a[text()='Logout']");
	
	public static By lnkSessions=By.xpath("//h4[text()='Sessions']");
	
	public static By lnkSpeakers=By.xpath("//h4[text()='Speakers']");
	
	public static By lnkEmailCampigns=By.xpath("//h4[text()='Email Campaigns']");
	//public static By lnkEmailCampigns=By.xpath("//a[text()='Email Campaigns']");
	
	
	public static By lnkRegistrants=By.xpath("//h4[text()='Registrants']");
	
	public static By lnkRooms=By.xpath("//h4[text()='Rooms']");
	
	public static By lnkAssociations=By.xpath("//h4[text()='Associations']");
	
	public static By lnkExhibitors=By.xpath("//h4[text()='Exhibitors']");
	
	public static By lnkReports=By.xpath("//h4[text()='Reports']");
	
	public static By lnkLeads=By.xpath("//h4[text()='Leads']");
	
	public static By lnkHighlights=By.xpath("//h4[text()='Highlights']");
	
	public static By frameTab=By.xpath("//div[@class='modalBoxBodyContent']/iframe");
	
	public static By txtSearch=By.id("search-terms");
	
	public static By lnkSearch=By.id("search-button-magnify");
	
	public static By btnSearch=By.id("search-submit");
	
	public static By lnkAddANewSurvey=By.xpath("//a[contains(text(),'Add New Survey')]");
	
	public static By lnkManageSurvey=By.xpath("//a[contains(text(),'Manage Survey')]");
	
	public static By txtManageSurveys=By.xpath("//h1[text()='Manage Surveys']");
	
	public static By openSurvey(String survey_Name)
	{
		return By.xpath("//td[text()='"+survey_Name+"']");
	}
	
	public static By txtEditSurvey=By.xpath("//h1[text()='Edit Survey']");
	
	public static By txtWarning=By.xpath("//div[@class='warning']");
			
	public static By txtAddSurvey=By.xpath("//h1[text()='Add Survey']");
	
	public static By lnkDeleteThisRecord=By.id("detail-link");
	
	public static By txtSurveyName=By.id("name");
	
	public static By txtAddNew(String LabelText){
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//input[@name='attribute']");
	}

	public static By btnPlus(String LabelText){
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td[1]//label[text()='Add New:']/following-sibling::input[@type='button']");
	}

	public static By txtBox(String LabelText){
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td[1]/input");
	}
	
	public static By txtSurveyName(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']/following-sibling::td/input[2]");
	}
	
	public static By txtArea(String LabelText)
	{
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td/textarea");
	}
	
	public static By txtSurveyDescription(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']/following-sibling::td/textarea");
	}
	public static By chckActive = By.xpath("//div[text()='Active']/parent::td/following-sibling::td/input[@type='checkbox']");

	public static By chckEnrollable = By.xpath("//div[text()='Enrollable']/parent::td/following-sibling::td/input[@type='checkbox']");
	
	public static By chckBox(String LabelText)
	{
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td/input[@type='checkbox']");
	}

	public static By txtBio = By.xpath("//div[text()='Bio']/parent::td/following-sibling::td/textarea");
	
	public static By txtAboutMe=By.xpath("//div[text()='About Me']/parent::td/following-sibling::td/textarea");
	
	public static By chckPrintBadgeOnSubmit=By.xpath("//input[@id='print-badge-detail-input' and @type='checkbox']");
	
	public static By ddBadgePrinter=By.xpath("//select[@id='badgeprinter-select']");
	
	public static By txtExhibitName=By.xpath("//div[text()='Exhibit Name']/parent::td/following-sibling::td/textarea");
	
	public static By txtDescription=By.xpath("//div[text()='Description']/parent::td/following-sibling::td/textarea");
	
	public static By ddLabel(String LabelText){
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td[1]//select");
	}
	
	public static By ddSurvey(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']/following-sibling::td/select");
	}

	public static By lbltxtValues(String LabelName){

		return By.xpath("//td[div[text()='"+LabelName+"']]/following-sibling::td/div");
	}
	
	public static By lblddValues(String LabelName){

		return By.xpath("//td[div[text()='"+LabelName+"']]/following-sibling::td/div/div[2]");
	}
	
	public static By lblMessage = By.id("server-msg-5000");

	public static By lblSearchData(String LabelName){
		System.out.println(By.xpath("//div[h2[text()='"+LabelName+"']]/following-sibling::div//tbody/tr"));
		return By.xpath("//div[h2[text()='"+LabelName+"']]/following-sibling::div//tbody/tr");
	}
	
	public static By ajaxLoad=By.xpath("//div[contains(@class,'ajax') or contains(@class,'loading')]");
	
	public static By loadingBySectionName(String SectionName)
	{
		return By.xpath("//div[h2[text()='"+SectionName+"']]/following-sibling::div//h2[text()='Loading...']");
	}
	
	public static By txtErrorMessage(String LabelText)
	{
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td/div[@class='error']");
	}
	
	public static By txtTitleErrorMessage=By.xpath("//textarea/following-sibling::div[5]");
	
	public static By lnkIconInHomePage(String icon)
	{
		return By.xpath("//a[text()='"+icon+"']");
	}
	
	public static By tabToBeClicked(String tab)
	{
		return By.xpath("//a[@title='"+tab+"']");
	}
	
	public static By txtBoxData(String LabelText)
	{
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td");
	}
	
	public static By btnSave(String LabelText)
	{
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//div/input[@value='Save']");
	}
	
	public static By ddRemoveValue(String LabelText)
	{
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td[1]/div/div[2]");
	}
	
	public static By txtChoose=By.xpath("//label[text()='Choose:']");
	
	public static By checkInAjax=By.id("ajax");
	
	public static By lnkRefresh(String type)
	{
		return By.xpath("//h2[text()='"+type+"']/following-sibling::div/a[contains(@id,refresh)]");
	}
	
	public static By btnReloadPropsOnThisServer=By.xpath("//form/p/input[@value='Re-Load All Cache on this Server']");
	
	public static By ddViewBy=By.xpath("//select[@id='view-by-select']");
	
	public static By viewByName(String viewByName)
	{
		return By.xpath("//ul[@id='view-bys']//li/div/h3[text()='"+viewByName+"']");
	}
	
	public static By ddSelectTyppe=By.xpath("(//div[@class='chosen-drop']/ul/li)[2]");
	
	
}
