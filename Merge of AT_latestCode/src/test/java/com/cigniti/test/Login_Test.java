/**
 * 
 */
package com.cigniti.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




/**
 * @author sujit
 * @category Test case
 * @Description verifying functionality of login with valid credentials
 *            
 * @date 24-Apr-2014
 * @Last_Modified
 * @Last_Modified_By
 * @Return_Value Void
 */

public class Login_Test {
	//public Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/Data/TestData.xlsx");
	
	@Test
	public void Login_Test1() {
		
		
		
		TestUtil.getArrayListData("Amazon_TestData");
		
		//System.out.println(aList.get(1).get("Test_Description"));
		
		
	}
}
