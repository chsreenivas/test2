package com.alliancetech.objectrepository;

import org.openqa.selenium.By;


public class SurveyOR {
  
	public static By targetPosition=By.xpath("//div[@class='page new-page ui-sortable']");
	
	public static By lastPosition=By.xpath("//div[@id='survey']/div[last()]/a[text()='+ Add A New Page']");
	
	public static By btnEdit=By.xpath("//div[text()='Edit']");
	
	public static By txtSurveyQuestion=By.xpath("//input[@class='question-text']");
	
	public static By btnAddForSurveyAnswer=By.xpath("//label[text()='Row Choices']/following-sibling::table//tr[last()]//div[@class='add-row']");
	
	public static By txtNewSurveyAnswer=By.xpath("//label[text()='Row Choices']/following-sibling::table//tr[last()]//input[@class='text']");
	
	public static By btnAddRatingsAnswer=By.xpath("//label[text()='Column Choices (Ratings)']/following-sibling::table//tr[last()]//td/div[@class='add-row']");
			
	public static By txtNewRating=By.xpath("//label[text()='Column Choices (Ratings)']/following-sibling::table//tr[last()]//td/input[@class='text']");
	
	public static By btnSaveQuestion=By.xpath("//div[text()='Save Question']");
			
	public static By btnSubmit=By.xpath("//input[@type='submit']");
	
	public static By lnkCancel=By.xpath("//a[@class='cancel-new-user']");
	
	public static By txtEmail=By.id("email");
	
	public static By btnLookMeUp=By.xpath("//input[@type='submit']");
	
	public static By btnAddSurveys=By.xpath("//a[text()='Add Surveys']");
	
	public static By txtAddSessionSsurvey=By.xpath("//h3[text()='Add Session Surveys']");
	
	public static By txtSearchBox=By.xpath("//input[@type='text']");
	
	public static By btnSearch=By.xpath("//a[text()='Search']");
	
	public static By txtSessionInSearchResults(String SessionID,String SessionTitle)
	{
		return By.xpath("//td/h4[text()='"+SessionID+" "+SessionTitle+"']");
	}
	
	public static By btnTakeSurvey=By.xpath("//ul[@class='session-survey-list']//a[text()='Take Survey']");
	
	public static By btnEditSurvey=By.xpath("//a[text()='Edit Survey']");
	
	public static By rdYes=By.xpath("//label[text()='Yes']/preceding-sibling::input[@type='radio']");
	
	public static By chck=By.xpath("//label[text()='Vanilla']/preceding-sibling::input[@type='checkbox']");
	
	public static By chckBoxMyFamily(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']/following-sibling::td[1]/input[@type='checkbox']");
	}
	
	public static By radioButtonMatrix(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']/following-sibling::td[1]/input[@type='radio']");
	}
	
	public static By txtArea=By.xpath("//li[@class='textbox']/textarea");
	
	public static By btnNext=By.xpath("//a[text()='Next']");
	
	public static By btnFinish=By.xpath("//a[text()='Finish']");
	
	public static By lnkMySessionEvaluations=By.xpath("//h4[text()='My Session Evaluations']");
			
	public static By tickMark(String SessionID,String SessionTitle)
	{
		return By.xpath("//td[h4[text()='"+SessionID+" "+SessionTitle+"']]/following-sibling::td/div[@class='status']");
	}
	
	public static By btnTakeOfASurvey(String SessionID,String SessionTitle)
	{
		System.out.println("//h4[text()='"+SessionID+" "+SessionTitle+"']/parent::td/preceding-sibling::td/a[text()='Take Survey']");
		return By.xpath("//h4[text()='"+SessionID+" "+SessionTitle+"']/parent::td/preceding-sibling::td/a[text()='Take Survey']");
	}
	
	public static By btnEditOfASurvey(String SessionID,String SessionTitle)
	{
		System.out.println("//h4[text()='"+SessionID+" "+SessionTitle+"']/parent::td/preceding-sibling::td/a[text()='Edit Survey']");
		return By.xpath("//h4[text()='"+SessionID+" "+SessionTitle+"']/parent::td/preceding-sibling::td/a[text()='Edit Survey']");
	}
	
	public static By lnkNotYou=By.xpath("//a[text()='Not You?']");
	
	public static By lstSearchResults=By.xpath("//table[@id='viewby-search-result']");
	
	public static By lnkClearSurvey=By.xpath("//span[text()='Clear Survey?']");
	
	public static By btnDeleteRow(String LabelText)
	{
		return By.xpath("//td[text()='"+LabelText+"']//preceding-sibling::td/div[@class='delete-row']");
	}
	
	public static By btnPopUpOK=By.id("popup_ok");
	
	public static By txtRequiredQtnsError=By.xpath("//div[@class='survey-error']");
	
	public static By btnPrevious=By.xpath("//a[@class='survey-button prev']");
	
	public static By chckboxDidNotAttend(String SessionID,String SessionTitle)
	{
		return By.xpath("//td[h4[text()='"+SessionID+" "+SessionTitle+"']]/following-sibling::td/input[@type='checkbox']");
	}
	
	public static By txtInManageSessionsTable(String LabelText)
	{
		return By.xpath("//div[a[text()='"+LabelText+"']]/parent::div/parent::div/following-sibling::div/div/table/tbody/tr/td[text()='Sample']");
	}
	
	public static By btnBrowse=By.xpath("//div[h2[text()='Upload A Survey']]/following-sibling::div/input[@type='file']");
	
	public static By lnkConferenceSession=By.xpath("//h4[text()='Conference Survey based on Registrant characteristic']");
	
	public static By m_btnSessionName(String SessionID, String SessionTitle){
		return By.xpath("//h4[text()='"+SessionID+" "+SessionTitle+"']");
		}
	
	public static By m_btnBacktoList = By.id("back-to-list");
	
	public static By m_btnSearchSessionButton = By.id("search-session-button");
	
	public static By m_btnMenu = By.id("show-menu-button");
	
	public static By txtVanilla =  By.xpath("//label[text()='Vanilla']");
	
	public static By m_txtQualiy =  By.xpath("//h4[text()='Quality of ingredients']");
	
	public static By m_chckExtremelyImp(String label) {
		return By.xpath("//h4[text()='"+label+"']/parent::li/following-sibling::li[1]/input[1]");
	}
	public static By m_chckFamily(String label) {
		return By.xpath("//h4[text()='"+label+"']/parent::li/following-sibling::li[1]/input[1]");
	}
	
	public static By m_chckboxDidNotAttend(String SessionID,String SessionTitle)
	{
		return By.xpath("//tr[td[h4[text()='"+SessionID+" "+SessionTitle+"']]]/following-sibling::tr/td/div/input[@type='checkbox']");
	}
	
	public static By m_txtAddSurveys=By.xpath("//h3[text()='Add Surveys']");
	
	public static By m_btnAddSurveys=By.xpath("//div[@id='search-session-button']");
	
	public static By m_editSurvey=By.xpath("//ul[@class='session-survey-list']/li/table/tbody/tr/td/h4");
	
	public static By m_radioButtonMatrix(String LabelText)
	{
		return By.xpath("//li[h4[text()='"+LabelText+"']]/following-sibling::li[1]/input[@type='radio']");
	}
	
	public static By m_chckBoxMyFamily(String LabelText)
	{
		return By.xpath("//form/ul/li/ul/li[h4[text()='"+LabelText+"']]/following-sibling::li/input[@type='checkbox']");
	}
}

