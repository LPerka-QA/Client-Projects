package com.AdminUser.PagesData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.Utils.ExcelConnection;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;






public class AdminUser_LoginPageData {
	static ExcelConnection testData = null;
	
	static //define an Excel Work Book
	XSSFWorkbook workbook;
	static //define an Excel Work sheet
	XSSFSheet sheet;
	static //define a test result data object
	Map<String, Object[]> testresultdata;
	
	
	public AdminUser_LoginPageData(){
		
		//create a new work book
	    workbook = new XSSFWorkbook();
	    //create a new work sheet
	     sheet = workbook.getSheet("Test Result");
	    testresultdata = new LinkedHashMap<String, Object[]>();
	    //add test result excel file column header
	    //write the header in the first row
	    testresultdata.put("1", new Object[] {"Test Step Id", "Action", "Expected Result","Actual Result", "Screenshot"});
		
		
	}
	
	
	
	public AdminUser_LoginPageData(String ExcelPath, String sheetname, String WritalPath) {
		testData = new ExcelConnection(ExcelPath, sheetname, WritalPath);
		testData.columnDictionary();

	}

	public String Getdata(String FieldName, int row) {

		return testData.readCell(testData.GetCell(FieldName), row).trim();

	}

	public int GetRows() {

		return testData.rowcount();
	}

	public void setData(String FieldName, int row, String TexttoEnter) throws BiffException, IOException {

		testData.setValueintocell(FieldName, row, TexttoEnter);

	}
	
	
	public void writeData(String column, String StepNo, String Action, String ExpectedResult, String ActualResult) throws BiffException, IOException {
			String Screenshot = System.getProperty("user.dir")+ "\\Resources\\Screenshots";
		hyperlinkScreenshot(Screenshot);
    testresultdata.put(column, new Object[] {StepNo, Action, ExpectedResult, ActualResult, Screenshot});
    
	}
	
	public void CloseWriteExcel() throws WriteException, IOException {
				testData.CloseWritExcel();
	}

	//hyperlink screenshot
		public static void hyperlinkScreenshot(String Screenshot){				 
	        
	        
		  //write excel file and file name is TestResult.xls 
		    Set<String> keyset = testresultdata.keySet();
		    int rownum = 0;
		    for (String key : keyset) {
		        Row row = sheet.createRow(rownum++);
		        Object [] objArr = testresultdata.get(key);
		        int cellnum = 0;
		        for (Object obj : objArr) {
		            Cell cell = (XSSFCell) row.createCell(cellnum++);
		            if(obj instanceof Date) 
		                cell.setCellValue((Date)obj);
		            else if(obj instanceof Boolean)
		                cell.setCellValue((Boolean)obj);
		            else if(obj instanceof String)
		                cell.setCellValue((String)obj);
		            else if(obj instanceof Double)
		                cell.setCellValue((Double)obj);
		        }
		        
		        try {
		        	FileOutputStream out =new FileOutputStream(new File(System.getProperty("user.dir") + "\\Resources\\TestData\\TestResult.xlsx"));
		        	workbook.write(out);
			        out.close();
			        System.out.println("Excel written successfully..");
			         
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
		        

				//creating workbook instance that refers to .xls file
		        //HSSFWorkbook wb=new HSSFWorkbook();
				
				//creating a Sheet object
		        XSSFSheet sheet = workbook.getSheet("Test Result");
		        
		        //get all rows in the sheet
		        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();	    
		        
		        //iterate over all the rows in Excel and put data in the form.
		        for(int i=1;i<=rowCount;i++) {
		        	
		        	//create a new cell in the row at index 5
		            XSSFCell cell = sheet.getRow(i).createCell(4); 
		     
			    CreationHelper createHelper = workbook.getCreationHelper();
			    XSSFCellStyle hlink_style = workbook.createCellStyle();
			    XSSFFont hlink_font = workbook.createFont();
			    hlink_font.setUnderline(XSSFFont.U_SINGLE);
			    hlink_font.setColor(HSSFColor.BLUE.index);
			    hlink_style.setFont(hlink_font);
			    cell.setCellValue("Screenshots");
			    XSSFHyperlink hp = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.FILE);
			    String FileAddress = Screenshot;
			    
			    hp.setAddress(FileAddress);
			    cell.setHyperlink((XSSFHyperlink) hp);
			    cell.setCellStyle(hlink_style);
			    
		        }
		    }
		    
		        
		}
		
	public void ClearExistingStatus() throws BiffException, IOException {

		for (int i = 1; i <= GetRows(); i++) {

			setData("Execution Status", i, "");

			setData("Error", i, "");

		}

	}

}