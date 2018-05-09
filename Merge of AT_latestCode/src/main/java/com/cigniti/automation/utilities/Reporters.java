package com.cigniti.automation.utilities;

import com.cigniti.automation.accelerators.Actiondriver;
import com.cigniti.automation.accelerators.ActiondriverM;
import com.cigniti.automation.accelerators.Base;

public class Reporters extends Base{

	public static Property configProps=new Property("config.properties");
	static String  timeStamp=Accessories.timeStamp().replace(":", "_").replace(".", "_");

	public static void reportCreater() throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));

		switch (intReporterType) {
		case 1:

			break;
		case 2:

			HtmlReporters.htmlCreateReport();
			HtmlReporters.createDetailedReport();

			break;
		default:

			HtmlReporters.htmlCreateReport();
			break;
		}
	}

	public static void SuccessReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			break;
		case 2:
			if(configProps.getProperty("OnSuccessScreenshot").equalsIgnoreCase("True"))
			{
				Actiondriver.screenShot(Base.filePath()+strStepDes.replace(" ", "_")+"_"+Base.timeStamp+".jpeg");
			}
			HtmlReporters.onSuccess(strStepName, strStepDes);

			break;

		default:
			if(configProps.getProperty("OnSuccessScreenshot").equalsIgnoreCase("True"))
			{
				Actiondriver.screenShot(Base.filePath()+strStepDes.replace(" ", "_")+"_"+Base.timeStamp+".jpeg");
			}
			HtmlReporters.onSuccess(strStepName, strStepDes);
			break;
		}
	}	

	public static void failureReport(String strStepName, String strStepDes) throws Throwable{
		int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
		switch (intReporterType) {
		case 1:
			flag= true;
			break;
		case 2:
			Actiondriver.screenShot(Base.filePath()+strStepDes.replaceAll("[^a-zA-Z0-9]+","_")+"_"+Base.timeStamp+".jpeg");
			flag= true;
			HtmlReporters.onFailure(strStepName.replaceAll("[^a-zA-Z0-9]+","_"), strStepDes.replaceAll("[^a-zA-Z0-9]+","_"));
			break;
		default:
			flag =true;
			Actiondriver.screenShot(Base.filePath()+strStepDes.replaceAll("[^a-zA-Z0-9]+","_")+"_"+Base.timeStamp+".jpeg");				
			HtmlReporters.onFailure(strStepName.replaceAll("[^a-zA-Z0-9]+","_"), strStepDes.replaceAll("[^a-zA-Z0-9]+","_"));
			break;
		}
	}
		public static void m_failureReport(String strStepName, String strStepDes) throws Throwable{
			int intReporterType=Integer.parseInt(configProps.getProperty("reportsType"));
			switch (intReporterType) {
			case 1:
				flag= true;
				break;
			case 2:
				Actiondriver.screenShot(Base.filePath()+strStepDes.replaceAll("[^a-zA-Z0-9]+","_")+"_"+Base.timeStamp+".jpeg");
				flag= true;
				HtmlReporters.onFailure(strStepName.replaceAll("[^a-zA-Z0-9]+","_"), strStepDes.replaceAll("[^a-zA-Z0-9]+","_"));
				break;
			default:
				flag =true;
				Actiondriver.screenShot(Base.filePath()+strStepDes.replaceAll("[^a-zA-Z0-9]+","_")+"_"+Base.timeStamp+".jpeg");				
				HtmlReporters.onFailure(strStepName.replaceAll("[^a-zA-Z0-9]+","_"), strStepDes.replaceAll("[^a-zA-Z0-9]+","_"));
				break;
			}

	}
}
