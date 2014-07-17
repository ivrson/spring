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
 * File			: Request.java
 * Function		:
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20140528000000][pluto@i-popcorn.co.kr][CREATE: Initial Release]
 */
package com.cdol.util.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author pluto@i-popcorn.co.kr
 * @since 2014-05-28
 * 
 * <p>DESCRIPTION
 * <p>IMPORTANT
 */
public class Request {
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getString(HttpServletRequest request, String name, String defaultValue) throws Exception {
		String value = request.getParameter(name);
		if (value == null)
			return defaultValue;
		else
			return value.trim();
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-05-28
	 * 
	 * @param HttpServletRequest
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getRemoteAddr(HttpServletRequest request) throws Exception {
		String value =  request.getRemoteAddr();
		if (value == null)
			return "";
		else
			return value.trim();
	}
		
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-03-05
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @param String
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getCookie(HttpServletRequest request, String key, String defaultValue) throws Exception {
		
		String value	= defaultValue;
		
		HttpSession session	 = ((HttpServletRequest)request).getSession();
		
		if (session != null) {
			Cookie[] cookie = ((HttpServletRequest)request).getCookies();
			if (cookie != null) {
				for (int nLoop = 0; nLoop < cookie.length; nLoop++) {
					if (cookie[nLoop].getName().equals(key))
						value = cookie[nLoop].getValue();
				}
			}
		}
		
		return value;
	}
	
	/**
	 * @author pluto@i-popcorn.co.kr
	 * @since 2014-04-15
	 * 
	 * @param HttpServletRequest
	 * @return String
	 * @throws Exception
	 * <p>DESCRIPTION
	 * <p>IMPORTANT
	 */
	public static String getServerInfo(HttpServletRequest request) {
		
		String value	= "";
		String domain	= Strings.getString(request.getServerName(), "");
		
		if (request.getServerPort() != 80 && request.getServerPort() != 443) {
			value = domain + ":" + Strings.getString(Integer.toString(request.getServerPort()), "");
		}
		else {
			value = domain;
		}
		 
		
		return value;
	}
}