package com.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import jxl.write.WriteException;
import jxl.Sheet;

public class ExcelConnection {
	Workbook book;
	Sheet sheet;
	public WritableSheet wsht;
	public Workbook wbook;
	public WritableWorkbook wwbCopy;
	static String ExecutedTestCasesSheet;
	// static Hashtable dict = new Hashtable<Object, Object>();
	Hashtable<String, Integer> dict = new Hashtable<String, Integer>();
	String WriteExcelSheetPath;
	String WriteSheetname;
	
	String WriteReportPath;
	String ReportSheetName;

	public ExcelConnection(String excelSheetPath, String Sheetname, String WritePath) {
		WriteExcelSheetPath = WritePath;
		WriteSheetname = Sheetname;
		try {
			 book = Workbook.getWorkbook(new File(excelSheetPath));
			 sheet = book.getSheet(Sheetname);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	public ExcelConnection(String excelSheetPath, String Sheetname) {
		WriteReportPath = excelSheetPath;
		ReportSheetName = Sheetname;
		try {
			 book = Workbook.getWorkbook(new File(excelSheetPath));
			 sheet = book.getSheet(Sheetname);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void setValueintocell(String FieldName, int row, String TexttoEnter) {

		Label label = new Label(getField(FieldName), row, TexttoEnter);
		try {
			wbook = Workbook.getWorkbook(new File(WriteExcelSheetPath));
			wwbCopy = Workbook.createWorkbook(new File(WriteExcelSheetPath), wbook);
			wsht = wwbCopy.getSheet(WriteSheetname);
			wsht.addCell(label);
			wwbCopy.write();
			wwbCopy.close();
			wbook.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void SetReportinExcel(String FieldName, int row, String TexttoEnter) {

		Label label = new Label(getField(FieldName), row, TexttoEnter);
		try {
			wbook = Workbook.getWorkbook(new File(WriteReportPath));
			wwbCopy = Workbook.createWorkbook(new File(WriteReportPath), wbook);
			wsht = wwbCopy.getSheet(ReportSheetName);
			wsht.addCell(label);
			wwbCopy.write();
			wwbCopy.close();
			wbook.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void CloseWritExcel() throws WriteException, IOException {

		wwbCopy.close();
		wbook.close();

	}

	public int getField(String FieldName) {

		int colNo = 0;

		for (int col = 0; col < sheet.getColumns(); col++) {
			// for (int r = 0; r < rowcount(); r++)
			// {
			String attributeName = sheet.getCell(col, 0).getContents().trim();
			if (attributeName.equalsIgnoreCase(FieldName)) {
				colNo = col;
				break;
			}

			// }

		}
		return colNo;
	}

	public Sheet[] getSheet(Sheet[] name) {
		Sheet[] sheetname = book.getSheets();
		if (sheetname.equals(name)) {
			sheetname = name;
		}
		return sheetname;

	}

	public String GetData(String FName, int rownum) {

		return readCell(getField(FName), rownum).trim();
	}

	public int rowcount() {
		return sheet.getRows();
	}

	public int rowcounth() {
		return sheet.getRows();
	}

	public String readCell(int clonum, int rownum) {
		return sheet.getCell(clonum, rownum).getContents().trim();
	}

	public void columnDictionary() {
		for (int col = 0; col < sheet.getColumns(); col++) {
			dict.put(readCell(col, 0), col);
		}
	}

	public int GetCell(String colName) {
		try {
			int value;
			value = ((Integer) dict.get(colName)).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}

}
