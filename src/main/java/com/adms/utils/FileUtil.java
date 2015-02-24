package com.adms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtil {

	private String encodeType = "UTF-8";
	private static FileUtil instance;
	
	public static FileUtil getInstance() {
		if(instance == null) {
			instance = new FileUtil();
		}
		return instance;
	}
	
	public String writeout(File fileOut, StringBuffer contents, String encodeType) throws Exception {
		FileOutputStream fos = null;
		Writer writer = null;
		try {
			if(contents != null && contents.length() > 0) {
				if(!fileOut.exists()) {
					fileOut.createNewFile();
				}
				fos = new FileOutputStream(fileOut);
				writer = new BufferedWriter(new OutputStreamWriter(fos, encodeType));
				writer.write(contents.toString());
				
				return fileOut.getAbsolutePath();
			}
		} catch(Exception e) {
			throw e;
		} finally {
			writer.flush();
			writer.close();
			fos.close();
		}
		return null;
	}
	
	public String writeout(File fileOut, StringBuffer contents) throws Exception {
		return writeout(fileOut, contents, encodeType);
	}
	
	public void setEncodeType(String encodeType) {
		this.encodeType = encodeType;
	}
}
