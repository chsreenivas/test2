package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class EMT_SessionsOR extends Actiondriver{

	public static By lnkViewMore=By.xpath("//a[text()='view more...']");

	public static By tabSessions=By.xpath("//h4[text()='Sessions']");

	public static By txtSessionTitle=By.xpath("//h1[text()='Sessions']");

	public static By lnkAddNewRecord=By.xpath("//span[text()='Add New Record?']");

	public static By btnAdd=By.xpath("//*[@id='add-button-container']/input");

	public static By txtPageTitle=By.xpath("//h1[text()='Add New Session']");

	public static By txtTitle=By.tagName("textarea");

	public static By ddStatus=By.xpath("//div[text()='Status']/parent::td/following-sibling::td/descendant::select");

	public static By ddRoom=By.xpath("//div[text()='Room']/parent::td/following-sibling::td/descendant::select");

	public static By ddDate=By.xpath("//table[@class='details-table']/tbody/tr[7]/td[2]/div[3]/select");

	public static By ddLocation=By.xpath("//div[text()='Location']/parent::td/following-sibling::td/descendant::select");

	//public static By txtStartTime=By.xpath("//table[@class='details-table']/tbody/tr[8]/td[2]/input");
	public static By txtStartTime=By.xpath("//div[text()='Start Time']/parent::td/following-sibling::td[@class='col2']/input");

	public static By imgSlider=By.xpath("//dd[@class='ui_tpicker_hour']//a");

	public static By txtScanStartTime=By.xpath("//table[@class='details-table']/tbody/tr[9]/td[2]/input");

	public static By txtScanEndTime=By.xpath("//table[@class='details-table']/tbody/tr[9]/td[4]/input");

	public static By ddSurvey=By.xpath("//table[@class='details-table']/tbody/tr[10]/td[2]/div[3]/select");

	public static By btnSubmit=By.xpath("//input[@value='Submit']");

	public static By lnkSearch=By.xpath("//*[@id='search-button-magnify']/span");

	public static By txtSearch=By.xpath("//*[@id='search-terms']");

	public static By btnSearch=By.xpath("//*[@id='search-submit']");

	public static By lstSessions=By.xpath("//div[@class='table-container']/table");

	public static By lnkManageSessionEnrollment=By.xpath("//a[text()='Manage Session Enrollment']");

	public static By txtSessionSearch=By.xpath("//input[@id='manage-search-input']");

	public static By btnSessionSearch=By.xpath("//input[@id='manage-search-btn']");

	public static By chckSelect=By.xpath("//table[@id='search-results-table']/tbody/tr/td/input");

	public static By btnRegistrantAdd=By.xpath("//*[@id='tabs-2']/div[2]/input");

	public static By btnSave=By.xpath("//*[@id='manage-buttons-top']/input");

	public static By lnkManageSessionAttendance=By.xpath("//a[text()='Manage Session Attendance']");

	public static By btnDeleteRegistrant=By.xpath("//div[@class='delete-row']");	

	public static By tblSessionEnrollment=By.xpath("//div[@class='container d1 border-top']/div[2]/table/tbody/tr/td");

	public static By tblSessionAttendance=By.xpath("//div[@class='container d1 border-top']/div[2]/table/tbody/tr/td");
	
	public static By lnkDeleteThisSession=By.xpath("//span[text()='Delete This Session?']");
	
	public static By frame_window=By.id("manage-iframe");
	
	public static By verifySession(String sessionTitle,String StartTime,String EndTime,String Date){
		return By.xpath("//tr[td[text()='"+sessionTitle+"'] and td[text()='"+StartTime+" - "+EndTime+"'] and td[text()='"+Date+"']]");
	}
	
	public static By openSessionTitle(String sessionTitle)
	{
		return By.xpath("//td[text()='"+sessionTitle+"']");
	}
	
	public static By verifySessionEnrollment(String Registrant){
		return By.xpath("//h2[text()='Session Enrollment']/parent::div/following-sibling::div/descendant::td[2]");
	}		


	public static By verifySessionAttendance(String Registrant){
		return By.xpath("//tr[td[text()='"+Registrant+"']]");
	}

	public static By getRegistrant(String regName){
		return By.xpath("//td[text()='"+regName+"']");
	}

	public static By btnCloseWindow = By.xpath("//a[@class='closeModalBox']");	

	public static By txtAddNew(String LabelText){
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//input[@name='attribute']");
	}

	public static By btnPlus(String LabelText){
		return By.xpath("//td[div[text()='"+LabelText+"']]/following-sibling::td//input[@type='button']");
	}

	public static By txtBox(String LabelText){
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td/input");
	}

	public static By chckActive = By.xpath("//div[text()='Active']/parent::td/following-sibling::td/input[@type='checkbox']");

	public static By chckEnrollable = By.xpath("//div[text()='Enrollable']/parent::td/following-sibling::td/input[@type='checkbox']");

	public static By txtDescription = By.xpath("//div[text()='Description']/parent::td/following-sibling::td/textarea");

	public static By ddLabel(String LabelText){
		return By.xpath("//div[text()='"+LabelText+"']/parent::td/following-sibling::td//select");
	}

	public static By lbltxtValues(String LabelName){

		return By.xpath("//td[div[text()='"+LabelName+"']]/following-sibling::td/div");
	}
	
	public static By lblddValues(String LabelName){

		return By.xpath("//td[div[text()='"+LabelName+"']]/following-sibling::td/div/div[2]");
	}

	public static By startTime=By.xpath("//td[div[text()='Start Time']]/following-sibling::td/input");
	
	public static By endTime=By.xpath("//td[div[text()='End Time']]/following-sibling::td/input");
	
	public static By scanStartTime=By.xpath("//td[div[text()='Scan Start Time']]/following-sibling::td/input");
	
	public static By scanEndTime=By.xpath("//td[div[text()='Scan End Time']]/following-sibling::td/input");
	
	public static By btnPlusOfRoom=By.xpath("//td[div[text()='Room']]/following-sibling::td//input[@id='new-attribute-btn1']");
	
	public static By btnPlusOfPaperType=By.xpath("//td[div[text()='Paper Type']]/following-sibling::td//input[@id='new-attribute-btn1']");
}
