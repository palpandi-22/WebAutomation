package com.openmrs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetReader {

	
   /**
	* @author Palpandi
	* @Usage  Reads data from an Excel sheet and returns it as a 2D array.
	* @param  filePath  Path to the Excel file.
	* @param  sheetName Name of the sheet to read data from.
	* @return 2D array containing the Excel data.
	* @throws IOException If an I/O error occurs.
	* @throws Exception if an error occurs
	*/
	public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {

		FileInputStream fis = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					data[i - 1][j] = cell.toString(); // Store cell value as string
				} else {
					data[i - 1][j] = ""; // Handle empty cells
				}
			}
		}
		workbook.close();
		fis.close();
		return data;
	}
	

	
   /**
	* @author Palpandi
	* @usage Reads data from a specified column in an Excel sheet and returns it as a 1D array.
	* @param filePath Path to the Excel file.
	* @param sheetName Name of the sheet to read data from.
	* @param columnName Name of the column to read data from.
	* @return An array containing the data from the specified column.
	* @throws IOException If an I/O error occurs.
	* @throws Exception If any other error occurs.
	*/
	public static Object[] readColumnData(String filePath, String sheetName, String columnName) throws IOException {
       
		try (FileInputStream fis = new FileInputStream(new File(filePath));
            
			Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet   = workbook.getSheet(sheetName);
            int rowCount  = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);
            int colCount  = headerRow.getPhysicalNumberOfCells();

            // Find the index of the specified column
            int columnIndex = -1;
            for (int j = 0; j < colCount; j++) {
                Cell cell = headerRow.getCell(j);
                if (cell != null && columnName.equalsIgnoreCase(cell.toString())) {
                    columnIndex = j;
                    break;
                }
            }

			if (columnIndex == -1) {
				throw new IllegalArgumentException("Column name not found: " + columnName);
			}
			// Create an array to hold the data from the specified column
			Object[] columnData = new Object[rowCount - 1];
			for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(columnIndex);
				columnData[i - 1] = (cell != null) ? cell.toString() : ""; // Handle empty cells
			}

            return columnData;
        }
    }
	
	
	
	
	
	
	
	
	
}
