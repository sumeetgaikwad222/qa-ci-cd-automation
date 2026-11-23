
package com.qa.fileRead;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

public class ReadFiles {

    // Read data from config.properties
    public String readConfig(String path, String key) throws IOException {

        Properties prop = new Properties();

        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
        }

        return prop.getProperty(key);
    }

    // Read data from Excel
    public String readExcel(String path, String sheetName,
                            int rows, int columns) throws IOException {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sheet = wb.getSheet(sheetName);

            if (sheet == null || sheet.getRow(rows) == null) {
                return "";
            }

            Cell cell = sheet.getRow(rows).getCell(columns);

            if (cell == null) {
                return "";
            }

            DataFormatter df = new DataFormatter();

            return df.formatCellValue(cell);
        }
    }

    // Write data into Excel
    public String writeExcel(String path, String sheetName,
                             int rows, int columns, String data)
            throws IOException {

        Workbook wb;

        try (FileInputStream fis = new FileInputStream(path)) {
            wb = WorkbookFactory.create(fis);
        }

        try (wb) {
            Sheet sheet = wb.getSheet(sheetName);

            if (sheet == null) {
                sheet = wb.createSheet(sheetName);
            }

            Row row = sheet.getRow(rows);

            if (row == null) {
                row = sheet.createRow(rows);
            }

            Cell cell = row.getCell(columns);

            if (cell == null) {
                cell = row.createCell(columns);
            }

            cell.setCellValue(data);

            try (FileOutputStream fos = new FileOutputStream(path)) {
                wb.write(fos);
            }
        }

        return data;
    }

    // Get last row number
    public int getLastRow(String path, String sheetName)
            throws EncryptedDocumentException, IOException {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sheet = wb.getSheet(sheetName);

            return sheet == null ? -1 : sheet.getLastRowNum();
        }
    }

    // Get last cell number
    public int getLastCell(String path, String sheetName)
            throws EncryptedDocumentException, IOException {

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = WorkbookFactory.create(fis)) {

            Sheet sheet = wb.getSheet(sheetName);

            if (sheet == null) {
                return 0;
            }

            return sheet.getRow(0).getLastCellNum();
        }
    }
}
