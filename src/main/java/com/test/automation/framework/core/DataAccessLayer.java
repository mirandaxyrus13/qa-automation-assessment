package com.test.automation.framework.core;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class DataAccessLayer {
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell excelCell;
    private static XSSFRow excelRow;
    public static String sheetname_text;

    public static String getTestData(String TCID, String headerName) throws Exception {

        excelRow = excelWSheet.getRow(0);
        int counter = 0;
        int headerIndex = 0;
        String header;
        do {
            excelCell = excelRow.getCell(counter);
            header = excelCell.getStringCellValue();
            if (header.equalsIgnoreCase(headerName)) {
                headerIndex = counter;
                break;
            } else {

            }
            counter++;
        } while (header != "");


        int counterRow = 0;
        int rowIndex = 0;
        String testCaseIDValue;
        do {
            excelCell = excelWSheet.getRow(counterRow).getCell(0);
            testCaseIDValue = excelCell.getStringCellValue().toString();
            if (testCaseIDValue.equalsIgnoreCase(TCID)) {
                rowIndex = counterRow;
                break;
            } else {

            }
            counterRow++;
        } while (testCaseIDValue != "");

        excelCell = excelWSheet.getRow(rowIndex).getCell(headerIndex);
        String testData = excelCell.getStringCellValue();
        return testData;
    }

    public static void setSheetName(String sheetname) throws Exception {
        // Open the Excel file
        sheetname_text = sheetname;
        String finalPath = System.getProperty("user.dir") + "/src/main/resources/TestData/TestData.xlsx";
        FileInputStream ExcelFile = new FileInputStream(finalPath);
        excelWBook = new XSSFWorkbook(ExcelFile);
        excelWSheet = excelWBook.getSheet(sheetname);
    }

}
