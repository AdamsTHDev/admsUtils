package com.adms.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileUtil {

	/**
	 * UTF-8
	 */
	private final String defaultEncodingType = "UTF-8";
	private static FileUtil instance;
	
	public static FileUtil getInstance() {
		if(instance == null) {
			instance = new FileUtil();
		}
		return instance;
	}
	
	/**
	 * For Writing file out with {@link #defaultEncodingType}
	 * @param fileOut
	 * @param contents
	 * @return absolutePath of File
	 * @throws Exception
	 */
	public String writeout(File fileOut, StringBuffer contents) throws Exception {
		return writeout(fileOut, contents, defaultEncodingType);
	}
	
	public String writeOut(String fullPath, StringBuffer contents, String encodeType) throws Exception {
		return writeout(new File(fullPath), contents, encodeType);
	}
	
	public String writeout(File fileOut, StringBuffer contents, String encodeType) throws Exception {
		FileOutputStream fos = null;
		Writer writer = null;
		try {
			if(contents != null && contents.length() > 0) {
				if(!fileOut.exists()) fileOut.createNewFile();
				
				fos = new FileOutputStream(fileOut);
				writer = new BufferedWriter(new OutputStreamWriter(fos, encodeType));
				writer.write(contents.toString());
				
				return fileOut.getAbsolutePath();
			}
		} catch(Exception e) {
			throw e;
		} finally {
			try{writer.flush();}catch(Exception e) {}
			try{writer.close();}catch(Exception e) {}
			try{fos.close();}catch(Exception e) {}
		}
		return null;
	}
	
	public void copyFile(String original, String destination) throws Exception {
		copyFile(new File(original), new File(destination));
	}
	
	public void copyFile(File original, File destination) throws Exception {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			createDirectory(original.getAbsolutePath());
			
			is = new FileInputStream(original);
			os = new FileOutputStream(destination);
			
			byte[] buffer = new byte[1024];
			
			int length;
//			copy file content in bytes
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
		} catch(Exception e) {
			throw e;
		} finally {
			try { is.close(); } catch(Exception e) {}
			try { os.close(); } catch(Exception e) {}
		}
	}
	
	public void moveFile(File original, File destination) throws Exception {
		copyFile(original, destination);
		original.delete();
	}
	
	public void moveFile(String original, String destination) throws Exception {
		moveFile(new File(original), new File(destination));
	}
	
	public void createDirectory(String path) {
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
}
