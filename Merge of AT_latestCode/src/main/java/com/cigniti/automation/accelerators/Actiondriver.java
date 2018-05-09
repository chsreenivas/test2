package com.cigniti.automation.accelerators;

import org.openqa.selenium.Alert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cigniti.automation.utilities.Reporters;

/**
 * All customized/ Generic functions
 * @author Cigniti
 */
public class Actiondriver extends Base {

	public static WebDriverWait wait;

	/**
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 * @return --boolean (true or false)
	 * @throws Throwable 
	 */
	public static boolean click(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).click();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = true;
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("Click "+locatorName, "Unable to click on :" + locatorName);

			} 
			else
			{
				Reporters.SuccessReport("Click "+locatorName, "Able to click on :" + locatorName);

			}
		}
	}

	/**
	 * This method returns check existence of element
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * @param locatorName : Meaningful name to the element (Ex:Textbox, checkbox etc)
	 * @return: Boolean value(True or False)
	 * @throws NoSuchElementException
	 */
	public static boolean isElementPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = true;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			flag=false;
		} finally {
			if (flag) {
				Reporters.SuccessReport("check IsElementPresent", locatorName +" Is Present on the Page");
			} else {
				//Reporters.failureReport("check IsElementPresent", locatorName + " Element is not present on the page");
			}
		}
		return flag;

	}

	/**
	 * This method used type value in to text box or text area
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param testdata: Value wish to type in text box / text area
	 *  
	 * @param locatorName: Meaningful name to the element (Ex:Textbox,Text Area etc..)
	 * 
	 * @throws NoSuchElementException
	 */
	public static boolean type(By locator, String testdata, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(testdata);
			
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Reporters.SuccessReport("Type ","Data typing action is performed on:" + locatorName+" with data is "+testdata);

			} else {
				Reporters.failureReport("Type ",			
						"Data typing action is not performed on: " + locatorName+" with data "+testdata);

			}
		}
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:link,menus  etc..)
	 * 
	 */
	public static boolean mouseover(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			new Actions(driver).moveToElement(mo).build().perform();

		} catch (NoSuchElementException e) {

			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("MouseOver",
						"MouseOver action is not perform on:" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				//				Reporters.SuccessReport("MouseOver",
				//						"MouserOver Action is Done on:" + locatorName);
			}
		}
		return flag;

	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves by a given offset, then releases the mouse.
	 * 
	 * @param source: Element to emulate button down at.
	 * 
	 * @param xOffset: Horizontal move offset.
	 * 
	 * @param yOffset: Vertical move offset.
	 * 
	 */
	public static boolean draggable(By source, int x, int y, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {

			WebElement dragitem = driver.findElement(source);

			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();
			Thread.sleep(3000);
			flag = true;
		} catch (NoSuchElementException e) {

		} finally {
			if (flag) {
				Reporters.SuccessReport("Draggable",
						"Draggable Action is Done on:" + locatorName);

			} else {

				Reporters.failureReport("Draggable",
						"Draggable action is not performed on:" + locatorName);
			}
		}
		return flag;
	}

	/**
	 * A convenience method that performs click-and-hold at the location of the
	 * source element, moves to the location of the target element, then
	 * releases the mouse.
	 * 
	 * @param source: Element to emulate button down at.
	 * 
	 * @param target: Element to move to and release the mouse at.
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Button,image etc..)
	 * 
	 */
	public static void draganddrop(By source, By target, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = driver.findElement(source);
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("DragAndDrop",
						"DragAndDrop action is not performed on:" + locatorName);

			} else {
				/*		Reporters.SuccessReport("DragAndDrop",
						"DragAndDrop Action is Done on:" + locatorName); */
			}
		}
	}


	public static boolean draganddrop(WebElement source, By target, String sourceName,String targetName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement from = source;
			WebElement to = driver.findElement(target);
			new Actions(driver).dragAndDrop(from, to).perform();
			flag=true;
		} catch (Exception e) {
			return false;
		} finally {
			if (!flag) {
				Reporters.failureReport("DragAndDrop",
						"Failed to drag "+sourceName+" to destination" + targetName);
			} else {
				Reporters.SuccessReport("DragAndDrop",
						"Successfully dragged "+sourceName+" to destination" + targetName);
			}
		}
		return flag;
	}

	public static boolean draganddropBy(WebElement source, WebElement target, int xOffset, int yOffset, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			new Actions(driver).clickAndHold(source).dragAndDropBy(target,xOffset,yOffset).release(target).build().perform();
			flag=true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("DragAndDrop",
						"DragAndDrop action is not perform on:" + locatorName);
			} else {
				Reporters.SuccessReport("DragAndDrop",
						"DragAndDrop Action is performed on:" + locatorName);
			}
		}
		return flag;
	}

	/**
	 * To slide an object to some distance
	 * 
	 * @param slider: Action to be performed on element
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 * 
	 */
	public static void slider(By slider, int x,int y,String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement dragitem = driver.findElement(slider);
			//	new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()	.perform();
			new Actions(driver).dragAndDropBy(dragitem, x, y).build().perform();//150,0

			Thread.sleep(5000);
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("Slider",
						"Slider action is not perform on:" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				//				Reporters.SuccessReport("Slider", "Slider Action is Done on:" + locatorName);
			}
		}
	}

	/**
	 * To right click on an element
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 * 
	 * @throws Throwable
	 */

	public static void rightclick(By by, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			WebElement elementToRightClick = driver.findElement(by);
			Actions clicker = new Actions(driver);
			clicker.contextClick(elementToRightClick).perform();
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("RightClick",
						"RightClick action is not perform on:" + locatorName);

			} else {
				//				Reporters.SuccessReport("RightClick", "RightClick Action is Done on:" + locatorName);
			}
		}
	}

	/**
	 * Wait for an element
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *         
	 */

	public static void waitForTitlePresent(By locator) throws Throwable {
		for (int i = 0; i < 200; i++) {
			if (driver.findElements(locator).size()>0) {
				break;
			} else {
				Thread.sleep(50);
			}
			{
				try {
					driver.wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Wait for an ElementPresent
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @return Whether or not the element is displayed
	 */
	public static boolean waitForElementPresent(By locator) throws Throwable {

		boolean flag=true;
		try {
			for (int i = 0; i < 200; i++) {
				if (driver.findElement(locator).isDisplayed()) {
					return true;
				} else {
					Thread.sleep(50);
				}
			}
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if(flag){

			}else{

			}
		}
		return flag;
	}


	/**
	 * This method Click on element and wait for an element
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param waitElement: Element name wish to wait for that (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 */
	public static void clickAndWaitForElementPresent(By locator, By waitElement, String locatorName) throws Throwable {
		click(locator, locatorName);
		waitForElementPresent(waitElement);
	}

	/**
	 * Select a value from Dropdown using send keys
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *           
	 * @param value: Value wish type in dropdown list
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
	 * 
	 */
	public static void selectBySendkeys(By locator, String value, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(locator).sendKeys(value);
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("Select", value+ "is Not Select from the DropDown:" + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else {
				Reporters.SuccessReport("Select", value + " is Selected from the DropDown:" + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *            
	 * @param index: Index of value wish to select from dropdown list.
	 *            
	 * @param locatorName: Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
	 *  
	 */
	public static boolean selectByIndex(By locator, int index, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByIndex(index);
			return true;
		} catch (Exception e) {
			flag = true;
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("Select",
						"Option at index "+index+" is Not Selected from the DropDown:" + locatorName);

			} else {
				Reporters.SuccessReport("Select",
						"Option at index "+index+"is Selected from the DropDown:" + locatorName);

			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *           
	 * @param value: Value wish to select from dropdown list.
	 *            
	 * @param locatorName: Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
	 */

	public static void selectByValue(By locator, String value, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			s.selectByValue(value);
		} catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("Select",
						"Option with value attribute "+value+" is Not Select from the DropDown:" + locatorName);


			} else {
				//Reporters.SuccessReport("Select","Option with value attribute "+value+" is  Selected from the DropDown:" + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *            
	 * @param visibletext: VisibleText wish to select from dropdown list.
	 *            
	 * @param locatorName: Meaningful name to the element (Ex:Year Dropdown, items Listbox etc..)
	 */

	public static boolean selectByVisibleText(By locator, String visibletext,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			Select s = new Select(driver.findElement(locator));
			System.out.println("Size is "+s.getOptions().size());

			for (int i = 0; i < s.getOptions().size(); i++) {
				System.out.println(s.getOptions().get(i).getText());
			}
			s.selectByVisibleText(visibletext);
			return true;
		} catch (Exception e) {
			flag = true;
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("Select '"+visibletext+"'",visibletext+" is not Selected from the DropDown '" + locatorName+"'");

			} else {
				Reporters.SuccessReport("Select '"+visibletext+"'","'"+visibletext+"'  is Selected from the DropDown '" + locatorName+"'");
			}
		}
	}

	/**
	 * SWITCH TO WINDOW BY USING TITLE
	 * 
	 * @param windowTitle: Title of window wish to switch
	 *            
	 * @param count: Selenium launched Window id (integer no)
	 * 
	 * @return: Boolean value(true or false)
	 *            
	 */
	//
	public static Boolean switchWindowByTitle(String windowTitle, int count)
			throws Throwable {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();
			int windowCount = windowList.size();
			//			Calendar calendar = new GregorianCalendar();
			//			int second = calendar.get(Calendar.SECOND); // /to get current time
			//			int timeout = second + 40;
			/*	while (windowCount != count && second < timeout) {
				Thread.sleep(500);
				windowList = driver.getWindowHandles();
				windowCount = windowList.size();

			}*/

			String[] array = windowList.toArray(new String[0]);

			for (int i = 0; i <=windowCount; i++) {

				driver.switchTo().window(array[count-1]);
				//if (driver.getTitle().contains(windowTitle))
				return true;
			}
			return false;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("SelectWindow",
						"The Window with title:" + windowTitle
						+ "is not Selected");

			} else {
				Reporters.SuccessReport("SelectWindow",
						"Focus navigated to the window with title:"
								+ windowTitle);
			}
		}
	}

	/**
	 * Function To get column count and print data in Columns
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *           
	 * @return: Returns no of columns.
	 * 
	 */
	public int getColumncount(By locator) throws Exception {

		WebElement tr = driver.findElement(locator);
		List<WebElement> columns = tr.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;

	}

	/**
	 * Function To get row count and print data in rows
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *           
	 * @return: returns no of rows. 
	 */
	public int getRowCount(By locator) throws Exception {

		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		/*for (WebElement row : rows) {
			System.out.println(row.getText());
		}*/
		return a;
	}

	/**
	 * Verify alert present or not
	 * 
	 * @return:  Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	public boolean Alert() throws Throwable {

		boolean presentFlag = false;
		Alert alert = null;

		try {

			// Check the presence of alert
			alert = driver.switchTo().alert();
			// Alert present; set the flag
			presentFlag = true;
			// if present consume the alert
			alert.accept();

		} catch (NoAlertPresentException ex) {
			// Alert not present
			ex.printStackTrace();
		} finally {
			if (presentFlag) {

			} else {
				Reporters.failureReport("Alert", "There was no alert to handle");
			}
		}

		return presentFlag;
	}

	/**
	 * To launch URL
	 * 
	 * @param url: url value want to launch
	 * 
	 */
	public void launchUrl(String url) {
		driver.navigate().to(url);
	}


	/**
	 * This method verify check box is checked or not
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * 
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 *  
	 */
	public static boolean isChecked(By locator, String locatorName) throws Throwable {
		boolean value = false;
		try {
			if (driver.findElement(locator).isSelected()) {
				value = true;
			}

			return value;
		} catch (Exception e) {
			return value;
		} finally {
			if (value) {
				//Reporters.SuccessReport("IsChecked", locatorName
				//	+ " is Selected");
				// throw new ElementNotFoundException("", "", "");

			} else {
				//Reporters.failureReport("IsChecked", locatorName
				//	+ " is not Select");
			}
		}
	}

	/**
	 * Element is enable or not
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 *            
	 * @param locatorName:  Meaningful name to the element (Ex:Login Button, UserName Textbox etc..)
	 * 
	 * @return: boolean value (True: if the element is enabled, false: if it not enabled).
	 * 
	 */

	public static Boolean isEnabled(By locator, String locatorName) throws Throwable {
		Boolean value = false;
		try {
			if (driver.findElement(locator).isEnabled()) {
				value = true;
			}
			return value;
		} catch (NoSuchElementException e) {
			return value;
		} finally {
			if (value) {
				//	Reporters.SuccessReport("IsEnabled", locatorName
				//			+ ": is Enabled");

			} else {
				Reporters.failureReport("IsEnabled", locatorName
						+ ":is not Enable");
			}
		}
	}

	/**
	 * Element visible or not
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 *            
	 * @return: boolean value(True: if the element is visible, false: If element not visible)
	 * 
	 */

	public static boolean isVisible(By locator, String locatorName) throws Throwable {
		boolean value = false;
		try {

			value = driver.findElement(locator).isDisplayed();
			return value;
		} catch (NoSuchElementException e) {
			return value;
		} finally {
			if (value) {
				Reporters.SuccessReport("IsVisible", locatorName+ ":Element is Visible");

			} else {
				Reporters.failureReport("IsVisible", locatorName+ ":Element is Not Visible");
			}
		}
	}

	/**
	 * Get the CSS value of an element
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, label color etc..)
	 * 
	 * @param cssattribute: CSS attribute name wish to verify the value (id, name, etc..)
	 * 
	 * @return: String CSS value of the element
	 *
	 */

	public String getCssValue(By locator, String cssattribute,
			String locatorName) throws Throwable {
		String value = "";
		if (isElementPresent(locator, "locatorName")) {
			value = driver.findElement(locator).getCssValue(cssattribute);
		}
		return value;
	}

	/**
	 * Check the expected value is available or not
	 * 
	 * @param expvalue: Expected value of attribute
	 *  
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param attribute: Attribute name of element wish to assert 
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:link text, label text etc..)
	 *  
	 */
	public static void assertValue(String expvalue, By locator,
			String attribute, String locatorName) throws Throwable {
		Assert.assertEquals(expvalue,
				getAttribute(locator, attribute, locatorName));
	}

	/**
	 * Check the text is presnt or not
	 * 
	 * @param text: Text wish to assert on the page.
	 * 
	 */
	public void assertTextPresent(String text) throws Throwable {
		Assert.assertTrue(isTextPresent(text));

	}

	/**
	 * Assert element present or not
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:Login Button, SignIn Link etc..)
	 *
	 */
	public static void assertElementPresent(By by, String locatorName)
			throws Throwable {

		Assert.assertTrue(isElementPresent(by, locatorName));

	}



	/**
	 * Assert text on element 
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param text: expected text to assert on the element
	 * 
	 */

	public static void assertText(By by, String text) throws Throwable {

		Assert.assertEquals(getText(by, text).trim(), text.trim());
	}

	/**
	 * Assert text on element 
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param text: expected text to assert on the element
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:link text, label text etc..)
	 * 
	 */
	public static boolean verifyText(By by, String text,String locatorName) throws Throwable {

		boolean flag=true;
		String vtxt=getText(by, locatorName).trim();
		System.out.println(vtxt);
		System.out.println(text);

		if(vtxt.equalsIgnoreCase(text.trim()))
		{
			Reporters.SuccessReport("VerifyText '"+text+"'", "'"+text+"' is present in the location '"+locatorName+"'");
			return flag;
		}
		else
		{
			Reporters.failureReport("VerifyText : "+text,text+" is not present in the location "+locatorName);
			flag=false;
		}
		return flag;
	}

	public static void verifyText1(By by, String text,String locatorName) throws Throwable {

		String a=driver.findElement(by).getText();
		Assert.assertTrue(a.matches(text));
	}
	/**
	 * @return: return title of current page.
	 * 
	 * @throws Throwable
	 */

	public static String getTitle() throws Throwable {

		String text = driver.getTitle();
		//	Reporters1.SuccessReport("Title", "Title of the page is:" + text);

		return text;
	}

	/**
	 * Assert Title of the page.
	 * 
	 * @param title: Expected title of the page.
	 *  
	 */
	public static boolean asserTitle(String title) throws Throwable {

		boolean bRetValue = false;
		try{
			By windowTitle = By.xpath("//title[contains(text(),'"+title+"')]");
			waitForTitlePresent(windowTitle);
			Assert.assertEquals(getTitle(), title);
			bRetValue = true;

		}catch(Exception ex){
			ex.printStackTrace();
		}finally{

			if(bRetValue){
				//	Reporters.SuccessReport("asserTitle"," Page title is verified with "+title);
			}else
				Reporters.failureReport("asserTitle","Page title is not matched with "+title);
		}
		return bRetValue;
	}

	/**
	 * Verify Title of the page.
	 * 
	 * @param title: Expected title of the page.
	 *  
	 */
	public static boolean verifyTitle(String title) throws Throwable {

		if(getTitle().equals(title))
		{
			//	Reporters.SuccessReport("VerifyTitle"," Page title is verified with "+title);
			return true;
		}
		else
		{
			Reporters.failureReport("VerifyTitle","Page title is not matched with "+title);
			return false;
		}

	}

	/**
	 * Verify text present or not
	 * 
	 * @param text: Text wish to verify on the current page.
	 * 
	 */
	public static boolean  verifyTextPresent(String text) throws Throwable
	{  
		boolean flag;
		if((driver.getPageSource()).contains(text))
		{
			flag=true;
			Reporters.SuccessReport("VerifyTextPresent "+text,text+" is present on the page");
			System.out.println(text + " is Available");
		}
		else
		{
			flag=false;
			Reporters.failureReport("VerifyTextPresent "+text,text+" is not present on the page");
		}
		return flag;
	}

	/**
	 * Verify text not present
	 * 
	 * @param text: Text wish to verify on the current page.
	 * 
	 */
	public static boolean  verifyTextNotPresent(String text) throws Throwable
	{  

		if((driver.getPageSource()).contains(text))
		{
			//			Reporters.SuccessReport("VerifyTextPresent",text+" is present in the page");
			//			System.out.println(text + " is Available");
			return false;
		}
		else
		{
			Reporters.SuccessReport("VerifyTextNotPresent",text+" is not present");
		}
		return true;
	}


	/**
	 * Verify the 404 error or broken links
	 * 
	 * @param text: Text expect to display when 404 error / broken link. 
	 *
	 */
	public static boolean verify404(String text) throws Throwable

	{

		if (driver.getPageSource().contains("404")) {
			Reporters.failureReport("Verify404",text+" is present in the page");

			return true;
		}
		else
		{
			//	Reporters.SuccessReport("Verify404",text+" is not present in the page");
			return false;
		}

	}

	/**
	 * Get the value of a the given attribute of the element.
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param attribute: Attribute name wish to assert the value.
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:label, SignIn Link etc..)
	 * 
	 * @return: String attribute value
	 * 
	 */

	public static String getAttribute(By by, String attribute,
			String locatorName) throws Throwable {
		String value = "";
		if (isElementPresent(by, locatorName)) {
			value = driver.findElement(by).getAttribute(attribute);
		}
		return value;
	}

	/**
	 * Text present or not
	 * 
	 * @param text: Text wish to verify on current page 
	 * 
	 * @return: boolean value(true: if Text present, false: if text not present)
	 */

	public static boolean  isTextPresent(String text) throws Throwable {
		boolean value = driver.getPageSource().contains(text);
		if (value) {
			//	Reporters.SuccessReport("IsTextPresent", "'" + text + "'"
			//			+ "is presented in the page");

		} else {
			Reporters.failureReport("IsTextPresent", "'" + text + "'"
					+ "is presented in the page");
			return false;
		}
		return value;
	}

	/**
	 * The innerText of this element.
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:label text, SignIn Link etc..)
	 * 
	 * @return: String return text on element
	 * 
	 */

	public static String getText(By locator, String locatorName)
			throws Throwable {
		String text = "";
		if (isElementPresent(locator, locatorName)) {
			text = driver.findElement(locator).getText();
		}
		return text;
	}

	/**
	 * Capture Screenshot
	 * 
	 * @param fileName: FileName screenshot save in local directory
	 * 
	 */
	public static void screenShot(String fileName) {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Click on the Link
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:SignIn Link, menu's etc..)
	 */


	public static void mouseHoverByJavaScript(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = driver.findElement(locator);
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
		}
		catch (Exception e) {
			flag = true;
		} finally {
			if (flag) {
				Reporters.failureReport("MouseOver",
						"MouseOver action is not perform on:" + locatorName);
			} else {
				//				Reporters.SuccessReport("MouseOver",
				//						"MouserOver Action is Done on:" + locatorName);
			}
		}
	}

	/**
	 * This method switch the focus to selected frame using frame index
	 * 
	 * @param index: Index of frame wish to switch
	 * 
	 */

	public static boolean switchToFrameByIndex(int index) throws Throwable
	{   boolean flag=false;
	try
	{
		driver.switchTo().frame(index);
		flag=true;
	}
	catch(Exception e)
	{
		flag=false;
	}
	finally
	{
		if(flag)
		{
			Reporters.SuccessReport("SelectFrame","Frame with index "+index+"is selected");
		}
		else
		{
			Reporters.failureReport("SelectFrame","Frame with index "+index+"is not selected");
		}
		return flag;
	}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue: Frame ID wish to switch
	 * 
	 */
	public static void switchToFrameById(String idValue)throws Throwable
	{   boolean flag=false;
	try
	{
		driver.switchTo().frame(idValue);
		flag=true;
	}
	catch(Exception e)
	{

	}
	finally
	{
		if(flag)
		{
			//			Reporters.SuccessReport("SelectFrame",
			//					"Frame with Id "+idValue+"is selected");
		}
		else
		{
			Reporters.failureReport("SelectFrame",
					"Frame with Id "+idValue+"is not selected");
		}
	}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue: Frame Name wish to switch
	 * 
	 */
	public static void switchToFrameByName(String nameValue)throws Throwable
	{   boolean flag=false;
	try
	{
		driver.switchTo().frame(nameValue);
		flag=true;
	}
	catch(Exception e)
	{

	}
	finally
	{
		if(flag)
		{
			//			Reporters.SuccessReport("SelectFrame",
			//					"Frame with Name "+nameValue+"is selected");
		}
		else
		{
			Reporters.failureReport("SelectFrame",
					"Frame with Name "+nameValue+"is not selected");
		}
	}
	}


	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue: Frame Name wish to switch
	 * 
	 * @param locatorName :   Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * 
	 * 
	 */
	public static void switchToFrameByLocator(By locacator,String locatorName)throws Throwable
	{   boolean flag=false;
	try
	{
		driver.switchTo().frame(driver.findElement(locacator));
		flag=true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if(flag)
		{
			//			Reporters.SuccessReport("SelectFrame",
			//					"Frame with Name "+nameValue+"is selected");
		}
		else
		{
			Reporters.failureReport("SelectFrame",
					"The Frame "+locatorName+"is not selected");
		}
	}
	}

	/**
	 * This method wait selenium until element present on web page.
	 */
	public static void ImplicitWait(){

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}





	/**
	 * Click on Element
	 * 
	 * @param locator: Action to be performed on element (Get it from Object repository)
	 * 
	 * @param locatorName: Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * 
	 * @throws StaleElementReferenceException - If the element no longer exists as initially defined
	 */

	public static boolean click1(WebElement locator,String locatorName ) throws Throwable
	{
		boolean flag=false; 
		try {
			locator.click();

			return true;
		} catch (Exception e) {
			flag=true;
			System.out.println(e.getMessage());
			return false;
		}finally {
			if (flag) {
				Reporters.failureReport("Click ", "Unable to click on :" + locatorName);

			} 
			else
			{
				//	Reporters.SuccessReport("Click ", "able to click on :" + locatorName);

			}
		}

	}	

	/**
	 * 
	 *  This method wait driver until given driver time.
	 *  
	 */
	public static WebDriverWait driverwait(){

		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait;
	}

	/**
	 *  This method waits for the element to be visible till 20 seconds.
	 *  
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * @param LocatorName : Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * @return true on success else false
	 * @throws Throwable 
	 * 
	 */
	public static boolean waitForVisibilityOfElement(By by,String LocatorName) throws Throwable{

		WebDriverWait wait = new WebDriverWait(driver, 20);
		boolean flag=true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			flag=false;
		}
		finally{
			if(flag){
				Reporters.SuccessReport("WaitForVisibility of Element "+LocatorName, LocatorName+" Element is found");
			}
			else{
				Reporters.failureReport("WaitForVisibility of Element "+LocatorName, LocatorName+" Element is not found");
			}
		}
		return flag;
	}

	/**
	 *  This method waits for the element to be invisible till 20 seconds.
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * @param LocatorName : Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * @return true on success else false
	 * @throws Throwable 
	 * 
	 */
	public static boolean waitForInVisibilityOfElement(By by, String LocatorName) throws Throwable{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		boolean flag=true;
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if(flag){
				Reporters.SuccessReport("WaitForInVisibility of Element "+LocatorName, LocatorName+" Element is inVisible");
			}
			else{
				Reporters.failureReport("WaitForInVisibility of Element "+LocatorName, LocatorName+" Element is not inVisible");
			}
		}
	}

	/**
	 *  This method waits for the frame to load till 20 seconds and switches to it
	 * 
	 * @param by: Action to be performed on element (Get it from Object repository)
	 * @param LocatorName : Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * @return true on success else false
	 * @throws Throwable 
	 * 
	 */
	public static boolean waitForFrameToLoadAndSwitchToIt(By by, String LocatorName) throws Throwable{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		boolean flag=true;
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if(flag){
				Reporters.SuccessReport("WaitForFrameToBeAvailable and Switch to It "+LocatorName, LocatorName+" is found and successfully switched to it");
			}
			else{
				Reporters.failureReport("WaitForFrameToBeAvailable and Switch to It "+LocatorName, LocatorName+" is not found and unable to switch to it");
			}
		}
	}

	/**
	 * This method clicks on element using java script executor
	 * @param by: Click Action to be performed on element (Get it from Object repository)
	 * @param LocatorName : Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * @return true on success else false
	 * @throws Throwable
	 */
	public static boolean js_click(By by,String LocatorName) throws Throwable{
		boolean flag=true;
		try {
			WebElement elementToClick=driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if (flag) {
				Reporters.SuccessReport("Click "+LocatorName, "Able to click on :" + LocatorName);
			} 
			else
			{
				Reporters.failureReport("Click "+LocatorName, "Unable to click on :" + LocatorName);
			}
		}
	}

	/**
	 * This method is used to trigger onclick event using Java script executor 
	 * @param onClick_methodName : Name of the method that this element has it in 'onclick' attribute
	 * <input class="button-small" type="button" onclick="addNewRecord();" name="add" value="Add">
	 * @param LocatorName : Meaningful name to the element (Ex:SignIn Link, login button etc..)
	 * @return true on success else false
	 * @throws Throwable
	 */
	public static boolean js_TriggerOnClickEvent(String onClick_methodName,String LocatorName) throws Throwable{
		boolean flag=true;
		try {
			//((JavascriptExecutor) driver).executeScript("addNewRecord();");
			((JavascriptExecutor) driver).executeScript(""+onClick_methodName+"");
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if (flag) {
				Reporters.SuccessReport("Click "+LocatorName, "Able to click on :" + LocatorName);
			} 
			else
			{
				Reporters.failureReport("Click "+LocatorName, "Unable to click on :" + LocatorName);
			}
		}
	}


	public static boolean sendEnter(By by){
		try {
			//driver.findElement(by).sendKeys(Keys.ENTER);
			new Actions(driver).sendKeys(Keys.ENTER).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
		finally{
			/*if (flag) {
				Reporters.SuccessReport("Click "+LocatorName, "Able to click on :" + LocatorName);
			} 
			else
			{
				Reporters.failureReport("Click "+LocatorName, "Unable to click on :" + LocatorName);
			}*/
		}

	}

	public static boolean isElementPresentInDOM(By by){
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(by);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return false;
		}
		finally{

		}

	}

	public static boolean verifyTextWithOutFailReport(By by, String text,String locatorName) throws Throwable {

		boolean flag=true;
		String vtxt=getText(by, locatorName).trim();

		if(vtxt.equalsIgnoreCase(text.trim()))
		{
			Reporters.SuccessReport("VerifyText '"+text+"'", "'"+text+"' is present in the location '"+locatorName+"'");
			return flag;
		}
		else
		{
			//Reporters.failureReport("VerifyText : "+text,text+" is not present in the location "+locatorName);
			flag=false;
		}
		return flag;
	}


	public static boolean click(WebElement wb, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			wb.click();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			flag = true;
			return false;
		} finally {
			if (flag) {
				Reporters.failureReport("Click "+locatorName, "Unable to click on :" + locatorName);

			} 
			else
			{
				Reporters.SuccessReport("Click "+locatorName, "Able to click on :" + locatorName);

			}
		}
	}


	public static boolean js_type(By by,String Text, String LocatorName) throws Throwable{
		boolean flag=true;
		try {
			WebElement location=driver.findElement(by);
			//((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementToClick);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+Text+"'", location);
			return true;
		} catch (Exception e) {
			flag=false;
			return false;
		}
		finally{
			if (flag) {
				Reporters.SuccessReport("Type Data into "+LocatorName, "Able to Type Data into :" + LocatorName);
			} 
			else
			{
				Reporters.failureReport("Type Data into "+LocatorName, "Able to Type Data into :" + LocatorName);
			}
		}
	}


	public static String js_getText(By by, String LocatorName) throws Throwable{

		String labelNodeText = "";
		if(isElementPresent(by, LocatorName)){
			labelNodeText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML",driver.findElement(by));
		}
		return labelNodeText;

	}

	public static boolean isElementNotPresent(By by, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			flag=true;
		} finally {
			if (flag) {
				Reporters.SuccessReport("check IsElementPresent", locatorName +" Is Present on the Page");
			} else {
				//Reporters.failureReport("check IsElementPresent", locatorName + " Element is not present on the page");
			}
		}
		return flag;

	}

}
