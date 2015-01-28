package com.adms.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Logger {

	private static final String LOG_TIME_FORMAT = "yyyyMMdd HH:mm:ss:SSS";
	private static final Locale LOG_TIME_LOCALE = Locale.US;
	private DateFormat logDf = new SimpleDateFormat(LOG_TIME_FORMAT, LOG_TIME_LOCALE);

	public static final int DEBUG = 50;
	public static final int INFO = 40;
	public static final int WARN = 30;
	public static final int ERROR = 20;
	public static final int FATAL = 10;

	public static final int STACK_TRACE_LEVEL = 5;

	private int logLevel = 40;

	private static Logger log;

	private Logger(int logLevel)
	{
		this.logLevel = logLevel;
	}

	public static Logger getLogger()
	{
		return getLogger(Logger.DEBUG);
	}

	public static Logger getLogger(int logLevel)
	{
		if (Logger.log == null)
			Logger.log = new Logger(logLevel);

		return Logger.log;
	}

	public void setLogLevel(int logLevel)
	{
		this.logLevel = logLevel;
	}

	private String getClassName()
	{
		return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL].getClassName();
	}

	private String getMethodName()
	{
		return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL].getMethodName();
	}

	private int getLineNumber()
	{
		return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVEL].getLineNumber();
	}

	private String buildMessage(String prefix, String message)
	{
		String className = getClassName();
		className = className.substring(className.lastIndexOf('.') + 1);
		String methodName = getMethodName();
		int lineNumber = getLineNumber();

		String classInfo = StringUtils.leftPad(className + "." + methodName, 50, ' ') + ":" + StringUtils.rightPad("" + lineNumber, 4);

		return new StringBuilder("").append(prefix).append(this.logDf.format(Calendar.getInstance(LOG_TIME_LOCALE).getTime())).append(" ").append(classInfo).append(" - ").append(message).toString();
	}

	private void log(String prefix, String message)
	{
		System.out.println(buildMessage(prefix, message));
	}

	private void err(String prefix, String message)
	{
		System.err.println(buildMessage(prefix, message));
	}

	public void debug(String message)
	{
		if (this.logLevel >= Logger.DEBUG)
		{
			log("DEBUG ", message);
		}
	}

	public void info(String message)
	{
		if (this.logLevel >= Logger.INFO)
		{
			log(" INFO ", message);
		}
	}

	public void warn(String message)
	{
		if (this.logLevel >= Logger.WARN)
		{
			err(" WARN ", message);
		}
	}

	public void error(String message)
	{
		if (this.logLevel >= Logger.ERROR)
		{
			err("ERROR ", message);
		}
	}

	public void fatal(String message)
	{
		if (this.logLevel >= Logger.FATAL)
		{
			err("FATAL ", message);
		}
	}
}
