package com.adms.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadXlsTest {

	public static void main(String[] args) {
		
		String path = "D:/Test/read/xlsx";
		String fileName = "Sales_Report_By_Records.xlsx";
		
		try {
			
			File file = new File(path + "/" + fileName);
			System.out.println("file: " + file.getAbsoluteFile());
			System.out.println("is file: " + file.isFile());
			System.out.println("can read: " + file.canRead());
			System.out.println("len: " + file.length());
			InputStream in = new FileInputStream(file);
			
			try {
				Workbook wb = WorkbookFactory.create(in);
				
				System.out.println("sheets: " + wb.getNumberOfSheets());
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void test() {
		
	}
	
}
