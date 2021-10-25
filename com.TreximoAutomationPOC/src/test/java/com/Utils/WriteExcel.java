package com.Utils;

import java.io.File;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcel {

	public WritableSheet wsht;
	public Workbook wbook;
	public WritableWorkbook wwbCopy;

	public WriteExcel() {

	}

	public WriteExcel(String excelSheetPath, String Sheetname) {

		try {
			wbook = Workbook.getWorkbook(new File(excelSheetPath));
			wwbCopy = Workbook.createWorkbook(new File(excelSheetPath), wbook);
			wsht = wwbCopy.getSheet(Sheetname);
			wwbCopy.close();
			wbook.close();
		} catch (Exception e) {

		}

	}

	public void setValueintocell(String FieldName, int row, String TexttoEnter) {

		Label label = new Label(getField(FieldName), row, TexttoEnter);
		try {

			wsht.addCell(label);
			wwbCopy.write();
			wwbCopy.close();
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excel Not Closed");
		}
	}

	public int getField(String FieldName) {

		int colNo = 0;

		for (int col = 0; col < wsht.getColumns(); col++) {
			// for (int r = 0; r < rowcount(); r++)
			// {
			String attributeName = wsht.getCell(col, 0).getContents().trim();
			if (attributeName.equalsIgnoreCase(FieldName)) {
				colNo = col;
				break;
			}

			// }

		}
		return colNo;
	}
}
