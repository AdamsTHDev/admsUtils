package com.adms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;


public class EncryptionUtil {

	private static EncryptionUtil instance;
	private static MessageDigest digester;
	private static final String MD5 = "MD5";
	
	static {
		try {
			digester = MessageDigest.getInstance(MD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static EncryptionUtil getInstance() {
		if(instance == null) {
			instance = new EncryptionUtil();
		}
		return instance;
	}
	
	public String encrypt(String str) {
		if(StringUtils.isEmpty(str)) throw new IllegalArgumentException("String for encrypt is null");
		
		digester.update(str.getBytes());
		byte[] hash = digester.digest();
		
		StringBuffer hexString = new StringBuffer();
		for(int i = 0; i < hash.length; i++) {
			if((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		
		return hexString.toString();
	}
	
}
