package com.cigniti.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelReader

{
	FileInputStream inpFile;
	File file;
	HSSFWorkbook hssfWorkbook;
	XSSFWorkbook xssfWorkbook;
	static Sheet sheet = null;
	String workbookName;
	String sheetName;
	int sheetNo;
	static int rows=0;
	int[] dataCount;


	HashMap<String, Integer> colIndex = new HashMap<String, Integer>();

	public ExcelReader(String workbookName, String sheetName, int sheetNo) {
		this.workbookName = workbookName;
		this.sheetName = sheetName;
		this.sheetNo = sheetNo;

	}

	/*public static void main(String[] args) {

		ExcelReader ereader = new ExcelReader("C:\\ravali\\framework\\AT_iConnect\\TestData\\TestData.xls", "EMTReg",3);
		System.out.println("Hi");
		ereader.getLastRow();
		//ereader.getColData("Assert Text Present", 1);
	}*/

	public void readData() {

		try {

			inpFile = new FileInputStream(workbookName);
			file = new File(workbookName);

			if (file.getCanonicalPath().endsWith(".xls")) {
				/*
				 * Get the workbook instance for XLS file. Get sheet from the
				 * workbook
				 */

				hssfWorkbook = new HSSFWorkbook(inpFile);
				sheet = hssfWorkbook.getSheet(sheetName);

			} else if (file.getCanonicalPath().endsWith(".xlsx")) {
				/*
				 * Get the workbook instance for XLSX file. Get sheet from the
				 * workbook
				 */
				xssfWorkbook = new XSSFWorkbook(inpFile);
				sheet = xssfWorkbook.getSheetAt(sheetNo);

			}

		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getRowData(int rowno)
	{
		readData();	
		Row row = null;
		Cell cell = null;
		List<String> rowData = new ArrayList();
		//int colNo = 0 ;
		int noOfRows=sheet.getPhysicalNumberOfRows();
		System.out.println(noOfRows);
		for (int i = 0; i < noOfRows; i++) {
			if(i==rowno)
			{
				int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
				for(int c=0;c<(noOfColumns-1);c++)
				{
					row = sheet.getRow(i);
					cell = row.getCell(c);
				
					rowData.add(cell.toString());
				
					System.out.println(rowData.get(c));
				}
				return rowData;
			}
			/*else
			{
				System.out.println("No row with number: "+rowno);
				break;
			}*/
			
		}
		return rowData;
	}

	public String getColData(String colName, int rowno) {

		readData();	
		Row row = null;
		Cell cell = null;
		List colData = new ArrayList();
		int colNo = 0 ;
		int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		//System.out.println("Index is "+noOfColumns);
		//System.out.println(getLastRow());
		for(int i=0;i<noOfColumns;i++)
		{
			row = sheet.getRow(0);

			cell = row.getCell(i);

			if(cell.toString().equalsIgnoreCase(colName))
			{
				colNo=i;
				break;
			}
		}

		try {

			row = sheet.getRow(rowno);

			cell = row.getCell(colNo);
			if (cell != null) {

				//System.out.println(cell.getStringCellValue());
				return cell.getStringCellValue();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cell.getStringCellValue();
	}

	public int getLastRow()

	{
		readData();
		//System.out.println(sheet.getLastRowNum());
		rows=sheet.getPhysicalNumberOfRows();
		return rows;
		//return sheet.getLastRowNum();
	}
	
	public int getLastColumn()
	{
		readData();
		int noOfColumns=sheet.getRow(0).getPhysicalNumberOfCells();
		return noOfColumns;
	}

	public int[] getRowCountByCol(){
		readData();
		String data;

		try {
			/*InputStream is = new FileInputStream("TestData//Test.xls");
	        Workbook wb = WorkbookFactory.create(is);
	        Sheet sheet = wb.getSheetAt(0);*/
			Iterator rowIter = sheet.rowIterator();
			Row r = (Row)rowIter.next();
			short lastCellNum = r.getLastCellNum();
			dataCount = new int[lastCellNum];
			int col = 0;
			rowIter = sheet.rowIterator();
			while(rowIter.hasNext()) {
				Iterator cellIter = ((Row)rowIter.next()).cellIterator();
				while(cellIter.hasNext()) {
					Cell cell = (Cell)cellIter.next();
					col = cell.getColumnIndex();
					dataCount[col] += 1;
					DataFormatter df = new DataFormatter();
					data = df.formatCellValue(cell);

					// System.out.println("Data: " + data);
				}
			}
		}

		catch(Exception e) {
			e.printStackTrace();
			return dataCount;
		}

		return dataCount;
	}



}