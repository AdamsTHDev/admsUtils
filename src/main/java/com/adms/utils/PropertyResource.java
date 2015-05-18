package com.adms.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyResource {

	private static PropertyResource PROPERTY_RESOURCE;
	private Properties prop;
	private String propPath;

	private PropertyResource(String propPath)
	{
		this.propPath = propPath;
	}

	public static PropertyResource getInstance(String propPath)
	{
		if (PROPERTY_RESOURCE == null)
		{
			PROPERTY_RESOURCE = new PropertyResource(propPath);
		}

		return PROPERTY_RESOURCE;
	}

	public String getValue(String name)
			throws Exception
	{
		if (this.prop == null)
		{
			InputStream propIs = null;
			try
			{
				propIs = ClassLoader.getSystemResourceAsStream(propPath);

				this.prop = new Properties();
				this.prop.load(propIs);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
			finally
			{
				propIs.close();
			}
		}

		return prop.getProperty(name);
	}

}
