package com.adms.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static String removeDoubleSpace(String s)
	{
		return s == null ? null : StringUtils.replace(s, "  ", " ");
	}
}
