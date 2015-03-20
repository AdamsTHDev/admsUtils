package com.adms.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyResource {

	private String propPath;
	
	public PropertyResource(String propPath) {
		this.propPath = propPath;
	}
	
	public String getValue(String name) throws IOException {
		Properties prop = new Properties();
		InputStream propIs = null;
		try {
			propIs = new FileInputStream(propPath);
			prop.load(propIs);
			
			return prop.getProperty(name);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			propIs.close();
		}
		
		return "";
	}
	
}
