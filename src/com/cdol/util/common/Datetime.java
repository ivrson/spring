/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * �ϱ� ���α׷��� ���� ���۱��� ������ ���������� cdol�� ������,
 * cdol�� ��������� ������� �ʴ� ���, ����, ���� �� �� 3�ڿ� ���� ����, ������ ������ �����Ǹ�
 * cdol�� �������� ħ�ؿ� �ش�ȴ�.
 * Copyright (C) 2014 cdol All Rights Reserved.
 *
 *
 * @author ivrson
 * @since 2014-04-10
 * @version 1.0.0
 *
 *
 * Program		: com.cdol
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: SystemInterceptor.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140225000000][ivrson][CREATE: Initial Release]
 */
package com.cdol.util.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ivrson
 * @since 2014-04-10
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Datetime {
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @param String
	 * @return Date
	 * <p>DESCRIPTION: ��¥ ��ü ���(Get datetime object)
	 * <p>IMPORTANT
	 * <p>EXAMPLE: <code>format = "yyyy-MM-dd kk:mm:ss"</code>
	 */
	public static Date getStringToDatetime(String dateString, String format) {
		SimpleDateFormat simpleDateFormat 	= new SimpleDateFormat(format);
		ParsePosition parsePosition 		= new ParsePosition(0);
		
		return simpleDateFormat.parse(dateString, parsePosition);
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION: ��¥ ���ڿ� ���(Get datetime string)
	 * <p>IMPORTANT
	 * <p>EXAMPLE: <code>format = "yyyy-MM-dd" or "yyyy-MM-dd kk:mm:ss" or "yyyy-MM-dd kk:mm:ss SSS"</code>
	 */
	public static String getDatetimeToString(String format) {
		Calendar currentDate 		= Calendar.getInstance();
		SimpleDateFormat formatter 	= new SimpleDateFormat(format);
		String value 				= formatter.format(currentDate.getTime());
		
		return value;
	}
}