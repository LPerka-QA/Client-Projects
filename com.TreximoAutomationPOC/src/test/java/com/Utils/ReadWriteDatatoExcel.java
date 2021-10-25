package com.Utils;

import java.io.IOException;
import com.Utils.ExcelConnection;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class ReadWriteDatatoExcel {
	static ExcelConnection testData = null;
	static ExcelConnection ReportData = null;
	public ReadWriteDatatoExcel() {

	}

	public ReadWriteDatatoExcel(String ExcelPath, String sheetname, String WritalPath) {
		testData = new ExcelConnection(ExcelPath, sheetname, WritalPath);
		testData.columnDictionary();

	}
	
	public ReadWriteDatatoExcel(String ReportExcelPath, String sheetname) {
		ReportData = new ExcelConnection(ReportExcelPath, sheetname);
		ReportData.columnDictionary();

	}

	public String Getdata(String FieldName, int row) {

		return testData.readCell(testData.GetCell(FieldName), row).trim();

	}
	public String ExcelReportGetdata(String FieldName, int row) {

		return ReportData.readCell(ReportData.GetCell(FieldName), row).trim();

	}
	public int GetRows() {

		return testData.rowcount();
	}
	
	public int GetRowsReport() {

		return ReportData.rowcount();
	}

	public void setData(String FieldName, int row, String TexttoEnter) throws BiffException, IOException {

		testData.setValueintocell(FieldName, row, TexttoEnter);

	}
	
	public void setReportData(String FieldName, int row, String TexttoEnter) throws BiffException, IOException {

		ReportData.SetReportinExcel(FieldName, row, TexttoEnter);
	}
	
	public void CloseWriteExcel() throws WriteException, IOException {
		
		testData.CloseWritExcel();
	}

	public void ClearExistingStatus() throws BiffException, IOException {

		for (int i = 1; i <= GetRows(); i++) {

			setData("Execution Status", i, "");

			setData("Error", i, "");

		}

	}

}
