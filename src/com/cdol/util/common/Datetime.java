/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF CDOL.
 * CDOL OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 CDOL ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 cdol에 있으며,
 * cdol이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * cdol의 지적재산권 침해에 해당된다.
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
	 * <p>DESCRIPTION: 날짜 객체 얻기(Get datetime object)
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
	 * <p>DESCRIPTION: 날짜 문자열 얻기(Get datetime string)
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