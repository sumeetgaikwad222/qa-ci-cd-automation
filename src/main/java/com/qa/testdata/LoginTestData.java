package com.qa.testdata;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

import com.qa.fileRead.ReadFiles;

public class LoginTestData {
	@DataProvider(name = "LoginData")
	public Object[][] LoginData() throws EncryptedDocumentException, IOException
	{
		String path = "./src/test/resources/sheet.xlsx";
		ReadFiles rf = new ReadFiles(); 
		int row = rf.getLastRow(path, "LoginData");
		int cell = rf.getLastCell(path,"LoginData");
		Object[][] data = new Object[row][cell];
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < cell; j++) {
				data[i-1][j] = rf.readExcel(path, "LoginData", i, j);
			}
		}
		return data;
	}
}
