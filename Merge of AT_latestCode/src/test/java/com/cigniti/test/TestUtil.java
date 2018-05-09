package com.cigniti.test;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class TestUtil {
	// true - Y
	// false - N
	public static boolean isTestCaseExecutable(String testCase, Xls_Reader xls){
		
		for(int rNum=2;rNum<=xls.getRowCount("Test Cases");rNum++){
			if(testCase.equals(xls.getCellData("Test Cases", "TCID", rNum))){
				// check runmode
				if(xls.getCellData("Test Cases", "Runmode", rNum).equals("Y"))
					return true;
				else
					return false;
			}
				
		}
		
		return false;
		
	}
	
	
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"/Data/TestData.xlsx");
	synchronized public static Hashtable<String, String> getData(String testCase, String sheetName){
		System.out.println("*************");
		// find the test in xls
		// find number of cols in test
		// number of rows in test
		// put the data in hashtable and put hashtable in object array
		// return object array
		
		int testCaseStartRowNum=0;
		for(int rNum=1;rNum<=xls.getRowCount(sheetName);rNum++){
			if(testCase.equals(xls.getCellData(sheetName, 1, rNum))){
				testCaseStartRowNum = rNum;
				break;
			}
		}
		System.out.println("Test Starts from row -> "+ testCaseStartRowNum);
		
	
		
		int cols=12;
		Hashtable<String,String> table=null;
		
		// print the test data
		
		table=new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				table.put(xls.getCellData(sheetName, cNum, 1),xls.getCellData(sheetName, cNum, testCaseStartRowNum));
				System.out.print(xls.getCellData(sheetName, cNum, testCaseStartRowNum)+" - ");
			}
			

		return table;// dummy
		
		
		
		
	}
	
	static ArrayList<HashMap<String, String>> aList;
	static HashMap<String, String> map;
	
	 public static ArrayList<HashMap<String, String>> getArrayListData(String sheetName){
		System.out.println("*************");
		// find the test in xls
		// find number of cols in test
		// number of rows in test
		// put the data in hashtable and put hashtable in object array
		// return object array
		
		aList = new ArrayList<HashMap<String, String>>();
		
		int rowCount = xls.getRowCount(sheetName);
		int colCount = xls.getColumnCount(sheetName);
		
		System.out.println("Data in (0,1) is :"+xls.getCellData(sheetName, 0, 1));
		System.out.println("Data in (1,1) is :"+xls.getCellData(sheetName, 1, 1));

		
		for (int rowNum = 1; rowNum < rowCount; rowNum++) {
			map = new HashMap<String,String>();
			for (int colNum = 0; colNum < colCount; colNum++) {
				map.put(xls.getCellData(sheetName, colNum, 1), xls.getCellData(sheetName, colNum, rowNum+1));
			}
			
			aList.add(map);
		}
		System.out.println(aList.get(0).get("Test_Description"));
		String [] cellValues = aList.get(0).get("Test_Description").split(" ");
		System.out.println(cellValues[0]);
		
		//System.out.println(cellValues[1]);
//		
//		System.out.println("Size of the List is: "+ aList.size());
//		
//		for (String hashMap : aList.get(0).keySet()) {
//			System.out.println(hashMap);
//			
//			for (int i = 0; i < aList.size(); i++) {
//				System.out.println(aList.get(i).get(hashMap));
//			}
//		}
////		
//		for (int i = 0; i < aList.size(); i++) {
//			System.out.println("------------------------------");
//			System.out.println(aList.get(i).get("Test_Description"));
//		}
		
//		for (HashMap<String, String> hashMap : aList) {
//			System.out.println(hashMap.get("TEST_NAME"));
//			System.out.println(hashMap.keySet());
//		}
		
		
		return aList;// dummy
		
		
		
		
	}

	
	
	
	

}
