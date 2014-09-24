package com.adms.utils;

import java.io.File;
import java.io.FileFilter;

public class ExcelFileFilter implements FileFilter {

	private final String[] extensions = new String[] {"xls", "xlsx"};
	
	@Override
	public boolean accept(File file) {
		if(file.isFile()) {
			for(String ext : extensions) {
				if(file.getName().toLowerCase().endsWith(ext)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
