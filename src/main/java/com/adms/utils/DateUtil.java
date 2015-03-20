package com.adms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	/**
	 * Default value: {@value #defaultMonthPattern}
	 */
	private static final String defaultMonthPattern = "MMM";
	
	/**
	 * Default value: {@value #defualtFullDayPattern}
	 */
	private static final String defualtFullDayPattern = "EEEE";
	
	/**
	 * 
	 * @return {@link #defaultDatePattern}
	 */
	public static String getDefaultDatePattern() {
		return defaultDatePattern;
	}
	
	/**
	 * 
	 * @return {@link #defaultTimePattern}
	 */
	public static String getDefaultTimePattern() {
		return defaultTimePattern;
	}
	
	/**
	 * 
	 * @return {@link #defaultMonthPattern}
	 */
	public static String getDefaultMonthPattern() {
		return defaultMonthPattern;
	}
	
	/**
	 * 
	 * @return {@link #defualtFullDayPattern}
	 */
	public static String getDefualtFullDayPattern() {
		return getDefualtFullDayPattern();
	}
	
	public static Calendar getCurrentCalendar() {
		return Calendar.getInstance();
	}
	
	public static Date getCurrentDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static void addDay(Calendar calendar, int noOfday) {
		calendar.add(Calendar.DATE, noOfday);
	}
	
	public static void addMonth(Calendar calendar, int noOfMonth) {
		calendar.add(Calendar.MONTH, noOfMonth);
	}
	
	public static void addYear(Calendar calendar, int noOfYear) {
		calendar.add(Calendar.YEAR, noOfYear);
	}
	
	/**
	 * Get String of month, default is {@link #defaultMonthPattern}
	 * @param month is No of month minus 1;
	 * <br/> ex: Jan is 0, Feb is 1, Dec is 11
	 * @return
	 */
	public static String getStringOfMonth(int month) {
		return getStringOfMonth(defaultMonthPattern, month);
	}
	
	/**
	 * Get String of month
	 * @param month is No of month minus 1;
	 * <br/> ex: Jan is 0, Feb is 1, Dec is 11
	 * @return
	 */
	public static String getStringOfMonth(String pattern, int month) {
		Calendar cal = getCurrentCalendar();
		cal.set(2000, month, 1);
		return convDateToString(pattern, cal.getTime());
	}
	
	/**
	 * Get Day in String
	 * @param date
	 * @return
	 */
	public static String getDayString(Date date) {
		return convDateToString(defualtFullDayPattern, date);
	}
	
	/**
	 * Get Integer of month
	 * @param month default pattern is {@link #defaultMonthPattern}
	 * @return <b>Integer of month;</b> ex: 0 is Jan
	 * @throws ParseException
	 */
	public static int getMonthNo(String month) throws ParseException {
		return getMonthNo(defaultMonthPattern, month);
	}

	
	/**
	 * Get Integer of month
	 * @param pattern is pattern of Month
	 * @param month String of Month
	 * @return <b>Integer of month;</b> ex: 0 is Jan
	 * @throws ParseException
	 */
	public static int getMonthNo(String pattern, String month) throws ParseException {
		Calendar cal = getCurrentCalendar();
		cal.setTime(convStringToDate(pattern, month));
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * Convert string to Date with {@link #defaultDatePattern}
	 * @param strDate the date String
	 * @return The parse date
	 * @throws Exception 
	 */
	public static Date convStringToDate(String strDate) throws Exception {
		return convStringToDate(defaultDatePattern, strDate);
	}
	
	/**
	 * Convert string to Date and time with {@link #defaultDatePattern} and {@link #defaultTimePattern}
	 * @param strDate the date String
	 * @return The parse date
	 * @throws Exception 
	 */
	public static Date convStringToDateTime(String strDate) throws Exception {
		return convStringToDate(defaultDatePattern + " " + defaultTimePattern, strDate);
	}
	
	/**
	 * Convert string to Date by pattern
	 * @param pattern the pattern style
	 * @param strDate the date String
	 * @return The parse date
	 * @throws ParseException 
	 */
	public static Date convStringToDate(String pattern, String strDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		
		try {
			
			if(strDate != null) {
				date = sdf.parse(strDate);
			}
			
		} catch (java.text.ParseException e) {
			throw e;
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
