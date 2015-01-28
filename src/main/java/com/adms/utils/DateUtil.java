package com.adms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Date Utility 
 * 
 */
public class DateUtil {

	/**
	 * Default value: {@value #defaultDatePattern}
	 */
	private static final String defaultDatePattern = "dd/MM/yyyy";
	
	/**
	 * Default value: {@value #defaultTimePattern}
	 */
	private static final String defaultTimePattern = "HH:mm:ss";
	
	public static String getDefaultDatePattern() {
		return defaultDatePattern;
	}
	public static String getDefaultTimePattern() {
		return defaultTimePattern;
	}
	
	/**
	 * Convert string to Date with {@link #defaultDatePattern}
	 * @param strDate the date String
	 * @return The parse date
	 */
	public static Date convStringToDate(String strDate) {
		return convStringToDate(defaultDatePattern, strDate);
	}
	
	/**
	 * Convert string to Date and time with {@link #defaultDatePattern} and {@link #defaultTimePattern}
	 * @param strDate the date String
	 * @return The parse date
	 */
	public static Date convStringToDateTime(String strDate) {
		return convStringToDate(defaultDatePattern + " " + defaultTimePattern, strDate);
	}
	
	/**
	 * Convert string to Date by pattern
	 * @param pattern the pattern style
	 * @param strDate the date String
	 * @return The parse date
	 */
	public static Date convStringToDate(String pattern, String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		
		try {
			
			if(strDate != null) {
				date = sdf.parse(strDate);
			}
			
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * Convert DateTime to String with {@link #defaultDatePattern} and {@link #defaultTimePattern}
	 * @param strDate the date String
	 * @return format date
	 */
	public static String convDateTimeToString(Date date) {
		return convDateToString(defaultDatePattern + " " + defaultTimePattern, date);
	}
	
	/**
	 * Convert Date to String with {@link #defaultDatePattern}
	 * @param strDate the date String
	 * @return format date
	 */
	public static String convDateToString(Date date) {
		return convDateToString(defaultDatePattern, date);
	}
	
	/**
	 * Convert Date to String by pattern
	 * @param pattern the pattern style
	 * @param date the date
	 * @return format date
	 */
	public static String convDateToString(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String arg = null;
		
		if(date != null) {
			arg = sdf.format(date);
		}
		
		return arg;
	}
}
