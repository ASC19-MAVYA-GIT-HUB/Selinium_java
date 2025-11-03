package com.ajio.utilities;
 
import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;
 
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelUtilities {
 
    public static Object[][] getData(String excelPath, String sheetName) throws InvalidFormatException, IOException {

        FileInputStream fis = new FileInputStream(new File(excelPath));

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);
 
        int rowCount = sheet.getPhysicalNumberOfRows();

        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
 
        Object[][] data = new Object[rowCount][colCount];
 
        for (int i = 0; i < rowCount; i++) {

            for (int j = 0; j < colCount; j++) {

                data[i][j] = sheet.getRow(i).getCell(j).toString();

            }

        }
 
        workbook.close();

        fis.close();
 
        return data;

    }

}

 