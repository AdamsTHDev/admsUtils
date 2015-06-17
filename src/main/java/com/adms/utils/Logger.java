package com.adms.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class Logger {

	private static final String LOG_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
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
	
	private OutputStream outputStream;

	public void setOutputStream(OutputStream outputStream)
	{
		this.outputStream = outputStream;
	}

	public void setLogFileName(String logFileName)
			throws FileNotFoundException
	{
		if (logFileName != null)
		{
			logFileName = logFileName.replace("logTime", "" + new SimpleDateFormat("yyyyMMdd_hhmmssSSS").format(new Date()));
			setOutputStream(new FileOutputStream(logFileName));
		}
	}

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

		String classInfo = StringUtils.leftPad(className + "." + methodName, 0, ' ') + ":" + StringUtils.rightPad("" + lineNumber, 4);

		return new StringBuilder("").append(this.logDf.format(Calendar.getInstance(LOG_TIME_LOCALE).getTime())).append(" ").append(prefix).append(" ").append(classInfo).append(" - ").append(message).toString();
	}

	private void log(String prefix, String message)
	{
		String log = buildMessage(prefix, message);
		
		System.out.println(log);
		
		if (outputStream != null)
		{
			try
			{
				outputStream.write((log + "\r\n").getBytes());
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void err(String prefix, String message, Throwable throwable)
	{
		String log = buildMessage(prefix, message);
		
		System.err.println(log);
		
		if (outputStream != null)
		{
			try
			{
				outputStream.write((log + "\r\n").getBytes());
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (throwable != null)
		{
			for (StackTraceElement stackTraceElement : throwable.getStackTrace())
			{
				try
				{
					outputStream.write(("          at " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")" + "\r\n").getBytes());
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
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

	public void warn(Object object)
	{
		warn(object.toString());
	}

	public void warn(String message)
	{
		if (this.logLevel >= Logger.WARN)
		{
			err(" WARN ", message, null);
		}
	}

	public void error(Object object)
	{
		error(object.toString());
	}

	public void error(String message)
	{
		if (this.logLevel >= Logger.ERROR)
		{
			err("ERROR ", message, null);
		}
	}

	public void error(String message, Throwable throwable)
	{
		if (this.logLevel >= Logger.ERROR)
		{
			err("ERROR ", message, throwable);
		}
	}

	public void fatal(String message)
	{
		if (this.logLevel >= Logger.FATAL)
		{
			err("FATAL ", message, null);
		}
	}
}
