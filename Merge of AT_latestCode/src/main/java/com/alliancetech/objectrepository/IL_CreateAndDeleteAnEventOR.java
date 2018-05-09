package com.alliancetech.objectrepository;

import org.openqa.selenium.By;

import com.cigniti.automation.accelerators.Actiondriver;

public class IL_CreateAndDeleteAnEventOR extends Actiondriver{
  
	public static By txtUsername=By.xpath("//input[@id='username']");
	
	public static By txtPassword=By.xpath("//input[@name='password']");
	
	public static By btnLogin=By.xpath("//input[@name='login0']");
	
	public static By tabEvents=By.xpath("//span[text()='Events']");
	
	public static By btnAdd=By.xpath("//a[text()='Add']");
	
	public static By btnDelete=By.xpath("//a[text()='Remove']");//Remove option under 'Add' option on left side
	
	public static By txtAddEvent=By.xpath("//legend[text()='Add Event ']");
	
	public static By txtFirstName=By.xpath("//input[@id='FirstName']");
	
	public static By txtLastName=By.xpath("//input[@id='LastName']");
	
	public static By txtCompany=By.xpath("//input[@id='Company']");
	
	public static By txtEmailAddress=By.xpath("//input[@id='Email']");
	
	public static By txtPass=By.xpath("//input[@id='Password']");
	
	public static By txtPhoneNumber=By.xpath("//input[@id='Phone']");
	
	public static By txtMobileNumber=By.xpath("//input[@id='Mobile']");
	
	public static By txtEventName=By.xpath("//input[@id='EventName']");
	
	public static By txtEventTag=By.xpath("//input[@id='EventTag']");
	
	public static By txtStartDate=By.xpath("//input[@id='StartDate']");
	
	public static By txtEndDate=By.xpath("//input[@id='EndDate']");
	
	public static By btnAddEvent=By.xpath("//input[@name='Submit']");
	
	public static By txtEventAddedMsg=By.xpath("//p[text()='Event added!']");
	
	public static By txtEvent=By.xpath("//fieldset/select/option[@selected='']");
	
	public static By tabLeadConfigWizard=By.xpath("//span[text()='Lead Config Wizard']");
	
	public static By txtEventFilter=By.xpath("//input[@id='eventFilter']");
	
	public static By lnkFilter=By.xpath("//a[text()='(Filter)']");
	
	public static By drpEvents=By.xpath("//select[@id='eventid']/option[2]");
	
	public static By drpEventsOption=By.xpath("//select[@id='eventid']");
	
	public static By drpEventTag=By.xpath("//select[@id='eventtag']");
	
	public static By tabLogout=By.xpath("//span[text()='Logout']");
	
	public static By txtLogin=By.xpath("//span[text()='Login']");
	
	public static By securityRole=By.xpath("//select[@id='securityrole']");
	
	public static By btnRemove=By.xpath("//form/table/tbody/tr/td/input[@type='submit']");//Remove button at the bottom of remove event page
	
	public static By txtRemoveEventPage=By.xpath("//legend[text()='Remove Event ']");
	
	public static By txtRemoveSuccessMsg=By.xpath("//p[text()='Event removed!']");
	
	public static By ddEventToRemove=By.xpath("//fieldset/select");
	
	public static By ddConfig=By.xpath("//select[@id='configfiles']");
	
	public static By ddNewConfigFile=By.xpath("//select[@id='configfiles']/option[2]");
	
	public static By btnNext=By.xpath("//input[@id='addConfigBtn']");
	
	public static By txtConfigSuccessMsg=By.xpath("//p[text()='Creation of a new xml file was sucessful.']");
	
	public static By txtCongifName=By.xpath("//input[@type='text']");
	
	public static By btnSave=By.xpath("//input[@value='Save']");
	
	public static By txtEditorInfoSuccessMsg=By.xpath("//p[text()='Editor info saved!']");
	
	public static By lnkDBFields=By.xpath("//ul/li/a[normalize-space()='DB Fields']");
	
	public static By txtBadgeId=By.xpath("//form/table/tbody/tr[2]/td/table/tbody/tr/td/fieldset/table/tbody/tr/td/select/option[text()='BADGEID']");
	
	public static By btnEditorInfoSave=By.xpath("//input[@name='editorInfo']");//Save button at the bottom of DB Fields page
	
	public static By txtDBFieldsSuccessMsg=By.xpath("//p[text()='DBFields have been saved!']");
	
	public static By lnkBadge=By.xpath("//ul/li/a[normalize-space()='Badge']");
	
	public static By ddBadgeLayoutField=By.xpath("//select[@id='currDBFields']/option[text()='BADGEID']");
	
	public static By btnBadgeSave=By.xpath("//input[@id='badgeSave']");
	
	public static By txtBadgeDataSuccessMsg=By.xpath("//p[text()='Badge data saved!']");
	
	public static By btnClear=By.xpath("//a[text()='Clear']");
	
	public static By txtClearEventPage=By.xpath("//legend[text()='Clear Event ']");
	
	public static By ddEventClear=By.xpath("//fieldset/select");
	
	public static By btnClearEvent=By.xpath("//form/table/tbody/tr/td/input[@value='Clear']");
	
	public static By txtClearSuccessMsg=By.xpath("//p[text()='Event cleared!']");
			
	public static By badgeType(String type)
	{
		return By.xpath("//form/table/tbody/tr[2]/td/fieldset/table/tbody/tr/td/select/option[text()='"+type+"']");
	}
	
	public static By txtError=By.xpath("//p[@class='error']");
	
	public static By tabEdit=By.xpath("//a[text()='Edit']");
	
	public static By txtEditPageTitle=By.xpath("//fieldset/legend[text()='Edit Event ']");
	
	public static By btnUpdate=By.xpath("//input[@value='Update']");
	
	public static By txtStartDateErrorMessage(String date)
	{
		return By.xpath("//li[text()='STARTDATE is not in a valid format. Valid formats are (yyyy-mm-dd), (mm/dd/yyyy) or (mm/dd/yy). Got ["+date+"]']");
	}
	
	public static By txtEndDateErrorMessage(String date)
	{
		return By.xpath("//li[text()='ENDDATE is not in a valid format. Valid formats are (yyyy-mm-dd), (mm/dd/yyyy) or (mm/dd/yy). Got ["+date+"]']");
	}
	
	public static By txtExpirationDateErrorMessage(String Date)
	{
		return By.xpath("//li[text()='EVENTEXPIRATIONDATE is not in a valid format. Valid formats are (yyyy-mm-dd), (mm/dd/yyyy) or (mm/dd/yy). Got ["+Date+"]']");
	}
	
	public static By ddIntegrationType=By.xpath("//select[@id='CRMIntType']");
	
	public static By txtURL=By.xpath("//input[@id='CRMIntURL']");
	
	public static By txtCRMUsername=By.xpath("//input[@id='CRMIntUsername']");
	
	public static By txtCRMPassword=By.xpath("//input[@id='CRMIntPassword']");
	
	public static By txtCRMStartTime=By.xpath("//input[@id='CRMIntStartTime']");
	
	public static By txtCRMStopTime=By.xpath("//input[@id='CRMIntStopTime']");
	
	public static By txtEventUpdatedSuccessMessage=By.xpath("//p[text()='Event updated!']");
	
	public static By txtExpirationDate=By.xpath("//input[@id='ExpirationDate']");
	
	public static By txtEmailErrorMessage=By.xpath("//p[@class='error']/following-sibling::ul/li[text()='EMAIL is not a valid e-mail address.']");
	
	public static By txtDefaultDemographicAttributeErrorMessage(String text)
	{
		return By.xpath("//p[@class='error']/following-sibling::ul/li[text()='DEFAULTDEMO must be a number. Got ["+text+"]']");
	}
	
	public static By txtDurationFilterErrorMessage(String text)
	{
		return By.xpath("//p[@class='error']/following-sibling::ul/li[text()='DEFAULTDURATION must be a number. Got ["+text+"]']");
	}
	
	public static By txtTimezoneOffsetErrorMessage(String text)
	{
		return By.xpath("//p[@class='error']/following-sibling::ul/li[text()='TIMEZONEOFFSET must be a number. Got ["+text+"]']");
	}
	
	public static By txtVisitorDisplayStatusErrorMessage=By.xpath("//p[@class='error']/following-sibling::ul/li[starts-with(text(),'VISITORDISPLAY must be a number.')]");
	
	public static By txtVisitorDisplayAddressErrorMessage=By.xpath("//p[@class='error']/following-sibling::ul/li[starts-with(text(),'VISITORDISPLAYADDR must be a number.')]");
	
	public static By txtFirstVisitModeErrorMessage=By.xpath("//p[@class='error']/following-sibling::ul/li[starts-with(text(),'FIRSTVISITMODE must be a number.')]");
	
	public static By txtDefaultDemographicAttribute=By.xpath("//input[@id='DemoAttr']");
	
	public static By txtDurationFilter=By.xpath("//input[@id='DurFilter']");
	
	public static By txtTimezoneOffset=By.xpath("//input[@id='TzOffset']");
	
	public static By txtVisitorDisplayStatus=By.xpath("//input[@id='VisitorDisplay']");
	
	public static By txtVisitorDisplayAddress=By.xpath("//input[@id='VisitorDisplayAddr']");
	
	public static By txtFirstVisitMode=By.xpath("//input[@id='FirstVisitMode']");
	
	public static By txtLoginid=By.xpath("//input[@name='username']");
}
