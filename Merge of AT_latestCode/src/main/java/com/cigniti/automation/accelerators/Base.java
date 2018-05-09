package com.cigniti.automation.accelerators;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.alliancetech.businessfunctions.BusinessFunctions;
import com.alliancetech.objectrepository.EMT_LogInOR;
import com.cigniti.automation.utilities.Accessories;
import com.cigniti.automation.utilities.HtmlReporters;
import com.cigniti.automation.utilities.MyListener;
import com.cigniti.automation.utilities.Property;
import com.cigniti.automation.utilities.Reporters;
import com.cigniti.automation.utilities.SendMail;

public class Base 
{
	public static Property configProps=new Property("config.properties");
	public static String currentSuite="";
	public static String method="";
	public static String timeStamp=Accessories.timeStamp().replace(" ","_").replace(":","_").replace(".", "_");
	public static boolean flag =false;
	public static WebDriver webDriver;
	public static AppiumDriver driverM;
	public static EventFiringWebDriver driver;
	public static MyListener myListener;
	public static FirefoxProfile profile;
	public static String result=null;
	public static String mesg=null;
	public static String TESTCASENAME="";
	public static String url = "jdbc:mysql://172.16.6.121/";
	public static String dbName = "test";
	public static String userName = "root";
	public static Connection conn = null;
	public static Statement stmt = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	public static int count=1;
	public static int x=0;
	public static boolean emtLogout=false;
	public static String browser=null;
	static int len=0;
	static int i=0;
	static int j=0;
	static	 ITestContext itc;
	public static Actions builder;
	public static String ReportFileName="";
	public static String SummaryReportFileName="";

	/**
	 *Initializing browser requirements, Test Results file path and Database requirements from the configuration file
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */

	@BeforeSuite(alwaysRun = true) 


	public static void setupSuite(ITestContext ctx) throws Throwable {
		itc=ctx;
		Accessories.calculateSuiteStartTime();

		browser=configProps.getProperty("browserType");
		//browser = System.getProperty("BrowserType-Maven");
		System.out.println("My browser name is "+browser);

		File DestinationY=new File(System.getProperty("user.dir").replace(File.separator, "/")+"//Results//Jenkins");
		FileUtils.cleanDirectory(DestinationY);

		if (!(browser==null)) {
			if (browser.equalsIgnoreCase("firefox")) {
				/*ProfilesIni p = new ProfilesIni();
				profile= p.getProfile("default");
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.dir", new File("Downloads").getAbsolutePath());
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/csv,application/vnd.ms-excel,application/pdf");
				//disable Firefox's built-in PDF viewer
				profile.setPreference("pdfjs.disabled", true);
				//webDriver = new FirefoxDriver(profile);
				webDriver = new FirefoxDriver(profile);*/
				System.setProperty("webdriver.gecko.driver", "/Users/alliancetech/Downloads/Gecko/geckodriver");
				webDriver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("ie")) {
				File file = new File("Drivers/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver",
						file.getAbsolutePath());
				webDriver = new InternetExplorerDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "/Users/alliancetech/Downloads/chromedriver 2");
				
				
				webDriver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("safari")) {
				webDriver = new SafariDriver();
			}
		}
		else{
			if(configProps.getProperty("browserType").equalsIgnoreCase("firefox"))
			{
				//ProfilesIni p = new ProfilesIni();
				//FirefoxProfile profile= p.getProfile("default");
				//FirefoxProfile profile = new FirefoxProfile();
				//profile.setPreference("browser.download.folderList", 2);
				//profile.setPreference("browser.download.dir", new File("Downloads").getAbsolutePath());
				//				profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv,application/csv,application/vnd.ms-excel,application/pdf");
				//profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document,image/jpeg");
				//profile.setPreference("pdfjs.disabled", true);
				//webDriver = new FirefoxDriver(profile);
				System.setProperty("webdriver.gecko.driver", "/Users/alliancetech/Downloads/Gecko/geckodriver");
				webDriver = new FirefoxDriver();
			}
			else if(configProps.getProperty("browserType").equalsIgnoreCase("chrome"))
			{
				//System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "/Users/alliancetech/Desktop/selenium-java-3.4.0/chromedriver");
				
				webDriver = new ChromeDriver();
			}
			else
			{
				System.setProperty("webdriver.ie.driver","Drivers\\IEDriverServer.exe");
				webDriver = new InternetExplorerDriver();
			}

		}


		driver = new EventFiringWebDriver(webDriver);
		builder= new Actions(driver);
		if (configProps.getProperty("HighlightElements").equalsIgnoreCase("true")) {
			myListener = new MyListener();
			driver.register(myListener);
		}
		try 
		{
			//driver.get(configProps.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			Reporters.reportCreater();
			HtmlReporters.currentSuit = ctx.getCurrentXmlTest().getSuite().getName();
		}
		catch (Exception e1)
		{
			System.out.println(e1);
		}
		/*
		 * DB start	
		 */
		/*try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			conn = DriverManager.getConnection(url + "test", userName, "");

			stmt = conn.createStatement();

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}*/

	}


	/**
	 *  De-Initializing and closing all the connections
	 * @throws Throwable
	 */

	@AfterSuite(alwaysRun = true) 
	public void tearDown(ITestContext ctx) throws Throwable 
	{
		Accessories.calculateSuiteExecutionTime();
		HtmlReporters.createHtmlSummaryReport();
		//driver.quit();

		if(configProps.getProperty("MailTransfer").equalsIgnoreCase("True"))
		{
			SendMail.attachReportsToEmail();
		}

		System.out.println(ctx.getFailedConfigurations());
		/*
		 * DB end	
		 */
		if (conn != null && !conn.isClosed())
			conn.close();

		/*if(configProps.getProperty("ReRunFailureTestCases").equalsIgnoreCase("True"))
		{

		//Rerun
			Test.copyFile();
			BatchExecute btexe=new BatchExecute();

			btexe.runFailure();

		}*/

		if (!(browser==null)){

			try {
				SummaryReportFileName=ReportFileName.replaceAll("Results", "SummaryResults");
				ReportFileName=ReportFileName.replaceAll("Results", browser);
				System.out.println(ReportFileName);
				ReportFileName=ReportFileName.replaceAll(".html", "");
				System.out.println(ReportFileName);
				File sourceX=new File(System.getProperty("user.dir").replace(File.separator, "/")+"\\Results\\"+browser+"\\"+ReportFileName);
				File DestinationX=new File(System.getProperty("user.dir").replace(File.separator, "/")+"\\Results\\Jenkins");
				System.out.println(sourceX.getAbsolutePath()+"    "+DestinationX.getAbsolutePath());
				System.out.println(sourceX.getParentFile());
				copyFolder(sourceX, DestinationX);
				File SummaryRes=new File(DestinationX.getAbsolutePath()+"/"+SummaryReportFileName);
				SummaryRes.renameTo(new File(DestinationX.getAbsolutePath()+"\\index.html"));
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		driver.quit();

	}

	/**
	 * Write results to Browser specific path
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	//@Parameters({"browserType"})
	public static String filePath()
	{
		String strDirectoy="";
		if(configProps.getProperty("browserType").equalsIgnoreCase("ie"))
		{
			strDirectoy="IE\\IE";	

		}
		else if(configProps.getProperty("browserType").equalsIgnoreCase("firefox"))
		{
			strDirectoy="Firefox\\Firefox";

		}
		else
		{
			strDirectoy="Chrome\\Chrome";

		}

		if(strDirectoy!="")
		{
			new File(configProps.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp).mkdirs();
		}

		return configProps.getProperty("screenShotPath")+strDirectoy+"_"+timeStamp+"\\";

	}
	/**
	 * Browser type prefix for Run ID
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static String result_browser()
	{
		if(configProps.getProperty("browserType").equals("ie"))
		{
			return "IE";
		}
		else if(configProps.getProperty("browserType").equals("firefox"))
		{
			return "Firefox";
		}
		else
		{
			return "Chrome";
		}
	}
	/**
	 * Related to Xpath
	 * @Date  19/02/2013
	 * @Revision History
	 * 
	 */
	public static String methodName()
	{
		if(configProps.getProperty("browserType").equals("ie"))
		{
			return "post";
		}
		else
		{
			return "POST";
		}
	}

	public static void copyFolder(File src, File dest)
			throws IOException{

		if(src.isDirectory()){

			//if directory not exists, create it
			if(!dest.exists()){
				dest.mkdir();
				System.out.println("Directory copied from " 
						+ src + "  to " + dest);
			}

			//list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile,destFile);
			}

		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest); 

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes 
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}

	/*@AfterMethod()
	public void TestLink() throws Throwable
	{

		Accessories.calculateTestCaseExecutionTime();
		TestClass.reportTestCaseResult(PROJECTNAME, TESTPLANNAME,SmokeTest.TESTCASENAME, BUILDNAME, SmokeTest.mesg, SmokeTest.result);
		SmokeTest.TESTCASENAME="";
		SmokeTest.mesg=null;
		SmokeTest.result=null;
	}*/
	@AfterMethod(alwaysRun=true)

	public static void TestLink() throws Throwable
	{
		Accessories.calculateTestCaseExecutionTime();
	}


	@BeforeMethod(alwaysRun=true)
	public void reportHeader(Method method) throws IOException{
		//Accessories.calculateSuiteStartTime();
		flag=false;
		HtmlReporters.tc_name=method.getName().toString();
		String[] ts_Name=this.getClass().getName().toString().split("\\.");
		HtmlReporters.packageName=ts_Name[0]+"."+ts_Name[1]+"."+ts_Name[2];
		HtmlReporters.testHeader(ts_Name[ts_Name.length-2].concat(" : ").concat(HtmlReporters.tc_name));
	}

	@AfterClass(alwaysRun=true)
	public void logout() throws Throwable{

		if(emtLogout)
		{
			if(BusinessFunctions.emtLogOut())

			{
				Reporters.SuccessReport("Log out from Application", "Application logged out successfully");
			}
			else{
				Reporters.failureReport("Log out from Application", "Application failed to log out");
			}
			Actiondriver.waitForElementPresent(EMT_LogInOR.txtusername);
			if(Actiondriver.verifyTextPresent("Forgot your password?")){
				Reporters.SuccessReport("Verify Log Out", "User has been Logged Out Successfully From EMT");
			}
			else{
				Reporters.failureReport("Verify Log Out", "Verification has been failed");
			} 
			emtLogout=false;
		}
	}

	@AfterTest
	public void keyNull(){

		try {
			builder.sendKeys(Keys.NULL).perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}