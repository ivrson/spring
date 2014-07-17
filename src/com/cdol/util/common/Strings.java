/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF I-POPCORN.CO.KR.
 * I-POPCORN.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2014 I-POPCORN.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 i-popcorn.co.kr에 있으며,
 * i-popcorn.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * i-popcorn.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2014 i-popcorn.co.kr All Rights Reserved.
 *
 *
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * @version 1.0.0
 *
 *
 * Program		: kr.co.i-popcorn.popcorn
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Strings.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.common;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Strings {
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
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
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
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
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
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
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-10
	 * 
	 * @param String
	 * @param String
	 * @return String[]
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String[] getArraybySplitter(String string, String splitter) {
		
		String array[] = null;

		if (string != null) {
			StringTokenizer stringtokenizer = new StringTokenizer(string, splitter);
			int i = 0;
			array = new String[i = stringtokenizer.countTokens()];
			for (int j = 0; j < i; j++) {
				array[j] = stringtokenizer.nextToken();
			}
		}
		
		return array;
	}
	
	/**
	 * @author jamni1010@i-popcorn.co.kr
	 * @since 2014-07-03
	 * 
	 * @param String
	 * @return String
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	
	public static String getTelString(String telno){
		StringBuffer buff = new StringBuffer();
		String telNo = "";

		Pattern tellPattern = Pattern.compile( "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})");
		Matcher  matcher = tellPattern.matcher(telno);
        if(matcher.matches()) {
        	telNo = matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
        } else {
        	telNo = telno;
        }
		buff.append(telNo);
		
		return buff.toString();
	}
}