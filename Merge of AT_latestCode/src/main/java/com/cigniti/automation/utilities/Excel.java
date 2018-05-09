package com.cigniti.automation.utilities;



import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Excel {
	
	static WritableWorkbook workbook;
	static WritableSheet sheet;
	
	public static String[][] getData(String strXlFilePath, String strSheetName) throws Exception 
    {
        String[][] tableArray=null;
        
        try
        {
        	int ci,cj;
        	Workbook workbook1 = Workbook.getWorkbook(new File(strXlFilePath));              
	        Sheet sheet1 = workbook1.getSheet(strSheetName); ; 
            int intRowCount=sheet1.getRows();
            int intColumnCount=sheet1.getColumns();
            tableArray=new String[intRowCount-1][intColumnCount-1];
            ci=0;

            for (int i=1;i<intRowCount;i++,ci++)
            {
            	cj=0;
                for (int j=1;j<intColumnCount;j++,cj++)
                {
                    tableArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
            workbook1.close();
        }
        catch (Exception e)    
        {
            System.out.println("error in getTableArray()");
            workbook.close();
        }
        return(tableArray);
    }

	
	
	
	public static void writeData(String xlFilePath,String sheetName,String[][] array) throws Exception
	{
		try
		{
		File file=new File(xlFilePath);
		workbook = Workbook.createWorkbook(file);
		sheet = workbook.createSheet(sheetName,0);
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				sheet.addCell(new jxl.write.Label(j,i,array[i][j]));

			}
		}
		  workbook.write();
          workbook.close();
		}
		catch (Exception e) {
			System.out.println(e);
			workbook.write();
	        workbook.close();
		}
	}
	
	
	
	public static void appendData(String filePath,String sheetName,String[][] array) throws Exception
	{
		try
		{
			
			File file=new File(filePath);
		
			if(file.exists()==false)
			{
					workbook = Workbook.createWorkbook(file);
					sheet = workbook.createSheet(sheetName,0); 
					workbook.write();
					workbook.close();
			}
			Workbook workbook1 = Workbook.getWorkbook(file);
			WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
			sheet = copy.getSheet(0);
			int row=sheet.getRows()+1; 
			for(int i=0;i<array.length;i++)
			{
				for(int j=0;j<array[i].length;j++)
				{
					sheet.addCell(new jxl.write.Label(j,i+row+3,array[i][j]));

				}
			}
		
		  copy.write();	
          copy.close();
		}
		catch (Exception e) {
			System.out.println(e);
			 workbook.write();
	         workbook.close();
		}
	}
	
	
	
	public static int usedRows(String strFilePath,String strSheetName) throws Throwable{
		WritableWorkbook workbook;
		WritableSheet sheet;
		File file=new File(strFilePath);
	
		if(file.exists()==false)
		{
				workbook = Workbook.createWorkbook(file);
				sheet = workbook.createSheet(strSheetName,0); 
				workbook.write();
				workbook.close();
		}
		Workbook workbook1 = Workbook.getWorkbook(file);
		WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
		sheet = copy.getSheet(strSheetName); 
		copy.write();
		copy.close();
		return sheet.getRows();
		
	}
	
	
	  // Create cell font and format
	  public static WritableCellFormat getCellFormat(Colour bgColour,Colour fontColour, Pattern pattern,int fontSize, Alignment alignmentType) throws WriteException {
	    
		  WritableFont cellFont = new WritableFont
	    			(WritableFont.TIMES, fontSize, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, fontColour);
		  WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
		  cellFormat.setBackground(bgColour, pattern);
		  cellFormat.setAlignment(alignmentType);
		  return cellFormat;
	  }
	  
	  
	  

		public static void WriteToCell(String strFilePath,String strSheetName,String strValue,int intColumn,int intRow,Colour bgColour,int intFontSize,Pattern pattern) throws Throwable
		{
			try
			{
				
				File file=new File(strFilePath);
			
				if(file.exists()==false)
				{
						workbook = Workbook.createWorkbook(file);
						sheet = workbook.getSheet(strSheetName); 
						workbook.write();
						workbook.close();
				}
				Workbook workbook1 = Workbook.getWorkbook(file);
				WritableWorkbook copy = Workbook.createWorkbook(file, workbook1);
				sheet = copy.getSheet(strSheetName);
						sheet.addCell(new jxl.write.Label(intColumn,intRow,strValue,
								getCellFormat(bgColour, Colour.BLACK, pattern, intFontSize, Alignment.CENTRE)));	
			  copy.write();	
	          copy.close();
			}
			catch (Exception e) {
				System.out.println(e);
				 workbook.write();
		         workbook.close();
			}
	
		}
		public static String getCellData(String strFilePath, String strSheetName,int intRow,int intCol) throws Exception //
		{	  
			String strCellData = null;
		    try
		    {
		    	Workbook workbook1 = Workbook.getWorkbook(new File(strFilePath));              
		        Sheet sheet1 = workbook1.getSheet(strSheetName); 
		        strCellData=sheet1.getCell(intRow,intCol).getContents();
		        workbook1.close();
		    }
		    catch (Exception e)    
		    {
		        System.out.println(e+" : error while getCellData");
		        workbook.close();
		    }
			return strCellData;
		}
		
}
