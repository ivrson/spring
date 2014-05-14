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
 * @since 2014-02-25
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

/**
 * @author ivrson
 * @since 2014-04-10
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Strings {
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param Object
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getString(Object object) {
		if (object == null) {
			return "";
		}
		else {
			return (String)object;
		}
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param Ojbect
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getString(Object object, String defaultValue) {
		if (object == null)
			return defaultValue;
		else if (((String)object).length() < 1)
			return defaultValue;
		else
			return (String)object;
	}
	
	/**
	 * @author ivrson
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @param String
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getReplaced(String string, String oldString, String newString) {
	
		String convert 	= new String();
		int position	= 0;
		int begin 		= 0;
		
		position = string.indexOf(oldString);
		
		if (position == -1) {
			return string;
		}
		else
		{
			while (position != -1) {
				convert 	= convert + string.substring(begin, position) + newString;
				begin 		= position + oldString.length();
				position 	= string.indexOf(oldString, begin);
			}
			
			convert = convert + string.substring(begin);
			
			return convert;
		}
	}

}