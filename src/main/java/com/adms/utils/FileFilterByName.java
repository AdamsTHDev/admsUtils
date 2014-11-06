package com.adms.utils;

import java.io.File;
import java.io.FileFilter;

public class FileFilterByName implements FileFilter {

	private String[] names;
	
	public FileFilterByName(String...names) {
		this.names = names;
	}
	
	@Override
	public boolean accept(File file) {
		for(String name : names) {
			if(file.getName().toLowerCase().contains(name.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
